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


import java.util.List;
import org.asqatasun.entity.audit.DefiniteResult;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.entity.subject.Site;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.service.ProcessRemarkService;

/**
 * <p>
 * This class should be overridden by concrete {@link RuleImplementation} 
 * classes which implement tests with site scope.
 * </p>
 * A no process rule implementation that returns NOT_APPLICABLE when the 
 * current audit is a page audit (one page tested) and NOT_TESTED instead. 
 */
public abstract class AbstractNotTestedSiteRuleImplementation 
            extends AbstractGroupRuleImplementation {

    private static final String MOCK_INDEFINITE_RESULT = "mock-result";
    
    /**
     * Default constructor
     */
    public AbstractNotTestedSiteRuleImplementation () {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        sspHandler.getProcessRemarkService().resetService();
        return processResultDataService.getIndefiniteResult(
                test, 
                sspHandler.getPage(), 
                MOCK_INDEFINITE_RESULT);
    }

    @Override
    protected DefiniteResult consolidateGroupImpl(
            Site group, 
            List<ProcessResult> groupedGrossResultList, 
            ProcessRemarkService processRemarkService) {
        return processResultDataService.getDefiniteResult(
                test, 
                group, 
                TestSolution.NOT_TESTED, 
                null);
    }

}