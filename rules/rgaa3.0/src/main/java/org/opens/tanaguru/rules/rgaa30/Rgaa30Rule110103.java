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

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementchecker.element.ElementPresenceChecker;
import org.opens.tanaguru.rules.elementchecker.text.TextEmptinessChecker;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import org.opens.tanaguru.rules.elementselector.builder.CssLikeSelectorBuilder;
import static org.opens.tanaguru.rules.keystore.AttributeStore.ARIA_LABELLEDBY_ATTR;
import static org.opens.tanaguru.rules.keystore.AttributeStore.ID_ATTR;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.INPUT_ELEMENT_WITH_ARIA_INSIDE_FORM_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.ARIA_LABELLEDBY_MISSING_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.FORM_ELEMENT_WITHOUT_LABEL_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.FORM_ELEMENT_WITH_NOT_UNIQUE_LABEL_MSG;
import org.opens.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Implementation of the rule 11.1.3 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a
 * href="https://github.com/Tanaguru/Tanaguru-rules-RGAA-3-doc/wiki/Rule-11-1-3">the rule 11.1.3
 * design page.</a>
 *
 * @see <a
 * href="https://references.modernisation.gouv.fr/sites/default/files/RGAA3/referentiel_technique.htm#test-11-1-3">
 * 11.1.3 rule specification</a>
 *
 */
public class Rgaa30Rule110103 extends AbstractPageRuleMarkupImplementation {

    private static final String FORM_TAG = "form";
    private SimpleElementSelector selector;
    private final ElementHandler<Element> inputElementHandler = new ElementHandlerImpl();
    private final Map<Element, ElementHandler<Element>> inputFormMap = new HashMap();

    /**
     * Default constructor
     */
    public Rgaa30Rule110103() {
        super();
    }

    @Override
    protected void select(SSPHandler sspHandler, ElementHandler elementHandler) {
        selector = new SimpleElementSelector(INPUT_ELEMENT_WITH_ARIA_INSIDE_FORM_CSS_LIKE_QUERY);
        selector.selectElements(
                sspHandler,
                inputElementHandler);
        putInputElementHandlerIntoTheMap();
    }

    @Override
    public int getSelectionSize() {
        return inputElementHandler.get().size();
    }

    @Override
    protected void check(
            SSPHandler sspHandler,
            ElementHandler elementHandler,
            TestSolutionHandler testSolutionHandler) {

        /* If the page has no input form element, the test is not applicable */
        if (inputFormMap.entrySet().isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }

        for (Map.Entry<Element, ElementHandler<Element>> entry : inputFormMap.entrySet()) {
            /* The attribute Emptiness Checker. Keep default value i.e failed 
             when attribute is empty
             */
            ElementChecker attributeEmptinessChecker
                    = new TextEmptinessChecker(
                            new TextAttributeOfElementBuilder(ARIA_LABELLEDBY_ATTR),
                            ARIA_LABELLEDBY_MISSING_MSG,
                            null);
            attributeEmptinessChecker.check(sspHandler, entry.getValue(), testSolutionHandler);

            ElementHandler<Element> inputWithoutLabel = new ElementHandlerImpl();
            ElementHandler<Element> notUniqueLabel = new ElementHandlerImpl();
            for (Element el : entry.getValue().get()) {
                ElementHandler<Element> labelHandler = new ElementHandlerImpl();
                labelHandler.addAll(entry.getKey().select(CssLikeSelectorBuilder
                        .buildSelectorFromAttributeTypeAndValue(ID_ATTR, el.attr(ARIA_LABELLEDBY_ATTR))));
                if (labelHandler.get().isEmpty()) {
                    inputWithoutLabel.add(el);
                } else if (labelHandler.get().size() > 1) {
                    notUniqueLabel.add(el);
                    notUniqueLabel.addAll(labelHandler.get());
                }
            }

            /* Check if the form element has a label associated */
            ElementChecker elementPresenceChecker
                    = new ElementPresenceChecker(TestSolution.FAILED,
                            TestSolution.PASSED,
                            FORM_ELEMENT_WITHOUT_LABEL_MSG,
                            null);
            elementPresenceChecker.check(sspHandler, inputWithoutLabel, testSolutionHandler);

            /* Check if the id attr of the label associated to the form element is unique */
            elementPresenceChecker
                    = new ElementPresenceChecker(TestSolution.FAILED,
                            TestSolution.PASSED,
                            FORM_ELEMENT_WITH_NOT_UNIQUE_LABEL_MSG,
                            null);
            elementPresenceChecker.check(sspHandler, notUniqueLabel, testSolutionHandler);
        }
    }

    /**
     * This method linked each input on a page to its form in a map.
     */
    private void putInputElementHandlerIntoTheMap() {
        for (Element el : inputElementHandler.get()) {
            Element tmpElement = el.parent();
            while (StringUtils.isNotBlank(tmpElement.tagName())) {
                if (tmpElement.tagName().equals(FORM_TAG)) {
                    if (inputFormMap.containsKey(tmpElement)) {
                        inputFormMap.get(tmpElement).add(el);
                    } else {
                        ElementHandler<Element> inputElement = new ElementHandlerImpl();
                        inputElement.add(el);
                        inputFormMap.put(tmpElement, inputElement);
                    }
                    break;
                }
                tmpElement = tmpElement.parent();
            }
        }
    }

}
