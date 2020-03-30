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
package org.asqatasun.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.asqatasun.webapp.command.AuditSetUpCommand;
import org.asqatasun.webapp.entity.contract.Contract;
import org.asqatasun.webapp.entity.contract.ScopeEnum;
import org.asqatasun.webapp.ui.form.parameterization.AuditSetUpFormField;
import org.asqatasun.webapp.ui.form.parameterization.builder.AuditSetUpFormFieldBuilder;
import static org.asqatasun.webapp.util.TgolKeyStore.*;
import org.asqatasun.webapp.validator.AuditSetUpFormValidator;
import org.springframework.beans.factory.annotation.Qualifier;
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

    private final Map<String, List<AuditSetUpFormFieldBuilder>> siteOptionFormFieldBuilderMap;
    private final AuditSetUpFormValidator auditSiteSetUpFormValidator;
    private final Map<String, List<AuditSetUpFormFieldBuilder>> pageOptionFormFieldBuilderMap;
    private final AuditSetUpFormValidator auditPageSetUpFormValidator;
    private final Map<String, List<AuditSetUpFormFieldBuilder>> uploadOptionFormFieldBuilderMap;
    private final AuditSetUpFormValidator auditUploadSetUpFormValidator;

    public AuditSetUpController(
        @Qualifier(value = "siteOptionFormFieldBuilderMap")
            Map <String, List <AuditSetUpFormFieldBuilder>> siteOptionFormFieldBuilderMap,
        @Qualifier(value = "auditSetUpFormValidator") AuditSetUpFormValidator auditSiteSetUpFormValidator,
        @Qualifier(value = "pageOptionFormFieldBuilderMap")
            Map <String, List <AuditSetUpFormFieldBuilder>> pageOptionFormFieldBuilderMap,
        @Qualifier(value = "pageAuditSetUpFormValidator") AuditSetUpFormValidator auditPageSetUpFormValidator,
        @Qualifier(value = "uploadOptionFormFieldBuilderMap")
            Map <String, List <AuditSetUpFormFieldBuilder>> uploadOptionFormFieldBuilderMap,
        @Qualifier(value = "uploadAuditSetUpFormValidator") AuditSetUpFormValidator auditUploadSetUpFormValidator) {
        super();
        this.siteOptionFormFieldBuilderMap = siteOptionFormFieldBuilderMap;
        this.auditSiteSetUpFormValidator = auditSiteSetUpFormValidator;
        this.pageOptionFormFieldBuilderMap = pageOptionFormFieldBuilderMap;
        this.auditPageSetUpFormValidator = auditPageSetUpFormValidator;
        this.uploadOptionFormFieldBuilderMap = uploadOptionFormFieldBuilderMap;
        this.auditUploadSetUpFormValidator = auditUploadSetUpFormValidator;
    }

    @PostConstruct
    protected void init() {
        super.init();
        if (viewFunctionalityBindingMap == null) {
            viewFunctionalityBindingMap = new HashMap <String, String>() {{
                put("audit-page-set-up", "PAGES");
                put("audit-site-set-up", "DOMAIN");
                put("audit-upload-set-up", "UPLOAD");
            }};
        }
    }

    /**
     * @param contractId
     * @param model
     * @return
     *      The pages audit set-up form page
     */
    @RequestMapping(value = AUDIT_PAGE_SET_UP_CONTRACT_URL, method = RequestMethod.GET)
    @Secured({ROLE_USER_KEY, ROLE_ADMIN_KEY})
    public String displayPageAuditSetUp(@RequestParam(CONTRACT_ID_KEY) String contractId, Model model) {
        return displayAuditSetUpView(
                AUDIT_PAGE_SET_UP_VIEW_NAME, 
                contractId, 
                "",
                pageOptionFormFieldBuilderMap,
                ScopeEnum.PAGE,
                model);
    }

    /**
     * @param contractId
     * @param model
     * @return
     *      The pages audit set-up form page
     */
    @RequestMapping(value = AUDIT_UPLOAD_SET_UP_CONTRACT_URL, method = RequestMethod.GET)
    @Secured({ROLE_USER_KEY, ROLE_ADMIN_KEY})
    public String displayUploadAuditSetUp(@RequestParam(CONTRACT_ID_KEY) String contractId, Model model) {
        return displayAuditSetUpView(
                AUDIT_UPLOAD_SET_UP_VIEW_NAME,
                contractId, 
                "",
                uploadOptionFormFieldBuilderMap,
                ScopeEnum.FILE,
                model);
    }

    /**
     * @param contractId
     * @param model
     * @return
     *      The site audit set-up form page
     */
    @RequestMapping(value = AUDIT_SITE_SET_UP_CONTRACT_URL, method = RequestMethod.GET)
    @Secured({ROLE_USER_KEY, ROLE_ADMIN_KEY})
    public String displaySiteAuditSetUp(@RequestParam(CONTRACT_ID_KEY) String contractId, Model model) {
        return displayAuditSetUpView(
                AUDIT_SITE_SET_UP_VIEW_NAME, 
                contractId, 
                "",
                siteOptionFormFieldBuilderMap,
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
    @RequestMapping(value = {AUDIT_PAGE_SET_UP_CONTRACT_URL,
                             AUDIT_SITE_SET_UP_CONTRACT_URL,
                             AUDIT_UPLOAD_SET_UP_CONTRACT_URL},
                    method = RequestMethod.POST)
    @Secured({ROLE_USER_KEY, ROLE_ADMIN_KEY})
    public String submitAuditSetUpForm(
            @ModelAttribute(AUDIT_SET_UP_COMMAND_KEY) AuditSetUpCommand auditSetUpCommand,
            BindingResult result,
            Model model,
            HttpServletRequest request) {
        Contract contract = contractDataService.read(auditSetUpCommand.getContractId());
        Map<String, List<AuditSetUpFormField>> formFielMap = null;
        AuditSetUpFormValidator auditSetUpFormValidator = null;
        if (auditSetUpCommand.getRelaunch()) {
            return launchAudit(contract, auditSetUpCommand, model, request);
        }
        switch (auditSetUpCommand.getScope()) {
            case DOMAIN:
                formFielMap = getFreshAuditSetUpFormFieldMap(
                    contract, 
                    siteOptionFormFieldBuilderMap);
                auditSetUpFormValidator = auditSiteSetUpFormValidator;
                break;
            case PAGE:
                formFielMap = getFreshAuditSetUpFormFieldMap(
                    contract, 
                    pageOptionFormFieldBuilderMap);
                auditSetUpFormValidator = auditPageSetUpFormValidator;
                break;
            case FILE:
                formFielMap = getFreshAuditSetUpFormFieldMap(
                    contract, 
                    uploadOptionFormFieldBuilderMap);
                auditSetUpFormValidator = auditUploadSetUpFormValidator;
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
