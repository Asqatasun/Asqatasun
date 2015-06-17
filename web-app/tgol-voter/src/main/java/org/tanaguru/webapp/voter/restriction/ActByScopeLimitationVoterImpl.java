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
package org.tanaguru.webapp.voter.restriction;

import java.util.ArrayList;
import java.util.Collection;
import org.tanaguru.webapp.entity.contract.Contract;
import org.tanaguru.webapp.entity.contract.ScopeEnum;
import org.tanaguru.webapp.entity.option.OptionElement;
import org.tanaguru.webapp.entity.service.contract.ActDataService;
import org.tanaguru.webapp.util.TgolKeyStore;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class ActByScopeLimitationVoterImpl implements RestrictionVoter {

    private Collection<ScopeEnum> scopes = new ArrayList<ScopeEnum>();
    public void setScopes(Collection<String> scopes) {
        for (String scope : scopes) {
            this.scopes.add(ScopeEnum.valueOf(scope));
        }
    }
    
    private ActDataService actDataService;

    @Autowired
    public void setActDataService(ActDataService actDataService) {
        this.actDataService = actDataService;
    }

    @Override
    public String checkRestriction(
            Contract contract, 
            OptionElement optionElement, 
            String clientIp, 
            ScopeEnum scope) {
        if (!scopes.contains(scope)) {
            return TgolKeyStore.ACT_ALLOWED;
        }
        int nbOfAct = Integer.valueOf(optionElement.getValue());
        if (nbOfAct <= actDataService.getNumberOfActByScope(contract, scopes)) {
            return TgolKeyStore.ACT_QUOTA_EXCEEDED;
        }
        return TgolKeyStore.ACT_ALLOWED;
    }

}
