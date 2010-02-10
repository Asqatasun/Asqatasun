package org.opens.tanaguru.entity.dao.membership;

import org.opens.tanaguru.entity.membership.OfficeGroup;
import org.opens.tanaguru.entity.membership.OfficeGroupImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;

public class GroupDAOImpl extends AbstractJPADAO<OfficeGroup, Long> implements
        GroupDAO {

    public GroupDAOImpl() {
        super();
    }

    @Override
    protected Class<OfficeGroupImpl> getEntityClass() {
        return OfficeGroupImpl.class;
    }
}
