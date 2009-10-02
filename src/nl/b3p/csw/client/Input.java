/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.b3p.csw.client;

import nl.b3p.csw.jaxb.request.GetRecords;


/**
 *
 * @author Erik van de Pol
 */
public class Input {

    protected GetRecords getRecords = null;

    public Input(String queryString) {
        this.getRecords = CswRequestCreator.createSimpleCswRequest(queryString);
    }

    public Input(GetRecords getRecords) {
        this.getRecords = getRecords;
    }
    // TODO: Wkt input

    /**
     * @return the getRecords
     */
    public GetRecords getGetRecords() {
        return getRecords;
    }
}
