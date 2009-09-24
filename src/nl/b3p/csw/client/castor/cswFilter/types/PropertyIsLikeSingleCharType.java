/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.2</a>, using an XML
 * Schema.
 * $Id$
 */

package nl.b3p.csw.client.castor.cswFilter.types;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.util.Hashtable;

/**
 * Class PropertyIsLikeSingleCharType.
 * 
 * @version $Revision$ $Date$
 */
public class PropertyIsLikeSingleCharType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The _ type
     */
    public static final int __TYPE = 0;

    /**
     * The instance of the _ type
     */
    public static final PropertyIsLikeSingleCharType _ = new PropertyIsLikeSingleCharType(__TYPE, "_");

    /**
     * Field _memberTable
     */
    private static java.util.Hashtable _memberTable = init();

    /**
     * Field type
     */
    private int type = -1;

    /**
     * Field stringValue
     */
    private java.lang.String stringValue = null;


      //----------------/
     //- Constructors -/
    //----------------/

    private PropertyIsLikeSingleCharType(int type, java.lang.String value) 
     {
        super();
        this.type = type;
        this.stringValue = value;
    } //-- nl.b3p.csw.client.castor.cswFilter.types.PropertyIsLikeSingleCharType(int, java.lang.String)


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate
     * 
     * Returns an enumeration of all possible instances of
     * PropertyIsLikeSingleCharType
     * 
     * @return Enumeration
     */
    public static java.util.Enumeration enumerate()
    {
        return _memberTable.elements();
    } //-- java.util.Enumeration enumerate() 

    /**
     * Method getType
     * 
     * Returns the type of this PropertyIsLikeSingleCharType
     * 
     * @return int
     */
    public int getType()
    {
        return this.type;
    } //-- int getType() 

    /**
     * Method init
     * 
     * 
     * 
     * @return Hashtable
     */
    private static java.util.Hashtable init()
    {
        Hashtable members = new Hashtable();
        members.put("_", _);
        return members;
    } //-- java.util.Hashtable init() 

    /**
     * Method readResolve
     * 
     *  will be called during deserialization to replace the
     * deserialized object with the correct constant instance.
     * <br/>
     * 
     * @return Object
     */
    private java.lang.Object readResolve()
    {
        return valueOf(this.stringValue);
    } //-- java.lang.Object readResolve() 

    /**
     * Method toString
     * 
     * Returns the String representation of this
     * PropertyIsLikeSingleCharType
     * 
     * @return String
     */
    public java.lang.String toString()
    {
        return this.stringValue;
    } //-- java.lang.String toString() 

    /**
     * Method valueOf
     * 
     * Returns a new PropertyIsLikeSingleCharType based on the
     * given String value.
     * 
     * @param string
     * @return PropertyIsLikeSingleCharType
     */
    public static nl.b3p.csw.client.castor.cswFilter.types.PropertyIsLikeSingleCharType valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid PropertyIsLikeSingleCharType";
            throw new IllegalArgumentException(err);
        }
        return (PropertyIsLikeSingleCharType) obj;
    } //-- nl.b3p.csw.client.castor.cswFilter.types.PropertyIsLikeSingleCharType valueOf(java.lang.String) 

}
