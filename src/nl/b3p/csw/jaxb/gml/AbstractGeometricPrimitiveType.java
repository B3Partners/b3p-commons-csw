//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.10 at 03:06:01 PM CET 
//


package nl.b3p.csw.jaxb.gml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * This is the abstract root type of the geometric primitives. A geometric primitive is a geometric object that is not 
 * 			decomposed further into other primitives in the system. All primitives are oriented in the direction implied by the sequence of their 
 * 			coordinate tuples.
 * 
 * <p>Java class for AbstractGeometricPrimitiveType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AbstractGeometricPrimitiveType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractGeometryType">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractGeometricPrimitiveType")
@XmlSeeAlso({
    PointType.class,
    AbstractSolidType.class,
    AbstractCurveType.class,
    AbstractSurfaceType.class
})
public abstract class AbstractGeometricPrimitiveType
    extends AbstractGeometryType
{


}
