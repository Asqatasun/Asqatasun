package org.opens.tanaguru.i18n.entity.service.reference;

import org.opens.tanaguru.i18n.entity.service.reference.CriterionI18nService;
import org.opens.tanaguru.entity.reference.Criterion;
import com.adex.sdk.entity.i18n.InternationalizedEntity;
import org.opens.tanaguru.i18n.entity.reference.CriterionI18n;
import org.opens.tanaguru.i18n.entity.service.AbstractGenericI18nService;

/**
 * 
 * @author ADEX
 */
public class CriterionI18nServiceImpl extends
		AbstractGenericI18nService<Criterion, Long> implements
		CriterionI18nService {

	public CriterionI18nServiceImpl() {
		super();
	}

	@Override
	protected void mergeI18nContent(Criterion entity,
			InternationalizedEntity<? extends Criterion> i18nContent) {
		entity.setDescription(((CriterionI18n) i18nContent).getDescription());
		entity.setLabel(((CriterionI18n) i18nContent).getLabel());
	}
}
