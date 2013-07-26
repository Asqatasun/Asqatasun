/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2013  Open-S Company
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: open-s AT open-s DOT com
 */

package org.opens.tanaguru.rules.elementchecker.pertinence;

import java.util.Collection;
import java.util.LinkedList;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandlerImpl;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementchecker.NomenclatureBasedElementChecker;

/**
 * 
 */
public abstract class PertinenceChecker extends NomenclatureBasedElementChecker {

    /**
     * The collection of checkers recursively called to check the pertinence. 
     * This collection is of LinkedList type to maintain an order.
     */
    private Collection<ElementChecker> checkers = new LinkedList<ElementChecker>();
    public void addChecker(ElementChecker elementChecker) {
        checkers.add(elementChecker);
    }
    
    /**
     * the message code thrown to manually check the element when no checker
     * has returned a failed result.
     */
    private String manualCheckMessage;
    
    /** 
     * The global result of the test when the element is seen as not pertinent.
     * Default is FAILED
     */
    private TestSolution notPertinentSolution = TestSolution.FAILED;

    /**
     * Constructor. 
     * Returns FAILED when the tested element is not pertinent.
     * 
     * @param manualCheckMessage
     */
    public PertinenceChecker(String manualCheckMessage) {
        super();
        this.manualCheckMessage = manualCheckMessage;
    }
    
    /**
     * Constructor. 
     * Returns FAILED when the tested element is not pertinent.
     * 
     * @param manualCheckMessage
     * @param eeAttributeNameList 
     */
    public PertinenceChecker(
            String manualCheckMessage,
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        this.manualCheckMessage = manualCheckMessage;
    }
    
    /**
     * Constructor.
     * Enables to override the not pertinent solution.
     * 
     * @param manualCheckMessage
     * @param notPertinentSolution
     */
    public PertinenceChecker(
            String manualCheckMessage,
            TestSolution notPertinentSolution) {
        super();
        this.notPertinentSolution = notPertinentSolution;
        this.manualCheckMessage = manualCheckMessage;
    }
    
    /**
     * Constructor.
     * Enables to override the not pertinent solution.
     * @param manualCheckMessage
     * @param notPertinentSolution
     * @param eeAttributeNameList 
     */
    public PertinenceChecker(
            String manualCheckMessage,
            TestSolution notPertinentSolution,
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        
        this.notPertinentSolution = notPertinentSolution;
        this.manualCheckMessage = manualCheckMessage;
    }

    @Override
    protected void doCheck(
             SSPHandler sspHandler, 
             Elements elements, 
             TestSolutionHandler testSolutionHandler) {
         setServicesToCheckers();
         ElementHandler elementHandler = new ElementHandlerImpl();
         for (Element element : elements) {
             elementHandler.clean().add(element);
             testSolutionHandler.addTestSolution(checkPertinence(sspHandler, elementHandler));
         }
    }
    
    /**
     * Check the pertinence of a text by calling recursively the checkers 
     * loaded by the instance. If no checker returns failed, a sourceCodeRemark
     * is created with a manual check message. To respect the ElementChecker 
     * interface, the check method is called with an elementHandler instance as
     * argument. This instance only contains the current checked element.
     * 
     * @param sspHandler
     * @param elementHandler
     */
    protected TestSolution checkPertinence(
            SSPHandler sspHandler,
            ElementHandler elementHandler) {
        
        TestSolutionHandler testSolutionHandler = new TestSolutionHandlerImpl();
        
        for (ElementChecker ec : checkers) {
            testSolutionHandler.cleanTestSolutions();
            ec.check(sspHandler, elementHandler, testSolutionHandler);
            
            TestSolution checkerSolution = testSolutionHandler.getTestSolution();

            Logger.getLogger(this.getClass()).debug("Checker "+ec.getClass() + 
                    "returned " + checkerSolution);

            if (checkerSolution.equals(notPertinentSolution)) {
                return checkerSolution;
            }
        }

        // if the test at this step is not failed, create a NMI ProcessRemark
        // for a manual check
        Element el = elementHandler.get().first();
        getProcessRemarkService().addSourceCodeRemarkOnElement(
                TestSolution.NEED_MORE_INFO,
                el,
                manualCheckMessage, 
                getEvidenceElementCollection(el, getEeAttributeNameList()));

        return TestSolution.NEED_MORE_INFO;
    }

    /**
     * Set service to elementChecker depending on their nature.
     * @param elementChecker 
     */
    private void setServicesToCheckers() {
        for (ElementChecker el : checkers) {
            if (el instanceof NomenclatureBasedElementChecker) {
                ((NomenclatureBasedElementChecker)el).
                    setNomenclatureLoaderService(getNomenclatureLoaderService());
            }
        }
    }

}