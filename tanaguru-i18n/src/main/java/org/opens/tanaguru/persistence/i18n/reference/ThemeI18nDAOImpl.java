package org.opens.tanaguru.persistence.i18n.reference;

import org.opens.tanaguru.i18n.entity.dao.reference.ThemeI18nDAO;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.i18n.entity.reference.ThemeI18nImpl;
import org.opens.tanaguru.persistence.i18n.AbstractJPAI18nDAO;

public class ThemeI18nDAOImpl extends AbstractJPAI18nDAO<Theme, Long> implements
		ThemeI18nDAO {

	public ThemeI18nDAOImpl() {
		super();
	}

	protected Class<ThemeI18nImpl> getEntityClass() {
		return ThemeI18nImpl.class;
	}
}
