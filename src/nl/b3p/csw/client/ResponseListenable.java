/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.b3p.csw.client;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Erik van de Pol
 */
public interface ResponseListenable<T> {

    public T handleResponse(InputStream response) throws IOException, JAXBException, OwsException;
}
