/*
 * Copyright 2009 B3Partners BV
 * 
 */

package nl.b3p.csw.util;

import java.io.File;
import java.io.StringWriter;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import nl.b3p.csw.jaxb.csw.RequestBaseType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.output.DOMOutputter;
import org.xml.sax.SAXException;

/**
 *
 * @author Erik van de Pol
 */
public class MarshallUtil {

    protected static Log log = LogFactory.getLog(MarshallUtil.class);

    public static String marshall(JAXBElement input, Schema schema) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance("nl.b3p.csw.jaxb.csw");
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setSchema(schema);
        marshaller.setProperty("jaxb.formatted.output", true);

        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(input, stringWriter);
        return stringWriter.toString();
    }

    public static JAXBElement unMarshall(org.w3c.dom.Document xmlDocument, Schema schema) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance("nl.b3p.csw.jaxb.csw");
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setSchema(schema);

        return (JAXBElement)unmarshaller.unmarshal(xmlDocument);
    }

    public static JAXBElement unMarshall(Document xmlDocument, Schema schema) throws JAXBException, JDOMException {
        // transform to w3c dom to be able to use jaxb to unmarshal.
        DOMOutputter domOutputter = new DOMOutputter();
        org.w3c.dom.Document w3cDomDoc = domOutputter.output(xmlDocument);

        return unMarshall(w3cDomDoc, schema);
    }

    public static Schema createSchema(String path) {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            return sf.newSchema(new File(path));
        } catch (SAXException saxe) {
            log.error("No validation possible. File '" + path + "'.", saxe);
            return null;
        }
    }

}
