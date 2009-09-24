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
 * Class Response.
 * 
 * @version $Revision$ $Date$
 */
public class Response implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _itemList
     */
    private java.util.ArrayList _itemList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Response() 
     {
        super();
        _itemList = new java.util.ArrayList();
    } //-- nl.b3p.csw.client.castor.simpleResponse.Response()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addItem
     * 
     * 
     * 
     * @param vItem
     */
    public void addItem(nl.b3p.csw.client.castor.simpleResponse.Item vItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _itemList.add(vItem);
    } //-- void addItem(nl.b3p.csw.client.castor.simpleResponse.Item) 

    /**
     * Method addItem
     * 
     * 
     * 
     * @param index
     * @param vItem
     */
    public void addItem(int index, nl.b3p.csw.client.castor.simpleResponse.Item vItem)
        throws java.lang.IndexOutOfBoundsException
    {
        _itemList.add(index, vItem);
    } //-- void addItem(int, nl.b3p.csw.client.castor.simpleResponse.Item) 

    /**
     * Method clearItem
     * 
     */
    public void clearItem()
    {
        _itemList.clear();
    } //-- void clearItem() 

    /**
     * Method enumerateItem
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateItem()
    {
        return Collections.enumeration(_itemList);
    } //-- java.util.Enumeration enumerateItem() 

    /**
     * Method getItem
     * 
     * 
     * 
     * @param index
     * @return Item
     */
    public nl.b3p.csw.client.castor.simpleResponse.Item getItem(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _itemList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (nl.b3p.csw.client.castor.simpleResponse.Item) _itemList.get(index);
    } //-- nl.b3p.csw.client.castor.simpleResponse.Item getItem(int) 

    /**
     * Method getItem
     * 
     * 
     * 
     * @return Item
     */
    public nl.b3p.csw.client.castor.simpleResponse.Item[] getItem()
    {
        int size = _itemList.size();
        nl.b3p.csw.client.castor.simpleResponse.Item[] mArray = new nl.b3p.csw.client.castor.simpleResponse.Item[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (nl.b3p.csw.client.castor.simpleResponse.Item) _itemList.get(index);
        }
        return mArray;
    } //-- nl.b3p.csw.client.castor.simpleResponse.Item[] getItem() 

    /**
     * Method getItemCount
     * 
     * 
     * 
     * @return int
     */
    public int getItemCount()
    {
        return _itemList.size();
    } //-- int getItemCount() 

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
     * Method removeItem
     * 
     * 
     * 
     * @param vItem
     * @return boolean
     */
    public boolean removeItem(nl.b3p.csw.client.castor.simpleResponse.Item vItem)
    {
        boolean removed = _itemList.remove(vItem);
        return removed;
    } //-- boolean removeItem(nl.b3p.csw.client.castor.simpleResponse.Item) 

    /**
     * Method setItem
     * 
     * 
     * 
     * @param index
     * @param vItem
     */
    public void setItem(int index, nl.b3p.csw.client.castor.simpleResponse.Item vItem)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _itemList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _itemList.set(index, vItem);
    } //-- void setItem(int, nl.b3p.csw.client.castor.simpleResponse.Item) 

    /**
     * Method setItem
     * 
     * 
     * 
     * @param itemArray
     */
    public void setItem(nl.b3p.csw.client.castor.simpleResponse.Item[] itemArray)
    {
        //-- copy array
        _itemList.clear();
        for (int i = 0; i < itemArray.length; i++) {
            _itemList.add(itemArray[i]);
        }
    } //-- void setItem(nl.b3p.csw.client.castor.simpleResponse.Item) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Response
     */
    public static nl.b3p.csw.client.castor.simpleResponse.Response unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (nl.b3p.csw.client.castor.simpleResponse.Response) Unmarshaller.unmarshal(nl.b3p.csw.client.castor.simpleResponse.Response.class, reader);
    } //-- nl.b3p.csw.client.castor.simpleResponse.Response unmarshal(java.io.Reader) 

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
