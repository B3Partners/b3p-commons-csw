//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.10 at 09:11:16 PM CET 
//


package nl.b3p.csw.jaxb.gml;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class AbstractSolid
    extends JAXBElement<AbstractSolidType>
{

    protected final static QName NAME = new QName("http://www.opengis.net/gml", "_Solid");

    public AbstractSolid(AbstractSolidType value) {
        super(NAME, ((Class) AbstractSolidType.class), null, value);
    }

    public AbstractSolid() {
        super(NAME, ((Class) AbstractSolidType.class), null, null);
    }

}
