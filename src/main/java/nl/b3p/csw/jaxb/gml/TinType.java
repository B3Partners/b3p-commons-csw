//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.06.10 at 04:19:23 PM CEST 
//


package nl.b3p.csw.jaxb.gml;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * A tin is a triangulated surface that uses
 *    the Delauny algorithm or a similar algorithm complemented with
 *    consideration of breaklines, stoplines, and maximum length of 
 *    triangle sides. These networks satisfy the Delauny's criterion
 *    away from the modifications: Fore each triangle in the 
 *    network, the circle passing through its vertices does not
 *    contain, in its interior, the vertex of any other triangle.
 * 
 * <p>Java class for TinType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TinType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}TriangulatedSurfaceType">
 *       &lt;sequence>
 *         &lt;element name="stopLines" type="{http://www.opengis.net/gml}LineStringSegmentArrayPropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="breakLines" type="{http://www.opengis.net/gml}LineStringSegmentArrayPropertyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="maxLength" type="{http://www.opengis.net/gml}LengthType"/>
 *         &lt;element name="controlPoint">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element ref="{http://www.opengis.net/gml}posList"/>
 *                   &lt;group ref="{http://www.opengis.net/gml}geometricPositionGroup" maxOccurs="unbounded" minOccurs="3"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TinType", propOrder = {
    "stopLines",
    "breakLines",
    "maxLength",
    "controlPoint"
})
public class TinType
    extends TriangulatedSurfaceType
{

    protected List<LineStringSegmentArrayPropertyType> stopLines;
    protected List<LineStringSegmentArrayPropertyType> breakLines;
    @XmlElement(required = true)
    protected LengthType maxLength;
    @XmlElement(required = true)
    protected TinType.ControlPoint controlPoint;

    /**
     * Default no-arg constructor
     * 
     */
    public TinType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public TinType(final List<MetaDataProperty> metaDataProperty, final Description description, final List<Name> name, final String id, final String gid, final String srsName, final BigInteger srsDimension, final List<String> axisLabels, final List<String> uomLabels, final JAXBElement<? extends SurfacePatchArrayPropertyType> patches, final List<LineStringSegmentArrayPropertyType> stopLines, final List<LineStringSegmentArrayPropertyType> breakLines, final LengthType maxLength, final TinType.ControlPoint controlPoint) {
        super(metaDataProperty, description, name, id, gid, srsName, srsDimension, axisLabels, uomLabels, patches);
        this.stopLines = stopLines;
        this.breakLines = breakLines;
        this.maxLength = maxLength;
        this.controlPoint = controlPoint;
    }

    /**
     * Gets the value of the stopLines property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stopLines property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStopLines().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LineStringSegmentArrayPropertyType }
     * 
     * 
     */
    public List<LineStringSegmentArrayPropertyType> getStopLines() {
        if (stopLines == null) {
            stopLines = new ArrayList<LineStringSegmentArrayPropertyType>();
        }
        return this.stopLines;
    }

    /**
     * Gets the value of the breakLines property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the breakLines property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBreakLines().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LineStringSegmentArrayPropertyType }
     * 
     * 
     */
    public List<LineStringSegmentArrayPropertyType> getBreakLines() {
        if (breakLines == null) {
            breakLines = new ArrayList<LineStringSegmentArrayPropertyType>();
        }
        return this.breakLines;
    }

    /**
     * Gets the value of the maxLength property.
     * 
     * @return
     *     possible object is
     *     {@link LengthType }
     *     
     */
    public LengthType getMaxLength() {
        return maxLength;
    }

    /**
     * Sets the value of the maxLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link LengthType }
     *     
     */
    public void setMaxLength(LengthType value) {
        this.maxLength = value;
    }

    /**
     * Gets the value of the controlPoint property.
     * 
     * @return
     *     possible object is
     *     {@link TinType.ControlPoint }
     *     
     */
    public TinType.ControlPoint getControlPoint() {
        return controlPoint;
    }

    /**
     * Sets the value of the controlPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link TinType.ControlPoint }
     *     
     */
    public void setControlPoint(TinType.ControlPoint value) {
        this.controlPoint = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice>
     *         &lt;element ref="{http://www.opengis.net/gml}posList"/>
     *         &lt;group ref="{http://www.opengis.net/gml}geometricPositionGroup" maxOccurs="unbounded" minOccurs="3"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "posList",
        "geometricPositionGroup"
    })
    public static class ControlPoint {

        @XmlElementRef(name = "posList", namespace = "http://www.opengis.net/gml", type = PosList.class)
        protected PosList posList;
        @XmlElementRefs({
            @XmlElementRef(name = "pointProperty", namespace = "http://www.opengis.net/gml", type = PointProperty.class),
            @XmlElementRef(name = "pos", namespace = "http://www.opengis.net/gml", type = Pos.class)
        })
        protected List<JAXBElement<?>> geometricPositionGroup;

        /**
         * Default no-arg constructor
         * 
         */
        public ControlPoint() {
            super();
        }

        /**
         * Fully-initialising value constructor
         * 
         */
        public ControlPoint(final PosList posList, final List<JAXBElement<?>> geometricPositionGroup) {
            this.posList = posList;
            this.geometricPositionGroup = geometricPositionGroup;
        }

        /**
         * Gets the value of the posList property.
         * 
         * @return
         *     possible object is
         *     {@link PosList }
         *     
         */
        public PosList getPosList() {
            return posList;
        }

        /**
         * Sets the value of the posList property.
         * 
         * @param value
         *     allowed object is
         *     {@link PosList }
         *     
         */
        public void setPosList(PosList value) {
            this.posList = value;
        }

        /**
         * Gets the value of the geometricPositionGroup property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the geometricPositionGroup property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getGeometricPositionGroup().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Pos }
         * {@link PointProperty }
         * 
         * 
         */
        public List<JAXBElement<?>> getGeometricPositionGroup() {
            if (geometricPositionGroup == null) {
                geometricPositionGroup = new ArrayList<JAXBElement<?>>();
            }
            return this.geometricPositionGroup;
        }

    }

}
