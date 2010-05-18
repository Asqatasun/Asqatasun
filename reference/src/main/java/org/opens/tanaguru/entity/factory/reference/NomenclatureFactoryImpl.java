package org.opens.tanaguru.entity.factory.reference;

import org.opens.tanaguru.entity.factory.reference.*;
import org.opens.tanaguru.entity.reference.Nomenclature;
import org.opens.tanaguru.entity.reference.NomenclatureImpl;

/**
 * 
 * @author ADEX
 */
public class NomenclatureFactoryImpl implements NomenclatureFactory {

	public NomenclatureFactoryImpl() {
		super();
	}

	public Nomenclature create() {
		return new NomenclatureImpl();
	}

	public Nomenclature create(String code) {
		return new NomenclatureImpl(code);
	}
}
