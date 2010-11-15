package org.opens.tanaguru.entity.dao.reference;

import org.opens.tanaguru.entity.reference.Level;
import org.opens.tanaguru.entity.reference.LevelImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.Collection;
import javax.persistence.Query;

public class LevelDAOImpl extends AbstractJPADAO<Level, Long> implements
        LevelDAO {

    public LevelDAOImpl() {
        super();
    }

    @Override
    protected Class<LevelImpl> getEntityClass() {
        return LevelImpl.class;
    }

    public Collection<Level> retrieveAllByCode(String code) {
        Query query = entityManager.createQuery("SELECT r FROM "
                + getEntityClass().getName() + " r WHERE r.code = :code");
        query.setParameter("code", code);
        return query.getResultList();
    }
}
