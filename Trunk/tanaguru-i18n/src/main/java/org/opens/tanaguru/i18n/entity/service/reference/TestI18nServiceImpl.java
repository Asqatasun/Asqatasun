package org.opens.tanaguru.i18n.entity.service.reference;

import org.opens.tanaguru.i18n.entity.service.reference.TestI18nService;
import org.opens.tanaguru.entity.reference.Test;
import com.adex.sdk.entity.i18n.InternationalizedEntity;
import org.opens.tanaguru.i18n.entity.reference.TestI18n;
import org.opens.tanaguru.i18n.entity.service.AbstractGenericI18nService;

/**
 * 
 * @author ADEX
 */
public class TestI18nServiceImpl extends AbstractGenericI18nService<Test, Long>
		implements TestI18nService {

	public TestI18nServiceImpl() {
		super();
	}

	@Override
	protected void mergeI18nContent(Test entity,
			InternationalizedEntity<? extends Test> i18nContent) {
		entity.setDescription(((TestI18n) i18nContent).getDescription());
		entity.setLabel(((TestI18n) i18nContent).getLabel());
	}
}
