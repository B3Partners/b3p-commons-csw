//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.06.10 at 04:19:23 PM CEST 
//


package nl.b3p.csw.jaxb.filter;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FunctionNamesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FunctionNamesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded">
 *         &lt;element name="FunctionName" type="{http://www.opengis.net/ogc}FunctionNameType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FunctionNamesType", propOrder = {
    "functionName"
})
public class FunctionNamesType {

    @XmlElement(name = "FunctionName", required = true)
    protected List<FunctionNameType> functionName;

    /**
     * Default no-arg constructor
     * 
     */
    public FunctionNamesType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public FunctionNamesType(final List<FunctionNameType> functionName) {
        this.functionName = functionName;
    }

    /**
     * Gets the value of the functionName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the functionName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFunctionName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FunctionNameType }
     * 
     * 
     */
    public List<FunctionNameType> getFunctionName() {
        if (functionName == null) {
            functionName = new ArrayList<FunctionNameType>();
        }
        return this.functionName;
    }

}
