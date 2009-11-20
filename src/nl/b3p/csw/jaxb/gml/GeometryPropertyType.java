//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.13 at 09:11:40 PM CET 
//


package nl.b3p.csw.jaxb.gml;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * A geometric property can either be any geometry element encapsulated in an element of this type or an XLink reference 
 * 			to a remote geometry element (where remote includes geometry elements located elsewhere in the same document). Note that either 
 * 			the reference or the contained element must be given, but not both or none.
 * 
 * <p>Java class for GeometryPropertyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GeometryPropertyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element ref="{http://www.opengis.net/gml}_Geometry"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.opengis.net/gml}AssociationAttributeGroup"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeometryPropertyType", propOrder = {
    "geometry"
})
public class GeometryPropertyType {

    @XmlElementRef(name = "_Geometry", namespace = "http://www.opengis.net/gml", type = AbstractGeometry.class)
    protected JAXBElement<? extends AbstractGeometryType> geometry;
    @XmlAttribute(namespace = "http://www.opengis.net/gml")
    @XmlSchemaType(name = "anyURI")
    protected String remoteSchema;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    protected String type;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anyURI")
    protected String href;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anyURI")
    protected String role;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    @XmlSchemaType(name = "anyURI")
    protected String arcrole;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    protected String title;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    protected String show;
    @XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
    protected String actuate;

    /**
     * Default no-arg constructor
     * 
     */
    public GeometryPropertyType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public GeometryPropertyType(final JAXBElement<? extends AbstractGeometryType> geometry, final String remoteSchema, final String type, final String href, final String role, final String arcrole, final String title, final String show, final String actuate) {
        this.geometry = geometry;
        this.remoteSchema = remoteSchema;
        this.type = type;
        this.href = href;
        this.role = role;
        this.arcrole = arcrole;
        this.title = title;
        this.show = show;
        this.actuate = actuate;
    }

    /**
     * Gets the value of the geometry property.
     * 
     * @return
     *     possible object is
     *     {@link AbstractSolid }
     *     {@link AbstractCurve }
     *     {@link AbstractRing }
     *     {@link Curve }
     *     {@link LineString }
     *     {@link MultiPolygon }
     *     {@link Surface }
     *     {@link OrientableCurve }
     *     {@link GeometricAggregate }
     *     {@link MultiPoint }
     *     {@link OrientableSurface }
     *     {@link MultiSurface }
     *     {@link AbstractSurface }
     *     {@link Solid }
     *     {@link Ring }
     *     {@link PolyhedralSurface }
     *     {@link Polygon }
     *     {@link MultiCurve }
     *     {@link TriangulatedSurface }
     *     {@link Tin }
     *     {@link AbstractGeometry }
     *     {@link LinearRing }
     *     {@link Point }
     *     {@link GeometricPrimitive }
     *     {@link MultiLineString }
     *     {@link MultiSolid }
     *     {@link MultiGeometry }
     *     
     */
    public JAXBElement<? extends AbstractGeometryType> getGeometry() {
        return geometry;
    }

    /**
     * Sets the value of the geometry property.
     * 
     * @param value
     *     allowed object is
     *     {@link AbstractSolid }
     *     {@link AbstractCurve }
     *     {@link AbstractRing }
     *     {@link Curve }
     *     {@link LineString }
     *     {@link MultiPolygon }
     *     {@link Surface }
     *     {@link OrientableCurve }
     *     {@link GeometricAggregate }
     *     {@link MultiPoint }
     *     {@link OrientableSurface }
     *     {@link MultiSurface }
     *     {@link AbstractSurface }
     *     {@link Solid }
     *     {@link Ring }
     *     {@link PolyhedralSurface }
     *     {@link Polygon }
     *     {@link MultiCurve }
     *     {@link TriangulatedSurface }
     *     {@link Tin }
     *     {@link AbstractGeometry }
     *     {@link LinearRing }
     *     {@link Point }
     *     {@link GeometricPrimitive }
     *     {@link MultiLineString }
     *     {@link MultiSolid }
     *     {@link MultiGeometry }
     *     
     */
    public void setGeometry(JAXBElement<? extends AbstractGeometryType> value) {
        this.geometry = ((JAXBElement<? extends AbstractGeometryType> ) value);
    }

    /**
     * Gets the value of the remoteSchema property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemoteSchema() {
        return remoteSchema;
    }

    /**
     * Sets the value of the remoteSchema property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemoteSchema(String value) {
        this.remoteSchema = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        if (type == null) {
            return "simple";
        } else {
            return type;
        }
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the href property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets the value of the href property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHref(String value) {
        this.href = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRole(String value) {
        this.role = value;
    }

    /**
     * Gets the value of the arcrole property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArcrole() {
        return arcrole;
    }

    /**
     * Sets the value of the arcrole property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArcrole(String value) {
        this.arcrole = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the show property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShow() {
        return show;
    }

    /**
     * Sets the value of the show property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShow(String value) {
        this.show = value;
    }

    /**
     * Gets the value of the actuate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActuate() {
        return actuate;
    }

    /**
     * Sets the value of the actuate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActuate(String value) {
        this.actuate = value;
    }

}