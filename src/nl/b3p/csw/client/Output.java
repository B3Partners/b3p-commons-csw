/*
 * Copyright 2009 B3Partners BV
 * 
 */
package nl.b3p.csw.client;

import java.io.File;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
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
import org.jdom.input.DOMBuilder;
import org.jdom.output.DOMOutputter;
import org.jdom.transform.JDOMResult;
import org.jdom.transform.JDOMSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Erik van de Pol
 */
public class Output {
    
    protected static final Log log = LogFactory.getLog(Output.class);

    protected static final Namespace gmdNameSpace = Namespace.getNamespace("http://www.isotc211.org/2005/gmd");
    protected static final Namespace gcoNameSpace = Namespace.getNamespace("http://www.isotc211.org/2005/gco");

    protected static final String cswResponseXsdPath = "c:\\dev_erik\\b3p-commons-csw\\jaxb\\xsds\\csw-response.xsd";
    protected static Schema cswResponseSchema = null;
    protected static boolean validate = true;
    
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

    public GetRecordsResponse getGetRecordsResponse() throws JDOMException, JAXBException {
        if (validate) {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            try {
                cswResponseSchema = sf.newSchema(new File(cswResponseXsdPath));
            } catch (SAXException saxe) {
                System.err.println("No validation possible. File '" + cswResponseXsdPath + "'; " +
                        saxe.getLocalizedMessage());
                cswResponseSchema = null;
            }
        }

        JAXBContext jaxbContext = JAXBContext.newInstance("nl.b3p.csw.jaxb.response");
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setSchema(cswResponseSchema);

        // transform to w3c dom to be able to use jaxb to unmarshal.
        DOMOutputter domOutputter = new DOMOutputter();
        org.w3c.dom.Document w3cDomDoc = domOutputter.output(xmlDocument);

        return (GetRecordsResponse)unmarshaller.unmarshal(w3cDomDoc);
    }

    public List<org.w3c.dom.Element> getSearchResultsW3C() throws JDOMException, JAXBException {
        GetRecordsResponse response = getGetRecordsResponse();
        return response.getSearchResults().getAny();
    }

    public List<Element> getSearchResults() throws JDOMException, JAXBException {
        List<org.w3c.dom.Element> w3cList = getSearchResultsW3C();
        List<Element> jdomList = new ArrayList<Element>(w3cList.size());

        // transform to jdom list
        DOMBuilder domBuilder = new DOMBuilder();
        for (org.w3c.dom.Element w3cElem : w3cList) {
            jdomList.add(domBuilder.build(w3cElem));
        }

        return jdomList;
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
