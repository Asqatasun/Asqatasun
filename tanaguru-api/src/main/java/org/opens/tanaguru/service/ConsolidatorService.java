package org.opens.tanaguru.service;

import java.util.List;
import org.opens.tanaguru.entity.audit.ProcessResult;
import javax.xml.bind.annotation.XmlTransient;
import org.opens.tanaguru.consolidator.ConsolidatorFactory;
import org.opens.tanaguru.entity.factory.audit.EvidenceElementFactory;
import org.opens.tanaguru.entity.factory.audit.ProcessRemarkFactory;
import org.opens.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.service.audit.EvidenceDataService;

/**
 * 
 * @author ADEX
 */
@XmlTransient
public interface ConsolidatorService {// TODO Write javadoc

    /**
     *
     * @param grossResultList
     * @param testList
     * @return
     */
    List<ProcessResult> consolidate(List<ProcessResult> grossResultList,
            List<Test> testList);

    /**
     * 
     * @param ruleImplementationLoaderService the rule implementation loader service to set
     */
    void setRuleImplementationLoaderService(RuleImplementationLoaderService ruleImplementationLoaderService);

    void setEvidenceDataService(EvidenceDataService evidenceDataService);

    void setEvidenceElementFactory(EvidenceElementFactory evidenceElementFactory);

    void setProcessRemarkFactory(ProcessRemarkFactory processRemarkFactory);

    void setSourceCodeRemarkFactory(SourceCodeRemarkFactory sourceCodeRemarkFactory);

    void setConsolidatorFactory(ConsolidatorFactory consolidatorFactory);
}
