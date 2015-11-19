package nl.b3p.csw.util;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

/**
 *
 * @author Chris
 */
public class CswNamespaceMapper extends NamespacePrefixMapper {

    private static final String CSW_PREFIX = "csw";
    private static final String CSW_URI = "http://www.opengis.net/cat/csw/2.0.2";

    @Override
    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
        if(CSW_URI.equals(namespaceUri)) {
            return CSW_PREFIX;
        }
        return suggestion;
    }

    @Override
    public String[] getPreDeclaredNamespaceUris() {
        return new String[] { CSW_URI };
    }

}