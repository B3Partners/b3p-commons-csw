/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.b3p.csw.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.validation.Schema;
import nl.b3p.csw.jaxb.csw.RequestBaseType;
import nl.b3p.csw.util.MarshallUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.JDOMException;


/**
 *
 * @author Erik van de Pol
 */
public abstract class Input {
    protected static final Log log = LogFactory.getLog(Input.class);

    protected JAXBElement<? extends RequestBaseType> request = null;

    protected Input() {
        
    }

    public Input(JAXBElement<? extends RequestBaseType> request) {
        this.request = request;
    }

    /*public Input(Document document, Schema schema) throws JAXBException, JDOMException {
        this.request = MarshallUtil.unMarshall(document, schema, getTargetType());
    }*/

    protected JAXBElement<? extends RequestBaseType> getRequest() {
        return request;
    }

    protected abstract Class getTargetType();
    
}
