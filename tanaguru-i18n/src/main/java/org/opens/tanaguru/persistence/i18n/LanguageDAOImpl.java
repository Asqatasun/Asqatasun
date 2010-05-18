package org.opens.tanaguru.persistence.i18n;

import com.adex.sdk.entity.i18n.dao.LanguageDAO;
import com.adex.sdk.entity.i18n.Language;
import org.opens.tanaguru.i18n.entity.LanguageImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;

/**
 * 
 * @author ADEX
 */
public class LanguageDAOImpl extends AbstractJPADAO<Language, Long> implements
		LanguageDAO {

	public LanguageDAOImpl() {
		super();
	}

	@Override
	protected Class<LanguageImpl> getEntityClass() {
		return LanguageImpl.class;
	}
}
