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

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.tanaguru.webapp.command.AuditSetUpCommand;
import org.tanaguru.webapp.command.factory.AuditSetUpCommandFactory;
import org.tanaguru.webapp.entity.contract.Contract;
import org.tanaguru.webapp.entity.contract.ScopeEnum;
import org.tanaguru.webapp.entity.functionality.Functionality;
import org.tanaguru.webapp.entity.option.OptionElement;
import org.tanaguru.webapp.entity.referential.Referential;
import org.tanaguru.webapp.entity.user.User;
import org.tanaguru.webapp.exception.ForbiddenPageException;
import org.tanaguru.webapp.form.SelectFormField;
import org.tanaguru.webapp.form.builder.SelectFormFieldBuilderImpl;
import org.tanaguru.webapp.form.parameterization.AuditSetUpFormField;
import org.tanaguru.webapp.form.parameterization.builder.AuditSetUpFormFieldBuilderImpl;
import org.tanaguru.webapp.form.parameterization.helper.AuditSetUpFormFieldHelper;
import org.tanaguru.webapp.util.TgolKeyStore;
import org.tanaguru.webapp.validator.AuditSetUpFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/** 
 *
 * @author jkowalczyk
 */
@Controller
public abstract class AbstractAuditSetUpController extends AbstractAuditDataHandlerController{

    private String defaultReferential = "Aw22";
    public String getDefaultReferential() {
        return defaultReferential;
    }

    public void setDefaultReferential(String defaultReferential) {
        this.defaultReferential = defaultReferential;
    }
    
    /**
     * The list of FormField builders that handles the audit site options
     */
    private Map<String, List<AuditSetUpFormFieldBuilderImpl>> siteOptionFormFieldBuilderMap;
    public Map<String, List<AuditSetUpFormFieldBuilderImpl>> getSiteOptionFormFieldBuilderMap() {
        return siteOptionFormFieldBuilderMap;
    }
    
    public final void setSiteOptionFormFieldBuilderMap(final Map<String, List<AuditSetUpFormFieldBuilderImpl>> formFieldBuilderMap) {
        this.siteOptionFormFieldBuilderMap = formFieldBuilderMap;
    }
    
    /**
     * The audit Site set-up validator
     */
    private AuditSetUpFormValidator auditSiteSetUpFormValidator;
    public AuditSetUpFormValidator getAuditSiteSetUpFormValidator() {
        return auditSiteSetUpFormValidator;
    }
    
    public final void setAuditSiteSetUpValidator(AuditSetUpFormValidator auditSiteSetUpValidator) {
        this.auditSiteSetUpFormValidator = auditSiteSetUpValidator;
    }

    /**
     * The list of FormField builders that handles the audit page options
     */
    private Map<String, List<AuditSetUpFormFieldBuilderImpl>> pageOptionFormFieldBuilderMap;
    public Map<String, List<AuditSetUpFormFieldBuilderImpl>> getPageOptionFormFieldBuilderMap() {
        return pageOptionFormFieldBuilderMap;
    }
    
    public final void setPageOptionFormFieldBuilderMap(final Map<String, List<AuditSetUpFormFieldBuilderImpl>> formFieldBuilderMap) {
        this.pageOptionFormFieldBuilderMap = formFieldBuilderMap;
    }
    
    /**
     * The audit Page set-up validator
     */
    private AuditSetUpFormValidator auditPageSetUpFormValidator;
    public AuditSetUpFormValidator getAuditPageSetUpFormValidator() {
        return auditPageSetUpFormValidator;
    }
    
    public final void setAuditPageSetUpValidator(AuditSetUpFormValidator auditPageSetUpValidator) {
        this.auditPageSetUpFormValidator = auditPageSetUpValidator;
    }
    
    /**
     * The list of FormField builders that handles the audit upload options
     */
    private Map<String, List<AuditSetUpFormFieldBuilderImpl>> uploadOptionFormFieldBuilderMap;
    public Map<String, List<AuditSetUpFormFieldBuilderImpl>> getUploadOptionFormFieldBuilderMap() {
        return uploadOptionFormFieldBuilderMap;
    }
    
    public final void setUploadOptionFormFieldBuilderMap(final Map<String, List<AuditSetUpFormFieldBuilderImpl>> formFieldBuilderMap) {
        this.uploadOptionFormFieldBuilderMap = formFieldBuilderMap;
    }
    
    /**
     * The audit Upload set-up validator
     */
    private AuditSetUpFormValidator auditUploadSetUpFormValidator;
    public AuditSetUpFormValidator getAuditUploadSetUpFormValidator() {
        return auditUploadSetUpFormValidator;
    }
    
    public final void setAuditUploadSetUpValidator(AuditSetUpFormValidator auditUploadSetUpValidator) {
        this.auditUploadSetUpFormValidator = auditUploadSetUpValidator;
    }

    /**
     * The list of FormField builders that handles the choice of the referential
     * and its level
     */
    List<SelectFormFieldBuilderImpl> referentialAndLevelFormFieldBuilderList;
    public final void setReferentialAndLevelFormFieldBuilderList(List<SelectFormFieldBuilderImpl> selectFormFieldBuilderList) {
        this.referentialAndLevelFormFieldBuilderList = selectFormFieldBuilderList;
    }

    /**
     * This map binds the audit set-up view with the functionality code that 
     * allows to access. It is used to ensure the displayed set-up view
     * is authorised regarding the contract functionalities.
     */
    Map<String, String> viewFunctionalityBindingMap;
    public Map<String, String> getViewFunctionalityBindingMap() {
        return viewFunctionalityBindingMap;
    }

    public void setViewFunctionalityBindingMap(Map<String, String> viewFunctionalityBindingMap) {
        this.viewFunctionalityBindingMap = viewFunctionalityBindingMap;
    }
    
    private AuditLauncherController auditLauncherController;
    public AuditLauncherController getAuditLauncherController() {
        return auditLauncherController;
    }
    
    @Autowired
    public void setAuditLauncherController(AuditLauncherController auditLauncherController) {
        this.auditLauncherController = auditLauncherController;
    }
    
    public AbstractAuditSetUpController() {
        super();
    }

    /**
     * 
     * @param viewName
     * @param contractId
     * @param scenarioId
     * @param optionFormFieldBuilderMap
     * @param scope
     * @param model
     * @return 
     */
    protected String displayAuditSetUpView(
            String viewName, 
            String contractId, 
            String scenarioId,
            Map<String, List<AuditSetUpFormFieldBuilderImpl>> optionFormFieldBuilderMap, 
            ScopeEnum scope,
            Model model) {
        Long contractIdValue;
        try { 
            contractIdValue = Long.valueOf(contractId);
        } catch (NumberFormatException nfe) {
            throw new ForbiddenPageException(getCurrentUser());
        }
        Contract contract  = getContractDataService().read(contractIdValue);
        if (isUserAllowedToDisplaySetUpPage(contract, viewName)) {
            
            Collection<String> authorisedReferentialList = getAuthorisedReferentialCodeFromContract(contract);
            
            // Get a fresh list of the auditSetUpFormField that handles the choice
            // of the referential and its level
            List<SelectFormField> refAndLevelFormFieldList = 
                    this.getFreshRefAndLevelSetUpFormFieldList(
                        authorisedReferentialList,
                        referentialAndLevelFormFieldBuilderList);
            
            String defaultRef = getDefaultReferential(authorisedReferentialList);
            AuditSetUpFormFieldHelper.selectDefaultLevelFromRefValue(
                    refAndLevelFormFieldList, 
                    defaultRef);
            
            // Get a fresh map of auditSetUpFormField. The value of the field is
            // them set by Parameter mapping handled by the AuditSetUpCommandObject
            Map<String, List<AuditSetUpFormField>> optionFormFieldMap = 
                    this.getFreshAuditSetUpFormFieldMap(contract, optionFormFieldBuilderMap);
            
            AuditSetUpCommand asuc;
            // Regarding the type of audit, we retrieve the appropriate 
            // instance of AuditSetUpCommand
            switch (scope) {
                case DOMAIN:
                    asuc = AuditSetUpCommandFactory.getInstance().
                        getSiteAuditSetUpCommand(contract,refAndLevelFormFieldList, optionFormFieldMap);
                    break;
                case FILE:
                case GROUPOFFILES:
                    asuc = AuditSetUpCommandFactory.getInstance().
                        getUploadAuditSetUpCommand(contract,refAndLevelFormFieldList, optionFormFieldMap);
                    break;
                case SCENARIO:
                    asuc = AuditSetUpCommandFactory.getInstance().
                        getScenarioAuditSetUpCommand(contract, scenarioId,refAndLevelFormFieldList, optionFormFieldMap);
                    break;
                case PAGE:
                case GROUPOFPAGES:
                default:
                    asuc = AuditSetUpCommandFactory.getInstance().
                        getPageAuditSetUpCommand(contract,refAndLevelFormFieldList, optionFormFieldMap);
            }
            model.addAttribute(TgolKeyStore.AUDIT_SET_UP_COMMAND_KEY, asuc);
            model.addAttribute(TgolKeyStore.DEFAULT_PARAM_SET_KEY,
                    asuc.isDefaultParamSet());
            this.prepareFormModel(
                    model,
                    contract,
                    refAndLevelFormFieldList,
                    optionFormFieldMap);
            return viewName;
        } else {
            return TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
        }
    }
    
    /**
     * 
     * @param contract
     * @param auditSetUpCommand
     * @param formFielMap
     * @param auditSetUpFormValidator
     * @param model
     * @param result
     * @param request
     * @return 
     */
    protected String submitForm(
            Contract contract, 
            AuditSetUpCommand auditSetUpCommand,
            Map<String, List<AuditSetUpFormField>> formFielMap, 
            AuditSetUpFormValidator auditSetUpFormValidator, 
            Model model, 
            BindingResult result, 
            HttpServletRequest request) {

        if (formFielMap == null || auditSetUpFormValidator == null) {
            Logger.getLogger(this.getClass()).info("oups" );
            return TgolKeyStore.OUPS_VIEW_NAME;
        }

        Collection<String> authorisedReferentialList = 
                getAuthorisedReferentialCodeFromContract(contract);

        auditSetUpFormValidator.setAuditSetUpFormFieldMap(formFielMap);

        auditSetUpFormValidator.validate(auditSetUpCommand, result);

        auditSetUpFormValidator.validateLevel(auditSetUpCommand, result,authorisedReferentialList);

        // If the form has some errors, we display it again with errors' details
        if (result.hasErrors()) {

            Contract currentContract  = 
                    getContractDataService().read(auditSetUpCommand.getContractId());

            return displayFormWithErrors(
                    model,
                    currentContract,
                    auditSetUpCommand,
                    authorisedReferentialList,
                    formFielMap);
        }

        // If the form is valid, the audit is launched with data from form
        model.addAttribute(TgolKeyStore.CONTRACT_ID_KEY, auditSetUpCommand.getContractId());

        model.addAttribute(TgolKeyStore.AUDIT_SET_UP_COMMAND_KEY, auditSetUpCommand);

        return launchAudit(
                contract,
                auditSetUpCommand, 
                model,
                request);
    }

    /**
     * 
     * @param contract
     * @param auditSetUpCommand
     * @param model
     * @param request
     * @return 
     */
    protected String launchAudit(
            Contract contract, 
            AuditSetUpCommand auditSetUpCommand,
            Model model, 
            HttpServletRequest request) {

        // If the form is valid, the audit is launched with data from form
        model.addAttribute(TgolKeyStore.CONTRACT_ID_KEY, auditSetUpCommand.getContractId());

        model.addAttribute(TgolKeyStore.AUDIT_SET_UP_COMMAND_KEY, auditSetUpCommand);

        return getAuditLauncherController().launchAudit(
                auditSetUpCommand, 
                getLocaleResolver().resolveLocale(request), 
                model);
    }
    
    /**
     *
     * @param model
     * @param contract
     * @param auditSetUpCommand
     * @param authorisedReferentialList
     * @param parametersMap
     * @return
     */
    private String displayFormWithErrors(
            Model model,
            Contract contract,
            AuditSetUpCommand auditSetUpCommand,
            Collection<String> authorisedReferentialList,
            Map<String, List<AuditSetUpFormField>> parametersMap) {
        model.addAttribute(TgolKeyStore.EXPANDED_KEY, TgolKeyStore.EXPANDED_VALUE);
        model.addAttribute(TgolKeyStore.AUDIT_SET_UP_COMMAND_KEY,
                auditSetUpCommand);
        model.addAttribute(TgolKeyStore.PARAMETERS_MAP_KEY, parametersMap);
        
        // Get a fresh list of the auditSetUpFormField that handles the choice
        // of the referential and its level
        List<SelectFormField> refAndLevelFormFieldList = 
                    this.getFreshRefAndLevelSetUpFormFieldList(
                        authorisedReferentialList, 
                        referentialAndLevelFormFieldBuilderList);

        // Otherwise it corresponds to the default behaviour;
        
        // The selection of default level is delegated to the helper.
        // When the form is on error, the default level value corresponds to the 
        // one the user has chosen. 
        
        AuditSetUpFormFieldHelper.selectDefaultLevelFromLevelValue(
                    refAndLevelFormFieldList, 
                    auditSetUpCommand.getLevel());
        
        model.addAttribute(TgolKeyStore.LEVEL_LIST_KEY, refAndLevelFormFieldList);

        // if the user is authenticated, the url of the form is under
        // /home/contract/. To display error pages, we need to precise the
        // backward relative path and to add the breadCrumb.
        model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY, contract.getLabel());
        ScopeEnum auditScope = auditSetUpCommand.getScope();
        switch (auditScope) {
            case DOMAIN : 
                return TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME;
            case PAGE :    
                return TgolKeyStore.AUDIT_PAGE_SET_UP_VIEW_NAME;
            case FILE : 
                return TgolKeyStore.AUDIT_UPLOAD_SET_UP_VIEW_NAME;
        }
        return TgolKeyStore.OUPS_VIEW_NAME;
    }
    
    /**
     * This methods checks whether the current user is allowed to display the
     * audit set-up for a given contract. To do so, we verify that the contract
     * belongs to the current user. We also check that the current contract handles
     * the functionality associated with the set-up page. 
     * 
     * @param contract
     * @param viewName
     * @return
     *      true if the user is allowed to display the result, false otherwise.
     */
    protected boolean isUserAllowedToDisplaySetUpPage(
            Contract contract, 
            String viewName) {
        if (contract == null) {
            throw new ForbiddenPageException(getCurrentUser());
        }
        User user = getCurrentUser();
        if (!contract.getUser().getId().equals(user.getId())) {
            throw new ForbiddenPageException(user);
        }
        Collection<String> functionalitySet = getAuthorisedFunctionalityCodeFromContract(contract);
        if (!functionalitySet.contains(viewFunctionalityBindingMap.get(viewName))) {
            throw new ForbiddenPageException(user);
        }
        return true;
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
            List<SelectFormField> refAndLevelFormFieldList,
            Map<String, List<AuditSetUpFormField>> optionFormFieldMap) {
        model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY, contract.getLabel());
        model.addAttribute(TgolKeyStore.URL_KEY, 
                getContractDataService().getUrlFromContractOption(contract));
        model.addAttribute(TgolKeyStore.PARAMETERS_MAP_KEY, optionFormFieldMap);
        model.addAttribute(TgolKeyStore.LEVEL_LIST_KEY, refAndLevelFormFieldList);
    }

    /**
     * Create a fresh map of auditSetUpFormField from an auditSetUpFormFieldBuilder
     * map
     * 
     * @param contract
     * @param auditSetUpFormFieldBuilderMap
     * @return 
     */
    protected Map<String, List<AuditSetUpFormField>> getFreshAuditSetUpFormFieldMap (
            Contract contract,
            Map<String, List<AuditSetUpFormFieldBuilderImpl>> auditSetUpFormFieldBuilderMap) {

        // Copy the audit setup form field map from the builders
        Map<String, List<AuditSetUpFormField>> initialisedSetUpFormFielMap = new LinkedHashMap();
        for (Map.Entry<String, List<AuditSetUpFormFieldBuilderImpl>> entry : auditSetUpFormFieldBuilderMap.entrySet()) {
            initialisedSetUpFormFielMap.put(
                    entry.getKey(), 
                    getFreshAuditSetUpFormFieldList(entry.getValue()));
        }
        // once the FormField are created, we check whether any option associated
        // with the contract overide the default values of the element
        applyRestrictionRegardingOption(
                contract.getOptionElementSet(), 
                initialisedSetUpFormFielMap.values());
        return initialisedSetUpFormFielMap;
    }
    
    /**
     * Create a fresh list of auditSetUpFormField from a auditSetUpFormFieldBuilder
     * list
     * 
     * @param auditSetUpFormFieldBuilderList
     * @return 
     */
    protected List<AuditSetUpFormField> getFreshAuditSetUpFormFieldList(
            List<AuditSetUpFormFieldBuilderImpl> auditSetUpFormFieldBuilderList) {
        List<AuditSetUpFormField> setUpFormFieldList = new LinkedList();
        for (AuditSetUpFormFieldBuilderImpl seb : auditSetUpFormFieldBuilderList) {
            setUpFormFieldList.add(seb.build());
        }
        return setUpFormFieldList;
    }
    
    /**
     * Create a fresh list of SelectFormField dedicated to the referential and
     * level choice.
     * The call of the helper method is due to activation mechanism. 
     * In this case, the activation of the element is done through the referential
     * list associated with the contract
     * 
     * @param authorisedReferentialList
     * @param auditSetUpFormFieldBuilderList
     * @return 
     */
    protected List<SelectFormField> getFreshRefAndLevelSetUpFormFieldList(
            Collection<String> authorisedReferentialList,
            List<SelectFormFieldBuilderImpl> auditSetUpFormFieldBuilderList) {
        
        List<SelectFormField> selectFormFieldList = new LinkedList();
        for (SelectFormFieldBuilderImpl seb : auditSetUpFormFieldBuilderList) {
            
            // Create the SelectElement from the builder
            SelectFormField selectFormField = seb.build();
            
            // enable-disable elements from the authorised referentials
            AuditSetUpFormFieldHelper.activateAllowedReferentialField(
                    selectFormField, 
                    authorisedReferentialList);
            
            // add the fresh instance to the returned list
            selectFormFieldList.add(selectFormField);
        }
        return selectFormFieldList;
    }
    
    /**
     * 
     * @param optionElementSet 
     * @param setUpFormFielList 
     */
    protected void applyRestrictionRegardingOption(
            Collection<OptionElement> optionElementSet, 
            Collection<List<AuditSetUpFormField>> setUpFormFielList) {
        if (optionElementSet.isEmpty()) {
            return;
        }
        for (List<AuditSetUpFormField> apl : setUpFormFielList) {
            for (AuditSetUpFormField ap : apl) {
                AuditSetUpFormFieldHelper.applyRestrictionToAuditSetUpFormField(
                        ap, 
                        optionElementSet);
            }
        }
    }

    /**
     * The contract handles a list of authorised referential. This method parses
     * the Collection of Refential associated with the contract and returns a list
     * of String containing the code of each authorised referential.
     * 
     * @param contract
     * @return 
     */
    protected Collection<String> getAuthorisedReferentialCodeFromContract (Contract contract) {
        Set<String> authorisedReferentialSet = new HashSet();
        for (Referential ref : contract.getReferentialSet()) {
            authorisedReferentialSet.add(ref.getCode());
        }
        return authorisedReferentialSet;
    }
    
    /**
     * The contract handles a list of authorised functionality. This method parses
     * the Collection of Functionalities associated with the contract and returns a list
     * of String containing the code of each authorised functionality.
     * 
     * @param contract
     * @return 
     */
    protected Collection<String> getAuthorisedFunctionalityCodeFromContract (Contract contract) {
        Set<String> authorisedFunctionalitySet = new HashSet();
        for (Functionality funct : contract.getFunctionalitySet()) {
            authorisedFunctionalitySet.add(funct.getCode());
        }
        return authorisedFunctionalitySet;
    }
    
    /**
     * 
     * @param authorisedReferentialList
     * @return 
     */
    protected String getDefaultReferential(Collection<String> authorisedReferentialList) {
        if (authorisedReferentialList.isEmpty()) {
            return "";
        }
        if (authorisedReferentialList.contains(defaultReferential)) {
            return defaultReferential;
        } else {
            return authorisedReferentialList.iterator().next();
        }
    }
    
}