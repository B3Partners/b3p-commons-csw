/*
 * Copyright 2009 B3Partners BV
 * 
 */

package nl.b3p.csw.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.validation.Schema;
import nl.b3p.csw.jaxb.csw.GetRecordByIdResponseType;
import org.jdom.Document;
import org.jdom.JDOMException;

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
    public JAXBElement<GetRecordByIdResponseType> getResponse() throws JDOMException, JAXBException {
        return super.getResponse();
    }

}
