//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.13 at 09:11:40 PM CET 
//


package nl.b3p.csw.jaxb.ows;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Connect point URL and any constraints for this HTTP request method for this operation request. In the OnlineResourceType, the xlink:href attribute in the xlink:simpleLink attribute group shall be used to contain this URL. The other attributes in the xlink:simpleLink attribute group should not be used. 
 * 
 * <p>Java class for RequestMethodType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestMethodType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/ows}OnlineResourceType">
 *       &lt;sequence>
 *         &lt;element name="Constraint" type="{http://www.opengis.net/ows}DomainType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestMethodType", propOrder = {
    "constraint"
})
public class RequestMethodType
    extends OnlineResourceType
{

    @XmlElement(name = "Constraint")
    protected List<DomainType> constraint;

    /**
     * Default no-arg constructor
     * 
     */
    public RequestMethodType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public RequestMethodType(final String type, final String href, final String role, final String arcrole, final String title, final String show, final String actuate, final List<DomainType> constraint) {
        super(type, href, role, arcrole, title, show, actuate);
        this.constraint = constraint;
    }

    /**
     * Gets the value of the constraint property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the constraint property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConstraint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DomainType }
     * 
     * 
     */
    public List<DomainType> getConstraint() {
        if (constraint == null) {
            constraint = new ArrayList<DomainType>();
        }
        return this.constraint;
    }

}
