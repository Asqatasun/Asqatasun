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
package org.asqatasun.rules.rgaa32016;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.asqatasun.rules.elementchecker.ElementChecker;
import org.asqatasun.rules.elementchecker.element.ElementPresenceChecker;
import org.asqatasun.rules.elementselector.MultipleElementSelector;
import static org.asqatasun.rules.keystore.AttributeStore.ARIA_LABEL_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.ROLE_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.SVG_NOT_IN_LINK_WITH_ARIA_LABEL_AND_ROLE_IMG_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.SVG_NOT_IN_LINK_WITH_DESC_CHILD_AND_ROLE_IMG_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_AT_RESTITUTION_OF_ALTERNATIVE_OF_INFORMATIVE_IMAGE_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_NATURE_OF_IMAGE_AND_AT_RESTITUTION_OF_ALTERNATIVE_MSG;

/**
 * Implementation of the rule 1.3.9 of the referential RGAA 3.2016
 *
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/rgaa3.2016/01.Images/Rule-1-3-9.html">the rule 1.3.9 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-1-3-9">1.3.9 rule specification</a>
 */

public class Rgaa32016Rule010309 extends AbstractMarkerPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa32016Rule010309 () {
        super(
                // the informative images are part of the scope
                INFORMATIVE_IMAGE_MARKER,
                // the decorative images are not part of the scope
                DECORATIVE_IMAGE_MARKER);
        
        setElementSelector(
                new MultipleElementSelector(
                    SVG_NOT_IN_LINK_WITH_DESC_CHILD_AND_ROLE_IMG_CSS_LIKE_QUERY,
                    SVG_NOT_IN_LINK_WITH_ARIA_LABEL_AND_ROLE_IMG_CSS_LIKE_QUERY));
        setMarkerElementChecker(getMarkerElementChecker());
        setRegularElementChecker(getLocalRegularElementChecker());
    }

    /**
     *
     * @return the checker user for marked elements
     */
    private ElementChecker getMarkerElementChecker () {
        ElementChecker ec = new ElementPresenceChecker(
                new ImmutablePair(TestSolution.NEED_MORE_INFO, CHECK_AT_RESTITUTION_OF_ALTERNATIVE_OF_INFORMATIVE_IMAGE_MSG),
                new ImmutablePair(TestSolution.NOT_APPLICABLE, ""),
                // evidence element
                ROLE_ATTR,
                TITLE_ATTR,
                ARIA_LABEL_ATTR
        );
        return ec;
    }
    
    /**
     *
     * @return the checker user for marked elements
     */
    private ElementChecker getLocalRegularElementChecker () {
        ElementChecker ec = new ElementPresenceChecker(
                new ImmutablePair(TestSolution.NEED_MORE_INFO, CHECK_NATURE_OF_IMAGE_AND_AT_RESTITUTION_OF_ALTERNATIVE_MSG),
                new ImmutablePair(TestSolution.NOT_APPLICABLE, ""),
                // evidence element
                ROLE_ATTR,
                TITLE_ATTR,
                ARIA_LABEL_ATTR
        );
        return ec;
    }

}