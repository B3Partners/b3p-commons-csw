//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.11.13 at 09:11:40 PM CET 
//


package nl.b3p.csw.jaxb.gml;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.annotation.OverrideAnnotationOf;


/**
 * Deprecated with GML version 3.1.0.
 * 
 * <p>Java class for GenericMetaDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GenericMetaDataType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/gml}AbstractMetaDataType">
 *       &lt;sequence>
 *         &lt;any processContents='lax' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GenericMetaDataType")
public class GenericMetaDataType
    extends AbstractMetaDataType
{

    @XmlMixed
    @XmlAnyElement(lax = true)
    @OverrideAnnotationOf
    protected List<java.lang.Object> contentOverrideForGenericMetaDataType;

    /**
     * Default no-arg constructor
     * 
     */
    public GenericMetaDataType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public GenericMetaDataType(final List<Serializable> content, final String id, final List<java.lang.Object> contentOverrideForGenericMetaDataType) {
        super(content, id);
        this.contentOverrideForGenericMetaDataType = contentOverrideForGenericMetaDataType;
    }

}
