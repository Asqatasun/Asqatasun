package org.opens.tanaguru.nomenclatureloader;

import org.opens.tanaguru.entity.reference.Nomenclature;

/**
 * 
 * @author ADEX
 */
public interface NomenclatureLoader {

	String getCode();

	Nomenclature getResult();

	void run();

	void setCode(String code);
}
