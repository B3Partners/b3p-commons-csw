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
 * Class GetRecordsResultTypeType.
 * 
 * @version $Revision$ $Date$
 */
public class GetRecordsResultTypeType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The results type
     */
    public static final int RESULTS_TYPE = 0;

    /**
     * The instance of the results type
     */
    public static final GetRecordsResultTypeType RESULTS = new GetRecordsResultTypeType(RESULTS_TYPE, "results");

    /**
     * The hits type
     */
    public static final int HITS_TYPE = 1;

    /**
     * The instance of the hits type
     */
    public static final GetRecordsResultTypeType HITS = new GetRecordsResultTypeType(HITS_TYPE, "hits");

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

    private GetRecordsResultTypeType(int type, java.lang.String value) 
     {
        super();
        this.type = type;
        this.stringValue = value;
    } //-- nl.b3p.csw.client.castor.cswRequest.types.GetRecordsResultTypeType(int, java.lang.String)


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate
     * 
     * Returns an enumeration of all possible instances of
     * GetRecordsResultTypeType
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
     * Returns the type of this GetRecordsResultTypeType
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
        members.put("results", RESULTS);
        members.put("hits", HITS);
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
     * GetRecordsResultTypeType
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
     * Returns a new GetRecordsResultTypeType based on the given
     * String value.
     * 
     * @param string
     * @return GetRecordsResultTypeType
     */
    public static nl.b3p.csw.client.castor.cswRequest.types.GetRecordsResultTypeType valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid GetRecordsResultTypeType";
            throw new IllegalArgumentException(err);
        }
        return (GetRecordsResultTypeType) obj;
    } //-- nl.b3p.csw.client.castor.cswRequest.types.GetRecordsResultTypeType valueOf(java.lang.String) 

}
