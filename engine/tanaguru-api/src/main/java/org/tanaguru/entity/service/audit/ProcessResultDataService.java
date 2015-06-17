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
import java.util.List;

import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.DefiniteResult;
import org.tanaguru.entity.audit.IndefiniteResult;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.reference.Criterion;
import org.tanaguru.entity.reference.Scope;
import org.tanaguru.entity.reference.Test;
import org.tanaguru.entity.reference.Theme;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.sdk.entity.service.GenericDataService;

/**
 * 
 * @author jkowalczyk
 */
public interface ProcessResultDataService extends
		GenericDataService<ProcessResult, Long> {

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
    Long getNumberOfGrossResultFromAudit(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    Long getNumberOfNetResultFromAudit(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    Collection<ProcessResult> getGrossResultFromAudit(Audit audit);

    /**
     *
     * @param audit
     * @param test
     * @return
     */
    Collection<ProcessResult> getGrossResultFromAuditAndTest(Audit audit, Test test);

    /**
     *
     * @param audit
     * @return
     */
    Collection<ProcessResult> getNetResultFromAudit(Audit audit);

    /**
     * 
     * @param audit
     * @param webResource
     * @return
     */
    Collection<ProcessResult> getNetResultFromAuditAndWebResource(Audit audit, WebResource webResource);
    
    /**
     * 
     * @param audit
     */
    void cleanUpIndefiniteResultFromAudit(Audit audit);
    
    /**
     * 
     * @param audit
     * @return the list of IndefiniteProcessResult for a given audit
     */
    Collection<ProcessResult> getIndefiniteResultFromAudit(Audit audit);

    
    /**
     * Return the history changes for the given processResult
     * @param processResult 
     * @return The list of changes
     */
    List<DefiniteResult> getHistoyChanges(ProcessResult processResult);

    /**
     * 
     * @param test
     * @param subject
     * @return 
     */
    DefiniteResult getDefiniteResult(Test test, WebResource subject);
    
    /**
     * 
     * @param test
     * @param solution
     * @return 
     */
    DefiniteResult getDefiniteResult(Test test, TestSolution solution);

    /**
     * 
     * @param test
     * @param subject
     * @param solution
     * @param remarkSet
     * @return 
     */
    DefiniteResult getDefiniteResult(
            Test test, 
            WebResource subject,
            TestSolution solution, 
            Collection<ProcessRemark> remarkSet);

    /**
     * 
     * @param test
     * @param subject
     * @param solution
     * @param elementCounter
     * @return 
     */
    DefiniteResult getDefiniteResult(
            Test test, 
            WebResource subject, 
            TestSolution solution, 
            int elementCounter);
    
    /**
     * 
     * @param test
     * @param subject
     * @param value
     * @param elementCounter
     * @param remarkSet
     * @return 
     */
    DefiniteResult getDefiniteResult(
            Test test, 
            WebResource subject, 
            TestSolution value, 
            int elementCounter, 
            Collection<ProcessRemark> remarkSet);

    /**
     * 
     * @param test
     * @param subject
     * @param value
     * @return 
     */
    IndefiniteResult getIndefiniteResult(Test test, WebResource subject, String value);
    
    /**
     * 
     * @param test
     * @param subject
     * @param value
     * @param remarkList
     * @return 
     */
    IndefiniteResult getIndefiniteResult(
            Test test, 
            WebResource subject,
            String value, 
            Collection<ProcessRemark> remarkList);
    
    /**
     * 
     * @param webResource
     * @param scope
     * @return 
     */
    Collection<ProcessResult> getProcessResultListByWebResourceAndScope(WebResource webResource, Scope scope);
    
    /**
     * 
     * @param webResource
     * @param criterion
     * @return 
     */
    Collection<ProcessResult> getProcessResultListByWebResourceAndCriterion(WebResource webResource, Criterion criterion);
    
    /**
     *
     * @param webResource
     * @param test
     * @return
     */
    Collection<ProcessResult> getProcessResultListByWebResourceAndTest(
            WebResource webResource,
            Test test);
    
    /**
     * 
     * @param webResource
     * @param scope
     * @param theme
     * @param testSolutionList
     * @return 
     */
    Collection<ProcessResult> getProcessResultListByWebResourceAndScope(
            WebResource webResource,
            Scope scope,
            String theme,
            Collection<String> testSolutionList);
    
    /**
     * 
     * @param webResource
     * @param scope
     * @return 
     */
    boolean hasAuditSiteScopeResult(WebResource webResource, Scope scope);
}