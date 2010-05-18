package org.opens.tanaguru.persistence.i18n.reference;

import org.opens.tanaguru.i18n.entity.dao.reference.ReferenceI18nDAO;
import org.opens.tanaguru.entity.reference.Reference;
import org.opens.tanaguru.i18n.entity.reference.ReferenceI18nImpl;
import org.opens.tanaguru.persistence.i18n.AbstractJPAI18nDAO;

public class ReferenceI18nDAOImpl extends AbstractJPAI18nDAO<Reference, Long>
		implements ReferenceI18nDAO {

	public ReferenceI18nDAOImpl() {
		super();
	}

	@Override
	protected Class<ReferenceI18nImpl> getEntityClass() {
		return ReferenceI18nImpl.class;
	}
}
