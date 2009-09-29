/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.b3p.csw.client;

import java.io.IOException;
import java.io.StringReader;
import javax.xml.transform.TransformerException;
import nl.b3p.csw.client.castor.cswRequest.GetRecords;
import nl.b3p.csw.server.CswServable;
import nl.b3p.csw.server.GeoNetworkCswServer;
import nl.b3p.csw.util.CswClientFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
//import org.geotools.referencing.wkt.Parser;

/**
 *
 * @author Erik van de Pol
 */
public class CswClient {

  protected final static boolean VALIDATE_CSW_RESPONSE = false;
  
  protected static Log log = LogFactory.getLog(CswClient.class);
  
  protected CswServable server;
  protected Document xmlDocument;

  protected CswClient() {
    this.server = null;
  }

  public CswClient(CswServable server) {
    this();
    this.setServer(server);
  }

  public void setServer(CswServable server) {
    this.server = server;
  }

  public Output search(Input input)
          throws IOException, JDOMException, MarshalException, ValidationException {
    GetRecords getRecords = input.getGetRecords();
    if (getRecords == null) {
      throw new IllegalArgumentException("Csw getRecords not set.");
    }

    String marshalledCswXml = CswRequestCreator.marshalObject(getRecords);
    String xmlResponse = server.search(marshalledCswXml);
    SAXBuilder builder = new SAXBuilder(VALIDATE_CSW_RESPONSE);

    try {
      xmlDocument = builder.build(new StringReader(xmlResponse));
      return new Output(xmlDocument);
    } catch(JDOMException ex) {
      throw new JDOMException("Could not build an xml document from the csw response.\nResponse: " + xmlResponse, ex);
    } catch(IOException ex) {
      throw new IOException("Could not build an xml document from the csw response.\nResponse: " + xmlResponse, ex);
    }
  }

  // temporarily for testing purposes:
  public static void main(String[] args) {
    CswServable server = new GeoNetworkCswServer(
            "http://dev.b3p.nl/geonetwork/srv/en/xml.user.login",
            "http://dev.b3p.nl/geonetwork/srv/en/csw",
            "admin", "admin");
    CswClient client = new CswClient(server);

    Input input = new Input("archeo*");

    XMLOutputter outputter = new XMLOutputter();
    
    try {
      Output output = client.search(input);
      
      Document xmlDoc = output.getResultAsXml();
      outputter.output(xmlDoc, System.out);

      Document transformedXmlDoc = output.getResultAsTransformedXml(
              "C:/dev_erik/b3p-commons-csw/xml/md-response.xsl");
      outputter.output(transformedXmlDoc, System.out);

      Document xmlDoc2 = CswClientFactory.searchSimpleAsXml("*eologie", server);
      outputter.output(xmlDoc2, System.out);

    } catch (JDOMException ex) {
      ex.printStackTrace(System.err);
    } catch (IOException ex) {
      ex.printStackTrace(System.err);
    } catch (MarshalException ex) {
      ex.printStackTrace(System.err);
    } catch (ValidationException ex) {
      ex.printStackTrace(System.err);
    } catch (TransformerException ex) {
      ex.printStackTrace(System.err);
    }
  }

}
