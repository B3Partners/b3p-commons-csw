/*
 * Copyright 2009 B3Partners BV
 * 
 */

package nl.b3p.csw.util;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.ParseException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.naming.OperationNotSupportedException;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import nl.b3p.csw.jaxb.gml.AbstractGeometry;
import nl.b3p.csw.jaxb.gml.AbstractGeometryType;
import org.geotools.geometry.jts.WKTReader2;
import org.geotools.xml.DocumentWriter;
import org.geotools.xml.gml.GMLSchema;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.DOMOutputter;
import org.xml.sax.SAXException;

/**
 *
 * @author Erik van de Pol
 */
public class Util {
    
    public static JAXBElement<AbstractGeometryType> readWkt(String wktFilter) throws ParseException, OperationNotSupportedException, IOException, JDOMException, JAXBException {
        // nogal wat omwegen: wkt string -> geotools gml3 object -> xml string -> mijn jaxb gml3 object
        Geometry gtGeom = new WKTReader2().read(wktFilter);

        StringWriter stringWriter = new StringWriter();
        DocumentWriter.writeFragment(gtGeom, GMLSchema.getInstance(), stringWriter, null);

        Document jdomDoc = new SAXBuilder(false).build(new StringReader(stringWriter.toString()));
        DOMOutputter domOutputter = new DOMOutputter();
        org.w3c.dom.Document w3cDomDoc = domOutputter.output(jdomDoc);

        JAXBContext jaxbContext = JAXBContext.newInstance(AbstractGeometryType.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        return unmarshaller.unmarshal(w3cDomDoc, AbstractGeometryType.class);
    }

    public static Schema createSchema(String path) throws SAXException {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        return sf.newSchema(new File(path));
    }



}
