package org.opens.tanaguru.entity.factory.audit;

import org.opens.tanaguru.entity.audit.ConsolidationRemark;
import org.opens.tanaguru.entity.audit.ConsolidationRemarkImpl;

/**
 * 
 * @author ADEX
 */
public class ConsolidationRemarkFactoryImpl implements ConsolidationRemarkFactory {

	public ConsolidationRemarkFactoryImpl() {
		super();
	}

	public ConsolidationRemark create() {
		return new ConsolidationRemarkImpl();
	}
}
