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
package org.opens.tanaguru.ruleimplementation;

import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementchecker.NomenclatureBasedElementChecker;
import org.opens.tanaguru.rules.elementchecker.pertinence.LinkPertinenceChecker;
import org.opens.tanaguru.rules.elementselector.LinkElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.opens.tanaguru.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * This class deals with the pertinence of the text links.
 * 
 */
public abstract class AbstractLinkPertinenceRuleImplementation 
        extends AbstractPageRuleMarkupImplementation {

    /* the link element selector */
    private LinkElementSelector linkElementSelector;
    
    /**
     * Constructor
     * 
     * @param linkElementSelector
     * @param considerContext 
     */
    public AbstractLinkPertinenceRuleImplementation (
            LinkElementSelector linkElementSelector) {
        super();
        this.linkElementSelector = linkElementSelector;
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
        super.check(sspHandler, elementHandler, testSolutionHandler);
        if (elementHandler.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        if (! linkElementSelector.getDecidableElements().isEmpty()) {
            String manualMessage;
            if (linkElementSelector.isConsiderContext()) {
                manualMessage = RemarkMessageStore.CHECK_LINK_WITHOUT_CONTEXT_PERTINENCE_MSG;
            } else {
                manualMessage = RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG;
            }
            getCheckerForDecidableElements(manualMessage).check(
                    sspHandler, 
                    linkElementSelector.getDecidableElements(), 
                    testSolutionHandler);
        }
        if (! linkElementSelector.getNotDecidableElements().isEmpty()) {
            getCheckerForNotDecidableElements().check(
                    sspHandler, 
                    linkElementSelector.getNotDecidableElements(), 
                    testSolutionHandler);
        }
    }

    /**
     * 
     * @return 
     */
    private ElementChecker getCheckerForNotDecidableElements() {
        return getTextPertinenceChecker(
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG, 
                RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG);
    }
    
    /**
     * @param manualMessage
     * @return 
     */
    private ElementChecker getCheckerForDecidableElements(String manualMessage) {
        return getTextPertinenceChecker(
                TestSolution.FAILED, 
                RemarkMessageStore.UNEXPLICIT_LINK_MSG, 
                manualMessage);
    }
    
    /**
     * 
     * @param notPertinentSolution
     * @param notPertinentMessage
     * @return 
     */
    private ElementChecker getTextPertinenceChecker(
            TestSolution notPertinentSolution, 
            String notPertinentMessage, 
            String manualCheckMessage) {
        NomenclatureBasedElementChecker checker = new LinkPertinenceChecker(
                    // not pertinent solution 
                    notPertinentSolution,
                    // not pertinent message
                    notPertinentMessage, 
                    // manual check message
                    manualCheckMessage,
                    // evidence elements
                    TEXT_ELEMENT2,
                    TITLE_ATTR
                );
        checker.setNomenclatureLoaderService(nomenclatureLoaderService);
        return checker;
    }
}