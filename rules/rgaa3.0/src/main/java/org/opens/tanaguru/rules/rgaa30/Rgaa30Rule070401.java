/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */

package org.opens.tanaguru.rules.rgaa30;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.CHANGE_CONTEXT_SCRIPT_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.CONTEXT_CHANGED_SCRIPT_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.NO_PATTERN_DETECTED_MSG;
import org.opens.tanaguru.rules.elementchecker.helper.RuleCheckHelper;

/**
 * Implementation of the rule 7.5.1 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="https://github.com/Tanaguru/Tanaguru-rules-RGAA-3-doc/wiki/Rule-7-5-1">the rule 7.5.1 design page.</a>
 * @see <a href="https://references.modernisation.gouv.fr/sites/default/files/RGAA3/referentiel_technique.htm#test-7-5-1"> 7.5.1 rule specification</a>
 *
 */

public class Rgaa30Rule070401 extends AbstractDetectionPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa30Rule070401 () {
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
