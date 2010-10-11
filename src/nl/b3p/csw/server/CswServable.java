/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.b3p.csw.server;

import java.io.IOException;
import javax.xml.bind.JAXBException;
import nl.b3p.csw.client.OwsException;
import org.jdom.JDOMException;

/**
 *
 * @author Erik van de Pol
 */
public interface CswServable<T> {

    /**
     * Posts cswRequestXml to the server.
     * Classes implementing CswServable should supply their own login logic.
     * @param cswRequestXml
     * @return The response from the server as a String. The String should contain xml.
     */
    public T doRequest(String cswRequestXml) throws IOException, JDOMException, JAXBException, OwsException;
}
