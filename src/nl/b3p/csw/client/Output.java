/*
 * Copyright 2009 B3Partners BV
 * 
 */
package nl.b3p.csw.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import nl.b3p.csw.jaxb.csw.GetRecordsResponseType;
import nl.b3p.csw.util.MarshallUtil;
import nl.b3p.csw.util.OnlineResource;
import nl.b3p.csw.util.Protocol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.filter.ElementFilter;
import org.jdom.input.DOMBuilder;
import org.jdom.transform.JDOMResult;
import org.jdom.transform.JDOMSource;

/**
 *
 * @author Erik van de Pol
 *
 */
public abstract class Output {
    
    protected static final Log log = LogFactory.getLog(Output.class);

    protected static final Namespace gmdNameSpace = Namespace.getNamespace("http://www.isotc211.org/2005/gmd");
    protected static final Namespace gcoNameSpace = Namespace.getNamespace("http://www.isotc211.org/2005/gco");

    // TODO: deze staat hard op ISO 19139. Andere standaarden toevoegen?
    protected static final ElementFilter resultElementFilter = new ElementFilter("MD_Metadata", gmdNameSpace);
    protected static final ElementFilter resourceElementFilter = new ElementFilter("CI_OnlineResource", gmdNameSpace);

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
                    "Xml Csw response could not be transformed with " + transformPath +
                    ".\nXml: " + xmlDocument.toString(), e);
        }
    }

    protected JAXBElement getResponse() throws JDOMException, JAXBException {
        if (response == null) {
            response = MarshallUtil.unMarshall(xmlDocument, schema);
        }
        return response;
    }

}
