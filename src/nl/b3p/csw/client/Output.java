/*
 * Copyright 2009 B3Partners BV
 * 
 */
package nl.b3p.csw.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import nl.b3p.csw.util.ExceptionUtil;
import nl.b3p.csw.util.MarshallUtil;
import nl.b3p.csw.util.OnlineResource;
import nl.b3p.csw.util.Protocol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.Text;
import org.jdom.filter.ElementFilter;
import org.jdom.input.DOMBuilder;
import org.jdom.output.XMLOutputter;
import org.jdom.transform.JDOMResult;
import org.jdom.transform.JDOMSource;

/**
 *
 * @author Erik van de Pol
 *
 */
public abstract class Output implements Iterable<Element> {

    protected static final Log log = LogFactory.getLog(Output.class);
    protected static final Namespace cswNameSpace = Namespace.getNamespace("http://www.opengis.net/cat/csw/2.0.2");
    protected static final Namespace gmdNameSpace = Namespace.getNamespace("http://www.isotc211.org/2005/gmd");
    protected static final Namespace gcoNameSpace = Namespace.getNamespace("http://www.isotc211.org/2005/gco");
    protected static final Namespace cswPrefixNameSpace = Namespace.getNamespace("csw","http://www.opengis.net/cat/csw/2.0.2");
    protected static final Namespace gmdPrefixNameSpace = Namespace.getNamespace("gmd","http://www.isotc211.org/2005/gmd");
    protected static final Namespace gcoPrefixNameSpace = Namespace.getNamespace("gco","http://www.isotc211.org/2005/gco");
    // TODO: deze staat hard op ISO 19139. Andere standaarden toevoegen?
    protected static final ElementFilter resultElementFilter = new ElementFilter("MD_Metadata", gmdNameSpace);
    protected static final ElementFilter resourceElementFilter = new ElementFilter("CI_OnlineResource", gmdNameSpace);
    protected static final ElementFilter fileIdentifierElementFilter = new ElementFilter("fileIdentifier", gmdNameSpace);
    //for jdom
    protected static org.jdom.xpath.XPath titleJdomXPath;
    protected static org.jdom.xpath.XPath keywordsJdomXPath;
    protected static org.jdom.xpath.XPath identificationDateJdomXPath;
    protected static org.jdom.xpath.XPath responsibleOrganisationNameJdomXPath;
    protected static org.jdom.xpath.XPath dateStampJdomXPath;
    protected static org.jdom.xpath.XPath abstractJdomXPath;

    protected static final Protocol defaultProtocol = Protocol.WMS;
    protected static final List<Protocol> defaultAllowedProtocols;

    protected static final String exceptionName = "ExceptionReport";

    static {
        defaultAllowedProtocols = new ArrayList<Protocol>();
        defaultAllowedProtocols.add(defaultProtocol);

        try {
            titleJdomXPath = org.jdom.xpath.XPath.newInstance("gmd:identificationInfo/gmd:MD_DataIdentification/gmd:citation/gmd:CI_Citation/gmd:title/gco:CharacterString/text()");
            titleJdomXPath.addNamespace(gmdPrefixNameSpace);
            titleJdomXPath.addNamespace(gcoPrefixNameSpace);
            keywordsJdomXPath = org.jdom.xpath.XPath.newInstance("gmd:identificationInfo/gmd:MD_DataIdentification/gmd:descriptiveKeywords/gmd:MD_Keywords/gmd:keyword/gco:CharacterString/text()");
            keywordsJdomXPath.addNamespace(gmdPrefixNameSpace);
            keywordsJdomXPath.addNamespace(gcoPrefixNameSpace);
            identificationDateJdomXPath = org.jdom.xpath.XPath.newInstance("gmd:identificationInfo/gmd:MD_DataIdentification/gmd:citation/gmd:CI_Citation/gmd:date/gmd:CI_Date/gmd:date/gco:Date/text()");
            identificationDateJdomXPath.addNamespace(gmdPrefixNameSpace);
            identificationDateJdomXPath.addNamespace(gcoPrefixNameSpace);
            responsibleOrganisationNameJdomXPath = org.jdom.xpath.XPath.newInstance("gmd:contact/gmd:CI_ResponsibleParty/gmd:organisationName/gco:CharacterString/text()");
            responsibleOrganisationNameJdomXPath.addNamespace(gmdPrefixNameSpace);
            responsibleOrganisationNameJdomXPath.addNamespace(gcoPrefixNameSpace);
            dateStampJdomXPath = org.jdom.xpath.XPath.newInstance("gmd:dateStamp/gco:Date/text()");
            dateStampJdomXPath.addNamespace(gmdPrefixNameSpace);
            dateStampJdomXPath.addNamespace(gcoPrefixNameSpace);
            abstractJdomXPath = org.jdom.xpath.XPath.newInstance("gmd:identificationInfo/gmd:MD_DataIdentification/gmd:abstract/gco:CharacterString/text()");
            abstractJdomXPath.addNamespace(gmdPrefixNameSpace);
            abstractJdomXPath.addNamespace(gcoPrefixNameSpace);
            
        } catch (JDOMException ex) {
            log.error("Error creating xpath expressions");
        }
    }

    protected Document xmlDocument = null;
    protected JAXBElement response = null;
    protected Schema schema = null;

    public Output(Document xmlDocument) {
        this.xmlDocument = xmlDocument;
    }

    public Output(Document xmlDocument, Schema schema) {
        this(xmlDocument);
        this.schema = schema;
    }

    public Document getXml() {
        return xmlDocument;
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
                    "Xml Csw response could not be transformed with " + transformPath
                    + ".\nXml: " + xmlDocument.toString(), e);
        }
    }

    protected JAXBElement getResponse() throws JDOMException, JAXBException, OwsException {
        ExceptionUtil.throwExceptionIfException(xmlDocument);
        
        return getResponseImpl();
    }

    private JAXBElement getResponseImpl() throws JDOMException, JAXBException {
        if (response == null) {
            response = MarshallUtil.unMarshall(xmlDocument, schema, getTargetType());
        }
        return response;
    }

    protected abstract Class getTargetType();

    @Override
    public Iterator<Element> iterator() {
        List<Element> jdomList = null;
        try {
            jdomList = getSearchResults();
        } catch(Exception ex) {
            log.error(ex);
            return null;
        }
        return jdomList.iterator();
    }

    public abstract List<org.w3c.dom.Element> getSearchResultsW3C() throws JDOMException, JAXBException, OwsException;

    public List<Element> getSearchResults() throws JDOMException, JAXBException, OwsException {
        List<org.w3c.dom.Element> w3cList = getSearchResultsW3C();
        List<Element> jdomList = new ArrayList<Element>(w3cList.size());

        // transform to jdom list
        DOMBuilder domBuilder = new DOMBuilder();
        for (org.w3c.dom.Element w3cElem : w3cList) {
            jdomList.add(domBuilder.build(w3cElem));
        }

        return jdomList;
    }

    public String getSearchResultsString() throws JDOMException, JAXBException, IOException, OwsException {
        return new XMLOutputter().outputString(getSearchResults());
    }

    /**
     * List of OnlineResource's. If the same resource-URL is included in more result-metadata,
     * all are included in this list.
     * @return List of OnlineResource's.
     */
    public List<OnlineResource> getResourcesFlattened() {
        return getResourcesFlattened(defaultAllowedProtocols);
    }

    /**
     * List of OnlineResource's. If the same resource-URL is included in more result-metadata,
     * all are included in this list.
     * @param allowedProtocols Online resource protocols that are allowed in the search results.
     * An empty list indicates all Protocol's are allowed.
     * @return List of OnlineResource's.
     */
    public List<OnlineResource> getResourcesFlattened(List<Protocol> allowedProtocols) {
        List<OnlineResource> resources = new ArrayList<OnlineResource>();

        Map<URI, List<OnlineResource>> resourcesMap = getResourcesMap(allowedProtocols);

        for (List<OnlineResource> list : resourcesMap.values()) {
            resources.addAll(list);
        }

        return resources;
    }

    /**
     * List of OnlineResource's grouped by URI.
     * @return List of OnlineResource's grouped by URI.
     */
    public Map<URI, List<OnlineResource>> getResourcesMap() {
        return getResourcesMap(defaultAllowedProtocols);
    }

    /**
     * List of OnlineResource's grouped by URI.
     * @param allowedProtocols Online resource protocols that are allowed in the search results.
     * An empty list indicates all Protocol's are allowed.
     * @return List of OnlineResource's grouped by URI.
     */
    public Map<URI, List<OnlineResource>> getResourcesMap(List<Protocol> allowedProtocols) {
        Map<URI, List<OnlineResource>> services = new HashMap<URI, List<OnlineResource>>();
        Element rootElement = xmlDocument.getRootElement();
        if (rootElement != null) {

            Iterator<Element> results = rootElement.getDescendants(resultElementFilter);
            while (results.hasNext()) {
                Element resultElem = results.next();

                Map<URI, List<OnlineResource>> resultServices = getResourcesMap(resultElem, allowedProtocols);
                for (Map.Entry<URI, List<OnlineResource>> resultService : resultServices.entrySet()) {
                    URI key = resultService.getKey();
                    List<OnlineResource> value = resultService.getValue();
                    // TODO: Dit moet een Set worden ipv een List! (duplicaten eruit). OnlineResource als Comparable? implementeren op url en name.
                    if (services.containsKey(key)) {
                        services.get(key).addAll(value);
                    } else {
                        services.putAll(resultServices);
                    }
                }
            }
        }
        return services;
    }

    public Map<URI, List<OnlineResource>> getResourcesMap(Element resultElem, List<Protocol> allowedProtocols) throws UnsupportedOperationException {
        Map<URI, List<OnlineResource>> services = new HashMap<URI, List<OnlineResource>>();

        //int resourceId = 0;
        //String uuid = getUUID(resultElem);

        Iterator<Element> resources = resultElem.getDescendants(resourceElementFilter);
        while (resources.hasNext()) {
            Element resourceElem = resources.next();

            OnlineResource onlineResource = getResource(resourceElem, allowedProtocols, resultElem);

            if (onlineResource != null) {
                //onlineResource.setUUID(uuid + ";" + resourceId);

                URI url = onlineResource.getUrl();
                if (services.get(url) == null) {
                    services.put(url, new ArrayList<OnlineResource>());
                }
                services.get(url).add(onlineResource);
            }
            //resourceId++;
        }

        return services;
    }

    public String getUUID(Element rootElement) throws UnsupportedOperationException {
        Iterator<Element> fileIdentifierResult = rootElement.getDescendants(fileIdentifierElementFilter);
        if (!fileIdentifierResult.hasNext()) {
            throw new UnsupportedOperationException("No UUID found for metadata.");
        }
        Element fileIdentifier = fileIdentifierResult.next();
        return fileIdentifier.getChildTextTrim("CharacterString", gcoNameSpace);
    }

    public String getTitle(Element recordElement) throws JDOMException {
        return titleJdomXPath.valueOf(recordElement);
    }

    public List<Text> getKeyWords(Element recordElement) throws JDOMException {
        return (List<Text>) keywordsJdomXPath.selectNodes(recordElement);
    }

    public String getIdentificationDate(Element recordElement) throws JDOMException {
        return identificationDateJdomXPath.valueOf(recordElement);
    }

    public String getResponsibleOrganisationName(Element recordElement) throws JDOMException {
        return responsibleOrganisationNameJdomXPath.valueOf(recordElement);
    }

    public String getDateStamp(Element recordElement) throws JDOMException{
        return dateStampJdomXPath.valueOf(recordElement);
    }

    public String getAbstractText(Element recordElement) throws JDOMException{
        return abstractJdomXPath.valueOf(recordElement);
    }
    private OnlineResource getResource(Element resourceElem, List<Protocol> allowedProtocols, Element metadataElement) {
        URI url = null;
        Protocol protocol = null;
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
                protocol = getProtocol(protocolElem);
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
            if (url != null && name != null
                    && (allowedProtocols.isEmpty() || allowedProtocols.contains(protocol))) {

                OnlineResource onlineResource = new OnlineResource();

                onlineResource.setUrl(url);
                onlineResource.setName(name);
                onlineResource.setDescription(desc);
                onlineResource.setProtocol(protocol);
                //log.debug("md:\n" + new XMLOutputter().outputString(metadataElement));
                onlineResource.setMetadata(metadataElement);
                //log.debug(onlineResource);

                return onlineResource;
            }
        } catch (URISyntaxException ex) {
            log.error(ex);
        }
        return null;
    }

    private Protocol getProtocol(Element protocolElem) {
        Protocol protocol = null;

        Element SV_ServiceTypeElem = protocolElem.getChild("SV_ServiceType", gmdNameSpace);
        // onderstaand element is voor compatibiliteit. Is eigenlijk niet correct.
        Element protocolStringElem = protocolElem.getChild("CharacterString", gcoNameSpace);

        if (SV_ServiceTypeElem != null) {
            String protocolText = SV_ServiceTypeElem.getTextTrim();
            try {
                protocol = Protocol.fromValue(protocolText);
            } catch (Exception e) {
            }
        } else if (protocolStringElem != null) {
            String protocolText = protocolStringElem.getTextTrim();
            try {
                protocol = Protocol.fromValue(protocolText);
            } catch (Exception e) {
            }
        }

        if (protocol == null) {
            protocol = defaultProtocol;
        }
        return protocol;
    }
}
