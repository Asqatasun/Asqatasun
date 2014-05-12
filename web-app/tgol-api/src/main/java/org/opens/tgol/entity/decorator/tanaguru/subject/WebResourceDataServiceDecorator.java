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
package org.opens.tgol.entity.decorator.tanaguru.subject;

import java.util.Collection;
import org.displaytag.properties.SortOrderEnum;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.entity.reference.Scope;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tgol.presentation.data.FailedPageInfo;
import org.opens.tgol.presentation.data.FailedTestInfo;
import org.opens.tgol.presentation.data.FailedThemeInfo;
import org.opens.tgol.presentation.data.PageResult;
import org.opens.tgol.util.HttpStatusCodeFamily;

/**
 * This interface decorates the WebResourceDataService interface defined in
 * the tanaguru API and adds more primitives.
 * 
 * @author jkowalczyk
 */
public interface WebResourceDataServiceDecorator extends WebResourceDataService {

    /**
     *
     * @param webresourceId
     * @return
     *          the id of the parent webresource for a given webresource.
     */
    Long getParentWebResourceId(Long webresourceId);

    /**
     * 
     * @param webResource
     * @param audit
     * @param nbOfResult
     * @return 
     */
    Collection<FailedPageInfo> getFailedWebResourceSortedByTest(
            WebResource webResource, 
            Audit audit,
            int nbOfResult);

    /**
     * 
     * @param webResource
     * @param audit
     * @param nbOfResult
     * @return 
     */
    Collection<FailedPageInfo> getFailedWebResourceSortedByOccurrence(
            WebResource webResource, 
            Audit audit,
            int nbOfResult);

    /**
     * 
     * @param webResource
     * @param audit
     * @param nbOfResult
     * @return 
     */
    Collection<FailedTestInfo> getFailedTestByOccurrence(
            WebResource webResource, 
            Audit audit,
            int nbOfResult);

    /**
     * 
     * @param webResourceId
     * @return 
     */
    WebResource ligthRead(Long webResourceId);

    /**
     *
     * @param webResourceId
     * @return
     */
    WebResource deepRead(Long webResourceId);

    /**
     * 
     * @param webResource
     * @param audit
     * @param testSolution
     * @return 
     */
    Long getResultCountByResultType(
            WebResource webResource,
            Audit audit,
            TestSolution testSolution);

    /**
     * 
     * @param webResource
     * @param audit
     * @param testSolution
     * @param nb0fResult
     * @return 
     */
    Collection<FailedThemeInfo> getResultCountByResultTypeAndTheme(
            WebResource webResource,
            Audit audit,
            TestSolution testSolution,
            int nb0fResult);

    /**
     * 
     * @param webresourceId
     * @return 
     */
    Long getChildWebResourceCount(WebResource webresourceId);

    /**
     * 
     * @param webResource
     * @param scope
     * @return 
     */
    Collection<ProcessResult> getProcessResultListByWebResourceAndScope(
            WebResource webResource,
            Scope scope);
    
    /**
     *
     * @param webResource
     * @param criterion
     * @return
     */
    Collection<ProcessResult> getProcessResultListByWebResourceAndCriterion(
            WebResource webResource,
            Criterion criterion);
    
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
     * @param audit
     * @param testSolution
     * @param theme
     * @return 
     */
    Long getResultCountByResultTypeAndTheme(
            WebResource webResource,
            Audit audit,
            TestSolution testSolution,
            Theme theme,
            boolean manualAudit
            );

    /**
     * 
     * @param webResource
     * @param audit
     * @return 
     */
    Collection<PageResult> getChildUrlList(
            WebResource webResource,
            Audit audit);

    /**
     * 
     * @param webResource
     * @param scope
     * @return 
     */
    boolean hasAuditSiteScopeTest(WebResource webResource, Scope scope);

    /**
     *
     * @param webResource
     * @param isRawMark
     * @param isManual
     * @return
     */
    Float getMarkByWebResourceAndAudit(WebResource webResource, boolean isRawMark, boolean isManual);
    
    /**
     * 
     * @param idAudit
     * @param httpStatusCode
     * @param invalidTestLabel
     * @param containingValue
     * @return 
     */
    Long getWebResourceCountByAuditAndHttpStatusCode(
            Long idAudit,
            HttpStatusCodeFamily httpStatusCode,
            String invalidTestLabel,
            String containingValue);
    
    /**
     * 
     * @param idAudit
     * @param httpStatusCode
     * @param invalidTestLabel
     * @param nbOfElements
     * @param window
     * @param sortDirection
     * @param sortCriterion
     * @param containingValue
     * @return 
     */
    Collection<PageResult> getWebResourceListByAuditAndHttpStatusCode(
            Long idAudit,
            HttpStatusCodeFamily httpStatusCode,
            String invalidTestLabel,
            int nbOfElements,
            int window,
            SortOrderEnum sortDirection,
            String sortCriterion,
            String containingValue);
}
