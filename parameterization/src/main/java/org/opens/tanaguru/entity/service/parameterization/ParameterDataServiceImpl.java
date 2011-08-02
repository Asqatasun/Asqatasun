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
    public Set<Parameter> updateParameterSet(final Set<Parameter> paramSet, final Set<Parameter> paramsToUpdate) {
        Set<Parameter> paramToReturn = new HashSet<Parameter>();
        for (Parameter parameter : paramSet){
            boolean found = false;
            for (Parameter paramToUpdate : paramsToUpdate) {
              if (parameter.getParameterElement().getParameterElementCode().equals(
                    paramToUpdate.getParameterElement().getParameterElementCode())) {
                  paramToReturn.add(paramToUpdate);
                  found = true;
                  break;
              }
            }
            if (!found) {
                paramToReturn.add(parameter);
            }
        }
        return paramToReturn;
    }

    @Override
    public Set<Parameter> updateParameter(final Set<Parameter> paramSet, final Parameter paramToUpdate) {
        Set<Parameter> paramToReturn = new HashSet<Parameter>();
        for (Parameter parameter : paramSet) {
            if (parameter.getParameterElement().getParameterElementCode().equals(
                    paramToUpdate.getParameterElement().getParameterElementCode())) {
                paramToReturn.add(paramToUpdate);
            } else {
                paramToReturn.add(parameter);
            }
        }
        return paramToReturn;
    }

    @Override
    public Parameter getDefaultParameter(ParameterElement parameterElement) {
        return ((ParameterDAO) entityDao).findDefaultParameter(parameterElement);
    }

}