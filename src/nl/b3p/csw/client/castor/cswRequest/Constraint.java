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
import nl.b3p.csw.client.castor.cswFilter.Filter;
/**
 * Class Constraint.
 * 
 * @version $Revision$ $Date$
 */
public class Constraint implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _version
     */
    private nl.b3p.csw.client.castor.cswRequest.types.ConstraintVersionType _version;

    /**
     * Field _filter
     */
    private Filter _filter;


      //----------------/
     //- Constructors -/
    //----------------/

    public Constraint() 
     {
        super();
    } //-- nl.b3p.csw.client.castor.cswRequest.Constraint()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'filter'.
     * 
     * @return Filter
     * @return the value of field 'filter'.
     */
    public Filter getFilter()
    {
        return this._filter;
    } //-- Filter getFilter() 

    /**
     * Returns the value of field 'version'.
     * 
     * @return ConstraintVersionType
     * @return the value of field 'version'.
     */
    public nl.b3p.csw.client.castor.cswRequest.types.ConstraintVersionType getVersion()
    {
        return this._version;
    } //-- nl.b3p.csw.client.castor.cswRequest.types.ConstraintVersionType getVersion() 

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
     * Sets the value of field 'filter'.
     * 
     * @param filter the value of field 'filter'.
     */
    public void setFilter(Filter filter)
    {
        this._filter = filter;
    } //-- void setFilter(Filter) 

    /**
     * Sets the value of field 'version'.
     * 
     * @param version the value of field 'version'.
     */
    public void setVersion(nl.b3p.csw.client.castor.cswRequest.types.ConstraintVersionType version)
    {
        this._version = version;
    } //-- void setVersion(nl.b3p.csw.client.castor.cswRequest.types.ConstraintVersionType) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Constraint
     */
    public static nl.b3p.csw.client.castor.cswRequest.Constraint unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (nl.b3p.csw.client.castor.cswRequest.Constraint) Unmarshaller.unmarshal(nl.b3p.csw.client.castor.cswRequest.Constraint.class, reader);
    } //-- nl.b3p.csw.client.castor.cswRequest.Constraint unmarshal(java.io.Reader) 

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
