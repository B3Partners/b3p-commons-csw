/*
 * Copyright 2009 B3Partners BV
 * 
 */

package nl.b3p.csw.util;

import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import nl.b3p.csw.jaxb.csw.RequestBaseType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.output.DOMOutputter;

/**
 *
 * @author Erik van de Pol
 */
public class MarshallUtil {

    protected static Log log = LogFactory.getLog(MarshallUtil.class);

    public static String marshall(JAXBElement<? extends RequestBaseType> request) throws JAXBException {
        if (request == null) {
            throw new IllegalArgumentException("Csw request not set.");
        }

        JAXBContext jaxbContext = JAXBContext.newInstance("nl.b3p.csw.jaxb.csw");
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty("jaxb.formatted.output", true);

        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(request, stringWriter);
        return stringWriter.toString();
    }

    public static JAXBElement unMarshall(Document xmlDocument) throws JAXBException, JDOMException {
        Schema schema = null;//validate ? getResponseSchema() : null;

        JAXBContext jaxbContext = JAXBContext.newInstance("nl.b3p.csw.jaxb.csw");
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setSchema(schema);

        // transform to w3c dom to be able to use jaxb to unmarshal.
        DOMOutputter domOutputter = new DOMOutputter();
        org.w3c.dom.Document w3cDomDoc = domOutputter.output(xmlDocument);

        return (JAXBElement)unmarshaller.unmarshal(w3cDomDoc);
    }

    /*protected Schema getResponseSchema() {
        if (cswResponseSchema == null) {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            try {
                cswResponseSchema = sf.newSchema(new File(cswResponseXsdPath));
            } catch (SAXException saxe) {
                log.error("No validation possible. File '" + cswResponseXsdPath + "'.", saxe);
                cswResponseSchema = null;
            }
        }
        return cswResponseSchema;
    }*/

    //protected static final String cswResponseXsdPath = "c:\\dev_erik\\b3p-commons-csw\\jaxb\\xsds\\csw-response.xsd";
    //protected static final boolean defaultValidate = false;
    //protected static Schema cswResponseSchema = null;
}
