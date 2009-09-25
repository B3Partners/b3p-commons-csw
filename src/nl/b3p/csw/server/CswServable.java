/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.b3p.csw.server;

/**
 *
 * @author Erik van de Pol
 */
public interface CswServable {

  /**
   * Posts cswRequestXml to the server.
   * Classes implementing CswServable should supply their own login logic.
   * @param cswRequestXml
   * @return The response from the server as a String. The String should contain xml.
   */
  public String search(String cswRequestXml);
  
}
