/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Tanaguru.org
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General License for more details.
 * 
 *  You should have received a copy of the GNU Affero General License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.ruleimplementation;

import java.util.Collection;
import org.tanaguru.entity.audit.TestSolution;

/**
 * Common interface to handle {link @TestSolution}
 * 
 * @author jkowalczyk
 */
public interface TestSolutionHandler {
    
    /**
     * Add a {link @TestSolution} to the collection
     * 
     * @param testSolution
     */
    void addTestSolution(TestSolution testSolution);
    
    /**
     * Add a collection of {link @TestSolution} to the collection
     * 
     * @param testSolutions
     */
    void addTestSolutions(Collection<TestSolution> testSolutions);
    
    /**
     * Clean the collection of {link @TestSolution}
     */
    void cleanTestSolutions();
    
    /**
     * 
     * @return the computed testSolution regarding the locale collection of
     *      testSolution
     */
    TestSolution getTestSolution();
    
}