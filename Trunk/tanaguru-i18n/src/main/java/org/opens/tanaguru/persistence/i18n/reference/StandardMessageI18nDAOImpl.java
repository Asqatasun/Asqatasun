package org.opens.tanaguru.persistence.i18n.reference;

import org.opens.tanaguru.i18n.entity.dao.reference.StandardMessageI18nDAO;
import org.opens.tanaguru.entity.reference.StandardMessage;
import org.opens.tanaguru.i18n.entity.reference.StandardMessageI18nImpl;
import org.opens.tanaguru.persistence.i18n.AbstractJPAI18nDAO;

public class StandardMessageI18nDAOImpl extends
		AbstractJPAI18nDAO<StandardMessage, Long> implements
		StandardMessageI18nDAO {

	public StandardMessageI18nDAOImpl() {
		super();
	}

	@Override
	protected Class<StandardMessageI18nImpl> getEntityClass() {
		return StandardMessageI18nImpl.class;
	}
}
