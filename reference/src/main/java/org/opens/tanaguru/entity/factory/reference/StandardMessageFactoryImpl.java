package org.opens.tanaguru.entity.factory.reference;

import org.opens.tanaguru.entity.reference.StandardMessage;
import org.opens.tanaguru.entity.reference.StandardMessageImpl;

/**
 * 
 * @author ADEX
 */
public class StandardMessageFactoryImpl implements StandardMessageFactory {

	public StandardMessageFactoryImpl() {
		super();
	}

	public StandardMessage create() {
		return new StandardMessageImpl();
	}

	public StandardMessage create(String code, String text) {
		return new StandardMessageImpl(code, text);
	}
}
