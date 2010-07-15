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
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.validation.Schema;
import nl.b3p.csw.jaxb.csw.Constraint;
import nl.b3p.csw.jaxb.csw.DeleteType;
import nl.b3p.csw.jaxb.csw.GetRecords;
import nl.b3p.csw.jaxb.csw.Harvest;
import nl.b3p.csw.jaxb.csw.HarvestResponse;
import nl.b3p.csw.jaxb.csw.HarvestResponseType;
import nl.b3p.csw.jaxb.csw.HarvestType;
import nl.b3p.csw.jaxb.csw.InsertType;
import nl.b3p.csw.jaxb.csw.Transaction;
import nl.b3p.csw.jaxb.csw.TransactionResponse;
import nl.b3p.csw.jaxb.csw.TransactionResponseType;
import nl.b3p.csw.jaxb.csw.TransactionType;
import nl.b3p.csw.jaxb.csw.UpdateType;
import nl.b3p.csw.jaxb.filter.Within;
import nl.b3p.csw.server.CswServable;
import nl.b3p.csw.server.GeoNetworkCswServer;
import nl.b3p.csw.util.CswClientFactory;
import nl.b3p.csw.util.MarshallUtil;
import nl.b3p.csw.util.OnlineResource;
import nl.b3p.csw.util.Protocol;
import nl.b3p.csw.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.xml.sax.SAXException;

/**
 *
 * @author Erik van de Pol
 *
 */
public class CswClient {

    protected static Log log = LogFactory.getLog(CswClient.class);
    
    protected CswServable server;

    protected Schema cswSchema;

    protected CswClient() {
        this.server = null;
        this.cswSchema = null;
    }

    public CswClient(CswServable server) {
        this();
        this.setServer(server);
    }

    public CswClient(CswServable server, String cswSchemaPath) throws SAXException {
        this(server);
        this.setCswSchema(cswSchemaPath);
    }


    public CswServable getServer() {
        return this.server;
    }

    public void setServer(CswServable server) {
        this.server = server;
    }

    public Schema getCswSchema() {
        return this.cswSchema;
    }

    public void setCswSchema(Schema schema) {
        this.cswSchema = schema;
    }

    public void setCswSchema(String validationPath) throws SAXException {
        this.cswSchema = Util.createSchema(validationPath);
    }

    public OutputById search(InputById input) throws IOException, JDOMException, JAXBException {
        return new OutputById(doRequest(input.getRequest()));
    }

    public OutputBySearch search(InputBySearch input) throws IOException, JDOMException, JAXBException {
        return new OutputBySearch(doRequest(input.getRequest()));
    }

    protected Document doRequest(JAXBElement jaxbElement) throws IOException, JDOMException, JAXBException {
        if (jaxbElement == null)
            throw new IllegalArgumentException("Provided jaxbElement must be non-null.");

        String marshalledCswXml = MarshallUtil.marshall(jaxbElement, null);

        //log.debug("Request:\n" + marshalledCswXml);

        // door expliciet te casten naar org.jdom.Document wordt het niet echt een algemene API
        // We zitten nu voor deze client altijd op JDOM.
        return (Document)server.doRequest(marshalledCswXml);
    }

    protected Transaction createTransaction(Object object) {
        TransactionType transactionType = new TransactionType();
        transactionType.getInsertOrUpdateOrDelete().add(object);

        return new Transaction(transactionType);
    }

    protected TransactionResponse createTransactionResponse(Document responseDocument) throws JDOMException, JAXBException {
        JAXBElement<TransactionResponseType> transactionResponse = (JAXBElement<TransactionResponseType>)
                MarshallUtil.unMarshall(responseDocument, cswSchema, TransactionResponseType.class);

        return new TransactionResponse(transactionResponse.getValue());
    }

    public TransactionResponse insert(Document document) throws IOException, JDOMException, JAXBException {
        InsertType insertType = new InsertType();
        insertType.getAny().add(new XMLOutputter().outputString(document));

        return insert(insertType);
    }

    public TransactionResponse insert(InsertType insertType) throws IOException, JDOMException, JAXBException {
        Document responseDocument = doRequest(createTransaction(insertType));

        return createTransactionResponse(responseDocument);
    }

    public TransactionResponse update(Document document) throws IOException, JDOMException, JAXBException {
        UpdateType updateType = new UpdateType();
        updateType.setAny(new XMLOutputter().outputString(document));

        return update(updateType);
    }

    public TransactionResponse update(UpdateType updateType) throws IOException, JDOMException, JAXBException {
        Document responseDocument = doRequest(createTransaction(updateType));

        return createTransactionResponse(responseDocument);
    }

    public TransactionResponse delete(Constraint constraint) throws IOException, JDOMException, JAXBException {
        DeleteType deleteType = new DeleteType();
        deleteType.setConstraint(constraint);

        return delete(deleteType);
    }

    public TransactionResponse delete(DeleteType deleteType) throws IOException, JDOMException, JAXBException {
        Document responseDocument = doRequest(createTransaction(deleteType));

        return createTransactionResponse(responseDocument);
    }

    public HarvestResponse harvest(HarvestType harvestType) throws IOException, JDOMException, JAXBException {
        Document responseDocument = doRequest(new Harvest(harvestType));

        JAXBElement<HarvestResponseType> harvestResponse = (JAXBElement<HarvestResponseType>)
                MarshallUtil.unMarshall(responseDocument, cswSchema, HarvestResponseType.class);

        return new HarvestResponse(harvestResponse.getValue());
    }

    // for testing purposes:
    public static void main(String[] args) {
        CswServable server = new GeoNetworkCswServer(
                "http://dev.b3p.nl/geonetwork/srv/en/xml.user.login",
                "http://dev.b3p.nl/geonetwork/srv/en/csw",
                "admin", "***REMOVED***");

        String cswValidationPath  = "c:\\dev_erik\\b3p-commons\\b3p-commons-csw\\jaxb\\xsds\\csw\\2.0.2\\CSW-discovery.xsd";

        //String testWktInput = "POLYGON((1 1,5 1,5 5,1 5,1 1),(2 2, 3 2, 3 3, 2 3,2 2))";
        String testWktInput = "POLYGON ((280 380, 280 200, 60 200, 60 380, 180 220, 280 380),(40 160, 260 160, 240 60, 20 80, 40 160))";

        try {
            GetRecords getRecords = CswRequestCreator.createCswRequest(
                    new Within(), "anyText", testWktInput);
            System.out.println(MarshallUtil.marshall(getRecords, null));

            CswClient client = new CswClient(server, cswValidationPath);

            InputBySearch inputBySearch = new InputBySearch("archeo");


            XMLOutputter outputter = new XMLOutputter();

            //Schema schema = MarshallUtil.createSchema(cswValidationPath);
            System.out.println(MarshallUtil.marshall(inputBySearch.getRequest(), null));
            /*SAXBuilder builder = new SAXBuilder(VALIDATE_CSW_RESPONSE);

            Document inputXmlDoc = builder.build(new FileInputStream("c:\\dev_erik\\b3p-commons-csw\\jaxb\\testcsw.xml"));

            boolean validateInputXml = true;
            input = new Input(inputXmlDoc, validateInputXml);*/

            /*InputById inputById = new InputById(
                    //"75e2306e-a8e4-4763-a7e3-c5837dad5a94"
                    "b9026a4c-962f-4826-b4f1-8ddf5c81c0f3"
                    //"1bb0eb5c-2ff9-434b-b32b-d6c47be52737"
                    //"48b51d03-c748-4669-9601-2a462992d105"
                    //"b823cc7c-9efd-4d8a-89b8-78f0dcdb319e"
                    //"f7bf2bd7-f765-463a-9f88-7d53b289ae23"
                    );
            System.out.println(MarshallUtil.marshall(inputById.getRequest(), null));
            OutputById outputById = client.search(inputById);

            Document getIdOutputXmlDoc = outputById.getXml();
            outputter.output(getIdOutputXmlDoc, System.out);*/
            


            OutputBySearch outputBySearch = client.search(inputBySearch);
            System.out.println("searchresults empty: " + outputBySearch.isEmpty());

            Document outputXmlDoc = outputBySearch.getXml();
            outputter.output(outputXmlDoc, System.out);

            List<Element> mdList = outputBySearch.getSearchResults();
            System.out.println("mdList size: " + mdList.size());
            for (Element elem : mdList) {
                System.out.println("elem: " + elem.toString());
            }

            //Document xmlDoc = output.getXml();
            //outputter.output(xmlDoc, System.out);

            Document transformedXmlDoc = outputBySearch.getTransformedXml(
                    "C:/dev_erik/b3p-commons/b3p-commons-csw/xml/md-response.xsl");
            outputter.output(transformedXmlDoc, System.out);

            Document xmlDoc2 = CswClientFactory.searchSimpleAsXml("*eologie", server);
            outputter.output(xmlDoc2, System.out);

            Map<URI, List<OnlineResource>> map = outputBySearch.getResourcesMap();
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

        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

}
