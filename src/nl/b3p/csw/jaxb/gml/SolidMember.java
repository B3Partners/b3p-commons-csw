//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.11 at 06:55:45 PM CET 
//


package nl.b3p.csw.jaxb.gml;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class SolidMember
    extends JAXBElement<SolidPropertyType>
{

    protected final static QName NAME = new QName("http://www.opengis.net/gml", "solidMember");

    public SolidMember(SolidPropertyType value) {
        super(NAME, ((Class) SolidPropertyType.class), null, value);
    }

    public SolidMember() {
        super(NAME, ((Class) SolidPropertyType.class), null, null);
    }

}
