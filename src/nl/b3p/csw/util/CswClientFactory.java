/*
 * Copyright 2009 B3Partners BV
 * 
 */
package nl.b3p.csw.util;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import nl.b3p.csw.client.CswClient;
import nl.b3p.csw.client.Input;
import nl.b3p.csw.client.Output;
import nl.b3p.csw.jaxb.request.GetRecords;
import nl.b3p.csw.server.CswServable;
import org.jdom.Document;
import org.jdom.JDOMException;

/**
 *
 * @author Erik van de Pol
 */
public class CswClientFactory {

    public static Document searchSimpleAsXml(String queryString, CswServable server)
            throws IOException, JDOMException, JAXBException {
        CswClient client = new CswClient(server);
        Input input = new Input(queryString);
        Output output = client.search(input);
        return output.getXml();
    }

    public static Document searchSimpleAsTransformedXml(String queryString, CswServable server, String transformPath)
            throws IOException, JDOMException, TransformerException, JAXBException {
        CswClient client = new CswClient(server);
        Input input = new Input(queryString);
        Output output = client.search(input);
        return output.getTransformedXml(transformPath);
    }

    public static Document searchAsXml(GetRecords getRecords, CswServable server)
            throws IOException, JDOMException, JAXBException {
        CswClient client = new CswClient(server);
        Input input = new Input(getRecords);
        Output output = client.search(input);
        return output.getXml();
    }

    public static Document searchAsTransformedXml(GetRecords getRecords, CswServable server, String transformPath)
            throws IOException, JDOMException, TransformerException, JAXBException {
        CswClient client = new CswClient(server);
        Input input = new Input(getRecords);
        Output output = client.search(input);
        return output.getTransformedXml(transformPath);
    }

    public static Map<URI, List<OnlineResource>> searchAsWmsResourcesMap(String queryString, CswServable server)
            throws IOException, JDOMException, JAXBException {
        CswClient client = new CswClient(server);
        Input input = new Input(queryString);
        Output output = client.search(input);
        return output.getResourcesMap();
    }
}
