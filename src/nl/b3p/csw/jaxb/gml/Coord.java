//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.13 at 09:11:40 PM CET 
//


package nl.b3p.csw.jaxb.gml;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class Coord
    extends JAXBElement<CoordType>
{

    protected final static QName NAME = new QName("http://www.opengis.net/gml", "coord");

    public Coord(CoordType value) {
        super(NAME, ((Class) CoordType.class), null, value);
    }

    public Coord() {
        super(NAME, ((Class) CoordType.class), null, null);
    }

}