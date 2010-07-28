/*
 * Copyright 2009 B3Partners BV
 * 
 */
package nl.b3p.csw.client;

import java.util.Iterator;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.NamespaceContext;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import nl.b3p.csw.util.MarshallUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.filter.ElementFilter;
import org.jdom.transform.JDOMResult;
import org.jdom.transform.JDOMSource;

/**
 *
 * @author Erik van de Pol
 *
 */
public abstract class Output {

    protected static final Log log = LogFactory.getLog(Output.class);
    protected static final Namespace cswNameSpace = Namespace.getNamespace("http://www.opengis.net/cat/csw/2.0.2");
    protected static final Namespace gmdNameSpace = Namespace.getNamespace("http://www.isotc211.org/2005/gmd");
    protected static final Namespace gcoNameSpace = Namespace.getNamespace("http://www.isotc211.org/2005/gco");
    protected static final Namespace cswPrefixNameSpace = Namespace.getNamespace("csw","http://www.opengis.net/cat/csw/2.0.2");
    protected static final Namespace gmdPrefixNameSpace = Namespace.getNamespace("gmd","http://www.isotc211.org/2005/gmd");
    protected static final Namespace gcoPrefixNameSpace = Namespace.getNamespace("gco","http://www.isotc211.org/2005/gco");
    // TODO: deze staat hard op ISO 19139. Andere standaarden toevoegen?
    protected static final ElementFilter resultElementFilter = new ElementFilter("MD_Metadata", gmdNameSpace);
    protected static final ElementFilter resourceElementFilter = new ElementFilter("CI_OnlineResource", gmdNameSpace);
    protected static final ElementFilter fileIdentifierElementFilter = new ElementFilter("fileIdentifier", gmdNameSpace);
    //for jdom
    protected static org.jdom.xpath.XPath titleJdomXPath;
    protected static org.jdom.xpath.XPath keywordsJdomXPath;
    protected static org.jdom.xpath.XPath identificationDateJdomXPath;
    protected static org.jdom.xpath.XPath responsibleOrganisationNameJdomXPath;
    static {
        try {
            titleJdomXPath = org.jdom.xpath.XPath.newInstance("gmd:identificationInfo/gmd:MD_DataIdentification/gmd:citation/gmd:CI_Citation/gmd:title/gco:CharacterString/text()");
            titleJdomXPath.addNamespace(gmdPrefixNameSpace);
            titleJdomXPath.addNamespace(gcoPrefixNameSpace);
            keywordsJdomXPath = org.jdom.xpath.XPath.newInstance("gmd:identificationInfo/gmd:MD_DataIdentification/gmd:descriptiveKeywords/gmd:MD_Keywords/gmd:keyword/gco:CharacterString/text()");
            keywordsJdomXPath.addNamespace(gmdPrefixNameSpace);
            keywordsJdomXPath.addNamespace(gcoPrefixNameSpace);
            identificationDateJdomXPath = org.jdom.xpath.XPath.newInstance("gmd:identificationInfo/gmd:MD_DataIdentification/gmd:citation/gmd:CI_Citation/gmd:date/gmd:CI_Date/gmd:date/gco:Date/text()");
            identificationDateJdomXPath.addNamespace(gmdPrefixNameSpace);
            identificationDateJdomXPath.addNamespace(gcoPrefixNameSpace);
            responsibleOrganisationNameJdomXPath = org.jdom.xpath.XPath.newInstance("gmd:contact/gmd:CI_ResponsibleParty/gmd:organisationName/gco:CharacterString/text()");
            responsibleOrganisationNameJdomXPath.addNamespace(gmdPrefixNameSpace);
            responsibleOrganisationNameJdomXPath.addNamespace(gcoPrefixNameSpace);
        } catch (JDOMException ex) {
            log.error("Error creating xpath expressions");
        }
    }
    protected Document xmlDocument = null;
    protected JAXBElement response = null;
    protected Schema schema = null;

    public Output(Document xmlDocument) {
        this.xmlDocument = xmlDocument;
    }

    public Output(Document xmlDocument, Schema schema) {
        this(xmlDocument);
        this.schema = schema;
    }

    public Document getXml() {
        return xmlDocument;
    }

    public Document getTransformedXml(String transformPath) throws TransformerException {
        try {
            Transformer transformer =
                    TransformerFactory.newInstance().newTransformer(new StreamSource(transformPath));
            JDOMSource in = new JDOMSource(xmlDocument);
            JDOMResult out = new JDOMResult();
            transformer.transform(in, out);
            return out.getDocument();
        } catch (TransformerException e) {
            throw new TransformerException(
                    "Xml Csw response could not be transformed with " + transformPath
                    + ".\nXml: " + xmlDocument.toString(), e);
        }
    }

    protected JAXBElement getResponse() throws JDOMException, JAXBException {
        if (response == null) {
            response = MarshallUtil.unMarshall(xmlDocument, schema, getTargetType());
        }
        return response;
    }

    protected abstract Class getTargetType();
}
