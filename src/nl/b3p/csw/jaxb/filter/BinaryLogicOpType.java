//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.13 at 09:11:40 PM CET 
//


package nl.b3p.csw.jaxb.filter;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BinaryLogicOpType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BinaryLogicOpType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/ogc}LogicOpsType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="2">
 *         &lt;element ref="{http://www.opengis.net/ogc}comparisonOps"/>
 *         &lt;element ref="{http://www.opengis.net/ogc}spatialOps"/>
 *         &lt;element ref="{http://www.opengis.net/ogc}logicOps"/>
 *       &lt;/choice>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BinaryLogicOpType", propOrder = {
    "comparisonOpsOrSpatialOpsOrLogicOps"
})
public class BinaryLogicOpType
    extends LogicOpsType
{

    @XmlElementRefs({
        @XmlElementRef(name = "comparisonOps", namespace = "http://www.opengis.net/ogc", type = ComparisonOps.class),
        @XmlElementRef(name = "logicOps", namespace = "http://www.opengis.net/ogc", type = LogicOps.class),
        @XmlElementRef(name = "spatialOps", namespace = "http://www.opengis.net/ogc", type = SpatialOps.class)
    })
    protected List<JAXBElement<?>> comparisonOpsOrSpatialOpsOrLogicOps;

    /**
     * Default no-arg constructor
     * 
     */
    public BinaryLogicOpType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public BinaryLogicOpType(final List<JAXBElement<?>> comparisonOpsOrSpatialOpsOrLogicOps) {
        super();
        this.comparisonOpsOrSpatialOpsOrLogicOps = comparisonOpsOrSpatialOpsOrLogicOps;
    }

    /**
     * Gets the value of the comparisonOpsOrSpatialOpsOrLogicOps property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comparisonOpsOrSpatialOpsOrLogicOps property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComparisonOpsOrSpatialOpsOrLogicOps().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link And }
     * {@link Overlaps }
     * {@link Within }
     * {@link Touches }
     * {@link PropertyIsNotEqualTo }
     * {@link PropertyIsNull }
     * {@link PropertyIsEqualTo }
     * {@link PropertyIsGreaterThanOrEqualTo }
     * {@link ComparisonOps }
     * {@link Contains }
     * {@link Equals }
     * {@link BBOX }
     * {@link DWithin }
     * {@link PropertyIsLike }
     * {@link Or }
     * {@link Beyond }
     * {@link Intersects }
     * {@link Not }
     * {@link Crosses }
     * {@link Disjoint }
     * {@link PropertyIsGreaterThan }
     * {@link PropertyIsLessThan }
     * {@link LogicOps }
     * {@link SpatialOps }
     * {@link PropertyIsBetween }
     * {@link PropertyIsLessThanOrEqualTo }
     * 
     * 
     */
    public List<JAXBElement<?>> getComparisonOpsOrSpatialOpsOrLogicOps() {
        if (comparisonOpsOrSpatialOpsOrLogicOps == null) {
            comparisonOpsOrSpatialOpsOrLogicOps = new ArrayList<JAXBElement<?>>();
        }
        return this.comparisonOpsOrSpatialOpsOrLogicOps;
    }

}
