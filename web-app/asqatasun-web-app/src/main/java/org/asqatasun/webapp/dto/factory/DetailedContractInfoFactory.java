/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
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
package org.asqatasun.webapp.dto.factory;

import java.util.Collection;

import org.asqatasun.webapp.dto.DetailedContractInfo;
import org.asqatasun.entity.contract.Act;
import org.asqatasun.entity.contract.Contract;
import org.asqatasun.entity.contract.ScopeEnum;
import org.asqatasun.entity.option.OptionElementImpl;
import org.asqatasun.webapp.util.TgolKeyStore;
import org.springframework.stereotype.Component;

/**
 *
 * @author jkowalczyk
 */
@Component("detailedContractInfoFactory")
public class DetailedContractInfoFactory extends ContractInfoFactory {

    private static final int NB_MAX_DISPLAYED_ACT = 100;
    private static final String NB_MAX_ACT_RESTRICTION_CODE = "NB_OF_AUDIT_TO_DISPLAY";

    public DetailedContractInfoFactory() {}

    public DetailedContractInfo getDetailedContractInfo(Contract contract) {
        DetailedContractInfo detailedContractInfo = new DetailedContractInfo();

        detailedContractInfo = (DetailedContractInfo) setBasicContractInfo(contract, detailedContractInfo);
        detailedContractInfo.setContractCreationDate(contract.getBeginDate());
        detailedContractInfo.setContractExpirationDate(contract.getEndDate());
        detailedContractInfo = (DetailedContractInfo) setAuditResultTrend(contract, detailedContractInfo);
        detailedContractInfo = (DetailedContractInfo) setLastActInfo(contract, detailedContractInfo);
        detailedContractInfo = setNLastActInfo(contract, detailedContractInfo);
        
        detailedContractInfo.setIsManualAuditEnabled(
                contractDataService.doesContractHaveFunctionality(
                        contract, 
                        TgolKeyStore.MANUAL_AUDIT_FUNCTIONALITY_KEY));
        return detailedContractInfo;
    }

    /**
     * Sets the act info of the n last acts launched from this contract
     *
     * @param contract
     * @param detailedContractInfo
     * @return
     */
    protected DetailedContractInfo setNLastActInfo(Contract contract, DetailedContractInfo detailedContractInfo) {
        int nb0fAct = actDataService.getNumberOfAct(contract);
        int nbMaxActInfo = getMaxAuthorizedNumberOfActByContract(contract);
        if (nbMaxActInfo > 0 && nb0fAct > nbMaxActInfo) {
            nb0fAct = nbMaxActInfo;
        }
        detailedContractInfo.setNumberOfAct(nb0fAct);
        Collection<Act> lastActSet = actDataService.getActsByContract(contract, nb0fAct, 2, null, false);
        for (Act act : lastActSet) {
            detailedContractInfo.addActInfo(actInfoFactory.getActInfo(act));
        }
        detailedContractInfo.setNumberOfDisplayedAct(lastActSet.size());
        detailedContractInfo.setSiteActInfoSet(actInfoFactory.getActInfoSet(
                actDataService.getActsByContract(contract, -1, 1, ScopeEnum.DOMAIN, true)));
        return detailedContractInfo;
    }

    /**
     * 
     * @param contract
     * @return 
     */
    private int getMaxAuthorizedNumberOfActByContract (Contract contract) {
        for (OptionElementImpl optionElement : contract.getOptionElementSet())  {
            if (optionElement.getOption().getCode().equals(NB_MAX_ACT_RESTRICTION_CODE)) {
                return Integer.parseInt(optionElement.getValue());
            }
        }
        return NB_MAX_DISPLAYED_ACT;
    }

}
