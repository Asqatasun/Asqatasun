package org.opens.tanaguru.entity.service.parameterization;

import com.adex.sdk.entity.service.GenericDataService;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.parameterization.ParameterFamily;

/**
 * 
 * @author jkowalczyk
 */
public interface ParameterElementDataService extends GenericDataService<ParameterElement, Long> {

    ParameterElement create(ParameterFamily parameterFamily);

    /**
     * Retrieve a parameter element from its code
     *
     * @param parameterElementCode
     * @return
     */
    ParameterElement getParameterElement(String parameterElementCode);

}