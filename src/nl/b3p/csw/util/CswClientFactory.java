/*
 * Copyright 2009 B3Partners BV
 * 
 */

package nl.b3p.csw.util;

import java.io.IOException;
import javax.xml.transform.TransformerException;
import nl.b3p.csw.client.CswClient;
import nl.b3p.csw.client.Input;
import nl.b3p.csw.client.Output;
import nl.b3p.csw.client.castor.cswRequest.GetRecords;
import nl.b3p.csw.server.CswServable;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.jdom.Document;
import org.jdom.JDOMException;

/**
 *
 * @author Erik van de Pol
 */
public class CswClientFactory {

  public static Document searchSimpleAsXml(String queryString, CswServable server)
          throws IOException, JDOMException, MarshalException, ValidationException {
    CswClient client = new CswClient(server);
    Input input = new Input(queryString);
    Output output = client.search(input);
    return output.getResultAsXml();
  }

  public static Document searchSimpleAsTransformedXml(String queryString, CswServable server, String transformPath)
          throws IOException, JDOMException, MarshalException, ValidationException, TransformerException {
    CswClient client = new CswClient(server);
    Input input = new Input(queryString);
    Output output = client.search(input);
    return output.getResultAsTransformedXml(transformPath);
  }

  public static Document searchAsXml(GetRecords getRecords, CswServable server)
          throws IOException, JDOMException, MarshalException, ValidationException {
    CswClient client = new CswClient(server);
    Input input = new Input(getRecords);
    Output output = client.search(input);
    return output.getResultAsXml();
  }

  public static Document searchAsTransformedXml(GetRecords getRecords, CswServable server, String transformPath)
          throws IOException, JDOMException, MarshalException, ValidationException, TransformerException {
    CswClient client = new CswClient(server);
    Input input = new Input(getRecords);
    Output output = client.search(input);
    return output.getResultAsTransformedXml(transformPath);
  }

}