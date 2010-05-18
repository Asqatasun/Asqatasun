package org.opens.tanaguru.contentadapter.css;

import java.util.Set;
import org.opens.tanaguru.contentadapter.ContentParser;

public interface CSSParser extends ContentParser {

	CSSOMStyleSheet getResult();

//        Set<CSSImportedStyle> getImportedStyles();

        Set<CSSImportedStyle> searchImportedStyles();
}
