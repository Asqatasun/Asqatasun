/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Tanaguru.org
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

import org.apache.commons.lang3.tuple.Pair;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.ElementHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.textbuilder.TextElementBuilder;

/**
 * This interface defines a check to be done on elements 
 */
public interface ElementChecker {

    /* The text element builder*/ 
    void setTextElementBuilder(TextElementBuilder textElementBuilder);
    
    /**
     * 
     * @return the textElementBuilder used to test the element
     */
    TextElementBuilder getTextElementBuilder();
    
    /* success solution pair : aggregation of solution and message*/
    Pair<TestSolution,String> getSuccessSolutionPair();
    
    /* success solution */
    TestSolution getSuccessSolution();
    
    /* success message code */
    String getSuccessMsgCode();
    
    /* failure solution pair : aggregation of solution and message*/
    Pair<TestSolution,String> getFailureSolutionPair();
    
    /* failure solution */
    TestSolution getFailureSolution();
    
    /* failure message code */
    String getFailureMsgCode();
    
    /**
     * Perform the check operation. The instance of {@link ElementHandler} 
     * received as a parameter is used to retrieve elements the test is about
     * and the instance of {@link TestSolutionHandler} received 
     * as a parameter is used to store the results of tests performed 
     * during the operation
     * 
     * @param sspHandler
     * @param elementHandler
     * @param testSolutionHandler
     *  
     */
    void check (
            SSPHandler sspHandler, 
            ElementHandler elementHandler, 
            TestSolutionHandler testSolutionHandler);
    
}