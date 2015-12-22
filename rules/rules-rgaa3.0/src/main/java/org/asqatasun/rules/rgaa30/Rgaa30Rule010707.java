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
package org.asqatasun.rules.rgaa30;

import org.apache.commons.lang3.tuple.ImmutablePair;
import static org.asqatasun.entity.audit.TestSolution.NEED_MORE_INFO;
import static org.asqatasun.entity.audit.TestSolution.NOT_APPLICABLE;
import org.asqatasun.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.asqatasun.rules.elementchecker.element.ElementPresenceChecker;
import org.asqatasun.rules.elementselector.ImageElementSelector;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.CANVAS_NOT_IN_LINK_WITH_NOT_EMPTY_CONTENT_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.asqatasun.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_AT_RESTITUTION_OF_DESC_OF_INFORMATIVE_IMG_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_NATURE_OF_IMAGE_AND_AT_RESTITUTION_OF_PERTINENCE_MSG;

/**
 * Implementation of the rule 1.7.7 of the referential Rgaa 3.0.
 *
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-7-7.html">the rule 1.7.7 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-7-7"> 1.7.7 rule specification</a>
 */

public class Rgaa30Rule010707 extends AbstractMarkerPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa30Rule010707() {
        super(
                new ImageElementSelector(CANVAS_NOT_IN_LINK_WITH_NOT_EMPTY_CONTENT_CSS_LIKE_QUERY),

                // the informative images are part of the scope
                INFORMATIVE_IMAGE_MARKER, 

                // the decorative images are not part of the scope
                DECORATIVE_IMAGE_MARKER, 
                
                // checker for elements identified by marker
                new ElementPresenceChecker(
                    // solution when at least one element is found
                    new ImmutablePair(NEED_MORE_INFO,CHECK_AT_RESTITUTION_OF_DESC_OF_INFORMATIVE_IMG_MSG),
                    // solution when no element is found
                    new ImmutablePair(NOT_APPLICABLE,""),
                    TEXT_ELEMENT2), 
                
                // checker for elements not identified by marker
                new ElementPresenceChecker(
                    // solution when at least one element is found
                    new ImmutablePair(NEED_MORE_INFO,CHECK_NATURE_OF_IMAGE_AND_AT_RESTITUTION_OF_PERTINENCE_MSG),
                    // solution when no element is found
                    new ImmutablePair(NOT_APPLICABLE,""),
                    TEXT_ELEMENT2)
            );
    }

}