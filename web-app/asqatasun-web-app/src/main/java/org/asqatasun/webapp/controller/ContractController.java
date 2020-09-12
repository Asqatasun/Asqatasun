package org.asqatasun.webapp.controller;

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

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.asqatasun.webapp.dto.factory.DetailedContractInfoFactory;
import org.asqatasun.entity.contract.Contract;
import org.asqatasun.entity.functionality.Functionality;
import org.asqatasun.entity.service.contract.ContractDataService;
import org.asqatasun.webapp.exception.ForbiddenPageException;
import org.asqatasun.webapp.exception.ForbiddenUserException;
import org.asqatasun.webapp.util.TgolKeyStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

/**
 *
 * @author jkowalczyk
 */
@Controller
public final class ContractController extends AbstractController {

    @Value("${app.webapp.ui.config.authorizedScopeForTrend}")
    private List<String> authorizedFunctionalityForTrend;
    private final ContractDataService contractDataService;
    private final DetailedContractInfoFactory detailedContractInfoFactory;
    private final LocaleResolver localeResolver;

    @Autowired
    public ContractController(ContractDataService contractDataService,
                              DetailedContractInfoFactory detailedContractInfoFactory,
                              LocaleResolver localeResolver) {
        this.contractDataService = contractDataService;
        this.detailedContractInfoFactory = detailedContractInfoFactory;
        this.localeResolver = localeResolver;
    }

    /**
     * 
     * @param contractId
     * @param request
     * @param model
     * @return 
     */
    @RequestMapping(value=TgolKeyStore.CONTRACT_URL, method=RequestMethod.GET)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String displayContractPage (
            @RequestParam(TgolKeyStore.CONTRACT_ID_KEY) String contractId,
            HttpServletRequest request,
            Model model) {
        Long contractIdValue;
        try {
            contractIdValue = Long.valueOf(contractId);
        } catch (NumberFormatException nfe) {
            throw new ForbiddenPageException(getCurrentUser());
        }
        if (!isUserOwnedContract(contractIdValue)) {
            throw new ForbiddenUserException(getCurrentUser());
        }
        return displayContractPage(request, model, contractIdValue);
    }

    /**
     * 
     * @param request
     * @param model
     * @param contractId
     * @return 
     */
    private String displayContractPage(
            HttpServletRequest request,
            Model model,
            Long contractId) {
                model.addAttribute(TgolKeyStore.LOCALE_KEY,localeResolver.resolveLocale(request));
                Contract contract = contractDataService.read(contractId);
                if (isContractExpired(contract)) {
                    throw new ForbiddenUserException(getCurrentUser());
                }
                if (isContractHasFunctionalityAllowingTrend(contract)) {
                    model.addAttribute(TgolKeyStore.DISPLAY_RESULT_TREND_KEY, true);
                }
                if(isContractHasFunctionalityAllowingManualAudit(contract)){
                	model.addAttribute(TgolKeyStore.CONTRACT_WITH_MANUAL_AUDIT_KEY, true);
                }
                return displayContractView(contract, model);
}
    
    /**
     * We iterate through the list of functionalities associated with the contract
     * to determine whether a manual audit option has to be displayed. 
     * @param contract
     * @return 
     */
    private boolean isContractHasFunctionalityAllowingManualAudit(Contract contract) {

        for (Functionality functionality : contract.getFunctionalitySet()) {
            if (functionality.getId() == 5) {
                return true;
            }
        }
        return false;
    }

    /**
     * We iterate through the list of functionalities associated with the contract
     * to determine whether a trend has to be displayed. 
     * @param contract
     * @return 
     */
    private boolean isContractHasFunctionalityAllowingTrend(Contract contract) {
        for (Functionality functionality : contract.getFunctionalitySet()) {
            if (Arrays.asList(authorizedFunctionalityForTrend).contains(functionality.getCode())) {
                return true;
            }
        }
        return false;
    }
    
     /**
     * This methods checks whether the given contract belongs to the authenticated
     * user of the current session
     * @param contractId
     * @return
     */
    private boolean isUserOwnedContract(Long contractId){
        for (Contract contract : contractDataService.getAllContractsByUser(getCurrentUser())) {
            if (contract.getId().equals(contractId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * To deal with contract expiration this method is defined here and accessible
     * from extended classes when needed.
     * The related jsp uses the IS_CONTRACT_EXPIRED_KEY to enable or not the launch
     * actions.
     * @param contract
     * @param model
     * @return
     */
    private String displayContractView(Contract contract, Model model) {
        model.addAttribute(TgolKeyStore.CONTRACT_ID_VALUE, contract.getId());
        model.addAttribute(TgolKeyStore.DETAILED_CONTRACT_INFO,
            detailedContractInfoFactory.getDetailedContractInfo(contract));
        model.addAttribute(TgolKeyStore.IS_CONTRACT_EXPIRED_KEY,
            isContractExpired(contract));
        return TgolKeyStore.CONTRACT_VIEW_NAME;
    }
}
