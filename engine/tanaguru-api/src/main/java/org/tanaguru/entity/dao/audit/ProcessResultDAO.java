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
import java.util.List;

import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.DefiniteResult;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.reference.Criterion;
import org.tanaguru.entity.reference.Scope;
import org.tanaguru.entity.reference.Test;
import org.tanaguru.entity.reference.Theme;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.sdk.entity.dao.GenericDAO;

/**
 * 
 * @author jkowalczyk
 */
public interface ProcessResultDAO extends GenericDAO<ProcessResult, Long> {

    /**
     * This method returns the number of elements for a given resource,
     * testSolution and theme
     * @param webresource
     * @param testSolution
     * @param theme
     * @return
     */
    int getResultByThemeCount(
            WebResource webresource,
            TestSolution testSolution,
            Theme theme);

    /**
     * This method returns all the process result for a given resource and
     * for a given scope (Site or Page)
     * @param webresource
     * @param scope
     * @return
     */
    Collection<ProcessResult> getResultByScopeList(
            WebResource webresource,
            Scope scope);

    /**
     * 
     * @param audit
     * @return
     */
    Long retrieveNumberOfGrossResultFromAudit(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    Long retrieveNumberOfNetResultFromAudit(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    Collection<ProcessResult> retrieveGrossResultFromAudit(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    Collection<ProcessResult> retrieveNetResultFromAudit(Audit audit);

    /**
     * 
     * @param audit
     * @param webResource
     * @return
     */
    Collection<ProcessResult> retrieveNetResultFromAuditAndWebResource(
            Audit audit,
            WebResource webResource);

    /**
     *
     * @param audit
     * @param test
     * @return
     */
    Collection<ProcessResult> retrieveGrossResultFromAuditAndTest(
            Audit audit,
            Test test);
    
    /**
     * 
     * @param audit 
     */
    void deleteIndefiniteResultFromAudit(Audit audit);
    
    /**
     * 
     * @param audit
     * @return the collection of indefiniteResults for a given audit
     */
    Collection<ProcessResult> retrieveIndefiniteResultFromAudit(Audit audit);
    
    
    /**
     * 
     * @param processResultImpl
     * @return 
     */
    List<DefiniteResult> getHistoryChanges (ProcessResult processResultImpl);

    /**
     * 
     * @param webResource
     * @param scope
     * @return 
     */
    Collection<ProcessResult> retrieveProcessResultListByWebResourceAndScope(WebResource webResource, Scope scope);
    
    /**
     * 
     * @param webResource
     * @param criterion
     * @return 
     */
    Collection<ProcessResult> retrieveProcessResultListByWebResourceAndCriterion(WebResource webResource, Criterion criterion);
    
    /**
     * 
     * @param webResource
     * @param scope
     * @param theme
     * @param testSolutionList
     * @return 
     */
    Collection<ProcessResult> retrieveProcessResultListByWebResourceAndScope(
            WebResource webResource,
            Scope scope,
            String theme,
            Collection<String> testSolutionList);
    
    /**
     * 
     * @param webResource
     * @param test
     * @return 
     */
    Collection<ProcessResult> retrieveProcessResultListByWebResourceAndTest(
            WebResource webResource, 
            Test test);
    
    /**
     * 
     * @param webResource
     * @param scope
     * @return 
     */
    boolean hasAuditSiteScopeResult(WebResource webResource, Scope scope);
}