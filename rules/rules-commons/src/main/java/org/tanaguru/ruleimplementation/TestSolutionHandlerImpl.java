/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015 Tanaguru.org
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
package org.tanaguru.ruleimplementation;

import java.util.ArrayList;
import java.util.Collection;
import org.tanaguru.entity.audit.TestSolution;

/**
 * Default implementation of the {@link TestSolutionHandler} interface.
 * It enables to collect the {@link TestSolution} of unitary checks and then 
 * provide a synthesized {@link TestSolution}.
 * 
 */
public class TestSolutionHandlerImpl implements TestSolutionHandler {

    /**
     * The testSolution of the elementary checks used to compute the final
     * result of the test
     */
    private Collection<TestSolution> testSolutions = new ArrayList<>();
    
    /**
     * Default constructor
     */
    public TestSolutionHandlerImpl(){}

    /**
     * {@inheritDoc}
     * @param testSolution
     */
    @Override
    public void addTestSolution(TestSolution testSolution) {
        testSolutions.add(testSolution);
    }
    
    /**
     * {@inheritDoc}
     * @param testSolutions
     */
    @Override
    public void addTestSolutions(Collection<TestSolution> testSolutions) {
        this.testSolutions.addAll(testSolutions);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void cleanTestSolutions() {
         testSolutions.clear();
    }

    /**
     * {@inheritDoc}
     * @return the TestSolution computed from all the unitary TestSolutions
     */
    @Override
    public TestSolution getTestSolution() {
        return RuleHelper.synthesizeTestSolutionCollection(testSolutions);
    }
    
}