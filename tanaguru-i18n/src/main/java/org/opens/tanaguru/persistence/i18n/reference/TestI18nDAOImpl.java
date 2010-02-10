package org.opens.tanaguru.persistence.i18n.reference;

import org.opens.tanaguru.i18n.entity.dao.reference.TestI18nDAO;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.i18n.entity.reference.TestI18nImpl;
import org.opens.tanaguru.persistence.i18n.AbstractJPAI18nDAO;

public class TestI18nDAOImpl extends AbstractJPAI18nDAO<Test, Long> implements
		TestI18nDAO {

	public TestI18nDAOImpl() {
		super();
	}

	@Override
	protected Class<TestI18nImpl> getEntityClass() {
		return TestI18nImpl.class;
	}
}
