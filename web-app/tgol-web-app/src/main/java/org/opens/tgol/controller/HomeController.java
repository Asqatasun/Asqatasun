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
package org.opens.tgol.controller;

import org.opens.tgol.action.voter.ActionHandler;
import org.opens.tgol.presentation.factory.ContractInfoFactory;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.service.contract.ActDataService;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.presentation.data.ContractInfo;
import org.opens.tgol.util.TgolKeyStore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
public class HomeController extends AbstractController {

    /**
     * The actDataService instance needed to launch the audit process
     */
    private ActDataService actDataService;
    @Autowired
    public final void setActDataService(ActDataService actDataService) {
        this.actDataService = actDataService;
    }

    private LocaleResolver localeResolver;

    @Autowired
    public final void setLocaleResolver(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    private List<String> authorizedProductForTrend = new ArrayList<String>();
    public List<String> getAuthorizedProductForTrend() {
        return authorizedProductForTrend;
    }

    public void setAuthorizedProductForTrend(List<String> authorizedProductForTrend) {
        this.authorizedProductForTrend = authorizedProductForTrend;
    }

    private ActionHandler actionHandler;
    public ActionHandler getActionHandler() {
        return actionHandler;
    }

    public void setActionHandler(ActionHandler contractActionHandler) {
        this.actionHandler = contractActionHandler;
    }

    public HomeController() {
        super();
    }

    @RequestMapping(value=TgolKeyStore.HOME_URL, method=RequestMethod.GET)
    @Secured(TgolKeyStore.ROLE_USER_KEY)
    public String displayHomePage(Model model) {
        User user = getCurrentUser();
        model.addAttribute(TgolKeyStore.AUTHENTICATED_USER_KEY,user);
        model.addAttribute(TgolKeyStore.CONTRACT_LIST_KEY, prepareContractInfo(user));
        return TgolKeyStore.HOME_VIEW_NAME;
    }

    @RequestMapping(value=TgolKeyStore.CONTRACT_URL, method=RequestMethod.GET)
    @Secured(TgolKeyStore.ROLE_USER_KEY)
    public String displayContractPage (
            @RequestParam(TgolKeyStore.CONTRACT_ID_KEY) String contractId,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        if (!isAuthenticated()) {
            return TgolKeyStore.DISPATCH_VIEW_REDIRECT_NAME;
        } else if (!isUserOwnedContract(Long.valueOf(contractId))) {
            return TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
        }
        return displayContractPage(request, model, Long.valueOf(contractId));
    }

    @Secured(TgolKeyStore.ROLE_USER_KEY)
    public String displayContractPage(
            HttpServletRequest request,
            Model model,
            Long contractId) {
        User user = getCurrentUser();
        model.addAttribute(TgolKeyStore.LOCALE_KEY,localeResolver.resolveLocale(request));
        model.addAttribute(TgolKeyStore.AUTHENTICATED_USER_KEY,user);
        model.addAttribute(TgolKeyStore.RUNNING_ACT, doesCurrentUserHaveARunningAct(user));
        Contract currentContract = getContractDataService().read(contractId);
        // add the action list to the view
        model.addAttribute(TgolKeyStore.CONTRACT_ACTION_LIST_KEY, actionHandler.getActionList(currentContract));
        if (authorizedProductForTrend.contains(currentContract.getProduct().getCode())) {
            model.addAttribute(TgolKeyStore.DISPLAY_RESULT_TREND_KEY, true);
        }
        return displayContractView(currentContract, model);
    }

    /**
     * This methods checks whether the given contract belongs to the authenticated
     * user of the current session
     * @param contractId
     * @return
     */
    private boolean isUserOwnedContract(Long contractId){
        for (Contract contract : getCurrentUser().getContractSet()) {
            if (contract.getId().equals(contractId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This methods retrieves and prepare contract info
     * @param user
     * @return
     */
    private Collection<ContractInfo> prepareContractInfo(User user) {
        Set<ContractInfo> contractInfoSet = new LinkedHashSet<ContractInfo>();
        for (Contract contract : user.getContractSet()){
            contractInfoSet.add(ContractInfoFactory.getInstance().getContractInfo(contract));
        }
        return contractInfoSet;
    }

    /**
     * This methods checks whether the authenticated user of the current session
     * has an act still running (an audit of site type for example)
     * @param user
     * @return
     */
    private boolean doesCurrentUserHaveARunningAct(User user) {
        boolean isAuditRunning = false;
        for (Contract contract : user.getContractSet()){
            if (!actDataService.getRunningActsByContract(contract).isEmpty()) {
                isAuditRunning = true;
                break;
            }
        }
        return isAuditRunning;
    }

    /**
     * This methods builds the breadcrumb for the contract page.
     * My Project
     * @param contractId
     * @param webResourceId
     * @return
     */
    public static Map<String, String> buildBreadCrumb() {
        Map<String, String> breadCrumb = new LinkedHashMap<String, String>();
        breadCrumb.put(
                TgolKeyStore.HOME_URL + TgolKeyStore.HTML_EXTENSION_KEY,
                TgolKeyStore.MY_PROJECT_KEY);
        return breadCrumb;
    }

}