/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.asqatasun.webapp.entity.dao.tanaguru.parameterization;

import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.parameterization.ParameterElement;
import org.asqatasun.sdk.entity.dao.GenericDAO;
import org.asqatasun.webapp.entity.contract.ScopeEnum;

/**
 *
 * @author jkowalczyk
 */
public interface TgolParameterDAO extends GenericDAO<Parameter, Long> {

    /**
     * 
     * @param idContract
     * @param parameterElement
     * @param scope
     * @return
     */
    String findLastParameterValueFromUser(
            Long idContract,
            ParameterElement parameterElement,
            ScopeEnum scope);

    /**
     * 
     * @param contract
     * @param parameterElement
     * @param scenarioName
     * @return 
     */
    String findLastParameterValueFromContractAndScenario(
            Long contract,
            ParameterElement parameterElement,
            String scenarioName);
}