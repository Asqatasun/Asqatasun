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
package org.asqatasun.rules.accessiweb22;

import org.jsoup.nodes.Element;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.asqatasun.ruleimplementation.ElementHandler;
import org.asqatasun.ruleimplementation.ElementHandlerImpl;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.ElementChecker;
import org.asqatasun.rules.elementchecker.attribute.AttributePresenceChecker;
import org.asqatasun.rules.elementselector.ElementSelector;
import org.asqatasun.rules.elementselector.InputFormElementWithExplicitLabelSelector;
import org.asqatasun.rules.elementselector.InputFormElementWithInplicitLabelSelector;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.FORM_ELEMENT_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.RemarkMessageStore.INVALID_FORM_FIELD_MSG;

/**
 * Implementation of the rule 11.1.1 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/40_Rules/accessiweb2.2/11.Forms/Rule-11.1.1.html">the rule 11.1.1 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-11-1-1"> 11.1.1 rule specification</a>
 *
 */
public class Aw22Rule11011 extends AbstractPageRuleMarkupImplementation {

    /** the input form elements with inplicit label */
    private final ElementHandler<Element> inputFormWithoutLabelHandler = 
            new ElementHandlerImpl();
    
    /** the input form elements  */
    private final ElementHandler<Element> inputFormHandler = 
            new ElementHandlerImpl();
        
    /**
     * Default constructor
     */
    public Aw22Rule11011() {
        super();
    }

    @Override
    protected void select(SSPHandler sspHandler) {
        // Selection of all the input form elements of the page
        ElementSelector elementSelector = new SimpleElementSelector(FORM_ELEMENT_CSS_LIKE_QUERY);
        elementSelector.selectElements(sspHandler, inputFormHandler);

        // the selection of the input form elements without label is initialised
        // with all the elements of the page, some elements will be removed later
        inputFormWithoutLabelHandler.addAll(inputFormHandler.get());
        
        // selection of the input form elements with explicit label
        ElementHandler<Element> inputFormLabelHandler = new ElementHandlerImpl();
        ElementSelector explicitLabelSelector = 
                new InputFormElementWithExplicitLabelSelector(inputFormHandler);
        explicitLabelSelector.selectElements(sspHandler, inputFormLabelHandler);

        // remove all the input form elements with explicit label from 
        // the selection of the input form elements without label
        inputFormWithoutLabelHandler.removeAll(inputFormLabelHandler.get());
        
        // selection of the input form with inplicit label
        ElementSelector inplicitLabelSelector = 
                new InputFormElementWithInplicitLabelSelector(inputFormHandler);
        inplicitLabelSelector.selectElements(sspHandler, inputFormLabelHandler);
        
        // remove all the input form elements with inplicit label from 
        // the selection of the input form elements without label
        inputFormWithoutLabelHandler.removeAll(inputFormLabelHandler.get());
    }

    @Override
    protected void check(
            SSPHandler sspHandler, 
            TestSolutionHandler testSolutionHandler) {

        // If the page has no input form element, the test is not applicable
        if (inputFormHandler.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        // If all the input form have a label, the test is passed
        if (inputFormWithoutLabelHandler.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.PASSED);
            return;
        }

        ElementChecker titleAttrPresenceChecker = 
                new AttributePresenceChecker(
                    TITLE_ATTR, 
                    TestSolution.PASSED, 
                    TestSolution.FAILED, 
                    null, 
                    INVALID_FORM_FIELD_MSG);

        titleAttrPresenceChecker.check(
                sspHandler, 
                inputFormWithoutLabelHandler, 
                testSolutionHandler);
    }

    @Override
    public int getSelectionSize() {
        return inputFormHandler.size();
    }
    
}