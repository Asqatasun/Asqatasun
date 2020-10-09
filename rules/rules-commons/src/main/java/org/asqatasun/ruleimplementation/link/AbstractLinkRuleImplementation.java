/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
package org.asqatasun.ruleimplementation.link;

import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.ElementChecker;
import org.asqatasun.rules.elementselector.DecidableElementSelector;
import org.asqatasun.rules.elementselector.ElementSelector;

/**
 * This class deals with the tests related with links. Two kinds of links are 
 * tested by a specific checker : the one that are decidable (with no context)
 * and the one that are not decidable (with context).
 * 
 */
public abstract class AbstractLinkRuleImplementation 
            extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    protected DecidableElementSelector decidableElementSelector;
    public void setLinkElementSelector(DecidableElementSelector decidableElementSelector) {
        setElementSelector(decidableElementSelector);
        this.decidableElementSelector = decidableElementSelector;
    }

    /* the checked used for decidable elements */
    protected ElementChecker decidableElementsChecker;
    public void setDecidableElementsChecker(ElementChecker decidableElementsChecker) {
        this.decidableElementsChecker = decidableElementsChecker;
    }

    
    /* the checked used for not decidable elements */
    protected ElementChecker notDecidableElementsChecker;
    public void setNotDecidableElementsChecker(ElementChecker notDecidableElementsChecker) {
        this.notDecidableElementsChecker = notDecidableElementsChecker;
    }

    public AbstractLinkRuleImplementation () { super();}

    /**
     * Constructor
     * 
     * @param linkElementSelector
     * @param decidableElementsChecker
     * @param notDecidableElementsChecker
     */
    public AbstractLinkRuleImplementation (
            DecidableElementSelector linkElementSelector,
            ElementChecker decidableElementsChecker,
            ElementChecker notDecidableElementsChecker) {
        super();
        setLinkElementSelector(linkElementSelector);
        this.decidableElementsChecker = decidableElementsChecker;
        this.notDecidableElementsChecker = notDecidableElementsChecker;
    }

    @Override
    protected void check(
            SSPHandler sspHandler, 
            TestSolutionHandler testSolutionHandler) {

        
        if (decidableElementSelector.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        if (! decidableElementSelector.getDecidableElements().isEmpty()) {
            setServicesToChecker(decidableElementsChecker);
            decidableElementsChecker.check(
                    sspHandler,
                    decidableElementSelector.getDecidableElements(),
                    testSolutionHandler);
        }
        if (! decidableElementSelector.getNotDecidableElements().isEmpty()) {
            setServicesToChecker(notDecidableElementsChecker);
            notDecidableElementsChecker.check(
                    sspHandler,
                    decidableElementSelector.getNotDecidableElements(),
                    testSolutionHandler);
        }
    }

    @Override
    public int getSelectionSize() {
        return decidableElementSelector.getDecidableElements().get().size()+
                decidableElementSelector.getNotDecidableElements().get().size();
    }
    
}
