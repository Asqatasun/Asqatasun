/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.entity.factory.parameterization;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.parameterization.ParameterImpl;

/**
 *
 * @author jkowalczyk
 */
public class ParameterFactoryImpl implements ParameterFactory {

    @Override
    public Parameter create() {
        return new ParameterImpl();
    }

    @Override
    public Parameter createParameter(ParameterElement parameterElement, String value) {
        Parameter parameter = new ParameterImpl();
        parameter.setDefaultParameterValue(false);
        parameter.setParameterElement(parameterElement);
        parameter.setValue(value);
        return parameter;
    }

    @Override
    public Parameter createParameter(ParameterElement parameterElement, String value, Audit audit) {
        Parameter parameter = createParameter(parameterElement, value);
        audit.addParameter(parameter);
        return parameter;
    }

}