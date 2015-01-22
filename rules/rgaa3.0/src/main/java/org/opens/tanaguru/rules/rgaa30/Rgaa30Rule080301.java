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

import org.apache.commons.collections.CollectionUtils;
import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.*;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.LANG_ATTRIBUTE_MISSING_ON_HTML_TAG_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.LANG_ATTRIBUTE_MISSING_ON_WHOLE_PAGE_MSG;

/**
 * Implementation of the rule 8.3.1 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="https://github.com/Tanaguru/Tanaguru-rules-RGAA-3-doc/wiki/Rule-8-3-1">the rule 8.3.1 design page.</a>
 * @see <a href="https://references.modernisation.gouv.fr/sites/default/files/RGAA3/referentiel_technique.htm#test-8-3-1"> 8.3.1 rule specification</a>
 *
 */
public class Rgaa30Rule080301 extends AbstractPageRuleMarkupImplementation {

    /**
     * the elements with a lang attribute
     */
    private final ElementHandler<Element> elementWithLang
            = new ElementHandlerImpl();
    /**
     * the elements without lang attribute
     */
    private final ElementHandler<Element> elementWithoutLang
            = new ElementHandlerImpl();
    /**
     * the html element with lang attribute
     */
    private final ElementHandler<Element> htmlWithLangHandler
            = new ElementHandlerImpl();

    /**
     * Default constructor
     */
    public Rgaa30Rule080301() {
        super();
    }

    @Override
    protected void select(SSPHandler sspHandler) {
        new SimpleElementSelector(HTML_WITH_LANG_CSS_LIKE_QUERY)
                .selectElements(sspHandler, htmlWithLangHandler);

        if (!htmlWithLangHandler.isEmpty()) {
            return;
        }

        new SimpleElementSelector(ELEMENT_WITH_LANG_ATTR_CSS_LIKE_QUERY)
                .selectElements(sspHandler, elementWithLang);
        new SimpleElementSelector(ELEMENT_WITHOUT_LANG_ATTR_CSS_LIKE_QUERY)
                .selectElements(sspHandler, elementWithoutLang);

        removeElementWithParentWithLangAttr();
    }

    @Override
    protected void check(
            SSPHandler sspHandler,
            TestSolutionHandler testSolutionHandler) {

        if (!htmlWithLangHandler.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.PASSED);
            return;
        }
        if (elementWithLang.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.FAILED);
            sspHandler.getProcessRemarkService().addProcessRemark(
                    TestSolution.FAILED,
                    LANG_ATTRIBUTE_MISSING_ON_WHOLE_PAGE_MSG);
            return;
        }
        if (elementWithoutLang.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.PASSED);
        } else {
            testSolutionHandler.addTestSolution(TestSolution.FAILED);
            sspHandler.getProcessRemarkService().addProcessRemark(
                    TestSolution.FAILED,
                    LANG_ATTRIBUTE_MISSING_ON_HTML_TAG_MSG);
        }
    }

    /**
     * Remove elements from the current elementHandler when an element has a
     * parent with a lang attribute
     *
     * @param elementHandler
     */
    private void removeElementWithParentWithLangAttr() {
        if (elementWithLang.isEmpty()) {
            return;
        }
        ElementHandler<Element> elementWithParentWithLang = new ElementHandlerImpl();
        for (Element el : elementWithoutLang.get()) {
            if (isElementHasParentWithLang(el)) {
                elementWithParentWithLang.add(el);
            }
        }
        elementWithoutLang.removeAll(elementWithParentWithLang);
        elementWithLang.addAll(elementWithParentWithLang.get());
    }

    /**
     * Checks recursively whether an element has a parent with a lang attribute
     *
     * @param el
     * @return whether the element passed as argument has a parent with a lang
     * attribute
     */
    private boolean isElementHasParentWithLang(Element el) {
        return CollectionUtils.containsAny(el.parents(), elementWithLang.get());
    }

    @Override
    public int getSelectionSize() {
        if (!htmlWithLangHandler.isEmpty()) {
            return htmlWithLangHandler.size();
        }
        return elementWithLang.size() + elementWithoutLang.size();
    }

}