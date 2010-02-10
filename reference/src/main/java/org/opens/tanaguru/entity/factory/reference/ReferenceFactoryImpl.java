package org.opens.tanaguru.entity.factory.reference;

import org.opens.tanaguru.entity.reference.Reference;
import org.opens.tanaguru.entity.reference.ReferenceImpl;

/**
 * 
 * @author ADEX
 */
public class ReferenceFactoryImpl implements ReferenceFactory {

	public ReferenceFactoryImpl() {
		super();
	}

	public Reference create() {
		return new ReferenceImpl();
	}
}
