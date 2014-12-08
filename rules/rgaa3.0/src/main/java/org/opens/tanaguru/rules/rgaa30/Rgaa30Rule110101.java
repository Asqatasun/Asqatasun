/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2014  Open-S Company
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

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementchecker.attribute.AttributePresenceChecker;
import org.opens.tanaguru.rules.elementselector.ElementSelector;
import org.opens.tanaguru.rules.elementselector.InputFormElementWithExplicitLabelSelector;
import org.opens.tanaguru.rules.elementselector.InputFormElementWithInplicitLabelSelector;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.INPUT_ELEMENT_INSIDE_FORM_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.INVALID_FORM_FIELD_MSG;

/**
 * Implementation of the rule 11.1.1 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="https://github.com/Tanaguru/Tanaguru-rules-RGAA-3-doc/wiki/Rule-11-1-1">the rule 11.1.1 design page.</a>
 * @see <a href="https://references.modernisation.gouv.fr/sites/default/files/RGAA3/referentiel_technique.htm#test-11-1-1"> 11.1.1 rule specification</a>
 *
 */
public class Rgaa30Rule110101 extends AbstractPageRuleMarkupImplementation {

    /** the input form elements with implicit label */
    private final ElementHandler inputFormWithoutLabelHandler = new ElementHandlerImpl();
        
    /**
     * Default constructor
     */
    public Rgaa30Rule110101() {
        super();
    }

    @Override
    protected void select(SSPHandler sspHandler, ElementHandler elementHandler) {
        
        // Selection of all the input form elements of the page
        ElementSelector elementSelector = new SimpleElementSelector(INPUT_ELEMENT_INSIDE_FORM_CSS_LIKE_QUERY);
        elementSelector.selectElements(sspHandler, elementHandler);

        // the selection of the input form elements without label is initialised
        // with all the elements of the page, some elements will be removed later
        inputFormWithoutLabelHandler.addAll(elementHandler.get());
        
        // selection of the input form elements with explicit label
        ElementHandler inputFormLabelHandler = new ElementHandlerImpl();
        ElementSelector explicitLabelSelector = 
                new InputFormElementWithExplicitLabelSelector(elementHandler);
        explicitLabelSelector.selectElements(sspHandler, inputFormLabelHandler);

        // remove all the input form elements with explicit label from 
        // the selection of the input form elements without label
        inputFormWithoutLabelHandler.removeAll(inputFormLabelHandler.get());
        
        // selection of the input form with inplicit label
        ElementSelector inplicitLabelSelector = 
                new InputFormElementWithInplicitLabelSelector(elementHandler);
        inplicitLabelSelector.selectElements(sspHandler, inputFormLabelHandler);
        
        // remove all the input form elements with inplicit label from 
        // the selection of the input form elements without label
        inputFormWithoutLabelHandler.removeAll(inputFormLabelHandler.get());
    }

    @Override
    protected void check(
            SSPHandler sspHandler, 
            ElementHandler selectionHandler, 
            TestSolutionHandler testSolutionHandler) {

        // If the page has no input form element, the test is not applicable
        if (selectionHandler.isEmpty()) {
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

}
