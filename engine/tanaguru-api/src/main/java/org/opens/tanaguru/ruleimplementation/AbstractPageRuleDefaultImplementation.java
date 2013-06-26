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

import java.util.ArrayList;
import java.util.Collection;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;

/**
 * This class should be overriden by concrete {@link RuleImplementation} classes
 * which implement tests with page scope. The process implementation
 * is divided into 3 commons actions when testing a page : 
 *  <ul>
 *  <li>selection</li>
 *  <li>check</li>
 *  <li>result computation<li>
 *  </ul>
 *  It provides a default implementation for the result computation.<br/>
 *  This class implements the {@link ElementHanlder} interface to handle the selected 
 *  elements of the selections and the {@link TestSolutionHanlder} to handle results
 *  of the checks. <br/>
 *  The concrete implementation may need to use local instances of {@link ElementHanlder}
 *  implementation when multiple selection is needed.
 * 
 * @author jkowalczyk
 */
public abstract class AbstractPageRuleDefaultImplementation extends AbstractPageRuleImplementation 
    implements ElementHandler, TestSolutionHandler{

    /**
     * The testSolution of the elementary checks used to compute the final
     * result of the test
     */
    Collection<TestSolution> testSolutions = new ArrayList<TestSolution>();
    
    /**
     * The elements implied by the test
     */
    private Elements elements = new Elements();
    
    /**
     * Default constructor
     */
    public AbstractPageRuleDefaultImplementation(){}
    
    /**
     * This implementation of the process defines 3 actions that characterise
     * an usual way to test a page : the selection, the check and the 
     * computation of the result.
     * 
     * @param sspHandler
     * @return 
     */
    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        select(sspHandler, this);
        check(sspHandler, this, this);
        return computeResult(sspHandler, this);
    }
    
    /**
     * This method defines a select operation . The instance of 
     * {@link SSPHandler} received as a parameter concerns only one page. 
     *
     * @param sspHandler
     *            the SSP handler to use.
     * @param elementHandler
     *            the selectionHandler that handles the selected elements of the DOM.
     * @return the result of the processing.
     */
    abstract protected void select(SSPHandler sspHandler, ElementHandler elementHandler);
    
    /**
     * This method defines a check operation . The instance of 
     * {@link SSPHandler} received as a parameter concerns only one page. 
     * The check operation may create TestSolutions handled by the 
     * testSolutionHandler
     *
     * @param sspHandler
     *            the SSP handler to use.
     * @param elementHandler
     *            the selectionHandler that handles the selected elements of the DOM.
     * @param testSolutionHandler
     *            the testSolutionHandler that handles the computed TestSolutions.
     * @return the result of the processing.
     */
    abstract protected void check(SSPHandler sspHandler, ElementHandler selectionHandler, TestSolutionHandler testSolutionHandler);
    
    /**
     * This method computes the {@link DefiniteResult} of the test from the
     * testSolutions collected while checking.
     *
     * @param sspHandler
     *            the SSP handler to use.
     * @param testSolutionHandler
     *            the testSolutionHandler that handles the computed TestSolutions.
     * @return the result of the processing.
     */
    protected ProcessResult computeResult(SSPHandler sspHandler, TestSolutionHandler testSolutionHandler) {
        return prepareDefiniteResult(testSolutionHandler.getTestSolutions(), sspHandler, elements.size());
    }
    
    /**
     * This method computes the {@link DefiniteResult} of the test from the
     * testSolutions collected while checking.
     *
     * @param sspHandler
     *            the SSP handler to use.
     * @param testSolutionHandler
     *            the testSolutionHandler that handles the computed TestSolutions.
     * @param elementCounter
     *            the number of elements that belongs to the rule scope 
     * @return the result of the processing.
     */
    protected ProcessResult computeResult(SSPHandler sspHandler, TestSolutionHandler testSolutionHandler, int elementCounter) {
        return prepareDefiniteResult(testSolutionHandler.getTestSolutions(), sspHandler, elementCounter);
    }
    
    /**
     * Prepares and return a {link @DefiniteResult} instance that handles the 
     * test result and all the associated data (counter and remarkList).
     * 
     * @param testSolution
     * @param sspHandler
     * * @param elementCounter
     * @return a Definite result that handles the result of the test
     */
    protected ProcessResult prepareDefiniteResult(
            TestSolution testSolution, 
            SSPHandler sspHandler, 
            int elementCounter) {

        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                testSolution,
                sspHandler.getRemarkList());

        result.setElementCounter(elementCounter);

        return result;
    }
    
    /**
     * Prepares {link @DefiniteResult} instance that handles the test result
     * and all the associated data. Compute the result of the test from a collection
     * of {link @TestSolution}
     * 
     * @param testSolutions
     * @param sspHandler
     * @param elementCounter
     * @return a Definite result that handles the result of the test
     */
    protected ProcessResult prepareDefiniteResult(
            Collection<TestSolution> testSolutions, 
            SSPHandler sspHandler, 
            int elementCounter) {
        return prepareDefiniteResult(
                RuleHelper.synthesizeTestSolutionCollection(testSolutions), 
                sspHandler, 
                elementCounter);
    }

    @Override
    public void addElement(Element element) {
        elements.add(element);
    }
    
    @Override
    public void removeElement(Element element) {
        elements.remove(element);
    }
    
    @Override
    public void addElements(Elements elements) {
        this.elements.addAll(elements);
    }

    @Override
    public void removeElements(Elements elements) {
        elements.removeAll(elements);
    }
    
    @Override
    public void cleanElements() {
        elements.clear();
    }
    
    @Override
    public Elements getElements() {
        return elements;
    }
    
    @Override
    public boolean isElementsEmpty() {
        return elements.isEmpty();
    }
    
    @Override
    public void addTestSolution(TestSolution testSolution) {
        testSolutions.add(testSolution);
    }
    
    @Override
    public void addTestSolutions(Collection<TestSolution> testSolutions) {
        testSolutions.addAll(testSolutions);
    }
    
    @Override
    public void cleanTestSolutions() {
         testSolutions.clear();
    }
    
    @Override    
    public Collection<TestSolution> getTestSolutions() {
        return testSolutions;
    }

}