//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.11 at 06:55:45 PM CET 
//


package nl.b3p.csw.jaxb.ows;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * For OWS use in the ServiceProvider section of a service metadata document, the optional organizationName element was removed, since this type is always used with the ProviderName element which provides that information. The mandatory "role" element was changed to optional, since no clear use of this information is known in the ServiceProvider section. 
 * 
 * <p>Java class for ResponsiblePartySubsetType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponsiblePartySubsetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/ows}IndividualName" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/ows}PositionName" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/ows}ContactInfo" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/ows}Role" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponsiblePartySubsetType", propOrder = {
    "individualName",
    "positionName",
    "contactInfo",
    "role"
})
public class ResponsiblePartySubsetType {

    @XmlElementRef(name = "IndividualName", namespace = "http://www.opengis.net/ows", type = IndividualName.class)
    protected IndividualName individualName;
    @XmlElementRef(name = "PositionName", namespace = "http://www.opengis.net/ows", type = PositionName.class)
    protected PositionName positionName;
    @XmlElementRef(name = "ContactInfo", namespace = "http://www.opengis.net/ows", type = ContactInfo.class)
    protected ContactInfo contactInfo;
    @XmlElementRef(name = "Role", namespace = "http://www.opengis.net/ows", type = Role.class)
    protected Role role;

    /**
     * Default no-arg constructor
     * 
     */
    public ResponsiblePartySubsetType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public ResponsiblePartySubsetType(final IndividualName individualName, final PositionName positionName, final ContactInfo contactInfo, final Role role) {
        this.individualName = individualName;
        this.positionName = positionName;
        this.contactInfo = contactInfo;
        this.role = role;
    }

    /**
     * Gets the value of the individualName property.
     * 
     * @return
     *     possible object is
     *     {@link IndividualName }
     *     
     */
    public IndividualName getIndividualName() {
        return individualName;
    }

    /**
     * Sets the value of the individualName property.
     * 
     * @param value
     *     allowed object is
     *     {@link IndividualName }
     *     
     */
    public void setIndividualName(IndividualName value) {
        this.individualName = value;
    }

    /**
     * Gets the value of the positionName property.
     * 
     * @return
     *     possible object is
     *     {@link PositionName }
     *     
     */
    public PositionName getPositionName() {
        return positionName;
    }

    /**
     * Sets the value of the positionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link PositionName }
     *     
     */
    public void setPositionName(PositionName value) {
        this.positionName = value;
    }

    /**
     * Gets the value of the contactInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ContactInfo }
     *     
     */
    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    /**
     * Sets the value of the contactInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactInfo }
     *     
     */
    public void setContactInfo(ContactInfo value) {
        this.contactInfo = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * @return
     *     possible object is
     *     {@link Role }
     *     
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the value of the role property.
     * 
     * @param value
     *     allowed object is
     *     {@link Role }
     *     
     */
    public void setRole(Role value) {
        this.role = value;
    }

}
