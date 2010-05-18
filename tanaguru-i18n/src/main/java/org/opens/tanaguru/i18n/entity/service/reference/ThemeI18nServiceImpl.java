package org.opens.tanaguru.i18n.entity.service.reference;

import org.opens.tanaguru.i18n.entity.service.reference.ThemeI18nService;
import org.opens.tanaguru.entity.reference.Theme;
import com.adex.sdk.entity.i18n.InternationalizedEntity;
import org.opens.tanaguru.i18n.entity.reference.ThemeI18n;
import org.opens.tanaguru.i18n.entity.service.AbstractGenericI18nService;

/**
 * 
 * @author ADEX
 */
public class ThemeI18nServiceImpl extends
		AbstractGenericI18nService<Theme, Long> implements ThemeI18nService {

	public ThemeI18nServiceImpl() {
		super();
	}

	@Override
	protected void mergeI18nContent(Theme entity,
			InternationalizedEntity<? extends Theme> i18nContent) {
		entity.setDescription(((ThemeI18n) i18nContent).getDescription());
		entity.setLabel(((ThemeI18n) i18nContent).getLabel());
	}
}
