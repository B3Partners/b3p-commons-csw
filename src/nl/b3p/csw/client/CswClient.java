/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.b3p.csw.client;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import nl.b3p.csw.client.castor.cswRequest.GetRecords;
import nl.b3p.csw.server.CswServable;
import nl.b3p.csw.server.GeoNetworkCswServer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jdom.transform.JDOMResult;
import org.jdom.transform.JDOMSource;
//import org.geotools.referencing.wkt.Parser;

/**
 *
 * @author Erik van de Pol
 */
public class CswClient {

  protected final static boolean VALIDATE_CSW_RESPONSE = false;
  
  protected static Log log = LogFactory.getLog(CswClient.class);
  
  protected CswServable server;
  protected CswRequestCreator requestCreator;
  protected Document xmlDocument;

  protected CswClient() {
    this.server = null;
    this.requestCreator = new CswRequestCreator();
  }

  public CswClient(CswServable server) {
    this();
    this.server = server;
  }

  public void setServer(CswServable server) {
    this.server = server;
  }

  /**
   * Searches the server on all fields.
   * @param queryString
   * @throws NullPointerException
   */
  public void searchSimple(String queryString)
          throws NullPointerException, IOException, JDOMException, MarshalException, ValidationException {
    if (queryString == null || queryString.equals("")) {
      throw new NullPointerException("Query string empty or not set.");
    }

    GetRecords getRecords = requestCreator.createSimpleCswRequest(queryString);
    search(getRecords);
  }

  /**
   * Searches the server using getRecords.
   * A getRecords object can be obtained from a CswRequestCreator or from a GetRecords-object.
   * @param getRecords
   * @throws NullPointerException
   */
  public void search(GetRecords getRecords)
          throws NullPointerException, IOException, JDOMException, MarshalException, ValidationException {
    if (getRecords == null) {
      throw new NullPointerException("Csw getRecords not set.");
    }

    String marshalledCswXml = requestCreator.marshalObject(getRecords);
    String xmlResponse = server.search(marshalledCswXml);
    SAXBuilder builder = new SAXBuilder(VALIDATE_CSW_RESPONSE);

    try {
      xmlDocument = builder.build(new StringReader(xmlResponse));
    } catch(JDOMException ex) {
      throw new JDOMException("Could not build an xml document from the csw response.\nResponse: " + xmlResponse, ex);
    } catch(IOException ex) {
      throw new IOException("Could not build an xml document from the csw response.\nResponse: " + xmlResponse, ex);
    }
  }

  public HashMap getResultMap() {
    throw new UnsupportedOperationException();
  }

  public Document getResultAsXml() {
    return xmlDocument;
  }

  public Document getResultAsTransformedXml(String transformPath) throws TransformerException {
    try {
      Transformer transformer =
              TransformerFactory.newInstance().newTransformer(new StreamSource(transformPath));
      JDOMSource in = new JDOMSource(xmlDocument);
      JDOMResult out = new JDOMResult();
      transformer.transform(in, out);
      return out.getDocument();
    } catch (TransformerException e) {
      throw new TransformerException(
              "Xml Csw response could not be transformed with " + transformPath +
              ".\nXml: " + xmlDocument.toString(), e);
    }
  }

  // temporarily for testing purposes:
  public static void main(String[] args) {
    CswServable server = new GeoNetworkCswServer(
            "http://dev.b3p.nl/geonetwork/srv/en/xml.user.login",
            "http://dev.b3p.nl/geonetwork/srv/en/csw",
            "admin", "admin");
    CswClient client = new CswClient(server);

    try {
      client.searchSimple("archeo*");
      Document xmlDoc = client.getResultAsXml();

      XMLOutputter outputter = new XMLOutputter();
      outputter.output(xmlDoc, System.out);
    } catch (JDOMException ex) {
      ex.printStackTrace(System.err);
    } catch (IOException ex) {
      ex.printStackTrace(System.err);
    } catch (NullPointerException ex) {
      ex.printStackTrace(System.err);
    } catch (MarshalException ex) {
      ex.printStackTrace(System.err);
    } catch (ValidationException ex) {
      ex.printStackTrace(System.err);
    }
  }

}
