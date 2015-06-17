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

import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementchecker.ElementChecker;
import org.tanaguru.rules.elementselector.LinkElementSelector;

/**
 * This class deals with the tests related with links. Two kinds of links are 
 * tested by a specific checker : the one that are decidable (with no context)
 * and the one that are not decidable (with context).
 * 
 */
public abstract class AbstractLinkRuleImplementation 
            extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    public LinkElementSelector getLinkElementSelector() {
        return (LinkElementSelector)this.getElementSelector();
    }

    /* the checked used for decidable elements */
    ElementChecker decidableElementsChecker;
    public ElementChecker getDecidableElementsChecker() {
        return decidableElementsChecker;
    }
    
    /* the checked used for not decidable elements */
    ElementChecker notDecidableElementsChecker;
    public ElementChecker getNotDecidableElementsChecker() {
        return notDecidableElementsChecker;
    }

    /**
     * Constructor
     * 
     * @param linkElementSelector
     * @param decidableElementsChecker
     * @param notDecidableElementsChecker
     */
    public AbstractLinkRuleImplementation (
            LinkElementSelector linkElementSelector, 
            ElementChecker decidableElementsChecker,
            ElementChecker notDecidableElementsChecker) {
        super();
        setElementSelector(linkElementSelector);
        this.decidableElementsChecker = decidableElementsChecker;
        this.notDecidableElementsChecker = notDecidableElementsChecker;
    }

    @Override
    protected void check(
            SSPHandler sspHandler, 
            TestSolutionHandler testSolutionHandler) {

        LinkElementSelector linkElementSelector = getLinkElementSelector();
        
        if (linkElementSelector.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        if (! linkElementSelector.getDecidableElements().isEmpty()) {
            setServicesToChecker(decidableElementsChecker);
            decidableElementsChecker.check(
                    sspHandler, 
                    linkElementSelector.getDecidableElements(), 
                    testSolutionHandler);
        }
        if (! linkElementSelector.getNotDecidableElements().isEmpty()) {
            setServicesToChecker(notDecidableElementsChecker);
            notDecidableElementsChecker.check(
                    sspHandler, 
                    linkElementSelector.getNotDecidableElements(), 
                    testSolutionHandler);
        }
    }

    @Override
    public int getSelectionSize() {
        return getLinkElementSelector().getDecidableElements().get().size()+
                getLinkElementSelector().getNotDecidableElements().get().size();
    }
    
}