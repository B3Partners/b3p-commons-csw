//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.11 at 06:55:45 PM CET 
//


package nl.b3p.csw.jaxb.csw;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;


/**
 * <p>Java class for DomainValuesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DomainValuesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="PropertyName" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *           &lt;element name="ParameterName" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;/choice>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="ListOfValues" type="{http://www.opengis.net/cat/csw/2.0.2}ListOfValuesType"/>
 *           &lt;element name="ConceptualScheme" type="{http://www.opengis.net/cat/csw/2.0.2}ConceptualSchemeType"/>
 *           &lt;element name="RangeOfValues" type="{http://www.opengis.net/cat/csw/2.0.2}RangeOfValuesType"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" />
 *       &lt;attribute name="uom" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DomainValuesType", propOrder = {
    "propertyName",
    "parameterName",
    "listOfValues",
    "conceptualScheme",
    "rangeOfValues"
})
public class DomainValuesType {

    @XmlElement(name = "PropertyName")
    @XmlSchemaType(name = "anyURI")
    protected String propertyName;
    @XmlElement(name = "ParameterName")
    @XmlSchemaType(name = "anyURI")
    protected String parameterName;
    @XmlElement(name = "ListOfValues")
    protected ListOfValuesType listOfValues;
    @XmlElement(name = "ConceptualScheme")
    protected ConceptualSchemeType conceptualScheme;
    @XmlElement(name = "RangeOfValues")
    protected RangeOfValuesType rangeOfValues;
    @XmlAttribute(required = true)
    protected QName type;
    @XmlAttribute
    @XmlSchemaType(name = "anyURI")
    protected String uom;

    /**
     * Default no-arg constructor
     * 
     */
    public DomainValuesType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public DomainValuesType(final String propertyName, final String parameterName, final ListOfValuesType listOfValues, final ConceptualSchemeType conceptualScheme, final RangeOfValuesType rangeOfValues, final QName type, final String uom) {
        this.propertyName = propertyName;
        this.parameterName = parameterName;
        this.listOfValues = listOfValues;
        this.conceptualScheme = conceptualScheme;
        this.rangeOfValues = rangeOfValues;
        this.type = type;
        this.uom = uom;
    }

    /**
     * Gets the value of the propertyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Sets the value of the propertyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropertyName(String value) {
        this.propertyName = value;
    }

    /**
     * Gets the value of the parameterName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParameterName() {
        return parameterName;
    }

    /**
     * Sets the value of the parameterName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParameterName(String value) {
        this.parameterName = value;
    }

    /**
     * Gets the value of the listOfValues property.
     * 
     * @return
     *     possible object is
     *     {@link ListOfValuesType }
     *     
     */
    public ListOfValuesType getListOfValues() {
        return listOfValues;
    }

    /**
     * Sets the value of the listOfValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfValuesType }
     *     
     */
    public void setListOfValues(ListOfValuesType value) {
        this.listOfValues = value;
    }

    /**
     * Gets the value of the conceptualScheme property.
     * 
     * @return
     *     possible object is
     *     {@link ConceptualSchemeType }
     *     
     */
    public ConceptualSchemeType getConceptualScheme() {
        return conceptualScheme;
    }

    /**
     * Sets the value of the conceptualScheme property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConceptualSchemeType }
     *     
     */
    public void setConceptualScheme(ConceptualSchemeType value) {
        this.conceptualScheme = value;
    }

    /**
     * Gets the value of the rangeOfValues property.
     * 
     * @return
     *     possible object is
     *     {@link RangeOfValuesType }
     *     
     */
    public RangeOfValuesType getRangeOfValues() {
        return rangeOfValues;
    }

    /**
     * Sets the value of the rangeOfValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link RangeOfValuesType }
     *     
     */
    public void setRangeOfValues(RangeOfValuesType value) {
        this.rangeOfValues = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link QName }
     *     
     */
    public QName getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link QName }
     *     
     */
    public void setType(QName value) {
        this.type = value;
    }

    /**
     * Gets the value of the uom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUom() {
        return uom;
    }

    /**
     * Sets the value of the uom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUom(String value) {
        this.uom = value;
    }

}
