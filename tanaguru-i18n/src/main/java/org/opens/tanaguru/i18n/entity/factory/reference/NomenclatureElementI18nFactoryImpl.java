package org.opens.tanaguru.i18n.entity.factory.reference;

import org.opens.tanaguru.i18n.entity.factory.reference.NomenclatureElementI18nFactory;
import org.opens.tanaguru.i18n.entity.reference.NomenclatureElementI18n;
import org.opens.tanaguru.i18n.entity.reference.NomenclatureElementI18nImpl;

/**
 * 
 * @author ADEX
 */
public class NomenclatureElementI18nFactoryImpl implements
		NomenclatureElementI18nFactory {

	public NomenclatureElementI18nFactoryImpl() {
		super();
	}

	public NomenclatureElementI18n create() {
		return new NomenclatureElementI18nImpl();
	}
}
