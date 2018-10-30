/*
 * Copyright 2009 B3Partners BV
 * 
 */

package nl.b3p.csw.util;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.io.ParseException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.naming.OperationNotSupportedException;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.TransformerException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.geotools.geometry.jts.WKTReader2;
import org.geotools.xml.Encoder;
import org.jdom2.JDOMException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.xml.sax.SAXException;

/**
 *
 * @author Erik van de Pol
 */
public class Util {
    protected final static Log log = LogFactory.getLog(Util.class);

    protected final static int SRID_RIJKSDRIEHOEKSTELSEL = 28992;

    protected final static String GML3_NAMESPACE = "http://www.opengis.net/gml";
    protected final static String GML3_BASEGEOM_OBJECT = "_Geometry";
    protected final static QName GML3_BASEGEOM_QNAME = new QName(GML3_NAMESPACE, GML3_BASEGEOM_OBJECT);
    
    public static JAXBElement readWkt(String wktFilter) throws ParseException, OperationNotSupportedException, IOException, JDOMException, JAXBException, NoSuchAuthorityCodeException, FactoryException, SAXException, TransformerException {
        GeometryFactory gf = new GeometryFactory(new PrecisionModel(), SRID_RIJKSDRIEHOEKSTELSEL);
        Geometry geom = new WKTReader2(gf).read(wktFilter);

        Encoder encoder = new Encoder(new org.geotools.gml3.GMLConfiguration());
        org.w3c.dom.Document w3cDomDoc = encoder.encodeAsDOM(geom, GML3_BASEGEOM_QNAME);

        JAXBContext jaxbContext = JAXBContext.newInstance("nl.b3p.csw.jaxb.gml");
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        return (JAXBElement)unmarshaller.unmarshal(w3cDomDoc);
    }

    public static Schema createSchema(String path) throws SAXException, URISyntaxException {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URI uri = cl.getResource(path).toURI();
        return sf.newSchema(new File(uri));
    }



}
