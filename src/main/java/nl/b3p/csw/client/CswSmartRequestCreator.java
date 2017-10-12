package nl.b3p.csw.client;

import java.io.IOException;
import java.math.BigInteger;
import nl.b3p.csw.jaxb.csw.GetRecords;
import nl.b3p.csw.jaxb.filter.BinaryLogicOpType;
import nl.b3p.csw.jaxb.filter.FilterType;
import nl.b3p.csw.jaxb.filter.Or;
import nl.b3p.csw.jaxb.filter.PropertyIsEqualTo;
import nl.b3p.csw.jaxb.filter.PropertyIsLike;
import nl.b3p.csw.jaxb.filter.SortBy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.nl.DutchAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;


/**
 *
 * Creates csw request using default values for oft used stuff. Preprocesses the
 * search string in order to get better searchresults.
 *
 * @author Erik van de Pol
 * @author mprins
 */
public class CswSmartRequestCreator extends CswRequestCreator {
    private static Log log = LogFactory.getLog(CswSmartRequestCreator.class);

    public static GetRecords createSmartCswRequest(String queryString) {
        return createSmartCswRequest(queryString, null, null, null);
    }

    public static GetRecords createSmartCswRequest(String queryString, BigInteger startPosition, BigInteger maxRecords, SortBy sortBy) {
        return createSmartCswRequest(queryString, null, startPosition, maxRecords, sortBy);
    }

    public static GetRecords createSmartCswRequest(
            String queryString,
            String propertyName) {
        return createSmartCswRequest(queryString, propertyName, null, null, null);
    }

    public static GetRecords createSmartCswRequest(
            String queryString,
            String propertyName,
            BigInteger startPosition,
            BigInteger maxRecords,
            SortBy sortBy) {

        queryString = createQueryString(queryString, false);
        if (queryString == null) {
            return null;
        }
        if (queryString.trim().equals(defaultWildCard)) {
            return createCswRequest(queryString, propertyName, startPosition, maxRecords, sortBy, null, null, null);
        }

        propertyName = createPropertyName(propertyName);

        PropertyIsEqualTo propertyIsEqualTo = FilterCreator.createPropertyIsEqualTo(queryString, propertyName);

        StandardAnalyzer standardAnalyzer = new StandardAnalyzer(Version.LUCENE_46, DutchAnalyzer.getDefaultStopSet());

        BinaryLogicOpType binaryLogicOpType = new BinaryLogicOpType();
        binaryLogicOpType.getComparisonOpsOrSpatialOpsOrLogicOps().add(propertyIsEqualTo);

        try {
            try (TokenStream tokenStream = standardAnalyzer.tokenStream("", queryString)) {
                CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
                tokenStream.reset();
                while (tokenStream.incrementToken()) {
                    String tokenString = charTermAttribute.toString();
                    log.debug("term: " + tokenString);
                    PropertyIsLike propertyIsLike = FilterCreator.createPropertyIsLike(tokenString, propertyName);
                    binaryLogicOpType.getComparisonOpsOrSpatialOpsOrLogicOps().add(propertyIsLike);
                }
            }
        } catch (IOException e) {
            PropertyIsLike propertyIsLike = FilterCreator.createPropertyIsLike(queryString, propertyName);
            binaryLogicOpType.getComparisonOpsOrSpatialOpsOrLogicOps().add(propertyIsLike);
        }

        Or or = new Or(binaryLogicOpType);

        FilterType filterType = new FilterType();
        filterType.setLogicOps(or);

        return createCswRequest(filterType, startPosition, maxRecords, sortBy);
    }

}
