package org.opens.tanaguru.service;

import org.opens.tanaguru.entity.reference.Nomenclature;
import org.opens.tanaguru.nomenclatureloader.NomenclatureLoader;

/**
 * 
 * @author ADEX
 */
public class NomenclatureLoaderServiceImpl implements NomenclatureLoaderService {

	private NomenclatureLoader nomenclatureLoader;

	public NomenclatureLoaderServiceImpl() {
		super();
	}

	public Nomenclature loadByCode(String code) {
		nomenclatureLoader.setCode(code);
		nomenclatureLoader.run();
		return nomenclatureLoader.getResult();
	}

	public void setNomenclatureLoader(NomenclatureLoader nomenclatureLoader) {
		this.nomenclatureLoader = nomenclatureLoader;
	}
}
