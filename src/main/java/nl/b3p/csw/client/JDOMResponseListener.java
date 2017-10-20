/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.b3p.csw.client;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.bind.JAXBException;
import nl.b3p.csw.util.ExceptionUtil;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Erik van de Pol
 */
public class JDOMResponseListener implements ResponseListenable<Document> {
    protected final static boolean VALIDATE_CSW_RESPONSE = false;

    public Document handleResponse(InputStream response) throws IOException, JAXBException, OwsException {
        SAXBuilder builder = new SAXBuilder(VALIDATE_CSW_RESPONSE);
        try {
            if (response == null)
                throw new IOException();

            Document responseDocument = builder.build(response);

            ExceptionUtil.throwExceptionIfException(responseDocument);

            return responseDocument;
        } catch (JDOMException ex) {
            throw new IOException("Could not build an xml document from the csw response.\nResponse: " + response, ex);
        } catch (IOException ex) {
            throw new IOException("Could not build an xml document from the csw response.\nResponse: " + response, ex);
        }
    }

}
