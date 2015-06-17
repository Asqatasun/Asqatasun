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
package org.tanaguru.entity.dao.audit;

import java.util.Collection;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.sdk.entity.dao.GenericDAO;

/**
 * 
 * @author jkowalczyk
 */
public interface ProcessRemarkDAO extends GenericDAO<ProcessRemark, Long> {

    /**
     * 
     * @param processResult
     * @param limit
     * @return 
     */
    Collection<ProcessRemark> retrieveProcessRemarksFromProcessResult(
            ProcessResult processResult, 
            int limit);

    /**
     * 
     * @param processResult
     * @param testSolution
     * @param limit
     * @return 
     */
    Collection<ProcessRemark> retrieveProcessRemarksFromProcessResultAndTestSolution(
            ProcessResult processResult, 
            TestSolution testSolution, 
            int limit);
    
    /**
     * 
     * @param processResult
     * @param testSolution
     * @return 
     */
    int countProcessRemarksFromProcessResultAndTestSolution(
            ProcessResult processResult, 
            TestSolution testSolution);
    
}