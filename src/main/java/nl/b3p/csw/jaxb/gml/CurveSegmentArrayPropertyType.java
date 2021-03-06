//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.06.10 at 04:19:23 PM CEST 
//


package nl.b3p.csw.jaxb.gml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * A container for an array of curve segments.
 * 
 * <p>Java class for CurveSegmentArrayPropertyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CurveSegmentArrayPropertyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}_CurveSegment" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CurveSegmentArrayPropertyType", propOrder = {
    "curveSegment"
})
public class CurveSegmentArrayPropertyType {

    @XmlElementRef(name = "_CurveSegment", namespace = "http://www.opengis.net/gml", type = CurveSegment.class)
    protected List<JAXBElement<? extends AbstractCurveSegmentType>> curveSegment;

    /**
     * Default no-arg constructor
     * 
     */
    public CurveSegmentArrayPropertyType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public CurveSegmentArrayPropertyType(final List<JAXBElement<? extends AbstractCurveSegmentType>> curveSegment) {
        this.curveSegment = curveSegment;
    }

    /**
     * Gets the value of the curveSegment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the curveSegment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCurveSegment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LineStringSegment }
     * {@link ArcStringByBulge }
     * {@link Geodesic }
     * {@link ArcByCenterPoint }
     * {@link CurveSegment }
     * {@link Bezier }
     * {@link Clothoid }
     * {@link OffsetCurve }
     * {@link ArcByBulge }
     * {@link Circle }
     * {@link ArcString }
     * {@link BSpline }
     * {@link Arc }
     * {@link CircleByCenterPoint }
     * {@link GeodesicString }
     * {@link CubicSpline }
     * 
     * 
     */
    public List<JAXBElement<? extends AbstractCurveSegmentType>> getCurveSegment() {
        if (curveSegment == null) {
            curveSegment = new ArrayList<JAXBElement<? extends AbstractCurveSegmentType>>();
        }
        return this.curveSegment;
    }

}
