//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.06.10 at 04:19:23 PM CEST 
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
 * A MultiLineString is defined by one or more LineStrings, referenced through lineStringMember elements. Deprecated with GML version 3.0. Use MultiCurveType instead.
 * 
 * <p>Java class for MultiLineStringType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MultiLineStringType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractGeometricAggregateType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.opengis.net/gml}lineStringMember" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MultiLineStringType", propOrder = {
    "lineStringMember"
})
public class MultiLineStringType
    extends AbstractGeometricAggregateType
{

    @XmlElementRef(name = "lineStringMember", namespace = "http://www.opengis.net/gml", type = LineStringMember.class)
    protected List<LineStringMember> lineStringMember;

    /**
     * Default no-arg constructor
     * 
     */
    public MultiLineStringType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public MultiLineStringType(final List<MetaDataProperty> metaDataProperty, final Description description, final List<Name> name, final String id, final String gid, final String srsName, final BigInteger srsDimension, final List<String> axisLabels, final List<String> uomLabels, final List<LineStringMember> lineStringMember) {
        super(metaDataProperty, description, name, id, gid, srsName, srsDimension, axisLabels, uomLabels);
        this.lineStringMember = lineStringMember;
    }

    /**
     * Gets the value of the lineStringMember property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lineStringMember property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLineStringMember().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LineStringMember }
     * 
     * 
     */
    public List<LineStringMember> getLineStringMember() {
        if (lineStringMember == null) {
            lineStringMember = new ArrayList<LineStringMember>();
        }
        return this.lineStringMember;
    }

}
