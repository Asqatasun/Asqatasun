package org.opens.tanaguru.entity.dao.reference;

import java.util.Collection;
import org.opens.tanaguru.entity.reference.Nomenclature;
import com.adex.sdk.entity.dao.GenericDAO;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface NomenclatureDAO extends GenericDAO<Nomenclature, Long> {

	Collection<Nomenclature> retrieveAllByCode(String code);

	/**
	 * 
	 * @param code
	 *            the code of the nomenclature to find
	 * @return the nomenclature found
	 */
	Nomenclature retrieveByCode(String code);
}
