package org.opens.tanaguru.entity.dao.reference;


import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import org.opens.tanaguru.entity.reference.Scope;
import org.opens.tanaguru.entity.reference.ScopeImpl;

public class ScopeDAOImpl extends AbstractJPADAO<Scope, Long> implements ScopeDAO {

    public ScopeDAOImpl() {
        super();
    }

    @Override
    protected Class<ScopeImpl> getEntityClass() {
        return ScopeImpl.class;
    }
}
