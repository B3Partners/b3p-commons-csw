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
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.validation.Schema;
import nl.b3p.csw.jaxb.csw.GetRecordsResponseType;
import nl.b3p.csw.util.OnlineResource;
import nl.b3p.csw.util.Protocol;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.DOMBuilder;

/**
 *
 * @author Erik van de Pol
 */
public class OutputBySearch extends Output {
    protected final static Protocol defaultProtocol = Protocol.WMS;
    protected final static List<Protocol> defaultAllowedProtocols;

    static {
        defaultAllowedProtocols = new ArrayList<Protocol>();
        defaultAllowedProtocols.add(defaultProtocol);
    }

    public OutputBySearch(Document xmlDocument) {
        super(xmlDocument);
    }

    public OutputBySearch(Document xmlDocument, Schema schema) {
        super(xmlDocument, schema);
    }

    @Override
    public JAXBElement<GetRecordsResponseType> getResponse() throws JDOMException, JAXBException {
        return super.getResponse();
    }

    public List<org.w3c.dom.Element> getSearchResultsW3C() throws JDOMException, JAXBException {
        JAXBElement<GetRecordsResponseType> getRecordsResponse = getResponse();
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

        for (List<OnlineResource> list : getResourcesMap().values()) {
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

                Iterator<Element> resources = resultElem.getDescendants(resourceElementFilter);
                while (resources.hasNext()) {
                    Element resourceElem = resources.next();

                    OnlineResource onlineResource = getResource(resourceElem, allowedProtocols);

                    if (onlineResource != null) {
                        URI url = onlineResource.getUrl();
                        if (services.get(url) == null) {
                            services.put(url, new ArrayList<OnlineResource>());
                        }

                        services.get(url).add(onlineResource);
                    }
                }
            }
        }
        return services;
    }

    private OnlineResource getResource(Element resourceElem, List<Protocol> allowedProtocols) {
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
                Element SV_ServiceTypeElem = protocolElem.getChild("SV_ServiceType", gmdNameSpace);
                if (SV_ServiceTypeElem != null) {
                    String protocolText = SV_ServiceTypeElem.getTextTrim();
                    try {
                        protocol = Protocol.fromValue(protocolText);
                    } catch (Exception e) {
                        protocol = defaultProtocol;
                    }
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
            if (url != null && name != null && 
                    (allowedProtocols.isEmpty() || allowedProtocols.contains(protocol))
                ) {

                OnlineResource onlineResource = new OnlineResource();

                onlineResource.setUrl(url);
                onlineResource.setName(name);
                onlineResource.setDescription(desc);
                onlineResource.setProtocol(protocol);

                return onlineResource;
            }
        } catch (URISyntaxException ex) {
            log.error(ex);
        }
        return null;
    }

}