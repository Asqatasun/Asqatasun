/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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
import static org.asqatasun.rules.keystore.AttributeStore.SRC_ATTR;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.EMBED_TYPE_IMG_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_NATURE_AND_PRESENCE_OF_ALTERNATIVE_MECHANISM_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_PRESENCE_OF_ALTERNATIVE_MECHANISM_FOR_INFORMATIVE_IMG_MSG;

/**
 * Implementation of the rule 1.3.5 of the referential Rgaa 3.0.
 *
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-3-5.html">the rule 1.3.5 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-3-5"> 1.3.5 rule specification</a>
 */

public class Rgaa30Rule010305 extends AbstractMarkerPageRuleImplementation {

    /**
     * Constructor
     */
    public Rgaa30Rule010305() {
        super(
                new ImageElementSelector(EMBED_TYPE_IMG_CSS_LIKE_QUERY, true, false),

                // the informative images are part of the scope
                INFORMATIVE_IMAGE_MARKER, 

                // the decorative images are not part of the scope
                DECORATIVE_IMAGE_MARKER, 

                // checker for elements identified by marker
                new ElementPresenceChecker(
                    new ImmutablePair(NEED_MORE_INFO,CHECK_PRESENCE_OF_ALTERNATIVE_MECHANISM_FOR_INFORMATIVE_IMG_MSG),
                    new ImmutablePair(NOT_APPLICABLE,""),
                    SRC_ATTR),
                
                // checker for elements not identified by marker
                new ElementPresenceChecker(
                    new ImmutablePair(NEED_MORE_INFO,CHECK_NATURE_AND_PRESENCE_OF_ALTERNATIVE_MECHANISM_MSG),
                    new ImmutablePair(NOT_APPLICABLE,""),
                    SRC_ATTR)
            );
    }

}
