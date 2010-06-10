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


/**
 * <p>Java class for LowerBoundaryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LowerBoundaryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{http://www.opengis.net/ogc}expression"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LowerBoundaryType", propOrder = {
    "expression"
})
public class LowerBoundaryType {

    @XmlElementRef(name = "expression", namespace = "http://www.opengis.net/ogc", type = Expression.class)
    protected JAXBElement<? extends ExpressionType> expression;

    /**
     * Default no-arg constructor
     * 
     */
    public LowerBoundaryType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public LowerBoundaryType(final JAXBElement<? extends ExpressionType> expression) {
        this.expression = expression;
    }

    /**
     * Gets the value of the expression property.
     * 
     * @return
     *     possible object is
     *     {@link Add }
     *     {@link Div }
     *     {@link Literal }
     *     {@link Sub }
     *     {@link Expression }
     *     {@link Mul }
     *     {@link PropertyName }
     *     {@link Function }
     *     
     */
    public JAXBElement<? extends ExpressionType> getExpression() {
        return expression;
    }

    /**
     * Sets the value of the expression property.
     * 
     * @param value
     *     allowed object is
     *     {@link Add }
     *     {@link Div }
     *     {@link Literal }
     *     {@link Sub }
     *     {@link Expression }
     *     {@link Mul }
     *     {@link PropertyName }
     *     {@link Function }
     *     
     */
    public void setExpression(JAXBElement<? extends ExpressionType> value) {
        this.expression = ((JAXBElement<? extends ExpressionType> ) value);
    }

}
