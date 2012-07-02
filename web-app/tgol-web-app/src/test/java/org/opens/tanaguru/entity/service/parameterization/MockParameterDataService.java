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

import java.util.*;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.parameterization.ParameterFamily;
import org.opens.tanaguru.entity.parameterization.ParameterImpl;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.sdk.entity.factory.GenericFactory;
import org.opens.tgol.entity.contract.ScopeEnum;
import org.opens.tgol.entity.decorator.tanaguru.parameterization.ParameterDataServiceDecorator;

/**
 * General mock environment for Parameters. 
 * 
 * @author jkowalczyk
 */
public class MockParameterDataService implements ParameterDataServiceDecorator {

    Set<Parameter> paramElemSet = new HashSet<Parameter>();
    Map<Long, List<Set<Parameter>>> paramSetByContractId = new HashMap<Long, List<Set<Parameter>>>();
    Set<Parameter> defaultParamSet = new HashSet<Parameter>();
    Set<Parameter> defaultParamSetWithUserRestriction = new HashSet<Parameter>();
    Set<Parameter> otherValuesParamSet = new HashSet<Parameter>();

    ParameterElementDataService parameterElementDataService = 
            new MockParameterElementDataService();
    
    public MockParameterDataService(){
        
        ParameterElement textualFormFieldParameterElement = parameterElementDataService.getParameterElement("TEXTUAL_FORMFIELD");
        ParameterElement levelParameterElement = parameterElementDataService.getParameterElement("LEVEL");
        addParameter(true, "50", textualFormFieldParameterElement, defaultParamSet);
        addParameter(true, "MockRef;MockLevel", levelParameterElement, defaultParamSet);
        // Have a look to the MockContractDataService. Each contract is associated
        // with a param Set. 
        List<Set<Parameter>> paramSetListForUser1 = new LinkedList<Set<Parameter>>();
        paramSetListForUser1.add(defaultParamSet);
        paramSetByContractId.put(Long.valueOf(1), paramSetListForUser1);
        
    }

    private Parameter addParameter (
            boolean isDefault,
            String value,
            ParameterElement parameterElement,
            Set<Parameter> paramSet) {
        Parameter parameter = new ParameterImpl();
        parameter.setDefaultParameterValue(isDefault);
        parameter.setValue(value);
        parameter.setParameterElement(parameterElement);
        paramSet.add(parameter);
        paramElemSet.add(parameter);
        return parameter;
    }

    @Override
    public Parameter create(ParameterElement parameterElement, String value, Audit audit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Parameter getParameter(ParameterElement parameterElement, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Parameter> getParameterSet(ParameterFamily parameterFamily, Audit audit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Parameter> getDefaultParameterSet() {
        return defaultParamSet;
    }

    @Override
    public Parameter getDefaultParameter(ParameterElement parameterElement) {
        for (Parameter param : defaultParamSet) {
            if (param.isDefaultParameterValue()
                    && param.getParameterElement().getParameterElementCode().equals(parameterElement.getParameterElementCode())) {
                return param;
            }
        }
        return null;
    }

    @Override
    public Set<Parameter> getParameterSetFromAudit(Audit audit) {
        return new HashSet<Parameter>();
    }

    @Override
    public Set<Parameter> updateParameterSet(Set<Parameter> paramSet, Set<Parameter> paramsToUpdate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Parameter> updateParameter(Set<Parameter> paramSet, Parameter paramToUpdate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Parameter create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create(Parameter e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Parameter e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long k) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Set<Parameter> set) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<? extends Parameter> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Parameter read(Long k) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Parameter saveOrUpdate(Parameter e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Parameter> saveOrUpdate(Set<Parameter> set) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityDao(GenericDAO<Parameter, Long> gdao) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityFactory(GenericFactory<Parameter> gf) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Parameter update(Parameter e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getLastParameterValueFromUser(Long idContract, ParameterElement parameterElement, ScopeEnum scope) {
        if (scope != ScopeEnum.DOMAIN) {
            return null;
        } else {
            List<Set<Parameter>> paramSetListByUser = paramSetByContractId.get(idContract);
            Set<Parameter> lastParamSet =
                    paramSetListByUser.get(paramSetListByUser.size()-1);
            for (Parameter param : lastParamSet) {
                if (param.getParameterElement().getParameterElementCode().equals(parameterElement.getParameterElementCode())) {
                    return param.getValue();
                }
            }
            return null;
        }
    }

    @Override
    public String getLastParameterValueFromContractAndScenario(Long idContract, ParameterElement parameterElement, String scenarioName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}