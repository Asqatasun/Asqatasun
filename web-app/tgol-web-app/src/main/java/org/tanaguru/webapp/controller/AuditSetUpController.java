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
package org.tanaguru.webapp.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.tanaguru.command.AuditSetUpCommand;
import org.tanaguru.entity.contract.Contract;
import org.tanaguru.entity.contract.ScopeEnum;
import org.tanaguru.form.parameterization.AuditSetUpFormField;
import org.tanaguru.util.TgolKeyStore;
import org.tanaguru.validator.AuditSetUpFormValidator;
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
public class AuditSetUpController extends AbstractAuditSetUpController{

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
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String displayPageAuditSetUp(
            @RequestParam(TgolKeyStore.CONTRACT_ID_KEY) String contractId,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        return displayAuditSetUpView(
                TgolKeyStore.AUDIT_PAGE_SET_UP_VIEW_NAME, 
                contractId, 
                "",
                getPageOptionFormFieldBuilderMap(), 
                ScopeEnum.PAGE,
                model);
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
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String displayUploadAuditSetUp(
            @RequestParam(TgolKeyStore.CONTRACT_ID_KEY) String contractId,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        return displayAuditSetUpView(
                TgolKeyStore.AUDIT_UPLOAD_SET_UP_VIEW_NAME, 
                contractId, 
                "",
                getUploadOptionFormFieldBuilderMap(), 
                ScopeEnum.FILE,
                model);
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
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String displaySiteAuditSetUp(
            @RequestParam(TgolKeyStore.CONTRACT_ID_KEY) String contractId,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        return displayAuditSetUpView(
                TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME, 
                contractId, 
                "",
                getSiteOptionFormFieldBuilderMap(), 
                ScopeEnum.DOMAIN,
                model);
    }

    /**
     * Submit in case of site audit
     * @param auditSetUpCommand
     * @param result
     * @param model
     * @param request
     * @return 
     */
    @RequestMapping(method = RequestMethod.POST)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    protected String submitAuditSetUpForm(
            @ModelAttribute(TgolKeyStore.AUDIT_SET_UP_COMMAND_KEY) AuditSetUpCommand auditSetUpCommand,
            BindingResult result,
            Model model,
            HttpServletRequest request) {
        Contract contract = getContractDataService().read(auditSetUpCommand.getContractId());   
        Map<String, List<AuditSetUpFormField>> formFielMap = null;
        AuditSetUpFormValidator auditSetUpFormValidator = null;
        if (auditSetUpCommand.getRelaunch()) {
            return launchAudit(contract, auditSetUpCommand, model, request);
        }
        switch (auditSetUpCommand.getScope()) {
            case DOMAIN:
                formFielMap = getFreshAuditSetUpFormFieldMap(
                    contract, 
                    getSiteOptionFormFieldBuilderMap());
                auditSetUpFormValidator = getAuditSiteSetUpFormValidator();
                break;
            case PAGE:
                formFielMap = getFreshAuditSetUpFormFieldMap(
                    contract, 
                    getPageOptionFormFieldBuilderMap());
                auditSetUpFormValidator = getAuditPageSetUpFormValidator();
                break;
            case FILE:
                formFielMap = getFreshAuditSetUpFormFieldMap(
                    contract, 
                    getUploadOptionFormFieldBuilderMap());
                auditSetUpFormValidator = getAuditUploadSetUpFormValidator();
                break;
        }
        return submitForm(
                contract, 
                auditSetUpCommand, 
                formFielMap, 
                auditSetUpFormValidator, 
                model, 
                result, 
                request);
    }
    
}