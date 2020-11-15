/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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
package org.asqatasun.entity.service.parameterization;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.contract.ScopeEnum;
import org.asqatasun.entity.dao.parameterization.ParameterDAO;
import org.asqatasun.entity.dao.parameterization.ParameterDAOImpl;
import org.asqatasun.entity.option.OptionElement;
import org.asqatasun.entity.option.OptionElementImpl;
import org.asqatasun.entity.parameterization.factory.ParameterFactory;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.parameterization.ParameterElement;
import org.asqatasun.entity.parameterization.ParameterFamily;
import org.asqatasun.entity.dao.GenericDAO;
import org.asqatasun.entity.service.AbstractGenericDataService;
import org.asqatasun.entity.service.option.OptionElementDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkowalczyk
 */
@Service("parameterDataService")
public class ParameterDataServiceImpl extends AbstractGenericDataService<Parameter, Long>
        implements ParameterDataService {

    private static final String AW22_REF = "Aw22";
    private static final String RGAA22_REF = "Rgaa22";
    private static final String RGAA30_REF = "Rgaa30";
    private static final String RGAA40_REF = "Rgaa40";
    private static String REF = AW22_REF;

    private static final String BRONZE_LEVEL = "Bz";
    private static final String A_LEVEL = "A";
    private static final String SILVER_LEVEL = "Ar";
    private static final String AA_LEVEL = "AA";
    private static final String GOLD_LEVEL = "Or";
    private static final String AAA_LEVEL = "AAA";

    private static final String LEVEL_1 = "LEVEL_1";
    private static final String LEVEL_2 = "LEVEL_2";
    private static final String LEVEL_3 = "LEVEL_3";

    private static final String LEVEL_PARAMETER_ELEMENT_CODE = "LEVEL";

    private static int REF_INDEX_IN_PARAM = 0;
    private static int LEVEL_INDEX_IN_PARAM = 1;
    
    private Parameter defaultLevelParameter;

    @Value("${levelAndRefParameterKey:LEVEL}")
    private String levelAndRefParameterKey;

    @Autowired
    ParameterElementDataService parameterElementDataService;
    @Autowired
    OptionElementDataService optionElementDataService;

    /**
     *
     * @param entityDao
     *            the entity DAO to set
     */
    @Override
    @Autowired
    @Qualifier("parameterDAO")
    public void setEntityDao(GenericDAO<Parameter, Long> entityDao) {
        this.entityDao = entityDao;
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
        Set<Parameter> paramToReturn = new HashSet<>();
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
        Set<Parameter> paramToReturn = new HashSet<>();
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
                if (param.getParameterElement().getParameterElementCode().equals(LEVEL_PARAMETER_ELEMENT_CODE)) {
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

    @Override
    public Set<Parameter> getAuditPageParameterSet(Set<Parameter> defaultParameterSet) {
        ParameterElement parameterElement = parameterElementDataService.getDepthParameterElement();
        Parameter depthParameter = getParameter(parameterElement, "0");
        Set<Parameter> auditPageParamSet = updateParameter(defaultParameterSet, depthParameter);
        return auditPageParamSet;
    }

    @Override
    public Set<Parameter> getParameterSetFromAuditLevel(String ref, String level) {
        if (ref.equalsIgnoreCase(RGAA22_REF)
            || ref.equalsIgnoreCase(RGAA30_REF)
            || ref.equalsIgnoreCase(RGAA40_REF)
        ) {
            if (level.equalsIgnoreCase(BRONZE_LEVEL)) {
                level=A_LEVEL;
            } else if (level.equalsIgnoreCase(SILVER_LEVEL)) {
                level=AA_LEVEL;
            } else if (level.equalsIgnoreCase(GOLD_LEVEL)) {
                level=AAA_LEVEL;
            }
        }
        if (level.equalsIgnoreCase(BRONZE_LEVEL) || level.equalsIgnoreCase(A_LEVEL)) {
            level=LEVEL_1;
        } else if (level.equalsIgnoreCase(SILVER_LEVEL) || level.equalsIgnoreCase(AA_LEVEL)) {
            level=LEVEL_2;
        } else if (level.equalsIgnoreCase(GOLD_LEVEL) || level.equalsIgnoreCase(AAA_LEVEL)) {
            level=LEVEL_3;
        }
        ParameterElement levelParameterElement = parameterElementDataService.getParameterElement(LEVEL_PARAMETER_ELEMENT_CODE);
        Parameter levelParameter = getParameter(levelParameterElement, ref + ";" + level);
        Set<Parameter> paramSet = getDefaultParameterSet();
        return updateParameter(paramSet, levelParameter);
    }

    public Collection<Parameter> getParameterSetFromOptionElementSet(Collection<OptionElement> optionElementSet) {
        Set<Parameter> paramSet = new HashSet<>();
        for (OptionElement optionElement : optionElementSet) {
            ParameterElement pe = parameterElementDataService.getParameterElement(optionElement.getOption().getCode());
            if (pe != null) {
                Parameter p = getParameter(pe, optionElement.getValue());
                p = saveOrUpdate(p);
                paramSet.add(p);
            }
        }
        return paramSet;
    }

    public String getLastParameterValueFromUser(Long idContract, ParameterElement parameterElement, ScopeEnum scope) {
        return ((ParameterDAOImpl) entityDao).findLastParameterValueFromUser(idContract, parameterElement, scope);
    }

    public String getLastParameterValueFromContractAndScenario(Long idContract, ParameterElement parameterElement, String scenarioName) {
        return ((ParameterDAOImpl) entityDao).findLastParameterValueFromContractAndScenario(idContract, parameterElement, scenarioName);
    }
}
