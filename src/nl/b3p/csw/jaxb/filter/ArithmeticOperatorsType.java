//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.11 at 06:55:45 PM CET 
//


package nl.b3p.csw.jaxb.filter;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArithmeticOperatorsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArithmeticOperatorsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element ref="{http://www.opengis.net/ogc}SimpleArithmetic"/>
 *         &lt;element name="Functions" type="{http://www.opengis.net/ogc}FunctionsType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArithmeticOperatorsType", propOrder = {
    "simpleArithmeticOrFunctions"
})
public class ArithmeticOperatorsType {

    @XmlElements({
        @XmlElement(name = "SimpleArithmetic", type = SimpleArithmetic.class),
        @XmlElement(name = "Functions", type = FunctionsType.class)
    })
    protected List<Object> simpleArithmeticOrFunctions;

    /**
     * Default no-arg constructor
     * 
     */
    public ArithmeticOperatorsType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public ArithmeticOperatorsType(final List<Object> simpleArithmeticOrFunctions) {
        this.simpleArithmeticOrFunctions = simpleArithmeticOrFunctions;
    }

    /**
     * Gets the value of the simpleArithmeticOrFunctions property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the simpleArithmeticOrFunctions property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSimpleArithmeticOrFunctions().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleArithmetic }
     * {@link FunctionsType }
     * 
     * 
     */
    public List<Object> getSimpleArithmeticOrFunctions() {
        if (simpleArithmeticOrFunctions == null) {
            simpleArithmeticOrFunctions = new ArrayList<Object>();
        }
        return this.simpleArithmeticOrFunctions;
    }

}
