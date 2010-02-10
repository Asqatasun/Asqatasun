package org.opens.tanaguru.i18n.entity.factory;

import com.adex.sdk.entity.i18n.factory.LanguageFactory;
import com.adex.sdk.entity.i18n.Language;
import org.opens.tanaguru.i18n.entity.LanguageImpl;

/**
 * 
 * @author ADEX
 */
public class LanguageFactoryImpl implements LanguageFactory {

	public LanguageFactoryImpl() {
		super();
	}

	public Language create() {
		return new LanguageImpl();
	}
}
