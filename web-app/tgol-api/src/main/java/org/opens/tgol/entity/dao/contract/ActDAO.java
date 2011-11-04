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
package org.opens.tgol.entity.dao.contract;

import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import org.opens.tgol.entity.contract.Act;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.product.ScopeEnum;
import java.util.Collection;
import java.util.Date;

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
     * @param product
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
     * @param webresourceId
     * @return
     */
    Act findActFromWebresource(Long webResourceId);

    /**
     * 
     * @param contract
     * @param limitDate
     * @param ip
     * @return
     */
    int findNumberOfActByPeriodAndIp(Contract contract, Date limitDate, String ip);
}
