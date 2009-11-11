//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.11 at 06:55:45 PM CET 
//


package nl.b3p.csw.jaxb.ows;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/ows}DescriptionType">
 *       &lt;sequence>
 *         &lt;element name="ServiceType" type="{http://www.opengis.net/ows}CodeType"/>
 *         &lt;element name="ServiceTypeVersion" type="{http://www.opengis.net/ows}VersionType" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.opengis.net/ows}Fees" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/ows}AccessConstraints" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "serviceType",
    "serviceTypeVersion",
    "fees",
    "accessConstraints"
})
@XmlRootElement(name = "ServiceIdentification")
public class ServiceIdentification
    extends DescriptionType
{

    @XmlElement(name = "ServiceType", required = true)
    protected CodeType serviceType;
    @XmlElement(name = "ServiceTypeVersion", required = true)
    protected List<String> serviceTypeVersion;
    @XmlElementRef(name = "Fees", namespace = "http://www.opengis.net/ows", type = Fees.class)
    protected Fees fees;
    @XmlElementRef(name = "AccessConstraints", namespace = "http://www.opengis.net/ows", type = AccessConstraints.class)
    protected List<AccessConstraints> accessConstraints;

    /**
     * Default no-arg constructor
     * 
     */
    public ServiceIdentification() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public ServiceIdentification(final Title title, final Abstract _abstract, final List<Keywords> keywords, final CodeType serviceType, final List<String> serviceTypeVersion, final Fees fees, final List<AccessConstraints> accessConstraints) {
        super(title, _abstract, keywords);
        this.serviceType = serviceType;
        this.serviceTypeVersion = serviceTypeVersion;
        this.fees = fees;
        this.accessConstraints = accessConstraints;
    }

    /**
     * Gets the value of the serviceType property.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getServiceType() {
        return serviceType;
    }

    /**
     * Sets the value of the serviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setServiceType(CodeType value) {
        this.serviceType = value;
    }

    /**
     * Gets the value of the serviceTypeVersion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceTypeVersion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceTypeVersion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getServiceTypeVersion() {
        if (serviceTypeVersion == null) {
            serviceTypeVersion = new ArrayList<String>();
        }
        return this.serviceTypeVersion;
    }

    /**
     * If this element is omitted, no meaning is implied. 
     * 
     * @return
     *     possible object is
     *     {@link Fees }
     *     
     */
    public Fees getFees() {
        return fees;
    }

    /**
     * Sets the value of the fees property.
     * 
     * @param value
     *     allowed object is
     *     {@link Fees }
     *     
     */
    public void setFees(Fees value) {
        this.fees = value;
    }

    /**
     * Unordered list of access constraints applied to assure the protection of privacy or intellectual property, and any other restrictions on retrieving or using data from or otherwise using this server. The reserved value NONE (case insensitive) shall be used to mean no access constraints are imposed. If this element is omitted, no meaning is implied. Gets the value of the accessConstraints property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accessConstraints property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccessConstraints().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccessConstraints }
     * 
     * 
     */
    public List<AccessConstraints> getAccessConstraints() {
        if (accessConstraints == null) {
            accessConstraints = new ArrayList<AccessConstraints>();
        }
        return this.accessConstraints;
    }

}
