//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.11 at 06:55:45 PM CET 
//


package nl.b3p.csw.jaxb.gml;

import java.math.BigInteger;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * OrientableSurface consists of a surface and an orientation. If the orientation is "+", then the OrientableSurface is identical to the baseSurface. If the orientation is "-", then the OrientableSurface is a reference to a Surface with an up-normal that reverses the direction for this OrientableSurface, the sense of "the top of the surface".
 * 
 * <p>Java class for OrientableSurfaceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrientableSurfaceType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractSurfaceType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}baseSurface"/>
 *       &lt;/sequence>
 *       &lt;attribute name="orientation" type="{http://www.opengis.net/gml}SignType" default="+" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrientableSurfaceType", propOrder = {
    "baseSurface"
})
public class OrientableSurfaceType
    extends AbstractSurfaceType
{

    @XmlElementRef(name = "baseSurface", namespace = "http://www.opengis.net/gml", type = BaseSurface.class)
    protected BaseSurface baseSurface;
    @XmlAttribute
    protected String orientation;

    /**
     * Default no-arg constructor
     * 
     */
    public OrientableSurfaceType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public OrientableSurfaceType(final List<MetaDataProperty> metaDataProperty, final Description description, final List<Name> name, final String id, final String gid, final String srsName, final BigInteger srsDimension, final List<String> axisLabels, final List<String> uomLabels, final BaseSurface baseSurface, final String orientation) {
        super(metaDataProperty, description, name, id, gid, srsName, srsDimension, axisLabels, uomLabels);
        this.baseSurface = baseSurface;
        this.orientation = orientation;
    }

    /**
     * References or contains the base surface (positive orientation).
     * 
     * @return
     *     possible object is
     *     {@link BaseSurface }
     *     
     */
    public BaseSurface getBaseSurface() {
        return baseSurface;
    }

    /**
     * Sets the value of the baseSurface property.
     * 
     * @param value
     *     allowed object is
     *     {@link BaseSurface }
     *     
     */
    public void setBaseSurface(BaseSurface value) {
        this.baseSurface = value;
    }

    /**
     * Gets the value of the orientation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrientation() {
        if (orientation == null) {
            return "+";
        } else {
            return orientation;
        }
    }

    /**
     * Sets the value of the orientation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrientation(String value) {
        this.orientation = value;
    }

}
