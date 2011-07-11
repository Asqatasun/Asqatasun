/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.entity.service.parameterization;

import com.adex.sdk.entity.service.AbstractGenericDataService;
import java.util.HashSet;
import java.util.Set;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.factory.parameterization.ParameterFactory;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.dao.parameterization.ParameterDAO;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.parameterization.ParameterFamily;

/**
 *
 * @author jkowalczyk
 */
public class ParameterDataServiceImpl extends AbstractGenericDataService<Parameter, Long>
        implements ParameterDataService {

    @Override
    public Parameter create(ParameterElement parameterElement, String value, Audit audit) {
        return ((ParameterFactory) entityFactory).createParameter(parameterElement, value, audit);
    }

    @Override
    public Parameter getParameter(ParameterElement parameterElement, String value) {
        Parameter parameter = ((ParameterDAO) entityDao).findParameter(parameterElement, value);
        if (parameter == null) {
            return ((ParameterFactory) entityFactory).createParameter(parameterElement, value);
        }
        return parameter;
    }

    @Override
    public Set<Parameter> getParameterSet(ParameterFamily parameterFamily, Audit audit) {
        return ((ParameterDAO) entityDao).findParameterSet(parameterFamily, audit);
    }

    @Override
    public Set<Parameter> getDefaultParameterSet() {
        return ((ParameterDAO) entityDao).findDefaultParameterSet();
    }

    @Override
    public Set<Parameter> getParameterSetFromAudit(Audit audit) {
        return ((ParameterDAO) entityDao).findParameterSetFromAudit(audit);
    }

    @Override
    public Set<Parameter> updateParameterSet(Set<Parameter> paramSet, Set<Parameter> paramsToUpdate) {
        Set<Parameter> paramToRemove = new HashSet<Parameter>();
        Set<String> paramElementToRemove = new HashSet<String>();
        for (Parameter parameter : paramsToUpdate) {
            paramElementToRemove.add(parameter.getParameterElement().getParameterElementCode());
        }
        for (Parameter parameter : paramSet){
            if (paramElementToRemove.contains(parameter.getParameterElement().getParameterElementCode())){
                paramToRemove.add(parameter);
            }
        }
        paramSet.removeAll(paramToRemove);
        paramSet.addAll(paramsToUpdate);
        return paramSet;
    }

    @Override
    public Set<Parameter> updateParameter(Set<Parameter> paramSet, Parameter paramToUpdate) {
        Parameter paramToRemove = null;
        for (Parameter parameter : paramSet) {
            if (parameter.getParameterElement().getParameterElementCode().equals(
                    paramToUpdate.getParameterElement().getParameterElementCode())) {
                paramToRemove = parameter;
                break;
            }
        }
        paramSet.remove(paramToRemove);
        paramSet.add(paramToUpdate);
        return paramSet;
    }

    @Override
    public Parameter getDefaultParameter(ParameterElement parameterElement) {
        return ((ParameterDAO) entityDao).findDefaultParameter(parameterElement);
    }

}