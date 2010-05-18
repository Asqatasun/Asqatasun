package org.opens.tanaguru.entity.service.reference;

import org.opens.tanaguru.entity.reference.StandardMessage;
import com.adex.sdk.entity.service.GenericDataService;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface StandardMessageDataService extends
		GenericDataService<StandardMessage, Long> {

	/**
	 * 
	 * @param code
	 *            the code to find
	 * @return the standard message found
	 */
	StandardMessage findByCode(String code);
}
