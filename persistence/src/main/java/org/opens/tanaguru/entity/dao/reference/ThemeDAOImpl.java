package org.opens.tanaguru.entity.dao.reference;

import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.reference.ThemeImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.Collection;
import javax.persistence.Query;

public class ThemeDAOImpl extends AbstractJPADAO<Theme, Long> implements
        ThemeDAO {

    public ThemeDAOImpl() {
        super();
    }

    @Override
    protected Class<ThemeImpl> getEntityClass() {
        return ThemeImpl.class;
    }

    public Collection<Theme> retrieveAllByCode(String code) {
        Query query = entityManager.createQuery("SELECT r FROM "
                + getEntityClass().getName() + " r WHERE r.code = :code");
        query.setParameter("code", code);
        return query.getResultList();
    }
}
