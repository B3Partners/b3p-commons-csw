/*
 * Copyright 2009 B3Partners BV
 * 
 */

package nl.b3p.csw.client;

import java.io.IOException;
import java.io.StringWriter;
import nl.b3p.csw.client.castor.cswFilter.Filter;
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
import nl.b3p.csw.client.castor.cswRequest.types.QueryTypeNamesType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.ValidationException;

/**
 *
 * @author Erik van de Pol
 */
public class CswRequestCreator {
  protected static Log log = null;

  public CswRequestCreator() {
    log = LogFactory.getLog(this.getClass());
  }

  public GetRecords createSimpleCswRequest(String queryString) {
    return createCswRequest(queryString, "", "", "", "");
  }

  public GetRecords createCswRequest(
          String queryString,
          String propertyName,
          String elementSetName,
          String outputSchema,
          String resultType)
  {
    if (queryString == null || queryString.trim().length() == 0) {
      return null;
    }

    if (propertyName == null || propertyName.trim().length() == 0) {
      propertyName = "anyText";
    }

    if (elementSetName == null || elementSetName.trim().length() == 0) {
      elementSetName = ElementSetNameType.FULL.toString();
    }

    if (outputSchema == null || outputSchema.trim().length() == 0) {
      outputSchema = GetRecordsOutputSchemaType.CSW_ISORECORD.toString();
    }

    if (resultType == null || resultType.trim().length() == 0) {
      resultType = GetRecordsResultTypeType.RESULTS.toString();
    }

    return createCswRequest(queryString, propertyName,
            ElementSetNameType.valueOf(elementSetName), //ElementSetNameType.BRIEF, //ElementSetNameType.FULL, //ElementSetNameType.SUMMARY,
            GetRecordsOutputSchemaType.valueOf(outputSchema), // GetRecordsOutputSchemaType.CSW_ISORECORD, // GetRecordsOutputSchemaType.CSW_RECORD,
            GetRecordsResultTypeType.valueOf(resultType)); // GetRecordsResultTypeType.RESULTS, GetRecordsResultTypeType.HITS
  }

  public GetRecords createCswRequest(
          String queryString,
          String propertyName,
          ElementSetNameType elementSetNameType,
          GetRecordsOutputSchemaType getRecordsOutputSchemaType,
          GetRecordsResultTypeType getRecordsResultTypeType)
  {
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
  }

  public String marshalObject(Object o) throws IOException, MarshalException, ValidationException{
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
  }
  
}
