//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.11 at 06:55:45 PM CET 
//


package nl.b3p.csw.jaxb.gml;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * A Polygon is a special surface that is defined by a single surface patch. The boundary of this patch is coplanar and the polygon uses planar interpolation in its interior. It is backwards compatible with the Polygon of GML 2, GM_Polygon of ISO 19107 is implemented by PolygonPatch.
 * 
 * <p>Java class for PolygonType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PolygonType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractSurfaceType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}exterior" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/gml}interior" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PolygonType", propOrder = {
    "exterior",
    "interior"
})
public class PolygonType
    extends AbstractSurfaceType
{

    @XmlElementRef(name = "exterior", namespace = "http://www.opengis.net/gml", type = Exterior.class)
    protected JAXBElement<AbstractRingPropertyType> exterior;
    @XmlElementRef(name = "interior", namespace = "http://www.opengis.net/gml", type = Interior.class)
    protected List<JAXBElement<AbstractRingPropertyType>> interior;

    /**
     * Default no-arg constructor
     * 
     */
    public PolygonType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public PolygonType(final List<MetaDataProperty> metaDataProperty, final Description description, final List<Name> name, final String id, final String gid, final String srsName, final BigInteger srsDimension, final List<String> axisLabels, final List<String> uomLabels, final JAXBElement<AbstractRingPropertyType> exterior, final List<JAXBElement<AbstractRingPropertyType>> interior) {
        super(metaDataProperty, description, name, id, gid, srsName, srsDimension, axisLabels, uomLabels);
        this.exterior = exterior;
        this.interior = interior;
    }

    /**
     * Gets the value of the exterior property.
     * 
     * @return
     *     possible object is
     *     {@link OuterBoundaryIs }
     *     {@link Exterior }
     *     
     */
    public JAXBElement<AbstractRingPropertyType> getExterior() {
        return exterior;
    }

    /**
     * Sets the value of the exterior property.
     * 
     * @param value
     *     allowed object is
     *     {@link OuterBoundaryIs }
     *     {@link Exterior }
     *     
     */
    public void setExterior(JAXBElement<AbstractRingPropertyType> value) {
        this.exterior = ((JAXBElement<AbstractRingPropertyType> ) value);
    }

    /**
     * Gets the value of the interior property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the interior property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInterior().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Interior }
     * {@link InnerBoundaryIs }
     * 
     * 
     */
    public List<JAXBElement<AbstractRingPropertyType>> getInterior() {
        if (interior == null) {
            interior = new ArrayList<JAXBElement<AbstractRingPropertyType>>();
        }
        return this.interior;
    }

}
