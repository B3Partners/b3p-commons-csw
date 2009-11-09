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
