//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.06.10 at 04:19:23 PM CEST 
//


package nl.b3p.csw.jaxb.csw;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class ElementSetName
    extends JAXBElement<ElementSetNameType>
{

    protected final static QName NAME = new QName("http://www.opengis.net/cat/csw/2.0.2", "ElementSetName");

    public ElementSetName(ElementSetNameType value) {
        super(NAME, ((Class) ElementSetNameType.class), null, value);
    }

    public ElementSetName() {
        super(NAME, ((Class) ElementSetNameType.class), null, null);
    }

}