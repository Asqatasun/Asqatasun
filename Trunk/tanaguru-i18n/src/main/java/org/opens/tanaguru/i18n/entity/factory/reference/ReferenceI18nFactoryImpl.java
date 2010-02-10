package org.opens.tanaguru.i18n.entity.factory.reference;

import org.opens.tanaguru.i18n.entity.factory.reference.ReferenceI18nFactory;
import org.opens.tanaguru.i18n.entity.reference.ReferenceI18n;
import org.opens.tanaguru.i18n.entity.reference.ReferenceI18nImpl;

/**
 * 
 * @author ADEX
 */
public class ReferenceI18nFactoryImpl implements ReferenceI18nFactory {

	public ReferenceI18nFactoryImpl() {
		super();
	}

	public ReferenceI18n create() {
		return new ReferenceI18nImpl();
	}
}
