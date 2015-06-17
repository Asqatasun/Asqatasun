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
import org.tanaguru.sdk.entity.service.AbstractGenericDataService;
import org.tanaguru.webapp.entity.contract.Act;
import org.tanaguru.webapp.entity.contract.Contract;
import org.tanaguru.webapp.entity.contract.ScopeEnum;
import org.tanaguru.webapp.entity.dao.contract.ActDAO;

/**
 *
 * @author jkowalczyk
 */
public class ActDataServiceImpl extends AbstractGenericDataService<Act, Long>
        implements ActDataService {

    @Override
    public int getNumberOfAct(Contract contract) {
        return ((ActDAO)entityDao).findNumberOfAct(contract);
    }

    @Override
    public int getNumberOfActByScope(Contract contract, Collection<ScopeEnum> scopes) {
        return ((ActDAO)entityDao).findNumberOfActByScope(contract, scopes);
    }
   
    @Override
    public Collection<Act> getAllActsByContract(Contract contract) {
        return ((ActDAO) entityDao).findAllActsByContract(contract);
    }

    @Override
    public Collection<Act> getActsByContract(
            Contract contract,
            int nbOfActs,
            int sortDirection,
            ScopeEnum scopeEnum,
            boolean onlyCompleted) {
        return ((ActDAO) entityDao).findActsByContract(
                contract,
                nbOfActs,
                sortDirection,
                scopeEnum,
                onlyCompleted);
    }

    @Override
    public Collection<Act> getRunningActsByContract(Contract contract) {
        return ((ActDAO) entityDao).findRunningActsByContract(contract);
    }

    @Override
    public Act getActFromAudit(Audit audit) {
        return getActFromAudit(audit.getId());
    }
    
    @Override
    public Act getActFromAudit(Long auditId) {
        return ((ActDAO) entityDao).findActFromAudit(auditId);
    }

    @Override
    public int getNumberOfActByPeriodAndIp(Contract contract, Date limitDate, String ip) {
        return ((ActDAO) entityDao).findNumberOfActByPeriodAndIp(contract, limitDate, ip);
    }

    
}