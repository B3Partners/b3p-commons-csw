/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.2</a>, using an XML
 * Schema.
 * $Id$
 */

package nl.b3p.csw.client.castor.cswRequest.types;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.util.Hashtable;

/**
 * Class QueryTypeNamesType.
 * 
 * @version $Revision$ $Date$
 */
public class QueryTypeNamesType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The gmd:MD_Metadata type
     */
    public static final int GMD_MD_METADATA_TYPE = 0;

    /**
     * The instance of the gmd:MD_Metadata type
     */
    public static final QueryTypeNamesType GMD_MD_METADATA = new QueryTypeNamesType(GMD_MD_METADATA_TYPE, "gmd:MD_Metadata");

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

    private QueryTypeNamesType(int type, java.lang.String value) 
     {
        super();
        this.type = type;
        this.stringValue = value;
    } //-- nl.b3p.csw.client.castor.cswRequest.types.QueryTypeNamesType(int, java.lang.String)


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate
     * 
     * Returns an enumeration of all possible instances of
     * QueryTypeNamesType
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
     * Returns the type of this QueryTypeNamesType
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
        members.put("gmd:MD_Metadata", GMD_MD_METADATA);
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
     * Returns the String representation of this QueryTypeNamesType
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
     * Returns a new QueryTypeNamesType based on the given String
     * value.
     * 
     * @param string
     * @return QueryTypeNamesType
     */
    public static nl.b3p.csw.client.castor.cswRequest.types.QueryTypeNamesType valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid QueryTypeNamesType";
            throw new IllegalArgumentException(err);
        }
        return (QueryTypeNamesType) obj;
    } //-- nl.b3p.csw.client.castor.cswRequest.types.QueryTypeNamesType valueOf(java.lang.String) 

}
