package org.opens.tanaguru.entity.service.reference;

import org.opens.tanaguru.entity.reference.Nomenclature;
import com.adex.sdk.entity.service.GenericDataService;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface NomenclatureDataService extends
		GenericDataService<Nomenclature, Long> {

	/**
	 * 
	 * @param code
	 *            the code to find from
	 * @return the nomenclature found
	 */
	Nomenclature findByCode(String code);
}
