//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.06.10 at 04:19:23 PM CEST 
//


package nl.b3p.csw.jaxb.gml;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class GriddedSurface
    extends JAXBElement<AbstractGriddedSurfaceType>
{

    protected final static QName NAME = new QName("http://www.opengis.net/gml", "_GriddedSurface");

    public GriddedSurface(AbstractGriddedSurfaceType value) {
        super(NAME, ((Class) AbstractGriddedSurfaceType.class), null, value);
    }

    public GriddedSurface() {
        super(NAME, ((Class) AbstractGriddedSurfaceType.class), null, null);
    }

}
