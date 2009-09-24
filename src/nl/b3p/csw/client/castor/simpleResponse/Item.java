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

import java.util.Collections;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class Item.
 * 
 * @version $Revision$ $Date$
 */
public class Item implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _identifier
     */
    private java.lang.String _identifier;

    /**
     * Field _title
     */
    private java.lang.String _title;

    /**
     * Field _abstract
     */
    private java.lang.String _abstract;

    /**
     * Field _resourceList
     */
    private java.util.ArrayList _resourceList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Item() 
     {
        super();
        _resourceList = new java.util.ArrayList();
    } //-- nl.b3p.csw.client.castor.simpleResponse.Item()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addResource
     * 
     * 
     * 
     * @param vResource
     */
    public void addResource(nl.b3p.csw.client.castor.simpleResponse.Resource vResource)
        throws java.lang.IndexOutOfBoundsException
    {
        _resourceList.add(vResource);
    } //-- void addResource(nl.b3p.csw.client.castor.simpleResponse.Resource) 

    /**
     * Method addResource
     * 
     * 
     * 
     * @param index
     * @param vResource
     */
    public void addResource(int index, nl.b3p.csw.client.castor.simpleResponse.Resource vResource)
        throws java.lang.IndexOutOfBoundsException
    {
        _resourceList.add(index, vResource);
    } //-- void addResource(int, nl.b3p.csw.client.castor.simpleResponse.Resource) 

    /**
     * Method clearResource
     * 
     */
    public void clearResource()
    {
        _resourceList.clear();
    } //-- void clearResource() 

    /**
     * Method enumerateResource
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateResource()
    {
        return Collections.enumeration(_resourceList);
    } //-- java.util.Enumeration enumerateResource() 

    /**
     * Returns the value of field 'abstract'.
     * 
     * @return String
     * @return the value of field 'abstract'.
     */
    public java.lang.String getAbstract()
    {
        return this._abstract;
    } //-- java.lang.String getAbstract() 

    /**
     * Returns the value of field 'identifier'.
     * 
     * @return String
     * @return the value of field 'identifier'.
     */
    public java.lang.String getIdentifier()
    {
        return this._identifier;
    } //-- java.lang.String getIdentifier() 

    /**
     * Method getResource
     * 
     * 
     * 
     * @param index
     * @return Resource
     */
    public nl.b3p.csw.client.castor.simpleResponse.Resource getResource(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _resourceList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (nl.b3p.csw.client.castor.simpleResponse.Resource) _resourceList.get(index);
    } //-- nl.b3p.csw.client.castor.simpleResponse.Resource getResource(int) 

    /**
     * Method getResource
     * 
     * 
     * 
     * @return Resource
     */
    public nl.b3p.csw.client.castor.simpleResponse.Resource[] getResource()
    {
        int size = _resourceList.size();
        nl.b3p.csw.client.castor.simpleResponse.Resource[] mArray = new nl.b3p.csw.client.castor.simpleResponse.Resource[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (nl.b3p.csw.client.castor.simpleResponse.Resource) _resourceList.get(index);
        }
        return mArray;
    } //-- nl.b3p.csw.client.castor.simpleResponse.Resource[] getResource() 

    /**
     * Method getResourceCount
     * 
     * 
     * 
     * @return int
     */
    public int getResourceCount()
    {
        return _resourceList.size();
    } //-- int getResourceCount() 

    /**
     * Returns the value of field 'title'.
     * 
     * @return String
     * @return the value of field 'title'.
     */
    public java.lang.String getTitle()
    {
        return this._title;
    } //-- java.lang.String getTitle() 

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
     * Method removeResource
     * 
     * 
     * 
     * @param vResource
     * @return boolean
     */
    public boolean removeResource(nl.b3p.csw.client.castor.simpleResponse.Resource vResource)
    {
        boolean removed = _resourceList.remove(vResource);
        return removed;
    } //-- boolean removeResource(nl.b3p.csw.client.castor.simpleResponse.Resource) 

    /**
     * Sets the value of field 'abstract'.
     * 
     * @param _abstract
     * @param abstract the value of field 'abstract'.
     */
    public void setAbstract(java.lang.String _abstract)
    {
        this._abstract = _abstract;
    } //-- void setAbstract(java.lang.String) 

    /**
     * Sets the value of field 'identifier'.
     * 
     * @param identifier the value of field 'identifier'.
     */
    public void setIdentifier(java.lang.String identifier)
    {
        this._identifier = identifier;
    } //-- void setIdentifier(java.lang.String) 

    /**
     * Method setResource
     * 
     * 
     * 
     * @param index
     * @param vResource
     */
    public void setResource(int index, nl.b3p.csw.client.castor.simpleResponse.Resource vResource)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _resourceList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _resourceList.set(index, vResource);
    } //-- void setResource(int, nl.b3p.csw.client.castor.simpleResponse.Resource) 

    /**
     * Method setResource
     * 
     * 
     * 
     * @param resourceArray
     */
    public void setResource(nl.b3p.csw.client.castor.simpleResponse.Resource[] resourceArray)
    {
        //-- copy array
        _resourceList.clear();
        for (int i = 0; i < resourceArray.length; i++) {
            _resourceList.add(resourceArray[i]);
        }
    } //-- void setResource(nl.b3p.csw.client.castor.simpleResponse.Resource) 

    /**
     * Sets the value of field 'title'.
     * 
     * @param title the value of field 'title'.
     */
    public void setTitle(java.lang.String title)
    {
        this._title = title;
    } //-- void setTitle(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Item
     */
    public static nl.b3p.csw.client.castor.simpleResponse.Item unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (nl.b3p.csw.client.castor.simpleResponse.Item) Unmarshaller.unmarshal(nl.b3p.csw.client.castor.simpleResponse.Item.class, reader);
    } //-- nl.b3p.csw.client.castor.simpleResponse.Item unmarshal(java.io.Reader) 

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
