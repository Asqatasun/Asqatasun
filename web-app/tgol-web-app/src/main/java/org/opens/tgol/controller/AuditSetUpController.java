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

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.opens.tgol.command.AuditSetUpCommand;
import org.opens.tgol.command.factory.AuditSetUpCommandFactory;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.product.ScopeEnum;
import org.opens.tgol.form.parameterization.AuditSetUpFormField;
import org.opens.tgol.form.parameterization.builder.AuditSetUpFormFieldBuilderImpl;
import org.opens.tgol.util.TgolKeyStore;
import org.opens.tgol.validator.AuditSetUpFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/** 
 *
 * @author jkowalczyk
 */
@Controller
public class AuditSetUpController extends AuditDataHandlerController{

    private Map<String, List<AuditSetUpFormFieldBuilderImpl>> siteFormFieldBuilderMap;
    
    private String readOnlyProductKey = "READONLY";
    public void setReadOnlyProductKey(String readOnlyProductKey) {
        this.readOnlyProductKey = readOnlyProductKey;
    }

    public final void setSiteParametersMap(final Map<String, List<AuditSetUpFormFieldBuilderImpl>> formFieldBuilderMap) {
        this.siteFormFieldBuilderMap = formFieldBuilderMap;
    }
    private AuditSetUpFormValidator auditSiteSetUpValidator;

    public final void setAuditSiteSetUpValidator(AuditSetUpFormValidator auditSiteSetUpValidator) {
        this.auditSiteSetUpValidator = auditSiteSetUpValidator;
    }
    private Map<String, List<AuditSetUpFormFieldBuilderImpl>> pageFormFieldBuilderMap;

    public final void setPageParametersMap(final Map<String, List<AuditSetUpFormFieldBuilderImpl>> formFieldBuilderMap) {
        this.pageFormFieldBuilderMap = formFieldBuilderMap;
    }
    private AuditSetUpFormValidator auditPageSetUpValidator;

    public final void setAuditPageSetUpValidator(AuditSetUpFormValidator auditPageSetUpValidator) {
        this.auditPageSetUpValidator = auditPageSetUpValidator;
    }

    private AuditLauncherController auditLauncherController;
    
    @Autowired
    public void setAuditLauncherController(AuditLauncherController auditLauncherController) {
        this.auditLauncherController = auditLauncherController;
    }
    
    public AuditSetUpController() {
        super();
    }

    /**
     * @param contractId
     * @param request
     * @param response
     * @param model
     * @return
     *      The pages audit set-up form page
     */
    @RequestMapping(value = TgolKeyStore.AUDIT_PAGE_SET_UP_CONTRACT_URL, method = RequestMethod.GET)
    @Secured(TgolKeyStore.ROLE_USER_KEY)
    public String displayPageAuditSetUp(
            @RequestParam(TgolKeyStore.CONTRACT_ID_KEY) String contractId,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        Contract currentContract  = getContractDataService().read(Long.valueOf(contractId));
        if (isUserAllowedToDisplaySetUpPage(currentContract) 
                && currentContract!=null
                && !currentContract.getProduct().getCode().contains(readOnlyProductKey)){
            this.prepareFormModel(
                    model,
                    currentContract,
                    pageFormFieldBuilderMap,
                    false,
                    false);
            return TgolKeyStore.AUDIT_PAGE_SET_UP_VIEW_NAME;
        } else {
            return TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
        }
    }

    /**
     * @param contractId
     * @param request
     * @param response
     * @param model
     * @return
     *      The pages audit set-up form page
     */
    @RequestMapping(value = TgolKeyStore.AUDIT_UPLOAD_SET_UP_CONTRACT_URL, method = RequestMethod.GET)
    @Secured(TgolKeyStore.ROLE_USER_KEY)
    public String displayUploadAuditSetUp(
            @RequestParam(TgolKeyStore.CONTRACT_ID_KEY) String contractId,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        Contract currentContract  = getContractDataService().read(Long.valueOf(contractId));
        if (isUserAllowedToDisplaySetUpPage(currentContract)
                && currentContract!=null
                && !currentContract.getProduct().getCode().contains(readOnlyProductKey)){
            this.prepareFormModel(
                    model,
                    currentContract,
                    pageFormFieldBuilderMap,
                    false,
                    true);
            return TgolKeyStore.AUDIT_UPLOAD_SET_UP_VIEW_NAME;
        } else {
            return TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
        }
    }

    /**
     * @param contractId
     * @param request
     * @param response
     * @param model
     * @return
     *      The site audit set-up form page
     */
    @RequestMapping(value = TgolKeyStore.AUDIT_SITE_SET_UP_CONTRACT_URL, method = RequestMethod.GET)
    @Secured(TgolKeyStore.ROLE_USER_KEY)
    public String displaySiteAuditSetUp(
            @RequestParam(TgolKeyStore.CONTRACT_ID_KEY) String contractId,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        Contract currentContract  = getContractDataService().read(Long.valueOf(contractId));
        if (isUserAllowedToDisplaySetUpPage(currentContract)
                && currentContract != null
                && currentContract.getProduct().getScope().getCode().equals(ScopeEnum.DOMAIN)) {
            this.prepareFormModel(
                    model,
                    currentContract,
                    siteFormFieldBuilderMap,
                    true,
                    false);
            return TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME;
        } else {
            return TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
        }
    }

    /**
     * This method prepares the data to display in the set-up form.
     *
     * @param model
     * @param contractId
     * @param parametersMap
     * @param auditSite
     */
    private void prepareFormModel(
            Model model,
            Contract contract,
            Map<String, List<AuditSetUpFormFieldBuilderImpl>> parametersMap,
            boolean isAuditSite,
            boolean isUploadAudit) {
        model.addAttribute(TgolKeyStore.AUTHENTICATED_USER_KEY, getCurrentUser());
        model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY, contract.getLabel());
        Map<String, List<AuditSetUpFormField>> parametersMapCopy = AuditSetUpCommandFactory.getInstance().getParametersMapCopy(contract, parametersMap);
        model.addAttribute(TgolKeyStore.PARAMETERS_MAP_KEY, parametersMapCopy);
        AuditSetUpCommand asuc = AuditSetUpCommandFactory.getInstance().getInitialisedAuditCommand(
                contract,
                parametersMapCopy,
                isAuditSite,
                isUploadAudit,
                isGuestUser());
        model.addAttribute(TgolKeyStore.AUDIT_SET_UP_COMMAND_KEY, asuc);
        model.addAttribute(TgolKeyStore.URL_KEY, contract.getUrl());
        model.addAttribute(TgolKeyStore.DEFAULT_PARAM_SET_KEY,
                asuc.isDefaultParamSet());
    }

    /**
     * 
     * @param auditSetUpCommand
     * @param result
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST)
    protected String submitForm(
            @ModelAttribute(TgolKeyStore.AUDIT_SET_UP_COMMAND_KEY) AuditSetUpCommand auditSetUpCommand,
            BindingResult result,
            Model model,
            HttpServletRequest request) {
        Contract contract = getContractDataService().read(auditSetUpCommand.getContractId());
        // We check whether the form is valid
        Map<String, List<AuditSetUpFormField>> parametersMap;
        if (auditSetUpCommand.isAuditSite()) {
            parametersMap = AuditSetUpCommandFactory.getInstance().getParametersMapCopy(contract, siteFormFieldBuilderMap);
            auditSiteSetUpValidator.setAuditSetUpFormFieldMap(parametersMap);
            auditSiteSetUpValidator.validate(auditSetUpCommand, result);
        } else {
            // in case of page upload audit, needs to converts the files to a
            // map before the tanaguru service is called
            auditSetUpCommand.convertFilesToMap();
            parametersMap = AuditSetUpCommandFactory.getInstance().getParametersMapCopy(contract, pageFormFieldBuilderMap);
            auditPageSetUpValidator.setAuditSetUpFormFieldMap(parametersMap);
            auditPageSetUpValidator.validate(auditSetUpCommand, result);
        }
        // If the form has some errors, we display it again with errors' details
        if (result.hasErrors()) {
            Contract currentContract  =
                    getContractDataService().read(Long.valueOf(auditSetUpCommand.getContractId()));
            return displayFormWithErrors(
                    model,
                    currentContract,
                    auditSetUpCommand,
                    parametersMap);
        }
        model.addAttribute(TgolKeyStore.CONTRACT_ID_KEY, auditSetUpCommand.getContractId());
        model.addAttribute(TgolKeyStore.AUDIT_SET_UP_COMMAND_KEY, auditSetUpCommand);
        return auditLauncherController.launchAudit(auditSetUpCommand, getLocaleResolver().resolveLocale(request), model);
    }

    /**
     *
     * @param model
     * @param contract
     * @param launchAuditFromContractCommand
     * @return
     */
    private String displayFormWithErrors(
            Model model,
            Contract contract,
            AuditSetUpCommand auditSetUpCommand,
            Map<String, List<AuditSetUpFormField>> parametersMap) {
        model.addAttribute(TgolKeyStore.AUTHENTICATED_USER_KEY, getCurrentUser());
        model.addAttribute(TgolKeyStore.EXPANDED_KEY, TgolKeyStore.EXPANDED_VALUE);
        model.addAttribute(TgolKeyStore.AUDIT_SET_UP_COMMAND_KEY,
                auditSetUpCommand);
        model.addAttribute(TgolKeyStore.PARAMETERS_MAP_KEY, parametersMap);
        // if the user is authenticated, the url of the form is under
        // /home/contract/. To display error pages, we need to precise the
        // backward relative path and to add the breadCrumb.
        if (!isGuestUser()) {
            model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY, contract.getLabel());
        }
        if (auditSetUpCommand.isAuditSite()) {
        return TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME;
        } else {
            return (auditSetUpCommand.isUploadAudit())?
                TgolKeyStore.AUDIT_UPLOAD_SET_UP_VIEW_NAME :
                TgolKeyStore.AUDIT_PAGE_SET_UP_VIEW_NAME;
        }
    }

}