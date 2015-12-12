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
import static org.asqatasun.rules.keystore.AttributeStore.SRC_ATTR;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.EMBED_TYPE_IMG_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_NATURE_OF_IMAGE_AND_TEXT_STYLED_PRESENCE_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_TEXT_STYLED_PRESENCE_OF_INFORMATIVE_IMG_MSG;

/**
 * Implementation of the rule 1.8.6 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-8-6.html">the rule 1.8.6 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-8-6"> 1.8.6 rule specification</a>
 *
 */
public class Rgaa30Rule010805 extends AbstractMarkerPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa30Rule010805 () {
        super(
                new ImageElementSelector(EMBED_TYPE_IMG_CSS_LIKE_QUERY),
                INFORMATIVE_IMAGE_MARKER,
                DECORATIVE_IMAGE_MARKER,
                new ElementPresenceChecker(
                        new ImmutablePair(NEED_MORE_INFO, CHECK_TEXT_STYLED_PRESENCE_OF_INFORMATIVE_IMG_MSG),
                        new ImmutablePair(NOT_APPLICABLE, ""),
                        // evidence elements
                        SRC_ATTR
                ),
                new ElementPresenceChecker(
                        new ImmutablePair(NEED_MORE_INFO, CHECK_NATURE_OF_IMAGE_AND_TEXT_STYLED_PRESENCE_MSG),
                        new ImmutablePair(NOT_APPLICABLE, ""),
                        // evidence elements
                        SRC_ATTR
                )
            );
    }

}
