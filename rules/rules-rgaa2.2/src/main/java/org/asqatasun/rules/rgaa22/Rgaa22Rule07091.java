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

package org.asqatasun.rules.rgaa22;

import org.jsoup.nodes.Element;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.asqatasun.ruleimplementation.ElementHandler;
import org.asqatasun.ruleimplementation.ElementHandlerImpl;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.ElementChecker;
import org.asqatasun.rules.elementchecker.element.ElementPresenceChecker;
import org.asqatasun.rules.elementchecker.helper.RuleCheckHelper;
import org.asqatasun.rules.elementselector.ElementSelector;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.FIELDSET_NOT_WITHIN_FORM_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.LINK_WITHOUT_TARGET_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.RemarkMessageStore.FIELDSET_NOT_WITHIN_FORM_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.LINK_WITHOUT_TARGET_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.NO_PATTERN_DETECTED_MSG;

/**
 * Implementation of the rule 7.9 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-7-9">the rule 7.9 design page.</a>
 * @see <a href="http://rgaa.net/Absence-d-elements-HTML-utilises-a.html"> 7.9 rule specification </a>
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule07091 extends AbstractPageRuleMarkupImplementation {

    /* the links without target */
    ElementHandler<Element> linkWithoutTarget = new ElementHandlerImpl();
    /* the fieldset not within form*/
    ElementHandler<Element> fieldsetNotWithinForm = new ElementHandlerImpl();
    /* the total number of elements */
    int totalNumberOfElements = 0;
            
    /**
     * Default constructor
     */
    public Rgaa22Rule07091 () {
        super();
    }
    
    @Override
    protected void select(SSPHandler sspHandler) {
        // Selection of all links without target
        ElementSelector linkWithoutTargetSelector = 
                new SimpleElementSelector(LINK_WITHOUT_TARGET_CSS_LIKE_QUERY);
        linkWithoutTargetSelector.selectElements(sspHandler, linkWithoutTarget);
        
        // Selection of all links without target
        ElementSelector fielsetNotWithinFormSelector = 
                new SimpleElementSelector(FIELDSET_NOT_WITHIN_FORM_CSS_LIKE_QUERY);
        fielsetNotWithinFormSelector.selectElements(sspHandler, fieldsetNotWithinForm);
        
        totalNumberOfElements = sspHandler.getTotalNumberOfElements();
    }

    @Override
    protected void check(
            SSPHandler sspHandler, 
            TestSolutionHandler testSolutionHandler) {

        if (linkWithoutTarget.isEmpty() && fieldsetNotWithinForm.isEmpty()) {
            sspHandler.getProcessRemarkService().addProcessRemark(
                    TestSolution.NEED_MORE_INFO, 
                    RuleCheckHelper.specifyMessageToRule(
                        NO_PATTERN_DETECTED_MSG, 
                        this.getTest().getCode())
                    );
            testSolutionHandler.addTestSolution(TestSolution.NEED_MORE_INFO);
            return;
        }
        
        ElementChecker linkWithoutTargetChecker = new ElementPresenceChecker(
                        TestSolution.FAILED,
                        TestSolution.PASSED,
                        LINK_WITHOUT_TARGET_MSG, 
                        null);
        linkWithoutTargetChecker.check(
                    sspHandler, 
                    linkWithoutTarget, 
                    testSolutionHandler);
        
        ElementChecker fieldsetNotWithinFormChecker = new ElementPresenceChecker(
                        TestSolution.FAILED,
                        TestSolution.PASSED,
                        FIELDSET_NOT_WITHIN_FORM_MSG, 
                        null);
        fieldsetNotWithinFormChecker.check(
                    sspHandler, 
                    fieldsetNotWithinForm, 
                    testSolutionHandler);
    }

    @Override
    public int getSelectionSize() {
        return totalNumberOfElements;
    }

}