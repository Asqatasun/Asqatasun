package org.opens.tanaguru.entity.dao.subject;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.subject.WebResourceImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import org.opens.tanaguru.entity.audit.Audit;

public class WebResourceDAOImpl extends AbstractJPADAO<WebResource, Long>
        implements WebResourceDAO {

    public WebResourceDAOImpl() {
        super();
    }

    public WebResource findByUrl(String url) {
        try {
            Query query = entityManager.createQuery(
                    "SELECT wr FROM " +
                    getEntityClass().getName() + " wr"
                    + " left join fetch wr.processResultList pr"
                    + " WHERE wr.url = :url");
            query.setParameter("url", url);
            return (WebResource) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public WebResource findByAuditAndUrl(Audit audit, String url) {
        try {
            Query query = entityManager.createQuery(
                    "SELECT wr FROM " +
                    getEntityClass().getName() + " wr"
                    + " left join fetch wr.processResultList pr"
                    + " WHERE wr.url = :url AND wr.audit = :audit");
            query.setParameter("url", url);
            query.setParameter("audit", audit);
            return (WebResource) query.getSingleResult();
        } catch (NoResultException nre) {
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
                + " inner join fetch wr.processResultList prs"
                + " left join fetch prs.remarkList prk"
                + " left join fetch prk.elementList pr"
                + " WHERE wr.id = :id ORDER BY prk.id");
            query.setParameter("id", key);
            return (WebResource) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }


}
