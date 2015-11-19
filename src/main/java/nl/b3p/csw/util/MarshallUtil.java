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

    protected final static String CSW_PACKAGE =      "nl.b3p.csw.jaxb.csw";
    protected final static String ELEMENTS_PACKAGE = "nl.b3p.csw.jaxb.elements";
    protected final static String FILTER_PACKAGE =   "nl.b3p.csw.jaxb.filter";
    protected final static String GML_PACKAGE =      "nl.b3p.csw.jaxb.gml";
    protected final static String OWS_PACKAGE =      "nl.b3p.csw.jaxb.ows";
    protected final static String TERMS_PACKAGE =    "nl.b3p.csw.jaxb.terms";
    protected final static String SEPARATOR =        ":";

    protected static String JAXB_PACKAGES = 
        CSW_PACKAGE + SEPARATOR +
        ELEMENTS_PACKAGE + SEPARATOR +
        FILTER_PACKAGE + SEPARATOR +
        GML_PACKAGE + SEPARATOR +
        OWS_PACKAGE + SEPARATOR +
        TERMS_PACKAGE;
   
    public static void addPackage(String packageName) {
        JAXB_PACKAGES += SEPARATOR + packageName;
    }

    //javax.xml.bind.PropertyException: property "com.sun.xml.bind.namespacePrefixMapper" 
    //must be an instance of type com.sun.xml.bind.marshaller.NamespacePrefixMapper, 
    //not nl.b3p.csw.util.CswNamespaceMapper
    
    //com.sun.xml.bind.marshaller.NamespacePrefixMapper

    public static String marshall(JAXBElement input, Schema schema) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(JAXB_PACKAGES);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setSchema(schema);
        marshaller.setProperty("jaxb.formatted.output", true);
        marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new CswNamespaceMapper());

        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(input, stringWriter);
        return stringWriter.toString();
    }

    public static JAXBElement unMarshall(org.w3c.dom.Document xmlDocument, Schema schema, Class clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(JAXB_PACKAGES);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setSchema(schema);

        return unmarshaller.unmarshal(xmlDocument, clazz);
    }

    public static JAXBElement unMarshall(Document xmlDocument, Schema schema, Class clazz) throws JAXBException, JDOMException {
        // transform to w3c dom to be able to use jaxb to unmarshal.
        DOMOutputter domOutputter = new DOMOutputter();
        org.w3c.dom.Document w3cDomDoc = domOutputter.output(xmlDocument);

        return unMarshall(w3cDomDoc, schema, clazz);
    }

}
