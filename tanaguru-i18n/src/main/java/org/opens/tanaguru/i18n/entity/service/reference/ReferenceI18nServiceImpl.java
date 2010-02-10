package org.opens.tanaguru.i18n.entity.service.reference;

import org.opens.tanaguru.i18n.entity.service.reference.ReferenceI18nService;
import org.opens.tanaguru.entity.reference.Reference;
import com.adex.sdk.entity.i18n.InternationalizedEntity;
import org.opens.tanaguru.i18n.entity.reference.ReferenceI18n;
import org.opens.tanaguru.i18n.entity.service.AbstractGenericI18nService;

/**
 * 
 * @author ADEX
 */
public class ReferenceI18nServiceImpl extends
		AbstractGenericI18nService<Reference, Long> implements
		ReferenceI18nService {

	public ReferenceI18nServiceImpl() {
		super();
	}

	@Override
	protected void mergeI18nContent(Reference entity,
			InternationalizedEntity<? extends Reference> i18nContent) {
		entity.setDescription(((ReferenceI18n) i18nContent).getDescription());
		entity.setLabel(((ReferenceI18n) i18nContent).getLabel());
	}
}
