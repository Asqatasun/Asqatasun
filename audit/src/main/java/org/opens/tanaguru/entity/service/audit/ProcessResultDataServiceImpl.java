/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.entity.service.audit;

import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;
import java.util.Collection;
import java.util.List;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.dao.audit.ProcessResultDAO;
import org.opens.tanaguru.entity.reference.Scope;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author jkowalczyk
 */
public class ProcessResultDataServiceImpl extends AbstractGenericDataService<ProcessResult, Long> implements
        ProcessResultDataService {

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
    public List<? extends ProcessResult> getGrossResultFromAudit(Audit audit) {
        return ((ProcessResultDAO) entityDao).retrieveGrossResultFromAudit(audit);
    }

    @Override
    public List<? extends ProcessResult> getNetResultFromAudit(Audit audit) {
        return ((ProcessResultDAO) entityDao).retrieveNetResultFromAudit(audit);
    }

    @Override
    public List<? extends ProcessResult> getNetResultFromAuditAndWebResource(Audit audit, WebResource webResource) {
        return ((ProcessResultDAO) entityDao).retrieveNetResultFromAuditAndWebResource(audit, webResource);
    }
    
    @Override
    public List<? extends ProcessResult> getGrossResultFromAuditAndTest(Audit audit, Test test) {
        return ((ProcessResultDAO) entityDao).retrieveGrossResultFromAuditAndTest(audit, test);
    }

}