/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.2</a>, using an XML
 * Schema.
 * $Id$
 */

package nl.b3p.csw.client.castor.cswResponse;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.util.Collections;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class SearchResults.
 * 
 * @version $Revision$ $Date$
 */
public class SearchResults implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _numberOfRecordsReturned
     */
    private int _numberOfRecordsReturned;

    /**
     * keeps track of state for field: _numberOfRecordsReturned
     */
    private boolean _has_numberOfRecordsReturned;

    /**
     * Field _numberOfRecordsMatched
     */
    private int _numberOfRecordsMatched;

    /**
     * keeps track of state for field: _numberOfRecordsMatched
     */
    private boolean _has_numberOfRecordsMatched;

    /**
     * Field _nextRecord
     */
    private int _nextRecord;

    /**
     * keeps track of state for field: _nextRecord
     */
    private boolean _has_nextRecord;

    /**
     * Field _elementSet
     */
    private nl.b3p.csw.client.castor.cswResponse.types.SearchResultsElementSetType _elementSet;

    /**
     * Field _anynodeList
     */
    private java.util.ArrayList _anynodeList;


      //----------------/
     //- Constructors -/
    //----------------/

    public SearchResults() 
     {
        super();
        _anynodeList = new java.util.ArrayList();
    } //-- nl.b3p.csw.client.castor.cswResponse.SearchResults()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addAnynode
     * 
     * 
     * 
     * @param vAnynode
     */
    public void addAnynode(java.lang.Object vAnynode)
        throws java.lang.IndexOutOfBoundsException
    {
        _anynodeList.add(vAnynode);
    } //-- void addAnynode(java.lang.Object) 

    /**
     * Method addAnynode
     * 
     * 
     * 
     * @param index
     * @param vAnynode
     */
    public void addAnynode(int index, java.lang.Object vAnynode)
        throws java.lang.IndexOutOfBoundsException
    {
        _anynodeList.add(index, vAnynode);
    } //-- void addAnynode(int, java.lang.Object) 

    /**
     * Method clearAnynode
     * 
     */
    public void clearAnynode()
    {
        _anynodeList.clear();
    } //-- void clearAnynode() 

    /**
     * Method deleteNextRecord
     * 
     */
    public void deleteNextRecord()
    {
        this._has_nextRecord= false;
    } //-- void deleteNextRecord() 

    /**
     * Method deleteNumberOfRecordsMatched
     * 
     */
    public void deleteNumberOfRecordsMatched()
    {
        this._has_numberOfRecordsMatched= false;
    } //-- void deleteNumberOfRecordsMatched() 

    /**
     * Method deleteNumberOfRecordsReturned
     * 
     */
    public void deleteNumberOfRecordsReturned()
    {
        this._has_numberOfRecordsReturned= false;
    } //-- void deleteNumberOfRecordsReturned() 

    /**
     * Method enumerateAnynode
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateAnynode()
    {
        return Collections.enumeration(_anynodeList);
    } //-- java.util.Enumeration enumerateAnynode() 

    /**
     * Method getAnynode
     * 
     * 
     * 
     * @param index
     * @return Object
     */
    public java.lang.Object getAnynode(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _anynodeList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (java.lang.Object) _anynodeList.get(index);
    } //-- java.lang.Object getAnynode(int) 

    /**
     * Method getAnynode
     * 
     * 
     * 
     * @return Object
     */
    public java.lang.Object[] getAnynode()
    {
        int size = _anynodeList.size();
        java.lang.Object[] mArray = new java.lang.Object[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (java.lang.Object) _anynodeList.get(index);
        }
        return mArray;
    } //-- java.lang.Object[] getAnynode() 

    /**
     * Method getAnynodeCount
     * 
     * 
     * 
     * @return int
     */
    public int getAnynodeCount()
    {
        return _anynodeList.size();
    } //-- int getAnynodeCount() 

    /**
     * Returns the value of field 'elementSet'.
     * 
     * @return SearchResultsElementSetType
     * @return the value of field 'elementSet'.
     */
    public nl.b3p.csw.client.castor.cswResponse.types.SearchResultsElementSetType getElementSet()
    {
        return this._elementSet;
    } //-- nl.b3p.csw.client.castor.cswResponse.types.SearchResultsElementSetType getElementSet() 

    /**
     * Returns the value of field 'nextRecord'.
     * 
     * @return int
     * @return the value of field 'nextRecord'.
     */
    public int getNextRecord()
    {
        return this._nextRecord;
    } //-- int getNextRecord() 

    /**
     * Returns the value of field 'numberOfRecordsMatched'.
     * 
     * @return int
     * @return the value of field 'numberOfRecordsMatched'.
     */
    public int getNumberOfRecordsMatched()
    {
        return this._numberOfRecordsMatched;
    } //-- int getNumberOfRecordsMatched() 

    /**
     * Returns the value of field 'numberOfRecordsReturned'.
     * 
     * @return int
     * @return the value of field 'numberOfRecordsReturned'.
     */
    public int getNumberOfRecordsReturned()
    {
        return this._numberOfRecordsReturned;
    } //-- int getNumberOfRecordsReturned() 

    /**
     * Method hasNextRecord
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasNextRecord()
    {
        return this._has_nextRecord;
    } //-- boolean hasNextRecord() 

    /**
     * Method hasNumberOfRecordsMatched
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasNumberOfRecordsMatched()
    {
        return this._has_numberOfRecordsMatched;
    } //-- boolean hasNumberOfRecordsMatched() 

    /**
     * Method hasNumberOfRecordsReturned
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasNumberOfRecordsReturned()
    {
        return this._has_numberOfRecordsReturned;
    } //-- boolean hasNumberOfRecordsReturned() 

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
     * Method removeAnynode
     * 
     * 
     * 
     * @param vAnynode
     * @return boolean
     */
    public boolean removeAnynode(java.lang.Object vAnynode)
    {
        boolean removed = _anynodeList.remove(vAnynode);
        return removed;
    } //-- boolean removeAnynode(java.lang.Object) 

    /**
     * Method setAnynode
     * 
     * 
     * 
     * @param index
     * @param vAnynode
     */
    public void setAnynode(int index, java.lang.Object vAnynode)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _anynodeList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _anynodeList.set(index, vAnynode);
    } //-- void setAnynode(int, java.lang.Object) 

    /**
     * Method setAnynode
     * 
     * 
     * 
     * @param anynodeArray
     */
    public void setAnynode(java.lang.Object[] anynodeArray)
    {
        //-- copy array
        _anynodeList.clear();
        for (int i = 0; i < anynodeArray.length; i++) {
            _anynodeList.add(anynodeArray[i]);
        }
    } //-- void setAnynode(java.lang.Object) 

    /**
     * Sets the value of field 'elementSet'.
     * 
     * @param elementSet the value of field 'elementSet'.
     */
    public void setElementSet(nl.b3p.csw.client.castor.cswResponse.types.SearchResultsElementSetType elementSet)
    {
        this._elementSet = elementSet;
    } //-- void setElementSet(nl.b3p.csw.client.castor.cswResponse.types.SearchResultsElementSetType) 

    /**
     * Sets the value of field 'nextRecord'.
     * 
     * @param nextRecord the value of field 'nextRecord'.
     */
    public void setNextRecord(int nextRecord)
    {
        this._nextRecord = nextRecord;
        this._has_nextRecord = true;
    } //-- void setNextRecord(int) 

    /**
     * Sets the value of field 'numberOfRecordsMatched'.
     * 
     * @param numberOfRecordsMatched the value of field
     * 'numberOfRecordsMatched'.
     */
    public void setNumberOfRecordsMatched(int numberOfRecordsMatched)
    {
        this._numberOfRecordsMatched = numberOfRecordsMatched;
        this._has_numberOfRecordsMatched = true;
    } //-- void setNumberOfRecordsMatched(int) 

    /**
     * Sets the value of field 'numberOfRecordsReturned'.
     * 
     * @param numberOfRecordsReturned the value of field
     * 'numberOfRecordsReturned'.
     */
    public void setNumberOfRecordsReturned(int numberOfRecordsReturned)
    {
        this._numberOfRecordsReturned = numberOfRecordsReturned;
        this._has_numberOfRecordsReturned = true;
    } //-- void setNumberOfRecordsReturned(int) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return SearchResults
     */
    public static nl.b3p.csw.client.castor.cswResponse.SearchResults unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (nl.b3p.csw.client.castor.cswResponse.SearchResults) Unmarshaller.unmarshal(nl.b3p.csw.client.castor.cswResponse.SearchResults.class, reader);
    } //-- nl.b3p.csw.client.castor.cswResponse.SearchResults unmarshal(java.io.Reader) 

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
