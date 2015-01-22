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
 * Contact us by mail: open-s AT open-s DOT com
 */

package org.opens.tanaguru.rules.rgaa30;

import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementchecker.element.ElementPresenceChecker;
import org.opens.tanaguru.rules.elementselector.ElementSelector;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.FIELDSET_NOT_WITHIN_FORM_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.LINK_WITHOUT_TARGET_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.FIELDSET_NOT_WITHIN_FORM_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.LINK_WITHOUT_TARGET_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.NO_PATTERN_DETECTED_MSG;
import org.opens.tanaguru.rules.elementchecker.helper.RuleCheckHelper;

/**
 * Implementation of the rule 8.9.1 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="https://github.com/Tanaguru/Tanaguru-rules-RGAA-3-doc/wiki/Rule-8-9-1">the rule 8.9.1 design page.</a>
 * @see <a href="https://references.modernisation.gouv.fr/sites/default/files/RGAA3/referentiel_technique.htm#test-8-9-1"> 8.9.1 rule specification</a>
 *
 * @author jkowalczyk
 */

public class Rgaa30Rule080901 extends AbstractPageRuleMarkupImplementation {

    /* the links without target */
    ElementHandler<Element> linkWithoutTarget = new ElementHandlerImpl();
    /* the fieldset not within form*/
    ElementHandler<Element> fieldsetNotWithinForm = new ElementHandlerImpl();
    /* the total number of elements */
    int totalNumberOfElements = 0;
            
    /**
     * Default constructor
     */
    public Rgaa30Rule080901 () {
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
