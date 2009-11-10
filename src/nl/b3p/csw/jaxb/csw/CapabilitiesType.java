//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.10 at 09:11:16 PM CET 
//


package nl.b3p.csw.jaxb.csw;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import nl.b3p.csw.jaxb.filter.FilterCapabilities;
import nl.b3p.csw.jaxb.ows.CapabilitiesBaseType;


/**
 * This type extends ows:CapabilitiesBaseType defined in OGC-05-008 
 *          to include information about supported OGC filter components. A 
 *          profile may extend this type to describe additional capabilities.
 * 
 * <p>Java class for CapabilitiesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CapabilitiesType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/ows}CapabilitiesBaseType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/ogc}Filter_Capabilities"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CapabilitiesType", propOrder = {
    "filterCapabilities"
})
public class CapabilitiesType
    extends CapabilitiesBaseType
{

    @XmlElement(name = "Filter_Capabilities", namespace = "http://www.opengis.net/ogc", required = true)
    protected FilterCapabilities filterCapabilities;

    /**
     * Gets the value of the filterCapabilities property.
     * 
     * @return
     *     possible object is
     *     {@link FilterCapabilities }
     *     
     */
    public FilterCapabilities getFilterCapabilities() {
        return filterCapabilities;
    }

    /**
     * Sets the value of the filterCapabilities property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterCapabilities }
     *     
     */
    public void setFilterCapabilities(FilterCapabilities value) {
        this.filterCapabilities = value;
    }

}
