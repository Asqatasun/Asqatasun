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

package org.tanaguru.rules.rgaa22;

import java.util.ArrayList;
import java.util.Collection;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.ruleimplementation.AbstractCompositePageRuleMarkupImplementation;
import org.tanaguru.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.tanaguru.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.tanaguru.rules.elementchecker.text.TextEmptinessChecker;
import org.tanaguru.rules.elementselector.ImageElementSelector;
import static org.tanaguru.rules.keystore.AttributeStore.*;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.APPLET_WITH_ALT_CSS_LIKE_QUERY;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.IMG_WITH_ALT_WITHOUT_LONGDESC_CSS_LIKE_QUERY;
import static org.tanaguru.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.tanaguru.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.tanaguru.rules.keystore.RemarkMessageStore.*;
import org.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Implementation of the rule 4.5 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-4-5">the rule 4.5 design page.</a>
 * @see <a href="http://rgaa.net/Pertinence-de-l-alternative-vide.html"> 4.5 rule specification </a>
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule04051 extends AbstractCompositePageRuleMarkupImplementation {

    /**
     * Default constructor
     */
    public Rgaa22Rule04051() {
        super();
        Collection<AbstractPageRuleMarkupImplementation> ruleCheckers = 
                new ArrayList<AbstractPageRuleMarkupImplementation>();
        ruleCheckers.add(new DecorativeImageRuleMarkupImplementation());
        ruleCheckers.add(new DecorativeAppletRuleMarkupImplementation());
        setInnerRuleCheckers(ruleCheckers);
    }

    /**
     * Inner class that works on "marked as decorative" <img> tags
     */
    private class DecorativeImageRuleMarkupImplementation 
                    extends AbstractMarkerPageRuleImplementation {
        
        /**
         * Default constructor
         */
        public DecorativeImageRuleMarkupImplementation() {
            super(
                new ImageElementSelector(IMG_WITH_ALT_WITHOUT_LONGDESC_CSS_LIKE_QUERY, true, false),

                // the decorative images are part of the scope
                DECORATIVE_IMAGE_MARKER, 

                // the informative images are not part of the scope
                INFORMATIVE_IMAGE_MARKER, 

                // checker for elements identified by marker
                new TextEmptinessChecker( 
                    new TextAttributeOfElementBuilder(ALT_ATTR), 
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
                new TextEmptinessChecker(
                    new TextAttributeOfElementBuilder(ALT_ATTR), 
                    // solution when attribute is empty
                    TestSolution.NEED_MORE_INFO, 
                    // solution when attribute is not empty
                    TestSolution.NEED_MORE_INFO, 
                    CHECK_ELEMENT_WITH_EMPTY_ALT_MSG, 
                    CHECK_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, 
                    ALT_ATTR, 
                    SRC_ATTR)
            );
        }
    }

    /**
     * Inner class that works on marked as decorative <applet> tags
     */
    private class DecorativeAppletRuleMarkupImplementation 
                    extends AbstractMarkerPageRuleImplementation {
        
        /**
         * Default constructor
         */
        public DecorativeAppletRuleMarkupImplementation() {
            super(
                new ImageElementSelector(APPLET_WITH_ALT_CSS_LIKE_QUERY, true, false),

                // the decorative images are part of the scope
                DECORATIVE_IMAGE_MARKER, 

                // the informative images are not part of the scope
                INFORMATIVE_IMAGE_MARKER, 

                // checker for elements identified by marker
                new TextEmptinessChecker( 
                    new TextAttributeOfElementBuilder(ALT_ATTR), 
                    // solution when attribute is empty
                    TestSolution.PASSED, 
                    // solution when attribute is not empty
                    TestSolution.FAILED, 
                    // no message created when a decorative with empty alt is found
                    null, 
                    DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, 
                    ALT_ATTR, 
                    CODE_ATTR),

                // checker for elements not identified by marker
                new TextEmptinessChecker(
                    new TextAttributeOfElementBuilder(ALT_ATTR), 
                    // solution when attribute is empty
                    TestSolution.NEED_MORE_INFO, 
                    // solution when attribute is not empty
                    TestSolution.NEED_MORE_INFO, 
                    CHECK_ELEMENT_WITH_EMPTY_ALT_MSG, 
                    CHECK_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, 
                    ALT_ATTR, 
                    CODE_ATTR)
            );
        }
    }
}