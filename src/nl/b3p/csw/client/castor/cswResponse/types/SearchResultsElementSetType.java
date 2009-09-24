/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.2</a>, using an XML
 * Schema.
 * $Id$
 */

package nl.b3p.csw.client.castor.cswResponse.types;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.util.Hashtable;

/**
 * Class SearchResultsElementSetType.
 * 
 * @version $Revision$ $Date$
 */
public class SearchResultsElementSetType implements java.io.Serializable {


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
    public static final SearchResultsElementSetType FULL = new SearchResultsElementSetType(FULL_TYPE, "full");

    /**
     * The brief type
     */
    public static final int BRIEF_TYPE = 1;

    /**
     * The instance of the brief type
     */
    public static final SearchResultsElementSetType BRIEF = new SearchResultsElementSetType(BRIEF_TYPE, "brief");

    /**
     * The summary type
     */
    public static final int SUMMARY_TYPE = 2;

    /**
     * The instance of the summary type
     */
    public static final SearchResultsElementSetType SUMMARY = new SearchResultsElementSetType(SUMMARY_TYPE, "summary");

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

    private SearchResultsElementSetType(int type, java.lang.String value) 
     {
        super();
        this.type = type;
        this.stringValue = value;
    } //-- nl.b3p.csw.client.castor.cswResponse.types.SearchResultsElementSetType(int, java.lang.String)


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate
     * 
     * Returns an enumeration of all possible instances of
     * SearchResultsElementSetType
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
     * Returns the type of this SearchResultsElementSetType
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
     * Returns the String representation of this
     * SearchResultsElementSetType
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
     * Returns a new SearchResultsElementSetType based on the given
     * String value.
     * 
     * @param string
     * @return SearchResultsElementSetType
     */
    public static nl.b3p.csw.client.castor.cswResponse.types.SearchResultsElementSetType valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid SearchResultsElementSetType";
            throw new IllegalArgumentException(err);
        }
        return (SearchResultsElementSetType) obj;
    } //-- nl.b3p.csw.client.castor.cswResponse.types.SearchResultsElementSetType valueOf(java.lang.String) 

}
