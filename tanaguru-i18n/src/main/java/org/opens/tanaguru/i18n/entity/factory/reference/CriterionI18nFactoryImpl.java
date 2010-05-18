package org.opens.tanaguru.i18n.entity.factory.reference;

import org.opens.tanaguru.i18n.entity.factory.reference.CriterionI18nFactory;
import org.opens.tanaguru.i18n.entity.reference.CriterionI18n;
import org.opens.tanaguru.i18n.entity.reference.CriterionI18nImpl;

/**
 * 
 * @author ADEX
 */
public class CriterionI18nFactoryImpl implements CriterionI18nFactory {

	public CriterionI18nFactoryImpl() {
		super();
	}

	public CriterionI18n create() {
		return new CriterionI18nImpl();
	}
}
