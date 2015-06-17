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

package org.tanaguru.rules.test;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.SourceCodeRemark;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;
import org.tanaguru.entity.service.audit.ProcessRemarkDataService;
import org.tanaguru.sdk.entity.dao.GenericDAO;
import org.tanaguru.sdk.entity.factory.GenericFactory;

/**
 *
 * @author jkowalczyk
 */
public class ProcessRemarkDataServiceMock implements ProcessRemarkDataService{

    private SourceCodeRemarkFactory sourceCodeRemarkFactory;
    @Autowired
    public void setSourceCodeRemarkFactory(SourceCodeRemarkFactory sourceCodeRemarkFactory) {
        this.sourceCodeRemarkFactory = sourceCodeRemarkFactory;
    }
    
    private GenericFactory<ProcessRemark> processRemarkFactory;
    
    @Override
    public Collection<ProcessRemark> findProcessRemarksFromProcessResult(ProcessResult processResult, int limit) {
        return processResult.getRemarkSet();
    }

    @Override
    public Collection<ProcessRemark> findProcessRemarksFromProcessResultAndTestSolution(ProcessResult processResult, TestSolution testSolution, int limit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int findNumberOfProcessRemarksFromProcessResultAndTestSolution(ProcessResult processResult, TestSolution testSolution) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ProcessRemark create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create(ProcessRemark entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(ProcessRemark entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Collection<ProcessRemark> entitySet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<ProcessRemark> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ProcessRemark read(Long key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ProcessRemark saveOrUpdate(ProcessRemark entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<ProcessRemark> saveOrUpdate(Collection<ProcessRemark> entitySet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityDao(GenericDAO<ProcessRemark, Long> dao) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityFactory(GenericFactory<ProcessRemark> factory) {
        this.processRemarkFactory = factory;
    }

    @Override
    public ProcessRemark update(ProcessRemark entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ProcessRemark getProcessRemark(TestSolution issue, String messageCode) {
        ProcessRemark pr = processRemarkFactory.create();
        pr.setIssue(issue);
        pr.setMessageCode(messageCode);
        return pr;
    }

    @Override
    public SourceCodeRemark getSourceCodeRemark() {
        return sourceCodeRemarkFactory.create();
    }

    @Override
    public SourceCodeRemark getSourceCodeRemark(TestSolution issue, String messageCode) {
        return sourceCodeRemarkFactory.create(issue, messageCode);
    }

    @Override
    public SourceCodeRemark getSourceCodeRemark(String target, TestSolution issue, String messageCode, int lineNumber) {
        return sourceCodeRemarkFactory.create(target, issue, messageCode, lineNumber);
    }

}