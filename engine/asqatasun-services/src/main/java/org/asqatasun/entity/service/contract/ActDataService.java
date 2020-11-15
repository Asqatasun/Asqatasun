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
package org.asqatasun.entity.service.contract;

import java.util.Collection;
import java.util.Date;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.service.AbstractGenericDataService;
import org.asqatasun.entity.contract.Act;
import org.asqatasun.entity.contract.Contract;
import org.asqatasun.entity.contract.ScopeEnum;
import org.asqatasun.entity.dao.contract.ActDAO;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkowalczyk
 */
@Service("actDataService")
public class ActDataService extends AbstractGenericDataService<Act, Long> {

    public int getNumberOfAct(Contract contract) {
        return ((ActDAO)entityDao).findNumberOfAct(contract);
    }
    public int getNumberOfActByScope(Contract contract, Collection<ScopeEnum> scopes) {
        return ((ActDAO)entityDao).findNumberOfActByScope(contract, scopes);
    }

    public Collection<Act> getAllActsByContract(Contract contract) {
        return ((ActDAO) entityDao).findAllActsByContract(contract);
    }
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

    public Collection<Act> getRunningActsByContract(Contract contract) {
        return ((ActDAO) entityDao).findRunningActsByContract(contract);
    }

    public Act getActFromAudit(Audit audit) {
        return getActFromAudit(audit.getId());
    }

    public Act getActFromAudit(Long auditId) {
        return ((ActDAO) entityDao).findActFromAudit(auditId);
    }

    public int getNumberOfActByPeriodAndIp(Contract contract, Date limitDate, String ip) {
        return ((ActDAO) entityDao).findNumberOfActByPeriodAndIp(contract, limitDate, ip);
    }

    
}
