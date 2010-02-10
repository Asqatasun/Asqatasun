package org.opens.tanaguru.service;

import java.util.ArrayList;
import org.opens.tanaguru.consolidator.Consolidator;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.ruleimplementation.RuleImplementation;
import java.util.List;
import org.opens.tanaguru.entity.reference.Test;

/**
 * 
 * @author ADEX
 */
public class ConsolidatorServiceImpl implements ConsolidatorService {

    protected Consolidator consolidator;
    protected RuleImplementationLoaderService ruleImplementationLoaderService;

    public ConsolidatorServiceImpl() {
        super();
    }

    public List<ProcessResult> consolidate(List<ProcessResult> grossResultList,
            List<Test> testList) {
        List<ProcessResult> resultList = new ArrayList<ProcessResult>();
        for (Test test : testList) {
            RuleImplementation ruleImplementation = ruleImplementationLoaderService.loadRuleImplementation(test);
            consolidator.setGrossResultList(grossResultList);
            consolidator.setRuleImplementation(ruleImplementation);
            consolidator.run();
            resultList.addAll(consolidator.getResult());
        }
        return resultList;
    }

    public void setConsolidator(Consolidator consolidator) {
        this.consolidator = consolidator;
    }

    public void setRuleImplementationLoaderService(RuleImplementationLoaderService ruleImplementationLoaderService) {
        this.ruleImplementationLoaderService = ruleImplementationLoaderService;
    }
}
