/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.b3p.csw.client;

import nl.b3p.csw.jaxb.ows.ExceptionReport;
import nl.b3p.csw.jaxb.ows.ExceptionType;

/**
 *
 * @author Erik van de Pol
 */
public class OwsException extends Exception {
    protected ExceptionReport exceptionReport;
    protected String message;

    public OwsException(Exception e) {
        message = "OGC OwsException could not be thrown. Cause:\n" + e.getMessage();
    }

    public OwsException(ExceptionReport exceptionReport) {
        this.exceptionReport = exceptionReport;
        
        if (exceptionReport == null) {
            message = "Exception report is null.";
        } else {
            StringBuilder stringBuilder = new StringBuilder();

            for (nl.b3p.csw.jaxb.ows.Exception exception : exceptionReport.getException()) {
                ExceptionType exceptionType = exception.getValue();
                //stringBuilder.append("Exception:\n");

                stringBuilder.append("\n");

                stringBuilder.append("Exception code: ");
                stringBuilder.append(exceptionType.getExceptionCode());
                stringBuilder.append("\n");

                stringBuilder.append("Locator: ");
                stringBuilder.append(exceptionType.getLocator());
                stringBuilder.append("\n");

                stringBuilder.append("Exception text:\n");

                for (String line : exceptionType.getExceptionText()) {
                    stringBuilder.append("\t");
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                }

                stringBuilder.append("\n");
            }

            //System.out.println(stringBuilder.toString());

            message = stringBuilder.toString();
        }
    }

    @Override
    public String getMessage() {
        return message;
    }

}
