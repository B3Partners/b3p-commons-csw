/*
 * Copyright 2009 B3Partners BV
 * 
 */
package nl.b3p.csw.client;

import nl.b3p.csw.jaxb.request.ComparisonOpsType;
import nl.b3p.csw.jaxb.request.FilterType;
import nl.b3p.csw.jaxb.request.GetRecords;
import nl.b3p.csw.jaxb.request.LiteralType;
import nl.b3p.csw.jaxb.request.LogicOpsType;
import nl.b3p.csw.jaxb.request.ObjectFactory;
import nl.b3p.csw.jaxb.request.PropertyIsLikeType;
import nl.b3p.csw.jaxb.request.PropertyNameType;
import nl.b3p.csw.jaxb.request.SpatialOpsType;
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

        if (elementSetName == null || elementSetName.trim().length() == 0) {
            elementSetName = "full";//ElementSetNameType.FULL.toString();
        }

        if (outputSchema == null || outputSchema.trim().length() == 0) {
            outputSchema = "csw:IsoRecord";// GetRecordsOutputSchemaType.CSW_ISORECORD.toString();
        }

        if (resultType == null || resultType.trim().length() == 0) {
            resultType = "results";//GetRecordsResultTypeType.RESULTS.toString();
        }

        ObjectFactory objectFactory = new ObjectFactory();

        PropertyIsLikeType comparisonType = objectFactory.createPropertyIsLikeType();

        LiteralType literalType = objectFactory.createLiteralType();
        literalType.getContent().add(queryString);

        comparisonType.setLiteral(literalType);

        PropertyNameType propertyNameType = objectFactory.createPropertyNameType();
        propertyNameType.setContent(propertyName);
        
        comparisonType.setPropertyName(propertyNameType);

        comparisonType.setWildCard("*");
        comparisonType.setSingleChar("?");
        comparisonType.setEscapeChar("\\");

        return createCswRequest(elementSetName,
                outputSchema,
                resultType,
                comparisonType, null, null);
    }

    public static GetRecords createCswRequest(
            ComparisonOpsType comparisonOpsType,
            LogicOpsType logicOpsType,
            SpatialOpsType spatialOpsType
            ) {
        return createCswRequest("full", "csw:IsoRecord", "results", 
                comparisonOpsType, logicOpsType, spatialOpsType);
    }

    public static GetRecords createCswRequest(
            String elementSetName,
            String outputSchema,
            String resultType,
            ComparisonOpsType comparisonOpsType,
            LogicOpsType logicOpsType,
            SpatialOpsType spatialOpsType
            ) {
        ObjectFactory objectFactory = new ObjectFactory();

        GetRecords getRecords = objectFactory.createGetRecords();

        getRecords.setService("CSW");
        getRecords.setResultType(resultType);
        getRecords.setOutputSchema(outputSchema);
        getRecords.setVersion("2.0.2");
        
        GetRecords.Query query = objectFactory.createGetRecordsQuery();

        query.setElementSetName(elementSetName);
        query.setTypeNames("gmd:MD_Metadata");

        GetRecords.Query.Constraint constraint = objectFactory.createGetRecordsQueryConstraint();

        constraint.setVersion("1.1.0");

        FilterType filterType = objectFactory.createFilterType();

        // TODO: meerdere filters??? Rare/onduidelijke xsds.
        if (comparisonOpsType != null) {
            filterType.setComparisonOps(objectFactory.createComparisonOps(comparisonOpsType));
        }
        if (logicOpsType != null) {
            filterType.setLogicOps(objectFactory.createLogicOps(logicOpsType));
        }
        if (spatialOpsType != null) {
            filterType.setSpatialOps(objectFactory.createSpatialOps(spatialOpsType));
        }

        constraint.setFilter(filterType);
        
        query.setConstraint(constraint);

        getRecords.setQuery(query);

        return getRecords;
    }
}
