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
    WMS("OGC:WMS-1.1.1-http-get-map");

    private String name;

    Protocol(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Protocol fromValue(String n) {
        String lowerCaseInput = n.toLowerCase();
        for (Protocol p: Protocol.values()) {
            if (p.name.toLowerCase().equals(lowerCaseInput)) {
                return p;
            }
        }
        throw new IllegalArgumentException(n);
    }
    
}
