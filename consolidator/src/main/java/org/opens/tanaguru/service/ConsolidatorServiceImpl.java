package org.opens.tanaguru.service;

import java.util.ArrayList;
import org.opens.tanaguru.consolidator.Consolidator;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.ruleimplementation.RuleImplementation;
import java.util.List;
import org.opens.tanaguru.consolidator.ConsolidatorFactory;
import org.opens.tanaguru.entity.factory.audit.EvidenceElementFactory;
import org.opens.tanaguru.entity.factory.audit.ProcessRemarkFactory;
import org.opens.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.service.audit.EvidenceDataService;
import org.opens.tanaguru.processing.ProcessRemarkServiceFactory;

/**
 * 
 * @author ADEX
 */
public class ConsolidatorServiceImpl implements ConsolidatorService {

    protected RuleImplementationLoaderService ruleImplementationLoaderService;
    private ProcessRemarkFactory processRemarkFactory;
    private SourceCodeRemarkFactory sourceCodeRemarkFactory;
    private EvidenceElementFactory evidenceElementFactory;
    private EvidenceDataService evidenceDataService;
    private ConsolidatorFactory consolidatorFactory;

    public ConsolidatorServiceImpl() {
        super();
    }

    @Override
    public List<ProcessResult> consolidate(List<ProcessResult> grossResultList,
            List<Test> testList) {
        List<ProcessResult> resultList = new ArrayList<ProcessResult>();
        for (Test test : testList) {
            RuleImplementation ruleImplementation = ruleImplementationLoaderService.loadRuleImplementation(test);
            Consolidator consolidator = consolidatorFactory.create(grossResultList, ruleImplementation, ProcessRemarkServiceFactory.create(processRemarkFactory, sourceCodeRemarkFactory, evidenceElementFactory, evidenceDataService));
            consolidator.run();
            resultList.addAll(consolidator.getResult());
        }
        return resultList;
    }

    public void setRuleImplementationLoaderService(RuleImplementationLoaderService ruleImplementationLoaderService) {
        this.ruleImplementationLoaderService = ruleImplementationLoaderService;
    }

    public void setEvidenceDataService(EvidenceDataService evidenceDataService) {
        this.evidenceDataService = evidenceDataService;
    }

    public void setEvidenceElementFactory(EvidenceElementFactory evidenceElementFactory) {
        this.evidenceElementFactory = evidenceElementFactory;
    }

    public void setProcessRemarkFactory(ProcessRemarkFactory processRemarkFactory) {
        this.processRemarkFactory = processRemarkFactory;
    }

    public void setSourceCodeRemarkFactory(SourceCodeRemarkFactory sourceCodeRemarkFactory) {
        this.sourceCodeRemarkFactory = sourceCodeRemarkFactory;
    }

    public void setConsolidatorFactory(ConsolidatorFactory consolidatorFactory) {
        this.consolidatorFactory = consolidatorFactory;
    }
}
