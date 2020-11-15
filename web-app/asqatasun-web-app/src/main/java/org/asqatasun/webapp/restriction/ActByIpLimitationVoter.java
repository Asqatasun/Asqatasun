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
package org.asqatasun.webapp.restriction;

import java.util.Calendar;
import java.util.Date;
import org.asqatasun.entity.contract.Contract;
import org.asqatasun.entity.contract.ScopeEnum;
import org.asqatasun.entity.option.OptionElementImpl;
import org.asqatasun.entity.service.contract.ActDataService;
import org.asqatasun.webapp.util.TgolKeyStore;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class ActByIpLimitationVoter implements RestrictionVoter {

    private ActDataService actDataService;

    @Autowired
    public void setActDataService(ActDataService actDataService) {
        this.actDataService = actDataService;
    }

    @Override
    public String checkRestriction(
            Contract contract, 
            OptionElementImpl optionElement,
            String clientIp, 
            ScopeEnum scope) {
        
        String[] limitationValues = optionElement.getValue().split("/");
        int nbOfAct = Integer.valueOf(limitationValues[0]);
        int period = Integer.valueOf(limitationValues[1]);
        Date now = Calendar.getInstance().getTime();
        Date limitDate = new Date(now.getTime() - (period * 1000L));
        if (actDataService.getNumberOfActByPeriodAndIp(contract, limitDate, clientIp) >= nbOfAct) {
            return TgolKeyStore.ACT_QUOTA_BY_IP_EXCEEDED;
        }
        return TgolKeyStore.ACT_ALLOWED;
    }
}
