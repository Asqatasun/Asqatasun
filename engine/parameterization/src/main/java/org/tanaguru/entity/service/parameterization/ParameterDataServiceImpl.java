/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.entity.service.parameterization;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.dao.parameterization.ParameterDAO;
import org.tanaguru.entity.factory.parameterization.ParameterFactory;
import org.tanaguru.entity.parameterization.Parameter;
import org.tanaguru.entity.parameterization.ParameterElement;
import org.tanaguru.entity.parameterization.ParameterFamily;
import org.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 *
 * @author jkowalczyk
 */
public class ParameterDataServiceImpl extends AbstractGenericDataService<Parameter, Long>
        implements ParameterDataService {

    private static String DEFAULT_LEVEL_AND_REF_PARAM_KEY = "LEVEL";
    private static int REF_INDEX_IN_PARAM = 0;
    private static int LEVEL_INDEX_IN_PARAM = 1;
    
    private Parameter defaultLevelParameter;
    
    private String levelAndRefParameterKey = DEFAULT_LEVEL_AND_REF_PARAM_KEY;
    public String getLevelAndRefParameterKey() {
        return levelAndRefParameterKey;
    }

    public void setLevelAndRefParameterKey(String levelAndRefParameterKey) {
        this.levelAndRefParameterKey = levelAndRefParameterKey;
    }
    
    @Override
    public Parameter create(ParameterElement parameterElement, String value, Audit audit) {
        return ((ParameterFactory) entityFactory).createParameter(parameterElement, value, audit);
    }

    @Override
    public Parameter getParameter(ParameterElement parameterElement, String value) {
        if (value == null) {
            // the set-up forms may return null when a checkbox is not selected.
            // As a checkbox is supposed to handle a state, the null value is 
            // considered as a false
            value=Boolean.FALSE.toString();
        }
        Parameter parameter = ((ParameterDAO) entityDao).findParameter(parameterElement, value);
        if (parameter == null) {
            return ((ParameterFactory) entityFactory).createParameter(parameterElement, value);
        }
        return parameter;
    }
    
    @Override
    public Parameter getParameter(Audit audit, String parameterElementCode) {
        return ((ParameterDAO) entityDao).findParameter(audit, parameterElementCode);
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
    
    @Override
    public Parameter getDefaultLevelParameter() {
        if (defaultLevelParameter == null) {
            for (Parameter param : getDefaultParameterSet()) {
                if (param.getParameterElement().getParameterElementCode().equals(DEFAULT_LEVEL_AND_REF_PARAM_KEY)) {
                    defaultLevelParameter = param;
                    break;
                }
            }
        }
        return defaultLevelParameter;
    }

    @Override
    public Set<Parameter> getParameterSet(ParameterFamily parameterFamily, Collection<Parameter> paramSet) {
        return ((ParameterDAO) entityDao).findParametersFromParameterFamily(parameterFamily, paramSet);
    }

    @Override
    public String getReferentialKeyFromAudit(Audit audit) {
        return getParameter(audit, levelAndRefParameterKey).getValue().split(";")[REF_INDEX_IN_PARAM];
    }

    @Override
    public String getLevelKeyFromAudit(Audit audit) {
        return getParameter(audit, levelAndRefParameterKey).getValue().split(";")[LEVEL_INDEX_IN_PARAM];
    }
    
    @Override
    public Parameter getLevelParameter(String levelKey) {
        return ((ParameterDAO) entityDao).findLevelParameter(levelKey);
    }

}