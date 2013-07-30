/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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
package org.opens.tanaguru.rules.accessiweb22;

import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleDefaultImplementation;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementselector.ElementSelector;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.LANG_ATTR;
import static org.opens.tanaguru.rules.keystore.AttributeStore.XML_LANG_ATTR;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.*;
import static org.opens.tanaguru.rules.keystore.HtmlElementStore.HTML_ELEMENT;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.LANG_ATTRIBUTE_MISSING_ON_HTML_TAG_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.LANG_ATTRIBUTE_MISSING_ON_WHOLE_PAGE_MSG;

/**
 * Implementation of the rule 8.3.1 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw22-rule-8-3-1">the rule 8.3.1 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-8-3-1"> 8.3.1 rule specification</a>
 *
 */
public class Aw22Rule08031 extends AbstractPageRuleDefaultImplementation {

    /** the elements with a lang attribute */
    private ElementHandler elementWithLang = new ElementHandlerImpl();
    /** the elements without lang attribute */
    private ElementHandler elementWithoutLang = new ElementHandlerImpl();
    
    /**
     * Default constructor
     */
    public Aw22Rule08031(){
        super();
    }
    
    @Override
    protected void select(SSPHandler sspHandler, ElementHandler elementHandler) {
        ElementSelector selector = new SimpleElementSelector(HTML_WITH_LANG_CSS_LIKE_QUERY);
        selector.selectElements(sspHandler, elementHandler);
        if (!elementHandler.isEmpty()) {
            return;
        }
        selector = new SimpleElementSelector(ELEMENT_WITH_LANG_ATTR_CSS_LIKE_QUERY);
        selector.selectElements(sspHandler, elementWithLang);
        if (elementWithLang.isEmpty()) {
            return;
        }
        selector = new SimpleElementSelector(ELEMENT_WITHOUT_LANG_ATTR_CSS_LIKE_QUERY);
        selector.selectElements(sspHandler, elementWithoutLang);
        removeElementWithParentWithLangAttr(elementWithoutLang);
   }

    @Override
    protected void check(
            SSPHandler sspHandler, 
            ElementHandler selectionHandler, 
            TestSolutionHandler testSolutionHandler) {
        super.check(sspHandler, selectionHandler, testSolutionHandler);
        
        if (!selectionHandler.isEmpty()) {
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
     * Remove elements from the current elementHandler when an element has 
     * a parent with a lang attribute
     * 
     * @param elementHandler 
     */
    private void removeElementWithParentWithLangAttr(ElementHandler elementHandler) {
        ElementHandler elementWithParentWithLang = new ElementHandlerImpl();
        for (Element el : elementHandler.get()) {
            if (isElementHasParentWithLang(el)) {
                elementWithParentWithLang.add(el);
            }
        }
        elementHandler.removeAll(elementWithParentWithLang);
    }
    
    /**
     * Checks recursively whether an element has a parent with a lang attribute
     * 
     * @param el
     * @return whether the element passed as argument has a parent with a 
     * lang attribute
     */
    private boolean isElementHasParentWithLang(Element el) {
        for (Element pel : el.parents()) {
            if (!pel.nodeName().equals(HTML_ELEMENT) && 
                    (pel.hasAttr(LANG_ATTR) || 
                        pel.hasAttr(XML_LANG_ATTR))){
                return true;
            }
        }
        return false;
    }

}