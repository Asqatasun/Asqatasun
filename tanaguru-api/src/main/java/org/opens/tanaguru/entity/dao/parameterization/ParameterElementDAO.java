package org.opens.tanaguru.entity.dao.parameterization;

import com.adex.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.entity.parameterization.ParameterElement;

/**
 * 
 * @author jkowalczyk
 */
public interface ParameterElementDAO extends GenericDAO<ParameterElement, Long> {

    ParameterElement findParameterElementFromCode (String parameterElementCode);

}