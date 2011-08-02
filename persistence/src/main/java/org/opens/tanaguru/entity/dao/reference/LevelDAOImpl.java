package org.opens.tanaguru.entity.dao.reference;

import org.opens.tanaguru.entity.reference.Level;
import org.opens.tanaguru.entity.reference.LevelImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
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

    @Override
    public Level retrieveByCode(String code) {
        Query query = entityManager.createQuery("SELECT r FROM "
                + getEntityClass().getName() + " r WHERE r.code = :code");
        query.setParameter("code", code);
        return (Level)query.getSingleResult();
    }

}