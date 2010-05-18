package org.opens.tanaguru.i18n.entity.service.reference;

import org.opens.tanaguru.i18n.entity.service.reference.LevelI18nService;
import org.opens.tanaguru.entity.reference.Level;
import com.adex.sdk.entity.i18n.InternationalizedEntity;
import org.opens.tanaguru.i18n.entity.reference.LevelI18n;
import org.opens.tanaguru.i18n.entity.service.AbstractGenericI18nService;

/**
 * 
 * @author ADEX
 */
public class LevelI18nServiceImpl extends
		AbstractGenericI18nService<Level, Long> implements LevelI18nService {

	public LevelI18nServiceImpl() {
		super();
	}

	@Override
	protected void mergeI18nContent(Level entity,
			InternationalizedEntity<? extends Level> i18nContent) {
		entity.setDescription(((LevelI18n) i18nContent).getDescription());
		entity.setLabel(((LevelI18n) i18nContent).getLabel());
	}
}
