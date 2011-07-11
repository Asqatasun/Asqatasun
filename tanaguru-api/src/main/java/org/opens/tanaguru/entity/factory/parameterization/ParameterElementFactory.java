package org.opens.tanaguru.entity.factory.parameterization;

import com.adex.sdk.entity.factory.GenericFactory;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.parameterization.ParameterFamily;

/**
 * 
 * @author jkowalczyk
 */
public interface ParameterElementFactory extends GenericFactory<ParameterElement> {

    /**
     *
     * @param parameterElement
     * @param value
     * @return
     */
    ParameterElement createParameter(ParameterFamily parameterFamily);

}