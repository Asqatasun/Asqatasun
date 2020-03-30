/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.webapp.entity.decorator.asqatasun.parameterization;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.asqatasun.entity.GenericFactory;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.dao.GenericDAO;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.parameterization.ParameterElement;
import org.asqatasun.entity.parameterization.ParameterFamily;
import org.asqatasun.entity.service.parameterization.ParameterDataService;
import org.asqatasun.entity.service.parameterization.ParameterElementDataService;
import org.asqatasun.webapp.entity.contract.ScopeEnum;
import org.asqatasun.webapp.entity.dao.asqatasun.parameterization.TgolParameterDAO;
import org.asqatasun.webapp.entity.option.OptionElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkowalczyk
 */
@Service(value = "parameterDataServiceDecorator")
public class ParameterDataServiceDecorator {

    private final ParameterDataService decoratedParameterDataService; // the ParameterDataService instance being decorated
    private final ParameterElementDataService parameterElementDataService;
    private final TgolParameterDAO tgolParameterDAO;
    @Autowired
    public ParameterDataServiceDecorator(
            @Qualifier("parameterDataService")ParameterDataService decoratedParameterDataService,
            ParameterElementDataService parameterElementDataService,
            TgolParameterDAO tgolParameterDAO) {
        this.decoratedParameterDataService = decoratedParameterDataService;
        this.parameterElementDataService = parameterElementDataService;
        this.tgolParameterDAO = tgolParameterDAO;
    }

    public String getLastParameterValueFromUser(Long idContract, ParameterElement parameterElement, ScopeEnum scope) {
        return tgolParameterDAO.findLastParameterValueFromUser(idContract, parameterElement, scope);
    }

    public String getLastParameterValueFromContractAndScenario(Long idContract, ParameterElement parameterElement, String scenarioName) {
        return tgolParameterDAO.findLastParameterValueFromContractAndScenario(idContract, parameterElement, scenarioName);
    }

    public Collection<Parameter> getParameterSetFromOptionElementSet(Collection<OptionElement> optionElementSet) {
        Set<Parameter> paramSet = new HashSet<>();
        for (OptionElement optionElement : optionElementSet) {
            ParameterElement pe = parameterElementDataService.getParameterElement(optionElement.getOption().getCode());
            if (pe != null) {
                Parameter p = decoratedParameterDataService.getParameter(pe, optionElement.getValue());
                p = saveOrUpdate(p);
                paramSet.add(p);
            }
        }
        return paramSet;
    }

    public Parameter create(ParameterElement pe, String string, Audit audit) {
        return decoratedParameterDataService.create(pe, string, audit);
    }

    public Parameter getParameter(ParameterElement pe, String string) {
        return decoratedParameterDataService.getParameter(pe, string);
    }

    public Set<Parameter> getParameterSet(ParameterFamily pf, Audit audit) {
        return decoratedParameterDataService.getParameterSet(pf, audit);
    }

    public Set<Parameter> getDefaultParameterSet() {
        return decoratedParameterDataService.getDefaultParameterSet();
    }

    public Parameter getDefaultParameter(ParameterElement pe) {
        return decoratedParameterDataService.getDefaultParameter(pe);
    }

    public Set<Parameter> getParameterSetFromAudit(Audit audit) {
        return decoratedParameterDataService.getParameterSetFromAudit(audit);
    }

    public Set<Parameter> updateParameterSet(Set<Parameter> set, Set<Parameter> set1) {
        return decoratedParameterDataService.updateParameterSet(set, set1);
    }

    public Set<Parameter> updateParameter(Set<Parameter> set, Parameter prmtr) {
        return decoratedParameterDataService.updateParameter(set, prmtr);
    }

    public Set<Parameter> getParameterSetFromAuditLevel(String ref, String level) {
        return decoratedParameterDataService.getParameterSetFromAuditLevel(ref, level);
    }

    public Set<Parameter> getAuditPageParameterSet(Set<Parameter> defaultParameterSet) {
        return decoratedParameterDataService.getAuditPageParameterSet(defaultParameterSet);
    }

    public Set<Parameter> getParameterSet(ParameterFamily parameterFamily, Collection<Parameter> paramSet) {
        return decoratedParameterDataService.getParameterSet(parameterFamily, paramSet);
    }

    public String getLevelKeyFromAudit(Audit audit) {
        return decoratedParameterDataService.getLevelKeyFromAudit(audit);
    }

    public String getReferentialKeyFromAudit(Audit audit) {
        return decoratedParameterDataService.getReferentialKeyFromAudit(audit);
    }

    public Parameter getParameter(Audit audit, String parameterElementCode) {
        return decoratedParameterDataService.getParameter(audit, parameterElementCode);
    }

    public Parameter getLevelParameter(String levelKey) {
        return decoratedParameterDataService.getLevelParameter(levelKey);
    }

    public Parameter getDefaultLevelParameter() {
        return decoratedParameterDataService.getDefaultLevelParameter();
    }

    public Parameter create() {
        return decoratedParameterDataService.create();
    }

    public void create(Parameter entity) {
        decoratedParameterDataService.create(entity);
    }

    public void delete(Parameter entity) {
        decoratedParameterDataService.delete(entity);
    }

    public void delete(Long key) {
        decoratedParameterDataService.delete(key);
    }

    public void delete(Collection <Parameter> entitySet) {
        decoratedParameterDataService.delete(entitySet);
    }

    public Collection <Parameter> findAll() {
        return decoratedParameterDataService.findAll();
    }

    public Parameter read(Long key) {
        return decoratedParameterDataService.read(key);
    }

    public Parameter saveOrUpdate(Parameter entity) {
        return decoratedParameterDataService.saveOrUpdate(entity);
    }

    public Collection <Parameter> saveOrUpdate(Collection <Parameter> entitySet) {
        return decoratedParameterDataService.saveOrUpdate(entitySet);
    }

    public void setEntityDao(GenericDAO <Parameter, Long> dao) {
        decoratedParameterDataService.setEntityDao(dao);
    }

    public void setEntityFactory(GenericFactory <Parameter> factory) {
        decoratedParameterDataService.setEntityFactory(factory);
    }

    public Parameter update(Parameter entity) {
        return decoratedParameterDataService.update(entity);
    }
}
