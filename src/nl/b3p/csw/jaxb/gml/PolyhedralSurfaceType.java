//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.11 at 06:55:45 PM CET 
//


package nl.b3p.csw.jaxb.gml;

import java.math.BigInteger;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * A polyhedral surface is a surface composed
 *    of polygon surfaces connected along their common boundary 
 *    curves. This differs from the surface type only in the
 *    restriction on the types of surface patches acceptable.
 * 
 * <p>Java class for PolyhedralSurfaceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PolyhedralSurfaceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.opengis.net/gml}SurfaceType">
 *       &lt;sequence>
 *         &lt;group ref="{http://www.opengis.net/gml}StandardObjectProperties"/>
 *         &lt;element ref="{http://www.opengis.net/gml}polygonPatches"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PolyhedralSurfaceType")
public class PolyhedralSurfaceType
    extends SurfaceType
{


    /**
     * Default no-arg constructor
     * 
     */
    public PolyhedralSurfaceType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public PolyhedralSurfaceType(final List<MetaDataProperty> metaDataProperty, final Description description, final List<Name> name, final String id, final String gid, final String srsName, final BigInteger srsDimension, final List<String> axisLabels, final List<String> uomLabels, final JAXBElement<? extends SurfacePatchArrayPropertyType> patches) {
        super(metaDataProperty, description, name, id, gid, srsName, srsDimension, axisLabels, uomLabels, patches);
    }

}
