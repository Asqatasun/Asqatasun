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
package org.tanaguru.webapp.entity.dao.contract;

import java.util.Collection;
import java.util.Date;
import org.tanaguru.sdk.entity.dao.GenericDAO;
import org.tanaguru.webapp.entity.contract.Act;
import org.tanaguru.webapp.entity.contract.Contract;
import org.tanaguru.webapp.entity.contract.ScopeEnum;

/**
 *
 * @author jkowalczyk
 */
public interface ActDAO extends GenericDAO<Act, Long> {

    /**
     *
     * @param contract
     * @return
     *      the total number of acts for a given contract
     */
    int findNumberOfAct(Contract contract);
    
    /**
     *
     * @param contract
     * @param scopes
     * @return
     *      the total number of acts for a given contract and scopes
     */
    int findNumberOfActByScope(Contract contract, Collection<ScopeEnum> scopes);

    /**
     *
     * @param contract
     * @return
     */
    Collection<Act> findAllActsByContract(Contract contract);

    /**
     * 
     * @param contract
     * @param nbOfActs
     * @param sortDirection
     * @param scopeEnum
     * @param onlyCompleted
     * @return
     *      a collection of n acts for the given contract, sortDirection and
     *      scopeEnum
     */
    Collection<Act> findActsByContract(
            Contract contract,
            int nbOfActs,
            int sortDirection,
            ScopeEnum scopeEnum,
            boolean onlyCompleted);

    /**
     *
     * @param contract
     * @return
     */
    Collection<Act> findRunningActsByContract(Contract contract);
    
    /**
     * 
     * @param auditId
     * @return
     */
    Act findActFromAudit(Long auditId);

    /**
     * 
     * @param contract
     * @param limitDate
     * @param ip
     * @return
     */
    int findNumberOfActByPeriodAndIp(Contract contract, Date limitDate, String ip);
    
    /**
     * 
     * @param idContract
     * @param scope
     * @return 
     */
    Act findLastActByContract(Long idContract, ScopeEnum scope);
    
    /**
     * 
     * @param idContract
     * @param scenarioName
     * @return 
     */
    Act findLastActByContractAndScenario(Long idContract, String scenarioName);
}
