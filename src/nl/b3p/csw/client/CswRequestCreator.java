/*
 * Copyright 2009 B3Partners BV
 * 
 */
package nl.b3p.csw.client;

import com.vividsolutions.jts.io.ParseException;
import java.io.IOException;
import javax.naming.OperationNotSupportedException;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.transform.TransformerException;
import nl.b3p.csw.jaxb.csw.Constraint;
import nl.b3p.csw.jaxb.csw.ElementSetName;
import nl.b3p.csw.jaxb.csw.ElementSetNameType;
import nl.b3p.csw.jaxb.csw.ElementSetType;
import nl.b3p.csw.jaxb.csw.GetRecordByIdType;
import nl.b3p.csw.jaxb.csw.GetRecords;
import nl.b3p.csw.jaxb.csw.GetRecordsType;
import nl.b3p.csw.jaxb.csw.Query;
import nl.b3p.csw.jaxb.csw.QueryConstraintType;
import nl.b3p.csw.jaxb.csw.QueryType;
import nl.b3p.csw.jaxb.csw.ResultType;
import nl.b3p.csw.jaxb.filter.FilterType;
import nl.b3p.csw.jaxb.filter.LiteralType;
import nl.b3p.csw.jaxb.filter.PropertyIsLikeType;
import nl.b3p.csw.jaxb.filter.PropertyNameType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import nl.b3p.csw.jaxb.filter.BinarySpatialOpType;
import nl.b3p.csw.jaxb.filter.Filter;
import nl.b3p.csw.jaxb.filter.Literal;
import nl.b3p.csw.jaxb.filter.PropertyName;
import nl.b3p.csw.util.Util;
import org.jdom.JDOMException;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.xml.sax.SAXException;

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

        propertyIsLikeType.setLiteral(new Literal(literalType));

        PropertyNameType propertyNameType = filterFactory.createPropertyNameType();
        //propertyNameType.setContent(propertyName);
        propertyNameType.getContent().add(propertyName);
        
        propertyIsLikeType.setPropertyName(new PropertyName(propertyNameType));

        propertyIsLikeType.setWildCard("*");
        propertyIsLikeType.setSingleChar("?");
        propertyIsLikeType.setEscapeChar("\\");

        FilterType filterType = filterFactory.createFilterType();
        filterType.setComparisonOps(filterFactory.createPropertyIsLike(propertyIsLikeType));


        return createCswRequest(elementSetNameType,
                outputSchema,
                resultType,
                filterType);
    }

    public static JAXBElement<GetRecordsType> createCswRequest(
            JAXBElement<BinarySpatialOpType> binarySpatialOp,
            String propertyName,
            String wktFilter) throws JDOMException, IOException, OperationNotSupportedException, JAXBException, ParseException, NoSuchAuthorityCodeException, FactoryException, SAXException, TransformerException {

        JAXBElement geom = Util.readWkt(wktFilter);
        log.debug("wkt-type: " + geom.getValue().toString());

        PropertyNameType propertyNameType = filterFactory.createPropertyNameType();
        propertyNameType.getContent().add(propertyName);

        BinarySpatialOpType binarySpatialOpType = filterFactory.createBinarySpatialOpType();
        binarySpatialOpType.setPropertyName(new PropertyName(propertyNameType));
        binarySpatialOpType.setGeometry(geom);

        binarySpatialOp.setValue(binarySpatialOpType);

        FilterType gml3Filter = filterFactory.createFilterType();
        gml3Filter.setSpatialOps(binarySpatialOp);

        return createCswRequest(gml3Filter);
    }

    public static JAXBElement<GetRecordsType> createCswRequest(FilterType gml3Filter) {
        ElementSetNameType elementSetNameType = cswFactory.createElementSetNameType();
        elementSetNameType.setValue(ElementSetType.FULL);
        return createCswRequest(elementSetNameType,
                "csw:IsoRecord",
                ResultType.RESULTS,
                gml3Filter);
    }

    public static JAXBElement<GetRecordsType> createCswRequest(
            ElementSetNameType elementSetNameType,
            String outputSchemaType,
            ResultType resultType,
            FilterType filterType
            ) {

        //GetRecordsType getRecordsType = cswFactory.createGetRecordsType();
        GetRecordsType getRecordsType = new GetRecordsType();

        getRecordsType.setService("CSW");
        getRecordsType.setResultType(resultType);
        getRecordsType.setOutputSchema(outputSchemaType);
        getRecordsType.setVersion("2.0.2");
        
        //QueryType queryType = cswFactory.createQueryType();
        QueryType queryType = new QueryType();

        queryType.setElementSetName(new ElementSetName(elementSetNameType));
        queryType.getTypeNames().add(QName.valueOf("gmd:MD_Metadata"));

        //QueryConstraintType constraintType = cswFactory.createQueryConstraintType();
        QueryConstraintType constraintType = new QueryConstraintType();

        constraintType.setVersion("1.1.0");
        constraintType.setFilter(new Filter(filterType));
        
        queryType.setConstraint(new Constraint(constraintType));

        getRecordsType.setAbstractQuery(new Query(queryType));

        //return cswFactory.createGetRecords(getRecordsType);
        return new GetRecords(getRecordsType);
    }

    public static JAXBElement<GetRecordByIdType> createGetRecordByIdRequest(String id) {
        GetRecordByIdType getRecordByIdType = cswFactory.createGetRecordByIdType();

        getRecordByIdType.setService("CSW");
        getRecordByIdType.setOutputSchema("http://www.opengis.net/cat/csw/2.0.2");
        getRecordByIdType.setOutputFormat("application/xml");
        getRecordByIdType.setVersion("2.0.2");

        ElementSetNameType elementSetNameType = cswFactory.createElementSetNameType();
        elementSetNameType.setValue(ElementSetType.FULL);
        
        getRecordByIdType.setElementSetName(new ElementSetName(elementSetNameType));
        getRecordByIdType.getId().add(id);
        
        return cswFactory.createGetRecordById(getRecordByIdType);
    }
}
