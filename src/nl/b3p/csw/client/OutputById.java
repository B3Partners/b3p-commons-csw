/*
 * Copyright 2009 B3Partners BV
 * 
 */

package nl.b3p.csw.client;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.validation.Schema;
import nl.b3p.csw.jaxb.csw.GetRecordByIdResponse;
import nl.b3p.csw.jaxb.csw.GetRecordByIdResponseType;
import nl.b3p.csw.util.ExceptionUtil;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.DOMBuilder;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Erik van de Pol
 */
public class OutputById extends Output {

    public OutputById(Document xmlDocument) {
        super(xmlDocument);
    }

    public OutputById(Document xmlDocument, Schema schema) {
        super(xmlDocument, schema);
    }

    @Override
    public GetRecordByIdResponse getResponse() throws JDOMException, JAXBException, OwsException {
        return new GetRecordByIdResponse((GetRecordByIdResponseType)super.getResponse().getValue());
    }

    @Override
    protected Class getTargetType() {
        return GetRecordByIdResponseType.class;
    }

    public org.w3c.dom.Element getSearchResultW3C() throws JDOMException, JAXBException, OwsException {
        List<org.w3c.dom.Element> searchResults = getSearchResultsW3C();
        if (searchResults.size() != 1)
            throw new JDOMException("Search result not found or multiple search results.");
        return searchResults.get(0);
    }

    @Override
    public List<org.w3c.dom.Element> getSearchResultsW3C() throws JDOMException, JAXBException, OwsException {
        return getResponse().getValue().getAny();
    }

    public Element getSearchResult() throws JDOMException, JAXBException, IOException, OwsException {
        // IE can't handle xml outputted by this DOMBuilder and/or created by org.w3c.dom (not sure if it's both or one of them)
        //return new DOMBuilder().build(getSearchResultW3C());
        return xmlDocument.getRootElement().getChild("MD_Metadata", gmdNameSpace);//ugly like this
    }

    public String getSearchResultString() throws JDOMException, JAXBException, IOException, OwsException {
        return new XMLOutputter().outputString(getSearchResult());
    }

}
