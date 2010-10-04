package org.opens.tanaguru.entity.dao.audit;

import javax.persistence.Query;

import org.opens.tanaguru.entity.audit.EvidenceImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.Collection;
import org.opens.tanaguru.entity.audit.Evidence;

public class EvidenceDAOImpl extends AbstractJPADAO<Evidence, Long>
        implements EvidenceDAO {

    public EvidenceDAOImpl() {
        super();
    }

    @Override
    protected Class<EvidenceImpl> getEntityClass() {
        return EvidenceImpl.class;
    }

    public Collection<Evidence> retrieveAllByCode(String code) {
        Query query = entityManager.createQuery("SELECT n FROM "
                + getEntityClass().getName() + " n WHERE n.code = :code");
        query.setParameter("code", code);
        return query.getResultList();
    }

    public Evidence retrieveByCode(String code) {
        Query query = entityManager.createQuery("SELECT n FROM "
                + getEntityClass().getName() + " n" + " WHERE n.code = :code");
        query.setParameter("code", code);
        return (Evidence) query.getSingleResult();
    }
}
