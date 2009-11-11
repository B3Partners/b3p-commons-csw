//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.11 at 06:55:45 PM CET 
//


package nl.b3p.csw.jaxb.filter;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FilterType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FilterType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{http://www.opengis.net/ogc}spatialOps"/>
 *         &lt;element ref="{http://www.opengis.net/ogc}comparisonOps"/>
 *         &lt;element ref="{http://www.opengis.net/ogc}logicOps"/>
 *         &lt;element ref="{http://www.opengis.net/ogc}AbstractId" maxOccurs="unbounded"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FilterType", propOrder = {
    "spatialOps",
    "comparisonOps",
    "logicOps",
    "abstractId"
})
public class FilterType {

    @XmlElementRef(name = "spatialOps", namespace = "http://www.opengis.net/ogc", type = SpatialOps.class)
    protected JAXBElement<? extends SpatialOpsType> spatialOps;
    @XmlElementRef(name = "comparisonOps", namespace = "http://www.opengis.net/ogc", type = ComparisonOps.class)
    protected JAXBElement<? extends ComparisonOpsType> comparisonOps;
    @XmlElementRef(name = "logicOps", namespace = "http://www.opengis.net/ogc", type = LogicOps.class)
    protected JAXBElement<? extends LogicOpsType> logicOps;
    @XmlElementRef(name = "AbstractId", namespace = "http://www.opengis.net/ogc", type = AbstractId.class)
    protected List<JAXBElement<? extends AbstractIdType>> abstractId;

    /**
     * Default no-arg constructor
     * 
     */
    public FilterType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public FilterType(final JAXBElement<? extends SpatialOpsType> spatialOps, final JAXBElement<? extends ComparisonOpsType> comparisonOps, final JAXBElement<? extends LogicOpsType> logicOps, final List<JAXBElement<? extends AbstractIdType>> abstractId) {
        this.spatialOps = spatialOps;
        this.comparisonOps = comparisonOps;
        this.logicOps = logicOps;
        this.abstractId = abstractId;
    }

    /**
     * Gets the value of the spatialOps property.
     * 
     * @return
     *     possible object is
     *     {@link Disjoint }
     *     {@link Crosses }
     *     {@link Contains }
     *     {@link Touches }
     *     {@link DWithin }
     *     {@link Beyond }
     *     {@link Intersects }
     *     {@link Within }
     *     {@link BBOX }
     *     {@link Overlaps }
     *     {@link Equals }
     *     {@link SpatialOps }
     *     
     */
    public JAXBElement<? extends SpatialOpsType> getSpatialOps() {
        return spatialOps;
    }

    /**
     * Sets the value of the spatialOps property.
     * 
     * @param value
     *     allowed object is
     *     {@link Disjoint }
     *     {@link Crosses }
     *     {@link Contains }
     *     {@link Touches }
     *     {@link DWithin }
     *     {@link Beyond }
     *     {@link Intersects }
     *     {@link Within }
     *     {@link BBOX }
     *     {@link Overlaps }
     *     {@link Equals }
     *     {@link SpatialOps }
     *     
     */
    public void setSpatialOps(JAXBElement<? extends SpatialOpsType> value) {
        this.spatialOps = ((JAXBElement<? extends SpatialOpsType> ) value);
    }

    /**
     * Gets the value of the comparisonOps property.
     * 
     * @return
     *     possible object is
     *     {@link PropertyIsEqualTo }
     *     {@link PropertyIsBetween }
     *     {@link PropertyIsLike }
     *     {@link ComparisonOps }
     *     {@link PropertyIsGreaterThanOrEqualTo }
     *     {@link PropertyIsLessThan }
     *     {@link PropertyIsNull }
     *     {@link PropertyIsNotEqualTo }
     *     {@link PropertyIsGreaterThan }
     *     {@link PropertyIsLessThanOrEqualTo }
     *     
     */
    public JAXBElement<? extends ComparisonOpsType> getComparisonOps() {
        return comparisonOps;
    }

    /**
     * Sets the value of the comparisonOps property.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertyIsEqualTo }
     *     {@link PropertyIsBetween }
     *     {@link PropertyIsLike }
     *     {@link ComparisonOps }
     *     {@link PropertyIsGreaterThanOrEqualTo }
     *     {@link PropertyIsLessThan }
     *     {@link PropertyIsNull }
     *     {@link PropertyIsNotEqualTo }
     *     {@link PropertyIsGreaterThan }
     *     {@link PropertyIsLessThanOrEqualTo }
     *     
     */
    public void setComparisonOps(JAXBElement<? extends ComparisonOpsType> value) {
        this.comparisonOps = ((JAXBElement<? extends ComparisonOpsType> ) value);
    }

    /**
     * Gets the value of the logicOps property.
     * 
     * @return
     *     possible object is
     *     {@link Not }
     *     {@link And }
     *     {@link Or }
     *     {@link LogicOps }
     *     
     */
    public JAXBElement<? extends LogicOpsType> getLogicOps() {
        return logicOps;
    }

    /**
     * Sets the value of the logicOps property.
     * 
     * @param value
     *     allowed object is
     *     {@link Not }
     *     {@link And }
     *     {@link Or }
     *     {@link LogicOps }
     *     
     */
    public void setLogicOps(JAXBElement<? extends LogicOpsType> value) {
        this.logicOps = ((JAXBElement<? extends LogicOpsType> ) value);
    }

    /**
     * Gets the value of the abstractId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the abstractId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAbstractId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AbstractId }
     * {@link FeatureId }
     * {@link GmlObjectId }
     * 
     * 
     */
    public List<JAXBElement<? extends AbstractIdType>> getAbstractId() {
        if (abstractId == null) {
            abstractId = new ArrayList<JAXBElement<? extends AbstractIdType>>();
        }
        return this.abstractId;
    }

}
