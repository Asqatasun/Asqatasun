package org.opens.tanaguru.entity.dao.reference;

import org.opens.tanaguru.entity.reference.NomenclatureElement;
import org.opens.tanaguru.entity.reference.NomenclatureElementImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.Collection;
import javax.persistence.Query;
import org.opens.tanaguru.entity.reference.Nomenclature;

public class NomenclatureElementDAOImpl extends AbstractJPADAO<NomenclatureElement, Long>
        implements NomenclatureElementDAO {

    public NomenclatureElementDAOImpl() {
        super();
    }

    @Override
    protected Class<NomenclatureElementImpl> getEntityClass() {
        return NomenclatureElementImpl.class;
    }

    public Collection<NomenclatureElement> retrieveAll(
            Nomenclature nomenclature, String nomenclatureValue) {
        Query query = entityManager.createQuery("SELECT r FROM "
                + getEntityClass().getName()
                + " r WHERE r.nomenclatureValue = :nomenclatureValue AND r.nomenclature = :nomenclature");
        query.setParameter("nomenclatureValue", nomenclatureValue);
        query.setParameter("nomenclature", nomenclature);
        return query.getResultList();
    }
}
