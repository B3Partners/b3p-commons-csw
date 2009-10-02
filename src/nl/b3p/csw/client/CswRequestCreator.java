/*
 * Copyright 2009 B3Partners BV
 * 
 */
package nl.b3p.csw.client;

/*import nl.b3p.csw.client.castor.cswFilter.Filter;
import nl.b3p.csw.client.castor.cswFilter.PropertyIsLike;
import nl.b3p.csw.client.castor.cswFilter.types.PropertyIsLikeEscapeType;
import nl.b3p.csw.client.castor.cswFilter.types.PropertyIsLikeSingleCharType;
import nl.b3p.csw.client.castor.cswFilter.types.PropertyIsLikeWildCardType;
import nl.b3p.csw.client.castor.cswRequest.Constraint;
import nl.b3p.csw.client.castor.cswRequest.GetRecords;
import nl.b3p.csw.client.castor.cswRequest.Query;
import nl.b3p.csw.client.castor.cswRequest.types.ConstraintVersionType;
import nl.b3p.csw.client.castor.cswRequest.types.ElementSetNameType;
import nl.b3p.csw.client.castor.cswRequest.types.GetRecordsOutputSchemaType;
import nl.b3p.csw.client.castor.cswRequest.types.GetRecordsResultTypeType;
import nl.b3p.csw.client.castor.cswRequest.types.GetRecordsServiceType;
import nl.b3p.csw.client.castor.cswRequest.types.GetRecordsVersionType;
import nl.b3p.csw.client.castor.cswRequest.types.QueryTypeNamesType;*/

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


        /*return createCswRequest(queryString, propertyName,
                ElementSetNameType.valueOf(elementSetName), //ElementSetNameType.BRIEF, //ElementSetNameType.FULL, //ElementSetNameType.SUMMARY,
                GetRecordsOutputSchemaType.valueOf(outputSchema), // GetRecordsOutputSchemaType.CSW_ISORECORD, // GetRecordsOutputSchemaType.CSW_RECORD,
                GetRecordsResultTypeType.valueOf(resultType)); // GetRecordsResultTypeType.RESULTS, GetRecordsResultTypeType.HITS
         */
    }

    /*public static GetRecords createCswRequest(
            String queryString,
            String propertyName,
            ElementSetNameType elementSetNameType,
            GetRecordsOutputSchemaType getRecordsOutputSchemaType,
            GetRecordsResultTypeType getRecordsResultTypeType) {
        PropertyIsLike propertyIslike = new PropertyIsLike();
        propertyIslike.setEscape(PropertyIsLikeEscapeType._);
        if ("identifier".equals(propertyName)) {
            // FIXME:
            // we assume here we are searching for an uuid.
            // we strip the uuid of any starting '{' and/or ending '}'.
            // these are used by Esri in uuid's.
            // because we use 'PropertyIsLike' and 'anyText' the uuid will still be found in the db.
            // Of course this means '{abc}' and 'abc' will be equal uuid's,
            // which kind of defeats the first 'u' in uuid.
            // So this is still kind of a hack.

            propertyName = "anyText";

            log.debug("queryString before: " + queryString);

            if (queryString.startsWith("{")) {
                queryString = queryString.substring(1);
            }
            if (queryString.endsWith("}")) {
                queryString = queryString.substring(0, queryString.length() - 1);
            }

            log.debug("queryString after: " + queryString);

            propertyIslike.setLiteral(queryString);
        } else {
            propertyIslike.setLiteral("%" + queryString + "%");
        }
        propertyIslike.setPropertyName(propertyName);
        propertyIslike.setSingleChar(PropertyIsLikeSingleCharType._);
        propertyIslike.setWildCard(PropertyIsLikeWildCardType._);
        Filter filter = new Filter();
        filter.setPropertyIsLike(propertyIslike);
        Constraint constraint = new Constraint();
        constraint.setFilter(filter);
        constraint.setVersion(ConstraintVersionType.VALUE_0);
        Query query = new Query();
        query.setConstraint(constraint);
        query.setElementSetName(elementSetNameType);
        query.setTypeNames(QueryTypeNamesType.GMD_MD_METADATA);
        GetRecords getRecords = new GetRecords();
        getRecords.setOutputSchema(getRecordsOutputSchemaType);
        getRecords.setQuery(query);
        getRecords.setResultType(getRecordsResultTypeType);
        getRecords.setService(GetRecordsServiceType.CSW);
        getRecords.setVersion(GetRecordsVersionType.VALUE_0);
        return getRecords;
    }*/

    /*public static String marshalObject(Object o) throws IOException, MarshalException, ValidationException {
        StringWriter xmlString = new StringWriter();
        try {
            Marshaller marshal = new Marshaller(xmlString);
            marshal.setEncoding("UTF-8");
            marshal.setNamespaceMapping("csw", "http://www.opengis.net/cat/csw/2.0.2");
            marshal.setNamespaceMapping("ogc", "http://www.opengis.net/ogc");
            marshal.marshal(o);
        } catch (IOException ex) {
            throw new IOException("Error marshalling object: " + o.toString(), ex);
        } catch (MarshalException ex) {
            throw new MarshalException("Error marshalling object: " + o.toString(), ex);
        } catch (ValidationException ex) {
            throw new ValidationException("Error marshalling object: " + o.toString(), ex);
        }

        return xmlString.toString();
    }*/


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
