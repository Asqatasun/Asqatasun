package org.opens.tanaguru.entity.factory.reference;

import org.opens.tanaguru.entity.reference.RulePackage;
import org.opens.tanaguru.entity.reference.RulePackageImpl;

/**
 * 
 * @author ADEX
 */
public class RulePackageFactoryImpl implements RulePackageFactory {

	public RulePackageFactoryImpl() {
		super();
	}

	public RulePackage create() {
		return new RulePackageImpl();
	}
}
