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

import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.product.Restriction;
import org.opens.tgol.util.TgolKeyStore;
import java.util.Map;
import java.util.Set;

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
        Set<Restriction> restrictionSet = (Set<Restriction>) contract.getRestrictionSet();
        if (contract.getRestrictionSet().isEmpty()) {
            return decision;
        }
        for (Restriction restriction : restrictionSet) {
            RestrictionVoter restrictionVoter = chooseRestrictionVoter(restriction);
            if (restrictionVoter != null) {
                decision = restrictionVoter.checkRestriction(contract, restriction, clientIp);
            }
            if (!decision.equalsIgnoreCase(TgolKeyStore.ACT_ALLOWED)) {
                break;
            }
        }
        return decision;
    }

    private RestrictionVoter chooseRestrictionVoter(Restriction restriction) {
        String restrictionElementCode = restriction.getRestrictionElement().getCode();
        if (restrictionVoterMap.containsKey(restrictionElementCode)) {
            return restrictionVoterMap.get(restrictionElementCode);
        }
        return null;
    }
    
}
