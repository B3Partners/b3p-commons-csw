/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.b3p.csw.client;

import java.io.IOException;
import java.io.StringReader;
import nl.b3p.csw.jaxb.csw.GetRecords;
import nl.b3p.csw.jaxb.filter.BinaryLogicOpType;
import nl.b3p.csw.jaxb.filter.FilterType;
import nl.b3p.csw.jaxb.filter.Or;
import nl.b3p.csw.jaxb.filter.PropertyIsEqualTo;
import nl.b3p.csw.jaxb.filter.PropertyIsLike;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.nl.DutchAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

/**
 *
 * @author Erik van de Pol
 *
 * Creates csw request using default values for oft used stuff.
 * Preprocesses the search string in order to get better searchresults.
 */
public class CswSmartRequestCreator extends CswRequestCreator {
    private static Log log = LogFactory.getLog(CswSmartRequestCreator.class);

    public static GetRecords createSmartCswRequest(
            String queryString,
            String propertyName) {

        queryString = createQueryString(queryString, false);
        if (queryString == null) return null;

        propertyName = createPropertyName(propertyName);

        PropertyIsEqualTo propertyIsEqualTo = FilterCreator.createPropertyIsEqualTo(queryString, propertyName);

        StandardAnalyzer standardAnalyzer = new StandardAnalyzer(DutchAnalyzer.DUTCH_STOP_WORDS);
        TokenStream tokenStream = standardAnalyzer.tokenStream("", new StringReader(queryString));

        BinaryLogicOpType binaryLogicOpType = new BinaryLogicOpType();
        binaryLogicOpType.getComparisonOpsOrSpatialOpsOrLogicOps().add(propertyIsEqualTo);

        try {
            Token token = null;
            while ((token = tokenStream.next()) != null) {
                String tokenString = new String(token.termBuffer()).trim();
                log.debug("term: " + tokenString);
                PropertyIsLike propertyIsLike = FilterCreator.createPropertyIsLike(tokenString, propertyName);
                binaryLogicOpType.getComparisonOpsOrSpatialOpsOrLogicOps().add(propertyIsLike);
            }
        } catch (IOException e) {
            PropertyIsLike propertyIsLike = FilterCreator.createPropertyIsLike(queryString, propertyName);
            binaryLogicOpType.getComparisonOpsOrSpatialOpsOrLogicOps().add(propertyIsLike);
        }

        Or or = new Or(binaryLogicOpType);

        FilterType filterType = new FilterType();
        filterType.setLogicOps(or);

        return createCswRequest(filterType);
    }

}
