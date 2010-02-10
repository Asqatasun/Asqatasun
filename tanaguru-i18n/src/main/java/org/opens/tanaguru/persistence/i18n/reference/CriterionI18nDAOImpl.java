package org.opens.tanaguru.persistence.i18n.reference;

import org.opens.tanaguru.i18n.entity.dao.reference.CriterionI18nDAO;
import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.i18n.entity.reference.CriterionI18nImpl;
import org.opens.tanaguru.persistence.i18n.AbstractJPAI18nDAO;

/**
 * 
 * @author ADEX
 */
public class CriterionI18nDAOImpl extends AbstractJPAI18nDAO<Criterion, Long>
		implements CriterionI18nDAO {

	public CriterionI18nDAOImpl() {
		super();
	}

	protected Class<CriterionI18nImpl> getEntityClass() {
		return CriterionI18nImpl.class;
	}
}
