package org.opens.tanaguru.entity.dao.reference;

import java.util.Collection;
import org.opens.tanaguru.entity.reference.StandardMessage;
import com.adex.sdk.entity.dao.GenericDAO;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface StandardMessageDAO extends GenericDAO<StandardMessage, Long> {

	/**
	 * 
	 * @param code
	 *            the code to find from
	 * @return the standard message found
	 */
	StandardMessage findByCode(String code);

	Collection<StandardMessage> retrieveAllByCodeAndText(String code,
			String text);
}
