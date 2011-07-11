package org.opens.tanaguru.entity.factory.parameterization;

import com.adex.sdk.entity.factory.GenericFactory;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.parameterization.ParameterElement;

/**
 * 
 * @author jkowalczyk
 */
public interface ParameterFactory extends GenericFactory<Parameter> {

    /**
     * 
     * @param parameterElement
     * @param value
     * @return
     */
    Parameter  createParameter(ParameterElement parameterElement, String value);
    
    /**
     *
     * @param parameterElement
     * @param value
     * @param audit
     * @return
     */
    Parameter  createParameter(ParameterElement parameterElement, String value, Audit audit);

}