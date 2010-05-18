package org.opens.tanaguru.i18n.entity.service.reference;

import org.opens.tanaguru.i18n.entity.service.reference.NomenclatureI18nService;
import org.opens.tanaguru.entity.reference.Nomenclature;
import com.adex.sdk.entity.i18n.InternationalizedEntity;
import org.opens.tanaguru.i18n.entity.reference.NomenclatureI18n;
import org.opens.tanaguru.i18n.entity.service.AbstractGenericI18nService;

/**
 * 
 * @author ADEX
 */
public class NomenclatureI18nServiceImpl extends
		AbstractGenericI18nService<Nomenclature, Long> implements
		NomenclatureI18nService {

	public NomenclatureI18nServiceImpl() {
		super();
	}

	@Override
	protected void mergeI18nContent(Nomenclature entity,
			InternationalizedEntity<? extends Nomenclature> i18nContent) {
		entity
				.setDescription(((NomenclatureI18n) i18nContent)
						.getDescription());
		entity.setLongLabel(((NomenclatureI18n) i18nContent).getLongLabel());
		entity.setShortLabel(((NomenclatureI18n) i18nContent).getShortLabel());
	}
}
