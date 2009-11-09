/*
 * Copyright 2009 B3Partners BV
 * 
 */
package nl.b3p.csw.client;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import nl.b3p.csw.jaxb.csw.ElementSetNameType;
import nl.b3p.csw.jaxb.csw.ElementSetType;
import nl.b3p.csw.jaxb.csw.GetRecordByIdType;
import nl.b3p.csw.jaxb.csw.GetRecordsType;
import nl.b3p.csw.jaxb.csw.QueryConstraintType;
import nl.b3p.csw.jaxb.csw.QueryType;
import nl.b3p.csw.jaxb.csw.ResultType;
import nl.b3p.csw.jaxb.filter.FilterType;
import nl.b3p.csw.jaxb.filter.LiteralType;
import nl.b3p.csw.jaxb.filter.PropertyIsLikeType;
import nl.b3p.csw.jaxb.filter.PropertyNameType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Erik van de Pol
 */
public class CswRequestCreator {

    protected static Log log = LogFactory.getLog(CswRequestCreator.class);

    protected static nl.b3p.csw.jaxb.csw.ObjectFactory cswFactory =
            new nl.b3p.csw.jaxb.csw.ObjectFactory();
    protected static nl.b3p.csw.jaxb.filter.ObjectFactory filterFactory =
            new nl.b3p.csw.jaxb.filter.ObjectFactory();

    public static JAXBElement<GetRecordsType> createSimpleCswRequest(String queryString) {
        return createCswRequest(queryString, "", "", "", "");
    }

    public static JAXBElement<GetRecordsType> createCswRequest(
            String queryString,
            String propertyName,
            String elementSetName,
            String outputSchema,
            String resultTypeString) {
        if (queryString == null || queryString.trim().length() == 0) {
            return null;
        }

        if (propertyName == null || propertyName.trim().length() == 0) {
            propertyName = "anyText";
        }

        ElementSetNameType elementSetNameType = cswFactory.createElementSetNameType();
        elementSetNameType.setValue(ElementSetType.FULL);
        try {
            elementSetNameType.setValue(ElementSetType.fromValue(elementSetName));
        } catch (Exception e) {}

        /*OutputSchemaType outputSchemaType = OutputSchemaType.CSW_ISO_RECORD;
        try {
            outputSchemaType = OutputSchemaType.fromValue(outputSchema);
        } catch (Exception e) {}*/
        if (outputSchema == null || outputSchema.trim().length() == 0) {
            outputSchema = "csw:IsoRecord";
        }

        ResultType resultType = ResultType.RESULTS;
        try {
            resultType = ResultType.fromValue(resultTypeString);
        } catch (Exception e) {}

        PropertyIsLikeType propertyIsLikeType = filterFactory.createPropertyIsLikeType();

        LiteralType literalType = filterFactory.createLiteralType();
        literalType.getContent().add(queryString);

        propertyIsLikeType.setLiteral(literalType);

        PropertyNameType propertyNameType = filterFactory.createPropertyNameType();
        //propertyNameType.setContent(propertyName);
        propertyNameType.getContent().add(propertyName);
        
        propertyIsLikeType.setPropertyName(propertyNameType);

        propertyIsLikeType.setWildCard("*");
        propertyIsLikeType.setSingleChar("?");
        propertyIsLikeType.setEscapeChar("\\");

        FilterType filterType = filterFactory.createFilterType();
        //filterType.setComparisonOps(filterFactory.createPrComparisonOps(propertyIsLikeType));
        filterType.setPropertyIsLike(propertyIsLikeType);


        return createCswRequest(elementSetNameType,
                outputSchema,
                resultType,
                filterType);
    }

    public static JAXBElement<GetRecordsType> createCswRequest(FilterType filterType) {
        ElementSetNameType elementSetNameType = cswFactory.createElementSetNameType();
        elementSetNameType.setValue(ElementSetType.FULL);
        return createCswRequest(elementSetNameType,
                "csw:IsoRecord",
                ResultType.RESULTS,
                filterType);
    }

    public static JAXBElement<GetRecordsType> createCswRequest(
            ElementSetNameType elementSetNameType,
            String outputSchemaType,
            ResultType resultType,
            FilterType filterType
            ) {

        GetRecordsType getRecordsType = cswFactory.createGetRecordsType();

        getRecordsType.setService("CSW");//ServiceType.CSW);
        getRecordsType.setResultType(resultType);
        getRecordsType.setOutputSchema(outputSchemaType);
        getRecordsType.setVersion("2.0.2");//CswVersionType.Version_2_0_2);
        
        QueryType queryType = cswFactory.createQueryType();

        queryType.setElementSetName(elementSetNameType);
        queryType.getTypeNames().add(QName.valueOf("gmd:MD_Metadata"));

        QueryConstraintType constraint = cswFactory.createQueryConstraintType();

        constraint.setVersion("1.1.0");//FilterVersionType.Version_1_1_0);
        constraint.setFilter(filterType);
        
        queryType.setConstraint(constraint);

        getRecordsType.setAbstractQuery(cswFactory.createQuery(queryType));

        return cswFactory.createGetRecords(getRecordsType);
    }

    public static JAXBElement<GetRecordByIdType> createGetRecordByIdRequest(String id) {
        GetRecordByIdType getRecordByIdType = cswFactory.createGetRecordByIdType();

        getRecordByIdType.setService("CSW");
        getRecordByIdType.setOutputSchema("http://www.opengis.net/cat/csw/2.0.2");
        getRecordByIdType.setOutputFormat("application/xml");
        getRecordByIdType.setVersion("2.0.2");

        ElementSetNameType elementSetNameType = cswFactory.createElementSetNameType();
        elementSetNameType.setValue(ElementSetType.FULL);
        
        getRecordByIdType.setElementSetName(elementSetNameType);
        getRecordByIdType.getId().add(id);
        
        return cswFactory.createGetRecordById(getRecordByIdType);
    }
}
