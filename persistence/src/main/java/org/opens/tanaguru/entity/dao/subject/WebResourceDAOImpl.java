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
                    "SELECT w FROM " +
                    getEntityClass().getName() +
                    " w WHERE w.url = :url");
            query.setParameter("url", url);
            return (WebResource) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public WebResource findByAuditAndUrl(Audit audit, String url) {
        try {
            Query query = entityManager.createQuery(
                    "SELECT w FROM " +
                    getEntityClass().getName() +
                    " w WHERE w.url = :url AND w.audit = :audit");
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
        return super.read(key);
    }


}
