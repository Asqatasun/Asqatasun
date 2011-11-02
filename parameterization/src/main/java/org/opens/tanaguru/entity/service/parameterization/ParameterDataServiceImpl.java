/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.entity.service.parameterization;

import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;
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