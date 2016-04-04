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

package org.asqatasun.rules.rgaa22;

import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.asqatasun.rules.elementchecker.element.ElementPresenceChecker;
import org.asqatasun.rules.elementselector.ImageElementSelector;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.AttributeStore.ALT_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.SRC_ATTR;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.IMG_NOT_IN_LINK_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_DESC_PERTINENCE_OF_INFORMATIVE_IMG_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_NATURE_OF_IMAGE_AND_DESC_PERTINENCE_MSG;

/**
 * Implementation of the rule 4.8 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.old-dot-org.org/en/content/rgaa22-rule-4-8">the rule 4.8 design page.</a>
 * @see <a href="http://rgaa.net/Pertinence-de-la-description.html"> 4.8 rule specification </a>
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule04081 extends AbstractMarkerPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa22Rule04081() {
        super(
                new ImageElementSelector(
                    new SimpleElementSelector(IMG_NOT_IN_LINK_CSS_LIKE_QUERY)
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