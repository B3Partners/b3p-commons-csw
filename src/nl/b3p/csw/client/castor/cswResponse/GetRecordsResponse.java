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

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class GetRecordsResponse.
 * 
 * @version $Revision$ $Date$
 */
public class GetRecordsResponse implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _searchStatus
     */
    private nl.b3p.csw.client.castor.cswResponse.SearchStatus _searchStatus;

    /**
     * Field _searchResults
     */
    private nl.b3p.csw.client.castor.cswResponse.SearchResults _searchResults;


      //----------------/
     //- Constructors -/
    //----------------/

    public GetRecordsResponse() 
     {
        super();
    } //-- nl.b3p.csw.client.castor.cswResponse.GetRecordsResponse()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'searchResults'.
     * 
     * @return SearchResults
     * @return the value of field 'searchResults'.
     */
    public nl.b3p.csw.client.castor.cswResponse.SearchResults getSearchResults()
    {
        return this._searchResults;
    } //-- nl.b3p.csw.client.castor.cswResponse.SearchResults getSearchResults() 

    /**
     * Returns the value of field 'searchStatus'.
     * 
     * @return SearchStatus
     * @return the value of field 'searchStatus'.
     */
    public nl.b3p.csw.client.castor.cswResponse.SearchStatus getSearchStatus()
    {
        return this._searchStatus;
    } //-- nl.b3p.csw.client.castor.cswResponse.SearchStatus getSearchStatus() 

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
     * Sets the value of field 'searchResults'.
     * 
     * @param searchResults the value of field 'searchResults'.
     */
    public void setSearchResults(nl.b3p.csw.client.castor.cswResponse.SearchResults searchResults)
    {
        this._searchResults = searchResults;
    } //-- void setSearchResults(nl.b3p.csw.client.castor.cswResponse.SearchResults) 

    /**
     * Sets the value of field 'searchStatus'.
     * 
     * @param searchStatus the value of field 'searchStatus'.
     */
    public void setSearchStatus(nl.b3p.csw.client.castor.cswResponse.SearchStatus searchStatus)
    {
        this._searchStatus = searchStatus;
    } //-- void setSearchStatus(nl.b3p.csw.client.castor.cswResponse.SearchStatus) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return GetRecordsResponse
     */
    public static nl.b3p.csw.client.castor.cswResponse.GetRecordsResponse unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (nl.b3p.csw.client.castor.cswResponse.GetRecordsResponse) Unmarshaller.unmarshal(nl.b3p.csw.client.castor.cswResponse.GetRecordsResponse.class, reader);
    } //-- nl.b3p.csw.client.castor.cswResponse.GetRecordsResponse unmarshal(java.io.Reader) 

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
