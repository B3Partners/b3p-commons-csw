//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.13 at 09:11:40 PM CET 
//


package nl.b3p.csw.jaxb.gml;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class Seconds
    extends JAXBElement<BigDecimal>
{

    protected final static QName NAME = new QName("http://www.opengis.net/gml", "seconds");

    public Seconds(BigDecimal value) {
        super(NAME, ((Class) BigDecimal.class), null, value);
    }

    public Seconds() {
        super(NAME, ((Class) BigDecimal.class), null, null);
    }

}
