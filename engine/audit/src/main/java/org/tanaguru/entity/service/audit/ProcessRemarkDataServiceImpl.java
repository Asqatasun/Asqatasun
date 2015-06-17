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
package org.tanaguru.entity.service.audit;

import java.util.Collection;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.SourceCodeRemark;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.dao.audit.ProcessRemarkDAO;
import org.tanaguru.entity.factory.audit.ProcessRemarkFactory;
import org.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;
import org.tanaguru.sdk.entity.service.AbstractGenericDataService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author jkowalczyk
 */
public class ProcessRemarkDataServiceImpl extends AbstractGenericDataService<ProcessRemark, Long> implements
        ProcessRemarkDataService {

    private SourceCodeRemarkFactory sourceCodeRemarkFactory;
    @Autowired
    public void setSourceCodeRemarkFactory(SourceCodeRemarkFactory sourceCodeRemarkFactory) {
        this.sourceCodeRemarkFactory = sourceCodeRemarkFactory;
    }
    
    @Override
    public Collection<ProcessRemark> findProcessRemarksFromProcessResult(
            ProcessResult processResult, 
            int limit) {
        return ((ProcessRemarkDAO) entityDao).
                retrieveProcessRemarksFromProcessResult(processResult, limit);
    }
    
    @Override
    public Collection<ProcessRemark> findProcessRemarksFromProcessResultAndTestSolution(
            ProcessResult processResult, 
            TestSolution testSolution, 
            int limit) {
        return ((ProcessRemarkDAO) entityDao).
                retrieveProcessRemarksFromProcessResultAndTestSolution(processResult, testSolution, limit);
    }

    @Override
    public int findNumberOfProcessRemarksFromProcessResultAndTestSolution(
            ProcessResult processResult, 
            TestSolution testSolution) {
        return ((ProcessRemarkDAO) entityDao).
                countProcessRemarksFromProcessResultAndTestSolution(processResult, testSolution);
    }

    @Override
    public ProcessRemark getProcessRemark(TestSolution issue, String messageCode) {
        return ((ProcessRemarkFactory) entityFactory).create(issue, messageCode);
    }
    
    @Override
    public SourceCodeRemark getSourceCodeRemark(TestSolution issue, String messageCode) {
        return sourceCodeRemarkFactory.create(issue, messageCode);
    }

    @Override
    public SourceCodeRemark getSourceCodeRemark() {
        return sourceCodeRemarkFactory.create();
    }
    
    @Override
    public SourceCodeRemark getSourceCodeRemark(String target, TestSolution issue, String messageCode, int lineNumber) {
        return sourceCodeRemarkFactory.create(target, issue, messageCode, lineNumber);
    }
    
}
