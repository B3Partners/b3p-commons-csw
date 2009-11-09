/*
 * Copyright 2009 B3Partners BV
 * 
 */

package nl.b3p.csw.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.validation.Schema;
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
        this.request = CswRequestCreator.createSimpleCswRequest(queryString);
    }

    public InputBySearch(JAXBElement<GetRecordsType> request) {
        this.request = request;
    }

    public InputBySearch(Document document, Schema schema) throws JAXBException, JDOMException {
        try {
            this.request = (JAXBElement<GetRecordsType>)MarshallUtil.unMarshall(document, schema);
        } catch (ClassCastException e) {
            throw new ClassCastException("Root element of input document is not of type JAXBElement<GetRecordsType>, as expected.");
        }
    }


    @Override
    public JAXBElement<GetRecordsType> getRequest() {
        return (JAXBElement<GetRecordsType>)super.getRequest();
    }

}
