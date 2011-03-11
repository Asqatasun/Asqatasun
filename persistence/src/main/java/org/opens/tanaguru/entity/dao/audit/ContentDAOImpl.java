package org.opens.tanaguru.entity.dao.audit;

import javax.persistence.Query;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ContentImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import org.apache.commons.httpclient.HttpStatus;
import org.opens.tanaguru.entity.audit.RelatedContent;
import org.opens.tanaguru.entity.audit.RelatedContentImpl;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.SSPImpl;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;

public class ContentDAOImpl extends AbstractJPADAO<Content, Long> implements
        ContentDAO {

    private static final int DEFAULT_HTTP_STATUS_VALUE = -1;
    private static final Integer HTTP_STATUS_OK = Integer.valueOf(HttpStatus.SC_OK);
    private static final String INSERT_QUERY =
            "insert into CONTENT_RELATIONSHIP (Id_Content_Parent, Id_Content_Child) values ";

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
        }
    }

    @Override
    protected Class<ContentImpl> getEntityClass() {
        return ContentImpl.class;
    }

    @Override
    public List<Content> findSSPContentWithRelatedContent(
            Audit audit,
            int start,
            int chunkSize) {
        Query query = entityManager.createQuery("SELECT s FROM "
                + SSPImpl.class.getName() + " s"
                + " WHERE s.audit = :audit"
                + " AND s.httpStatusCode =:httpStatusCode");
        query.setParameter("audit", audit);
        query.setParameter("httpStatusCode", HTTP_STATUS_OK);
        query.setFirstResult(start);
        query.setMaxResults(chunkSize);
        List<Content> contentList = query.getResultList();
        for (Content content : contentList) {
            ((SSP) content).getRelatedContentSet().size();
        }
        return contentList;
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
    public RelatedContent findRelatedContentFromUriWithParentContent(
            WebResource webResource,
            String uri) {
        Query query = entityManager.createQuery(
                "SELECT distinct rc FROM "
                + RelatedContentImpl.class.getName() + " rc"
                + " LEFT JOIN FETCH rc.parentContentSet s"
                + " JOIN s.page w"
                + " WHERE w=:webResource"
                + " AND rc.uri=:uri");
        query.setParameter("webResource", webResource);
        query.setParameter("uri", uri);
        try {
            return (RelatedContent) query.getSingleResult();
        } catch (NoResultException e) {
            query = entityManager.createQuery(
                    "SELECT distinct rc FROM "
                    + RelatedContentImpl.class.getName() + " rc"
                    + " LEFT JOIN FETCH rc.parentContentSet s"
                    + " JOIN s.page w"
                    + " JOIN w.parent p"
                    + " WHERE p=:webResource"
                    + " AND rc.uri=:uri");
            query.setParameter("webResource", webResource);
            query.setParameter("uri", uri);
            try {
                return (RelatedContent) query.getSingleResult();
            } catch (NoResultException e2) {
                return null;
            }
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
                    + " AND s.source IS NULL"
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
                    + " AND s.source IS NULL"
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
                    + " AND s.source IS NULL"
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
                    + " AND s.source IS NULL"
                    + " AND s.httpStatusCode=:httpStatusCode");
            query.setParameter("webResource", webResource);
            query.setParameter("httpStatusCode", DEFAULT_HTTP_STATUS_VALUE);
            query.setFirstResult(start);
            query.setMaxResults(chunkSize);
            List<Content> contentList = (List<Content>) query.getResultList();
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
                    + " AND rc.source IS NULL"
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
                    + " AND rc.source IS NULL"
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
                    + " AND rc.source IS NULL"
                    + " AND rc.httpStatusCode !=:httpStatusCode");
            query.setParameter("webResource", webResource);
            query.setParameter("httpStatusCode", HTTP_STATUS_OK);
            query.setFirstResult(start);
            query.setMaxResults(chunkSize);
            return (List<Content>) query.getResultList();
        } else if (webResource instanceof Site) {
            Query query = entityManager.createQuery(
                    "SELECT distinct rc FROM "
                    + RelatedContentImpl.class.getName() + " rc"
                    + " JOIN rc.parentContentSet s"
                    + " JOIN s.page w"
                    + " JOIN w.parent p"
                    + " WHERE p=:webResource"
                    + " AND rc.source IS NULL"
                    + " AND rc.httpStatusCode !=:httpStatusCode");
            query.setParameter("webResource", webResource);
            query.setParameter("httpStatusCode", HTTP_STATUS_OK);
            query.setFirstResult(start);
            query.setMaxResults(chunkSize);
            List<Content> contentList = (List<Content>) query.getResultList();
            return contentList;
        }
        return new ArrayList<Content>();
    }

    @Override
    public Long findNumberOfSSPFromWebResource(WebResource webResource) {
        if (webResource instanceof Page) {
            Query query = entityManager.createQuery(
                    "SELECT count(distinct s.id) FROM "
                    + SSPImpl.class.getName() + " s"
                    + " JOIN s.page w"
                    + " WHERE w=:webResource"
                    + " AND s.httpStatusCode !=:httpStatusCode");
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
                    + " AND s.httpStatusCode !=:httpStatusCode");
            query.setParameter("webResource", webResource);
            query.setParameter("httpStatusCode", DEFAULT_HTTP_STATUS_VALUE);
            return (Long) query.getSingleResult();
        }
        return Long.valueOf(0);
    }

    @Override
    public List<? extends SSP> findSSPList(
            WebResource webResource,
            int start,
            int chunkSize) {
        if (webResource instanceof Page) {
            Query query = entityManager.createQuery(
                    "SELECT distinct s FROM "
                    + SSPImpl.class.getName() + " s"
                    + " JOIN s.page w"
                    + " WHERE w=:webResource"
                    + " AND s.httpStatusCode !=:httpStatusCode");
            query.setParameter("webResource", webResource);
            query.setParameter("httpStatusCode", DEFAULT_HTTP_STATUS_VALUE);
            query.setFirstResult(start);
            query.setMaxResults(chunkSize);
            return (List<SSP>) query.getResultList();
        } else if (webResource instanceof Site) {
            Query query = entityManager.createQuery(
                    "SELECT distinct s FROM "
                    + SSPImpl.class.getName() + " s"
                    + " JOIN s.page w"
                    + " JOIN w.parent p"
                    + " WHERE p=:webResource"
                    + " AND s.httpStatusCode !=:httpStatusCode");
            query.setParameter("webResource", webResource);
            query.setParameter("httpStatusCode", DEFAULT_HTTP_STATUS_VALUE);
            query.setFirstResult(start);
            query.setMaxResults(chunkSize);
            return (List<SSP>) query.getResultList();
        }
        return null;
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
                    + " JOIN w.parent p"
                    + " WHERE p=:webResource"
                    + " AND s.httpStatusCode !=:httpStatusCode");
            query.setParameter("webResource", webResource);
            query.setParameter("httpStatusCode", DEFAULT_HTTP_STATUS_VALUE);
            return (Long) query.getSingleResult();
        }
        return Long.valueOf(0);
    }

    @Override
    public List<? extends RelatedContent> findRelatedContentList(
            WebResource webResource,
            int start,
            int chunkSize) {
        if (webResource instanceof Page) {
            Query query = entityManager.createQuery(
                    "SELECT distinct rc FROM "
                    + SSPImpl.class.getName() + " s"
                    + " JOIN s.relatedContentSet rc"
                    + " JOIN s.page w"
                    + " WHERE w=:webResource"
                    + " AND s.httpStatusCode !=:httpStatusCode");
            query.setParameter("webResource", webResource);
            query.setParameter("httpStatusCode", DEFAULT_HTTP_STATUS_VALUE);
            query.setFirstResult(start);
            query.setMaxResults(chunkSize);
            return (List<RelatedContent>) query.getResultList();
        } else if (webResource instanceof Site) {
            Query query = entityManager.createQuery(
                    "SELECT distinct rc FROM "
                    + SSPImpl.class.getName() + " s"
                    + " JOIN s.relatedContentSet rc"
                    + " JOIN s.page w"
                    + " JOIN w.parent p"
                    + " WHERE p=:webResource"
                    + " AND s.httpStatusCode !=:httpStatusCode");
            query.setParameter("webResource", webResource);
            query.setParameter("httpStatusCode", DEFAULT_HTTP_STATUS_VALUE);
            query.setFirstResult(start);
            query.setMaxResults(chunkSize);
            return (List<RelatedContent>) query.getResultList();
        }
        return null;
    }

    public RelatedContent findRelatedContent(WebResource webResource, String uri) {
        if (webResource instanceof Page) {
            Query query = entityManager.createQuery(
                    "SELECT distinct rc FROM "
                    + SSPImpl.class.getName() + " s"
                    + " JOIN s.relatedContentSet rc"
                    + " JOIN s.page w"
                    + " WHERE w=:webResource"
                    + " AND rc.uri=:uri");
            query.setParameter("webResource", webResource);
            query.setParameter("uri", uri);
            try {
                return (RelatedContent) query.getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        } else if (webResource instanceof Site) {
            Query query = entityManager.createQuery(
                    "SELECT distinct rc FROM "
                    + SSPImpl.class.getName() + " s"
                    + " JOIN s.relatedContentSet rc"
                    + " JOIN s.page w"
                    + " JOIN w.parent p"
                    + " WHERE p=:webResource"
                    + " AND rc.uri=:uri");
            query.setParameter("webResource", webResource);
            query.setParameter("uri", uri);
            try {
                return (RelatedContent) query.getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public List<Content> findContentWithRelatedContentFromWebResource(
            WebResource webResource,
            int start,
            int chunkSize) {
        if (webResource instanceof Page) {
            Query query = entityManager.createQuery(
                    "SELECT s FROM "
                    + SSPImpl.class.getName() + " s"
                    + " JOIN s.page w"
                    + " WHERE w=:webResource"
                    + " AND s.httpStatusCode =:httpStatusCode");
            query.setParameter("webResource", webResource);
            query.setParameter("httpStatusCode", HTTP_STATUS_OK);
            query.setFirstResult(start);
            query.setMaxResults(chunkSize);
            List<Content> contentList = query.getResultList();
            for (Content content : contentList) {
                ((SSP) content).getRelatedContentSet().size();
            }
            return contentList;
        } else if (webResource instanceof Site) {
            Query query = entityManager.createQuery(
                    "SELECT s FROM "
                    + SSPImpl.class.getName() + " s"
                    + " JOIN s.page w"
                    + " JOIN w.parent p"
                    + " WHERE p=:webResource"
                    + " AND s.httpStatusCode =:httpStatusCode");
            query.setParameter("webResource", webResource);
            query.setParameter("httpStatusCode", HTTP_STATUS_OK);
            query.setFirstResult(start);
            query.setMaxResults(chunkSize);
            List<Content> contentList = query.getResultList();
            for (Content content : contentList) {
                ((SSP) content).getRelatedContentSet().size();
            }
            return contentList;
        }
        return null;
    }

    /**
     * This native query is used to avoid multiple select before insert realized
     * by hibernate while persisting the content relationship relation.
     * @param ssp
     */
    @Override
    public void saveContentRelationShip(SSP ssp) {
        if (!ssp.getRelatedContentSet().isEmpty()) {
            StringBuilder queryValuesBuilder = new StringBuilder();
            for (RelatedContent relatedContent : ssp.getRelatedContentSet()) {
                queryValuesBuilder.append("(");
                queryValuesBuilder.append(ssp.getId());
                queryValuesBuilder.append(",");
                queryValuesBuilder.append(((Content)relatedContent).getId());
                queryValuesBuilder.append(")");
                queryValuesBuilder.append(",");
            }
            queryValuesBuilder.setCharAt(queryValuesBuilder.length()-1, ';');
            Query query = entityManager.createNativeQuery(
                    INSERT_QUERY+
                    queryValuesBuilder.toString());
            query.executeUpdate();
        }
    }

}