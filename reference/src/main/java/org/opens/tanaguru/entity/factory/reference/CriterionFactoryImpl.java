package org.opens.tanaguru.entity.factory.reference;

import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.entity.reference.CriterionImpl;

/**
 * 
 * @author ADEX
 */
public class CriterionFactoryImpl implements CriterionFactory {

	public CriterionFactoryImpl() {
		super();
	}

	public Criterion create() {
		return new CriterionImpl();
	}
}
