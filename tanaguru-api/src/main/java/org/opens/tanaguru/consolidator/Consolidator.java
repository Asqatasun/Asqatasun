package org.opens.tanaguru.consolidator;

import java.util.List;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.ruleimplementation.RuleImplementation;

/**
 * 
 * @author ADEX
 */
public interface Consolidator {

    /**
     *
     * @return
     */
    List<ProcessResult> getGrossResultList();

    /**
     *
     * @return
     */
    List<ProcessResult> getResult();

    /**
     *
     * @return
     */
    RuleImplementation getRuleImplementation();

    /**
     *
     */
    void run();

    /**
     *
     * @param grossResultList
     */
    void setGrossResultList(List<ProcessResult> grossResultList);

    /**
     * 
     * @param ruleImplementation
     */
    void setRuleImplementation(RuleImplementation ruleImplementation);
}
