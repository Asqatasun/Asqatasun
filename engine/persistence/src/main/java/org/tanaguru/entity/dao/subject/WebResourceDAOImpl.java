/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.tanaguru.entity.dao.subject;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.entity.subject.WebResourceImpl;
import org.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.List;
import javax.persistence.NonUniqueResultException;
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.subject.PageImpl;

/**
 * 
 * @author jkowalczyk
 */
public class WebResourceDAOImpl extends AbstractJPADAO<WebResource, Long>
        implements WebResourceDAO {

    private static final String CACHEABLE_OPTION = "org.hibernate.cacheable";
    private static final String TRUE = "true";
    
    public WebResourceDAOImpl() {
        super();
    }

    @Override
    public WebResource findByUrl(String url) {
        Query query = entityManager.createQuery(
                "SELECT wr FROM " +
                getEntityClass().getName() + " wr"
                + " left join fetch wr.processResultSet pr"
                + " WHERE wr.url = :url");
        query.setParameter("url", url);
        try {
            return (WebResource) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } catch (NonUniqueResultException nure) {
            List<WebResource> queryResult = query.getResultList();
            for (WebResource wr : queryResult) {
                if (StringUtils.equals(wr.getURL(),url)) {
                    return wr;
                }
            }
            return null;
        }
    }

    @Override
    public WebResource findByAuditAndUrl(Audit audit, String url) {
        Query query = entityManager.createQuery(
                "SELECT wr FROM " +
                getEntityClass().getName() + " wr"
                + " left join fetch wr.processResultSet pr"
                + " WHERE wr.url = :url AND wr.audit = :audit");
        query.setParameter("url", url);
        query.setParameter("audit", audit);
        try {
            return (WebResource) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } catch (NonUniqueResultException nure) {
            List<WebResource> queryResult = query.getResultList();
            for (WebResource wr : queryResult) {
                if (StringUtils.equals(wr.getURL(),url)) {
                    return wr;
                }
            }
            return null;
        }
    }

    @Override
    protected Class<WebResourceImpl> getEntityClass() {
        return WebResourceImpl.class;
    }

    @Override
    public WebResource read(Long key) {
        try {
            Query query = entityManager.createQuery("SELECT wr FROM "
                + getEntityClass().getName() + " wr"
                + " left join fetch wr.processResultSet prs"
                + " left join fetch prs.remarkSet prk"
                + " left join fetch prk.elementSet pr"
                + " WHERE wr.id = :id");
            query.setParameter("id", key);
            return (WebResource) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public WebResource findByUrlAndParentWebResource(String url, WebResource webResource) {
        Query query = entityManager.createQuery(
                "SELECT wr FROM " +
                PageImpl.class.getName() + " wr"
                + " WHERE wr.url = :url"
                + " AND wr.parent =:webResource");
        query.setParameter("url", url);
        query.setParameter("webResource", webResource);
        try {
            return (WebResource) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } catch (NonUniqueResultException nure) {
            List<WebResource> queryResult = query.getResultList();
            for (WebResource wr : queryResult) {
                if (StringUtils.equals(wr.getURL(),url)) {
                    return wr;
                }
            }
            return null;
        }
    }

    @Override
    public List<WebResource> findWebResourceFromItsParent (
            WebResource webResource,
            int start,
            int chunkSize) {
        Query query = entityManager.createQuery(
                    "SELECT wr FROM "
                    + getEntityClass().getName() + " wr"
                    + " JOIN wr.parent p"
                    + " WHERE p=:webResource");
        query.setParameter("webResource", webResource);
        query.setFirstResult(start);
        query.setMaxResults(chunkSize);
        return (List<WebResource>) query.getResultList();
    }

    @Override
    public Long findNumberOfChildWebResource(WebResource webResource) {
        Query query = entityManager.createQuery(
                    "SELECT count(wr.id) FROM "
                    + getEntityClass().getName() + " wr"
                    + " JOIN wr.parent p"
                    + " WHERE p=:webResource");
        query.setParameter("webResource", webResource);;
        return (Long) query.getSingleResult();
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
    public Long findParentWebResourceId(Long webresourceId) {
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
    public Long findChildWebResourceCount(WebResource parentWebResource) {
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


}