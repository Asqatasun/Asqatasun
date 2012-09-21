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
package org.opens.tgol.entity.dao.tanaguru.subject;

import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.Collection;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Scope;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.subject.WebResourceImpl;

/**
 * This class implements the TgolWebResourceDAO interface.
 *
 * @author jkowalczyk
 */
public class TgolWebResourceDAOImpl extends AbstractJPADAO<WebResource, Long>
        implements TgolWebResourceDAO {

    private static final String CACHEABLE_OPTION = "org.hibernate.cacheable";
    private static final String TRUE = "true";
    private static final String JOIN_PROCESS_RESULT =" JOIN r.processResultList pr";
    private static final String JOIN_TEST =" JOIN pr.test t";

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
    
    /**
     * Default constructor
     */
    public TgolWebResourceDAOImpl() {
        super();
    }

    @Override
    protected Class<? extends WebResource> getEntityClass() {
        return WebResourceImpl.class;
    }

    @Override
    public Long retrieveParentWebResourceId(Long webresourceId) {
        if (webresourceId == null) {
            return null;
        }
        try {
            Query query = entityManager.createQuery("SELECT r FROM "
                    + getEntityClass().getName() + " r"
                    + " WHERE r.id = :id");
            query.setParameter("id", webresourceId);
            if (query.getSingleResult() != null
                    && ((WebResource) query.getSingleResult()).getParent() != null) {
                return ((WebResource) query.getSingleResult()).getParent().getId();
            } else {
                return null;
            }
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public WebResource ligthRead(Long webResourceId) {
        try {
            Query query = entityManager.createQuery("SELECT wr FROM "
                    + getEntityClass().getName() + " wr"
                    + " WHERE wr.id = :id");
            query.setParameter("id", webResourceId);
            query.setHint(CACHEABLE_OPTION, TRUE);
            return (WebResource) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public Long retrieveChildWebResourceCount(WebResource parentWebResource) {
        if (parentWebResource == null) {
            return null;
        }
        Query query = entityManager.createQuery(
                "SELECT count (r.id) FROM "
                + getEntityClass().getName() + " r"
                + " WHERE r.parent.id = :id");
        query.setParameter("id", parentWebResource.getId());
        query.setHint(CACHEABLE_OPTION, TRUE);
        try {
            return (Long)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Collection<ProcessResult> retrieveProcessResultListByWebResourceAndScope(
            WebResource webResource,
            Scope scope) {
        Query query = entityManager.createQuery(
                "SELECT distinct(pr) FROM "
                + getEntityClass().getName() + " r"
                + JOIN_PROCESS_RESULT
                + " LEFT JOIN FETCH pr.remarkList pk"
                + " LEFT JOIN FETCH pk.elementList el"
                + " JOIN pr.test t"
                + " JOIN t.scope s"
                + " WHERE (r=:webResource)"
                + " AND s = :scope");
        query.setParameter("webResource", webResource);
        query.setParameter("scope", scope);
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
            String testSolution) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT distinct(pr) FROM ");
        sb.append(getEntityClass().getName());
        sb.append(" r");
        sb.append(JOIN_PROCESS_RESULT);
        sb.append(" LEFT JOIN FETCH pr.remarkList pk");
        sb.append(" LEFT JOIN FETCH pk.elementList el");
        sb.append(" JOIN pr.test t");
        sb.append(" JOIN pr.test.criterion.theme th");
        sb.append(" JOIN t.scope s");
        sb.append(" WHERE r=:webResource");
        sb.append(" AND (s = :scope or s.id = 3) ");
        if (theme != null && 
                !theme.isEmpty() &&
                    !theme.equals(selectAllThemeKey)) {
            sb.append("AND (th.code = :theme)");
        }
        if (testSolution != null && 
                !testSolution.isEmpty() &&
                    !testSolution.equals(selectAllTestResultKey)) {
            sb.append(" AND (pr.definiteValue = :testSolution) ");
        }

        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("webResource", webResource);
        query.setParameter("scope", scope);
        if (theme != null &&
                !theme.isEmpty() &&
                    !theme.equals(selectAllThemeKey)) {
            query.setParameter("theme", theme);
        }
        if (testSolution != null &&
                !testSolution.isEmpty() &&
                    !testSolution.equals(selectAllTestResultKey)) {
            query.setParameter("testSolution", TestSolution.valueOf(testSolution));
        }
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Collection<? extends Object> retrieveChildUrlList(
            WebResource webResource) {
        Query query = entityManager.createQuery(
                "SELECT NEW  com.opens.tgol.presentation.data.PageResultImpl(r.url, r.mark, r.id) FROM "
                + getEntityClass().getName() + " r"
                + " WHERE r.parent.id = :id"
                + " GROUP BY r.url"
                + " ORDER BY r.url ASC");
        query.setParameter("id", webResource.getId());
        query.setHint(CACHEABLE_OPTION, TRUE);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean hasAuditSiteScopeTest(WebResource webResource, Scope scope) {
        Query query = entityManager.createQuery(
                "SELECT count(pr.id) FROM "
                + getEntityClass().getName() + " r"
                + JOIN_PROCESS_RESULT
                + JOIN_TEST
                + " JOIN t.scope s"
                + " WHERE (r.id=:id)"
                + " AND (s = :scope or s.id=3)");
        query.setParameter("id", webResource.getId());
        query.setParameter("scope", scope);
        query.setHint(CACHEABLE_OPTION, TRUE);
        try {
            if ((Long)query.getSingleResult()>1) {
                return true;
            } else {
                return false;
            }
        } catch (NoResultException e) {
            return false;
        }
    }

}