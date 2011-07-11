/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.entity.service.parameterization;

import com.adex.sdk.entity.service.AbstractGenericDataService;
import org.opens.tanaguru.entity.dao.parameterization.ParameterElementDAO;
import org.opens.tanaguru.entity.factory.parameterization.ParameterElementFactory;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.parameterization.ParameterFamily;

/**
 *
 * @author jkowalczyk
 */
public class ParameterElementDataServiceImpl extends AbstractGenericDataService<ParameterElement, Long>
        implements ParameterElementDataService{

    @Override
    public ParameterElement getParameterElement(String parameterElementCode) {
        return ((ParameterElementDAO) entityDao).findParameterElementFromCode(parameterElementCode);
    }

    @Override
    public ParameterElement create(ParameterFamily parameterFamily) {
        return ((ParameterElementFactory) entityFactory).createParameter(parameterFamily);
    }

}