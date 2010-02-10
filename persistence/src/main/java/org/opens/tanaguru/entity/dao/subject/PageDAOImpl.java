package org.opens.tanaguru.entity.dao.subject;

import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.PageImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;

public class PageDAOImpl extends AbstractJPADAO<Page, Long> implements PageDAO {

	public PageDAOImpl() {
		super();
	}

	@Override
	protected Class<PageImpl> getEntityClass() {
		return PageImpl.class;
	}
}
