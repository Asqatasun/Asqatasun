/**
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
package org.asqatasun.rules.rgaa40;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.asqatasun.ruleimplementation.AbstractNotTestedRuleImplementation;
import org.asqatasun.rules.elementselector.CaptchaElementSelector;
import org.asqatasun.rules.elementselector.ElementWithAccessibleNameSelector;
import org.asqatasun.rules.elementselector.SimpleElementSelector;

import static org.asqatasun.rules.keystore.AttributeStore.*;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.EMBED_TYPE_IMG_NOT_IN_LINK_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.SVG_NOT_IN_LINK_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.EvidenceStore.COMPUTED_LINK_TITLE;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_CAPTCHA_ALTERNATIVE_MSG;

/**
 * Implementation of rule 1.4.6 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://doc.asqatasun.org/v5/en/Business-rules/RGAA-v4/01.Images/Rule-1-4-6/">rule 1.4.6 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-4-6">1.4.6 rule specification</a>
 */
public class Rgaa40Rule010406 extends AbstractDetectionPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa40Rule010406 () {
        super(
            new ElementWithAccessibleNameSelector(new CaptchaElementSelector(new SimpleElementSelector(SVG_NOT_IN_LINK_CSS_LIKE_QUERY))),
            // solution when at least one element is found
            new ImmutablePair<>(TestSolution.NEED_MORE_INFO, CHECK_CAPTCHA_ALTERNATIVE_MSG),
            // solution when no element is found
            new ImmutablePair<>(TestSolution.NOT_APPLICABLE,""),
            // evidence elements
            ROLE_ATTR,
            ARIA_LABEL_ATTR,
            COMPUTED_LINK_TITLE
        );
    }

}
