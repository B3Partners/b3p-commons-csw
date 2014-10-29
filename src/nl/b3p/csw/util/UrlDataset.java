package nl.b3p.csw.util;

import org.jdom.Element;
import org.jdom.Namespace;

/**
 * <gmd:linkage>
 * 	<gmd:URL>http://agentschapnl.kaartenbalie.nl/cgi-bin/mapserv?map=/srv/maps/agentschapnl/RVO_WarmteAtlas.map&amp;version=1.0.0</gmd:URL>
 * </gmd:linkage>
 * <gmd:protocol>
 * 	<gco:CharacterString xmlns:gco="http://www.isotc211.org/2005/gco">OGC:WFS</gco:CharacterString>
 * </gmd:protocol>
 * <gmd:name>
 * 	<gco:CharacterString xmlns:gco="http://www.isotc211.org/2005/gco">BG_GFT_OPP</gco:CharacterString>
 * </gmd:name>
 * 
 * @author Chris van Lith
 */
public class UrlDataset {
    private String href;
    private String protocol;
    private String name;
    
    public UrlDataset(Element el) {
        Element linkage = el.getChild("linkage", Namespace.getNamespace("gmd", "http://www.isotc211.org/2005/gmd"));
        if (linkage != null) {
            this.href = linkage.getChildText("URL", Namespace.getNamespace("gmd", "http://www.isotc211.org/2005/gmd"));
        }
        Element lprotocol = el.getChild("protocol", Namespace.getNamespace("gmd", "http://www.isotc211.org/2005/gmd"));
        if (lprotocol != null) {
            this.protocol = lprotocol.getChildText("CharacterString", Namespace.getNamespace("gco", "http://www.isotc211.org/2005/gco"));
        }
        Element lname = el.getChild("name", Namespace.getNamespace("gmd", "http://www.isotc211.org/2005/gmd"));
        if (lname != null) {
            this.name = lname.getChildText("CharacterString", Namespace.getNamespace("gco", "http://www.isotc211.org/2005/gco"));
        }
    }

    /**
     * @return the href
     */
    public String getHref() {
        return href;
    }

    /**
     * @param href the href to set
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * @return the protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * @param protocol the protocol to set
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


}
