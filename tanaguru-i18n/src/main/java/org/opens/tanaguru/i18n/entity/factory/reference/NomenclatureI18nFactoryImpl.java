package org.opens.tanaguru.i18n.entity.factory.reference;

import org.opens.tanaguru.i18n.entity.factory.reference.NomenclatureI18nFactory;
import org.opens.tanaguru.i18n.entity.reference.NomenclatureI18n;
import org.opens.tanaguru.i18n.entity.reference.NomenclatureI18nImpl;

/**
 * 
 * @author ADEX
 */
public class NomenclatureI18nFactoryImpl implements NomenclatureI18nFactory {

	public NomenclatureI18nFactoryImpl() {
		super();
	}

	public NomenclatureI18n create() {
		return new NomenclatureI18nImpl();
	}
}
