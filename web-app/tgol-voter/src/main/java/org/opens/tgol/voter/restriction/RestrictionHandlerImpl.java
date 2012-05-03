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
package org.opens.tgol.voter.restriction;

import java.util.Map;
import java.util.Set;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.option.OptionElement;
import org.opens.tgol.util.TgolKeyStore;

/**
 *
 * @author jkowalczyk
 */
public class RestrictionHandlerImpl implements RestrictionHandler {

    private Map<String, RestrictionVoter> restrictionVoterMap;

    public void setRestrictionVoterMap(Map<String, RestrictionVoter> restrictionVoterMap) {
        this.restrictionVoterMap = restrictionVoterMap;
    }

    @Override
    public synchronized String checkRestriction(Contract contract, String clientIp) {
        String decision = TgolKeyStore.ACT_ALLOWED;
        Set<OptionElement> optionElementSet = (Set<OptionElement>) contract.getOptionElementSet();
        if (optionElementSet.isEmpty()) {
            return decision;
        }
        for (OptionElement optionElement : optionElementSet) {
            RestrictionVoter restrictionVoter = chooseRestrictionVoter(optionElement);
            if (restrictionVoter != null) {
                decision = restrictionVoter.checkRestriction(contract, optionElement, clientIp);
            }
            if (!decision.equalsIgnoreCase(TgolKeyStore.ACT_ALLOWED)) {
                break;
            }
        }
        return decision;
    }

    /**
     * 
     * @param option
     * @return 
     */
    private RestrictionVoter chooseRestrictionVoter(OptionElement optionElement) {
        String restrictionElementCode = optionElement.getOption().getCode();
        if (optionElement.getOption().isRestriction() && 
                restrictionVoterMap.containsKey(restrictionElementCode)) {
            return restrictionVoterMap.get(restrictionElementCode);
        }
        return null;
    }
    
}
