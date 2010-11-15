package org.opens.tanaguru.entity.dao.audit;

import javax.persistence.Query;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ContentImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import org.opens.tanaguru.entity.subject.WebResource;

public class ContentDAOImpl extends AbstractJPADAO<Content, Long> implements
        ContentDAO {

    public ContentDAOImpl() {
        super();
    }

    public Content find(Audit audit, String uri) {
        Query query = entityManager.createQuery("SELECT c FROM "
                + getEntityClass().getName() + " c"
                + " WHERE c.audit = :audit AND c.uri = :uri");
        query.setParameter("audit", audit);
        query.setParameter("uri", uri);
        return (Content) query.getSingleResult();
    }

    public Content find(WebResource page, String uri) {
        Query query = entityManager.createQuery("SELECT c FROM "
                + getEntityClass().getName() + " c"
                + " WHERE c.page = :page AND c.uri = :uri");
        query.setParameter("page", page);
        query.setParameter("uri", uri);
        return (Content) query.getSingleResult();
    }

    @Override
    protected Class<ContentImpl> getEntityClass() {
        return ContentImpl.class;
    }
}
