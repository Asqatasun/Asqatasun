package org.tanaguru.webapp.controller;

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


import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.tanaguru.webapp.command.ContractSortCommand;
import org.tanaguru.webapp.command.CreateContractCommand;
import org.tanaguru.webapp.command.factory.CreateContractCommandFactory;
import org.tanaguru.webapp.command.helper.ContractSortCommandHelper;
import org.tanaguru.webapp.entity.contract.Contract;
import org.tanaguru.webapp.entity.user.User;
import org.tanaguru.webapp.exception.ForbiddenPageException;
import org.tanaguru.webapp.exception.ForbiddenUserException;
import org.tanaguru.webapp.form.parameterization.ContractOptionFormField;
import org.tanaguru.webapp.form.parameterization.helper.ContractOptionFormFieldHelper;
import org.tanaguru.webapp.util.TgolKeyStore;
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

    public ContractManagementController() {
        super();
    }

    /**
     * @param userId
     * @param request
     * @param response
     * @param model
     * @return The pages audit set-up form page
     */
    @RequestMapping(value = TgolKeyStore.MANAGE_CONTRACTS_URL, method = RequestMethod.GET)
    @Secured({TgolKeyStore.ROLE_ADMIN_KEY})
    public String displayManageContractsAdminPage(
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
        User userToManage = getUserDataService().read(lUserId);

        model.addAttribute(
                TgolKeyStore.CONTRACT_LIST_KEY, 
                ContractSortCommandHelper.prepareContract(
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
     * @param request
     * @param response
     * @param model
     * @return The pages audit set-up form page
     */
    @RequestMapping(value = TgolKeyStore.MANAGE_CONTRACTS_URL, method = RequestMethod.POST)
    @Secured(TgolKeyStore.ROLE_ADMIN_KEY)
    public String submitManageContractsAdminPage(
            @ModelAttribute(TgolKeyStore.CONTRACT_SORT_COMMAND_KEY) ContractSortCommand contractDisplayCommand,
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
        User userToManage = getUserDataService().read(lUserId);
        
        model.addAttribute(
                TgolKeyStore.CONTRACT_LIST_KEY, 
                ContractSortCommandHelper.prepareContract(
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

        User userToManage = getUserDataService().read(lUserId);
        if (userToManage == null) {
            throw new ForbiddenUserException();
        }
        request.getSession().setAttribute(TgolKeyStore.USER_ID_KEY,lUserId);

        return prepateDataAndReturnCreateContractView(
                model,
                userToManage,
                null,
                ContractOptionFormFieldHelper.getFreshContractOptionFormFieldMap(getContractOptionFormFieldBuilderMap()),
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
                    ContractOptionFormFieldHelper.getFreshContractOptionFormFieldMap(getContractOptionFormFieldBuilderMap());

        getCreateContractFormValidator().setContractOptionFormFieldMap(optionFormFieldMap);
        // We check whether the form is valid
        getCreateContractFormValidator().validate(createContractCommand, result);
        // If the form has some errors, we display it again with errors' details
        User currentModifiedUser=getUserDataService().read(lUserId);

        if (result.hasErrors()) {
            return displayFormWithErrors(
                    model,
                    createContractCommand,
                    currentModifiedUser.getEmail1(),
                    lUserId,
                    optionFormFieldMap,
                    TgolKeyStore.EDIT_CONTRACT_VIEW_NAME);
        }
        
        Contract contract = getContractDataService().create();
        contract.setUser(currentModifiedUser);
        contract = CreateContractCommandFactory.getInstance().updateContractFromCommand(
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

        Contract contract = getContractDataService().read(lContractId);
        if (contract == null) {
            throw new ForbiddenPageException();
        }
        request.getSession().setAttribute(TgolKeyStore.CONTRACT_ID_KEY,contract.getId());

        return prepateDataAndReturnCreateContractView(
                model,
                contract.getUser(),
                contract,
                ContractOptionFormFieldHelper.getFreshContractOptionFormFieldMap(getContractOptionFormFieldBuilderMap()),
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

        Contract contract = getContractDataService().read(lContractId);
        Map<String, List<ContractOptionFormField>> optionFormFieldMap = 
                    ContractOptionFormFieldHelper.getFreshContractOptionFormFieldMap(getContractOptionFormFieldBuilderMap());

        getCreateContractFormValidator().setContractOptionFormFieldMap(optionFormFieldMap);
        // We check whether the form is valid
        getCreateContractFormValidator().validate(createContractCommand, result);
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
        
        contract = CreateContractCommandFactory.getInstance().updateContractFromCommand(createContractCommand, contract);
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
        Contract contractToDelete = getContractDataService().read(lContractId);
        
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
        Contract contractToDelete = getContractDataService().read(lContractId);
        
        getContractDataService().delete(contractToDelete.getId());
        // The current user has been updated, its storage in session needs also
        // to be updated
        if (getAuthenticatedUsername().equals(contractToDelete.getUser().getEmail1())) {
            updateCurrentUser(getUserDataService().read(contractToDelete.getUser().getId()));
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
        Contract contractToDelete = getContractDataService().read(lContractId);
        
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
        Contract contractToDelete = getContractDataService().read(lContractId);
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
       getContractDataService().saveOrUpdate(contract);
       if (getAuthenticatedUsername().equals(contract.getUser().getEmail1())) {
           updateCurrentUser(getUserDataService().read(contract.getUser().getId()));
       }
    }
    
}