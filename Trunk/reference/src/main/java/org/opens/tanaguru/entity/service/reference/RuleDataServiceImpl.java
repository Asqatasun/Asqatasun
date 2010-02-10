package org.opens.tanaguru.entity.service.reference;

import org.opens.tanaguru.entity.reference.Rule;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.dao.reference.RuleDAO;
import com.adex.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author ADEX
 */
public class RuleDataServiceImpl extends AbstractGenericDataService<Rule, Long>
		implements RuleDataService {

	public RuleDataServiceImpl() {
		super();
	}

	public Rule find(Test test) {
		return ((RuleDAO) entityDao).retrieve(test);
	}

	@Override
	public Rule read(Long key) {
		Rule entity = super.read(key);
		for (Test test : entity.getTestList()) {
		}
		return entity;
	}
}
