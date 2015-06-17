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
package org.tanaguru.ruleimplementation;

import java.util.ArrayList;
import java.util.Collection;
import org.tanaguru.entity.audit.DefiniteResult;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;

/**
 * This class should be overriden by concrete {@link RuleImplementation} classes
 * which implement tests with page scope. The process implementation
 * is divided into 2 commons actions when testing a page : 
 *  <ul>
 *  <li>selectionAndCheck</li>
 *  <li>result computation</li>
 *  </ul>
 *  It provides a default implementation for the result computation.<br/>
 *  This class implements the {@link TestSolutionHanlder} to handle results
 *  of the checks. <br/>
 * 
 * @author jkowalczyk
 */
public abstract class AbstractPageRuleDefaultImplementation extends AbstractPageRuleImplementation 
    implements TestSolutionHandler {

    /**
     * The testSolution of the elementary checks used to compute the final
     * result of the test
     */
    Collection<TestSolution> testSolutions = new ArrayList<>();
    
    /**
     * Default constructor
     */
    public AbstractPageRuleDefaultImplementation(){}
    
    /**
     * This implementation of the process defines 3 actions that characterise
     * an usual way to test a page : the selection, the check and the 
     * computation of the result.
     * It also reset the processRemarkService.
     * 
     * @param sspHandler
     * @return 
     */
    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        sspHandler.getProcessRemarkService().resetService();
        selectAndCheck(sspHandler, this);
        return computeResult(sspHandler, this);
    }
    
    /**
     * This method combines the select and the check operations. The instance of 
     * {@link SSPHandler} received as a parameter concerns only one page. 
     *
     * @param sspHandler
     *            the SSP handler to use.
     * @param testSolutionHandler
     *            the testSolutionHandler that handles the computed TestSolutions.
     */
    abstract protected void selectAndCheck(SSPHandler sspHandler, TestSolutionHandler testSolutionHandler);
    
    /**
     * 
     * @return The total number of elements implied by test.
     */
    abstract protected int getSelectionSize();
    
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
        return prepareDefiniteResult(testSolutionHandler.getTestSolution(), sspHandler, getSelectionSize());
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
        return prepareDefiniteResult(testSolutionHandler.getTestSolution(), sspHandler, elementCounter);
    }
    
    /**
     * Prepares and return a {link @DefiniteResult} instance that handles the 
     * test result and all the associated data (counter and remarkList).
     * 
     * @param testSolution
     * @param sspHandler
     * @param elementCounter
     * @return a Definite result that handles the result of the test
     */
    protected ProcessResult prepareDefiniteResult(
            TestSolution testSolution, 
            SSPHandler sspHandler, 
            int elementCounter) {

        DefiniteResult result = processResultDataService.getDefiniteResult(
                test,
                sspHandler.getSSP().getPage(),
                testSolution,
                elementCounter,
                sspHandler.getRemarkList());
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
    public void addTestSolution(TestSolution testSolution) {
        testSolutions.add(testSolution);
    }
    
    @Override
    public void addTestSolutions(Collection<TestSolution> testSolutions) {
        this.testSolutions.addAll(testSolutions);
    }
    
    @Override
    public void cleanTestSolutions() {
         testSolutions.clear();
    }
    
    @Override    
    public TestSolution getTestSolution() {
        return RuleHelper.synthesizeTestSolutionCollection(testSolutions);
    }

}