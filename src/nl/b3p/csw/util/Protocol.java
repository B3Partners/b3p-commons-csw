/*
 * Copyright 2009 B3Partners BV
 * 
 */
package nl.b3p.csw.util;

/**
 *
 * @author Erik van de Pol
 */
public enum Protocol {
    CSW("OGC:CSW"),
    WMS("OGC:WMS"),
    WFS("OGC:WFS"),
    WCS("OGC:WCS"),
    WCTS("OGC:WCTS"),
    WPS("OGC:WPS"),
    UKST("UKST"),
    WMC("OGC:WMC"),
    KML("OGC:KML"),
    GML("OGC:GML"),
    WFSG("OGC:WFS-G"),
    SOS("OGC:SOS"),
    SPS("OGC:SPS"),
    SAS("OGC:SAS"),
    WNS("OGC:WNS"),
    ODS("OGC:ODS"),
    OGS("OGC:OGS"),
    OUS("OGC:OUS"),
    OPS("OGC:OPS"),
    ORS("OGC:ORS"),    
    WEBSITE("website"),
    DOWNLOAD("download"),
    WMTS("OGC:WMTS");

    private String name;

    Protocol(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Protocol fromValue(String n) {
        if (n!=null){
            String lowerCaseInput = n.toLowerCase();
            for (Protocol p: Protocol.values()) {
                if (lowerCaseInput.startsWith(p.name.toLowerCase())) {
                    return p;
                }
            }
        }
        throw new IllegalArgumentException(n);
    }
    
}
