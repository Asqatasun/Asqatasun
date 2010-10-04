package org.opens.tanaguru.entity.service.audit;

import org.opens.tanaguru.entity.audit.Evidence;
import com.adex.sdk.entity.service.GenericDataService;

/**
 * 
 * @author jkowalczyk
 */
public interface EvidenceDataService extends
        GenericDataService<Evidence, Long> {

    /**
     *
     * @param code
     *            the code to find from
     * @return the evidence found
     */
    Evidence findByCode(String code);
}
