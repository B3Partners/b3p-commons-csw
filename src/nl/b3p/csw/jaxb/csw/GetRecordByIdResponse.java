//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.06.10 at 04:19:23 PM CEST 
//


package nl.b3p.csw.jaxb.csw;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

public class GetRecordByIdResponse
    extends JAXBElement<GetRecordByIdResponseType>
{

    protected final static QName NAME = new QName("http://www.opengis.net/cat/csw/2.0.2", "GetRecordByIdResponse");

    public GetRecordByIdResponse(GetRecordByIdResponseType value) {
        super(NAME, ((Class) GetRecordByIdResponseType.class), null, value);
    }

    public GetRecordByIdResponse() {
        super(NAME, ((Class) GetRecordByIdResponseType.class), null, null);
    }

}
