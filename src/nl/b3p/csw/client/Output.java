/*
 * Copyright 2009 B3Partners BV
 * 
 */

package nl.b3p.csw.client;

import java.util.HashMap;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import org.jdom.Document;
import org.jdom.transform.JDOMResult;
import org.jdom.transform.JDOMSource;

/**
 *
 * @author Erik van de Pol
 */
public class Output {
  protected Document xmlDocument;

  public Output(Document xmlDocument) {
    this.xmlDocument = xmlDocument;
  }

  public HashMap getResultMap() {
    throw new UnsupportedOperationException();
  }

  public Document getResultAsXml() {
    return xmlDocument;
  }

  public Document getResultAsTransformedXml(String transformPath) throws TransformerException {
    try {
      Transformer transformer =
              TransformerFactory.newInstance().newTransformer(new StreamSource(transformPath));
      JDOMSource in = new JDOMSource(xmlDocument);
      JDOMResult out = new JDOMResult();
      transformer.transform(in, out);
      return out.getDocument();
    } catch (TransformerException e) {
      throw new TransformerException(
              "Xml Csw response could not be transformed with " + transformPath +
              ".\nXml: " + xmlDocument.toString(), e);
    }
  }


}
