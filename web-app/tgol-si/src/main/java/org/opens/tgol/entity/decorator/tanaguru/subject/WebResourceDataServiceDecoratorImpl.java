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
import java.util.List;
import org.displaytag.properties.SortOrderEnum;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.entity.reference.Scope;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;
import org.opens.tgol.entity.dao.statistics.StatisticsDAO;
import org.opens.tgol.entity.dao.tanaguru.subject.TgolWebResourceDAO;
import org.opens.tgol.presentation.data.FailedPageInfo;
import org.opens.tgol.presentation.data.FailedTestInfo;
import org.opens.tgol.presentation.data.FailedThemeInfo;
import org.opens.tgol.presentation.data.PageResult;
import org.opens.tgol.util.HttpStatusCodeFamily;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class WebResourceDataServiceDecoratorImpl extends AbstractGenericDataService<WebResource, Long>
        implements WebResourceDataServiceDecorator{

    private WebResourceDataService decoratedWebResourceDataService; // the WebResourceDataService instance being decorated
    private StatisticsDAO statisticsDAO;

    @Autowired
    public WebResourceDataServiceDecoratorImpl (
            WebResourceDataService decoratedWebResourceDataService,
            StatisticsDAO statisticsDAO) {
        this.decoratedWebResourceDataService = decoratedWebResourceDataService;
        this.statisticsDAO = statisticsDAO;
    }

    @Override
    public Long getParentWebResourceId(Long webresourceId) {
        return ((TgolWebResourceDAO)entityDao).retrieveParentWebResourceId(webresourceId);
    }

    @Override
    public Page createPage(String pageURL) {
        return decoratedWebResourceDataService.createPage(pageURL);
    }

    @Override
    public Site createSite(String siteURL) {
        return decoratedWebResourceDataService.createSite(siteURL);
    }

    @Override
    public WebResource getByUrl(String url) {
        return decoratedWebResourceDataService.getByUrl(url);
    }

    @Override
    public WebResource read(Long key) {
        return decoratedWebResourceDataService.read(key);
    }

    @Override
    public Collection<FailedPageInfo> getFailedWebResourceSortedByTest(
            WebResource webResource,
            Audit audit,
            int nbOfResult) {
        return statisticsDAO.findFailedWebResourceSortedByTest(
                    webResource, 
                    audit,
                    nbOfResult);
    }

    @Override
    public Collection<FailedPageInfo> getFailedWebResourceSortedByOccurrence(
            WebResource webResource,
            Audit audit,
            int nbOfResult) {
        return statisticsDAO.findFailedWebResourceSortedByOccurrence(
                    webResource, 
                    audit,
                    nbOfResult);
    }

    @Override
    public Collection<FailedTestInfo> getFailedTestByOccurrence(
            WebResource webResource,
            Audit audit,
            int nbOfResult) {
        return statisticsDAO.findFailedTestByOccurrence(
                    webResource,
                    audit, 
                    nbOfResult);
    }

    @Override
    public WebResource ligthRead(Long webResourceId) {
        return ((TgolWebResourceDAO)entityDao).ligthRead(webResourceId);
    }

    @Override
    public WebResource deepRead(Long webResourceId) {
        return this.read(webResourceId);
    }

    @Override
    public Long getResultCountByResultType(
            WebResource webResource,
            Audit audit, 
            TestSolution testSolution) {
        return statisticsDAO.findResultCountByResultType(
                webResource,
                audit, 
                testSolution);
    }

    @Override
    public Collection<FailedThemeInfo> getResultCountByResultTypeAndTheme(
            WebResource webResource,
            Audit audit,
            TestSolution testSolution,
            int nb0fResult) {
        return statisticsDAO.findResultCountByResultTypeAndTheme(
                webResource,
                audit, 
                testSolution,
                nb0fResult);
    }

    @Override
    public Long getChildWebResourceCount(WebResource parentWebresource) {
        return ((TgolWebResourceDAO)entityDao).retrieveChildWebResourceCount(
                parentWebresource);
    }

    @Override
    public Collection<ProcessResult> getProcessResultListByWebResourceAndScope(WebResource webResource, Scope scope) {
        return ((TgolWebResourceDAO)entityDao).
                retrieveProcessResultListByWebResourceAndScope(webResource, scope);
    }
    
    @Override
    public Collection<ProcessResult> getProcessResultListByWebResourceAndCriterion(WebResource webResource, Criterion criterion) {
        return ((TgolWebResourceDAO)entityDao).
                retrieveProcessResultListByWebResourceAndCriterion(webResource, criterion);
    }

    @Override
    public Collection<ProcessResult> getProcessResultListByWebResourceAndScope(
            WebResource webResource,
            Scope scope,
            String theme,
            Collection<String> testSolutionList) {
        return ((TgolWebResourceDAO)entityDao).
                retrieveProcessResultListByWebResourceAndScope(
                    webResource,
                    scope,
                    theme,
                    testSolutionList);
    }

    @Override
    public Long getResultCountByResultTypeAndTheme(
            WebResource webResource,
            Audit audit,
            TestSolution testSolution,
            Theme theme,
            boolean manualAudit) {
        return statisticsDAO.findResultCountByResultTypeAndTheme(
            webResource,audit,testSolution,theme, manualAudit);
    }

    @Override
    public Collection<PageResult> getChildUrlList(
            WebResource webResource,
            Audit audit) {
        List<PageResult> pageResultList = (List<PageResult>)
                ((TgolWebResourceDAO)entityDao).retrieveChildUrlList(webResource);
        for (PageResult pageResult : pageResultList) {
            pageResult.setWeightedMark(String.valueOf(getMarkByWebResourceAndAudit(pageResult.getId(), false , false).intValue()));
            pageResult.setRawMark(String.valueOf(getMarkByWebResourceAndAudit(pageResult.getId(), true, false).intValue()));
        }
        return pageResultList;
    }

    @Override
    public boolean hasAuditSiteScopeTest(WebResource webResource, Scope scope) {
        return ((TgolWebResourceDAO)entityDao).hasAuditSiteScopeTest(webResource, scope);
    }

    @Override
    public WebResource getByUrlAndParentWebResource(String string, WebResource wr) {
        return decoratedWebResourceDataService.getByUrlAndParentWebResource(string, wr);
    }

    @Override
    public List<WebResource> getWebResourceFromItsParent(WebResource wr, int i, int i1) {
        return decoratedWebResourceDataService.getWebResourceFromItsParent(wr, i, i1);
    }

    @Override
    public Long getNumberOfChildWebResource(WebResource wr) {
        return decoratedWebResourceDataService.getNumberOfChildWebResource(wr);
    }

    @Override
    public Float getMarkByWebResourceAndAudit(
            WebResource webResource,
            boolean isRawMark,
            boolean isManual) {
        return this.getMarkByWebResourceAndAudit(webResource.getId(), isRawMark, isManual);
    }

    /**
     * 
     * @param idWebResource
     * @param audit
     * @return
     */
    private Float getMarkByWebResourceAndAudit(
            Long idWebResource,
            boolean isRawMark,
            boolean isManual) {
        if (isRawMark) {
            return statisticsDAO.findRawMarkByWebResourceAndAudit(idWebResource, isManual);
        } else {
            return statisticsDAO.findWeightedMarkByWebResourceAndAudit(idWebResource, isManual);
        }
    }

    @Override
    public Long getWebResourceCountByAuditAndHttpStatusCode(
            Long idAudit, 
            HttpStatusCodeFamily httpStatusCode, 
            String invalidTestLabel,
            String containingValue) {
        return statisticsDAO.findWebResourceCountByAuditAndHttpStatusCode(
                idAudit,
                httpStatusCode,
                invalidTestLabel,
                containingValue);
    }

    @Override
    public Collection<PageResult> getWebResourceListByAuditAndHttpStatusCode(
            Long idAudit,
            HttpStatusCodeFamily httpStatusCode,
            String invalidTestLabel,
            int nbOfElements,
            int window,
            SortOrderEnum sortDirection,
            String sortCriterion,
            String containingValue) {
        return statisticsDAO.findWebResourceByAuditAndHttpStatusCode(
                idAudit,
                httpStatusCode,
                invalidTestLabel,
                nbOfElements,
                window,
                sortDirection,
                sortCriterion,
                containingValue);
    }

    @Override
    public Collection<ProcessResult> getProcessResultListByWebResourceAndTest(WebResource webResource, Test test) {
        return ((TgolWebResourceDAO)entityDao).
                retrieveProcessResultListByWebResourceAndTest(webResource, test);
    }

}