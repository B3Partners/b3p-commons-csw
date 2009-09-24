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
 * Class GetRecordsOutputSchemaType.
 * 
 * @version $Revision$ $Date$
 */
public class GetRecordsOutputSchemaType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * The csw:Record type
     */
    public static final int CSW_RECORD_TYPE = 0;

    /**
     * The instance of the csw:Record type
     */
    public static final GetRecordsOutputSchemaType CSW_RECORD = new GetRecordsOutputSchemaType(CSW_RECORD_TYPE, "csw:Record");

    /**
     * The csw:IsoRecord type
     */
    public static final int CSW_ISORECORD_TYPE = 1;

    /**
     * The instance of the csw:IsoRecord type
     */
    public static final GetRecordsOutputSchemaType CSW_ISORECORD = new GetRecordsOutputSchemaType(CSW_ISORECORD_TYPE, "csw:IsoRecord");

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

    private GetRecordsOutputSchemaType(int type, java.lang.String value) 
     {
        super();
        this.type = type;
        this.stringValue = value;
    } //-- nl.b3p.csw.client.castor.cswRequest.types.GetRecordsOutputSchemaType(int, java.lang.String)


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method enumerate
     * 
     * Returns an enumeration of all possible instances of
     * GetRecordsOutputSchemaType
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
     * Returns the type of this GetRecordsOutputSchemaType
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
        members.put("csw:Record", CSW_RECORD);
        members.put("csw:IsoRecord", CSW_ISORECORD);
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
     * GetRecordsOutputSchemaType
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
     * Returns a new GetRecordsOutputSchemaType based on the given
     * String value.
     * 
     * @param string
     * @return GetRecordsOutputSchemaType
     */
    public static nl.b3p.csw.client.castor.cswRequest.types.GetRecordsOutputSchemaType valueOf(java.lang.String string)
    {
        java.lang.Object obj = null;
        if (string != null) obj = _memberTable.get(string);
        if (obj == null) {
            String err = "'" + string + "' is not a valid GetRecordsOutputSchemaType";
            throw new IllegalArgumentException(err);
        }
        return (GetRecordsOutputSchemaType) obj;
    } //-- nl.b3p.csw.client.castor.cswRequest.types.GetRecordsOutputSchemaType valueOf(java.lang.String) 

}
