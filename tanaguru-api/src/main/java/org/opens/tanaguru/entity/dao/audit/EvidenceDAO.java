package org.opens.tanaguru.entity.dao.audit;

import java.util.Collection;
import com.adex.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.entity.audit.Evidence;

/**
 * 
 * @author jkowalczyk
 */
public interface EvidenceDAO extends GenericDAO<Evidence, Long> {

	Collection<Evidence> retrieveAllByCode(String code);

	/**
	 * 
	 * @param code
	 *            the code of the evidence to find
	 * @return the evidence found
	 */
	Evidence retrieveByCode(String code);
}
