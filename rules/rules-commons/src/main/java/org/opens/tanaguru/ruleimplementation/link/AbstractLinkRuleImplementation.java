/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.ruleimplementation.link;

import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementchecker.NomenclatureBasedElementChecker;
import org.opens.tanaguru.rules.elementselector.LinkElementSelector;

/**
 * This class deals with the tests related with links. Two kinds of links are 
 * tested by a specific checker : the one that are decidable (with no context)
 * and the one that are not decidable (with context).
 * 
 */
public abstract class AbstractLinkRuleImplementation 
            extends AbstractPageRuleMarkupImplementation {

    /* the link element selector */
    LinkElementSelector linkElementSelector;
    public LinkElementSelector getLinkElementSelector() {
        return linkElementSelector;
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
        this.linkElementSelector = linkElementSelector;
        this.decidableElementsChecker = decidableElementsChecker;
        this.notDecidableElementsChecker = notDecidableElementsChecker;
    }

    @Override
    protected void select(SSPHandler sspHandler, ElementHandler<Element> elementHandler) {
        linkElementSelector.selectElements(sspHandler, elementHandler);
    }
    
    @Override
    protected void check(
            SSPHandler sspHandler, 
            ElementHandler<Element> elementHandler, 
            TestSolutionHandler testSolutionHandler) {
        if (elementHandler.isEmpty()) {
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

    /**
     * Set service to elementChecker depending on their nature.
     * @param elementChecker 
     */
    protected void setServicesToChecker(ElementChecker elementChecker) {
        if (elementChecker instanceof NomenclatureBasedElementChecker) {
            ((NomenclatureBasedElementChecker)elementChecker).
                setNomenclatureLoaderService(nomenclatureLoaderService);
        }
    }
    
}