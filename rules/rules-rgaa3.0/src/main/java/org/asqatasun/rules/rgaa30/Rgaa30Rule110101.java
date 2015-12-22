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
import org.jsoup.nodes.Element;
import static org.asqatasun.entity.audit.TestSolution.FAILED;
import static org.asqatasun.entity.audit.TestSolution.NOT_APPLICABLE;
import static org.asqatasun.entity.audit.TestSolution.PASSED;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.asqatasun.ruleimplementation.ElementHandler;
import org.asqatasun.ruleimplementation.ElementHandlerImpl;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.ElementChecker;
import org.asqatasun.rules.elementchecker.element.ElementPresenceChecker;
import org.asqatasun.rules.elementselector.ElementSelector;
import org.asqatasun.rules.elementselector.InputFormElementWithExplicitLabelSelector;
import org.asqatasun.rules.elementselector.InputFormElementWithInplicitLabelSelector;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.AttributeStore.ARIA_LABELLEDBY_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.ARIA_LABEL_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.FORM_ELEMENT_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.RemarkMessageStore.INVALID_FORM_FIELD_MSG;

/**
 * Implementation of the rule 11.1.1 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-1-1.html">the rule 11.1.1 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-11-1-1"> 11.1.1 rule specification</a>
 *
 */
public class Rgaa30Rule110101  extends AbstractPageRuleMarkupImplementation {

    /** the input form elements with implicit label */
    private final ElementHandler<Element> inputFormWithoutLabelHandler = 
            new ElementHandlerImpl();
        
    /** the input form elements  */
    private final ElementHandler<Element> inputFormHandler = 
            new ElementHandlerImpl();
    
    /**
     * Default constructor
     */
    public Rgaa30Rule110101() {
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
        
        // selection of the input form elements with explicit label
        ElementHandler<Element> inputFormWithAttrHandler = new ElementHandlerImpl();
        for (Element el : inputFormWithoutLabelHandler.get()) {
            if (el.hasAttr(TITLE_ATTR) || el.hasAttr(ARIA_LABEL_ATTR) || el.hasAttr(ARIA_LABELLEDBY_ATTR)) {
                inputFormWithAttrHandler.add(el);
            }
        }
        
        // remove all the input form elements with title, aria-label, or 
        // aria-labelledby attributes from the selection of the input form 
        // elements without label
        inputFormWithoutLabelHandler.removeAll(inputFormWithAttrHandler.get());
    }

    @Override
    protected void check(
            SSPHandler sspHandler, 
            TestSolutionHandler testSolutionHandler) {

        // If the page has no input form element, the test is not applicable
        if (inputFormHandler.isEmpty()) {
            testSolutionHandler.addTestSolution(NOT_APPLICABLE);
            return;
        }
        // If all the input form have a label, the test is passed
        if (inputFormWithoutLabelHandler.isEmpty()) {
            testSolutionHandler.addTestSolution(PASSED);
            return;
        }

        ElementChecker ec = new ElementPresenceChecker(
                    new ImmutablePair(FAILED, INVALID_FORM_FIELD_MSG),
                    new ImmutablePair(PASSED, ""));
        ec.check(sspHandler, inputFormWithoutLabelHandler, testSolutionHandler);
    }

    @Override
    public int getSelectionSize() {
        return inputFormHandler.size();
    }

}