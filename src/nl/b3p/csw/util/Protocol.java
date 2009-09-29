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
}
