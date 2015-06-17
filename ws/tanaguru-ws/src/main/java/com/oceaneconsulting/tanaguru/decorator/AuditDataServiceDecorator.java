package com.oceaneconsulting.tanaguru.decorator;

import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.service.audit.AuditDataService;

/**
 * This interface decorates the AuditDataService interface to expose the 
 * audit result with all its related data
 *
 * @author jkowalczyk
 *
 */
public interface AuditDataServiceDecorator extends AuditDataService {

    /**
     * 
     * @param id
     * @return a POJO audit instance with its dependencies (WebResource, 
     * Parameter, ProcessResult, ProcessRemark, EvidenceElement)
     */
    Audit getAuditFullDeep(Long id);
}
