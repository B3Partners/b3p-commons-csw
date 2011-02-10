/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.b3p.csw.util;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import nl.b3p.csw.client.OwsException;
import nl.b3p.csw.jaxb.ows.ExceptionReport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.JDOMException;

/**
 *
 * @author Erik van de Pol
 */
public class ExceptionUtil {
    protected static final Log log = LogFactory.getLog(ExceptionUtil.class);

    protected static final String exceptionName = "ExceptionReport";
    
    public static void throwExceptionIfException(Document document) throws JDOMException, JAXBException, OwsException {
        if (isException(document))
            throw getException(document);
    }

    protected static boolean isException(Document document) {
        return document.getRootElement().getName().equals(exceptionName);
    }

    protected static OwsException getException(Document document) throws JDOMException, JAXBException {
        try {
            JAXBElement<ExceptionReport> jaxbElement =
                    MarshallUtil.unMarshall(document, null, ExceptionReport.class);
            if (jaxbElement==null){
                ExceptionReport er=null;
                return new OwsException(er);
            }else{
                return new OwsException(jaxbElement.getValue());
            }
        } catch(Exception e) {
            log.debug(e);
            return new OwsException(e);
        }
    }
}
