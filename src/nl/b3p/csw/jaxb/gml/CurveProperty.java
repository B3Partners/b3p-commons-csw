//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.06.10 at 04:19:23 PM CEST 
//


package nl.b3p.csw.jaxb.gml;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class CurveProperty
    extends JAXBElement<CurvePropertyType>
{

    protected final static QName NAME = new QName("http://www.opengis.net/gml", "curveProperty");

    public CurveProperty(CurvePropertyType value) {
        super(NAME, ((Class) CurvePropertyType.class), null, value);
    }

    public CurveProperty() {
        super(NAME, ((Class) CurvePropertyType.class), null, null);
    }

}
