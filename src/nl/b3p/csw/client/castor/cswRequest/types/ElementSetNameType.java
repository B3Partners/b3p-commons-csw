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
 * Class ElementSetNameType.
 * 
 * @version $Revision$ $Date$
 */
public class ElementSetNameType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The full type
     */
    public static final int FULL_TYPE = 0;

    /**
     * The instance of the full type
     */
    public static final ElementSetNameType FULL = new ElementSetNameType(FULL_TYPE, "full");

    /**
     * The brief type
     */
    public static final int BRIEF_TYPE = 1;

    /**
     * The instance of the brief type
     */
    public static final ElementSetNameType BRIEF = new ElementSetNameType(BRIEF_TYPE, "brief");

    /**
     * The summary type
     */
    public static final int SUMMARY_TYPE = 2;

    /**
     * The instance of the summary type
     */
    public static final ElementSetNameType SUMMARY = new ElementSetNameType(SUMMARY_TYPE, "summary");

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

    private ElementSetNameType(int type, java.lang.String value) 
     {
        super();
        this.type = type;
        this.stringValue = value;
    } //-- nl.b3p.csw.client.castor.cswRequest.types.ElementSetNameType(int, java.lang.String)


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate
     * 
     * Returns an enumeration of all possible instances of
     * ElementSetNameType
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
     * Returns the type of this ElementSetNameType
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
        members.put("full", FULL);
        members.put("brief", BRIEF);
        members.put("summary", SUMMARY);
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
     * Returns the String representation of this ElementSetNameType
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
     * Returns a new ElementSetNameType based on the given String
     * value.
     * 
     * @param string
     * @return ElementSetNameType
     */
    public static nl.b3p.csw.client.castor.cswRequest.types.ElementSetNameType valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid ElementSetNameType";
            throw new IllegalArgumentException(err);
        }
        return (ElementSetNameType) obj;
    } //-- nl.b3p.csw.client.castor.cswRequest.types.ElementSetNameType valueOf(java.lang.String) 

}
