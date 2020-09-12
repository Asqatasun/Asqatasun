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
package org.asqatasun.entity.service.parameterization;

import java.util.Collection;
import java.util.Set;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.contract.ScopeEnum;
import org.asqatasun.entity.option.OptionElement;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.parameterization.ParameterElement;
import org.asqatasun.entity.parameterization.ParameterFamily;
import org.asqatasun.entity.service.GenericDataService;

/**
 * 
 * @author jkowalczyk
 */
public interface ParameterDataService extends GenericDataService<Parameter, Long> {

    /**
     * Create an instance of parameter
     *
     * @param parameterElement
     * @param value
     * @param audit
     * @return
     */
    Parameter create(ParameterElement parameterElement, String value, Audit audit);

    /**
     *
     * @param parameterElement
     * @param value
     * @return
     *      a parameter from the parameter element and its value
     */
    Parameter getParameter(ParameterElement parameterElement, String value);
    
    /**
     * 
     * @param audit
     * @param parameterElementCode
     * @return 
     *      the value of a parameter for a given audit and a given parameter 
     * element
     */
    Parameter getParameter(Audit audit, String parameterElementCode);
    
    /**
     * 
     * @param levelKey
     * @return the parameter for the given level key
     */
    Parameter getLevelParameter(String levelKey);

    /**
     * @param parameterFamily
     * @param audit
     * @return
     *      the list of parameters for an audit from a given parameter family
     */
    Set<Parameter> getParameterSet(ParameterFamily parameterFamily, Audit audit);
    
    /**
     * @param parameterFamily
     * @param paramSet
     * @return
     *      the subset of parameters corresponding to the parameterFamily
     */
    Set<Parameter> getParameterSet(ParameterFamily parameterFamily, Collection<Parameter> paramSet);

    /**
     * @return
     *      the list of default parameters
     */
    Set<Parameter> getDefaultParameterSet();
    
    /**
     * @return
     *      the default parameter for a given parameter element.
     */
    Parameter getDefaultParameter(ParameterElement parameterElement);
    
    /**
     * @return
     *      the default parameter for a given parameter element.
     */
    Parameter getDefaultLevelParameter();

    /**
     * Retrieve the list of parameters for a given audit
     * @return
     */
    Set<Parameter> getParameterSetFromAudit(Audit audit);
    
    /**
     * Retrieve the referential parameter for a given audit
     * @return
     */
    String getReferentialKeyFromAudit(Audit audit);
    
    /**
     * Retrieve the level parameter for a given audit
     * @return
     */
    String getLevelKeyFromAudit(Audit audit);

    /**
     * Update the current paramSet (generally the default one) with other parameters
     * (generally the parameters overridden by the user)
     * @param paramSet
     * @param paramsToUpdate
     * @return
     */
    Set<Parameter> updateParameterSet(Set<Parameter> paramSet, Set<Parameter> paramsToUpdate);

    /**
     * Update the current paramSet (generally the default one) with one parameter
     * (generally the parameters overridden by the user)
     * @param paramSet
     * @param paramToUpdate
     * @return
     */
    Set<Parameter> updateParameter(Set<Parameter> paramSet, Parameter paramToUpdate);

    /**
     *
     * @param ref
     * @param level
     * @return the set of parameters for a given couple of ref and level
     */
    Set<Parameter> getParameterSetFromAuditLevel(String ref, String level);

    /**
     * The default parameter set embeds a depth value that corresponds to the
     * site audit. We need here to replace this parameter by a parameter value
     * equals to 0.
     *
     * @return
     */
    Set<Parameter> getAuditPageParameterSet(Set<Parameter> defaultParameterSet);

    Collection<Parameter> getParameterSetFromOptionElementSet(Collection<OptionElement> optionElementSet);

    String getLastParameterValueFromUser(Long idContract, ParameterElement parameterElement, ScopeEnum scope);
    String getLastParameterValueFromContractAndScenario(Long idContract, ParameterElement parameterElement, String scenarioName);

}
