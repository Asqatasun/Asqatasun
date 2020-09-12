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


import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.asqatasun.webapp.command.ContractSortCommand;
import org.asqatasun.webapp.command.CreateContractCommand;
import org.asqatasun.webapp.command.factory.CreateContractCommandFactory;
import org.asqatasun.webapp.command.helper.ContractSortCommandHelper;
import org.asqatasun.entity.contract.Contract;
import org.asqatasun.entity.service.contract.ContractDataService;
import org.asqatasun.entity.user.User;
import org.asqatasun.webapp.exception.ForbiddenPageException;
import org.asqatasun.webapp.exception.ForbiddenUserException;
import org.asqatasun.webapp.ui.form.builder.FormFieldBuilder;
import org.asqatasun.webapp.ui.form.parameterization.ContractOptionFormField;
import org.asqatasun.webapp.ui.form.parameterization.helper.ContractOptionFormFieldHelper;
import org.asqatasun.webapp.util.TgolKeyStore;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ContractManagementController extends AbstractUserAndContractsController {

    private final List<FormFieldBuilder> displayOptionFieldsBuilderList;
    private final CreateContractCommandFactory createContractCommandFactory;
    private final ContractSortCommandHelper contractSortCommandHelper;
    private final ContractDataService contractDataService;

    @Autowired
    public ContractManagementController(ContractSortCommandHelper contractSortCommandHelper,
                                        CreateContractCommandFactory createContractCommandFactory,
                                        ContractDataService contractDataService,
                                        @Qualifier(value="contractManagementOptionFieldsBuilderList")
                                                List<FormFieldBuilder> displayOptionFieldsBuilderList){
        super();
        this.contractSortCommandHelper = contractSortCommandHelper;
        this.createContractCommandFactory = createContractCommandFactory;
        this.contractDataService = contractDataService;
        this.displayOptionFieldsBuilderList = displayOptionFieldsBuilderList;
    }

    /**
     * @param userId
     * @param request
     * @param model
     * @return The pages audit set-up form page
     */
    @RequestMapping(value = TgolKeyStore.MANAGE_CONTRACTS_URL, method = RequestMethod.GET)
    @Secured({TgolKeyStore.ROLE_ADMIN_KEY})
    public String displayManageContractsAdminPage(
            @RequestParam(TgolKeyStore.USER_ID_KEY) String userId,
            HttpServletRequest request,
            Model model) {

        Long lUserId;
        try {
            lUserId = Long.valueOf(userId);
        } catch (NumberFormatException nfe) {
            throw new ForbiddenUserException();
        }
        if (request.getSession().getAttribute(TgolKeyStore.DELETED_CONTRACT_NAME_KEY) != null) {
            model.addAttribute(TgolKeyStore.DELETED_CONTRACT_NAME_KEY, 
                    request.getSession().getAttribute(TgolKeyStore.DELETED_CONTRACT_NAME_KEY));
            request.getSession().removeAttribute(TgolKeyStore.DELETED_CONTRACT_NAME_KEY);
        }
        if (request.getSession().getAttribute(TgolKeyStore.DELETED_CONTRACT_AUDITS_NAME_KEY) != null) {
            model.addAttribute(TgolKeyStore.DELETED_CONTRACT_AUDITS_NAME_KEY, 
                    request.getSession().getAttribute(TgolKeyStore.DELETED_CONTRACT_AUDITS_NAME_KEY));
            request.getSession().removeAttribute(TgolKeyStore.DELETED_CONTRACT_AUDITS_NAME_KEY);
        }
        if (request.getSession().getAttribute(TgolKeyStore.UPDATED_CONTRACT_NAME_KEY) != null) {
            model.addAttribute(TgolKeyStore.UPDATED_CONTRACT_NAME_KEY, 
                    request.getSession().getAttribute(TgolKeyStore.UPDATED_CONTRACT_NAME_KEY));
            request.getSession().removeAttribute(TgolKeyStore.UPDATED_CONTRACT_NAME_KEY);
        }
        if (request.getSession().getAttribute(TgolKeyStore.ADDED_CONTRACT_NAME_KEY) != null) {
            model.addAttribute(TgolKeyStore.ADDED_CONTRACT_NAME_KEY, 
                    request.getSession().getAttribute(TgolKeyStore.ADDED_CONTRACT_NAME_KEY));
            request.getSession().removeAttribute(TgolKeyStore.ADDED_CONTRACT_NAME_KEY);
        }
        User userToManage = userDataService.read(lUserId);

        model.addAttribute(
                TgolKeyStore.CONTRACT_LIST_KEY, 
                contractSortCommandHelper.prepareContract(
                    userToManage, 
                    null,
                    displayOptionFieldsBuilderList,
                    model));
        
        model.addAttribute(TgolKeyStore.USER_NAME_KEY, userToManage.getEmail1());
        return TgolKeyStore.MANAGE_CONTRACTS_VIEW_NAME;
    }
    
    /**
     * @param contractDisplayCommand
     * @param userId
     * @param model
     * @return The pages audit set-up form page
     */
    @RequestMapping(value = TgolKeyStore.MANAGE_CONTRACTS_URL, method = RequestMethod.POST)
    @Secured(TgolKeyStore.ROLE_ADMIN_KEY)
    public String submitManageContractsAdminPage(
            @ModelAttribute(TgolKeyStore.CONTRACT_SORT_COMMAND_KEY) ContractSortCommand contractDisplayCommand,
            @RequestParam(TgolKeyStore.USER_ID_KEY) String userId,
            Model model) {

        Long lUserId;
        try {
            lUserId = Long.valueOf(userId);
        } catch (NumberFormatException nfe) {
            throw new ForbiddenUserException();
        }
        User userToManage = userDataService.read(lUserId);
        
        model.addAttribute(
                TgolKeyStore.CONTRACT_LIST_KEY, 
                contractSortCommandHelper.prepareContract(
                    userToManage, 
                    contractDisplayCommand,
                    displayOptionFieldsBuilderList,
                    model));
        
        model.addAttribute(TgolKeyStore.USER_NAME_KEY, userToManage.getEmail1());
        return TgolKeyStore.MANAGE_CONTRACTS_VIEW_NAME;
    }
    
    /**
     * @param userId
     * @param request
     * @param response
     * @param model
     * @return The pages audit set-up form page
     */
    @RequestMapping(value = TgolKeyStore.ADD_CONTRACT_FROM_CONTRACT_MNGT_URL, method = RequestMethod.GET)
    @Secured(TgolKeyStore.ROLE_ADMIN_KEY)
    public String addContractAdminPage(
            @RequestParam(TgolKeyStore.USER_ID_KEY) String userId,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        Long lUserId;
        try {
            lUserId = Long.valueOf(userId);
        } catch (NumberFormatException nfe) {
            throw new ForbiddenUserException();
        }

        User userToManage = userDataService.read(lUserId);
        if (userToManage == null) {
            throw new ForbiddenUserException();
        }
        request.getSession().setAttribute(TgolKeyStore.USER_ID_KEY,lUserId);

        return prepateDataAndReturnCreateContractView(
                model,
                userToManage,
                null,
                ContractOptionFormFieldHelper.getFreshContractOptionFormFieldMap(contractOptionFormFieldBuilderMap),
                TgolKeyStore.ADD_CONTRACT_VIEW_NAME);
    }
    
    /**
     * @param createContractCommand
     * @param result
     * @param request
     * @param response
     * @param model
     * @return The pages audit set-up form page
     */
    @RequestMapping(value = TgolKeyStore.ADD_CONTRACT_FROM_CONTRACT_MNGT_URL, method = RequestMethod.POST)
    @Secured(TgolKeyStore.ROLE_ADMIN_KEY)
    public String submitAddContractAdminPage(
            @ModelAttribute(TgolKeyStore.CREATE_CONTRACT_COMMAND_KEY) CreateContractCommand createContractCommand,
            BindingResult result,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        
        Object userId = request.getSession().getAttribute(TgolKeyStore.USER_ID_KEY);
        Long lUserId;
        if (userId instanceof Long) {
            lUserId = (Long)userId;
        } else {
            try {
                lUserId = Long.valueOf(userId.toString());
            } catch (NumberFormatException nfe) {
                throw new ForbiddenUserException();
            }
        }
        
        Map<String, List<ContractOptionFormField>> optionFormFieldMap =
                    ContractOptionFormFieldHelper.getFreshContractOptionFormFieldMap(contractOptionFormFieldBuilderMap);

        createContractFormValidator.setContractOptionFormFieldMap(optionFormFieldMap);
        // We check whether the form is valid
        createContractFormValidator.validate(createContractCommand, result);
        // If the form has some errors, we display it again with errors' details
        User currentModifiedUser=userDataService.read(lUserId);

        if (result.hasErrors()) {
            return displayFormWithErrors(
                    model,
                    createContractCommand,
                    currentModifiedUser.getEmail1(),
                    lUserId,
                    optionFormFieldMap,
                    TgolKeyStore.EDIT_CONTRACT_VIEW_NAME);
        }
        
        Contract contract = contractDataService.create();
        contract.setUser(currentModifiedUser);
        contract = createContractCommandFactory.updateContractFromCommand(
                createContractCommand, 
                contract);
        
        saveOrUpdateContract(contract);

        request.getSession().setAttribute(TgolKeyStore.ADDED_CONTRACT_NAME_KEY,contract.getLabel());
        model.addAttribute(TgolKeyStore.USER_ID_KEY,contract.getUser().getId());
        request.getSession().removeAttribute(TgolKeyStore.USER_ID_KEY);
        return TgolKeyStore.MANAGE_CONTRACTS_VIEW_REDIRECT_NAME;
    }
    
    /**
     * @param contractId
     * @param request
     * @param response
     * @param model
     * @return The pages audit set-up form page
     */
    @RequestMapping(value = TgolKeyStore.EDIT_CONTRACT_URL, method = RequestMethod.GET)
    @Secured(TgolKeyStore.ROLE_ADMIN_KEY)
    public String editContractAdminPage(
            @RequestParam(TgolKeyStore.CONTRACT_ID_KEY) String contractId,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        Long lContractId;
        try {
            lContractId = Long.valueOf(contractId);
        } catch (NumberFormatException nfe) {
            throw new ForbiddenUserException();
        }

        Contract contract = contractDataService.read(lContractId);
        if (contract == null) {
            throw new ForbiddenPageException();
        }
        request.getSession().setAttribute(TgolKeyStore.CONTRACT_ID_KEY,contract.getId());

        return prepateDataAndReturnCreateContractView(
                model,
                contract.getUser(),
                contract,
                ContractOptionFormFieldHelper.getFreshContractOptionFormFieldMap(contractOptionFormFieldBuilderMap),
                TgolKeyStore.EDIT_CONTRACT_VIEW_NAME);
    }
    
    /**
     * @param createContractCommand
     * @param result
     * @param request
     * @param response
     * @param model
     * @return The pages audit set-up form page
     */
    @RequestMapping(value = TgolKeyStore.EDIT_CONTRACT_URL, method = RequestMethod.POST)
    @Secured(TgolKeyStore.ROLE_ADMIN_KEY)
    public String submitEditContractAdminPage(
            @ModelAttribute(TgolKeyStore.CREATE_CONTRACT_COMMAND_KEY) CreateContractCommand createContractCommand,
            BindingResult result,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        
        Object contractId = request.getSession().getAttribute(TgolKeyStore.CONTRACT_ID_KEY);
        Long lContractId;
        if (contractId instanceof Long) {
            lContractId = (Long)contractId;
        } else {
            try {
                lContractId = Long.valueOf(contractId.toString());
            } catch (NumberFormatException nfe) {
                throw new ForbiddenUserException();
            }
        }

        Contract contract = contractDataService.read(lContractId);
        Map<String, List<ContractOptionFormField>> optionFormFieldMap = 
                    ContractOptionFormFieldHelper.getFreshContractOptionFormFieldMap(contractOptionFormFieldBuilderMap);

        createContractFormValidator.setContractOptionFormFieldMap(optionFormFieldMap);
        // We check whether the form is valid
        createContractFormValidator.validate(createContractCommand, result);
        // If the form has some errors, we display it again with errors' details
        if (result.hasErrors()) {
            return displayFormWithErrors(
                    model,
                    createContractCommand,
                    contract.getUser().getEmail1(),
                    contract.getUser().getId(),
                    optionFormFieldMap,
                    TgolKeyStore.EDIT_CONTRACT_VIEW_NAME);
        }
        
        contract = createContractCommandFactory.updateContractFromCommand(createContractCommand, contract);
        saveOrUpdateContract(contract);

        request.getSession().setAttribute(TgolKeyStore.UPDATED_CONTRACT_NAME_KEY,contract.getLabel());
        model.addAttribute(TgolKeyStore.USER_ID_KEY,contract.getUser().getId());
        request.getSession().removeAttribute(TgolKeyStore.CONTRACT_ID_KEY);
        return TgolKeyStore.MANAGE_CONTRACTS_VIEW_REDIRECT_NAME;
    }
    
    /**
     * @param contractId
     * @param request
     * @param response
     * @param model
     * @return The pages audit set-up form page
     */
    @RequestMapping(value = TgolKeyStore.DELETE_CONTRACT_URL, method = RequestMethod.GET)
    @Secured(TgolKeyStore.ROLE_ADMIN_KEY)
    public String deleteContractPage(
            @RequestParam(TgolKeyStore.CONTRACT_ID_KEY) String contractId,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        Long lContractId;
        try {
            lContractId = Long.valueOf(contractId);
        } catch (NumberFormatException nfe) {
            throw new ForbiddenUserException();
        }
        Contract contractToDelete = contractDataService.read(lContractId);
        
        request.getSession().setAttribute(TgolKeyStore.CONTRACT_ID_TO_DELETE_KEY,contractToDelete.getId());
        model.addAttribute(TgolKeyStore.CONTRACT_NAME_TO_DELETE_KEY, contractToDelete.getLabel());
        model.addAttribute(TgolKeyStore.USER_ID_KEY,contractToDelete.getUser().getId());
        model.addAttribute(TgolKeyStore.USER_NAME_KEY,contractToDelete.getUser().getEmail1());
        
        return TgolKeyStore.DELETE_CONTRACT_VIEW_NAME;
    }

    /**
     * 
     * @param request
     * @param response
     * @param model
     * @return 
     */
    @RequestMapping(value = TgolKeyStore.DELETE_CONTRACT_URL, method = RequestMethod.POST)
    @Secured(TgolKeyStore.ROLE_ADMIN_KEY)
    public String submitDeleteContractConfirmation(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        Object contractId = request.getSession().getAttribute(TgolKeyStore.CONTRACT_ID_TO_DELETE_KEY);
        Long lContractId;
        if (contractId instanceof Long) {
            lContractId = (Long)contractId;
        } else {
            try {
                lContractId = Long.valueOf(contractId.toString());
            } catch (NumberFormatException nfe) {
                throw new ForbiddenUserException();
            }
        }
        Contract contractToDelete = contractDataService.read(lContractId);
        
        contractDataService.delete(contractToDelete.getId());
        // The current user has been updated, its storage in session needs also
        // to be updated
        if (getAuthenticatedUsername().equals(contractToDelete.getUser().getEmail1())) {
            updateCurrentUser(userDataService.read(contractToDelete.getUser().getId()));
        }
        request.getSession().removeAttribute(TgolKeyStore.CONTRACT_ID_TO_DELETE_KEY);
        request.getSession().setAttribute(TgolKeyStore.DELETED_CONTRACT_NAME_KEY,contractToDelete.getLabel());
        model.addAttribute(TgolKeyStore.USER_ID_KEY,contractToDelete.getUser().getId());
        
        return TgolKeyStore.MANAGE_CONTRACTS_VIEW_REDIRECT_NAME;
    }
    
    /**
     * 
     * @param contractId
     * @param request
     * @param response
     * @param model
     * @return 
     */
    @RequestMapping(value = TgolKeyStore.DELETE_CONTRACT_AUDITS_URL, method = RequestMethod.GET)
    @Secured(TgolKeyStore.ROLE_ADMIN_KEY)
    public String deleteContractAuditsPage(
            @RequestParam(TgolKeyStore.CONTRACT_ID_KEY) String contractId,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        Long lContractId;
        try {
            lContractId = Long.valueOf(contractId);
        } catch (NumberFormatException nfe) {
            throw new ForbiddenUserException();
        }
        Contract contractToDelete = contractDataService.read(lContractId);
        
        model.addAttribute(TgolKeyStore.CONTRACT_NAME_TO_DELETE_KEY, contractToDelete.getLabel());
        model.addAttribute(TgolKeyStore.USER_ID_KEY, contractToDelete.getUser().getId());
        model.addAttribute(TgolKeyStore.USER_NAME_KEY, contractToDelete.getUser().getEmail1());
        request.getSession().setAttribute(TgolKeyStore.CONTRACT_ID_TO_DELETE_KEY,contractToDelete.getId());
        
        return TgolKeyStore.DELETE_AUDITS_VIEW_NAME;
    }
    
    /**
     * 
     * @param request
     * @param response
     * @param model
     * @return 
     */
    @RequestMapping(value = TgolKeyStore.DELETE_CONTRACT_AUDITS_URL, method = RequestMethod.POST)
    @Secured(TgolKeyStore.ROLE_ADMIN_KEY)
    public String deleteContractAuditsConfirmationPage(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        Object contractId = request.getSession().getAttribute(TgolKeyStore.CONTRACT_ID_TO_DELETE_KEY);
        Long lContractId;
        if (contractId instanceof Long) {
            lContractId = (Long)contractId;
        } else {
            try {
                lContractId = Long.valueOf(contractId.toString());
            } catch (NumberFormatException nfe) {
                throw new ForbiddenUserException();
            }
        }
        Contract contractToDelete = contractDataService.read(lContractId);
        deleteAllAuditsFromContract(contractToDelete);
        request.getSession().removeAttribute(TgolKeyStore.CONTRACT_ID_TO_DELETE_KEY);
        request.getSession().setAttribute(TgolKeyStore.DELETED_CONTRACT_AUDITS_NAME_KEY,contractToDelete.getLabel());
        model.addAttribute(TgolKeyStore.USER_ID_KEY,contractToDelete.getUser().getId());
        return TgolKeyStore.MANAGE_CONTRACTS_VIEW_REDIRECT_NAME;
    }
 
    /**
     * 
     * @param contract 
     */
    private void saveOrUpdateContract(Contract contract) {
       contractDataService.saveOrUpdate(contract);
       if (getAuthenticatedUsername().equals(contract.getUser().getEmail1())) {
           updateCurrentUser(userDataService.read(contract.getUser().getId()));
       }
    }
    
}
