//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.06.10 at 04:19:23 PM CEST 
//


package nl.b3p.csw.jaxb.terms;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import nl.b3p.csw.jaxb.elements.SimpleLiteral;

public class Replaces
    extends JAXBElement<SimpleLiteral>
{

    protected final static QName NAME = new QName("http://purl.org/dc/terms/", "replaces");

    public Replaces(SimpleLiteral value) {
        super(NAME, ((Class) SimpleLiteral.class), null, value);
    }

    public Replaces() {
        super(NAME, ((Class) SimpleLiteral.class), null, null);
    }

}
