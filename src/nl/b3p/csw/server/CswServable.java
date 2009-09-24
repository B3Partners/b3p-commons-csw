/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.b3p.csw.server;

import java.io.IOException;

/**
 *
 * @author Erik van de Pol
 */
public interface CswServable {

  public boolean login(String loginUrl, String username, String password) throws Exception;
    
}
