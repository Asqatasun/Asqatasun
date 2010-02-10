package org.opens.tanaguru.i18n.entity.factory.reference;

import org.opens.tanaguru.i18n.entity.factory.reference.TestI18nFactory;
import org.opens.tanaguru.i18n.entity.reference.TestI18n;
import org.opens.tanaguru.i18n.entity.reference.TestI18nImpl;

/**
 * 
 * @author ADEX
 */
public class TestI18nFactoryImpl implements TestI18nFactory {

	public TestI18nFactoryImpl() {
		super();
	}

	public TestI18n create() {
		return new TestI18nImpl();
	}
}
