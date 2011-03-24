package org.opens.tanaguru.service;

import java.util.List;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ProcessResult;
import javax.xml.bind.annotation.XmlTransient;
import org.opens.tanaguru.contentadapter.util.URLIdentifierFactory;
import org.opens.tanaguru.entity.factory.audit.EvidenceElementFactory;
import org.opens.tanaguru.entity.factory.audit.ProcessRemarkFactory;
import org.opens.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.service.audit.EvidenceDataService;
import org.opens.tanaguru.processor.ProcessorFactory;

/**
 * 
 * @author ADEX
 */
@XmlTransient
public interface ProcessorService {// TODO Write javadoc

    /**
     *
     * @param contentList
     * @param testList
     * @return
     */
    List<ProcessResult> process(List<Content> contentList, List<Test> testList);

    void setNomenclatureLoaderService(NomenclatureLoaderService nomenclatureLoaderService);

    void setEvidenceDataService(EvidenceDataService evidenceDataService);

    void setEvidenceElementFactory(EvidenceElementFactory evidenceElementFactory);

    void setProcessRemarkFactory(ProcessRemarkFactory processRemarkFactory);

    void setSourceCodeRemarkFactory(SourceCodeRemarkFactory sourceCodeRemarkFactory);

    void setProcessorFactory(ProcessorFactory processorFactory);

    void setUrlIdentifierFactory(URLIdentifierFactory urlIdentifierFactory);
}
