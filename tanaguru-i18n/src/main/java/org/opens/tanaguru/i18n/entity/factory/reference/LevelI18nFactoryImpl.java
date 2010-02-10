package org.opens.tanaguru.i18n.entity.factory.reference;

import org.opens.tanaguru.i18n.entity.factory.reference.LevelI18nFactory;
import org.opens.tanaguru.i18n.entity.reference.LevelI18n;
import org.opens.tanaguru.i18n.entity.reference.LevelI18nImpl;

/**
 * 
 * @author ADEX
 */
public class LevelI18nFactoryImpl implements LevelI18nFactory {

	public LevelI18nFactoryImpl() {
		super();
	}

	public LevelI18n create() {
		return new LevelI18nImpl();
	}
}
