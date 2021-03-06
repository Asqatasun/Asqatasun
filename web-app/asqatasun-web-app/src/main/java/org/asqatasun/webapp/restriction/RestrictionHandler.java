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

import java.util.Map;
import java.util.Set;
import org.asqatasun.entity.contract.Contract;
import org.asqatasun.entity.contract.ScopeEnum;
import org.asqatasun.entity.option.OptionElementImpl;
import org.asqatasun.webapp.util.TgolKeyStore;

/**
 *
 * @author jkowalczyk
 */
public class RestrictionHandler {

    private Map<String, RestrictionVoter> restrictionVoterMap;

    public void setRestrictionVoterMap(Map<String, RestrictionVoter> restrictionVoterMap) {
        this.restrictionVoterMap = restrictionVoterMap;
    }

    public synchronized String checkRestriction(Contract contract, String clientIp, ScopeEnum scope) {
        String decision = TgolKeyStore.ACT_ALLOWED;
        Set<OptionElementImpl> optionElementSet = (Set<OptionElementImpl>) contract.getOptionElementSet();
        if (optionElementSet.isEmpty()) {
            return decision;
        }
        for (OptionElementImpl optionElement : optionElementSet) {
            RestrictionVoter restrictionVoter = chooseRestrictionVoter(optionElement);
            if (restrictionVoter != null) {
                decision = restrictionVoter.checkRestriction(contract, optionElement, clientIp, scope);
            }
            if (!decision.equalsIgnoreCase(TgolKeyStore.ACT_ALLOWED)) {
                break;
            }
        }
        return decision;
    }

    /**
     * 
     * @param optionElement
     * @return 
     */
    private RestrictionVoter chooseRestrictionVoter(OptionElementImpl optionElement) {
        String restrictionElementCode = optionElement.getOption().getCode();
        if (optionElement.getOption().isRestriction() && 
                restrictionVoterMap.containsKey(restrictionElementCode)) {
            return restrictionVoterMap.get(restrictionElementCode);
        }
        return null;
    }
    
}
