//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.06.10 at 04:19:23 PM CEST 
//


package nl.b3p.csw.jaxb.gml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * Definition of a unit of measure which is related to a preferred unit for this quantity type through a conversion formula.  A method for deriving this unit by algebraic combination of more primitive units, may also be provided.
 * 
 * <p>Java class for ConventionalUnitType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConventionalUnitType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}UnitDefinitionType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://www.opengis.net/gml}conversionToPreferredUnit"/>
 *           &lt;element ref="{http://www.opengis.net/gml}roughConversionToPreferredUnit"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://www.opengis.net/gml}derivationUnitTerm" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConventionalUnitType", propOrder = {
    "conversionToPreferredUnit",
    "roughConversionToPreferredUnit",
    "derivationUnitTerm"
})
public class ConventionalUnitType
    extends UnitDefinitionType
{

    @XmlElementRef(name = "conversionToPreferredUnit", namespace = "http://www.opengis.net/gml", type = ConversionToPreferredUnit.class)
    protected ConversionToPreferredUnit conversionToPreferredUnit;
    @XmlElementRef(name = "roughConversionToPreferredUnit", namespace = "http://www.opengis.net/gml", type = RoughConversionToPreferredUnit.class)
    protected RoughConversionToPreferredUnit roughConversionToPreferredUnit;
    @XmlElementRef(name = "derivationUnitTerm", namespace = "http://www.opengis.net/gml", type = DerivationUnitTerm.class)
    protected List<DerivationUnitTerm> derivationUnitTerm;

    /**
     * Default no-arg constructor
     * 
     */
    public ConventionalUnitType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public ConventionalUnitType(final List<MetaDataProperty> metaDataProperty, final Description description, final List<Name> name, final String id, final QuantityType quantityType, final CatalogSymbol catalogSymbol, final ConversionToPreferredUnit conversionToPreferredUnit, final RoughConversionToPreferredUnit roughConversionToPreferredUnit, final List<DerivationUnitTerm> derivationUnitTerm) {
        super(metaDataProperty, description, name, id, quantityType, catalogSymbol);
        this.conversionToPreferredUnit = conversionToPreferredUnit;
        this.roughConversionToPreferredUnit = roughConversionToPreferredUnit;
        this.derivationUnitTerm = derivationUnitTerm;
    }

    /**
     * Gets the value of the conversionToPreferredUnit property.
     * 
     * @return
     *     possible object is
     *     {@link ConversionToPreferredUnit }
     *     
     */
    public ConversionToPreferredUnit getConversionToPreferredUnit() {
        return conversionToPreferredUnit;
    }

    /**
     * Sets the value of the conversionToPreferredUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConversionToPreferredUnit }
     *     
     */
    public void setConversionToPreferredUnit(ConversionToPreferredUnit value) {
        this.conversionToPreferredUnit = value;
    }

    /**
     * Gets the value of the roughConversionToPreferredUnit property.
     * 
     * @return
     *     possible object is
     *     {@link RoughConversionToPreferredUnit }
     *     
     */
    public RoughConversionToPreferredUnit getRoughConversionToPreferredUnit() {
        return roughConversionToPreferredUnit;
    }

    /**
     * Sets the value of the roughConversionToPreferredUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoughConversionToPreferredUnit }
     *     
     */
    public void setRoughConversionToPreferredUnit(RoughConversionToPreferredUnit value) {
        this.roughConversionToPreferredUnit = value;
    }

    /**
     * Gets the value of the derivationUnitTerm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the derivationUnitTerm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDerivationUnitTerm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DerivationUnitTerm }
     * 
     * 
     */
    public List<DerivationUnitTerm> getDerivationUnitTerm() {
        if (derivationUnitTerm == null) {
            derivationUnitTerm = new ArrayList<DerivationUnitTerm>();
        }
        return this.derivationUnitTerm;
    }

}
