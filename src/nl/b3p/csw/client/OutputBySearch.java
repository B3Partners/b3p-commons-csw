/*
 * Copyright 2009 B3Partners BV
 * 
 */
package nl.b3p.csw.client;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.validation.Schema;
import nl.b3p.csw.jaxb.csw.GetRecordsResponse;
import nl.b3p.csw.jaxb.csw.GetRecordsResponseType;
import nl.b3p.csw.jaxb.csw.SearchResultsType;
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

    public boolean isEmpty() {
        try {
            return xmlDocument.getRootElement().getChild("SearchResults", cswNameSpace).getAttribute("numberOfRecordsReturned").getIntValue() == 0;
        } catch (Exception ex) {
            return true;
        }
    }

    @Override
    public GetRecordsResponse getResponse() throws JDOMException, JAXBException, OwsException {
        JAXBElement<GetRecordsResponseType> jaxbElement = super.getResponse();
        return new GetRecordsResponse(jaxbElement.getValue());
    }

    @Override
    protected Class getTargetType() {
        return GetRecordsResponseType.class;
    }

    public SearchResultsType getSearchResultsObject() throws JDOMException, JAXBException, OwsException{
        if (getResponse()==null ||
                getResponse().getValue()==null ||
                getResponse().getValue().getSearchResults()==null)
            return null;
        return getResponse().getValue().getSearchResults();
    }

    @Override
    public List<org.w3c.dom.Element> getSearchResultsW3C() throws JDOMException, JAXBException, OwsException {
        return getSearchResultsObject().getAny();
    }

    /*public List<Document> getSearchResultsAsDocuments() throws JDOMException, JAXBException {
    List<Element> elemList = getSearchResults();
    List<Document> docList = new ArrayList<Document>(elemList.size());

    // transform to jdom doc list
    for (Element elem : elemList) {
    docList.add(new Document(elem));// werkt niet als niet detached. detachen is niet handig voor algehele consistentie van deze klasse.
    }

    return docList;
    }*/

    public BigInteger getNumberOfRecordsMatched() throws JDOMException, JAXBException, OwsException{
        SearchResultsType results =getSearchResultsObject();
        if (results==null){
            return null;
        }
        return results.getNumberOfRecordsMatched();
    }
    public BigInteger getNumberOfRecordsReturned() throws JDOMException, JAXBException, OwsException{
        SearchResultsType results =getSearchResultsObject();
        if (results==null){
            return null;
        }
        return results.getNumberOfRecordsReturned();
    }
    public BigInteger getNextRecord() throws JDOMException, JAXBException, OwsException{
        SearchResultsType results =getSearchResultsObject();
        if (results==null){
            return null;
        }
        return results.getNextRecord();
    }

}
