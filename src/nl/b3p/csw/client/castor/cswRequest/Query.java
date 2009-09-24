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
 * Class Query.
 * 
 * @version $Revision$ $Date$
 */
public class Query implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _typeNames
     */
    private nl.b3p.csw.client.castor.cswRequest.types.QueryTypeNamesType _typeNames;

    /**
     * Field _elementSetName
     */
    private nl.b3p.csw.client.castor.cswRequest.types.ElementSetNameType _elementSetName;

    /**
     * Field _constraint
     */
    private nl.b3p.csw.client.castor.cswRequest.Constraint _constraint;


      //----------------/
     //- Constructors -/
    //----------------/

    public Query() 
     {
        super();
    } //-- nl.b3p.csw.client.castor.cswRequest.Query()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'constraint'.
     * 
     * @return Constraint
     * @return the value of field 'constraint'.
     */
    public nl.b3p.csw.client.castor.cswRequest.Constraint getConstraint()
    {
        return this._constraint;
    } //-- nl.b3p.csw.client.castor.cswRequest.Constraint getConstraint() 

    /**
     * Returns the value of field 'elementSetName'.
     * 
     * @return ElementSetNameType
     * @return the value of field 'elementSetName'.
     */
    public nl.b3p.csw.client.castor.cswRequest.types.ElementSetNameType getElementSetName()
    {
        return this._elementSetName;
    } //-- nl.b3p.csw.client.castor.cswRequest.types.ElementSetNameType getElementSetName() 

    /**
     * Returns the value of field 'typeNames'.
     * 
     * @return QueryTypeNamesType
     * @return the value of field 'typeNames'.
     */
    public nl.b3p.csw.client.castor.cswRequest.types.QueryTypeNamesType getTypeNames()
    {
        return this._typeNames;
    } //-- nl.b3p.csw.client.castor.cswRequest.types.QueryTypeNamesType getTypeNames() 

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
     * Sets the value of field 'constraint'.
     * 
     * @param constraint the value of field 'constraint'.
     */
    public void setConstraint(nl.b3p.csw.client.castor.cswRequest.Constraint constraint)
    {
        this._constraint = constraint;
    } //-- void setConstraint(nl.b3p.csw.client.castor.cswRequest.Constraint) 

    /**
     * Sets the value of field 'elementSetName'.
     * 
     * @param elementSetName the value of field 'elementSetName'.
     */
    public void setElementSetName(nl.b3p.csw.client.castor.cswRequest.types.ElementSetNameType elementSetName)
    {
        this._elementSetName = elementSetName;
    } //-- void setElementSetName(nl.b3p.csw.client.castor.cswRequest.types.ElementSetNameType) 

    /**
     * Sets the value of field 'typeNames'.
     * 
     * @param typeNames the value of field 'typeNames'.
     */
    public void setTypeNames(nl.b3p.csw.client.castor.cswRequest.types.QueryTypeNamesType typeNames)
    {
        this._typeNames = typeNames;
    } //-- void setTypeNames(nl.b3p.csw.client.castor.cswRequest.types.QueryTypeNamesType) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Query
     */
    public static nl.b3p.csw.client.castor.cswRequest.Query unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (nl.b3p.csw.client.castor.cswRequest.Query) Unmarshaller.unmarshal(nl.b3p.csw.client.castor.cswRequest.Query.class, reader);
    } //-- nl.b3p.csw.client.castor.cswRequest.Query unmarshal(java.io.Reader) 

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
