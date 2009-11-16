/*
 * Copyright 2009 B3Partners BV
 * 
 */
package nl.b3p.csw.util;

import java.net.URI;

/**
 *
 * @author Erik van de Pol
 */
public class OnlineResource {

    private String UUID = null;
    private URI url = null;
    private Protocol protocol = null;
    private String name = null;
    private String description = null;

    public OnlineResource() {
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String uuid) {
        this.UUID = uuid;
    }

    public URI getUrl() {
        return url;
    }

    public void setUrl(URI url) {
        this.url = url;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
