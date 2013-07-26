/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.rules.accessiweb22;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.opens.tanaguru.rules.elementchecker.attribute.AttributeEmptinessChecker;
import org.opens.tanaguru.rules.elementselector.ImageElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.ALT_ATTR;
import static org.opens.tanaguru.rules.keystore.AttributeStore.SRC_ATTR;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.IMG_WITH_ALT_WITHOUT_LONGDESC_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.opens.tanaguru.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.*;

/**
 * Implementation of the rule 1.2.1 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw22-rule-1-2-1">the rule 1.2.1 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-2-1"> 1.2.1 rule specification</a>
 *
 */
public class Aw22Rule01021 extends AbstractMarkerPageRuleImplementation {

    public Aw22Rule01021() {
        super(
                new ImageElementSelector(IMG_WITH_ALT_WITHOUT_LONGDESC_CSS_LIKE_QUERY, true, false),
                
                // the decorative images are part of the scope
                DECORATIVE_IMAGE_MARKER, 
                
                // the informative images are not part of the scope
                INFORMATIVE_IMAGE_MARKER, 

               // checker for elements identified by marker
                new AttributeEmptinessChecker( 
                    ALT_ATTR, 
                    // solution when attribute is empty
                    TestSolution.PASSED, 
                    // solution when attribute is not empty
                    TestSolution.FAILED, 
                    // no message created when a decorative with empty alt is found
                    null, 
                    DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, 
                    ALT_ATTR, 
                    SRC_ATTR),
                
                // checker for elements not identified by marker
                new AttributeEmptinessChecker(
                    ALT_ATTR, 
                    TestSolution.NEED_MORE_INFO, 
                    TestSolution.NEED_MORE_INFO, 
                    CHECK_ELEMENT_WITH_EMPTY_ALT_MSG, 
                    CHECK_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, 
                    ALT_ATTR, 
                    SRC_ATTR)
            );
    }

}