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
 * Class PropertyIsLike.
 * 
 * @version $Revision$ $Date$
 */
public class PropertyIsLike implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _wildCard
     */
    private nl.b3p.csw.client.castor.cswFilter.types.PropertyIsLikeWildCardType _wildCard;

    /**
     * Field _singleChar
     */
    private nl.b3p.csw.client.castor.cswFilter.types.PropertyIsLikeSingleCharType _singleChar;

    /**
     * Field _escape
     */
    private nl.b3p.csw.client.castor.cswFilter.types.PropertyIsLikeEscapeType _escape;

    /**
     * Field _propertyName
     */
    private java.lang.String _propertyName;

    /**
     * Field _literal
     */
    private java.lang.String _literal;


      //----------------/
     //- Constructors -/
    //----------------/

    public PropertyIsLike() 
     {
        super();
    } //-- nl.b3p.csw.client.castor.cswFilter.PropertyIsLike()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'escape'.
     * 
     * @return PropertyIsLikeEscapeType
     * @return the value of field 'escape'.
     */
    public nl.b3p.csw.client.castor.cswFilter.types.PropertyIsLikeEscapeType getEscape()
    {
        return this._escape;
    } //-- nl.b3p.csw.client.castor.cswFilter.types.PropertyIsLikeEscapeType getEscape() 

    /**
     * Returns the value of field 'literal'.
     * 
     * @return String
     * @return the value of field 'literal'.
     */
    public java.lang.String getLiteral()
    {
        return this._literal;
    } //-- java.lang.String getLiteral() 

    /**
     * Returns the value of field 'propertyName'.
     * 
     * @return String
     * @return the value of field 'propertyName'.
     */
    public java.lang.String getPropertyName()
    {
        return this._propertyName;
    } //-- java.lang.String getPropertyName() 

    /**
     * Returns the value of field 'singleChar'.
     * 
     * @return PropertyIsLikeSingleCharType
     * @return the value of field 'singleChar'.
     */
    public nl.b3p.csw.client.castor.cswFilter.types.PropertyIsLikeSingleCharType getSingleChar()
    {
        return this._singleChar;
    } //-- nl.b3p.csw.client.castor.cswFilter.types.PropertyIsLikeSingleCharType getSingleChar() 

    /**
     * Returns the value of field 'wildCard'.
     * 
     * @return PropertyIsLikeWildCardType
     * @return the value of field 'wildCard'.
     */
    public nl.b3p.csw.client.castor.cswFilter.types.PropertyIsLikeWildCardType getWildCard()
    {
        return this._wildCard;
    } //-- nl.b3p.csw.client.castor.cswFilter.types.PropertyIsLikeWildCardType getWildCard() 

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
     * Sets the value of field 'escape'.
     * 
     * @param escape the value of field 'escape'.
     */
    public void setEscape(nl.b3p.csw.client.castor.cswFilter.types.PropertyIsLikeEscapeType escape)
    {
        this._escape = escape;
    } //-- void setEscape(nl.b3p.csw.client.castor.cswFilter.types.PropertyIsLikeEscapeType) 

    /**
     * Sets the value of field 'literal'.
     * 
     * @param literal the value of field 'literal'.
     */
    public void setLiteral(java.lang.String literal)
    {
        this._literal = literal;
    } //-- void setLiteral(java.lang.String) 

    /**
     * Sets the value of field 'propertyName'.
     * 
     * @param propertyName the value of field 'propertyName'.
     */
    public void setPropertyName(java.lang.String propertyName)
    {
        this._propertyName = propertyName;
    } //-- void setPropertyName(java.lang.String) 

    /**
     * Sets the value of field 'singleChar'.
     * 
     * @param singleChar the value of field 'singleChar'.
     */
    public void setSingleChar(nl.b3p.csw.client.castor.cswFilter.types.PropertyIsLikeSingleCharType singleChar)
    {
        this._singleChar = singleChar;
    } //-- void setSingleChar(nl.b3p.csw.client.castor.cswFilter.types.PropertyIsLikeSingleCharType) 

    /**
     * Sets the value of field 'wildCard'.
     * 
     * @param wildCard the value of field 'wildCard'.
     */
    public void setWildCard(nl.b3p.csw.client.castor.cswFilter.types.PropertyIsLikeWildCardType wildCard)
    {
        this._wildCard = wildCard;
    } //-- void setWildCard(nl.b3p.csw.client.castor.cswFilter.types.PropertyIsLikeWildCardType) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return PropertyIsLike
     */
    public static nl.b3p.csw.client.castor.cswFilter.PropertyIsLike unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (nl.b3p.csw.client.castor.cswFilter.PropertyIsLike) Unmarshaller.unmarshal(nl.b3p.csw.client.castor.cswFilter.PropertyIsLike.class, reader);
    } //-- nl.b3p.csw.client.castor.cswFilter.PropertyIsLike unmarshal(java.io.Reader) 

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
