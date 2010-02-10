package org.opens.tanaguru.i18n.entity.service.reference;

import org.opens.tanaguru.i18n.entity.service.reference.StandardMessageI18nService;
import org.opens.tanaguru.entity.reference.StandardMessage;
import com.adex.sdk.entity.i18n.InternationalizedEntity;
import org.opens.tanaguru.i18n.entity.reference.StandardMessageI18n;
import org.opens.tanaguru.i18n.entity.service.AbstractGenericI18nService;

/**
 * 
 * @author ADEX
 */
public class StandardMessageI18nServiceImpl extends
		AbstractGenericI18nService<StandardMessage, Long> implements
		StandardMessageI18nService {

	public StandardMessageI18nServiceImpl() {
		super();
	}

	@Override
	protected void mergeI18nContent(StandardMessage entity,
			InternationalizedEntity<? extends StandardMessage> i18nContent) {
		entity.setLabel(((StandardMessageI18n) i18nContent).getLabel());
		entity.setText(((StandardMessageI18n) i18nContent).getText());
	}
}
