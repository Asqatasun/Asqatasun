package org.opens.tanaguru.processor;

import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.ruleimplementation.RuleImplementation;

/**
 * 
 * @author ADEX
 */
public class ProcessorImpl implements Processor {

    private ProcessResult result;
    private RuleImplementation ruleImplementation;
    private SSPHandler sspHandler;

    public ProcessorImpl(SSPHandler sspHandler) {
        super();
        this.sspHandler = sspHandler;
    }

    public ProcessResult getResult() {
        return result;
    }

    public RuleImplementation getRuleImplementation() {
        return ruleImplementation;
    }

    public SSP getSSP() {
        return sspHandler.getSSP();
    }

    public void run() {
        result = ruleImplementation.process(sspHandler);
    }

    public void setRuleImplementation(RuleImplementation ruleImplementation) {
        this.ruleImplementation = ruleImplementation;
    }

    public void setSSP(SSP ssp) {
        sspHandler.setSSP(ssp);
    }

    public void setSSPHandler(SSPHandler sspHandler) {
        this.sspHandler = sspHandler;
    }
}
