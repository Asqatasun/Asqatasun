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

import org.apache.commons.collections.CollectionUtils;
import org.jsoup.nodes.Element;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.asqatasun.ruleimplementation.ElementHandler;
import org.asqatasun.ruleimplementation.ElementHandlerImpl;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.*;
import static org.asqatasun.rules.keystore.RemarkMessageStore.LANG_ATTRIBUTE_MISSING_ON_HTML_TAG_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.LANG_ATTRIBUTE_MISSING_ON_WHOLE_PAGE_MSG;

/**
 * Implementation of the rule 8.3.1 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to
 * * <a href="http://doc.asqatasun.org/en/90_Rules/accessiweb2.2/08.Mandatory_elements/Rule-8.3.1.html">the rule 8.3.1
 * design page.</a>
 *
 * @see
 * <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-8-3-1">
 * 8.3.1 rule specification</a>
 *
 */
public class Aw22Rule08031 extends AbstractPageRuleMarkupImplementation {

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
    public Aw22Rule08031() {
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
