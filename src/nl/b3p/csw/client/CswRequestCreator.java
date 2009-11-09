/*
 * Copyright 2009 B3Partners BV
 * 
 */
package nl.b3p.csw.client;

import java.io.IOException;
import javax.naming.OperationNotSupportedException;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
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
import org.geotools.geometry.jts.WKTReader2;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.geom;
import java.io.StringReader;
import java.io.StringWriter;
import nl.b3p.csw.util.MarshallUtil;
import org.geotools.xml.DocumentWriter;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.DOMOutputter;
import org.opengis.geometry.Geometry;

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

    public static JAXBElement<GetRecordsType> createCswRequest(String wktFilter) throws JDOMException, IOException, OperationNotSupportedException, JAXBException {
        // nogal wat omwegen: wkt string -> geotools gml3 object -> xml string -> mijn jaxb gml3 object
        // check imports: vind com.vividsolution.jts.* !!!!
        Geometry geom = new WKTReader2().read(wktFilter);

        StringWriter stringWriter = new StringWriter();
        // schema erbij? (eerste null)
        DocumentWriter.writeFragment(geom, null, stringWriter, null);

        Document jdomDoc = new SAXBuilder(true).build(new StringReader(stringWriter.toString()));
        DOMOutputter domOutputter = new DOMOutputter();
        org.w3c.dom.Document w3cDomDoc = domOutputter.output(jdomDoc);

        JAXBElement<FilterType> filter = MarshallUtil.unMarshall(w3cDomDoc, null); // TODO: unmarshall
        FilterType gml3Filter = filter.getValue();

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
