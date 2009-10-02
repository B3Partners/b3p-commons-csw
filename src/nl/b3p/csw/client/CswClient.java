/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.b3p.csw.client;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.TransformerException;
import nl.b3p.csw.jaxb.request.GetRecords;
import nl.b3p.csw.jaxb.response.GetRecordsResponse;
import nl.b3p.csw.server.CswServable;
import nl.b3p.csw.server.GeoNetworkCswServer;
import nl.b3p.csw.util.CswClientFactory;
import nl.b3p.csw.util.OnlineResource;
import nl.b3p.csw.util.Protocol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
            throws IOException, JDOMException, JAXBException { //, MarshalException, ValidationException {
        GetRecords getRecords = input.getGetRecords();
        if (getRecords == null) {
            throw new IllegalArgumentException("Csw getRecords not set.");
        }

        JAXBContext jaxbContext = JAXBContext.newInstance("nl.b3p.csw.jaxb.request");
        Marshaller marshaller = jaxbContext.createMarshaller();

        //XMLOutputter o = new Xml
        String marshalledCswXml = marshaller.marshal(getRecords, null);
        
        //CswRequestCreator.marshalObject(getRecords);

        String xmlResponse = server.search(marshalledCswXml);
        SAXBuilder builder = new SAXBuilder(VALIDATE_CSW_RESPONSE);

        try {
            xmlDocument = builder.build(new StringReader(xmlResponse));
            return new Output(xmlDocument);
        } catch (JDOMException ex) {
            throw new JDOMException("Could not build an xml document from the csw response.\nResponse: " + xmlResponse, ex);
        } catch (IOException ex) {
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

            GetRecordsResponse response = output.getGetRecordsResponse();

            List<Object> listMD_Metadata = response.getSearchResults().getAnynode();
            System.out.println("MD_Metadata: " + listMD_Metadata.size());

            Document xmlDoc = output.getXml();
            outputter.output(xmlDoc, System.out);

            Document transformedXmlDoc = output.getTransformedXml(
                    "C:/dev_erik/b3p-commons-csw/xml/md-response.xsl");
            outputter.output(transformedXmlDoc, System.out);

            Document xmlDoc2 = CswClientFactory.searchSimpleAsXml("*eologie", server);
            outputter.output(xmlDoc2, System.out);

            Map<URI, List<OnlineResource>> map = output.getResourcesMap(Protocol.WMS);
            for (List<OnlineResource> resourceList : map.values()) {
                for (OnlineResource resource : resourceList) {
                    URI url = resource.getUrl();
                    if (url != null) {
                        System.out.println("Resource URL: " + url.toString());
                    }
                    String name = resource.getName();
                    if (name != null) {
                        System.out.println("Name: " + name);
                    }
                    String desc = resource.getDescription();
                    if (desc != null) {
                        System.out.println("Description: " + desc);
                    }
                    Protocol prot = resource.getProtocol();
                    if (prot != null) {
                        System.out.println("Protocol: " + prot.getName());
                    }
                    System.out.println();
                }
            }

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
        } catch (JAXBException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
