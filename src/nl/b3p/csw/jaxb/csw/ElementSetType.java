//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.10 at 03:06:01 PM CET 
//


package nl.b3p.csw.jaxb.csw;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ElementSetType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ElementSetType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="brief"/>
 *     &lt;enumeration value="summary"/>
 *     &lt;enumeration value="full"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ElementSetType")
@XmlEnum
public enum ElementSetType {

    @XmlEnumValue("brief")
    BRIEF("brief"),
    @XmlEnumValue("summary")
    SUMMARY("summary"),
    @XmlEnumValue("full")
    FULL("full");
    private final String value;

    ElementSetType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ElementSetType fromValue(String v) {
        for (ElementSetType c: ElementSetType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
