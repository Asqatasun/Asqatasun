package org.opens.tanaguru.entity.service.reference;

import org.opens.tanaguru.entity.reference.Reference;
import com.adex.sdk.entity.service.GenericDataService;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface ReferenceDataService extends
		GenericDataService<Reference, Long> {
    /**
     *
     * @param code
     *            the code to find from
     * @return the found reference
     */
    Reference getByCode(String code);

}