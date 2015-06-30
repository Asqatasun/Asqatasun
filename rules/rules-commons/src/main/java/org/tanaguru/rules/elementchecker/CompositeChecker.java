/*
 *  Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
 *  Contact us by mail: tanaguru AT tanaguru DOT org
 */

package org.tanaguru.rules.elementchecker;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.ElementHandler;
import org.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandlerImpl;

/**
 * This checker aggregate checkers and call then recursively, element by element
 * 
 */
public class CompositeChecker extends NomenclatureBasedElementChecker {

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
     * boolean used to determine how to compute results from successive checkers
     */
    private boolean isOrCombinaison = true;
    public void setIsOrCombinaison(boolean isOrCombinaison) {
        this.isOrCombinaison = isOrCombinaison;
    }
    
    /**
     * This map provides a way to override the final result of the successive 
     * checkers execution and associate eventually a message.
     */
    private final Map<TestSolution, Map<TestSolution, String>> checkMessageFromSolutionMap = 
            new HashMap<>();
    public void addCheckMessageFromSolution(TestSolution solution, Map<TestSolution, String> resultAndMessage) {
        checkMessageFromSolutionMap.put(solution, resultAndMessage);
    }
    
    /**
     * Constructor. 
     * 
     * @param elementCheckers
     */
    public CompositeChecker(ElementChecker... elementCheckers) {
        super();
        this.checkers.addAll(Arrays.asList(elementCheckers));
    }
    
    /**
     * 
     */
    public CompositeChecker() {
        super();
    }
    
    /**
     * 
     * @param eeAttributeNameList 
     */
    public CompositeChecker(String... eeAttributeNameList) {
        super(eeAttributeNameList);
    }
    
    /**
     * 
     * @param failureSolution
     */
    public CompositeChecker(TestSolution failureSolution) {
        super();
        setFailureSolution(failureSolution);
    }
    
    /**
     * Constructor.
     * Enables to override the failure solution.
     * @param failureSolution
     * @param eeAttributeNameList 
     */
    public CompositeChecker(
            TestSolution failureSolution,
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        setFailureSolution(failureSolution);
    }

    @Override
    protected void doCheck(
             SSPHandler sspHandler, 
             Elements elements, 
             TestSolutionHandler testSolutionHandler) {
         setServicesToCheckers();
         ElementHandler<Element> elementHandler = new ElementHandlerImpl();
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
            
            if (isOrCombinaison && (checkerSolution.equals(ec.getFailureSolution()) || 
                    checkerSolution.equals(TestSolution.NOT_APPLICABLE)))  {
                return checkerSolution;
            }
            globalTestSolutionHandler.addTestSolution(checkerSolution);
        }

        return createSolutionFromCheckersResult(
                globalTestSolutionHandler.getTestSolution(), 
                elementHandler);
    }
            
    /**
     * 
     * @param testSolution 
     * @param elementHandler 
     * @return the solution when the successive checkers doesn't produce a 
     * failure or a not_applicable result. The final result may be overidden 
     * if the solution is managed by the checkMessageFromSolutionMap.
     */
    protected TestSolution createSolutionFromCheckersResult (
            TestSolution testSolution, 
            ElementHandler<Element> elementHandler) {
        if (checkMessageFromSolutionMap.containsKey(testSolution)) {
            // if the final solution belongs to the checkMessageFromSolutionMap,
            // we create a message and override the final result
            Map.Entry<TestSolution, String> entry = 
                    checkMessageFromSolutionMap.get(testSolution).entrySet().iterator().next();
            addSourceCodeRemark(
                    entry.getKey(),
                    (Element)elementHandler.get().iterator().next(),
                    entry.getValue());
            return entry.getKey();
        } else {
            return testSolution;
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