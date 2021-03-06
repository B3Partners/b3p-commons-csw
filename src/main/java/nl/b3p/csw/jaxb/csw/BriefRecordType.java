//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.06.10 at 04:19:23 PM CEST 
//


package nl.b3p.csw.jaxb.csw;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import nl.b3p.csw.jaxb.elements.Identifier;
import nl.b3p.csw.jaxb.elements.SimpleLiteral;
import nl.b3p.csw.jaxb.elements.Title;
import nl.b3p.csw.jaxb.elements.Type;
import nl.b3p.csw.jaxb.ows.BoundingBox;
import nl.b3p.csw.jaxb.ows.BoundingBoxType;
import nl.b3p.csw.jaxb.ows.WGS84BoundingBox;
import nl.b3p.csw.jaxb.terms.Alternative;
import nl.b3p.csw.jaxb.terms.BibliographicCitation;


/**
 * 
 *             This type defines a brief representation of the common record
 *             format.  It extends AbstractRecordType to include only the
 *              dc:identifier and dc:type properties.
 *          
 * 
 * <p>Java class for BriefRecordType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BriefRecordType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.opengis.net/cat/csw/2.0.2}AbstractRecordType">
 *       &lt;sequence>
 *         &lt;element ref="{http://purl.org/dc/elements/1.1/}identifier" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://purl.org/dc/elements/1.1/}title" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://purl.org/dc/elements/1.1/}type" minOccurs="0"/>
 *         &lt;element ref="{http://www.opengis.net/ows}BoundingBox" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BriefRecordType", propOrder = {
    "identifier",
    "title",
    "type",
    "boundingBox"
})
public class BriefRecordType
    extends AbstractRecordType
{

    @XmlElementRef(name = "identifier", namespace = "http://purl.org/dc/elements/1.1/", type = Identifier.class)
    protected List<JAXBElement<SimpleLiteral>> identifier;
    @XmlElementRef(name = "title", namespace = "http://purl.org/dc/elements/1.1/", type = Title.class)
    protected List<JAXBElement<SimpleLiteral>> title;
    @XmlElementRef(name = "type", namespace = "http://purl.org/dc/elements/1.1/", type = Type.class)
    protected Type type;
    @XmlElementRef(name = "BoundingBox", namespace = "http://www.opengis.net/ows", type = BoundingBox.class)
    protected List<JAXBElement<? extends BoundingBoxType>> boundingBox;

    /**
     * Default no-arg constructor
     * 
     */
    public BriefRecordType() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public BriefRecordType(final List<JAXBElement<SimpleLiteral>> identifier, final List<JAXBElement<SimpleLiteral>> title, final Type type, final List<JAXBElement<? extends BoundingBoxType>> boundingBox) {
        super();
        this.identifier = identifier;
        this.title = title;
        this.type = type;
        this.boundingBox = boundingBox;
    }

    /**
     * Gets the value of the identifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BibliographicCitation }
     * {@link Identifier }
     * 
     * 
     */
    public List<JAXBElement<SimpleLiteral>> getIdentifier() {
        if (identifier == null) {
            identifier = new ArrayList<JAXBElement<SimpleLiteral>>();
        }
        return this.identifier;
    }

    /**
     * Gets the value of the title property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the title property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTitle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Alternative }
     * {@link Title }
     * 
     * 
     */
    public List<JAXBElement<SimpleLiteral>> getTitle() {
        if (title == null) {
            title = new ArrayList<JAXBElement<SimpleLiteral>>();
        }
        return this.title;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link Type }
     *     
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link Type }
     *     
     */
    public void setType(Type value) {
        this.type = value;
    }

    /**
     * Gets the value of the boundingBox property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the boundingBox property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBoundingBox().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WGS84BoundingBox }
     * {@link BoundingBox }
     * 
     * 
     */
    public List<JAXBElement<? extends BoundingBoxType>> getBoundingBox() {
        if (boundingBox == null) {
            boundingBox = new ArrayList<JAXBElement<? extends BoundingBoxType>>();
        }
        return this.boundingBox;
    }

}
