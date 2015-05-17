/*
 * Copyright 2009 B3Partners BV
 * 
 */

package nl.b3p.csw.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.validation.Schema;
import nl.b3p.csw.jaxb.csw.GetRecordByIdType;
import nl.b3p.csw.util.MarshallUtil;
import org.jdom.Document;
import org.jdom.JDOMException;

/**
 *
 * @author Erik van de Pol
 */
public class InputById extends Input {

    public InputById(String idString) {
        this.request = CswRequestCreator.createGetRecordByIdRequest(idString);
    }
    
    public InputById(String[] idsString) {
        this.request = CswRequestCreator.createGetRecordByIdRequest(idsString);
    }

    public InputById(JAXBElement<GetRecordByIdType> request) {
        this.request = request;
    }

    public InputById(Document document, Schema schema) throws JAXBException, JDOMException {
        try {
            this.request = (JAXBElement<GetRecordByIdType>)MarshallUtil.unMarshall(document, schema, getTargetType());
        } catch (ClassCastException e) {
            throw new ClassCastException("Root element of input document is not of type JAXBElement<GetRecordByIdType>, as expected.");
        }
    }


    @Override
    public JAXBElement<GetRecordByIdType> getRequest() {
        return (JAXBElement<GetRecordByIdType>)super.getRequest();
    }

    @Override
    protected Class getTargetType() {
        return GetRecordByIdType.class;
    }

}
