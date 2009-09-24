package nl.b3p.csw.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.transform.JDOMSource;
//=============================================================================

public class Xml {
    public static Element loadString(String data, boolean validate)
            throws IOException, JDOMException {
        SAXBuilder builder = new SAXBuilder(validate);
        Document jdoc = builder.build(new StringReader(data));

        return (Element) jdoc.getRootElement().detach();
    }

    public static void transform(Element xml, String styleSheetPath, OutputStream out) throws Exception {
        StreamResult resStream = new StreamResult(out);
        transform(xml, styleSheetPath, resStream);
    }

    private static void transform(Element xml, String styleSheetPath, Result result) throws Exception {
        Source srcXml = new JDOMSource(new Document((Element) xml.detach()));
        Source srcSheet = new StreamSource(styleSheetPath);

        TransformerFactory transFact = TransformerFactory.newInstance();
        Transformer t = transFact.newTransformer(srcSheet);
        t.transform(srcXml, result);
    }

}

