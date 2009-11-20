/*
 * Copyright 2009 B3Partners BV
 * 
 */
package nl.b3p.csw.util;

import java.net.URI;
import java.util.List;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import org.jdom.Element;
import org.jdom.Text;
import org.jdom.output.XMLOutputter;
import org.jdom.transform.JDOMResult;
import org.jdom.transform.JDOMSource;

/**
 *
 * @author Erik van de Pol
 */
public class OnlineResource {

    private URI url = null;
    private Protocol protocol = null;
    private String name = null;
    private String description = null;
    private Element metadata = null;
    private String metadataDescription = null;

    public OnlineResource() {
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

    public Element getMetadata() {
        return metadata;
    }

    public void setMetadata(Element metadata) {
        this.metadata = metadata;
    }

    public String getMetadataDescription() {
        return metadataDescription;
    }

    public void setMetadataDescription(String xslDocPath) throws Exception {
        if (metadata == null)
            throw new Exception("Metadata not set.");
        
        Transformer transformer =
                TransformerFactory.newInstance().newTransformer(new StreamSource(xslDocPath));

        if (transformer == null) {
            throw new Exception("Transformer could not be created. Wrong xslDocPath?");
        }

        JDOMSource in = new JDOMSource(metadata);
        JDOMResult out = new JDOMResult();
        transformer.transform(in, out);
        
        List result = out.getResult();

        if (result.size() > 0) {
            Text text = (Text)result.get(0);
            this.metadataDescription = text.getTextTrim();
        } else {
            this.metadataDescription = "";
        }
    }
    
}
