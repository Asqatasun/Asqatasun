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
package org.opens.tanaguru.entity.service.audit;

import java.util.Collection;
import java.util.List;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.IndefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.dao.audit.ProcessResultDAO;
import org.opens.tanaguru.entity.factory.audit.DefiniteResultFactory;
import org.opens.tanaguru.entity.factory.audit.IndefiniteResultFactory;
import org.opens.tanaguru.entity.reference.Scope;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author jkowalczyk
 */
public class ProcessResultDataServiceImpl extends AbstractGenericDataService<ProcessResult, Long> implements
        ProcessResultDataService {

    private DefiniteResultFactory definiteResultFactory;
    @Autowired
    public void setDefiniteResultFactory(DefiniteResultFactory definiteResultFactory) {
        this.definiteResultFactory = definiteResultFactory;
    }
    
    private IndefiniteResultFactory indefiniteResultFactory;
    @Autowired
    public void setIndefiniteResultFactory(IndefiniteResultFactory indefiniteResultFactory) {
        this.indefiniteResultFactory = indefiniteResultFactory;
    }
    
    @Override
    public int getResultByThemeCount(WebResource webresource, TestSolution testSolution, Theme theme) {
        return ((ProcessResultDAO) entityDao).
                getResultByThemeCount(webresource, testSolution, theme);
    }

    @Override
    public Collection<ProcessResult> getResultByScopeList(WebResource webresource, Scope scope) {
        return ((ProcessResultDAO) entityDao).
                getResultByScopeList(webresource, scope);
    }
    
    @Override
    public Long getNumberOfGrossResultFromAudit(Audit audit) {
        return ((ProcessResultDAO) entityDao).retrieveNumberOfGrossResultFromAudit(audit);
    }

    @Override
    public Long getNumberOfNetResultFromAudit(Audit audit) {
        return ((ProcessResultDAO) entityDao).retrieveNumberOfNetResultFromAudit(audit);
    }

    @Override
    public Collection<ProcessResult> getGrossResultFromAudit(Audit audit) {
        return ((ProcessResultDAO) entityDao).retrieveGrossResultFromAudit(audit);
    }

    @Override
    public Collection<ProcessResult> getNetResultFromAudit(Audit audit) {
        return ((ProcessResultDAO) entityDao).retrieveNetResultFromAudit(audit);
    }

    @Override
    public Collection<ProcessResult> getNetResultFromAuditAndWebResource(Audit audit, WebResource webResource) {
        return ((ProcessResultDAO) entityDao).retrieveNetResultFromAuditAndWebResource(audit, webResource);
    }
    
    @Override
    public Collection<ProcessResult> getGrossResultFromAuditAndTest(Audit audit, Test test) {
        return ((ProcessResultDAO) entityDao).retrieveGrossResultFromAuditAndTest(audit, test);
    }
    
    @Override
    public void cleanUpIndefiniteResultFromAudit(Audit audit) {
        ((ProcessResultDAO) entityDao).deleteIndefiniteResultFromAudit(audit);
    }

    @Override
    public Collection<ProcessResult> getIndefiniteResultFromAudit(Audit audit) {
        return ((ProcessResultDAO) entityDao).retrieveIndefiniteResultFromAudit(audit);
    }

    @Override
    public List<DefiniteResult> getHistoyChanges(ProcessResult processResult) {
        return ((ProcessResultDAO) entityDao).getHistoryChanges(processResult);
    }

    @Override
    public DefiniteResult getDefiniteResult(Test test, WebResource subject) {
        return definiteResultFactory.create(test, subject);
    }

    @Override
    public DefiniteResult getDefiniteResult(Test test, WebResource subject, TestSolution value, Collection<ProcessRemark> remarkSet) {
        return definiteResultFactory.create(test, subject, value, remarkSet);
    }

    @Override
    public DefiniteResult getDefiniteResult(Test test, WebResource subject, TestSolution value, int elementCounter) {
        return definiteResultFactory.create(test, subject, value, elementCounter);
    }

    @Override
    public DefiniteResult getDefiniteResult(Test test, WebResource subject, TestSolution value, int elementCounter, Collection<ProcessRemark> remarkSet) {
        return definiteResultFactory.create(test, subject, value, elementCounter, remarkSet);
    }

    @Override
    public IndefiniteResult getIndefiniteResult(Test test, WebResource subject, String value) {
        return indefiniteResultFactory.create(test, subject, value);
    }

    @Override
    public IndefiniteResult getIndefiniteResult(Test test, WebResource subject, String value, Collection<ProcessRemark> remarkList) {
        return indefiniteResultFactory.create(test, subject, value, remarkList);
    }

}