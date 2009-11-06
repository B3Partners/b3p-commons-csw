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
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import nl.b3p.csw.jaxb.csw.GetRecordsResponseType;
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

/**
 *
 * @author Erik van de Pol
 *
 * TODO: check for return type. make that gettable.
 */
public class Output {
    
    protected static final Log log = LogFactory.getLog(Output.class);

    protected static final Namespace gmdNameSpace = Namespace.getNamespace("http://www.isotc211.org/2005/gmd");
    protected static final Namespace gcoNameSpace = Namespace.getNamespace("http://www.isotc211.org/2005/gco");

    //protected static final String cswResponseXsdPath = "c:\\dev_erik\\b3p-commons-csw\\jaxb\\xsds\\csw-response.xsd";
    //protected static final boolean defaultValidate = false;
    //protected static Schema cswResponseSchema = null;
    
    // TODO: deze staat hard op ISO 19139. Andere standaarden toevoegen?
    protected static final ElementFilter resultElementFilter = new ElementFilter("MD_Metadata", gmdNameSpace);
    protected static final ElementFilter resourceElementFilter = new ElementFilter("CI_OnlineResource", gmdNameSpace);

    protected Document xmlDocument = null;
    protected JAXBElement response = null;


    public Output(Document xmlDocument) {
        this.xmlDocument = xmlDocument;
    }

    public Document getXml() {
        return xmlDocument;
    }

    /*protected Schema getResponseSchema() {
        if (cswResponseSchema == null) {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            try {
                cswResponseSchema = sf.newSchema(new File(cswResponseXsdPath));
            } catch (SAXException saxe) {
                log.error("No validation possible. File '" + cswResponseXsdPath + "'.", saxe);
                cswResponseSchema = null;
            }
        }
        return cswResponseSchema;
    }*/

    public JAXBElement getResponse() throws JDOMException, JAXBException {
        /*return getGetRecordsResponse(defaultValidate);
    }

    public GetRecordsResponse getGetRecordsResponse(boolean validate) throws JDOMException, JAXBException {*/
        if (response == null) {
            Schema schema = null;//validate ? getResponseSchema() : null;

            JAXBContext jaxbContext = JAXBContext.newInstance("nl.b3p.csw.jaxb.csw");
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setSchema(schema);

            // transform to w3c dom to be able to use jaxb to unmarshal.
            DOMOutputter domOutputter = new DOMOutputter();
            org.w3c.dom.Document w3cDomDoc = domOutputter.output(xmlDocument);

            response = (JAXBElement)unmarshaller.unmarshal(w3cDomDoc);
        }
        return response;
    }

    // TODO: is een beetje lelijk. structuur overdenken.
    public JAXBElement<GetRecordsResponseType> getGetRecordsResponse() throws JDOMException, JAXBException {
        return (JAXBElement<GetRecordsResponseType>)getResponse();
    }

    public List<org.w3c.dom.Element> getSearchResultsW3C() throws JDOMException, JAXBException {
        JAXBElement<GetRecordsResponseType> getRecordsResponse = getGetRecordsResponse();
        return getRecordsResponse.getValue().getSearchResults().getAny();
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

    public List<Document> getSearchResultsAsDocuments() throws JDOMException, JAXBException {
        List<Element> elemList = getSearchResults();
        List<Document> docList = new ArrayList<Document>(elemList.size());

        // transform to jdom doc list
        for (Element elem : elemList) {
            docList.add(new Document(elem));
        }

        return docList;
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
