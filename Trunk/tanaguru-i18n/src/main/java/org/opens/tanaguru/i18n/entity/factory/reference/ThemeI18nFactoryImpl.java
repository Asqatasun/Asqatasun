package org.opens.tanaguru.i18n.entity.factory.reference;

import org.opens.tanaguru.i18n.entity.factory.reference.ThemeI18nFactory;
import org.opens.tanaguru.i18n.entity.reference.ThemeI18n;
import org.opens.tanaguru.i18n.entity.reference.ThemeI18nImpl;

/**
 * 
 * @author ADEX
 */
public class ThemeI18nFactoryImpl implements ThemeI18nFactory {

	public ThemeI18nFactoryImpl() {
		super();
	}

	public ThemeI18n create() {
		return new ThemeI18nImpl();
	}
}
