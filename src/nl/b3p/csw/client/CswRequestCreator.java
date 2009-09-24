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

  /*public String createEmptyCswResponse() {
    GetRecordsResponse r = new GetRecordsResponse();
    SearchStatus ss = new SearchStatus();
    ss.setTimestamp(new Date());
    r.setSearchStatus(ss);
    SearchResults sr = new SearchResults();
    sr.setElementSet(SearchResultsElementSetType.BRIEF);
    sr.setNextRecord(0);
    sr.setNumberOfRecordsMatched(0);
    sr.setNumberOfRecordsReturned(0);
    return marshalObject(r);
  }*/

  public String createCswRequest(String q, String p, String o, String s, String t) {
    if (q == null || q.trim().length() == 0) {
      return null;
    }

    if (p == null || p.trim().length() == 0) {
      p = "anyText";
    }

    if (o == null || o.trim().length() == 0) {
      o = ElementSetNameType.FULL.toString();
    }

    if (s == null || s.trim().length() == 0) {
      s = GetRecordsOutputSchemaType.CSW_ISORECORD.toString();
    }

    if (t == null || t.trim().length() == 0) {
      t = GetRecordsResultTypeType.RESULTS.toString();
    }

    return createCswRequest(q, p,
            ElementSetNameType.valueOf(o), //ElementSetNameType.BRIEF, //ElementSetNameType.FULL, //ElementSetNameType.SUMMARY,
            GetRecordsOutputSchemaType.valueOf(s), // GetRecordsOutputSchemaType.CSW_ISORECORD, // GetRecordsOutputSchemaType.CSW_RECORD,
            GetRecordsResultTypeType.valueOf(t)); // GetRecordsResultTypeType.RESULTS, GetRecordsResultTypeType.HITS
  }

  public String createCswRequest(String q, String p, ElementSetNameType esn,
          GetRecordsOutputSchemaType gros, GetRecordsResultTypeType grrt) {
    PropertyIsLike pil = new PropertyIsLike();
    pil.setEscape(PropertyIsLikeEscapeType._);
    if ("identifier".equals(p)) {
      // FIXME:
      // we assume here we are searching for an uuid.
      // we strip the uuid of any starting '{' and/or ending '}'.
      // these are used by Esri in uuid's.
      // because we use 'PropertyIsLike' and 'anyText' the uuid will still be found in the db.
      // Of course this means '{abc}' and 'abc' will be equal uuid's,
      // which kind of defeats the first 'u' in uuid.
      // So this is still kind of a hack.

      p = "anyText";

      log.debug("q before: " + q);

      if (q.startsWith("{")) {
        q = q.substring(1);
      }
      if (q.endsWith("}")) {
        q = q.substring(0, q.length() - 1);
      }

      log.debug("q after: " + q);

      pil.setLiteral(q);
    } else {
      pil.setLiteral("%" + q + "%");
    }
    pil.setPropertyName(p);
    pil.setSingleChar(PropertyIsLikeSingleCharType._);
    pil.setWildCard(PropertyIsLikeWildCardType._);
    Filter f = new Filter();
    f.setPropertyIsLike(pil);
    Constraint c = new Constraint();
    c.setFilter(f);
    c.setVersion(ConstraintVersionType.VALUE_0);
    Query qry = new Query();
    qry.setConstraint(c);
    qry.setElementSetName(esn);
    qry.setTypeNames(QueryTypeNamesType.GMD_MD_METADATA);
    GetRecords gr = new GetRecords();
    gr.setOutputSchema(gros);
    gr.setQuery(qry);
    gr.setResultType(grrt);
    gr.setService(GetRecordsServiceType.CSW);
    gr.setVersion(GetRecordsVersionType.VALUE_0);
    return marshalObject(gr);
  }

  private String marshalObject(Object o) {
    StringWriter xmlString = new StringWriter();
    try {
      Marshaller marshal = new Marshaller(xmlString);
      marshal.setEncoding("UTF-8");
      marshal.setNamespaceMapping("csw", "http://www.opengis.net/cat/csw/2.0.2");
      marshal.setNamespaceMapping("ogc", "http://www.opengis.net/ogc");
      marshal.marshal(o);
    } catch (IOException ex) {
      log.error("IOException: " + o.toString(), ex);
    } catch (MarshalException ex) {
      log.error("MarshalException: " + o.toString(), ex);
    } catch (ValidationException ex) {
      log.error("ValidationException: " + o.toString(), ex);
    }

    return xmlString.toString();
  }
  
}
