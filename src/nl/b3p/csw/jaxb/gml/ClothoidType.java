//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.11 at 06:55:45 PM CET 
//


package nl.b3p.csw.jaxb.gml;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * A clothoid, or Cornu's spiral, is plane
 *    curve whose curvature is a fixed function of its length.
 *    In suitably chosen co-ordinates it is given by Fresnel's
 *    integrals.
 * 
 *     x(t) = 0-integral-t cos(AT*T/2)dT    
 *     
 *     y(t) = 0-integral-t sin(AT*T/2)dT
 *    
 *    This geometry is mainly used as a transition curve between
 *    curves of type straight line to circular arc or circular arc
 *    to circular arc. With this curve type it is possible to 
 *    achieve a C2-continous transition between the above mentioned
 *    curve types. One formula for the Clothoid is A*A = R*t where
 *    A is constant, R is the varying radius of curvature along the
 *    the curve and t is the length along and given in the Fresnel 
 *    integrals.
 * 
 * <p>Java class for ClothoidType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ClothoidType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractCurveSegmentType">
 *       &lt;sequence>
 *         &lt;element name="refLocation">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element ref="{http://www.opengis.net/gml}AffinePlacement"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="scaleFactor" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="startParameter" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="endParameter" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ClothoidType", propOrder = {
    "refLocation",
    "scaleFactor",
    "startParameter",
    "endParameter"
})
public class ClothoidType
    extends AbstractCurveSegmentType
{

    @XmlElement(required = true)
    protected ClothoidType.RefLocation refLocation;
    @XmlElement(required = true)
    protected BigDecimal scaleFactor;
    protected double startParameter;
    protected double endParameter;

    /**
     * Default no-arg constructor
     * 
     */
    public ClothoidType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public ClothoidType(final BigInteger numDerivativesAtStart, final BigInteger numDerivativesAtEnd, final BigInteger numDerivativeInterior, final ClothoidType.RefLocation refLocation, final BigDecimal scaleFactor, final double startParameter, final double endParameter) {
        super(numDerivativesAtStart, numDerivativesAtEnd, numDerivativeInterior);
        this.refLocation = refLocation;
        this.scaleFactor = scaleFactor;
        this.startParameter = startParameter;
        this.endParameter = endParameter;
    }

    /**
     * Gets the value of the refLocation property.
     * 
     * @return
     *     possible object is
     *     {@link ClothoidType.RefLocation }
     *     
     */
    public ClothoidType.RefLocation getRefLocation() {
        return refLocation;
    }

    /**
     * Sets the value of the refLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClothoidType.RefLocation }
     *     
     */
    public void setRefLocation(ClothoidType.RefLocation value) {
        this.refLocation = value;
    }

    /**
     * Gets the value of the scaleFactor property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getScaleFactor() {
        return scaleFactor;
    }

    /**
     * Sets the value of the scaleFactor property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setScaleFactor(BigDecimal value) {
        this.scaleFactor = value;
    }

    /**
     * Gets the value of the startParameter property.
     * 
     */
    public double getStartParameter() {
        return startParameter;
    }

    /**
     * Sets the value of the startParameter property.
     * 
     */
    public void setStartParameter(double value) {
        this.startParameter = value;
    }

    /**
     * Gets the value of the endParameter property.
     * 
     */
    public double getEndParameter() {
        return endParameter;
    }

    /**
     * Sets the value of the endParameter property.
     * 
     */
    public void setEndParameter(double value) {
        this.endParameter = value;
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
     *       &lt;sequence>
     *         &lt;element ref="{http://www.opengis.net/gml}AffinePlacement"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "affinePlacement"
    })
    public static class RefLocation {

        @XmlElementRef(name = "AffinePlacement", namespace = "http://www.opengis.net/gml", type = AffinePlacement.class)
        protected AffinePlacement affinePlacement;

        /**
         * Default no-arg constructor
         * 
         */
        public RefLocation() {
            super();
        }

        /**
         * Fully-initialising value constructor
         * 
         */
        public RefLocation(final AffinePlacement affinePlacement) {
            this.affinePlacement = affinePlacement;
        }

        /**
         * The "refLocation" is an affine mapping 
         *           that places  the curve defined by the Fresnel Integrals  
         *           into the co-ordinate reference system of this object.
         * 
         * @return
         *     possible object is
         *     {@link AffinePlacement }
         *     
         */
        public AffinePlacement getAffinePlacement() {
            return affinePlacement;
        }

        /**
         * Sets the value of the affinePlacement property.
         * 
         * @param value
         *     allowed object is
         *     {@link AffinePlacement }
         *     
         */
        public void setAffinePlacement(AffinePlacement value) {
            this.affinePlacement = value;
        }

    }

}
