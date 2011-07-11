/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.entity.service.parameterization;

import com.adex.sdk.entity.service.AbstractGenericDataService;
import org.opens.tanaguru.entity.parameterization.ParameterFamily;

/**
 *
 * @author jkowalczyk
 */
public class ParameterFamilyDataServiceImpl extends AbstractGenericDataService<ParameterFamily, Long>
        implements ParameterFamilyDataService{

    @Override
    public ParameterFamily getParameterFamily(String parameterFamilyCode) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}