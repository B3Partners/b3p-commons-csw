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
    WMS("OGC:WMS-1.1.1-http-get-map"),
    WFS("OGC:WFS");

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
