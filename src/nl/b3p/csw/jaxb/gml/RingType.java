//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.11 at 06:55:45 PM CET 
//


package nl.b3p.csw.jaxb.gml;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * A Ring is used to represent a single connected component of a surface boundary. It consists of a sequence of curves connected in a cycle (an object whose boundary is empty).
 * A Ring is structurally similar to a composite curve in that the endPoint of each curve in the sequence is the startPoint of the next curve in the Sequence. Since the sequence is circular, there is no exception to this rule. Each ring, like all boundaries, is a cycle and each ring is simple.
 * NOTE: Even though each Ring is simple, the boundary need not be simple. The easiest case of this is where one of the interior rings of a surface is tangent to its exterior ring.
 * 
 * <p>Java class for RingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RingType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractRingType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}curveMember" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RingType", propOrder = {
    "curveMember"
})
public class RingType
    extends AbstractRingType
{

    @XmlElementRef(name = "curveMember", namespace = "http://www.opengis.net/gml", type = CurveMember.class)
    protected List<CurveMember> curveMember;

    /**
     * Default no-arg constructor
     * 
     */
    public RingType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public RingType(final List<MetaDataProperty> metaDataProperty, final Description description, final List<Name> name, final String id, final String gid, final String srsName, final BigInteger srsDimension, final List<String> axisLabels, final List<String> uomLabels, final List<CurveMember> curveMember) {
        super(metaDataProperty, description, name, id, gid, srsName, srsDimension, axisLabels, uomLabels);
        this.curveMember = curveMember;
    }

    /**
     * This element references or contains one curve in the composite curve. The curves are contiguous, the collection of curves is ordered.
     * NOTE: This definition allows for a nested structure, i.e. a CompositeCurve may use, for example, another CompositeCurve as a curve member.Gets the value of the curveMember property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the curveMember property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCurveMember().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CurveMember }
     * 
     * 
     */
    public List<CurveMember> getCurveMember() {
        if (curveMember == null) {
            curveMember = new ArrayList<CurveMember>();
        }
        return this.curveMember;
    }

}
