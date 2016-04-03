/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.asqatasun.entity.dao.audit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.NoResultException;

import javax.persistence.Query;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.asqatasun.entity.audit.*;
import org.asqatasun.entity.reference.Criterion;
import org.asqatasun.entity.reference.Scope;
import org.asqatasun.entity.reference.Test;
import org.asqatasun.entity.reference.Theme;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.sdk.entity.dao.jpa.AbstractJPADAO;

/**
 *
 * @author jkowalczyk
 */
public class ProcessResultDAOImpl extends AbstractJPADAO<ProcessResult, Long>
        implements ProcessResultDAO {

    private static final String CACHEABLE_OPTION = "org.hibernate.cacheable";
    private static final String TRUE = "true";
    
    private Long pageAndSiteScopeId = 3l;
    public Long getPageAndSiteScopeId() {
        return pageAndSiteScopeId;
    }

    public void setPageAndSiteScopeId(String pageAndSiteScopeId) {
        this.pageAndSiteScopeId = Long.valueOf(pageAndSiteScopeId);
    }

    private String selectAllThemeKey;
    public String getSelectAllThemeKey() {
        return selectAllThemeKey;
    }

    public void setSelectAllThemeKey(String selectAllThemeKey) {
        this.selectAllThemeKey = selectAllThemeKey;
    }

    private String selectAllTestResultKey;
    public String getSelectAllTestResultKey() {
        return selectAllTestResultKey;
    }

    public void setSelectAllTestResultKey(String selectAllTestResultKey) {
        this.selectAllTestResultKey = selectAllTestResultKey;
    }
    
    public ProcessResultDAOImpl() {
        super();
    }

    @Override
    protected Class<ProcessResultImpl> getEntityClass() {
        return ProcessResultImpl.class;
    }

    private Class<DefiniteResultImpl> getDefitiniteResultClass() {
        return DefiniteResultImpl.class;
    }

    private Class<IndefiniteResultImpl> getIndefitiniteResultClass() {
        return IndefiniteResultImpl.class;
    }

    @Override
    public int getResultByThemeCount(WebResource webresource, TestSolution testSolution, Theme theme) {
        StringBuilder queryStr = new StringBuilder();
        queryStr.append("SELECT COUNT(pr) FROM ");
        queryStr.append(getDefitiniteResultClass().getName());
        queryStr.append(" pr WHERE ");
        queryStr.append("pr.subject = :webresource AND ");
        queryStr.append("pr.definiteValue = :definiteValue");
        if (theme != null) {
            queryStr.append(" AND pr.test.criterion.theme = :theme");
        }
        Query query = entityManager.createQuery(queryStr.toString());
        query.setParameter("definiteValue", testSolution);
        query.setParameter("webresource", webresource);
        if (theme != null) {
            query.setParameter("theme", theme);
        }
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public Collection<ProcessResult> getResultByScopeList(WebResource webresource, Scope scope) {
        Query query = entityManager.createQuery("SELECT pr FROM "
                + getDefitiniteResultClass().getName() + " pr "
                + " WHERE "
                + "pr.subject = :webresource AND "
                + "pr.test.scope = :scope");
        query.setParameter("webresource", webresource);
        query.setParameter("scope", scope);
        Set setItems = new LinkedHashSet(query.getResultList());
        return setItems;
    }

    @Override
    public Long retrieveNumberOfGrossResultFromAudit(Audit audit) {
        Query query = entityManager.createQuery("SELECT count(pr.id) FROM "
                + getEntityClass().getName() + " pr "
                + " WHERE "
                + " pr.grossResultAudit = :audit");
        query.setParameter("audit", audit);
        return (Long) query.getSingleResult();
    }

    @Override
    public Long retrieveNumberOfNetResultFromAudit(Audit audit) {
        Query query = entityManager.createQuery("SELECT count(pr.id) FROM "
                + getEntityClass().getName() + " pr "
                + " WHERE "
                + " pr.netResultAudit = :audit");
        query.setParameter("audit", audit);
        return (Long) query.getSingleResult();
    }

    @Override
    public Collection<ProcessResult> retrieveGrossResultFromAudit(Audit audit) {
        Query query = entityManager.createQuery("SELECT pr FROM "
                + getEntityClass().getName() + " pr "
                + " LEFT JOIN FETCH pr.subject"
                + " LEFT JOIN FETCH pr.test"
                + " WHERE "
                + " pr.grossResultAudit = :audit");
        query.setParameter("audit", audit);
        return query.getResultList();
    }

    @Override
    public Collection<ProcessResult> retrieveNetResultFromAudit(Audit audit) {
        Query query = entityManager.createQuery("SELECT pr FROM "
                + getEntityClass().getName() + " pr "
                + " LEFT JOIN FETCH pr.subject"
                + " WHERE "
                + " pr.netResultAudit = :audit");
        query.setParameter("audit", audit);
        return query.getResultList();
    }

    @Override
    public Collection<ProcessResult> retrieveNetResultFromAuditAndWebResource(Audit audit, WebResource webResource) {
        Query query = entityManager.createQuery("SELECT pr FROM "
                + getDefitiniteResultClass().getName() + " pr "
                + " WHERE "
                + " pr.netResultAudit = :audit AND"
                + " pr.subject = :webResource");
        query.setParameter("audit", audit);
        query.setParameter("webResource", webResource);
        return query.getResultList();
    }

    @Override
    public Collection<ProcessResult> retrieveGrossResultFromAuditAndTest(Audit audit, Test test) {
        Query query = entityManager.createQuery("SELECT pr FROM "
                + getEntityClass().getName() + " pr "
                + " LEFT JOIN FETCH pr.subject"
                + " LEFT JOIN FETCH pr.test as t"
                + " WHERE "
                + " pr.grossResultAudit = :audit"
                + " AND t=:test");
        query.setParameter("audit", audit);
        query.setParameter("test", test);
        return query.getResultList();
    }

    @Override
    public void deleteIndefiniteResultFromAudit(Audit audit) {
        Query query = entityManager.createQuery("SELECT pr FROM "
                + getIndefitiniteResultClass().getName() + " pr "
                + " WHERE "
                + " pr.grossResultAudit = :audit");
        query.setParameter("audit", audit);
        for (ProcessResult pr : (Collection<ProcessResult>) query.getResultList()) {
            delete(pr.getId());
        }
    }

    @Override
    public Collection<ProcessResult> retrieveIndefiniteResultFromAudit(Audit audit) {
        Query query = entityManager.createQuery("SELECT pr FROM "
                + getIndefitiniteResultClass().getName() + " pr "
                + " WHERE "
                + " pr.grossResultAudit = :audit");
        query.setParameter("audit", audit);
        return query.getResultList();
    }

    @Override
    public List<DefiniteResult> getHistoryChanges(ProcessResult processResultImpl) {

        List<DefiniteResult> history = new ArrayList<>();
        AuditReader auditReader = AuditReaderFactory.get(this.entityManager);
        Long id = processResultImpl.getId();
        if (id == null) {
            return new ArrayList<>();
        }
        List<Number> revisions = auditReader.getRevisions(processResultImpl.getClass(), id);
        DefiniteResult find;
        for (int i = 0; i < revisions.size(); i++) {
            Number revision = revisions.get(i);
            find = auditReader.find(DefiniteResultImpl.class, id, revision);
            history.add(find);
        }

        return history;

    }

    @Override
    public Collection<ProcessResult> retrieveProcessResultListByWebResourceAndScope(
            WebResource webResource, 
            Scope scope) {
        Query query = entityManager.createQuery(
                "SELECT distinct(pr) FROM "
                + getEntityClass().getName() + " pr"
                + " LEFT JOIN FETCH pr.remarkSet pk"
                + " LEFT JOIN FETCH pk.elementSet el"
                + " JOIN pr.subject r"
                + " JOIN pr.test t"
                + " JOIN t.scope s"
                + " WHERE (r=:webResource)"
                + " AND (s = :scope or s.id = :pageAndSiteScope)");
        query.setParameter("webResource", webResource);
        query.setParameter("scope", scope);
        query.setParameter("pageAndSiteScope", pageAndSiteScopeId);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Collection<ProcessResult> retrieveProcessResultListByWebResourceAndCriterion(
            WebResource webResource, 
            Criterion criterion) {
        Query query = entityManager.createQuery(
                "SELECT distinct(pr) FROM "
                + getEntityClass().getName() + " pr"
                + " LEFT JOIN FETCH pr.remarkSet pk"
                + " LEFT JOIN FETCH pk.elementSet el"
                + " JOIN pr.subject r"        
                + " JOIN pr.test t"
                + " JOIN t.criterion c"
                + " WHERE r=:webResource"
                + " AND c=:criterion");
        query.setParameter("webResource", webResource);
        query.setParameter("criterion", criterion);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Collection<ProcessResult> retrieveProcessResultListByWebResourceAndScope(
            WebResource webResource, 
            Scope scope, 
            String theme, 
            Collection<String> testSolutions) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT pr FROM ");
        sb.append(getEntityClass().getName());
        sb.append(" pr");
        sb.append(" JOIN pr.subject w");
        sb.append(" JOIN pr.test t");
        sb.append(" JOIN pr.test.criterion.theme th");
        sb.append(" JOIN t.scope s");
        sb.append(" WHERE w=:webResource");
        sb.append(" AND (s = :scope or s.id = :pageAndSiteScope) ");
        if (theme != null && 
                !theme.isEmpty() &&
                    !theme.equals(selectAllThemeKey)) {
            sb.append("AND (th.code = :theme)");
        }
        if (!testSolutions.isEmpty()) {
            sb.append(" AND (pr.definiteValue IN (:testSolution)) ");
        }

        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("webResource", webResource);
        query.setParameter("scope", scope);
        query.setParameter("pageAndSiteScope", pageAndSiteScopeId);
        if (theme != null &&
                !theme.isEmpty() &&
                    !theme.equals(selectAllThemeKey)) {
            query.setParameter("theme", theme);
        }
        if (!testSolutions.isEmpty()) {
            Collection<TestSolution> solutions = new ArrayList<>();
            for (String solution : testSolutions) {
                solutions.add(TestSolution.valueOf(solution));
            }
            query.setParameter("testSolution", solutions);
        }
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Collection<ProcessResult> retrieveProcessResultListByWebResourceAndTest(WebResource webResource, Test test) {
        Query query = entityManager.createQuery(
                "SELECT distinct(pr) FROM "
                + getEntityClass().getName() + " pr"
                + " LEFT JOIN FETCH pr.remarkSet pk"
                + " LEFT JOIN FETCH pk.elementSet el"
                + " JOIN pr.subject r"
                + " JOIN pr.test t"
                + " WHERE r=:webResource"
                + " AND t=:test");
        query.setParameter("webResource", webResource);
        query.setParameter("test", test);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean hasAuditSiteScopeResult(WebResource webResource, Scope scope) {
        Query query = entityManager.createQuery(
                "SELECT count(pr.id) FROM "
                + getEntityClass().getName() + " pr"
                + " JOIN pr.subject r"
                + " JOIN pr.test t"
                + " JOIN t.scope s"
                + " WHERE (r.id=:id)"
                + " AND (s = :scope or s.id = :pageAndSiteScope)");
        query.setParameter("id", webResource.getId());
        query.setParameter("pageAndSiteScope", pageAndSiteScopeId);
        query.setParameter("scope", scope);
        query.setHint(CACHEABLE_OPTION, TRUE);
        try {
            if ((Long)query.getSingleResult()>0) {
                return true;
            } else {
                return false;
            }
        } catch (NoResultException e) {
            return false;
        }
    }

}