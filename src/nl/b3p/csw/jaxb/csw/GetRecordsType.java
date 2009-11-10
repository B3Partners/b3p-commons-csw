//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.10 at 09:11:16 PM CET 
//


package nl.b3p.csw.jaxb.csw;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *          The principal means of searching the catalogue. The matching 
 *          catalogue entries may be included with the response. The client 
 *          may assign a requestId (absolute URI). A distributed search is 
 *          performed if the DistributedSearch element is present and the 
 *          catalogue is a member of a federation. Profiles may allow 
 *          alternative query expressions.
 * 
 * <p>Java class for GetRecordsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetRecordsType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/cat/csw/2.0.2}RequestBaseType">
 *       &lt;sequence>
 *         &lt;element name="DistributedSearch" type="{http://www.opengis.net/cat/csw/2.0.2}DistributedSearchType" minOccurs="0"/>
 *         &lt;element name="ResponseHandler" type="{http://www.w3.org/2001/XMLSchema}anyURI" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/cat/csw/2.0.2}AbstractQuery"/>
 *           &lt;any namespace='##other'/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://www.opengis.net/cat/csw/2.0.2}BasicRetrievalOptions"/>
 *       &lt;attribute name="requestId" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="resultType" type="{http://www.opengis.net/cat/csw/2.0.2}ResultType" default="hits" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetRecordsType", propOrder = {
    "distributedSearch",
    "responseHandler",
    "abstractQuery",
    "any"
})
public class GetRecordsType
    extends RequestBaseType
{

    @XmlElement(name = "DistributedSearch")
    protected DistributedSearchType distributedSearch;
    @XmlElement(name = "ResponseHandler")
    @XmlSchemaType(name = "anyURI")
    protected List<String> responseHandler;
    @XmlElementRef(name = "AbstractQuery", namespace = "http://www.opengis.net/cat/csw/2.0.2", type = JAXBElement.class)
    protected JAXBElement<? extends AbstractQueryType> abstractQuery;
    @XmlAnyElement(lax = true)
    protected Object any;
    @XmlAttribute
    @XmlSchemaType(name = "anyURI")
    protected String requestId;
    @XmlAttribute
    protected ResultType resultType;
    @XmlAttribute
    protected String outputFormat;
    @XmlAttribute
    @XmlSchemaType(name = "anyURI")
    protected String outputSchema;
    @XmlAttribute
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger startPosition;
    @XmlAttribute
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger maxRecords;

    /**
     * Gets the value of the distributedSearch property.
     * 
     * @return
     *     possible object is
     *     {@link DistributedSearchType }
     *     
     */
    public DistributedSearchType getDistributedSearch() {
        return distributedSearch;
    }

    /**
     * Sets the value of the distributedSearch property.
     * 
     * @param value
     *     allowed object is
     *     {@link DistributedSearchType }
     *     
     */
    public void setDistributedSearch(DistributedSearchType value) {
        this.distributedSearch = value;
    }

    /**
     * Gets the value of the responseHandler property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the responseHandler property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResponseHandler().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getResponseHandler() {
        if (responseHandler == null) {
            responseHandler = new ArrayList<String>();
        }
        return this.responseHandler;
    }

    /**
     * Gets the value of the abstractQuery property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link QueryType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractQueryType }{@code >}
     *     
     */
    public JAXBElement<? extends AbstractQueryType> getAbstractQuery() {
        return abstractQuery;
    }

    /**
     * Sets the value of the abstractQuery property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link QueryType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AbstractQueryType }{@code >}
     *     
     */
    public void setAbstractQuery(JAXBElement<? extends AbstractQueryType> value) {
        this.abstractQuery = ((JAXBElement<? extends AbstractQueryType> ) value);
    }

    /**
     * Gets the value of the any property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getAny() {
        return any;
    }

    /**
     * Sets the value of the any property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setAny(Object value) {
        this.any = value;
    }

    /**
     * Gets the value of the requestId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Sets the value of the requestId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestId(String value) {
        this.requestId = value;
    }

    /**
     * Gets the value of the resultType property.
     * 
     * @return
     *     possible object is
     *     {@link ResultType }
     *     
     */
    public ResultType getResultType() {
        if (resultType == null) {
            return ResultType.HITS;
        } else {
            return resultType;
        }
    }

    /**
     * Sets the value of the resultType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResultType }
     *     
     */
    public void setResultType(ResultType value) {
        this.resultType = value;
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

    /**
     * Gets the value of the startPosition property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStartPosition() {
        if (startPosition == null) {
            return new BigInteger("1");
        } else {
            return startPosition;
        }
    }

    /**
     * Sets the value of the startPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStartPosition(BigInteger value) {
        this.startPosition = value;
    }

    /**
     * Gets the value of the maxRecords property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxRecords() {
        if (maxRecords == null) {
            return new BigInteger("10");
        } else {
            return maxRecords;
        }
    }

    /**
     * Sets the value of the maxRecords property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxRecords(BigInteger value) {
        this.maxRecords = value;
    }

}
