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
import org.asqatasun.entity.reference.Test;
import org.asqatasun.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.CHANGE_CONTEXT_SCRIPT_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CONTEXT_CHANGED_SCRIPT_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.NO_PATTERN_DETECTED_MSG;
import org.asqatasun.rules.elementchecker.helper.RuleCheckHelper;

/**
 * Implementation of rule 7.4.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://doc.asqatasun.org/v5/en/Business-rules/RGAA-v4/07.Scripts/Rule-7-4-1/">rule 7.4.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-7-4-1">7.4.1 rule specification</a>
 */
public class Rgaa40Rule070401 extends AbstractDetectionPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa40Rule070401() {
        super(
                new SimpleElementSelector(CHANGE_CONTEXT_SCRIPT_CSS_LIKE_QUERY),
                // solution when at least one element is found
                TestSolution.NEED_MORE_INFO,
                // solution when no element is found
                TestSolution.NEED_MORE_INFO,
                // detected message
                CONTEXT_CHANGED_SCRIPT_MSG,
                // not detected message (set 
                null
            );
    }

    @Override
    public void setTest(Test test) {
        super.setTest(test);
        // set the not detected message after instanciation to make it
        // rule-specific
        getElementPresenceChecker().setMessageCodeOnElementNotDetected(
                RuleCheckHelper.specifyMessageToRule(
                    NO_PATTERN_DETECTED_MSG, 
                    this.getTest().getCode()));
    }

}
