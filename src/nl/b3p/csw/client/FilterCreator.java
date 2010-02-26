/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.b3p.csw.client;

import nl.b3p.csw.jaxb.filter.BinaryComparisonOpType;
import nl.b3p.csw.jaxb.filter.Literal;
import nl.b3p.csw.jaxb.filter.LiteralType;
import nl.b3p.csw.jaxb.filter.PropertyIsEqualTo;
import nl.b3p.csw.jaxb.filter.PropertyIsLike;
import nl.b3p.csw.jaxb.filter.PropertyIsLikeType;
import nl.b3p.csw.jaxb.filter.PropertyName;
import nl.b3p.csw.jaxb.filter.PropertyNameType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Erik van de Pol
 *
 * Creates (parts of) filters to create a csw request using default values for oft used stuff.
 */
public class FilterCreator extends CswRequestCreator {
    private static Log log = LogFactory.getLog(FilterCreator.class);

    public static PropertyIsLike createPropertyIsLike(String queryString, String propertyName) {
        LiteralType literalType = new LiteralType();
        literalType.getContent().add(queryString);

        PropertyNameType propertyNameType = new PropertyNameType();
        propertyNameType.getContent().add(propertyName);

        PropertyIsLikeType propertyIsLikeType = new PropertyIsLikeType();
        propertyIsLikeType.setLiteral(new Literal(literalType));
        propertyIsLikeType.setPropertyName(new PropertyName(propertyNameType));
        propertyIsLikeType.setWildCard(defaultWildCard);
        propertyIsLikeType.setSingleChar(defaultSingleChar);
        propertyIsLikeType.setEscapeChar(defaultEscapeChar);

        return new PropertyIsLike(propertyIsLikeType);
    }

    public static PropertyIsEqualTo createPropertyIsEqualTo(String propertyName, String queryString) {
        PropertyNameType propertyNameType = new PropertyNameType();
        propertyNameType.getContent().add(propertyName);

        LiteralType literalType = new LiteralType();
        literalType.getContent().add(queryString);

        BinaryComparisonOpType binaryComparisonOpType = new BinaryComparisonOpType();
        binaryComparisonOpType.getExpression().add(new PropertyName(propertyNameType));
        binaryComparisonOpType.getExpression().add(new Literal(literalType));
        binaryComparisonOpType.setMatchCase(Boolean.FALSE);
        
        return new PropertyIsEqualTo(binaryComparisonOpType);
    }


}
