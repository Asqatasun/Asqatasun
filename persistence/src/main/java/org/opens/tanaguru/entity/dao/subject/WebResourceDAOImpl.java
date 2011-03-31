package org.opens.tanaguru.entity.dao.subject;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.subject.WebResourceImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.List;
import javax.persistence.NonUniqueResultException;
import org.apache.commons.lang.StringUtils;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.subject.PageImpl;

public class WebResourceDAOImpl extends AbstractJPADAO<WebResource, Long>
        implements WebResourceDAO {

    public WebResourceDAOImpl() {
        super();
    }

    public WebResource findByUrl(String url) {
        Query query = entityManager.createQuery(
                "SELECT wr FROM " +
                getEntityClass().getName() + " wr"
                + " left join fetch wr.processResultList pr"
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

    public WebResource findByAuditAndUrl(Audit audit, String url) {
        Query query = entityManager.createQuery(
                "SELECT wr FROM " +
                getEntityClass().getName() + " wr"
                + " left join fetch wr.processResultList pr"
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
                + " left join fetch wr.processResultList prs"
                + " left join fetch prs.remarkList prk"
                + " left join fetch prk.elementList pr"
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

}
