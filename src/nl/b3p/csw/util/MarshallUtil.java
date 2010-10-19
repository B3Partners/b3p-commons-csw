/*
 * Copyright 2009 B3Partners BV
 * 
 */

package nl.b3p.csw.util;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import org.apache.commons.lang.StringUtils;
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

    protected final static Set<String> JAXB_PACKAGES_LIST = new HashSet<String>(Arrays.asList(
        CSW_PACKAGE,
        ELEMENTS_PACKAGE,
        FILTER_PACKAGE,
        GML_PACKAGE,
        OWS_PACKAGE,
        TERMS_PACKAGE
    ));

    protected static String JAXB_PACKAGES = createPackagesString(JAXB_PACKAGES_LIST);
    

    public static void addPackage(String packageName) {
        JAXB_PACKAGES_LIST.add(packageName);
        JAXB_PACKAGES = createPackagesString(JAXB_PACKAGES_LIST);
    }

    private static String createPackagesString(Set<String> packagesList) {
        return StringUtils.join(packagesList, SEPARATOR);
    }

    public static String marshall(JAXBElement input, Schema schema) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(JAXB_PACKAGES);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setSchema(schema);
        marshaller.setProperty("jaxb.formatted.output", true);

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
