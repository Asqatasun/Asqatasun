package org.opens.tanaguru.i18n.entity.service.reference;

import org.opens.tanaguru.i18n.entity.service.reference.NomenclatureElementI18nService;
import org.opens.tanaguru.entity.reference.NomenclatureElement;
import com.adex.sdk.entity.i18n.InternationalizedEntity;
import org.opens.tanaguru.i18n.entity.reference.NomenclatureElementI18n;
import org.opens.tanaguru.i18n.entity.service.AbstractGenericI18nService;

/**
 * 
 * @author ADEX
 */
public class NomenclatureElementI18nServiceImpl extends
		AbstractGenericI18nService<NomenclatureElement, Long> implements
		NomenclatureElementI18nService {

	public NomenclatureElementI18nServiceImpl() {
		super();
	}

	@Override
	protected void mergeI18nContent(NomenclatureElement entity,
			InternationalizedEntity<? extends NomenclatureElement> i18nContent) {
		entity.setLabel(((NomenclatureElementI18n) i18nContent).getValue());
	}
}
