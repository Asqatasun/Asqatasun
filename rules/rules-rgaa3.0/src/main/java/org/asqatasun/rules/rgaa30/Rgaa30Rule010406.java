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
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.asqatasun.rules.elementselector.CaptchaElementSelector;
import org.asqatasun.rules.elementselector.MultipleElementSelector;
import static org.asqatasun.rules.keystore.AttributeStore.ARIA_LABEL_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.SVG_NOT_IN_LINK_WITH_ARIA_LABEL_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.SVG_NOT_IN_LINK_WITH_DESC_CHILD_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_CAPTCHA_ALTERNATIVE_MSG;

/**
 * Implementation of the rule 1.4.6 of the referential Rgaa 3.0.
 *
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-4-6.html">the rule 1.4.6 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-4-6"> 1.4.6 rule specification</a>
 */

public class Rgaa30Rule010406 extends AbstractDetectionPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa30Rule010406 () {
        super(
                new CaptchaElementSelector(
                    new MultipleElementSelector(
                            SVG_NOT_IN_LINK_WITH_ARIA_LABEL_CSS_LIKE_QUERY,
                            SVG_NOT_IN_LINK_WITH_DESC_CHILD_CSS_LIKE_QUERY)),
                // solution when at least one element is found
                new ImmutablePair(TestSolution.NEED_MORE_INFO,CHECK_CAPTCHA_ALTERNATIVE_MSG),
                // solution when no element is found
                new ImmutablePair(TestSolution.NOT_APPLICABLE,""),
                // evidence elements
                TITLE_ATTR,
                ARIA_LABEL_ATTR
            );
    }

}