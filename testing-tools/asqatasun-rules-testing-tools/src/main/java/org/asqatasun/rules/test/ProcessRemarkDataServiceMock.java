/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2020  Asqatasun.org
 * 
 *  This file is part of Asqatasun.
 * 
 *  Asqatasun is free software: you can redistribute it and/or modify
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
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */

package org.asqatasun.rules.test;

import org.asqatasun.entity.audit.ProcessRemark;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.SourceCodeRemark;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.entity.audit.factory.SourceCodeRemarkFactory;
import org.asqatasun.entity.service.audit.ProcessRemarkDataService;
import org.asqatasun.entity.dao.GenericDAO;
import org.asqatasun.entity.GenericFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 *
 * @author jkowalczyk
 */
@Profile("test")
@Component
public class ProcessRemarkDataServiceMock implements ProcessRemarkDataService{

    private final SourceCodeRemarkFactory sourceCodeRemarkFactory;
    private GenericFactory<ProcessRemark> processRemarkFactory;

    public ProcessRemarkDataServiceMock(
        SourceCodeRemarkFactory sourceCodeRemarkFactory,
        GenericFactory <ProcessRemark> processRemarkFactory) {
        this.sourceCodeRemarkFactory = sourceCodeRemarkFactory;
        this.processRemarkFactory = processRemarkFactory;
    }

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
