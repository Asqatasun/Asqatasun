package org.opens.tanaguru.entity.factory.reference;

import org.opens.tanaguru.entity.reference.Rule;
import org.opens.tanaguru.entity.reference.RuleImpl;

/**
 * 
 * @author ADEX
 */
public class RuleFactoryImpl implements RuleFactory {

	public RuleFactoryImpl() {
		super();
	}

	public Rule create() {
		return new RuleImpl();
	}
}
