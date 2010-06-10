//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.06.10 at 04:19:23 PM CEST 
//


package nl.b3p.csw.jaxb.csw;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *             Convenience operation to retrieve default record representations 
 *             by identifier.
 *             Id - object identifier (a URI) that provides a reference to a 
 *                  catalogue item (or a result set if the catalogue supports 
 *                  persistent result sets).
 *             ElementSetName - one of "brief, "summary", or "full"
 *          
 * 
 * <p>Java class for GetRecordByIdType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetRecordByIdType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/cat/csw/2.0.2}RequestBaseType">
 *       &lt;sequence>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}anyURI" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.opengis.net/cat/csw/2.0.2}ElementSetName" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="outputFormat" type="{http://www.w3.org/2001/XMLSchema}string" default="application/xml" />
 *       &lt;attribute name="outputSchema" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetRecordByIdType", propOrder = {
    "id",
    "elementSetName"
})
public class GetRecordByIdType
    extends RequestBaseType
{

    @XmlElement(name = "Id", required = true)
    @XmlSchemaType(name = "anyURI")
    protected List<String> id;
    @XmlElementRef(name = "ElementSetName", namespace = "http://www.opengis.net/cat/csw/2.0.2", type = ElementSetName.class)
    protected ElementSetName elementSetName;
    @XmlAttribute
    protected String outputFormat;
    @XmlAttribute
    @XmlSchemaType(name = "anyURI")
    protected String outputSchema;

    /**
     * Default no-arg constructor
     * 
     */
    public GetRecordByIdType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public GetRecordByIdType(final String service, final String version, final List<String> id, final ElementSetName elementSetName, final String outputFormat, final String outputSchema) {
        super(service, version);
        this.id = id;
        this.elementSetName = elementSetName;
        this.outputFormat = outputFormat;
        this.outputSchema = outputSchema;
    }

    /**
     * Gets the value of the id property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the id property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getId() {
        if (id == null) {
            id = new ArrayList<String>();
        }
        return this.id;
    }

    /**
     * Gets the value of the elementSetName property.
     * 
     * @return
     *     possible object is
     *     {@link ElementSetName }
     *     
     */
    public ElementSetName getElementSetName() {
        return elementSetName;
    }

    /**
     * Sets the value of the elementSetName property.
     * 
     * @param value
     *     allowed object is
     *     {@link ElementSetName }
     *     
     */
    public void setElementSetName(ElementSetName value) {
        this.elementSetName = value;
    }

    /**
     * Gets the value of the outputFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputFormat() {
        if (outputFormat == null) {
            return "application/xml";
        } else {
            return outputFormat;
        }
    }

    /**
     * Sets the value of the outputFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputFormat(String value) {
        this.outputFormat = value;
    }

    /**
     * Gets the value of the outputSchema property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputSchema() {
        return outputSchema;
    }

    /**
     * Sets the value of the outputSchema property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputSchema(String value) {
        this.outputSchema = value;
    }

}
