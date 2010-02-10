package org.opens.tanaguru.persistence.i18n.reference;

import org.opens.tanaguru.i18n.entity.dao.reference.NomenclatureI18nDAO;
import org.opens.tanaguru.entity.reference.Nomenclature;
import org.opens.tanaguru.i18n.entity.reference.NomenclatureI18nImpl;
import org.opens.tanaguru.persistence.i18n.AbstractJPAI18nDAO;

public class NomenclatureI18nDAOImpl extends
		AbstractJPAI18nDAO<Nomenclature, Long> implements NomenclatureI18nDAO {

	public NomenclatureI18nDAOImpl() {
		super();
	}

	@Override
	protected Class<NomenclatureI18nImpl> getEntityClass() {
		return NomenclatureI18nImpl.class;
	}
}
