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

package org.tanaguru.rules.rgaa30;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.tanaguru.rules.elementchecker.text.TextEmptinessChecker;
import org.tanaguru.rules.elementselector.ImageElementSelector;
import static org.tanaguru.rules.keystore.AttributeStore.DATA_ATTR;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.OBJECT_TYPE_IMG_NOT_IN_LINK_CSS_LIKE_QUERY;
import static org.tanaguru.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.tanaguru.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.tanaguru.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_ELEMENT_WITH_EMPTY_ALT_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_ELEMENT_WITH_NOT_EMPTY_ALT_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG;
import org.tanaguru.rules.textbuilder.SimpleTextElementBuilder;

/**
 * Implementation of the rule 1.2.3 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="http://tanaguru-rules-rgaa3.readthedocs.org/en/latest/Rule-1-2-3">the rule 1.2.3 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-2-3"> 1.2.3 rule specification</a>
 *
 */
public class Rgaa30Rule010203 extends AbstractMarkerPageRuleImplementation {
    
    /**
     * Constructor
     */
    public Rgaa30Rule010203() {
        super(
                new ImageElementSelector(OBJECT_TYPE_IMG_NOT_IN_LINK_CSS_LIKE_QUERY, true, false),
                
                // the decorative images are part of the scope
                DECORATIVE_IMAGE_MARKER, 
                
                // the informative images are not part of the scope
                INFORMATIVE_IMAGE_MARKER, 

                // checker for elements identified by marker
                new TextEmptinessChecker(
                    // the text element builder
                    new SimpleTextElementBuilder(),
                    // solution when text is empty
                    new ImmutablePair(TestSolution.PASSED, ""), 
                    // solution when text is not empty
                    new ImmutablePair(TestSolution.FAILED, DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG),
                    // evidence elements
                    TEXT_ELEMENT2,
                    DATA_ATTR),
                
                // checker for elements not identified by marker
                new TextEmptinessChecker(
                    // the text element builder
                    new SimpleTextElementBuilder(),
                    // solution when text is empty
                    new ImmutablePair(TestSolution.NEED_MORE_INFO, CHECK_ELEMENT_WITH_EMPTY_ALT_MSG),
                    // solution when text is notempty
                    new ImmutablePair(TestSolution.NEED_MORE_INFO, CHECK_ELEMENT_WITH_NOT_EMPTY_ALT_MSG),
                    // evidence elements
                    TEXT_ELEMENT2,
                    DATA_ATTR)
            );
    }

}