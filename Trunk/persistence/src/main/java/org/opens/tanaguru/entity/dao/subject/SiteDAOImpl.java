package org.opens.tanaguru.entity.dao.subject;

import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.SiteImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;

public class SiteDAOImpl extends AbstractJPADAO<Site, Long> implements SiteDAO {

	public SiteDAOImpl() {
		super();
	}

	@Override
	protected Class<SiteImpl> getEntityClass() {
		return SiteImpl.class;
	}
}
