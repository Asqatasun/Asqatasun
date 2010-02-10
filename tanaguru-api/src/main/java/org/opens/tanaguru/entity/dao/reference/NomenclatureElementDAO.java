package org.opens.tanaguru.entity.dao.reference;

import java.util.Collection;
import org.opens.tanaguru.entity.reference.Nomenclature;
import org.opens.tanaguru.entity.reference.NomenclatureElement;
import com.adex.sdk.entity.dao.GenericDAO;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface NomenclatureElementDAO extends
		GenericDAO<NomenclatureElement, Long> {

	Collection<NomenclatureElement> retrieveAll(Nomenclature nomenclature,
			String nomenclatureValue);
}
