/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */

package org.asqatasun.rules.accessiweb22;

import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.asqatasun.rules.elementchecker.element.ElementPresenceChecker;
import org.asqatasun.rules.elementselector.ImageElementSelector;
import org.asqatasun.rules.elementselector.MultipleElementSelector;
import static org.asqatasun.rules.keystore.AttributeStore.ALT_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.SRC_ATTR;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.FORM_BUTTON_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.IMG_NOT_IN_LINK_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_DESC_PERTINENCE_OF_INFORMATIVE_IMG_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_NATURE_OF_IMAGE_AND_DESC_PERTINENCE_MSG;

/**
 * Implementation of the rule 1.7.1 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw22-rule-1-7-1">the rule 1.7.1 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-7-1"> 1.7.1 rule specification</a>
 *
 */
public class Aw22Rule01071 extends AbstractMarkerPageRuleImplementation {

    /**
     * Default constructor
     */
    public Aw22Rule01071() {
        super(
                new ImageElementSelector(
                        new MultipleElementSelector(
                                FORM_BUTTON_CSS_LIKE_QUERY,
                                IMG_NOT_IN_LINK_CSS_LIKE_QUERY    
                        )
                ),

                // the informative images are part of the scope
                INFORMATIVE_IMAGE_MARKER, 

                // the decorative images are not part of the scope
                DECORATIVE_IMAGE_MARKER, 
                
                // checker for elements identified by marker
                new ElementPresenceChecker(
                    // solution when at least one element is found
                    TestSolution.NEED_MORE_INFO,
                    // solution when no element is found
                    TestSolution.NOT_APPLICABLE,
                    // manual check message
                    CHECK_DESC_PERTINENCE_OF_INFORMATIVE_IMG_MSG,
                    null, 
                    // evidence elements
                    ALT_ATTR, 
                    SRC_ATTR), 
                
                // checker for elements not identified by marker
                new ElementPresenceChecker(
                    // solution when at least one element is found
                    TestSolution.NEED_MORE_INFO,
                    // solution when no element is found
                    TestSolution.NOT_APPLICABLE,
                    // manual check message
                    CHECK_NATURE_OF_IMAGE_AND_DESC_PERTINENCE_MSG,
                    null, 
                    // evidence elements
                    ALT_ATTR, 
                    SRC_ATTR)
            );
    }

}