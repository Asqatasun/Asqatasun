package org.opens.tanaguru.entity.dao.reference;

import java.util.Collection;
import org.opens.tanaguru.entity.reference.Criterion;
import com.adex.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.entity.reference.Reference;
import org.opens.tanaguru.entity.reference.Theme;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface CriterionDAO extends GenericDAO<Criterion, Long> {

	Collection<Criterion> retrieveAll(String code, Reference reference,
			Theme theme);
}
