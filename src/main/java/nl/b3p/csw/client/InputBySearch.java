/*
 * Copyright 2009 B3Partners BV
 * 
 */

package nl.b3p.csw.client;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.validation.Schema;
import nl.b3p.csw.jaxb.csw.GetRecords;
import nl.b3p.csw.jaxb.csw.GetRecordsType;
import nl.b3p.csw.util.MarshallUtil;
import org.jdom.Document;
import org.jdom.JDOMException;

/**
 *
 * @author Erik van de Pol
 */
public class InputBySearch extends Input {

    public InputBySearch(String queryString) {
        this(queryString,null,null);
    }

    public InputBySearch(String queryString,BigInteger maxRecords,BigInteger startPosition) {
        GetRecords getRecords=CswRequestCreator.createSimpleCswRequest(queryString);
        if (maxRecords!=null)
            getRecords.getValue().setMaxRecords(maxRecords);
        if (startPosition!=null)
            getRecords.getValue().setStartPosition(startPosition);
        this.request = getRecords;
    }

    public InputBySearch(JAXBElement<GetRecordsType> request) {
        this.request = request;
    }

    public InputBySearch(Document document, Schema schema) throws JAXBException, JDOMException {
        try {
            this.request = (JAXBElement<GetRecordsType>)MarshallUtil.unMarshall(document, schema, getTargetType());
        } catch (ClassCastException e) {
            throw new ClassCastException("Root element of input document is not of type JAXBElement<GetRecordsType>, as expected.");
        }
    }


    @Override
    public JAXBElement<GetRecordsType> getRequest() {
        return (JAXBElement<GetRecordsType>)super.getRequest();
    }

    @Override
    protected Class getTargetType() {
        return GetRecordsType.class;
    }

}
