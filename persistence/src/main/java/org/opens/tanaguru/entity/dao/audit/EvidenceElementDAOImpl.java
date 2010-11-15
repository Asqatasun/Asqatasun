package org.opens.tanaguru.entity.dao.audit;

import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.EvidenceElementImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.Collection;
import javax.persistence.Query;
import org.opens.tanaguru.entity.audit.Evidence;
import org.opens.tanaguru.entity.audit.SourceCodeRemark;

public class EvidenceElementDAOImpl extends AbstractJPADAO<EvidenceElement, Long> implements
        EvidenceElementDAO {

    public EvidenceElementDAOImpl() {
        super();
    }

    @Override
    protected Class<EvidenceElementImpl> getEntityClass() {
        return EvidenceElementImpl.class;
    }

    public Collection<EvidenceElement> retrieveAll(
            Evidence Evidence, String EvidenceValue) {
        Query query = entityManager.createQuery("SELECT r FROM "
                + getEntityClass().getName()
                + " r WHERE r.value = :EvidenceValue AND r.evidence = :Evidence");
        query.setParameter("EvidenceValue", EvidenceValue);
        query.setParameter("Evidence", Evidence);
        return query.getResultList();
    }

    public Collection<EvidenceElement> retrieveAllBySourceCodeRemark(
            SourceCodeRemark sourceCodeRemark) {
        Query query = entityManager.createQuery("SELECT r FROM "
                + getEntityClass().getName()
                + " r WHERE r.sourceCodeRemark = :sourceCodeRemark");
        query.setParameter("sourceCodeRemark", sourceCodeRemark);
        return query.getResultList();
    }

}
