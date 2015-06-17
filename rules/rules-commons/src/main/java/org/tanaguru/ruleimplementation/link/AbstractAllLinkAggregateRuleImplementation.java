/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.ruleimplementation.link;

import org.jsoup.nodes.Element;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.tanaguru.ruleimplementation.ElementHandler;
import org.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementchecker.ElementChecker;
import org.tanaguru.rules.elementselector.ElementSelector;
import org.tanaguru.rules.elementselector.LinkElementSelector;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.BUTTON_FORM_CSS_LIKE_QUERY;

/**
 * This class deals with the tests related with links. All the link types are 
 * tested regarding two different checkers : the one that are decidable (with no context)
 * and the one that are not decidable (with context).
 * We consider here that a link can be 
 * <ul>
 * <li>a simple link </li>
 * <li>an image link </li>
 * <li>a composite link </li>
 * <li>an area </li>
 * </ul>
 * By addition, some tests may be done on buttons that can be considered as 
 * elements that lead to any action (as a link).
 */
public abstract class AbstractAllLinkAggregateRuleImplementation 
            extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /* the link element selector */
    LinkElementSelector linkElementSelector;
    ElementHandler<Element> linkElementHandler;
    /* the composite link element selector */
    LinkElementSelector compositeLinkElementSelector;
    ElementHandler<Element> compositeLinkElementHandler;
    /* the image link element selector */
    LinkElementSelector imageLinkElementSelector;
    ElementHandler<Element> imageLinkElementHandler;
    /* the area link element selector */
    LinkElementSelector areaLinkElementSelector;
    ElementHandler<Element> areaLinkElementHandler;
    /* the form buttons selector*/
    ElementSelector formButtonSelector;
    ElementHandler<Element> formButtonHandler;
    
    /* the checked used for decidable elements */
    ElementChecker decidableElementsChecker;
    /* the checked used for not decidable elements */
    ElementChecker notDecidableElementsChecker;

    /**
     * Constructor
     * 
     * @param linkElementSelector
     * @param imageLinkElementSelector
     * @param areaLinkElementSelector
     * @param compositeLinkElementSelector
     * @param decidableElementsChecker
     * @param notDecidableElementsChecker
     */
    public AbstractAllLinkAggregateRuleImplementation (
            LinkElementSelector linkElementSelector, 
            LinkElementSelector imageLinkElementSelector, 
            LinkElementSelector areaLinkElementSelector, 
            LinkElementSelector compositeLinkElementSelector, 
            ElementChecker decidableElementsChecker,
            ElementChecker notDecidableElementsChecker) {
        super();
        
        this.linkElementSelector = linkElementSelector;
        this.linkElementHandler = new ElementHandlerImpl();
        
        this.imageLinkElementSelector = imageLinkElementSelector;
        this.imageLinkElementHandler = new ElementHandlerImpl();
        
        this.areaLinkElementSelector = areaLinkElementSelector;
        this.areaLinkElementHandler = new ElementHandlerImpl();
        
        this.compositeLinkElementSelector = compositeLinkElementSelector;
        this.compositeLinkElementHandler = new ElementHandlerImpl();
        
        formButtonSelector = new SimpleElementSelector(BUTTON_FORM_CSS_LIKE_QUERY);
        formButtonHandler = new ElementHandlerImpl();
        
        this.decidableElementsChecker = decidableElementsChecker;
        this.notDecidableElementsChecker = notDecidableElementsChecker;
    }

    @Override
    protected void select(SSPHandler sspHandler) {
        linkElementSelector.selectElements(sspHandler, linkElementHandler);
        imageLinkElementSelector.selectElements(sspHandler, imageLinkElementHandler);
        areaLinkElementSelector.selectElements(sspHandler, areaLinkElementHandler);
        compositeLinkElementSelector.selectElements(sspHandler, compositeLinkElementHandler);
        formButtonSelector.selectElements(sspHandler, formButtonHandler);
    }
    
    @Override
    protected void check(
            SSPHandler sspHandler, 
            TestSolutionHandler testSolutionHandler) {
        
        if (selectionsEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        
        testElements(sspHandler, linkElementSelector, testSolutionHandler);
        testElements(sspHandler, imageLinkElementSelector, testSolutionHandler);
        testElements(sspHandler, areaLinkElementSelector, testSolutionHandler);
        testElements(sspHandler, compositeLinkElementSelector, testSolutionHandler);
        
        checkButtonSelection(sspHandler, formButtonHandler, testSolutionHandler);
    }

    /**
     * 
     * @return 
     */
    private boolean selectionsEmpty() {
        return linkElementHandler.isEmpty() && 
            imageLinkElementHandler.isEmpty() && 
            compositeLinkElementHandler.isEmpty() &&
            areaLinkElementHandler.isEmpty() &&
            formButtonHandler.isEmpty();
    }
    
    /**
     * 
     * @param sspHandler
     * @param linkElementSelector
     * @param testSolutionHandler 
     */
    private void testElements(
            SSPHandler sspHandler, 
            LinkElementSelector linkElementSelector, 
            TestSolutionHandler testSolutionHandler) {
        if (! linkElementSelector.getDecidableElements().isEmpty() && 
                decidableElementsChecker != null) {
            setServicesToChecker(decidableElementsChecker);
            decidableElementsChecker.check(
                    sspHandler, 
                    linkElementSelector.getDecidableElements(), 
                    testSolutionHandler);
        }
        if (! linkElementSelector.getNotDecidableElements().isEmpty() && 
                notDecidableElementsChecker != null) {
            setServicesToChecker(notDecidableElementsChecker);
            notDecidableElementsChecker.check(
                    sspHandler, 
                    linkElementSelector.getNotDecidableElements(), 
                    testSolutionHandler);
        }
    }
    
    /**
     * 
     * @param sspHandler
     * @param formButtonHandler
     * @param testSolutionHandler 
     */
    protected abstract void checkButtonSelection(
            SSPHandler sspHandler, 
            ElementHandler formButtonHandler, 
            TestSolutionHandler testSolutionHandler);
    
    @Override
    public int getSelectionSize() {
        return linkElementHandler.size() +
                imageLinkElementHandler.size() +
                compositeLinkElementHandler.size() +
                areaLinkElementHandler.size() +
                formButtonHandler.size();
    }

}