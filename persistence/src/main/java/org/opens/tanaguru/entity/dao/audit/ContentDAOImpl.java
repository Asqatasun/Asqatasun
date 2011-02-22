package org.opens.tanaguru.entity.dao.audit;

import javax.persistence.Query;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ContentImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.List;
import org.opens.tanaguru.entity.audit.SSPImpl;
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

    @Override
    public List<Content> retrieveSSPContentWithRelatedContent(Audit audit, int start, int chunkSize){
        Query query = entityManager.createQuery("SELECT s FROM "
                + SSPImpl.class.getName() + " s"
//                + " LEFT JOIN FETCH s.relatedContentSet as r"
                + " WHERE s.audit = :audit");
        query = query.setParameter("audit",audit);
        query = query.setFirstResult(start);
        query = query.setMaxResults(chunkSize);
        return query.getResultList();
    }

    @Override
    public Long retrieveNumberOfSSPContentFromAudit(Audit audit){
        Query query = entityManager.createQuery("SELECT count(s.id) FROM "
                + SSPImpl.class.getName() + " s"
                + " WHERE s.audit = :audit");
        query = query.setParameter("audit",audit);
        return (Long)query.getSingleResult();
    }

    @Override
    public boolean hasAdaptedSSP(Audit audit) {
        Query query = entityManager.createQuery("SELECT count(s.id) FROM "
                + SSPImpl.class.getName() + " s"
                + " WHERE s.audit = :audit"
                + " AND s.dom != null "
                + " AND s.dom != '' ");
        query = query.setParameter("audit",audit);

         if ((Long)query.getSingleResult() > 0) {
             return true;
         } else {
            return false;
         }
    }

    @Override
    public boolean hasContent(Audit audit) {
        Query query = entityManager.createQuery("SELECT count(s.id) FROM "
                + SSPImpl.class.getName() + " s"
                + " WHERE s.audit = :audit"
                + " AND s.source != null "
                + " AND s.source != '' )");
        query = query.setParameter("audit",audit);
        if ((Long)query.getSingleResult() > 0) {
             return true;
         } else {
            return false;
         }
    }

}
