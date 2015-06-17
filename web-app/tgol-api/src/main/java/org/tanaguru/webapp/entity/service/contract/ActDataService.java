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
package org.tanaguru.webapp.entity.service.contract;

import java.util.Collection;
import java.util.Date;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.sdk.entity.service.GenericDataService;
import org.tanaguru.webapp.entity.contract.Act;
import org.tanaguru.webapp.entity.contract.Contract;
import org.tanaguru.webapp.entity.contract.ScopeEnum;

/**
 *
 * @author jkowalczyk
 */
public interface ActDataService extends GenericDataService<Act, Long> {

    /**
     *
     * @param contract
     * @return
     *      the total number of acts for a given contract
     */
    int getNumberOfAct(Contract contract);
    /**
     *
     * @param contract
     * @param scopes
     * @return
     *      the total number of acts for a given contract and scopes
     */
    int getNumberOfActByScope(Contract contract, Collection<ScopeEnum> scopes);
    
    /**
     *
     * @param contract
     * @return
     *          All the acts associated with a contract
     */
    Collection<Act> getAllActsByContract(Contract contract);

    /**
     * 
     * @param contract
     * @param nbOfActs
     * @param sortDirection
     * @param scopeEnum
     * @param onlyCompleted
     * @return
     *
     *      The n occurrences of acts for a given contract, scope and sortDirection
     */
    Collection<Act> getActsByContract(
            Contract contract,
            int nbOfActs,
            int sortDirection,
            ScopeEnum scopeEnum,
            boolean onlyCompleted);

    /**
     *
     * @param contract
     * @return
     *          The running acts for a given contract
     */
    Collection<Act> getRunningActsByContract(Contract contract);

    /**
     * 
     * @param audit
     * @return
     *        The act corresponding to the given audit
     */
    Act getActFromAudit(Audit audit);

    /**
     *
     * @param auditId
     * @return
     *        The act corresponding to the given audit id
     */
    Act getActFromAudit(Long auditId);

    /**
     * 
     * @param contract
     * @param limitDate
     * @param ip
     * @return
     */
    int getNumberOfActByPeriodAndIp(Contract contract, Date limitDate, String ip);
}
