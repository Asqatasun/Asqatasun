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
package org.opens.tanaguru.entity.dao.audit;

import java.math.BigInteger;
import java.util.*;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import org.apache.http.HttpStatus;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.opens.tanaguru.entity.audit.*;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

/**
 * 
 * @author jkowalczyk
 */
public class ContentDAOImpl extends AbstractJPADAO<Content, Long> implements
        ContentDAO {

    private static final Logger LOGGER = Logger.getLogger(ContentDAOImpl.class);
    private static final int DEFAULT_HTTP_STATUS_VALUE = -1;
    private static final Integer HTTP_STATUS_OK = HttpStatus.SC_OK;
    private static final String INSERT_QUERY =
            "INSERT INTO CONTENT_RELATIONSHIP (Id_Content_Parent, Id_Content_Child) values ";
    private static final String DELETE_CONTENT_RELATIONSHIP_QUERY =
            "DELETE FROM CONTENT_RELATIONSHIP WHERE Id_Content_Child=:idContentChild ";
    private static final String DELETE_RELATED_CONTENT_QUERY =
            "DELETE FROM CONTENT WHERE "
            + "Id_Content in (SELECT Id_Content_Child FROM CONTENT_RELATIONSHIP WHERE Id_Content_Parent=:idContent) "
            + "AND DTYPE not like \'SSPImpl\'";
    private static final String UPDATE_QUERY =
            "UPDATE CONTENT SET Id_Audit=:idAudit WHERE Id_Content=:idContent ";
    private static final String SELECT_SSP_QUERY =
            "SELECT ssp.Id_Content"
            +" FROM CONTENT ssp"
            +" INNER JOIN WEB_RESOURCE page ON ssp.Id_Page = page.Id_Web_Resource"
            +" WHERE ssp.DTYPE = \'SSPImpl\'"
            +" AND (page.Id_Web_Resource =:idWebResource OR page.Id_Web_Resource_Parent =:idWebResource)";
    private static final String SELECT_RELATED_CONTENT_QUERY =
            "SELECT relatedContent2.Id_Content "
            +" FROM CONTENT ssp"
            +" INNER JOIN CONTENT_RELATIONSHIP relatedContent1 ON ssp.Id_Content=relatedContent1.Id_Content_Parent"
            +" INNER JOIN CONTENT relatedContent2 ON relatedContent1.Id_Content_Child=relatedContent2.Id_Content"
            +" INNER JOIN WEB_RESOURCE page ON ssp.Id_Page=page.Id_Web_Resource"
            +" WHERE ssp.DTYPE='SSPImpl'"
            +" AND (page.Id_Web_Resource =:idWebResource OR page.Id_Web_Resource_Parent =:idWebResource)"
            +" AND ssp.Http_Status_Code<>-1"
            +" LIMIT :chunkSize OFFSET :start";
    private static final String LIMIT_OPTION = " LIMIT :chunkSize OFFSET :start";
    private static final String HTTP_STATUS_EQUAL_OPTION = " AND ssp.Http_Status_Code = :httpStatusCode";
    private static final String HTTP_STATUS_NOT_EQUAL_OPTION = " AND ssp.Http_Status_Code != :httpStatusCode";
    private static final String JOIN_WR = " JOIN s.page w";
    private static final String SELECT_DISTINCT_RELATED_CONTENT = 
             "SELECT count(distinct rc.id) FROM ";
    private static final String SELECT_DISTINCT_SSP = "SELECT count(distinct s.id) FROM ";
    private static final String JOIN_PARENT_CONTENT_SET = " JOIN rc.parentContentSet s";
    private static final String WEB_RESOURCE_CONDITION = " WHERE w=:webResource";
    private static final String HTTP_STATUS_CONDITION = " AND s.httpStatusCode=:httpStatusCode";
    
    private static final String AUDIT_KEY = "audit";
    private static final String HTTP_STATUS_CODE_KEY = "httpStatusCode";
    private static final String WEB_RESOURCE_KEY = "webResource";
    private static final String ID_WEB_RESOURCE_KEY = "idWebResource";
    private static final String RELATED_CONTENT_KEY = " rc";

    public ContentDAOImpl() {
        super();
    }

    @Override
    public Content find(Audit audit, String uri) {
        Query query = entityManager.createQuery("SELECT c FROM "
                + getEntityClass().getName() + " c"
                + " WHERE c.audit = :audit"
                + " AND c.uri = :uri"
                + " AND c.httpStatusCode =:httpStatusCode");
        query.setParameter(AUDIT_KEY, audit);
        query.setParameter("uri", uri);
        query.setParameter(HTTP_STATUS_CODE_KEY, HTTP_STATUS_OK);
        try {
            return (Content) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } catch (NonUniqueResultException nure) {
            List<Content> queryResult = query.getResultList();
            for (Content content : queryResult) {
                if (StringUtils.equals(content.getURI(),uri)) {
                    return content;
                }
            }
            return null;
        }
    }

    @Override
    public Content find(WebResource page, String uri) {
        Query query = entityManager.createQuery("SELECT c FROM "
                + getEntityClass().getName() + " c"
                + " WHERE c.page = :page "
                + " AND c.uri = :uri");
        query.setParameter("page", page);
        query.setParameter("uri", uri);
        try {
            return (Content) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } catch (NonUniqueResultException nure) {
            List<Content> queryResult = query.getResultList();
            for (Content content : queryResult) {
                if (StringUtils.equals(content.getURI(),uri)) {
                    return content;
                }
            }
            return null;
        }
    }

    @Override
    protected Class<ContentImpl> getEntityClass() {
        return ContentImpl.class;
    }

    @Override
    public Long findNumberOfSSPContentFromAudit(Audit audit) {
        Query query = entityManager.createQuery(SELECT_DISTINCT_SSP
                + SSPImpl.class.getName() + " s"
                + " WHERE s.audit = :audit"
                + " AND s.httpStatusCode =:httpStatusCode");
        query.setParameter(AUDIT_KEY, audit);
        query.setParameter(HTTP_STATUS_CODE_KEY, HTTP_STATUS_OK);
        return (Long) query.getSingleResult();
    }

    @Override
    public boolean hasAdaptedSSP(Audit audit) {
        Query query = entityManager.createQuery(
                "SELECT count(s.id) FROM "
                + SSPImpl.class.getName() 
                + " s"
                + " JOIN s.audit as a"
                + " WHERE a = :audit"
                + " AND s.dom != null "
                + " AND s.dom != '' ");
        query.setParameter(AUDIT_KEY, audit);
        return (Long) query.getSingleResult() > 0;
    }

    @Override
    public boolean hasContent(Audit audit) {
        Query query = entityManager.createQuery(
                "SELECT count(s.id) FROM "
                + SSPImpl.class.getName() 
                + " s"
                + " JOIN s.audit as a"
                + " WHERE a = :audit"
                + " AND s.source != null "
                + " AND s.source != '' )");
        query = query.setParameter(AUDIT_KEY, audit);
        return (Long) query.getSingleResult() > 0;
    }

    @Override
    public Long findNumberOfOrphanContentFromWebResource(WebResource webResource) {
        if (webResource instanceof Page) {
            Query query = entityManager.createQuery(
                    SELECT_DISTINCT_SSP
                    + SSPImpl.class.getName() + " s"
                    + JOIN_WR
                    + WEB_RESOURCE_CONDITION
                    + HTTP_STATUS_CONDITION);
            query.setParameter(WEB_RESOURCE_KEY, webResource);
            query.setParameter(HTTP_STATUS_CODE_KEY, DEFAULT_HTTP_STATUS_VALUE);
            return (Long) query.getSingleResult();
        } else if (webResource instanceof Site) {
            Query query = entityManager.createQuery(
                    SELECT_DISTINCT_SSP
                    + SSPImpl.class.getName() + " s"
                    + JOIN_WR
                    + " JOIN w.parent p"
                    + " WHERE p=:webResource"
                    + HTTP_STATUS_CONDITION);
            query.setParameter(WEB_RESOURCE_KEY, webResource);
            query.setParameter(HTTP_STATUS_CODE_KEY, DEFAULT_HTTP_STATUS_VALUE);
            return (Long) query.getSingleResult();
        }
        return (long) 0;
    }

    @Override
    public List<Content> findOrphanContentList(
            WebResource webResource,
            int start,
            int chunkSize) {
        if (webResource instanceof Page) {
            Query query = entityManager.createQuery(
                    "SELECT distinct s FROM "
                    + SSPImpl.class.getName() + " s"
                    + JOIN_WR
                    + WEB_RESOURCE_CONDITION
                    + HTTP_STATUS_CONDITION);
            query.setParameter(WEB_RESOURCE_KEY, webResource);
            query.setParameter(HTTP_STATUS_CODE_KEY, DEFAULT_HTTP_STATUS_VALUE);
            query.setFirstResult(start);
            query.setMaxResults(chunkSize);
            return (List<Content>) query.getResultList();
        } else if (webResource instanceof Site) {
            Query query = entityManager.createQuery(
                    "SELECT distinct s FROM "
                    + SSPImpl.class.getName() + " s"
                    + JOIN_WR
                    + " JOIN w.parent p"
                    + " WHERE p=:webResource"
                    + HTTP_STATUS_CONDITION);
            query.setParameter(WEB_RESOURCE_KEY, webResource);
            query.setParameter(HTTP_STATUS_CODE_KEY, DEFAULT_HTTP_STATUS_VALUE);
            query.setFirstResult(start);
            query.setMaxResults(chunkSize);
            List<Content> contentList = (List<Content>) query.getResultList();
            flushAndCloseEntityManager();
            return contentList;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public Long findNumberOfOrphanRelatedContentFromWebResource(
            WebResource webResource) {
        if (webResource instanceof Page) {
            Query query = entityManager.createQuery(
                    SELECT_DISTINCT_RELATED_CONTENT
                    + RelatedContentImpl.class.getName() + RELATED_CONTENT_KEY
                    + JOIN_PARENT_CONTENT_SET
                    + JOIN_WR
                    + WEB_RESOURCE_CONDITION
                    + " AND rc.httpStatusCode =:httpStatusCode");
            query.setParameter(WEB_RESOURCE_KEY, webResource);
            query.setParameter(HTTP_STATUS_CODE_KEY, DEFAULT_HTTP_STATUS_VALUE);
            return (Long) query.getSingleResult();
        } else if (webResource instanceof Site) {
            Query query = entityManager.createQuery(
                    SELECT_DISTINCT_RELATED_CONTENT
                    + RelatedContentImpl.class.getName() + RELATED_CONTENT_KEY
                    + JOIN_PARENT_CONTENT_SET
                    + JOIN_WR
                    + " JOIN w.parent p"
                    + " WHERE p=:webResource"
                    + " AND rc.httpStatusCode =:httpStatusCode");
            query.setParameter(WEB_RESOURCE_KEY, webResource);
            query.setParameter(HTTP_STATUS_CODE_KEY, DEFAULT_HTTP_STATUS_VALUE);
            return (Long) query.getSingleResult();
        }
        return (long) 0;
    }

    @Override
    public List<Content> findOrphanRelatedContentList(
            WebResource webResource,
            int start,
            int chunkSize) {
        if (webResource instanceof Page) {
            Query query = entityManager.createQuery(
                    "SELECT distinct rc FROM "
                    + RelatedContentImpl.class.getName() + RELATED_CONTENT_KEY
                    + JOIN_PARENT_CONTENT_SET
                    + JOIN_WR
                    + WEB_RESOURCE_CONDITION
                    + " AND rc.httpStatusCode =:httpStatusCode");
            query.setParameter(WEB_RESOURCE_KEY, webResource);
            query.setParameter(HTTP_STATUS_CODE_KEY, DEFAULT_HTTP_STATUS_VALUE);
            query.setFirstResult(start);
            query.setMaxResults(chunkSize);
            return (List<Content>) query.getResultList();
        } else if (webResource instanceof Site) {
            Query query = entityManager.createQuery(
                    "SELECT distinct rc FROM "
                    + RelatedContentImpl.class.getName() + RELATED_CONTENT_KEY
                    + JOIN_PARENT_CONTENT_SET
                    + JOIN_WR
                    + " WHERE w.parent.id=:idWebResource"
                    + " AND rc.httpStatusCode =:httpStatusCode");
            query.setParameter(ID_WEB_RESOURCE_KEY, webResource.getId());
            query.setParameter(HTTP_STATUS_CODE_KEY, DEFAULT_HTTP_STATUS_VALUE);
            query.setFirstResult(start);
            query.setMaxResults(chunkSize);
            List<Content> contentList = (List<Content>) query.getResultList();
            flushAndCloseEntityManager();
            return contentList;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public Long findNumberOfSSPFromWebResource(WebResource webResource, int httpStatusCode) {
        if (webResource != null) {
            StringBuilder strb = new StringBuilder();
            strb.append(SELECT_DISTINCT_SSP);
            strb.append(SSPImpl.class.getName());
            strb.append(" s");
            strb.append(JOIN_WR);
            if (webResource instanceof Page) {
                strb.append(" WHERE w.id=:idWebResource");
            } else if (webResource instanceof Site) {
                strb.append(" WHERE w.parent.id=:idWebResource");
            }
            if (httpStatusCode != -1) {
                strb.append(HTTP_STATUS_CONDITION);
            } else {
                strb.append(" AND s.httpStatusCode!=:httpStatusCode");
            }
            Query query = entityManager.createQuery(strb.toString());
            query.setParameter(ID_WEB_RESOURCE_KEY, webResource.getId());
            query.setParameter(HTTP_STATUS_CODE_KEY, httpStatusCode);
            return (Long) query.getSingleResult();
        } else {
            return (long) 0;
        }
    }

    @Override
    public Long findNumberOfRelatedContentFromWebResource(WebResource webResource) {
        if (webResource instanceof Page) {
            Query query = entityManager.createQuery(
                    SELECT_DISTINCT_RELATED_CONTENT
                    + SSPImpl.class.getName() + " s"
                    + " JOIN s.relatedContentSet rc"
                    + JOIN_WR
                    + WEB_RESOURCE_CONDITION
                    + " AND s.httpStatusCode !=:httpStatusCode");
            query.setParameter(WEB_RESOURCE_KEY, webResource);
            query.setParameter(HTTP_STATUS_CODE_KEY, DEFAULT_HTTP_STATUS_VALUE);
            return (Long) query.getSingleResult();
        } else if (webResource instanceof Site) {
            Query query = entityManager.createQuery(
                    SELECT_DISTINCT_RELATED_CONTENT
                    + SSPImpl.class.getName() + " s"
                    + " JOIN s.relatedContentSet rc"
                    + JOIN_WR
                    + " WHERE w.parent.id=:idWebResource"
                    + " AND s.httpStatusCode !=:httpStatusCode");
            query.setParameter(ID_WEB_RESOURCE_KEY, webResource.getId());
            query.setParameter(HTTP_STATUS_CODE_KEY, DEFAULT_HTTP_STATUS_VALUE);
            return (Long) query.getSingleResult();
        }
        return (long) 0;
    }

    @Override
    public Collection<StylesheetContent> findExternalStylesheetFromAudit(Audit audit) {
        Set<StylesheetContent> externalCssSet = new HashSet<>();
        if (audit != null) {
            Query query = entityManager.createQuery(
                    "SELECT sc FROM "
                    + StylesheetContentImpl.class.getName() + " sc"
                    + " WHERE sc.audit=:audit "
                    + " AND sc.uri not LIKE :inlineUrl "
                    + " AND sc.httpStatusCode != :httpStatusCode");
            query.setParameter(AUDIT_KEY, audit);
            query.setParameter("inlineUrl", "%#tanaguru-css-%");
            query.setParameter(HTTP_STATUS_CODE_KEY, DEFAULT_HTTP_STATUS_VALUE);
            try {
                externalCssSet.addAll(query.getResultList());
                return externalCssSet;
            } catch (NoResultException e) {
                return externalCssSet;
            }
        }
        return externalCssSet;
    }

    /**
     * This native query is used to avoid multiple select before insert realized
     * by hibernate while persisting the content relationship relation.
     * @param ssp
     */
    @Override
    public void saveContentRelationShip(SSP ssp, Set<Long> relatedContentIdSet) {
        List<Long> relatedContentIds = findRelatedContentFromSsp(ssp);
        Set<Long> newRelatedContentIdSet = new HashSet<>();
        for (Long relatedContentId : relatedContentIdSet) {
            if (!relatedContentIds.contains(relatedContentId)) {
                newRelatedContentIdSet.add(relatedContentId);
            }
        }
        if (!newRelatedContentIdSet.isEmpty()) {
            StringBuilder queryValuesBuilder = new StringBuilder();
            for (Long relatedContentId : newRelatedContentIdSet) {
                    queryValuesBuilder.append("(");
                    queryValuesBuilder.append(ssp.getId());
                    queryValuesBuilder.append(",");
                    queryValuesBuilder.append(relatedContentId);
                    queryValuesBuilder.append(")");
                    queryValuesBuilder.append(",");
            }
            queryValuesBuilder.setCharAt(queryValuesBuilder.length()-1, ';');
            Query query = entityManager.createNativeQuery(
                    INSERT_QUERY+
                    queryValuesBuilder.toString());
            try {
                query.executeUpdate();
                flushAndCloseEntityManager();
            } catch (ConstraintViolationException micve) {
                LOGGER.warn(micve.getMessage());
            } finally {
                flushAndCloseEntityManager();
            }
        }
    }

    /**
     * 
     * @param ssp
     * @return the list of ids of related contents for a given ssp
     */
    private List<Long> findRelatedContentFromSsp(SSP ssp) {
        Query query = entityManager.createQuery(
                "select rc.id FROM "
                + RelatedContentImpl.class.getName() + RELATED_CONTENT_KEY
                + JOIN_PARENT_CONTENT_SET
                + " WHERE s.id =:idSSP");
        query.setParameter("idSSP", ssp.getId());
        return query.getResultList();
    }
    
    /**
     *
     * @param idContent
     * @param idAudit
     */
    @Override
    public void saveAuditToContent(Long idContent, Long idAudit ) {
        if (idContent != null && idAudit != null) {
            Query query = entityManager.createNativeQuery(UPDATE_QUERY);
            query.setParameter("idContent", idContent);
            query.setParameter("idAudit", idAudit);
            query.executeUpdate();
            flushAndCloseEntityManager();
        }
    }

    /**
     *
     * @param webResourceId
     * @param httpStatusCode
     * @param start
     * @param chunkSize
     * @return
     */
    @Override
    public List<Long> getSSPFromWebResource(
            Long webResourceId,
            int httpStatusCode,
            int start,
            int chunkSize) {
        if (webResourceId != null) {
            StringBuilder strb = new StringBuilder();
            strb.append(SELECT_SSP_QUERY);
            if (httpStatusCode != -1) {
               strb.append(HTTP_STATUS_EQUAL_OPTION);
            } else {
                strb.append(HTTP_STATUS_NOT_EQUAL_OPTION);
            }
            strb.append(LIMIT_OPTION);
            Query query = entityManager.createNativeQuery(strb.toString());
            query.setParameter(ID_WEB_RESOURCE_KEY, webResourceId);
            query.setParameter(HTTP_STATUS_CODE_KEY, httpStatusCode);
            query.setParameter("start", start);
            query.setParameter("chunkSize", chunkSize);
            List<Long> idList = new ArrayList<>();
            for (BigInteger value : (List<BigInteger>)query.getResultList()) {
                idList.add(Long.valueOf(value.intValue()));
            }
            flushAndCloseEntityManager();
            return idList;
        }
        return null;
    }

    @Override
    public List<Long> getRelatedContentFromWebResource(
            Long webResourceId,
            int start,
            int chunkSize) {
        if (webResourceId != null) {
            Query query = entityManager.createNativeQuery(SELECT_RELATED_CONTENT_QUERY);
            query.setParameter(ID_WEB_RESOURCE_KEY, webResourceId);
            query.setParameter("start", start);
            query.setParameter("chunkSize", chunkSize);
            List<Long> idList = new ArrayList<>();
            for (BigInteger value : (List<BigInteger>)query.getResultList()) {
                idList.add(Long.valueOf(value.intValue()));
            }
            flushAndCloseEntityManager();
            return idList;
        }
        return null;
    }

    /**
     * 
     * @param id
     * @param isFetchParameters
     * @return
     */
    @Override
    public Content readWithRelatedContent(Long id, boolean isFetchParameters) {
        Query query = entityManager.createQuery("SELECT c FROM "
                + getEntityClass().getName() + " c"
                + " WHERE c.id = :id"
                + " AND c.httpStatusCode =:httpStatusCode");
        query.setParameter("id", id);
        query.setParameter(HTTP_STATUS_CODE_KEY, HTTP_STATUS_OK);
        try {
            Content content = (Content)query.getSingleResult();
            if (content instanceof SSP) {
                ((SSP)content).getRelatedContentSet().size();
                if (isFetchParameters) {
                    Audit audit = ((SSP)content).getAudit();
                    audit.getParameterSet().size();
                }
            }
            flushAndCloseEntityManager();
            return content;
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public Collection<RelatedContent> findRelatedContentFromAudit(Audit audit) {
        Set<RelatedContent> relatedContentSet = new HashSet<>();
        Query query = entityManager.createQuery(
                "SELECT distinct rc FROM "
                + RelatedContentImpl.class.getName() + RELATED_CONTENT_KEY
                + " WHERE rc.audit=:audit");
        query.setParameter(AUDIT_KEY, audit);
        try {
            relatedContentSet.addAll(query.getResultList());
            return relatedContentSet;
        } catch (NoResultException nre) {
            return relatedContentSet;
        }
    }

    @Override
    public void deleteContentRelationShip(Long relatedContentId) {
        Query query = entityManager.createNativeQuery(DELETE_CONTENT_RELATIONSHIP_QUERY);
        query.setParameter("idContentChild", relatedContentId);
        try {
            query.executeUpdate();
        } catch (NoResultException nre) {
            // do nothing
        }
    }
    
    @Override
    public void deleteRelatedContentFromContent(Content content) {
        Query query = entityManager.createNativeQuery(DELETE_RELATED_CONTENT_QUERY);
        query.setParameter("idContent", content.getId());
        try {
            query.executeUpdate();
        } catch (NoResultException nre) {
            // do nothing
        }
    }

    /**
     * Due to memory leaks, the entity manager has to be flushed and closed after
     * each db operation. All the elements retrieved while the db access keep
     * a reference to the entity manager and can never be garbaged.
     * By flushing and closing the entity manager, these objects can be free.
     */
    private void flushAndCloseEntityManager(){
        entityManager.flush();
        entityManager.close();
    }

}