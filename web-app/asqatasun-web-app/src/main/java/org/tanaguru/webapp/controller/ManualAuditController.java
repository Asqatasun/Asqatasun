/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.tanaguru.webapp.controller;

import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.AuditStatus;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.service.audit.ProcessResultDataService;
import org.tanaguru.entity.service.statistics.WebResourceStatisticsDataService;
import org.tanaguru.entity.subject.Site;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.webapp.command.ManualAuditCommand;
import org.tanaguru.webapp.entity.contract.Act;
import org.tanaguru.webapp.entity.contract.Contract;
import org.tanaguru.webapp.exception.ForbiddenPageException;
import org.tanaguru.webapp.presentation.factory.TestResultFactory;
import org.tanaguru.webapp.util.TgolKeyStore;
import org.tanaguru.webapp.validator.ManualAuditValidator;
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
 * Controller dedicated to the manual audit. 
 * Deal with the display (GET) and the update (POST)
 * @author jkowalczyk
 */
@Controller
public class ManualAuditController extends AbstractAuditResultController {

    /** The String that represents the "Finish" state */
    private static final String FINISH_ACTION_BUNDLE_NAME = "i18n/result-page-I18N";
    private static final String FINISH_ACTION_NAME_KEY = "resultPage.closeManualAudit";
    
    @Autowired
    private WebResourceStatisticsDataService webResourceStatisticsDataService;
    public void setWebResourceStatisticsDataService(WebResourceStatisticsDataService webResourceStatisticsDataService) {
        this.webResourceStatisticsDataService = webResourceStatisticsDataService;
    }
    
    @Autowired
    private ProcessResultDataService processResultDataService;
    public void setProcessResultDataService(ProcessResultDataService processResultDataService) {
        this.processResultDataService = processResultDataService;
    }
   
    @Autowired
    ManualAuditValidator manualAuditValidator;
    public void setManualAuditValidator(ManualAuditValidator manualAuditValidator) {
        this.manualAuditValidator = manualAuditValidator;
    }
    
    private String manualAuditFunctionalityKey = TgolKeyStore.MANUAL_AUDIT_FUNCTIONALITY_KEY;
    public void setManualAuditFunctionalityKey(String manualAuditFunctionalityKey) {
        this.manualAuditFunctionalityKey = manualAuditFunctionalityKey;
    }
    
    public ManualAuditController() {
        super();
    }
    
    /**
     * General router when receive audit-result request. Regarding the scope of
     * the audit, the returned page may differ.
     *
     * @param auditId
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = TgolKeyStore.MANUAL_AUDIT_RESULT_CONTRACT_URL, method = RequestMethod.GET)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String displayManualAuditResultFromContract(
            @RequestParam(TgolKeyStore.AUDIT_ID_KEY) String auditId,
            HttpServletRequest request,
            Model model) {
        try {
            Audit audit = getAuditDataService().read(Long.valueOf(auditId));
            Act act = getActDataService().getActFromAudit(audit);
            switch (act.getScope().getCode()) {
                case FILE:
                case PAGE:
                    if (!getContractDataService().doesContractHaveFunctionality(act.getContract(), manualAuditFunctionalityKey)) {
                        return TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
                    }
                model.addAttribute(TgolKeyStore.WEBRESOURCE_ID_KEY, audit.getSubject().getId());
                return TgolKeyStore.MANUAL_AUDIT_RESULT_VIEW_REDIRECT_NAME;
                case DOMAIN:
                case SCENARIO:
                case GROUPOFFILES:
                case GROUPOFPAGES:
                default:
                    throw new ForbiddenPageException();
            }
        } catch (NumberFormatException nfe) {
            throw new ForbiddenPageException();
        }
    }
    
    /**
     * General router when receive audit-result request. Regarding the scope of
     * the audit, the returned page may differ.
     *
     * @param webResourceId
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = TgolKeyStore.MANUAL_AUDIT_PAGE_RESULT_CONTRACT_URL, method = RequestMethod.GET)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String displayManualPageAuditResultFromContract(
            @RequestParam(TgolKeyStore.WEBRESOURCE_ID_KEY) String webResourceId,
            HttpServletRequest request,
            Model model) {
        try {
            model.addAttribute(TgolKeyStore.IS_MANUAL_AUDIT_KEY, true);
            return dispatchDisplayResultRequest(
                    Long.valueOf(webResourceId),
                    null,
                    model,
                    request,
                    true,
                    null);

        } catch (NumberFormatException nfe) {
            throw new ForbiddenPageException();
        }
    }
    
    /**
     *
     * @param webresourceId
     * @param manualAuditCommand
     * @param action
     * @param result
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = {TgolKeyStore.UPDATE_MANUAL_RESULT_CONTRACT_URL}, method = RequestMethod.POST)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String updateManualResult(
            @ModelAttribute(TgolKeyStore.MANUAL_AUDIT_COMMAND_KEY) ManualAuditCommand manualAuditCommand,
            @RequestParam(TgolKeyStore.WEBRESOURCE_ID_KEY) String webresourceId,
            @RequestParam String action,
            BindingResult result,
            Model model,
            HttpServletRequest request) {
      
        return dispatchSubmitManualAuditValues(
                webresourceId, 
                manualAuditCommand, 
                result, 
                model,
                request, 
                StringUtils.equalsIgnoreCase(action, getFinishActionNameFromLocale(request)));
    }
    
    private String getFinishActionNameFromLocale(HttpServletRequest request) {
        return ResourceBundle.getBundle(
                  FINISH_ACTION_BUNDLE_NAME, 
                  getLocaleResolver().resolveLocale(request))
                      .getString(FINISH_ACTION_NAME_KEY);
    }
    
    /**
     * Override this method to change default false argument value
     * 
     * @param webResource
     * @param model
     * @param displayScope
     */
    @Override
    protected void addAuditStatisticsToModel(WebResource webResource, Model model, String displayScope) {
        model.addAttribute(
                TgolKeyStore.STATISTICS_KEY,
                getAuditStatistics(webResource, model, displayScope, true));
    }
    
    /**
     *
     * @param webResourceId
     * @param referentialParameter
     * @return the criterion view is not accessible from this context
     */
    @Override
    protected boolean isCriterionViewAccessible(Long webResourceId, String referentialParameter) {
        return false;
    }
    
    /**
     * TO DO : replace this method with an call to the orchestrator 
     * to delegate the writes to the engine.
     * 
     * @param webresourceId
     * @param manualAuditCommand
     * @param result
     * @param model
     * @param request
     * @param isValidating
     * @return
     */
    private String dispatchSubmitManualAuditValues(
            String webresourceId,
            ManualAuditCommand manualAuditCommand,
            BindingResult result, Model model, HttpServletRequest request,
            boolean isValidating) {

        WebResource webResource;
        try {
            webResource = getWebResourceDataService().ligthRead(
                    Long.valueOf(webresourceId));
        } catch (NumberFormatException nfe) {
            throw new ForbiddenPageException();
        }
        if (webResource instanceof Site) {
            throw new ForbiddenPageException();
        }
        Audit audit = getAuditFromWebResource(webResource);
        if (isUserAllowedToDisplayResult(audit)) {
            model.addAttribute(TgolKeyStore.IS_MANUAL_AUDIT_KEY, true);
            List<ProcessResult> processResultList = TestResultFactory
                    .getInstance().getProcessResultListFromTestsResult(
                            manualAuditCommand.getModifiedManualResultMap(),
                            webResource);

            processResultDataService.saveOrUpdate(processResultList);
            /**
             * if save the manual audit for the first time save we set the
             * manual audit start time and status to MANUAL_INITIALIZING
             */
            if (audit.getManualAuditDateOfCreation() == null) {
                audit.setManualAuditDateOfCreation(Calendar.getInstance()
                        .getTime());
                audit.setStatus(AuditStatus.MANUAL_INITIALIZING);
                getAuditDataService().update(audit);
            }

            List<ProcessResult> allProcessResultList = TestResultFactory
                    .getInstance().getAllProcessResultListFromTestsResult(
                            manualAuditCommand.getModifiedManualResultMap(), webResource);
            manualAuditCommand.setProcessResultList(allProcessResultList);

            if (isValidating) {

                manualAuditValidator.validate(manualAuditCommand, result);
                if (result.hasErrors()) {
                    // ajout message d'erreur.
                    model.addAttribute(TgolKeyStore.MANUAL_AUDIT_COMMAND_KEY, manualAuditCommand);

                    return dispatchDisplayResultRequest(
                            webResource.getId(),
                            null, 
                            model, 
                            request, 
                            true, 
                            manualAuditCommand);
                } else {
                    // mettre à jour le statut
                    audit.setStatus(AuditStatus.MANUAL_COMPLETED);
                    getAuditDataService().update(audit);

                    webResourceStatisticsDataService.createWebResourceStatisticsForManualAudit(
                            audit,
                            webResource, 
                            allProcessResultList);

                    Contract contract = retrieveContractFromAudit(audit);
                    model.addAttribute(TgolKeyStore.CONTRACT_ID_KEY, contract.getId());
                    return TgolKeyStore.CONTRACT_VIEW_NAME_REDIRECT;
                }
            }

            webResourceStatisticsDataService.createWebResourceStatisticsForManualAudit(
                    audit,
                    webResource, 
                    allProcessResultList);

            return dispatchDisplayResultRequest(
                    webResource.getId(), 
                    null,
                    model, 
                    request, 
                    true, 
                    manualAuditCommand);
        } else {
            throw new ForbiddenPageException();
        }
    }

}