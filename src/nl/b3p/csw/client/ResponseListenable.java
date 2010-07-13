/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.b3p.csw.client;

import java.io.IOException;
import java.io.InputStream;
import org.jdom.Document;
import org.jdom.JDOMException;

/**
 *
 * @author Erik van de Pol
 */
public interface ResponseListenable {

    public Document handleResponse(InputStream response) throws IOException, JDOMException;
}
