package org.opens.tanaguru.persistence.i18n.reference;

import org.opens.tanaguru.i18n.entity.dao.reference.NomenclatureElementI18nDAO;
import org.opens.tanaguru.entity.reference.NomenclatureElement;
import org.opens.tanaguru.i18n.entity.reference.NomenclatureElementI18nImpl;
import org.opens.tanaguru.persistence.i18n.AbstractJPAI18nDAO;

public class NomenclatureElementI18nDAOImpl extends
		AbstractJPAI18nDAO<NomenclatureElement, Long> implements
		NomenclatureElementI18nDAO {

	public NomenclatureElementI18nDAOImpl() {
		super();
	}

	@Override
	protected Class<NomenclatureElementI18nImpl> getEntityClass() {
		return NomenclatureElementI18nImpl.class;
	}
}
