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
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * Encapsulates a ring to represent the surface boundary property of a surface.
 * 
 * <p>Java class for AbstractRingPropertyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AbstractRingPropertyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}_Ring"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractRingPropertyType", propOrder = {
    "ring"
})
public class AbstractRingPropertyType {

    @XmlElementRef(name = "_Ring", namespace = "http://www.opengis.net/gml", type = AbstractRing.class)
    protected JAXBElement<? extends AbstractRingType> ring;

    /**
     * Default no-arg constructor
     * 
     */
    public AbstractRingPropertyType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public AbstractRingPropertyType(final JAXBElement<? extends AbstractRingType> ring) {
        this.ring = ring;
    }

    /**
     * Gets the value of the ring property.
     * 
     * @return
     *     possible object is
     *     {@link Ring }
     *     {@link AbstractRing }
     *     {@link LinearRing }
     *     
     */
    public JAXBElement<? extends AbstractRingType> getRing() {
        return ring;
    }

    /**
     * Sets the value of the ring property.
     * 
     * @param value
     *     allowed object is
     *     {@link Ring }
     *     {@link AbstractRing }
     *     {@link LinearRing }
     *     
     */
    public void setRing(JAXBElement<? extends AbstractRingType> value) {
        this.ring = ((JAXBElement<? extends AbstractRingType> ) value);
    }

}
