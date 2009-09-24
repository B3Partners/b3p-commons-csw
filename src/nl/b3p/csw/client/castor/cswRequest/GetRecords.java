/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.2</a>, using an XML
 * Schema.
 * $Id$
 */

package nl.b3p.csw.client.castor.cswRequest;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class GetRecords.
 * 
 * @version $Revision$ $Date$
 */
public class GetRecords implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _version
     */
    private nl.b3p.csw.client.castor.cswRequest.types.GetRecordsVersionType _version;

    /**
     * Field _service
     */
    private nl.b3p.csw.client.castor.cswRequest.types.GetRecordsServiceType _service;

    /**
     * Field _resultType
     */
    private nl.b3p.csw.client.castor.cswRequest.types.GetRecordsResultTypeType _resultType;

    /**
     * Field _outputSchema
     */
    private nl.b3p.csw.client.castor.cswRequest.types.GetRecordsOutputSchemaType _outputSchema;

    /**
     * Field _query
     */
    private nl.b3p.csw.client.castor.cswRequest.Query _query;


      //----------------/
     //- Constructors -/
    //----------------/

    public GetRecords() 
     {
        super();
    } //-- nl.b3p.csw.client.castor.cswRequest.GetRecords()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'outputSchema'.
     * 
     * @return GetRecordsOutputSchemaType
     * @return the value of field 'outputSchema'.
     */
    public nl.b3p.csw.client.castor.cswRequest.types.GetRecordsOutputSchemaType getOutputSchema()
    {
        return this._outputSchema;
    } //-- nl.b3p.csw.client.castor.cswRequest.types.GetRecordsOutputSchemaType getOutputSchema() 

    /**
     * Returns the value of field 'query'.
     * 
     * @return Query
     * @return the value of field 'query'.
     */
    public nl.b3p.csw.client.castor.cswRequest.Query getQuery()
    {
        return this._query;
    } //-- nl.b3p.csw.client.castor.cswRequest.Query getQuery() 

    /**
     * Returns the value of field 'resultType'.
     * 
     * @return GetRecordsResultTypeType
     * @return the value of field 'resultType'.
     */
    public nl.b3p.csw.client.castor.cswRequest.types.GetRecordsResultTypeType getResultType()
    {
        return this._resultType;
    } //-- nl.b3p.csw.client.castor.cswRequest.types.GetRecordsResultTypeType getResultType() 

    /**
     * Returns the value of field 'service'.
     * 
     * @return GetRecordsServiceType
     * @return the value of field 'service'.
     */
    public nl.b3p.csw.client.castor.cswRequest.types.GetRecordsServiceType getService()
    {
        return this._service;
    } //-- nl.b3p.csw.client.castor.cswRequest.types.GetRecordsServiceType getService() 

    /**
     * Returns the value of field 'version'.
     * 
     * @return GetRecordsVersionType
     * @return the value of field 'version'.
     */
    public nl.b3p.csw.client.castor.cswRequest.types.GetRecordsVersionType getVersion()
    {
        return this._version;
    } //-- nl.b3p.csw.client.castor.cswRequest.types.GetRecordsVersionType getVersion() 

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
     * Sets the value of field 'outputSchema'.
     * 
     * @param outputSchema the value of field 'outputSchema'.
     */
    public void setOutputSchema(nl.b3p.csw.client.castor.cswRequest.types.GetRecordsOutputSchemaType outputSchema)
    {
        this._outputSchema = outputSchema;
    } //-- void setOutputSchema(nl.b3p.csw.client.castor.cswRequest.types.GetRecordsOutputSchemaType) 

    /**
     * Sets the value of field 'query'.
     * 
     * @param query the value of field 'query'.
     */
    public void setQuery(nl.b3p.csw.client.castor.cswRequest.Query query)
    {
        this._query = query;
    } //-- void setQuery(nl.b3p.csw.client.castor.cswRequest.Query) 

    /**
     * Sets the value of field 'resultType'.
     * 
     * @param resultType the value of field 'resultType'.
     */
    public void setResultType(nl.b3p.csw.client.castor.cswRequest.types.GetRecordsResultTypeType resultType)
    {
        this._resultType = resultType;
    } //-- void setResultType(nl.b3p.csw.client.castor.cswRequest.types.GetRecordsResultTypeType) 

    /**
     * Sets the value of field 'service'.
     * 
     * @param service the value of field 'service'.
     */
    public void setService(nl.b3p.csw.client.castor.cswRequest.types.GetRecordsServiceType service)
    {
        this._service = service;
    } //-- void setService(nl.b3p.csw.client.castor.cswRequest.types.GetRecordsServiceType) 

    /**
     * Sets the value of field 'version'.
     * 
     * @param version the value of field 'version'.
     */
    public void setVersion(nl.b3p.csw.client.castor.cswRequest.types.GetRecordsVersionType version)
    {
        this._version = version;
    } //-- void setVersion(nl.b3p.csw.client.castor.cswRequest.types.GetRecordsVersionType) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return GetRecords
     */
    public static nl.b3p.csw.client.castor.cswRequest.GetRecords unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (nl.b3p.csw.client.castor.cswRequest.GetRecords) Unmarshaller.unmarshal(nl.b3p.csw.client.castor.cswRequest.GetRecords.class, reader);
    } //-- nl.b3p.csw.client.castor.cswRequest.GetRecords unmarshal(java.io.Reader) 

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
