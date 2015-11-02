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

package org.tanaguru.rules.rgaa22;

import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleFromPreProcessImplementation;
import org.tanaguru.ruleimplementation.ElementHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.domelement.DomElement;
import org.tanaguru.rules.domelement.extractor.DomElementExtractor;
import org.tanaguru.rules.elementchecker.element.ElementPresenceChecker;
import org.tanaguru.rules.keystore.HtmlElementStore;
import static org.tanaguru.rules.keystore.HtmlElementStore.BODY_ELEMENT;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_MANUALLY_OUTLINE_FOR_FORM_ELEMENT_AND_IFRAME_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.INVISIBLE_OUTLINE_ON_FOCUS_MSG;

/**
 * Implementation of the rule 7.11 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-7-11">the rule 7.11 design page.</a>
 * @see <a href="http://rgaa.net/Absence-de-suppression-de-l-effet.html"> 7.11 rule specification </a>
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule07111 extends AbstractPageRuleFromPreProcessImplementation {

    /* the none css property outline-style value*/
    private static final String NONE_OUTLINE_STYLE = "none";
    /* the hidden css property outline-style value*/
    private static final String HIDDEN_OUTLINE_STYLE = "hidden";
    /* the counter of focusable elements*/
    private int nbOfFocusableElements = 0;
    /* 
     * The focusable elements not treated.
     * For these elements, the extraction is not trusted, the default value
     * depends on the browser and may be confusable
     */
    private static final String[] FOCUSABLE_EXCLUDED_LIST = 
            {HtmlElementStore.INPUT_ELEMENT, 
             HtmlElementStore.BUTTON_ELEMENT, 
             HtmlElementStore.TEXTAREA_ELEMENT, 
             HtmlElementStore.IFRAME_ELEMENT, 
             HtmlElementStore.SELECT_ELEMENT, 
            };
    /* 
     * Whether some focusable elements have been excluded, used to throw a 
     * ProcessRemark.
     */
    private boolean focusableElementExcluded = false;
    
    /**
     * Default constructor
     */
    public Rgaa22Rule07111 () {
        super(new ElementPresenceChecker(
                    // if some elements are found
                    TestSolution.NEED_MORE_INFO, 
                    // if no found element
                    TestSolution.PASSED, 
                    // message for each detected element
                    INVISIBLE_OUTLINE_ON_FOCUS_MSG,
                    null));
    }

    @Override
    protected void doSelect(
            Collection<DomElement> domElements, 
            SSPHandler sspHandler) {
        for (DomElement domElement : domElements) {
            if (domElement.isFocusable()) {
                Element el = DomElementExtractor.getElementFromDomElement(domElement, sspHandler);
                treatFocusableElement(el, domElement, getElements());
            }
        }
    }   

     @Override
    protected void check(
            SSPHandler sspHandler, 
            TestSolutionHandler testSolutionHandler) {
         if (nbOfFocusableElements == 0) {
             testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
             return;
         }
         super.check(sspHandler, testSolutionHandler);
         if (focusableElementExcluded) {
             testSolutionHandler.addTestSolution(TestSolution.NEED_MORE_INFO);
             sspHandler.getProcessRemarkService().addProcessRemark(
                     TestSolution.NEED_MORE_INFO, 
                     CHECK_MANUALLY_OUTLINE_FOR_FORM_ELEMENT_AND_IFRAME_MSG);
         }
    }
     
    /**
     * 
     * @param element
     * @return whether the outline of the current element is visible
     */
    private boolean isOutlineVisible(DomElement element) {
        if (StringUtils.equalsIgnoreCase(element.getOutlineStyle(), NONE_OUTLINE_STYLE) || 
                StringUtils.equalsIgnoreCase(element.getOutlineStyle(), HIDDEN_OUTLINE_STYLE)) {
            return false;
        }
        if (StringUtils.equalsIgnoreCase(element.getBgColor(),element.getOutlineColor())) {
            return false;
        }
        return element.getOutlineWidthValue() != 0;
    }

    @Override
    public int getSelectionSize() {
        return nbOfFocusableElements;
    }

    /**
     * 
     * @return the FOCUSABLE_EXCLUDED_LIST tab as a list
     */
    private Collection<String> getFocusableElementExcludedList() {
        return Arrays.asList(FOCUSABLE_EXCLUDED_LIST);
    }
    
    /**
     * 
     * @param element
     * @param domElement
     * @param elementHandler 
     */
    private void treatFocusableElement(
            Element element,
            DomElement domElement, 
            ElementHandler<Element> elementHandler){
        if (element == null) {
            return;
        }
        nbOfFocusableElements++;
        if (getFocusableElementExcludedList().contains(element.tagName())) {
            focusableElementExcluded = true;
        } else if (!isOutlineVisible(domElement)) {
            if (!StringUtils.equalsIgnoreCase(element.tagName(), BODY_ELEMENT)) {
                elementHandler.add(element);
            }
        }
    }

}