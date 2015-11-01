/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Asqatasun.org
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */

package org.tanaguru.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.emailsender.EmailSender;
import org.tanaguru.entity.service.audit.AuditDataService;
import org.tanaguru.util.MD5Encoder;
import org.tanaguru.webapp.command.CreateContractCommand;
import org.tanaguru.webapp.command.CreateUserCommand;
import org.tanaguru.webapp.command.factory.CreateContractCommandFactory;
import org.tanaguru.webapp.command.factory.CreateUserCommandFactory;
import org.tanaguru.webapp.entity.contract.Act;
import org.tanaguru.webapp.entity.contract.Contract;
import org.tanaguru.webapp.entity.service.contract.ActDataService;
import org.tanaguru.webapp.entity.user.User;
import org.tanaguru.webapp.form.builder.FormFieldBuilder;
import org.tanaguru.webapp.form.parameterization.ContractOptionFormField;
import org.tanaguru.webapp.form.parameterization.builder.ContractOptionFormFieldBuilder;
import org.tanaguru.webapp.util.TgolKeyStore;
import org.tanaguru.webapp.util.webapp.ExposablePropertyPlaceholderConfigurer;
import org.tanaguru.webapp.validator.CreateContractFormValidator;
import org.tanaguru.webapp.validator.CreateUserFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 *
 * @author jkowalczyk
 */
@Controller
public class AbstractUserAndContractsController extends AbstractController{

//    public static final String EMAIL_FROM_KEY="emailFrom";
//    public static final String EMAIL_TO_KEY="emailTo";
//    public static final String EMAIL_SUBJECT_KEY="emailSubject";
//    public static final String EMAIL_CONTENT_KEY="emailContent";
//    private static final String URL_KEY="#urlToTest";
//    private static final String EMAIL_KEY="#email";
//    private static final String FIRST_NAME_KEY="#firstName";
//    private static final String LAST_NAME_KEY="#lastName";
//    private static final String PHONE_NUMBER_KEY="#phoneNumber";

    List<FormFieldBuilder> displayOptionFieldsBuilderList;
    public final void setDisplayOptionFieldsBuilderList(final List<FormFieldBuilder> formFieldBuilderList) {
        this.displayOptionFieldsBuilderList = formFieldBuilderList;
    }
    
    private Map<String, List<ContractOptionFormFieldBuilder>> contractOptionFormFieldBuilderMap;
    public Map<String, List<ContractOptionFormFieldBuilder>> getContractOptionFormFieldBuilderMap() {
        return contractOptionFormFieldBuilderMap;
    }

    public final void setContractOptionFormFieldBuilderMap(
            final Map<String, List<ContractOptionFormFieldBuilder>> formFieldBuilderMap) {
        this.contractOptionFormFieldBuilderMap = formFieldBuilderMap;
    }
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }
    
    private ExposablePropertyPlaceholderConfigurer exposablePropertyPlaceholderConfigurer;
    @Autowired
    public final void setExposablePropertyPlaceholderConfigurer(ExposablePropertyPlaceholderConfigurer exposablePropertyPlaceholderConfigurer) {
        this.exposablePropertyPlaceholderConfigurer = exposablePropertyPlaceholderConfigurer;
    }

    private EmailSender emailSender;
    @Autowired
    public final void setEmailSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }
    
    private CreateUserFormValidator createUserFormValidator;
    public CreateUserFormValidator getCreateUserFormValidator() {
        return createUserFormValidator;
    }

    public final void setCreateUserFormValidator(CreateUserFormValidator createUserFormValidator) {
        this.createUserFormValidator = createUserFormValidator;
    }
    
    private CreateContractFormValidator createContractFormValidator;
    public CreateContractFormValidator getCreateContractFormValidator() {
        return createContractFormValidator;
    }

    public final void setCreateContractFormValidator(CreateContractFormValidator createContractFormValidator) {
        this.createContractFormValidator = createContractFormValidator;
    }
    
    private ActDataService actDataService;
    public ActDataService getActDataService() {
        return actDataService;
    }

    @Autowired
    public void setActDataService(ActDataService actDataService) {
        this.actDataService = actDataService;
    }
    
    private AuditDataService auditDataService;
    public AuditDataService getAuditDataService() {
        return auditDataService;
    }

    @Autowired
    public void setAuditDataService(AuditDataService auditDataService) {
        this.auditDataService = auditDataService;
    }

    /**
     * 
     * @param model
     * @param user
     * @param successViewName
     * @return 
     */
    protected String prepateDataAndReturnCreateUserView(
            Model model,
            User user,
            String successViewName) {
        CreateUserCommand createUserCommand;
        if (user != null) {
            createUserCommand = CreateUserCommandFactory.getInstance().getInitialisedCreateUserCommand(user);
            model.addAttribute(TgolKeyStore.USER_NAME_KEY,createUserCommand.getEmail());
        } else {
            createUserCommand = CreateUserCommandFactory.getInstance().getNewCreateUserCommand();
        }
        model.addAttribute(TgolKeyStore.CREATE_USER_COMMAND_KEY,createUserCommand);
        return successViewName;
    }
    
    /**
     * 
     * @param model
     * @param user
     * @param contract
     * @param optionFormFieldMap
     * @param successViewName
     * @return 
     */
    protected String prepateDataAndReturnCreateContractView(
            Model model,
            User user,
            Contract contract,
            Map<String, List<ContractOptionFormField>> optionFormFieldMap, 
            String successViewName) {
        CreateContractCommand createContractCommand;

        if (contract != null) {
            createContractCommand = CreateContractCommandFactory.getInstance().getInitialisedCreateContractCommand(contract);
            model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY,createContractCommand.getLabel());
        } else {
            createContractCommand = CreateContractCommandFactory.getInstance().getNewCreateContractCommand();
        }
        if (user != null) {
            model.addAttribute(TgolKeyStore.USER_NAME_KEY,user.getEmail1());
            model.addAttribute(TgolKeyStore.USER_ID_KEY,user.getId());
        } else {
            model.addAttribute(TgolKeyStore.USER_LIST_KEY,getUserDataService().findAll());
        }
        model.addAttribute(TgolKeyStore.CREATE_CONTRACT_COMMAND_KEY,createContractCommand);
        model.addAttribute(TgolKeyStore.OPTIONS_MAP_KEY, optionFormFieldMap);

        return successViewName;
    }

    /**
     * A new user can be created from the main form that can be accessed without 
     * being authentified. In this case, we check the validity of the filled-in 
     * url and we prevent the new users to be activated and created with admin 
     * privileges.
     * On the other side, if the user is created from the admin interface, it can
     * be set with activation and admin privileges info but the check of the url
     * is useless cause the field has been removed from the form.
     * 
     * @param createUserCommand
     * @param result
     * @param model
     * @param successViewName
     * @param errorViewName
     * @param newUserFromAdmin
     * @param successMessageKey
     * @return
     * @throws Exception
     */
    protected String submitCreateUserForm (
            CreateUserCommand createUserCommand,
            BindingResult result,
            Model model,
            String successViewName,
            String errorViewName,
            boolean newUserFromAdmin,
            String successMessageKey) throws Exception {

        createUserFormValidator.setCheckSiteUrl(!newUserFromAdmin);
        // We check whether the form is valid
        createUserFormValidator.validate(createUserCommand, result);
        // If the form has some errors, we display it again with errors' details
        if (result.hasErrors()) {
            return displayFormWithErrors(
                    model,
                    createUserCommand,
                    errorViewName);
        }
        User user;
        if (!newUserFromAdmin) {
            user = createUser(createUserCommand,false,false);
            sendEmailInscription(user);
        } else {
            user = createUser(createUserCommand,true,true);
            model.addAttribute(TgolKeyStore.USER_LIST_KEY,getUserDataService().findAll());
            model.addAttribute(successMessageKey,user.getEmail1());
        }
        return successViewName;
    }
    
    /**
     * This methods controls the validity of the form and updated the user 
     * 
     * @param createUserCommand
     * @param result
     * @param request
     * @param model
     * @param userToModify
     * @param successViewName
     * @param errorViewName
     * @param updateAllUserData
     * @param updateUserFromAdmin
     * @param successMessageKey
     * @return
     * @throws Exception
     */
    protected String submitUpdateUserForm (
            CreateUserCommand createUserCommand,
            BindingResult result,
            HttpServletRequest request,
            Model model,
            User userToModify,
            String successViewName,
            String errorViewName,
            boolean updateAllUserData,
            boolean updateUserFromAdmin,
            String successMessageKey)
            throws Exception {

        // We check whether the form is valid
        createUserFormValidator.validateUpdate(createUserCommand, result, userToModify.getEmail1());
        // If the form has some errors, we display it again with errors' details
        if (result.hasErrors()) {
            return displayFormWithErrors(
                    model,
                    createUserCommand,
                    errorViewName);
        }
        User user = updateUser(
                createUserCommand,
                userToModify,
                updateAllUserData, 
                updateUserFromAdmin);

        // when updated from admin page, the id of the user to modify is passed
        // through the session and needs to be cleaned up once updated.
        if (updateUserFromAdmin) {
            model.addAttribute(TgolKeyStore.USER_LIST_KEY,getUserDataService().findAll());
            request.getSession().removeAttribute(TgolKeyStore.USER_ID_KEY);
        }
        if (successMessageKey != null) {
            model.addAttribute(successMessageKey,user.getEmail1());
        }
        return successViewName;
    }
    
    /**
     * Create a user entit
     * @param createUserCommand
     * @return
     * @throws Exception
     */
    private User createUser(
            CreateUserCommand createUserCommand,
            boolean allowActivation,
            boolean allowAdmin) throws Exception {
        User user = getUserDataService().create();
        user.setEmail1(createUserCommand.getEmail());
        user.setFirstName(createUserCommand.getFirstName());
        user.setName(createUserCommand.getLastName());
        user.setPhoneNumber(createUserCommand.getPhoneNumber());
        user.setPassword(MD5Encoder.MD5(createUserCommand.getPassword()));
        user.setWebUrl1(createUserCommand.getSiteUrl());
        if (allowActivation) {
            user.setAccountActivation(createUserCommand.getActivated());
        } else {
            user.setAccountActivation(false);
        }
        if (allowAdmin && createUserCommand.getAdmin()) {
            user.setRole(CreateUserCommandFactory.getInstance().getAdminRole());
        } else {
            user.setRole(CreateUserCommandFactory.getInstance().getUserRole());
        }
        getUserDataService().saveOrUpdate(user);
        return user;
    }

    /**
     * Update a user entity
     * 
     * @param createUserCommand
     * @param user
     * @param updateAllUserData
     * @param updateUserFromAdmin
     * @return
     * @throws Exception 
     */
    private User updateUser(
            CreateUserCommand createUserCommand,
            User user,
            boolean updateAllUserData,
            boolean updateUserFromAdmin) throws Exception {
        boolean hasSessionToBeUpdated = true;
        if (updateUserFromAdmin && !user.getEmail1().equals(getAuthenticatedUsername())) {
            hasSessionToBeUpdated = false;
        }
        if (updateAllUserData) {
            user.setEmail1(createUserCommand.getEmail());
        }
        user.setFirstName(createUserCommand.getFirstName());
        user.setName(createUserCommand.getLastName());
        user.setPhoneNumber(createUserCommand.getPhoneNumber());
        if (updateAllUserData) {
            user.setAccountActivation(createUserCommand.getActivated());
            if (createUserCommand.getAdmin()) {
                user.setRole(CreateUserCommandFactory.getInstance().getAdminRole());
            } else {
                user.setRole(CreateUserCommandFactory.getInstance().getUserRole());
            }
        }
        getUserDataService().saveOrUpdate(user);
        // the current user accessible from the session needs also to be updated
        if (hasSessionToBeUpdated) {
            updateCurrentUser(user);
        }
        return user;
    }
    
    /**
     * 
     * @param model
     * @param CreateUserCommand
     * @param errorViewName
     * @return
     */
    private String displayFormWithErrors(
            Model model,
            CreateUserCommand createUserCommand,
            String errorViewName) {
        model.addAttribute(TgolKeyStore.CREATE_USER_COMMAND_KEY,
                createUserCommand);
        return errorViewName;
    }
    
    /**
     * 
     * @param model
     * @param createContractCommand
     * @param userName
     * @param userId
     * @param optionFormFieldMap
     * @param errorViewName
     * @return 
     */
    public String displayFormWithErrors(
            Model model,
            CreateContractCommand createContractCommand,
            String userName,
            Long userId,
            Map<String, List<ContractOptionFormField>> optionFormFieldMap, 
            String errorViewName) {
        model.addAttribute(TgolKeyStore.CREATE_CONTRACT_COMMAND_KEY,
                CreateContractCommandFactory.getInstance().getInitialisedCreateContractCommand(createContractCommand));
        model.addAttribute(TgolKeyStore.USER_NAME_KEY,userName);
        model.addAttribute(TgolKeyStore.USER_ID_KEY,userId);
        model.addAttribute(TgolKeyStore.OPTIONS_MAP_KEY, optionFormFieldMap);
        // case : create contract for multiple users
        if (userName == null && userId == null) {
            model.addAttribute(TgolKeyStore.USER_LIST_KEY, getUserDataService().findAll());
        }
        return errorViewName;
    }
    
        /**
     * This method gets data from a property file to fill-in the inscription
     * e-mail
     * @param user
     */
    private void sendEmailInscription(User user) {
        String emailFrom =
            exposablePropertyPlaceholderConfigurer.getResolvedProps().get(TgolKeyStore.EMAIL_FROM_KEY);
        String[] emailTo =
                exposablePropertyPlaceholderConfigurer.getResolvedProps().get(TgolKeyStore.EMAIL_TO_KEY).split(",");
        Set<String> emailToSet = new HashSet();
        emailToSet.addAll(Arrays.asList(emailTo));
        String emailSubject =
            exposablePropertyPlaceholderConfigurer.getResolvedProps().get(TgolKeyStore.EMAIL_SUBJECT_KEY);
        String emailContent =
            exposablePropertyPlaceholderConfigurer.getResolvedProps().get(TgolKeyStore.EMAIL_CONTENT_KEY);
        emailContent = emailContent.replace(TgolKeyStore.EMAIL_CONTENT_EMAIL_KEY, user.getEmail1());
        emailContent = emailContent.replace(TgolKeyStore.EMAIL_CONTENT_URL_KEY, user.getWebUrl1());
        if (user.getName() != null) {
            emailContent = emailContent.replace(TgolKeyStore.EMAIL_CONTENT_LAST_NAME_KEY, user.getName());
        } else {
            emailContent = emailContent.replace(TgolKeyStore.EMAIL_CONTENT_LAST_NAME_KEY, "");
        }
        if (user.getFirstName() != null) {
            emailContent = emailContent.replace(TgolKeyStore.EMAIL_CONTENT_FIRST_NAME_KEY, user.getFirstName());
        } else {
            emailContent = emailContent.replace(TgolKeyStore.EMAIL_CONTENT_FIRST_NAME_KEY, "");
        }
        if (user.getPhoneNumber() != null) {
            emailContent = emailContent.replace(TgolKeyStore.EMAIL_CONTENT_PHONE_NUMBER_KEY, user.getPhoneNumber());
        } else {
            emailContent = emailContent.replace(TgolKeyStore.EMAIL_CONTENT_PHONE_NUMBER_KEY, "");
        }
        emailSender.sendEmail(
                emailFrom, 
                emailToSet, 
                Collections.EMPTY_SET, 
                StringUtils.EMPTY,
                emailSubject, 
                emailContent);
    }

    /**
     * @param user
     * @return whether the current authenticated user is admin
     */
    protected boolean isUserAdmin(User user) {
        return user.getRole().getId().equals(
                CreateUserCommandFactory.getInstance().getAdminRole().getId());
    }

    /**
     * @return whether the current authenticated user is admin
     */
    protected boolean isCurrentUserAdmin() {
        return isUserAdmin(getCurrentUser());
    }

    /**
    * 
    * @param contract 
    */
    protected void deleteAllAuditsFromContract(Contract contract) {
        Collection<Act> actsByContract = getActDataService().getAllActsByContract(contract);
        for (Act act : actsByContract) {
            if (act.getAudit() != null) {
                getAuditDataService().delete(act.getAudit().getId());
            }
            getActDataService().delete(act.getId());
        }
    }

    

}