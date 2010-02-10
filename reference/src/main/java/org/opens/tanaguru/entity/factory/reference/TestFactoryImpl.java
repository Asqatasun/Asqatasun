package org.opens.tanaguru.entity.factory.reference;

import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.reference.TestImpl;

/**
 * 
 * @author ADEX
 */
public class TestFactoryImpl implements TestFactory {

	public TestFactoryImpl() {
		super();
	}

	public Test create() {
		return new TestImpl();
	}
}
