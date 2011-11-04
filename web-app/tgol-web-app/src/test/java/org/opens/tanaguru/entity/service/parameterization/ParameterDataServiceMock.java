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

import org.opens.tgol.entity.decorator.tanaguru.parameterization.ParameterDataServiceDecorator;
import org.opens.tgol.entity.product.ScopeEnum;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.parameterization.ParameterElementImpl;
import org.opens.tanaguru.entity.parameterization.ParameterFamily;
import org.opens.tanaguru.entity.parameterization.ParameterImpl;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.sdk.entity.factory.GenericFactory;

/**
 *
 * @author jkowalczyk
 */
public class ParameterDataServiceMock implements ParameterDataServiceDecorator {

    Set<Parameter> paramElemSet = new HashSet<Parameter>();
    Map<Long, List<Set<Parameter>>> paramSetByContractId = new HashMap<Long, List<Set<Parameter>>>();
    Set<Parameter> defaultParamSet = new HashSet<Parameter>();
    Set<Parameter> defaultParamSetWithUserRestriction = new HashSet<Parameter>();
    Set<Parameter> otherValuesParamSet = new HashSet<Parameter>();

    public ParameterDataServiceMock(){
        // Level parameters
        ParameterElement levelParameterElement = new ParameterElementImpl();
        levelParameterElement.setParameterElementCode("LEVEL");
        // default level parameter
        addParameter(true, "ref1;level1", levelParameterElement, defaultParamSet);
        addParameter(true, "ref1;level1", levelParameterElement, defaultParamSetWithUserRestriction);
        //other level parameter
        addParameter(false, "ref2;level2", levelParameterElement, otherValuesParamSet);

        //depth parameters
        ParameterElement depthParameterElement = new ParameterElementImpl();
        depthParameterElement.setParameterElementCode("DEPTH");
        // default depth parameter
        addParameter(true, "10", depthParameterElement, defaultParamSet);
        addParameter(true, "10", depthParameterElement, defaultParamSetWithUserRestriction);
        //other level parameter
        addParameter(false, "5", depthParameterElement, otherValuesParamSet);

        // max documents parameters
        ParameterElement maxDocumentsParameterElement = new ParameterElementImpl();
        maxDocumentsParameterElement.setParameterElementCode("MAX_DOCUMENTS");
        // default max documents parameter
        addParameter(true, "1000", maxDocumentsParameterElement, defaultParamSet);
        addParameter(false, "2", maxDocumentsParameterElement, defaultParamSetWithUserRestriction);
        //other max documents parameter
        addParameter(false, "5", maxDocumentsParameterElement, otherValuesParamSet);

        // max duration parameters
        ParameterElement maxDurationParameterElement = new ParameterElementImpl();
        maxDurationParameterElement.setParameterElementCode("MAX_DURATION");
        // default max duration parameter
        addParameter(true, "10000", maxDurationParameterElement, defaultParamSet);
        addParameter(true, "10000", maxDurationParameterElement, defaultParamSetWithUserRestriction);
        // other max duration parameter
        addParameter(false, "100", maxDurationParameterElement, otherValuesParamSet);

        // exclusion regexp parameters
        ParameterElement exlusionRegexpParameterElement = new ParameterElementImpl();
        exlusionRegexpParameterElement.setParameterElementCode("EXCLUSION_REGEXP");
        // default exclusion regexp parameter
        addParameter(true, "", exlusionRegexpParameterElement, defaultParamSet);
        addParameter(true, "", exlusionRegexpParameterElement, defaultParamSetWithUserRestriction);
        // other exclusion regexp parameter
        addParameter(false, "expression1;expression2", exlusionRegexpParameterElement, otherValuesParamSet);

        List<Set<Parameter>> paramSetListForUser1 = new LinkedList<Set<Parameter>>();
        paramSetListForUser1.add(defaultParamSet);
        paramSetListForUser1.add(otherValuesParamSet);
        paramSetByContractId.put(Long.valueOf(1), paramSetListForUser1);

        List<Set<Parameter>> paramSetListForUser2 = new LinkedList<Set<Parameter>>();
        paramSetListForUser2.add(otherValuesParamSet);
        paramSetListForUser2.add(defaultParamSet);
        paramSetByContractId.put(Long.valueOf(2), paramSetListForUser2);

        List<Set<Parameter>> paramSetListForUser3 = new LinkedList<Set<Parameter>>();
        paramSetListForUser3.add(defaultParamSet);
        paramSetListForUser3.add(otherValuesParamSet);
        paramSetByContractId.put(Long.valueOf(3), paramSetListForUser3);

        List<Set<Parameter>> paramSetListForUser4 = new LinkedList<Set<Parameter>>();
        paramSetListForUser4.add(defaultParamSet);
        paramSetListForUser4.add(otherValuesParamSet);
        paramSetByContractId.put(Long.valueOf(4), paramSetListForUser4);

        List<Set<Parameter>> paramSetListForUser5 = new LinkedList<Set<Parameter>>();
        paramSetListForUser5.add(defaultParamSet);
        paramSetListForUser5.add(defaultParamSetWithUserRestriction);
        paramSetByContractId.put(Long.valueOf(5), paramSetListForUser5);

        List<Set<Parameter>> paramSetListForUser6 = new LinkedList<Set<Parameter>>();
        paramSetListForUser6.add(otherValuesParamSet);
        paramSetByContractId.put(Long.valueOf(6), paramSetListForUser6);

        List<Set<Parameter>> paramSetListForUser7 = new LinkedList<Set<Parameter>>();
        paramSetListForUser7.add(otherValuesParamSet);
        paramSetByContractId.put(Long.valueOf(7), paramSetListForUser7);

        List<Set<Parameter>> paramSetListForUser8 = new LinkedList<Set<Parameter>>();
        paramSetListForUser8.add(otherValuesParamSet);
        paramSetByContractId.put(Long.valueOf(8), paramSetListForUser8);
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
        throw new UnsupportedOperationException("Not supported yet.");
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

}