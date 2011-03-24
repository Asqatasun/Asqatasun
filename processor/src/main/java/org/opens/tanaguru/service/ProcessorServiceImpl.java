package org.opens.tanaguru.service;

import java.util.ArrayList;
import java.util.List;
import org.opens.tanaguru.contentadapter.util.URLIdentifierFactory;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.factory.audit.EvidenceElementFactory;
import org.opens.tanaguru.entity.factory.audit.ProcessRemarkFactory;
import org.opens.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.service.audit.EvidenceDataService;
import org.opens.tanaguru.processing.ProcessRemarkServiceFactory;
import org.opens.tanaguru.processor.Processor;
import org.opens.tanaguru.processor.ProcessorFactory;
import org.opens.tanaguru.ruleimplementation.RuleImplementation;

/**
 * 
 * @author ADEX
 */
public class ProcessorServiceImpl implements ProcessorService {

    private ProcessorFactory processorFactory;
    private RuleImplementationLoaderService ruleImplementationLoaderService;
    private NomenclatureLoaderService nomenclatureLoaderService;
    private ProcessRemarkFactory processRemarkFactory;
    private SourceCodeRemarkFactory sourceCodeRemarkFactory;
    private EvidenceElementFactory evidenceElementFactory;
    private EvidenceDataService evidenceDataService;
    private URLIdentifierFactory urlIdentifierFactory;

    public ProcessorServiceImpl() {
        super();
    }

    public List<ProcessResult> process(List<Content> contentList, List<Test> testList) {
        List<ProcessResult> processResultList = new ArrayList<ProcessResult>();

        Processor processor = processorFactory.create(ProcessRemarkServiceFactory.create(processRemarkFactory, sourceCodeRemarkFactory, evidenceElementFactory, evidenceDataService), nomenclatureLoaderService, urlIdentifierFactory.create());

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

    public void setRuleImplementationLoaderService(RuleImplementationLoaderService ruleImplementationLoaderService) {
        this.ruleImplementationLoaderService = ruleImplementationLoaderService;
    }

    public void setNomenclatureLoaderService(NomenclatureLoaderService nomenclatureLoaderService) {
        this.nomenclatureLoaderService = nomenclatureLoaderService;
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

    public void setProcessorFactory(ProcessorFactory processorFactory) {
        this.processorFactory = processorFactory;
    }

    public void setUrlIdentifierFactory(URLIdentifierFactory urlIdentifierFactory) {
        this.urlIdentifierFactory = urlIdentifierFactory;
    }
}
