package org.opens.tanaguru.processing;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import org.opens.tanaguru.service.ProcessRemarkService;
import org.opens.tanaguru.entity.factory.audit.EvidenceElementFactory;
import org.opens.tanaguru.entity.factory.audit.ProcessRemarkFactory;
import org.opens.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;
import org.opens.tanaguru.entity.service.audit.EvidenceDataService;

/**
 *
 * @author enzolalay
 */
public class ProcessRemarkServiceFactory {

    public static ProcessRemarkService create(ProcessRemarkFactory processRemarkFactory, SourceCodeRemarkFactory sourceCodeRemarkFactory, EvidenceElementFactory evidenceElementFactory, EvidenceDataService evidenceDataService) {
        return new ProcessRemarkServiceImpl(processRemarkFactory, sourceCodeRemarkFactory, evidenceElementFactory, evidenceDataService);
    }
}
