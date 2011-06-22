package org.opens.tanaguru.entity.dao.audit;

import javax.persistence.Query;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ContentImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.opens.tanaguru.entity.audit.RelatedContent;
import org.opens.tanaguru.entity.audit.RelatedContentImpl;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.SSPImpl;
import org.opens.tanaguru.entity.audit.StylesheetContent;
import org.opens.tanaguru.entity.audit.StylesheetContentImpl;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;

public class ContentDAOImpl extends AbstractJPADAO<Content, Long> implements
        ContentDAO {

    private static final Logger LOGGER = Logger.getLogger(ContentDAOImpl.class);
    private static final int DEFAULT_HTTP_STATUS_VALUE = -1;
    private static final Integer HTTP_STATUS_OK = Integer.valueOf(HttpStatus.SC_OK);
    private static final String INSERT_QUERY =
            "INSERT IGNORE INTO CONTENT_RELATIONSHIP (Id_Content_Parent, Id_Content_Child) values ";
    private static final String DELETE_CONTENT_RELATIONSHIP_QUERY =
            "DELETE FROM CONTENT_RELATIONSHIP WHERE Id_Content_Child=:idContentChild ";
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
            +" LIMIT :start , :chunkSize ";
    private static final String LIMIT_OPTION = " LIMIT :start , :chunkSize ";
    private static final String HTTP_STATUS_EQUAL_OPTION = " AND ssp.Http_Status_Code = :httpStatusCode";
    private static final String HTTP_STATUS_NOT_EQUAL_OPTION = " AND ssp.Http_Status_Code != :httpStatusCode";
    
    public ContentDAOImpl() {
        super();
    }

    public Content find(Audit audit, String uri) {
        Query query = entityManager.createQuery("SELECT c FROM "
                + getEntityClass().getName() + " c"
                + " WHERE c.audit = :audit"
                + " AND c.uri = :uri"
                + " AND c.httpStatusCode =:httpStatusCode");
        query.setParameter("audit", audit);
        query.setParameter("uri", uri);
        query.setParameter("httpStatusCode", HTTP_STATUS_OK);
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
        Query query = entityManager.createQuery("SELECT count(distinct s.id) FROM "
                + SSPImpl.class.getName() + " s"
                + " WHERE s.audit = :audit"
                + " AND s.httpStatusCode =:httpStatusCode");
        query.setParameter("audit", audit);
        query.setParameter("httpStatusCode", HTTP_STATUS_OK);
        return (Long) query.getSingleResult();
    }

    @Override
    public boolean hasAdaptedSSP(Audit audit) {
        Query query = entityManager.createQuery("SELECT count(s.id) FROM "
                + SSPImpl.class.getName() + " s"
                + " JOIN s.audit as a"
                + " WHERE a = :audit"
                + " AND s.dom != null "
                + " AND s.dom != '' ");
        query.setParameter("audit", audit);
        if ((Long) query.getSingleResult() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean hasContent(Audit audit) {
        Query query = entityManager.createQuery("SELECT count(s.id) FROM "
                + SSPImpl.class.getName() + " s"
                + " JOIN s.audit as a"
                + " WHERE a = :audit"
                + " AND s.source != null "
                + " AND s.source != '' )");
        query = query.setParameter("audit", audit);
        if ((Long) query.getSingleResult() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Long findNumberOfOrphanContentFromWebResource(WebResource webResource) {
        if (webResource instanceof Page) {
            Query query = entityManager.createQuery(
                    "SELECT count(distinct s.id) FROM "
                    + SSPImpl.class.getName() + " s"
                    + " JOIN s.page w"
                    + " WHERE w=:webResource"
                    + " AND s.httpStatusCode=:httpStatusCode");
            query.setParameter("webResource", webResource);
            query.setParameter("httpStatusCode", DEFAULT_HTTP_STATUS_VALUE);
            return (Long) query.getSingleResult();
        } else if (webResource instanceof Site) {
            Query query = entityManager.createQuery(
                    "SELECT count(distinct s.id) FROM "
                    + SSPImpl.class.getName() + " s"
                    + " JOIN s.page w"
                    + " JOIN w.parent p"
                    + " WHERE p=:webResource"
                    + " AND s.httpStatusCode=:httpStatusCode");
            query.setParameter("webResource", webResource);
            query.setParameter("httpStatusCode", DEFAULT_HTTP_STATUS_VALUE);
            return (Long) query.getSingleResult();
        }
        return Long.valueOf(0);
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
                    + " JOIN s.page w"
                    + " WHERE w=:webResource"
                    + " AND s.httpStatusCode=:httpStatusCode");
            query.setParameter("webResource", webResource);
            query.setParameter("httpStatusCode", DEFAULT_HTTP_STATUS_VALUE);
            query.setFirstResult(start);
            query.setMaxResults(chunkSize);
            return (List<Content>) query.getResultList();
        } else if (webResource instanceof Site) {
            Query query = entityManager.createQuery(
                    "SELECT distinct s FROM "
                    + SSPImpl.class.getName() + " s"
                    + " JOIN s.page w"
                    + " JOIN w.parent p"
                    + " WHERE p=:webResource"
                    + " AND s.httpStatusCode=:httpStatusCode");
            query.setParameter("webResource", webResource);
            query.setParameter("httpStatusCode", DEFAULT_HTTP_STATUS_VALUE);
            query.setFirstResult(start);
            query.setMaxResults(chunkSize);
            List<Content> contentList = (List<Content>) query.getResultList();
            flushAndCloseEntityManager();
            return contentList;
        }
        return new ArrayList<Content>();
    }

    @Override
    public Long findNumberOfOrphanRelatedContentFromWebResource(
            WebResource webResource) {
        if (webResource instanceof Page) {
            Query query = entityManager.createQuery(
                    "SELECT count(distinct rc.id) FROM "
                    + RelatedContentImpl.class.getName() + " rc"
                    + " JOIN rc.parentContentSet s"
                    + " JOIN s.page w"
                    + " WHERE w=:webResource"
                    + " AND rc.httpStatusCode =:httpStatusCode");
            query.setParameter("webResource", webResource);
            query.setParameter("httpStatusCode", DEFAULT_HTTP_STATUS_VALUE);
            return (Long) query.getSingleResult();
        } else if (webResource instanceof Site) {
            Query query = entityManager.createQuery(
                    "SELECT count(distinct rc.id) FROM "
                    + RelatedContentImpl.class.getName() + " rc"
                    + " JOIN rc.parentContentSet s"
                    + " JOIN s.page w"
                    + " JOIN w.parent p"
                    + " WHERE p=:webResource"
                    + " AND rc.httpStatusCode =:httpStatusCode");
            query.setParameter("webResource", webResource);
            query.setParameter("httpStatusCode", DEFAULT_HTTP_STATUS_VALUE);
            return (Long) query.getSingleResult();
        }
        return Long.valueOf(0);
    }

    @Override
    public List<Content> findOrphanRelatedContentList(
            WebResource webResource,
            int start,
            int chunkSize) {
        if (webResource instanceof Page) {
            Query query = entityManager.createQuery(
                    "SELECT distinct rc FROM "
                    + RelatedContentImpl.class.getName() + " rc"
                    + " JOIN rc.parentContentSet s"
                    + " JOIN s.page w"
                    + " WHERE w=:webResource"
                    + " AND rc.httpStatusCode =:httpStatusCode");
            query.setParameter("webResource", webResource);
            query.setParameter("httpStatusCode", DEFAULT_HTTP_STATUS_VALUE);
            query.setFirstResult(start);
            query.setMaxResults(chunkSize);
            return (List<Content>) query.getResultList();
        } else if (webResource instanceof Site) {
            Query query = entityManager.createQuery(
                    "SELECT distinct rc FROM "
                    + RelatedContentImpl.class.getName() + " rc"
                    + " JOIN rc.parentContentSet s"
                    + " JOIN s.page w"
                    + " WHERE w.parent.id=:idWebResource"
                    + " AND rc.httpStatusCode =:httpStatusCode");
            query.setParameter("idWebResource", webResource.getId());
            query.setParameter("httpStatusCode", DEFAULT_HTTP_STATUS_VALUE);
            query.setFirstResult(start);
            query.setMaxResults(chunkSize);
            List<Content> contentList = (List<Content>) query.getResultList();
            flushAndCloseEntityManager();
            return contentList;
        }
        return new ArrayList<Content>();
    }

    @Override
    public Long findNumberOfSSPFromWebResource(WebResource webResource, int httpStatusCode) {
        if (webResource != null) {
            StringBuilder strb = new StringBuilder();
            strb.append("SELECT count(distinct s.id) FROM ");
            strb.append(SSPImpl.class.getName());
            strb.append(" s");
            strb.append(" JOIN s.page w");
            if (webResource instanceof Page) {
                strb.append(" WHERE w.id=:idWebResource");
            } else if (webResource instanceof Site) {
                strb.append(" WHERE w.parent.id=:idWebResource");
            }
            if (httpStatusCode != -1) {
                strb.append(" AND s.httpStatusCode=:httpStatusCode");
            } else {
                strb.append(" AND s.httpStatusCode!=:httpStatusCode");
            }
            Query query = entityManager.createQuery(strb.toString());
            query.setParameter("idWebResource", webResource.getId());
            query.setParameter("httpStatusCode", httpStatusCode);
            return (Long) query.getSingleResult();
        } else {
            return Long.valueOf(0);
        }
    }

    @Override
    public Long findNumberOfRelatedContentFromWebResource(WebResource webResource) {
        if (webResource instanceof Page) {
            Query query = entityManager.createQuery(
                    "SELECT count(distinct rc.id) FROM "
                    + SSPImpl.class.getName() + " s"
                    + " JOIN s.relatedContentSet rc"
                    + " JOIN s.page w"
                    + " WHERE w=:webResource"
                    + " AND s.httpStatusCode !=:httpStatusCode");
            query.setParameter("webResource", webResource);
            query.setParameter("httpStatusCode", DEFAULT_HTTP_STATUS_VALUE);
            return (Long) query.getSingleResult();
        } else if (webResource instanceof Site) {
            Query query = entityManager.createQuery(
                    "SELECT count(distinct rc.id) FROM "
                    + SSPImpl.class.getName() + " s"
                    + " JOIN s.relatedContentSet rc"
                    + " JOIN s.page w"
                    + " WHERE w.parent.id=:idWebResource"
                    + " AND s.httpStatusCode !=:httpStatusCode");
            query.setParameter("idWebResource", webResource.getId());
            query.setParameter("httpStatusCode", DEFAULT_HTTP_STATUS_VALUE);
            return (Long) query.getSingleResult();
        }
        return Long.valueOf(0);
    }

    @Override
    public Collection<StylesheetContent> findExternalStylesheetFromAudit(Audit audit) {
        Set<StylesheetContent> externalCssSet = new HashSet<StylesheetContent>();
        if (audit != null) {
            Query query = entityManager.createQuery(
                    "SELECT sc FROM "
                    + StylesheetContentImpl.class.getName() + " sc"
                    + " WHERE sc.audit=:audit "
                    + " AND sc.uri not LIKE :inlineUrl "
                    + " AND sc.httpStatusCode != :httpStatusCode");
            query.setParameter("audit", audit);
            query.setParameter("inlineUrl", "%#tanaguru-css-%");
            query.setParameter("httpStatusCode", DEFAULT_HTTP_STATUS_VALUE);
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
        if (!relatedContentIdSet.isEmpty()) {
            StringBuilder queryValuesBuilder = new StringBuilder();
            for (Long relatedContentId : relatedContentIdSet) {
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
            query.setParameter("idWebResource", webResourceId);
            query.setParameter("httpStatusCode", httpStatusCode);
            query.setParameter("start", start);
            query.setParameter("chunkSize", chunkSize);
            List<Long> idList = new ArrayList<Long>();
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
            query.setParameter("idWebResource", webResourceId);
            query.setParameter("start", start);
            query.setParameter("chunkSize", chunkSize);
            List<Long> idList = new ArrayList<Long>();
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
     * @return
     */
    @Override
    public Content readWithRelatedContent(Long id) {
        Query query = entityManager.createQuery("SELECT c FROM "
                + getEntityClass().getName() + " c"
                + " WHERE c.id = :id"
                + " AND c.httpStatusCode =:httpStatusCode");
        query.setParameter("id", id);
        query.setParameter("httpStatusCode", HTTP_STATUS_OK);
        try {
            Content content = (Content)query.getSingleResult();
            if (content instanceof SSP) {
                ((SSP)content).getRelatedContentSet().size();
            }
            flushAndCloseEntityManager();
            return content;
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public Collection<RelatedContent> findRelatedContentFromAudit(Audit audit) {
        Set<RelatedContent> relatedContentSet = new HashSet<RelatedContent>();
        Query query = entityManager.createQuery(
                "SELECT distinct rc FROM "
                + RelatedContentImpl.class.getName() + " rc"
                + " WHERE rc.audit=:audit");
        query.setParameter("audit", audit);
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