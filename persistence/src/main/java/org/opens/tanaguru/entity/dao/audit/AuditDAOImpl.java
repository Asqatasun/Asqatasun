package org.opens.tanaguru.entity.dao.audit;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditImpl;
import org.opens.tanaguru.entity.audit.AuditStatus;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class AuditDAOImpl extends AbstractJPADAO<Audit, Long> implements
        AuditDAO {

    public AuditDAOImpl() {
        super();
    }

    @Override
    public List<Audit> findAll() {
        List<Audit> auditList = super.findAll();
        return auditList;
    }

    @Override
    public List<Audit> findAll(AuditStatus status) {
        Query query = entityManager.createQuery("SELECT o FROM "
                + getEntityClass().getName() + " o"
                + " LEFT JOIN FETCH o.subject"
                + " WHERE o.status = :status");
        query.setParameter("status", status);
        return (List<Audit>)query.getResultList();
    }

    @Override
    protected Class<AuditImpl> getEntityClass() {
        return AuditImpl.class;
    }

    @Override
    public Audit findAuditWithWebResource(Long id) {
        Query query = entityManager.createQuery("SELECT a FROM "
                + getEntityClass().getName() + " a"
                + " LEFT JOIN FETCH a.subject"
                + " WHERE a.id = :id");
        query.setParameter("id", id);
        try {
            return (Audit)query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public Audit findAuditWithTest(Long id) {
        Query query = entityManager.createQuery("SELECT a FROM "
                + getEntityClass().getName() + " a"
                + " LEFT JOIN FETCH a.testList"
                + " WHERE a.id = :id");
        query.setParameter("id", id);
        try {
            return (Audit)query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
