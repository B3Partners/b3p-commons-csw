//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.06.10 at 04:19:23 PM CEST 
//


package nl.b3p.csw.jaxb.gml;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Definition of a unit of measure (or uom). The definition includes a quantityType property, which indicates the phenomenon to which the units apply, and a catalogSymbol, which gives the short symbol used for this unit. This element is used when the relationship of this unit to other units or units systems is unknown.
 * 
 * <p>Java class for UnitDefinitionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UnitDefinitionType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}DefinitionType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}quantityType"/>
 *         &lt;element ref="{http://www.opengis.net/gml}catalogSymbol" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnitDefinitionType", propOrder = {
    "quantityType",
    "catalogSymbol"
})
@XmlSeeAlso({
    DerivedUnitType.class,
    BaseUnitType.class,
    ConventionalUnitType.class
})
public class UnitDefinitionType
    extends DefinitionType
{

    @XmlElementRef(name = "quantityType", namespace = "http://www.opengis.net/gml", type = QuantityType.class)
    protected QuantityType quantityType;
    @XmlElementRef(name = "catalogSymbol", namespace = "http://www.opengis.net/gml", type = CatalogSymbol.class)
    protected CatalogSymbol catalogSymbol;

    /**
     * Default no-arg constructor
     * 
     */
    public UnitDefinitionType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public UnitDefinitionType(final List<MetaDataProperty> metaDataProperty, final Description description, final List<Name> name, final String id, final QuantityType quantityType, final CatalogSymbol catalogSymbol) {
        super(metaDataProperty, description, name, id);
        this.quantityType = quantityType;
        this.catalogSymbol = catalogSymbol;
    }

    /**
     * Gets the value of the quantityType property.
     * 
     * @return
     *     possible object is
     *     {@link QuantityType }
     *     
     */
    public QuantityType getQuantityType() {
        return quantityType;
    }

    /**
     * Sets the value of the quantityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link QuantityType }
     *     
     */
    public void setQuantityType(QuantityType value) {
        this.quantityType = value;
    }

    /**
     * Gets the value of the catalogSymbol property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogSymbol }
     *     
     */
    public CatalogSymbol getCatalogSymbol() {
        return catalogSymbol;
    }

    /**
     * Sets the value of the catalogSymbol property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogSymbol }
     *     
     */
    public void setCatalogSymbol(CatalogSymbol value) {
        this.catalogSymbol = value;
    }

}
