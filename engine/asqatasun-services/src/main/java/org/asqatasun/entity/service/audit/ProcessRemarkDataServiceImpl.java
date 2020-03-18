/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
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
import org.asqatasun.entity.audit.factory.ProcessRemarkFactory;
import org.asqatasun.entity.audit.factory.SourceCodeRemarkFactory;
import org.asqatasun.entity.dao.audit.ProcessRemarkDAO;
import org.asqatasun.entity.service.AbstractGenericDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * 
 * @author jkowalczyk
 */
@Service("processRemarkDataService")
@Profile("!test")
public class ProcessRemarkDataServiceImpl extends AbstractGenericDataService<ProcessRemark, Long> implements
        ProcessRemarkDataService {

    private final SourceCodeRemarkFactory sourceCodeRemarkFactory;
    protected final ProcessRemarkFactory entityFactory;

    public ProcessRemarkDataServiceImpl(
        @Qualifier("sourceCodeRemarkFactory") SourceCodeRemarkFactory sourceCodeRemarkFactory,
        @Qualifier("processRemarkFactory") ProcessRemarkFactory entityFactory) {
        this.sourceCodeRemarkFactory = sourceCodeRemarkFactory;
        this.entityFactory = entityFactory;
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
        return entityFactory.create(issue, messageCode);
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
