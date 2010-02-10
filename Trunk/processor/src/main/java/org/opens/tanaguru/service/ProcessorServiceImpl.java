package org.opens.tanaguru.service;

import java.util.ArrayList;
import java.util.List;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.processor.Processor;
import org.opens.tanaguru.ruleimplementation.RuleImplementation;

/**
 * 
 * @author ADEX
 */
public class ProcessorServiceImpl implements ProcessorService {

    protected Processor processor;
    protected RuleImplementationLoaderService ruleImplementationLoaderService;

    public ProcessorServiceImpl() {
        super();
    }

    public List<ProcessResult> process(List<Content> contentList, List<Test> testList) {
        List<ProcessResult> processResultList = new ArrayList<ProcessResult>();

        for (Content content : contentList) {
            if (content instanceof SSP) {
                processor.setSSP((SSP) content);
                for (Test test : testList) {
                    RuleImplementation ruleImplementation = ruleImplementationLoaderService.loadRuleImplementation(test);
                    processor.setRuleImplementation(ruleImplementation);
                    processor.run();
                    processResultList.add(processor.getResult());
                }
            }
        }

        return processResultList;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public void setRuleImplementationLoaderService(RuleImplementationLoaderService ruleImplementationLoaderService) {
        this.ruleImplementationLoaderService = ruleImplementationLoaderService;
    }
}
