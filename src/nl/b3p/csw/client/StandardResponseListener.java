/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.b3p.csw.client;

import java.io.IOException;
import java.io.InputStream;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author Erik van de Pol
 */
public class StandardResponseListener implements ResponseListenable {
    protected final static boolean VALIDATE_CSW_RESPONSE = false;

    public Document handleResponse(InputStream response) throws IOException, JDOMException {
        SAXBuilder builder = new SAXBuilder(VALIDATE_CSW_RESPONSE);
        try {
            if (response == null)
                throw new IOException();

            return builder.build(response);
        } catch (JDOMException ex) {
            throw new JDOMException("Could not build an xml document from the csw response.\nResponse: " + response, ex);
        } catch (IOException ex) {
            throw new IOException("Could not build an xml document from the csw response.\nResponse: " + response, ex);
        }
    }

}
