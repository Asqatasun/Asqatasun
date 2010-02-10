package org.opens.tanaguru.entity.dao.membership;

import org.opens.tanaguru.entity.dao.membership.OfficeDAO;
import org.opens.tanaguru.entity.membership.Office;
import org.opens.tanaguru.entity.membership.OfficeImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;

public class OfficeDAOImpl extends AbstractJPADAO<Office, Long> implements
		OfficeDAO {

	public OfficeDAOImpl() {
		super();
	}

	@Override
	protected Class<OfficeImpl> getEntityClass() {
		return OfficeImpl.class;
	}
}
