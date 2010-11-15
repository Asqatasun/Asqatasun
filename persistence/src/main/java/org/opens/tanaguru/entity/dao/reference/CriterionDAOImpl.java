package org.opens.tanaguru.entity.dao.reference;

import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.entity.reference.CriterionImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.Collection;
import javax.persistence.Query;
import org.opens.tanaguru.entity.reference.Reference;
import org.opens.tanaguru.entity.reference.Theme;

public class CriterionDAOImpl extends AbstractJPADAO<Criterion, Long> implements
        CriterionDAO {

    public CriterionDAOImpl() {
        super();
    }

    @Override
    protected Class<CriterionImpl> getEntityClass() {
        return CriterionImpl.class;
    }

    public Collection<Criterion> retrieveAll(String code, Reference reference,
            Theme theme) {
        Query query = entityManager.createQuery("SELECT r FROM "
                + getEntityClass().getName()
                + " r WHERE r.code = :code AND r.reference = :reference AND r.theme = :theme");
        query.setParameter("code", code);
        query.setParameter("reference", reference);
        query.setParameter("theme", theme);
        return query.getResultList();
    }
}
