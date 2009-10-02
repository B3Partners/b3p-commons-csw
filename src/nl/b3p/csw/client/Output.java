/*
 * Copyright 2009 B3Partners BV
 * 
 */
package nl.b3p.csw.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import nl.b3p.csw.jaxb.response.GetRecordsResponse;
import nl.b3p.csw.util.OnlineResource;
import nl.b3p.csw.util.Protocol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.filter.ElementFilter;
import org.jdom.output.DOMOutputter;
import org.jdom.transform.JDOMResult;
import org.jdom.transform.JDOMSource;

/**
 *
 * @author Erik van de Pol
 */
public class Output {
    
    protected static final Log log = LogFactory.getLog(Output.class);

    protected static Namespace gmdNameSpace = Namespace.getNamespace("http://www.isotc211.org/2005/gmd");
    protected static Namespace gcoNameSpace = Namespace.getNamespace("http://www.isotc211.org/2005/gco");

    // TODO: deze staat hard op ISO 19139. Andere standaarden toevoegen?
    protected static final ElementFilter resultElementFilter = new ElementFilter("MD_Metadata", gmdNameSpace);
    protected static final ElementFilter resourceElementFilter = new ElementFilter("CI_OnlineResource", gmdNameSpace);

    protected Document xmlDocument;


    public Output(Document xmlDocument) {
        this.xmlDocument = xmlDocument;
    }

    public Document getXml() {
        return xmlDocument;
    }

    // BUG: result list seems empty
    public GetRecordsResponse getGetRecordsResponse() throws JDOMException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance("nl.b3p.csw.jaxb.response");
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        // transform to w3c dom to be able to use jaxb to unmarshall.
        DOMOutputter domOutputter = new DOMOutputter();
        org.w3c.dom.Document w3cDomDoc = domOutputter.output(xmlDocument);
        return (GetRecordsResponse)unmarshaller.unmarshal(w3cDomDoc);
        
        //return (GetRecordsResponse)unmarshaller.unmarshal(new JDOMSource(xmlDocument));
    }

    public Document getTransformedXml(String transformPath) throws TransformerException {
        try {
            Transformer transformer =
                    TransformerFactory.newInstance().newTransformer(new StreamSource(transformPath));
            JDOMSource in = new JDOMSource(xmlDocument);
            JDOMResult out = new JDOMResult();
            transformer.transform(in, out);
            return out.getDocument();
        } catch (TransformerException e) {
            throw new TransformerException(
                    "Xml Csw response could not be transformed with " + transformPath +
                    ".\nXml: " + xmlDocument.toString(), e);
        }
    }

    public Map<URI, List<OnlineResource>> getResourcesMap() {
        return getResourcesMap(Protocol.WMS);
    }

    public Map<URI, List<OnlineResource>> getResourcesMap(Protocol protocolFilter) {
        Map<URI, List<OnlineResource>> services = new HashMap<URI, List<OnlineResource>>();
        Element rootElement = xmlDocument.getRootElement();
        if (rootElement != null) {

            Iterator<Element> results = rootElement.getDescendants(resultElementFilter);
            while (results.hasNext()) {
                Element resultElem = results.next();

                Iterator<Element> resources = resultElem.getDescendants(resourceElementFilter);
                while (resources.hasNext()) {
                    Element resourceElem = resources.next();

                    handleResource(resourceElem, protocolFilter, services);
                }
            }
        }
        return services;
    }

    private void handleResource(Element resourceElem, Protocol protocolFilter, Map<URI, List<OnlineResource>> services) {
        URI url = null;
        String protocol = null;
        String name = null;
        String desc = null;

        try {
            Element linkageElem = resourceElem.getChild("linkage", gmdNameSpace);
            if (linkageElem != null) {
                Element URLElem = linkageElem.getChild("URL", gmdNameSpace);
                if (URLElem != null) {
                    url = new URI(URLElem.getTextTrim());
                }
            }
            Element protocolElem = resourceElem.getChild("protocol", gmdNameSpace);
            if (protocolElem != null) {
                Element SV_ServiceTypeElem = protocolElem.getChild("SV_ServiceType", gmdNameSpace);
                if (SV_ServiceTypeElem != null) {
                    protocol = SV_ServiceTypeElem.getTextTrim();
                }
            }
            Element nameElem = resourceElem.getChild("name", gmdNameSpace);
            if (nameElem != null) {
                Element nameStringElem = nameElem.getChild("CharacterString", gcoNameSpace);
                if (nameStringElem != null) {
                    name = nameStringElem.getTextTrim();
                }
            }
            Element descElem = resourceElem.getChild("description", gmdNameSpace);
            if (descElem != null) {
                Element descStringElem = descElem.getChild("CharacterString", gcoNameSpace);
                if (descStringElem != null) {
                    desc = descStringElem.getTextTrim();
                }
            }
            if (url != null && name != null && (protocol == null || protocol.length() == 0 || protocol.toUpperCase().equals(protocolFilter.getName().toUpperCase()))) {
                Protocol forcedProtocol = Protocol.WMS;
                if (services.get(url) == null) {
                    services.put(url, new ArrayList<OnlineResource>());
                }
                OnlineResource onlineResource = new OnlineResource();
                onlineResource.setUrl(url);
                onlineResource.setName(name);
                onlineResource.setDescription(desc);
                onlineResource.setProtocol(forcedProtocol);
                services.get(url).add(onlineResource);
            }
        } catch (URISyntaxException ex) {
            // move on to the next item
        }
    }
}
