//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.06.10 at 04:19:23 PM CEST 
//


package nl.b3p.csw.jaxb.filter;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import nl.b3p.csw.jaxb.gml.AbstractCurve;
import nl.b3p.csw.jaxb.gml.AbstractGeometry;
import nl.b3p.csw.jaxb.gml.AbstractGeometryType;
import nl.b3p.csw.jaxb.gml.AbstractRing;
import nl.b3p.csw.jaxb.gml.AbstractSolid;
import nl.b3p.csw.jaxb.gml.AbstractSurface;
import nl.b3p.csw.jaxb.gml.Curve;
import nl.b3p.csw.jaxb.gml.Envelope;
import nl.b3p.csw.jaxb.gml.GeometricAggregate;
import nl.b3p.csw.jaxb.gml.GeometricPrimitive;
import nl.b3p.csw.jaxb.gml.LineString;
import nl.b3p.csw.jaxb.gml.LinearRing;
import nl.b3p.csw.jaxb.gml.MultiCurve;
import nl.b3p.csw.jaxb.gml.MultiGeometry;
import nl.b3p.csw.jaxb.gml.MultiLineString;
import nl.b3p.csw.jaxb.gml.MultiPoint;
import nl.b3p.csw.jaxb.gml.MultiPolygon;
import nl.b3p.csw.jaxb.gml.MultiSolid;
import nl.b3p.csw.jaxb.gml.MultiSurface;
import nl.b3p.csw.jaxb.gml.OrientableCurve;
import nl.b3p.csw.jaxb.gml.OrientableSurface;
import nl.b3p.csw.jaxb.gml.Point;
import nl.b3p.csw.jaxb.gml.Polygon;
import nl.b3p.csw.jaxb.gml.PolyhedralSurface;
import nl.b3p.csw.jaxb.gml.Ring;
import nl.b3p.csw.jaxb.gml.Solid;
import nl.b3p.csw.jaxb.gml.Surface;
import nl.b3p.csw.jaxb.gml.Tin;
import nl.b3p.csw.jaxb.gml.TriangulatedSurface;


/**
 * <p>Java class for BinarySpatialOpType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BinarySpatialOpType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/ogc}SpatialOpsType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/ogc}PropertyName"/>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/gml}_Geometry"/>
 *           &lt;element ref="{http://www.opengis.net/gml}Envelope"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BinarySpatialOpType", propOrder = {
    "propertyName",
    "geometry",
    "envelope"
})
public class BinarySpatialOpType
    extends SpatialOpsType
{

    @XmlElementRef(name = "PropertyName", namespace = "http://www.opengis.net/ogc", type = PropertyName.class)
    protected PropertyName propertyName;
    @XmlElementRef(name = "_Geometry", namespace = "http://www.opengis.net/gml", type = AbstractGeometry.class)
    protected JAXBElement<? extends AbstractGeometryType> geometry;
    @XmlElementRef(name = "Envelope", namespace = "http://www.opengis.net/gml", type = Envelope.class)
    protected Envelope envelope;

    /**
     * Default no-arg constructor
     * 
     */
    public BinarySpatialOpType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public BinarySpatialOpType(final PropertyName propertyName, final JAXBElement<? extends AbstractGeometryType> geometry, final Envelope envelope) {
        super();
        this.propertyName = propertyName;
        this.geometry = geometry;
        this.envelope = envelope;
    }

    /**
     * Gets the value of the propertyName property.
     * 
     * @return
     *     possible object is
     *     {@link PropertyName }
     *     
     */
    public PropertyName getPropertyName() {
        return propertyName;
    }

    /**
     * Sets the value of the propertyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertyName }
     *     
     */
    public void setPropertyName(PropertyName value) {
        this.propertyName = value;
    }

    /**
     * Gets the value of the geometry property.
     * 
     * @return
     *     possible object is
     *     {@link Curve }
     *     {@link AbstractGeometry }
     *     {@link Solid }
     *     {@link TriangulatedSurface }
     *     {@link OrientableSurface }
     *     {@link Polygon }
     *     {@link GeometricPrimitive }
     *     {@link LineString }
     *     {@link MultiPoint }
     *     {@link MultiSurface }
     *     {@link Point }
     *     {@link AbstractCurve }
     *     {@link Surface }
     *     {@link MultiLineString }
     *     {@link MultiSolid }
     *     {@link MultiGeometry }
     *     {@link AbstractSurface }
     *     {@link LinearRing }
     *     {@link OrientableCurve }
     *     {@link PolyhedralSurface }
     *     {@link Tin }
     *     {@link AbstractRing }
     *     {@link MultiCurve }
     *     {@link AbstractSolid }
     *     {@link Ring }
     *     {@link MultiPolygon }
     *     {@link GeometricAggregate }
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
     *     {@link Curve }
     *     {@link AbstractGeometry }
     *     {@link Solid }
     *     {@link TriangulatedSurface }
     *     {@link OrientableSurface }
     *     {@link Polygon }
     *     {@link GeometricPrimitive }
     *     {@link LineString }
     *     {@link MultiPoint }
     *     {@link MultiSurface }
     *     {@link Point }
     *     {@link AbstractCurve }
     *     {@link Surface }
     *     {@link MultiLineString }
     *     {@link MultiSolid }
     *     {@link MultiGeometry }
     *     {@link AbstractSurface }
     *     {@link LinearRing }
     *     {@link OrientableCurve }
     *     {@link PolyhedralSurface }
     *     {@link Tin }
     *     {@link AbstractRing }
     *     {@link MultiCurve }
     *     {@link AbstractSolid }
     *     {@link Ring }
     *     {@link MultiPolygon }
     *     {@link GeometricAggregate }
     *     
     */
    public void setGeometry(JAXBElement<? extends AbstractGeometryType> value) {
        this.geometry = ((JAXBElement<? extends AbstractGeometryType> ) value);
    }

    /**
     * Gets the value of the envelope property.
     * 
     * @return
     *     possible object is
     *     {@link Envelope }
     *     
     */
    public Envelope getEnvelope() {
        return envelope;
    }

    /**
     * Sets the value of the envelope property.
     * 
     * @param value
     *     allowed object is
     *     {@link Envelope }
     *     
     */
    public void setEnvelope(Envelope value) {
        this.envelope = value;
    }

}
