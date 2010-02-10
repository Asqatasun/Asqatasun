package org.opens.tanaguru.entity.dao.membership;

import org.opens.tanaguru.entity.dao.membership.UserDAO;
import org.opens.tanaguru.entity.membership.User;
import org.opens.tanaguru.entity.membership.UserImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;

public class UserDAOImpl extends AbstractJPADAO<User, Long> implements UserDAO {

	public UserDAOImpl() {
		super();
	}

	@Override
	protected Class<UserImpl> getEntityClass() {
		return UserImpl.class;
	}
}
