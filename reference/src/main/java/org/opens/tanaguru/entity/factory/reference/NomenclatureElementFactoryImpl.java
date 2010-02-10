package org.opens.tanaguru.entity.factory.reference;

import org.opens.tanaguru.entity.reference.NomenclatureElement;
import org.opens.tanaguru.entity.reference.NomenclatureElementImpl;

/**
 * 
 * @author ADEX
 */
public class NomenclatureElementFactoryImpl implements
		NomenclatureElementFactory {

	public NomenclatureElementFactoryImpl() {
		super();
	}

	public NomenclatureElement create() {
		return new NomenclatureElementImpl();
	}

	public NomenclatureElement create(String value) {
		return new NomenclatureElementImpl(value);
	}
}
