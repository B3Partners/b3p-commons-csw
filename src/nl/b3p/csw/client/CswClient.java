/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.b3p.csw.client;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
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
import nl.b3p.csw.jaxb.csw.QueryConstraintType;
import nl.b3p.csw.jaxb.csw.Transaction;
import nl.b3p.csw.jaxb.csw.TransactionResponse;
import nl.b3p.csw.jaxb.csw.TransactionResponseType;
import nl.b3p.csw.jaxb.csw.TransactionType;
import nl.b3p.csw.jaxb.csw.UpdateType;
import nl.b3p.csw.jaxb.filter.*;
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
import org.jdom.Namespace;
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
    
    protected CswServable server = null;
    protected Schema cswSchema = null;

    //protected String APISO_XSD_PATH = "nl/b3p/csw/xsd/csw/2.0.2/profiles/apiso/1.0.0/apiso.xsd";
    protected String CSW_XSD_PATH = "nl/b3p/csw/xsd/csw/2.0.2/CSW-publication.xsd";

    
    protected CswClient() {
        
    }

    public CswClient(CswServable server) {
        init(server, CSW_XSD_PATH);
    }

    public CswClient(CswServable server, String cswSchemaPath) {
        init(server, cswSchemaPath);
    }
    
    private void init(CswServable server, String cswSchemaPath) {
        this.server = server;
        try {
            // schema validation turned off because of strange requirement that
            // iso 19139 must be loaded.
            // apparently caused by using "gmd:MD_Metadata" as the value of an attribute.
            // we seem to be required to use this as the value of the attribute though.
            this.cswSchema = null;//Util.createSchema(cswSchemaPath);
        } catch(Exception e) {
            log.warn("Schema validation not possible.", e);
        }
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

    public void setCswSchema(String validationPath) throws SAXException, URISyntaxException {
        this.cswSchema = Util.createSchema(validationPath);
    }

    public OutputById search(InputById input) throws IOException, JDOMException, JAXBException, OwsException {
        return new OutputById(doRequest(input.getRequest(), false));
    }

    public OutputBySearch search(InputBySearch input) throws IOException, JDOMException, JAXBException, OwsException {
        return new OutputBySearch(doRequest(input.getRequest(), false));
    }

    protected Document doRequest(JAXBElement jaxbElement, boolean transaction) throws IOException, JDOMException, JAXBException, OwsException {
        if (jaxbElement == null)
            throw new IllegalArgumentException("Provided jaxbElement must be non-null.");

        log.debug(MarshallUtil.marshall(jaxbElement, null));
        String marshalledCswXml = MarshallUtil.marshall(jaxbElement, cswSchema);

        // Voor compatibiliteit met het apiso profiel op csw:
        // OpenGIS Catalogue Services Specification 2.0.2 - ISO Metadata Application Profile (1.0.0)
        // voegen we de apiso namspace toe.
        // Zonder deze namespace kan Degree bijvoorbeeld geen delete request uitvoeren.
        Document doc = new SAXBuilder().build(new StringReader(marshalledCswXml));
        doc.getRootElement().addNamespaceDeclaration(Namespace.getNamespace("apiso", "http://www.opengis.net/cat/csw/apiso/1.0"));
        marshalledCswXml = new XMLOutputter().outputString(doc);

        //log.debug("Request:\n" + marshalledCswXml);

        // door expliciet te casten naar org.jdom.Document wordt het niet echt een algemene API
        // We zitten nu voor deze client altijd op JDOM.
        Document responseDocument = (Document)server.doRequest(marshalledCswXml, transaction);

        return responseDocument;
    }

    protected Transaction createTransaction(Object object) {
        TransactionType transactionType = new TransactionType();

        transactionType.setService("CSW");
        transactionType.setVersion("2.0.2");
        transactionType.getInsertOrUpdateOrDelete().add(object);

        return new Transaction(transactionType);
    }

    protected TransactionResponse createTransactionResponse(Document responseDocument) throws JDOMException, JAXBException {
        JAXBElement<TransactionResponseType> transactionResponse = (JAXBElement<TransactionResponseType>)
                MarshallUtil.unMarshall(responseDocument, cswSchema, TransactionResponseType.class);

        return new TransactionResponse(transactionResponse.getValue());
    }

    protected InsertType createInsertType(org.w3c.dom.Document document) {
        InsertType insertType = new InsertType();

        insertType.setTypeName("gmd:MD_Metadata");
        insertType.setHandle("insertHandle");// kan van alles zijn; is voor error handling/localization handig
        insertType.getAny().add(document.getDocumentElement());
        
        return insertType;
    }

    public TransactionResponse insert(org.w3c.dom.Document document) throws IOException, JDOMException, JAXBException, OwsException {
        return insert(createInsertType(document));
    }

    public TransactionResponse insert(InsertType insertType) throws IOException, JDOMException, JAXBException, OwsException {
        Document responseDocument = doRequest(createTransaction(insertType), true);

        return createTransactionResponse(responseDocument);
    }

    public TransactionResponse update(String fileIdentifier, org.w3c.dom.Document document) throws Exception {
        UpdateType update = new UpdateType();
        
        update.setHandle("update_" + fileIdentifier);
        update.setAny(document.getDocumentElement());
        
        QueryConstraintType qct = new QueryConstraintType();
        qct.setVersion("1.0.0");
        
        // Fails on (old?) GeoNetwork with:
        // Error transforming Filter to XMLnet.sf.saxon.trans.XPathException: 
        // The SAX2 parser org.geotools.xml.transform.TransformerBase$XMLReaderSupport 
        // does not support setting the 'namespaces' feature to true
        //qct.setCqlText("apiso:identifier = '" + fileIdentifier + "'");
                
        // Fucking shit having to use generated Java code from XML 
        // schema without thinking
        // WHILE GEOTOOLS ALREADY HAS FILTER SUPPORT!
        
        FilterType ft = new FilterType();
        
        List<JAXBElement<? extends ExpressionType>> operators = new ArrayList();
        
        List<Serializable> l = new ArrayList();
        l.add("apiso:identifier");
        operators.add(new PropertyName(new PropertyNameType(l)));
        
        l = new ArrayList();
        l.add(fileIdentifier);
        operators.add(new Literal(new LiteralType(l)));

        PropertyIsEqualTo eq = new PropertyIsEqualTo(new BinaryComparisonOpType(operators, null));
        ft.setComparisonOps(eq);
        qct.setFilter(new Filter(ft));

        update.setConstraint(new Constraint(qct));
        
        return update(update);
    }
    
    // TODO: update nog niet ok
    public TransactionResponse update(Document document) throws IOException, JDOMException, JAXBException, OwsException {
        UpdateType updateType = new UpdateType();
        updateType.setAny(new XMLOutputter().outputString(document));

        return update(updateType);
    }

    public TransactionResponse update(UpdateType updateType) throws IOException, JDOMException, JAXBException, OwsException {
        Document responseDocument = doRequest(createTransaction(updateType), true);

        return createTransactionResponse(responseDocument);
    }

    public DeleteType createDeleteType(String uuid) {
        DeleteType deleteType = new DeleteType();

        deleteType.setTypeName("gmd:MD_Metadata");
        deleteType.setHandle("deleteHandle");// kan van alles zijn; is voor error handling/localization handig

        PropertyIsEqualTo propertyIsEqualTo = FilterCreator.createPropertyIsEqualTo(
                uuid, "apiso:identifier", null);

        FilterType filterType = new FilterType();
        filterType.setComparisonOps(propertyIsEqualTo);

        QueryConstraintType queryConstraintType = new QueryConstraintType();
        queryConstraintType.setVersion("1.1.0");
        queryConstraintType.setFilter(new Filter(filterType));

        deleteType.setConstraint(new Constraint(queryConstraintType));

        return deleteType;
    }

    public DeleteType createDeleteType(String[] uuids) {
        if (uuids.length==1){
            return createDeleteType(uuids[0]);
        }
        DeleteType deleteType = new DeleteType();

        deleteType.setTypeName("gmd:MD_Metadata");
        deleteType.setHandle("deleteHandle");// kan van alles zijn; is voor error handling/localization handig
        BinaryLogicOpType binaryLogicOpType = new BinaryLogicOpType();
        for (String uuid : uuids){
            PropertyIsEqualTo propertyIsEqualTo = FilterCreator.createPropertyIsEqualTo(
                uuid, "apiso:identifier", null);
            binaryLogicOpType.getComparisonOpsOrSpatialOpsOrLogicOps().add(propertyIsEqualTo);
        }
        Or or = new Or(binaryLogicOpType);

        FilterType filterType = new FilterType();
        filterType.setLogicOps(or);

        QueryConstraintType queryConstraintType = new QueryConstraintType();
        queryConstraintType.setVersion("1.1.0");
        queryConstraintType.setFilter(new Filter(filterType));

        deleteType.setConstraint(new Constraint(queryConstraintType));

        return deleteType;
    }

    public TransactionResponse delete(String uuid) throws IOException, JDOMException, JAXBException, OwsException {
        return delete(createDeleteType(uuid));
    }

    public TransactionResponse delete(DeleteType deleteType) throws IOException, JDOMException, JAXBException, OwsException {
        Document responseDocument = doRequest(createTransaction(deleteType), true);

        return createTransactionResponse(responseDocument);
    }

    public HarvestResponse harvest(HarvestType harvestType) throws IOException, JDOMException, JAXBException, OwsException {
        Document responseDocument = doRequest(new Harvest(harvestType), true);

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

        //String cswValidationPath  = "c:\\dev_erik\\b3p-commons\\b3p-commons-csw\\jaxb\\xsds\\csw\\2.0.2\\CSW-discovery.xsd";

        //String testWktInput = "POLYGON((1 1,5 1,5 5,1 5,1 1),(2 2, 3 2, 3 3, 2 3,2 2))";
        String testWktInput = "POLYGON ((280 380, 280 200, 60 200, 60 380, 180 220, 280 380),(40 160, 260 160, 240 60, 20 80, 40 160))";

        try {
            GetRecords getRTest = CswSmartRequestCreator.createSmartCswRequest("test in nog een test");
            System.out.println("Test smart csw");
            System.out.println(MarshallUtil.marshall(getRTest, null));

            GetRecords getRecords = CswRequestCreator.createCswRequest(
                    new Within(), "anyText", testWktInput);
            System.out.println(MarshallUtil.marshall(getRecords, null));

            CswClient client = new CswClient(server);//, cswValidationPath);

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
