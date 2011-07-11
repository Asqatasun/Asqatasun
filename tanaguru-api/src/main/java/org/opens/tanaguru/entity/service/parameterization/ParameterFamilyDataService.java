package org.opens.tanaguru.entity.service.parameterization;

import com.adex.sdk.entity.service.GenericDataService;
import org.opens.tanaguru.entity.parameterization.ParameterFamily;

/**
 * 
 * @author jkowalczyk
 */
public interface ParameterFamilyDataService extends GenericDataService<ParameterFamily, Long> {

    /**
     * Retrieve a parameter family from its code
     * 
     * @param parameterFamilyCode
     * @return
     */
    ParameterFamily getParameterFamily(String parameterFamilyCode);

}