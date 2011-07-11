package org.opens.tanaguru.entity.service.parameterization;

import org.opens.tanaguru.entity.audit.Audit;
import com.adex.sdk.entity.service.GenericDataService;
import java.util.Set;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.parameterization.ParameterFamily;

/**
 * 
 * @author jkowalczyk
 */
public interface ParameterDataService extends GenericDataService<Parameter, Long> {

    /**
     * Create an instance of parameter
     *
     * @param parameterElement
     * @param value
     * @param audit
     * @return
     */
    Parameter create(ParameterElement parameterElement, String value, Audit audit);

    /**
     *
     * @param parameterElement
     * @param audit
     * @return
     *      a parameter from the parameter element and its value
     */
    Parameter getParameter(ParameterElement parameterElement, String value);

    /**
     * @param parameterFamily
     * @param audit
     * @return
     *      the list of parameters for an audit from a given parameter family
     */
    Set<Parameter> getParameterSet(ParameterFamily parameterFamily, Audit audit);

    /**
     * @return
     *      the list of default parameters
     */
    Set<Parameter> getDefaultParameterSet();
    
    /**
     * @return
     *      the default parameter for a given parameter element.
     */
    Parameter getDefaultParameter(ParameterElement parameterElement);

    /**
     * Retrieve the list of parameters for a given audit
     * @return
     */
    Set<Parameter> getParameterSetFromAudit(Audit audit);

    /**
     * Update the current paramSet (generally the default one) with other parameters
     * (generally the parameters overridden by the user)
     * @param paramSet
     * @param paramsToUpdate
     * @return
     */
    Set<Parameter> updateParameterSet(Set<Parameter> paramSet, Set<Parameter> paramsToUpdate);

    /**
     * Update the current paramSet (generally the default one) with one parameter
     * (generally the parameters overridden by the user)
     * @param paramSet
     * @param paramToUpdate
     * @return
     */
    Set<Parameter> updateParameter(Set<Parameter> paramSet, Parameter paramToUpdate);
}