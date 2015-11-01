/*
 * Tanaguru - Automated webpage assessment
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

package org.tanaguru.rules.rgaa22;

import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.ElementHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.ruleimplementation.link.AbstractAllLinkAggregateRuleImplementation;
import org.tanaguru.rules.elementchecker.link.IdenticalLinkWithDifferentTargetChecker;
import org.tanaguru.rules.elementselector.AreaLinkElementSelector;
import org.tanaguru.rules.elementselector.CompositeLinkElementSelector;
import org.tanaguru.rules.elementselector.LinkElementSelector;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_BUTTON_WITH_SAME_TEXT_LEAD_TO_SAME_ACTION_MSG;

/**
 * Implementation of the rule 6.15 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-6-15">the rule 6.15 design page.</a>
 * @see <a href="http://rgaa.net/Coherence-de-la-destination-ou-de.html"> 6.15 rule specification </a>
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule06151 extends AbstractAllLinkAggregateRuleImplementation {
    
    /**
     * Constructor
     */
    public Rgaa22Rule06151 () {
        super(new LinkElementSelector(false, true), 
              new CompositeLinkElementSelector(false, true, true),
              new AreaLinkElementSelector(false, true),
              new CompositeLinkElementSelector(false, true, false),
              new IdenticalLinkWithDifferentTargetChecker(false),
              new IdenticalLinkWithDifferentTargetChecker(true)
        );
    }

    @Override
    protected void checkButtonSelection(
            SSPHandler sspHandler, 
            ElementHandler formButtonHandler, 
            TestSolutionHandler testSolutionHandler) {        
        // after all checks, if the test is passed, we check whether some form 
        // buttons are present on the page and set the result as NMI
        if (testSolutionHandler.getTestSolution().equals(TestSolution.PASSED)
                && !formButtonHandler.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NEED_MORE_INFO);
            sspHandler.getProcessRemarkService().addProcessRemark(
                    TestSolution.NEED_MORE_INFO, 
                    CHECK_BUTTON_WITH_SAME_TEXT_LEAD_TO_SAME_ACTION_MSG);
        }
    }
    
}