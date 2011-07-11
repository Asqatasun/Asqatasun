/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.entity.factory.parameterization;

import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.parameterization.ParameterElementImpl;
import org.opens.tanaguru.entity.parameterization.ParameterFamily;

/**
 *
 * @author jkowalczyk
 */
public class ParameterElementFactoryImpl implements ParameterElementFactory{

    @Override
    public ParameterElement create() {
        return new ParameterElementImpl();
    }

    @Override
    public ParameterElement createParameter(ParameterFamily parameterFamily) {
        ParameterElement parameterElement =  new ParameterElementImpl();
        parameterElement.setParameterFamily(parameterFamily);
        return parameterElement;
    }

}