/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.b3p.csw.client;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import nl.b3p.csw.jaxb.request.GetRecords;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.output.DOMOutputter;
import org.xml.sax.SAXException;


/**
 *
 * @author Erik van de Pol
 */
public class Input {
    protected static final Log log = LogFactory.getLog(Input.class);

    protected static final String cswRequestXsdPath = "c:\\dev_erik\\b3p-commons-csw\\jaxb\\xsds\\csw-request.xsd";
    protected static Schema cswRequestSchema = null;
    protected static final boolean defaultValidate = true;

    protected GetRecords getRecords = null;

    public Input(String queryString) {
        this.getRecords = CswRequestCreator.createSimpleCswRequest(queryString);
    }

    public Input(GetRecords getRecords) {
        this.getRecords = getRecords;
    }

    public Input(Document document) throws JAXBException, JDOMException {
        this(document, defaultValidate);
    }

    public Input(Document document, boolean validate) throws JAXBException, JDOMException {
        Schema schema = validate ? getRequestSchema() : null;
        
        JAXBContext jaxbContext = JAXBContext.newInstance("nl.b3p.csw.jaxb.request");
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setSchema(schema);

        // transform to w3c dom to be able to use jaxb to unmarshal.
        DOMOutputter domOutputter = new DOMOutputter();
        org.w3c.dom.Document w3cDomDoc = domOutputter.output(document);

        this.getRecords = (GetRecords)unmarshaller.unmarshal(w3cDomDoc);
    }

    protected Schema getRequestSchema() {
        if (cswRequestSchema == null) {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            try {
                cswRequestSchema = sf.newSchema(new File(cswRequestXsdPath));
            } catch (SAXException saxe) {
                log.error("No validation possible. File '" + cswRequestXsdPath + "'.", saxe);
                cswRequestSchema = null;
            }
        }
        return cswRequestSchema;
    }


    // TODO: Wkt input

    /**
     * @return the getRecords
     */
    public GetRecords getGetRecords() {
        return getRecords;
    }
}
