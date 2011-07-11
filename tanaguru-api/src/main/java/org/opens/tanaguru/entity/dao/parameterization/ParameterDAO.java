package org.opens.tanaguru.entity.dao.parameterization;

import com.adex.sdk.entity.dao.GenericDAO;
import java.util.Set;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.parameterization.ParameterFamily;

/**
 * 
 * @author jkowalczyk
 */
public interface ParameterDAO extends GenericDAO<Parameter, Long> {

    /**
     *
     * @param parameterElement
     * @param audit
     * @return
     *      a parameter from a parameterElement and an audit
     */
    Parameter findParameter(ParameterElement parameterElement, String value);

    /**
     * 
     * @param parameterFamily
     * @param audit
     * @return
     *      a collection of parameters for given parameterFamily and audit
     */
    Set<Parameter> findParameterSet(ParameterFamily parameterFamily, Audit audit);

    /**
     *
     * @return
     *      the collection of default parameters
     */
    Set<Parameter> findDefaultParameterSet();

    /**
     *
     * @param audit
     * @return
     *      the collection of parameters for a given audit
     */
    Set<Parameter> findParameterSetFromAudit(Audit audit);

    /**
     * 
     * @param parameterElement
     * @return
     *      the default parameter for a given parameterElement
     */
    Parameter findDefaultParameter(ParameterElement parameterElement);

}