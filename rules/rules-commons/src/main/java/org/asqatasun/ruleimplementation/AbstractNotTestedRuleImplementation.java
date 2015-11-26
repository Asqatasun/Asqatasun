/*
 * Asqatasun - Automated webpage assessment
  * Copyright (C) 2008-2015  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.ruleimplementation;


import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;

/**
 * <p>
 * This class should be overridden by concrete {@link RuleImplementation} 
 * classes which implement tests with page scope.
 * </p>
 * A no process rule implementation that returns NOT_TESTED as test result. 
 */
public abstract class AbstractNotTestedRuleImplementation 
            extends AbstractPageRuleMarkupImplementation {

    /**
     * Default constructor
     */
    public AbstractNotTestedRuleImplementation () {
        super();
    }
    
    @Override
    protected void select(SSPHandler sspHandler) {
        // DO NOTHING
    }
    
    @Override
    protected void check(SSPHandler sspHandler,
                         TestSolutionHandler testSolutionHandler) {
        testSolutionHandler.addTestSolution(TestSolution.NOT_TESTED);
    }

    @Override
    public int getSelectionSize() {
        return 0;
    }

}