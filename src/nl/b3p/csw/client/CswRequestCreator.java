/*
 * Copyright 2009 B3Partners BV
 * 
 */
package nl.b3p.csw.client;

import nl.b3p.csw.jaxb.request.ComparisonOpsType;
import nl.b3p.csw.jaxb.request.ConstraintType;
import nl.b3p.csw.jaxb.request.CswVersionType;
import nl.b3p.csw.jaxb.request.ElementSetNameType;
import nl.b3p.csw.jaxb.request.FilterType;
import nl.b3p.csw.jaxb.request.FilterVersionType;
import nl.b3p.csw.jaxb.request.GetRecords;
import nl.b3p.csw.jaxb.request.LiteralType;
import nl.b3p.csw.jaxb.request.LogicOpsType;
import nl.b3p.csw.jaxb.request.ObjectFactory;
import nl.b3p.csw.jaxb.request.OutputSchemaType;
import nl.b3p.csw.jaxb.request.PropertyIsLikeType;
import nl.b3p.csw.jaxb.request.PropertyNameType;
import nl.b3p.csw.jaxb.request.QueryType;
import nl.b3p.csw.jaxb.request.ResultTypeType;
import nl.b3p.csw.jaxb.request.ServiceType;
import nl.b3p.csw.jaxb.request.SpatialOpsType;
import nl.b3p.csw.jaxb.request.TypeNamesType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Erik van de Pol
 */
public class CswRequestCreator {

    protected static Log log = LogFactory.getLog(CswRequestCreator.class);

    public static GetRecords createSimpleCswRequest(String queryString) {
        return createCswRequest(queryString, "", "", "", "");
    }

    public static GetRecords createCswRequest(
            String queryString,
            String propertyName,
            String elementSetName,
            String outputSchema,
            String resultType) {
        if (queryString == null || queryString.trim().length() == 0) {
            return null;
        }

        if (propertyName == null || propertyName.trim().length() == 0) {
            propertyName = "anyText";
        }

        ElementSetNameType elementSetNameType = ElementSetNameType.FULL;
        try {
            elementSetNameType = ElementSetNameType.fromValue(elementSetName);
        } catch (Exception e) {}

        OutputSchemaType outputSchemaType = OutputSchemaType.CSW_ISO_RECORD;
        try {
            outputSchemaType = OutputSchemaType.fromValue(outputSchema);
        } catch (Exception e) {}

        ResultTypeType resultTypeType = ResultTypeType.RESULTS;
        try {
            resultTypeType = ResultTypeType.fromValue(resultType);
        } catch (Exception e) {}

        ObjectFactory objectFactory = new ObjectFactory();

        PropertyIsLikeType propertyIsLikeType = objectFactory.createPropertyIsLikeType();

        LiteralType literalType = objectFactory.createLiteralType();
        literalType.getContent().add(queryString);

        propertyIsLikeType.setLiteral(literalType);

        PropertyNameType propertyNameType = objectFactory.createPropertyNameType();
        propertyNameType.setContent(propertyName);
        
        propertyIsLikeType.setPropertyName(propertyNameType);

        propertyIsLikeType.setWildCard("*");
        propertyIsLikeType.setSingleChar("?");
        propertyIsLikeType.setEscapeChar("\\");

        FilterType filterType = objectFactory.createFilterType();
        filterType.setComparisonOps(objectFactory.createComparisonOps(propertyIsLikeType));

        return createCswRequest(elementSetNameType,
                outputSchemaType,
                resultTypeType,
                filterType);
    }

    public static GetRecords createCswRequest(
            ElementSetNameType elementSetNameType,
            OutputSchemaType outputSchemaType,
            ResultTypeType resultTypeType,
            FilterType filterType
            ) {
        ObjectFactory objectFactory = new ObjectFactory();

        GetRecords getRecords = objectFactory.createGetRecords();

        getRecords.setService(ServiceType.CSW);
        getRecords.setResultType(resultTypeType);
        getRecords.setOutputSchema(outputSchemaType);
        getRecords.setVersion(CswVersionType.Version_2_0_2);
        
        QueryType query = objectFactory.createQueryType();

        query.setElementSetName(elementSetNameType);
        query.setTypeNames(TypeNamesType.GMD_MD_METADATA);

        ConstraintType constraint = objectFactory.createConstraintType();

        constraint.setVersion(FilterVersionType.Version_1_1_0);
        constraint.setFilter(filterType);
        
        query.setConstraint(constraint);

        getRecords.setQuery(query);

        return getRecords;
    }
}
