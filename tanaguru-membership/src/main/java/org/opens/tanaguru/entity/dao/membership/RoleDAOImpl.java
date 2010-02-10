package org.opens.tanaguru.entity.dao.membership;

import org.opens.tanaguru.entity.dao.membership.RoleDAO;
import org.opens.tanaguru.entity.membership.Role;
import org.opens.tanaguru.entity.membership.RoleImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;

public class RoleDAOImpl extends AbstractJPADAO<Role, Long> implements RoleDAO {

	public RoleDAOImpl() {
		super();
	}

	@Override
	protected Class<RoleImpl> getEntityClass() {
		return RoleImpl.class;
	}
}
