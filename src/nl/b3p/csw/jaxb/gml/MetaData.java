//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.11 at 06:55:45 PM CET 
//


package nl.b3p.csw.jaxb.gml;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class MetaData
    extends JAXBElement<AbstractMetaDataType>
{

    protected final static QName NAME = new QName("http://www.opengis.net/gml", "_MetaData");

    public MetaData(AbstractMetaDataType value) {
        super(NAME, ((Class) AbstractMetaDataType.class), null, value);
    }

    public MetaData() {
        super(NAME, ((Class) AbstractMetaDataType.class), null, null);
    }

}
