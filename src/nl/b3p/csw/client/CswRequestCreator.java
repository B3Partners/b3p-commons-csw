/*
 * Copyright 2009 B3Partners BV
 * 
 */
package nl.b3p.csw.client;

import com.vividsolutions.jts.io.ParseException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import javax.naming.OperationNotSupportedException;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.transform.TransformerException;
import nl.b3p.csw.jaxb.csw.Constraint;
import nl.b3p.csw.jaxb.csw.ElementSetName;
import nl.b3p.csw.jaxb.csw.ElementSetNameType;
import nl.b3p.csw.jaxb.csw.ElementSetType;
import nl.b3p.csw.jaxb.csw.GetRecordById;
import nl.b3p.csw.jaxb.csw.GetRecordByIdType;
import nl.b3p.csw.jaxb.csw.GetRecords;
import nl.b3p.csw.jaxb.csw.GetRecordsType;
import nl.b3p.csw.jaxb.csw.Query;
import nl.b3p.csw.jaxb.csw.QueryConstraintType;
import nl.b3p.csw.jaxb.csw.QueryType;
import nl.b3p.csw.jaxb.csw.ResultType;
import nl.b3p.csw.jaxb.filter.FilterType;
import nl.b3p.csw.jaxb.filter.PropertyNameType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import nl.b3p.csw.jaxb.filter.BinarySpatialOpType;
import nl.b3p.csw.jaxb.filter.Filter;
import nl.b3p.csw.jaxb.filter.PropertyName;
import nl.b3p.csw.jaxb.filter.SortBy;
import nl.b3p.csw.jaxb.filter.SortByType;
import nl.b3p.csw.jaxb.filter.SortOrderType;
import nl.b3p.csw.jaxb.filter.SortPropertyType;
import nl.b3p.csw.util.Util;
import org.jdom.JDOMException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.xml.sax.SAXException;

/**
 *
 * @author Erik van de Pol
 *
 * Creates csw request using default values for oft used stuff.
 */
public class CswRequestCreator {

    private static Log log = LogFactory.getLog(CswRequestCreator.class);

    protected static final nl.b3p.csw.jaxb.csw.ObjectFactory cswFactory =
            new nl.b3p.csw.jaxb.csw.ObjectFactory();
    protected static final nl.b3p.csw.jaxb.filter.ObjectFactory filterFactory =
            new nl.b3p.csw.jaxb.filter.ObjectFactory();

    protected static final String defaultWildCard = "*";
    protected static final String defaultSingleChar = "?";
    protected static final String defaultEscapeChar = "\\";

    public static String createQueryString(String queryString, boolean forceSearchUsingPartialWords) {
        if (queryString == null || queryString.trim().length() == 0) {
            return defaultWildCard;
        } else {
            if (forceSearchUsingPartialWords) {
                queryString = surroundWithWildCards(queryString);
            }
            return queryString;
        }
    }

    public static String createOutputSchema(String outputSchema) {
        if (outputSchema == null || outputSchema.trim().length() == 0) {
            outputSchema = "http://www.isotc211.org/2005/gmd";
        }
        return outputSchema;
    }

    public static String createPropertyName(String propertyName) {
        if (propertyName == null || propertyName.trim().length() == 0) {
            propertyName = "anyText";
        }
        return propertyName;
    }

    public static ResultType createResultType(String resultTypeString) {
        ResultType resultType = ResultType.RESULTS;
        try {
            resultType = ResultType.fromValue(resultTypeString);
        } catch (Exception e) {
        }
        return resultType;
    }

    public static ElementSetNameType createElementSetNameType(String elementSetName) {
        ElementSetNameType elementSetNameType = new ElementSetNameType();
        elementSetNameType.setValue(ElementSetType.FULL);
        try {
            elementSetNameType.setValue(ElementSetType.fromValue(elementSetName));
        } catch (Exception e) {
        }
        return elementSetNameType;
    }

    // will sort on "anyText"
    public static SortBy createDefaultSortBy() {
        return createSortBy(null, SortOrderType.ASC);
    }

    public static SortBy createSortBy(String propertyName, SortOrderType type) {
        PropertyNameType propertyNameType = new PropertyNameType();
        propertyNameType.getContent().add(createPropertyName(propertyName));
        
        SortPropertyType sortProperty = new SortPropertyType(new PropertyName(propertyNameType), type);

        SortByType sortByType = new SortByType();
        sortByType.getSortProperty().add(sortProperty);

        return new SortBy(sortByType);
    }

    public static void addStartPosition(GetRecordsType getRecordsType, BigInteger startPosition) {
        if (startPosition != null) {
            getRecordsType.setStartPosition(startPosition);
        }
    }

    public static void addMaxRecords(GetRecordsType getRecordsType, BigInteger maxRecords) {
        if (maxRecords != null) {
            getRecordsType.setMaxRecords(maxRecords);
        }
    }

    public static GetRecords createSimpleCswRequest(String queryString) {
        return createCswRequest(queryString, "", null, null, null, "", "", "");
    }

    public static GetRecords createSimpleCswRequest(
            String queryString,
            BigInteger startPosition,
            BigInteger maxRecords,
            SortBy sortBy) {
        return createCswRequest(queryString, "", startPosition, maxRecords, sortBy, "", "", "");
    }

    public static GetRecords createCswRequest(
            String queryString,
            String propertyName,
            String elementSetName,
            String outputSchema,
            String resultTypeString) {
        return createCswRequest(queryString, propertyName, elementSetName, outputSchema, resultTypeString, true);
    }

    public static GetRecords createCswRequest(
            String queryString,
            String propertyName,
            String elementSetName,
            String outputSchema,
            String resultTypeString,
            boolean forceSearchUsingPartialWords) {
        return createCswRequest(queryString, propertyName, null, null, null, elementSetName, outputSchema, resultTypeString, forceSearchUsingPartialWords);
    }

    public static GetRecords createCswRequest(
            String queryString,
            String propertyName,
            BigInteger startPosition,
            BigInteger maxRecords,
            SortBy sortBy,
            String elementSetName,
            String outputSchema,
            String resultTypeString) {
        return createCswRequest(queryString, propertyName, startPosition, maxRecords, sortBy, elementSetName, outputSchema, resultTypeString, true);
    }

    /**
     * Defaults to simple PropertyIsLike filter
     * @param queryString
     * @param propertyName
     * @param elementSetName
     * @param outputSchema
     * @param resultTypeString
     * @param forceSearchUsingPartialWords
     * @return
     */
    public static GetRecords createCswRequest(
            String queryString,
            String propertyName,
            BigInteger startPosition,
            BigInteger maxRecords,
            SortBy sortBy,
            String elementSetName,
            String outputSchema,
            String resultTypeString,
            boolean forceSearchUsingPartialWords) {
        
        queryString = createQueryString(queryString, forceSearchUsingPartialWords);
        if (queryString == null) return null;

        propertyName = createPropertyName(propertyName);

        FilterType filterType = new FilterType();
        filterType.setComparisonOps(FilterCreator.createPropertyIsLike(queryString, propertyName));

        return createCswRequest(filterType,
                startPosition,
                maxRecords,
                sortBy,
                elementSetName,
                outputSchema,
                resultTypeString
                );
    }

    public static GetRecords createCswRequestPropertyIsEqual(
            String queryString,
            String propertyName,
            BigInteger startPosition,
            BigInteger maxRecords,
            SortBy sortBy,
            String elementSetName,
            String outputSchema,
            String resultTypeString,
            boolean forceSearchUsingPartialWords) {

        queryString = createQueryString(queryString, forceSearchUsingPartialWords);
        if (queryString == null) return null;

        propertyName = createPropertyName(propertyName);

        FilterType filterType = new FilterType();
        filterType.setComparisonOps(FilterCreator.createPropertyIsEqualTo(queryString, propertyName));

        return createCswRequest(filterType,
                startPosition,
                maxRecords,
                sortBy,
                elementSetName,
                outputSchema,
                resultTypeString
                );
    }

    public static GetRecords createCswRequest(
            JAXBElement<BinarySpatialOpType> binarySpatialOp,
            String propertyName,
            String wktFilter) throws JDOMException, IOException, OperationNotSupportedException, JAXBException, ParseException, NoSuchAuthorityCodeException, FactoryException, SAXException, TransformerException {

        JAXBElement geom = Util.readWkt(wktFilter);
        log.debug("wkt-type: " + geom.getValue().toString());

        PropertyNameType propertyNameType = new PropertyNameType();
        propertyNameType.getContent().add(propertyName);

        BinarySpatialOpType binarySpatialOpType = new BinarySpatialOpType();
        binarySpatialOpType.setPropertyName(new PropertyName(propertyNameType));
        binarySpatialOpType.setGeometry(geom);

        binarySpatialOp.setValue(binarySpatialOpType);

        FilterType gml3Filter = new FilterType();
        gml3Filter.setSpatialOps(binarySpatialOp);

        return createCswRequest(gml3Filter);
    }

    /*public static GetRecords createCswRequest(FilterType gml3Filter) {
        ElementSetNameType elementSetNameType = new ElementSetNameType();
        elementSetNameType.setValue(ElementSetType.FULL);
        return createCswRequest(elementSetNameType,
                "csw:IsoRecord",
                ResultType.RESULTS,
                gml3Filter);
    }*/

    public static GetRecords createCswRequest(FilterType filterType) {
        return createCswRequest(filterType, null, null, null, null, null, null);
    }

    public static GetRecords createCswRequest(FilterType filterType, BigInteger startPosition, BigInteger maxRecords, SortBy sortBy) {
        return createCswRequest(filterType, startPosition, maxRecords, sortBy, null, null, null);
    }

    public static GetRecords createCswRequest(
            FilterType filterType,
            BigInteger startPosition,
            BigInteger maxRecords,
            SortBy sortBy,
            String elementSetName,
            String outputSchema,
            String resultTypeString
            ) {

        ElementSetNameType elementSetNameType = createElementSetNameType(elementSetName);
        String outputSchemaType = createOutputSchema(outputSchema);
        ResultType resultType = createResultType(resultTypeString);

        GetRecordsType getRecordsType = new GetRecordsType();
        getRecordsType.setService("CSW");
        getRecordsType.setVersion("2.0.2");
        getRecordsType.setResultType(resultType);
        getRecordsType.setOutputSchema(outputSchemaType);
        //getRecordsType.setOutputFormat("application/xml");
        
        addStartPosition(getRecordsType, startPosition);
        addMaxRecords(getRecordsType, maxRecords);
        
        QueryType queryType = new QueryType();
        queryType.setSortBy(sortBy);
        queryType.setElementSetName(new ElementSetName(elementSetNameType));
        queryType.getTypeNames().add(QName.valueOf("gmd:MD_Metadata"));
        //queryType.getTypeNames().add(QName.valueOf("csw:Records"));

        if (filterType != null) {
            QueryConstraintType constraintType = new QueryConstraintType();
            constraintType.setVersion("1.1.0");
            constraintType.setFilter(new Filter(filterType));

            queryType.setConstraint(new Constraint(constraintType));
        }

        getRecordsType.setAbstractQuery(new Query(queryType));

        return new GetRecords(getRecordsType);
    }

    public static GetRecords createEmptyCswRequest() {
        return createEmptyCswRequest(null, null, null, null, null, null);
    }

    public static GetRecords createEmptyCswRequest(
            BigInteger startPosition,
            BigInteger maxRecords,
            SortBy sortBy,
            String elementSetName,
            String outputSchema,
            String resultTypeString
            ) {
        return createCswRequest(
                null,
                startPosition,
                maxRecords,
                sortBy,
                elementSetName,
                outputSchema,
                resultTypeString);
    }

    public static GetRecordById createGetRecordByIdRequest(String id) {
        return createGetRecordByIdRequest(new String[]{id});
    }

    public static GetRecordById createGetRecordByIdRequest(String[] ids) {
        GetRecordByIdType getRecordByIdType = new GetRecordByIdType();

        getRecordByIdType.setService("CSW");
        getRecordByIdType.setVersion("2.0.2");
        //getRecordByIdType.setOutputSchema("http://www.opengis.net/cat/csw/2.0.2");
        getRecordByIdType.setOutputSchema(createOutputSchema(null));
        //getRecordByIdType.setOutputFormat("application/xml");

        ElementSetNameType elementSetNameType = new ElementSetNameType();
        elementSetNameType.setValue(ElementSetType.FULL);

        getRecordByIdType.setElementSetName(new ElementSetName(elementSetNameType));
        getRecordByIdType.getId().addAll(Arrays.asList(ids));
        
        return new GetRecordById(getRecordByIdType);
    }

    protected static String surroundWithWildCards(String queryString) {
        // enabling this sometimes yields 0 results in Geonetwork
        /*if (!queryString.startsWith(defaultWildCard)) {
            queryString = defaultWildCard + queryString;
        }*/
        if (!queryString.endsWith(defaultWildCard)) {
            queryString = queryString + defaultWildCard;
        }
        return queryString;
    }
}
