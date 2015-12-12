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

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jsoup.nodes.Element;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.asqatasun.ruleimplementation.ElementHandler;
import org.asqatasun.ruleimplementation.ElementHandlerImpl;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.ElementChecker;
import org.asqatasun.rules.elementchecker.element.ElementPresenceChecker;
import org.asqatasun.rules.elementchecker.text.TextEmptinessChecker;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import org.asqatasun.rules.elementselector.builder.CssLikeSelectorBuilder;
import static org.asqatasun.rules.keystore.AttributeStore.ARIA_LABELLEDBY_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.ID_ATTR;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.INPUT_ELEMENT_WITH_ARIA_INSIDE_FORM_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.HtmlElementStore.FORM_ELEMENT;
import static org.asqatasun.rules.keystore.RemarkMessageStore.ARIA_LABELLEDBY_EMPTY_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.FORM_ELEMENT_WITHOUT_LABEL_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.FORM_ELEMENT_WITH_NOT_UNIQUE_LABEL_MSG;
import org.asqatasun.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Implementation of the rule 11.1.3 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a
 * href="http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-1-3.html">the rule 11.1.3
 * design page.</a>
 *
 * @see <a
 * href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-11-1-3">
 * 11.1.3 rule specification</a>
 *
 */
public class Rgaa30Rule110103 extends AbstractPageRuleMarkupImplementation {

    private SimpleElementSelector selector;
    private final ElementHandler<Element> inputElementHandler = new ElementHandlerImpl();
    private final Map<Element, ElementHandler<Element>> inputFormMap = new HashMap<>();

    /**
     * Default constructor
     */
    public Rgaa30Rule110103() {
        super();
    }

    @Override
    protected void select(SSPHandler sspHandler) {
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
                            ARIA_LABELLEDBY_EMPTY_MSG,
                            null);
            attributeEmptinessChecker.check(sspHandler, entry.getValue(), testSolutionHandler);

            ElementHandler<Element> inputWithoutLabel = new ElementHandlerImpl();
            ElementHandler<Element> notUniqueLabel = new ElementHandlerImpl();
            for (Element el : entry.getValue().get()) {
                if (StringUtils.isNotEmpty(el.attr(ARIA_LABELLEDBY_ATTR))) {
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
            }

            /* Check if the form element has a label associated */
            ElementChecker elementPresenceChecker
                    = new ElementPresenceChecker(
                            new ImmutablePair(TestSolution.FAILED,FORM_ELEMENT_WITHOUT_LABEL_MSG),
                            new ImmutablePair(TestSolution.PASSED,""));
            elementPresenceChecker.check(sspHandler, inputWithoutLabel, testSolutionHandler);

            /* Check if the id attr of the label associated to the form element is unique */
            elementPresenceChecker
                    = new ElementPresenceChecker(
                            new ImmutablePair(TestSolution.FAILED,FORM_ELEMENT_WITH_NOT_UNIQUE_LABEL_MSG),
                            new ImmutablePair(TestSolution.PASSED,""));
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
                if (tmpElement.tagName().equals(FORM_ELEMENT)) {
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
