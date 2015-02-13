/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
package org.opens.tgol.presentation.factory;

import java.util.Collection;
import org.opens.tgol.entity.contract.Act;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.contract.ScopeEnum;
import org.opens.tgol.entity.option.OptionElement;
import org.opens.tgol.presentation.data.DetailedContractInfo;
import org.opens.tgol.presentation.data.DetailedContractInfoImpl;
import org.opens.tgol.util.TgolKeyStore;

/**
 *
 * @author jkowalczyk
 */
public final class DetailedContractInfoFactory extends ContractInfoFactory {

    private static final int NB_MAX_DISPLAYED_ACT = 100;

    private String nbMaxActRestrictionCode;
    public String getNbMaxActRestrictionCode() {
        return nbMaxActRestrictionCode;
    }

    public void setNbMaxActRestrictionCode(String nbMaxActRestrictionCode) {
        this.nbMaxActRestrictionCode = nbMaxActRestrictionCode;
    }

    /**
     * The unique shared instance of DetailedContractInfoFactory
     */
    private static DetailedContractInfoFactory detailedContractInfoFactory;

    /**
     * Default private constructor
     */
    private DetailedContractInfoFactory() {}

    public static synchronized DetailedContractInfoFactory getInstance() {
        if (detailedContractInfoFactory == null) {
            detailedContractInfoFactory = new DetailedContractInfoFactory();
        }
        return detailedContractInfoFactory;
    }

    public DetailedContractInfo getDetailedContractInfo(Contract contract) {
        DetailedContractInfo detailedContractInfo = new DetailedContractInfoImpl();

        detailedContractInfo = (DetailedContractInfoImpl) setBasicContractInfo(contract, detailedContractInfo);
        detailedContractInfo.setContractCreationDate(contract.getBeginDate());
        detailedContractInfo.setContractExpirationDate(contract.getEndDate());
        detailedContractInfo = (DetailedContractInfoImpl) setAuditResultTrend(contract, detailedContractInfo);
        detailedContractInfo = (DetailedContractInfoImpl) setLastActInfo(contract, detailedContractInfo);
        detailedContractInfo = setNLastActInfo(contract, detailedContractInfo);
        
        detailedContractInfo.setIsManualAuditEnabled(
                getContractDataService().doesContractHaveFunctionality(
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
        int nb0fAct = getActDataService().getNumberOfAct(contract);
        int nbMaxActInfo = getMaxAuthorizedNumberOfActByContract(contract);
        if (nbMaxActInfo > 0 && nb0fAct > nbMaxActInfo) {
            nb0fAct = nbMaxActInfo;
        }
        detailedContractInfo.setNumberOfAct(nb0fAct);
        Collection<Act> lastActSet = getActDataService().getActsByContract(contract, nb0fAct, 2, null, false);
        for (Act act : lastActSet) {
            detailedContractInfo.addActInfo(ActInfoFactory.getInstance().getActInfo(act));
        }
        detailedContractInfo.setNumberOfDisplayedAct(lastActSet.size());
        detailedContractInfo.setSiteActInfoSet(ActInfoFactory.getInstance().getActInfoSet(
                getActDataService().getActsByContract(contract, -1, 1, ScopeEnum.DOMAIN, true)));
        return detailedContractInfo;
    }

    /**
     * 
     * @param contract
     * @return 
     */
    private int getMaxAuthorizedNumberOfActByContract (Contract contract) {
        for (OptionElement optionElement : contract.getOptionElementSet())  {
            if (optionElement.getOption().getCode().equals(nbMaxActRestrictionCode)) {
                return Integer.valueOf(optionElement.getValue());
            }
        }
        return NB_MAX_DISPLAYED_ACT;
    }

}