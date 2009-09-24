/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.2</a>, using an XML
 * Schema.
 * $Id$
 */

package nl.b3p.csw.client.castor.cswFilter;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class Filter.
 * 
 * @version $Revision$ $Date$
 */
public class Filter implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _propertyIsLike
     */
    private nl.b3p.csw.client.castor.cswFilter.PropertyIsLike _propertyIsLike;


      //----------------/
     //- Constructors -/
    //----------------/

    public Filter() 
     {
        super();
    } //-- nl.b3p.csw.client.castor.cswFilter.Filter()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'propertyIsLike'.
     * 
     * @return PropertyIsLike
     * @return the value of field 'propertyIsLike'.
     */
    public nl.b3p.csw.client.castor.cswFilter.PropertyIsLike getPropertyIsLike()
    {
        return this._propertyIsLike;
    } //-- nl.b3p.csw.client.castor.cswFilter.PropertyIsLike getPropertyIsLike() 

    /**
     * Method isValid
     * 
     * 
     * 
     * @return boolean
     */
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param out
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param handler
     */
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Sets the value of field 'propertyIsLike'.
     * 
     * @param propertyIsLike the value of field 'propertyIsLike'.
     */
    public void setPropertyIsLike(nl.b3p.csw.client.castor.cswFilter.PropertyIsLike propertyIsLike)
    {
        this._propertyIsLike = propertyIsLike;
    } //-- void setPropertyIsLike(nl.b3p.csw.client.castor.cswFilter.PropertyIsLike) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Filter
     */
    public static nl.b3p.csw.client.castor.cswFilter.Filter unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (nl.b3p.csw.client.castor.cswFilter.Filter) Unmarshaller.unmarshal(nl.b3p.csw.client.castor.cswFilter.Filter.class, reader);
    } //-- nl.b3p.csw.client.castor.cswFilter.Filter unmarshal(java.io.Reader) 

    /**
     * Method validate
     * 
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
