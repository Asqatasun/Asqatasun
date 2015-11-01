/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.tanaguru.webapp.presentation.factory;

import java.util.Calendar;
import java.util.Collection;
import org.tanaguru.webapp.action.voter.ActionHandler;
import org.tanaguru.webapp.entity.contract.Act;
import org.tanaguru.webapp.entity.contract.Contract;
import org.tanaguru.webapp.entity.contract.ScopeEnum;
import org.tanaguru.webapp.entity.service.contract.ActDataService;
import org.tanaguru.webapp.entity.service.contract.ContractDataService;
import org.tanaguru.webapp.presentation.data.AuditProgressionEnum;
import org.tanaguru.webapp.presentation.data.ContractInfo;
import org.tanaguru.webapp.presentation.data.ContractInfoImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class ContractInfoFactory {

    private ActDataService actDataService;
    public ActDataService getActDataService() {
        return actDataService;
    }

    @Autowired
    public void setActDataService(ActDataService actDataService) {
        this.actDataService = actDataService;
    }
    
    private ContractDataService contractDataService;
    public ContractDataService getContractDataService() {
        return contractDataService;
    }

    @Autowired
    public void setContractDataService(ContractDataService contractDataService) {
        this.contractDataService = contractDataService;
    }

    private ActionHandler actionHandler;
    public ActionHandler getActionHandler() {
        return actionHandler;
    }

    @Autowired
    public void setActionHandler(ActionHandler contractActionHandler) {
        this.actionHandler = contractActionHandler;
    }
    
    /**
     * The unique shared instance of ContractInfoFactory
     */
    private static ContractInfoFactory contractInfoFactory;

    /**
     * Default private constructor
     */
    protected ContractInfoFactory(){}

    public static synchronized ContractInfoFactory getInstance(){
        if (contractInfoFactory == null) {
            contractInfoFactory = new ContractInfoFactory();
        }
        return contractInfoFactory;
    }

    /**
     * 
     * @param contract
     * @return an ContractInfo instance that handles displayable contract data
     * 
     */
    public ContractInfo getContractInfo(Contract contract){
        ContractInfo contractInfo = new ContractInfoImpl();
        contract = contractDataService.read(contract.getId());
        setBasicContractInfo(contract, contractInfo);
        
        contractInfo.setExpirationDate(contract.getEndDate());
        if (Calendar.getInstance().getTime().after(contract.getEndDate())) {
            contractInfo.setContractExpired(true);
        }

        contractInfo = setLastActInfo(contract, contractInfo);
        
        contractInfo = setAuditResultTrend(contract, contractInfo);
        return contractInfo;
    }

    /**
     * Sets the basic data of the current contract
     * @param contract
     * @param contractInfo
     * @return
     */
    protected ContractInfo setBasicContractInfo(Contract contract, ContractInfo contractInfo) {
        contractInfo.setLabel(contract.getLabel());
        contractInfo.setUrl(contractDataService.getUrlFromContractOption(contract));
        contractInfo.setId(contract.getId().intValue());
        contractInfo.setIsActRunning(
                !actDataService.getRunningActsByContract(contract).isEmpty());
        contractInfo.setActionList(actionHandler.getActionList(contract));
        contractInfo.setPresetContractKey(
                contractDataService.getPresetContractKeyContractOption(contract));
        return contractInfo;
    }

    /**
     * Sets the act info of the last act launched from this contract
     * 
     * @param contract
     * @param contractInfo
     * @return
     */
    protected ContractInfo setLastActInfo(Contract contract, ContractInfo contractInfo) {
        Collection<Act> lastActRequestResult = actDataService.getActsByContract(contract, 1, 2, null, false);
        if (!lastActRequestResult.isEmpty() && lastActRequestResult.size() ==1) {
            contractInfo.setLastActInfo(ActInfoFactory.getInstance().getActInfo(lastActRequestResult.iterator().next()));
        }
        return contractInfo;
    }

    /**
     * Set the trend of the current contract info regarding two last site
     * audit results.
     *
     * @param contract
     * @param contractInfo
     * @return
     */
    protected ContractInfo setAuditResultTrend(Contract contract, ContractInfo contractInfo) {
        // retrieves the two last results of site audit
        Collection<Act> lastActRequestResult = getActDataService().getActsByContract(contract, 2, 2, ScopeEnum.DOMAIN, true);

        // when at least two site audits have been realized for the current
        // contract, compute the progression
        if (lastActRequestResult.size() == 2) {
            Object[] actTab = lastActRequestResult.toArray();
            if (actTab[0] instanceof Act && actTab[1] instanceof Act) {
                int lastRawMark = ActInfoFactory.getInstance().getActInfo((Act) actTab[0]).getRawMark();
                int lastButOneRawMark = ActInfoFactory.getInstance().getActInfo((Act) actTab[1]).getRawMark();
                if (lastRawMark == lastButOneRawMark) {
                    contractInfo.setSiteAuditProgression(AuditProgressionEnum.STABLE);
                } else if (lastRawMark < lastButOneRawMark) {
                    contractInfo.setSiteAuditProgression(AuditProgressionEnum.REGRESS);
                } else {
                    contractInfo.setSiteAuditProgression(AuditProgressionEnum.PROGRESS);
                }
            }
        }
        return contractInfo;
    }
}