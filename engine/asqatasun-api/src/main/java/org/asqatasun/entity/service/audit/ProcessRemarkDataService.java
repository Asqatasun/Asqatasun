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
package org.asqatasun.entity.service.audit;

import java.util.Collection;
import org.asqatasun.entity.audit.ProcessRemark;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.SourceCodeRemark;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.sdk.entity.service.GenericDataService;

/**
 * 
 * @author jkowalczyk
 */
public interface ProcessRemarkDataService extends
		GenericDataService<ProcessRemark, Long> {

    /**
     * 
     * @param processResult
     * @param limit
     * @return a collection of ProcessRemark
     */
    Collection<ProcessRemark> findProcessRemarksFromProcessResult(
            ProcessResult processResult, 
            int limit);

    /**
     * 
     * @param processResult
     * @param testSolution
     * @param limit
     * @return a collection of ProcessRemark
     */
    Collection<ProcessRemark> findProcessRemarksFromProcessResultAndTestSolution(
            ProcessResult processResult, 
            TestSolution testSolution,
            int limit);
    
    /**
     * 
     * @param processResult
     * @param testSolution
     * @return the number of processRemark regarding the parameters
     */
    int findNumberOfProcessRemarksFromProcessResultAndTestSolution(
            ProcessResult processResult, 
            TestSolution testSolution);
    
    /**
     *
     * @param issue
     * @param messageCode
     * @return
     */
    ProcessRemark getProcessRemark(TestSolution issue, String messageCode);
    
    /**
     * 
     * @return 
     */
    SourceCodeRemark getSourceCodeRemark();
    
    /**
     * 
     * @param issue
     * @param messageCode
     * @return 
     */
    SourceCodeRemark getSourceCodeRemark(TestSolution issue, String messageCode);
    
    /**
     * 
     * @param target
     * @param issue
     * @param messageCode
     * @param lineNumber
     * @return 
     */
    SourceCodeRemark getSourceCodeRemark(String target, TestSolution issue, String messageCode, int lineNumber);
    
}