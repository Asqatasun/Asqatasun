package org.opens.tanaguru.entity.service.reference;

import org.opens.tanaguru.entity.reference.Rule;
import org.opens.tanaguru.entity.reference.Test;
import com.adex.sdk.entity.service.GenericDataService;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface RuleDataService extends GenericDataService<Rule, Long> {

	/**
	 * 
	 * @param test
	 *            the test to find the rule from
	 * @return the rule found
	 */
	Rule find(Test test);
}
