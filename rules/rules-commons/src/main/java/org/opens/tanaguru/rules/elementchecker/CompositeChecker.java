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

package org.opens.tanaguru.rules.elementchecker;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandlerImpl;

/**
 * This checker aggregate checkers and call then recursively, element by element
 * 
 */
public abstract class CompositeChecker extends NomenclatureBasedElementChecker {

    /**
     * The collection of checkers recursively called. 
     * This collection is of LinkedList type to maintain an order.
     */
    private final Collection<ElementChecker> checkers = new LinkedList<>();
    public Collection<ElementChecker> getCheckers() {
        return checkers;
    }
    public void addChecker(ElementChecker elementChecker) {
        checkers.add(elementChecker);
    }
    
    /**
     * the message code thrown to manually check the element when no checker
     * has returned a failed result.
     */
    private final String manualCheckMessage;
    
    /**
     * Constructor. 
     * 
     * @param elementCheckers
     */
    public CompositeChecker(ElementChecker... elementCheckers) {
        super();
        this.manualCheckMessage = "";
        this.checkers.addAll(Arrays.asList(elementCheckers));
    }
    
    /**
     * 
     * @param manualCheckMessage
     */
    public CompositeChecker(String manualCheckMessage) {
        super();
        this.manualCheckMessage = manualCheckMessage;
    }
    
    /**
     * 
     * @param manualCheckMessage
     * @param eeAttributeNameList 
     */
    public CompositeChecker(
            String manualCheckMessage,
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        this.manualCheckMessage = manualCheckMessage;
    }
    
    /**
     * 
     * @param manualCheckMessage
     * @param failureSolution
     */
    public CompositeChecker(
            String manualCheckMessage,
            TestSolution failureSolution) {
        super();
        setFailureSolution(failureSolution);
        this.manualCheckMessage = manualCheckMessage;
    }
    
    /**
     * Constructor.
     * Enables to override the failure solution.
     * @param manualCheckMessage
     * @param failureSolution
     * @param eeAttributeNameList 
     */
    public CompositeChecker(
            String manualCheckMessage,
            TestSolution failureSolution,
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        setFailureSolution(failureSolution);
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
             testSolutionHandler.addTestSolution(callCheckers(sspHandler, elementHandler));
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
     * @return the solution of the pertinence check
     */
    protected TestSolution callCheckers(
            SSPHandler sspHandler,
            ElementHandler<Element> elementHandler) {
        
        TestSolutionHandler globalTestSolutionHandler = new TestSolutionHandlerImpl();

        for (ElementChecker ec : checkers) {
            TestSolutionHandler testSolutionHandler = new TestSolutionHandlerImpl();
            ec.check(sspHandler, elementHandler, testSolutionHandler);

            TestSolution checkerSolution = testSolutionHandler.getTestSolution();

            Logger.getLogger(this.getClass()).debug("Checker "+ec.getClass() + 
                    " returned " + checkerSolution);

            if (checkerSolution.equals(ec.getFailureSolution()) || 
                    checkerSolution.equals(TestSolution.NOT_APPLICABLE))  {
                return checkerSolution;
            }
            globalTestSolutionHandler.addTestSolution(checkerSolution);
        }

        return createSolutionWhenCheckersOnSuccess(globalTestSolutionHandler, elementHandler);
    }

    /**
     * 
     * @param testSolutionHandler
     * @param elementHandler
     * @return the solution when the successive checkers doesn't produce a 
     * failure or a not_applicable result.
     */
    protected TestSolution createSolutionWhenCheckersOnSuccess(
            TestSolutionHandler testSolutionHandler, 
            ElementHandler<Element> elementHandler) {
        return testSolutionHandler.getTestSolution();
    }
            
    /**
     * 
     * @param elementHandler 
     */
    protected void createNMIProcessRemark(ElementHandler<Element> elementHandler) {
        if (StringUtils.isNotBlank(manualCheckMessage)) {
            // if the test at this step is not failed, create a NMI ProcessRemark
            // for a manual check
            Element el = (Element)elementHandler.get().iterator().next();
            addSourceCodeRemark(
                    TestSolution.NEED_MORE_INFO,
                    el,
                    manualCheckMessage);
        }
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