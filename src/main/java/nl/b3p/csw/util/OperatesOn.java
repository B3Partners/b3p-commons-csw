/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.b3p.csw.util;

import org.jdom.Element;
import org.jdom.Namespace;

/**
 *  <srv:operatesOn xmlns:xlink="http://www.w3.org/1999/xlink" uuidref="" xlink:href="" />
 * @author Roy Braam
 */
public class OperatesOn {
    private String uuidref;
    private String href;

    public OperatesOn(Element el) {
        this.uuidref=el.getAttributeValue("uuidref");
        this.href=el.getAttributeValue("href", Namespace.getNamespace("xlink", "http://www.w3.org/1999/xlink"));
    }

    //<editor-fold defaultstate="collapsed" desc="getters setters">
    public String getUuidref() {
        return uuidref;
    }
    
    public void setUuidref(String uuidref) {
        this.uuidref = uuidref;
    }
    
    public String getHref() {
        return href;
    }
    
    public void setHref(String href) {
        this.href = href;
    }
    //</editor-fold>
}
