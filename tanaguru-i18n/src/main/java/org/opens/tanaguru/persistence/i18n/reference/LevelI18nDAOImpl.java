package org.opens.tanaguru.persistence.i18n.reference;

import org.opens.tanaguru.i18n.entity.dao.reference.LevelI18nDAO;
import org.opens.tanaguru.entity.reference.Level;
import org.opens.tanaguru.i18n.entity.reference.LevelI18nImpl;
import org.opens.tanaguru.persistence.i18n.AbstractJPAI18nDAO;

public class LevelI18nDAOImpl extends AbstractJPAI18nDAO<Level, Long> implements
		LevelI18nDAO {

	public LevelI18nDAOImpl() {
		super();
	}

	@Override
	protected Class<LevelI18nImpl> getEntityClass() {
		return LevelI18nImpl.class;
	}
}
