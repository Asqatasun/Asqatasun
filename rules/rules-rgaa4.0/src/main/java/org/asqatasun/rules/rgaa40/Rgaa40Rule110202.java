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

import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.asqatasun.rules.elementselector.SimpleElementSelector;

import static org.asqatasun.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.FORM_ELEMENT_WITH_TITLE_CSS_LIKE_QUERY_RGAA4;
import static org.asqatasun.rules.keystore.RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG;

/**
 * Implementation of rule 11.2.2 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://doc.asqatasun.org/v5/en/Business-rules/RGAA-v4/11.Forms/Rule-11-2-2/">rule 11.2.2 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-11-2-2">11.2.2 rule specification</a>
 */
public class Rgaa40Rule110202 extends AbstractDetectionPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa40Rule110202() {
        super(
            new SimpleElementSelector(FORM_ELEMENT_WITH_TITLE_CSS_LIKE_QUERY_RGAA4),
            // solution when at least one element is found
            TestSolution.NEED_MORE_INFO,
            // solution when no element is found
            TestSolution.NOT_APPLICABLE,
            // manual check message
            MANUAL_CHECK_ON_ELEMENTS_MSG,
            null,
            // evidence elements
            TITLE_ATTR
        );
    }

}
