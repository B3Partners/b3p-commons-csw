/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.2</a>, using an XML
 * Schema.
 * $Id$
 */

package nl.b3p.csw.client.castor.simpleResponse;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class Resource.
 * 
 * @version $Revision$ $Date$
 */
public class Resource implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _linkage
     */
    private java.lang.String _linkage;

    /**
     * Field _protocol
     */
    private java.lang.String _protocol;

    /**
     * Field _name
     */
    private java.lang.String _name;


      //----------------/
     //- Constructors -/
    //----------------/

    public Resource() 
     {
        super();
    } //-- nl.b3p.csw.client.castor.simpleResponse.Resource()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'linkage'.
     * 
     * @return String
     * @return the value of field 'linkage'.
     */
    public java.lang.String getLinkage()
    {
        return this._linkage;
    } //-- java.lang.String getLinkage() 

    /**
     * Returns the value of field 'name'.
     * 
     * @return String
     * @return the value of field 'name'.
     */
    public java.lang.String getName()
    {
        return this._name;
    } //-- java.lang.String getName() 

    /**
     * Returns the value of field 'protocol'.
     * 
     * @return String
     * @return the value of field 'protocol'.
     */
    public java.lang.String getProtocol()
    {
        return this._protocol;
    } //-- java.lang.String getProtocol() 

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
     * Sets the value of field 'linkage'.
     * 
     * @param linkage the value of field 'linkage'.
     */
    public void setLinkage(java.lang.String linkage)
    {
        this._linkage = linkage;
    } //-- void setLinkage(java.lang.String) 

    /**
     * Sets the value of field 'name'.
     * 
     * @param name the value of field 'name'.
     */
    public void setName(java.lang.String name)
    {
        this._name = name;
    } //-- void setName(java.lang.String) 

    /**
     * Sets the value of field 'protocol'.
     * 
     * @param protocol the value of field 'protocol'.
     */
    public void setProtocol(java.lang.String protocol)
    {
        this._protocol = protocol;
    } //-- void setProtocol(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Resource
     */
    public static nl.b3p.csw.client.castor.simpleResponse.Resource unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (nl.b3p.csw.client.castor.simpleResponse.Resource) Unmarshaller.unmarshal(nl.b3p.csw.client.castor.simpleResponse.Resource.class, reader);
    } //-- nl.b3p.csw.client.castor.simpleResponse.Resource unmarshal(java.io.Reader) 

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
