/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2012  Open-S Company
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditImpl;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Scope;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.subject.*;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.sdk.entity.factory.GenericFactory;
import org.opens.tgol.util.HttpStatusCodeFamily;

/**
 *
 * @author jkowalczyk
 */
public class MockWebResourceDataServiceDecorator implements WebResourceDataServiceDecorator{

    // locale map that contains 2 webResource : the first (with Id=1) is an 
    // instance of PageImpl and the second (with Id=2) is an instance of SiteImpl
    private Map<Long, WebResource> webResourceMap = new HashMap<Long, WebResource>();
    
    public MockWebResourceDataServiceDecorator() {
        WebResource page = new PageImpl();
        page.setURL("http://test.page.tgol.com");
        Audit audit = new AuditImpl();
        audit.setId(Long.valueOf(1));
        page.setAudit(audit);
        webResourceMap.put(Long.valueOf(1), page);
        WebResource site = new SiteImpl();
        audit = new AuditImpl();
        audit.setId(Long.valueOf(2));
        site.setAudit(audit);
        site.setURL("http://test.site.tgol.com");
        webResourceMap.put(Long.valueOf(2), site);
    }
    
    @Override
    public Long getParentWebResourceId(Long webresourceId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<? extends Object> getFailedWebResourceSortedByTest(WebResource webResource, Audit audit, int nbOfResult) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<? extends Object> getFailedWebResourceSortedByOccurrence(WebResource webResource, Audit audit, int nbOfResult) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<? extends Object> getFailedTestByOccurrence(WebResource webResource, Audit audit, int nbOfResult) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public WebResource ligthRead(Long webResourceId) {
        if (webResourceMap.containsKey(webResourceId)) {
            return webResourceMap.get(webResourceId);
        }
        return null;
    }

    @Override
    public WebResource deepRead(Long webResourceId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long getResultCountByResultType(WebResource webResource, Audit audit, TestSolution testSolution) {
        return Long.valueOf(10);
    }

    @Override
    public Collection<? extends Object> getResultCountByResultTypeAndTheme(WebResource webResource, Audit audit, TestSolution testSolution, int nb0fResult) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long getChildWebResourceCount(WebResource webresourceId) {
        return Long.valueOf(10);
    }

    @Override
    public Collection<ProcessResult> getProcessResultListByWebResourceAndScope(WebResource webResource, Scope scope) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<ProcessResult> getProcessResultListByWebResourceAndScope(WebResource webResource, Scope scope, String theme, String testSolution) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long getResultCountByResultTypeAndTheme(WebResource webResource, Audit audit, TestSolution testSolution, Theme theme) {
        return Long.valueOf(10);
    }

    @Override
    public Collection<? extends Object> getChildUrlList(WebResource webResource, Audit audit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasAuditSiteScopeTest(WebResource webResource, Scope scope) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Float getMarkByWebResourceAndAudit(WebResource webResource, Audit audit, boolean isRawMark) {
        return Float.valueOf(50);
    }

    @Override
    public Long getWebResourceCountByAuditAndHttpStatusCode(Long idAudit, HttpStatusCodeFamily httpStatusCode, String containingValue) {
        return Long.valueOf(10);
    }

    @Override
    public Collection<? extends Object> getWebResourceListByAuditAndHttpStatusCode(Long idAudit, HttpStatusCodeFamily httpStatusCode, int nbOfElements, int window, int sortDirection, String sortCriterion, String containingValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Page createPage(String pageURL) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Site createSite(String siteURL) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public WebResource getByUrl(String url) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public WebResource getByUrlAndParentWebResource(String url, WebResource webResource) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<WebResource> getWebResourceFromItsParent(WebResource webResource, int start, int chunkSize) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long getNumberOfChildWebResource(WebResource webResource) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public WebResource create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create(WebResource entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(WebResource entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Set<WebResource> entitySet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<? extends WebResource> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public WebResource read(Long key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public WebResource saveOrUpdate(WebResource entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<WebResource> saveOrUpdate(Set<WebResource> entitySet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityDao(GenericDAO<WebResource, Long> dao) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityFactory(GenericFactory<WebResource> factory) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public WebResource update(WebResource entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}