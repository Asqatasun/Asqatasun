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

package org.opens.tanaguru.rules.rgaa22;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.element.ElementPresenceChecker;
import org.opens.tanaguru.rules.elementselector.CompositeLinkElementSelector;
import org.opens.tanaguru.rules.elementselector.ElementSelector;
import org.opens.tanaguru.rules.elementselector.LinkElementSelector;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.TARGET_ATTR;
import static org.opens.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import static org.opens.tanaguru.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.CHECK_USER_IS_WARNED_WHEN_NEW_WINDOW_OPEN_MSG;
import org.opens.tanaguru.rules.textbuilder.LinkTextElementBuilder;

/**
 * Implementation of the rule 6.2 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-6-2">the rule 6.2 design page.</a>
 * @see <a href="http://rgaa.net/Presence-d-un-avertissement.html"> 6.2 rule specification </a>
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule06021 extends AbstractPageRuleMarkupImplementation {

    private static final String PARENT_TARGET_VALUE = "_parent";
    private static final String SELF_TARGET_VALUE = "_self";
    private static final String TOP_TARGET_VALUE = "_top";

    private final ElementHandler<Element> formWithTargetHandler = 
            new ElementHandlerImpl();
    private final ElementHandler<Element> imageLinks = new ElementHandlerImpl();
    
    private int linkSelected=0;
    
    /**
     * Default constructor
     */
    public Rgaa22Rule06021 () {
        super();
    }
    
    @Override
    protected void select(SSPHandler sspHandler) {
        ElementHandler<Element> tmpElHandler = new ElementHandlerImpl();
        
        /* the image link element selector */
        LinkElementSelector linkElementSelector = new LinkElementSelector(false);
        linkElementSelector.selectElements(sspHandler, tmpElHandler);
        for (Element el : linkElementSelector.getDecidableElements().get()) {
            if (doesElementHaveRequestedTargetAttribute(el)) {
                imageLinks.add(el);
            }
        }
              
        LinkElementSelector compositeLinkElementSelector = 
                new CompositeLinkElementSelector(false, false);
        tmpElHandler.clean();
        compositeLinkElementSelector.selectElements(sspHandler, tmpElHandler);
        for (Element el : compositeLinkElementSelector.getDecidableElements().get()) {
            if (doesElementHaveRequestedTargetAttribute(el)) {
                imageLinks.add(el);
            }
        }
        linkSelected = imageLinks.get().size();
        
        ElementSelector formElementSelector = new SimpleElementSelector(HtmlElementStore.FORM_ELEMENT);
        tmpElHandler.clean();
        formElementSelector.selectElements(sspHandler, tmpElHandler);
        for (Element el : tmpElHandler.get()) {
            if (doesElementHaveRequestedTargetAttribute(el)) {
                formWithTargetHandler.add(el);
            }
        }
    }
    
    @Override
    protected void check(
            SSPHandler sspHandler, 
            TestSolutionHandler testSolutionHandler) {
        if (imageLinks.isEmpty() && formWithTargetHandler.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        ElementPresenceChecker epc = new ElementPresenceChecker(
                                        TestSolution.NEED_MORE_INFO, 
                                        TestSolution.NOT_APPLICABLE, 
                                        CHECK_USER_IS_WARNED_WHEN_NEW_WINDOW_OPEN_MSG,
                                        null,
                                        TEXT_ELEMENT2,
                                        TITLE_ATTR);
        epc.setTextElementBuilder(new LinkTextElementBuilder());
        epc.check(sspHandler, imageLinks, testSolutionHandler);
        
        epc = new ElementPresenceChecker(
                    TestSolution.NEED_MORE_INFO, 
                    TestSolution.NOT_APPLICABLE, 
                    CHECK_USER_IS_WARNED_WHEN_NEW_WINDOW_OPEN_MSG,
                    null);
        epc.check(sspHandler, formWithTargetHandler, testSolutionHandler);
    }
    
    /**
     * 
     * @param element
     * @return 
     */
    private boolean doesElementHaveRequestedTargetAttribute(Element element) {
       if (!element.hasAttr(TARGET_ATTR)) {
           return false;
       }
       String targetValue = element.attr(TARGET_ATTR);
       return !(StringUtils.equalsIgnoreCase(targetValue, TOP_TARGET_VALUE) || 
               StringUtils.equalsIgnoreCase(targetValue, PARENT_TARGET_VALUE) ||
               StringUtils.equalsIgnoreCase(targetValue, SELF_TARGET_VALUE));
    }
    
    @Override
    public int getSelectionSize() {
        return linkSelected + formWithTargetHandler.get().size();
    }

}