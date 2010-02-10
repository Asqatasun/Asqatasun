package org.opens.tanaguru.service;

import org.opens.tanaguru.entity.reference.Nomenclature;
import org.opens.tanaguru.nomenclatureloader.NomenclatureLoader;

/**
 * 
 * @author ADEX
 */
public interface NomenclatureLoaderService {

	Nomenclature loadByCode(String code);

	void setNomenclatureLoader(NomenclatureLoader nomenclatureLoader);
}
