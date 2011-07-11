/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.entity.factory.parameterization;

import org.opens.tanaguru.entity.parameterization.ParameterFamily;
import org.opens.tanaguru.entity.parameterization.ParameterFamilyImpl;

/**
 *
 * @author jkowalczyk
 */
public class ParameterFamilyFactoryImpl implements ParameterFamilyFactory {

    @Override
    public ParameterFamily create() {
        return new ParameterFamilyImpl();
    }

}