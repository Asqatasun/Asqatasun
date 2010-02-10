package org.opens.tanaguru.processor;

import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.ruleimplementation.RuleImplementation;

/**
 * 
 * @author ADEX
 */
public interface Processor {

	ProcessResult getResult();

	RuleImplementation getRuleImplementation();

	SSP getSSP();

	void run();

	void setRuleImplementation(RuleImplementation ruleImplementation);

	void setSSP(SSP ssp);

	void setSSPHandler(SSPHandler sspHandler);
}
