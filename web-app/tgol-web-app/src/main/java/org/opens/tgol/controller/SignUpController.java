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

import org.opens.tgol.command.UserSignUpCommand;
import org.opens.tgol.emailsender.EmailSender;
import org.opens.tgol.entity.service.user.RoleDataService;
import org.opens.tgol.entity.user.Role;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.util.ExposablePropertyPlaceholderConfigurer;
import org.opens.tgol.util.TgolKeyStore;
import org.opens.tgol.validator.SignUpFormValidator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.opens.tanaguru.util.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
 *
 * @author jkowalczyk
 */
@Controller
public class SignUpController extends AbstractController {

    private static final String EMAIL_FROM_KEY="emailFrom";
    private static final String EMAIL_TO_KEY="emailTo";
    private static final String EMAIL_SUBJECT_KEY="emailSubject";
    private static final String EMAIL_CONTENT_KEY="emailContent";
    private static final String URL_KEY="#urlToTest";
    private static final String EMAIL_KEY="#email";
    private static final String FIRST_NAME_KEY="#firstName";
    private static final String LAST_NAME_KEY="#lastName";
    private static final Long USER_ROLE_ID=Long.valueOf("2");
    private static final String PHONE_NUMBER_KEY="#phoneNumber";

    private SignUpFormValidator signUpFormValidator;
    @Autowired
    public final void setSignUpFormValidator(SignUpFormValidator signUpFormValidator) {
        this.signUpFormValidator = signUpFormValidator;
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

    private Role userRole;
    @Autowired
    public final void setRoleDataService (RoleDataService roleDataService){
        userRole = roleDataService.read(USER_ROLE_ID);
    }

    public SignUpController() {
        super();
    }

    /**
     * This method displays the one page form for an authenticated user
     * @param contractId
     * @param model
     * @return
     */
    @RequestMapping(value = TgolKeyStore.SIGN_UP_URL, method = RequestMethod.GET)
    public String setUpSignUpPage(Model model) {

        model.addAttribute(TgolKeyStore.USER_SIGN_UP_COMMAND_KEY,
                new UserSignUpCommand());
         return TgolKeyStore.SIGN_UP_VIEW_NAME;
    }

    /**
     * This methods controls the validity of the form and launch an audit with
     * values populated by the user. In case of audit failure, an appropriate
     * message is displayed
     * @param launchAuditFromContractCommand
     * @param result
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST)
    protected String submitForm(
            @ModelAttribute(TgolKeyStore.USER_SIGN_UP_COMMAND_KEY) UserSignUpCommand userSignUpCommand,
            BindingResult result,
            Model model,
            HttpServletRequest request)
            throws Exception {

        // We check whether the form is valid
        signUpFormValidator.validate(userSignUpCommand, result);
        // If the form has some errors, we display it again with errors' details
        if (result.hasErrors()) {
            return displayFormWithErrors(
                    model,
                    userSignUpCommand,
                    request);
        }
        sendEmailInscription(createAndSaveUserFromFromData(userSignUpCommand));
        return TgolKeyStore.SIGN_UP_CONFIRMATION_VIEW_REDIRECT_NAME;
    }

    /**
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = TgolKeyStore.SIGN_UP_CONFIRMATION_URL, method = RequestMethod.GET)
    public String setUpSignUpConfirmationPage(Model model) {
        model.addAttribute(TgolKeyStore.USER_SIGN_UP_COMMAND_KEY,
                new UserSignUpCommand());
         return TgolKeyStore.SIGN_UP_CONFIRMATION_VIEW_NAME;
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
            UserSignUpCommand userSignUpCommand,
            HttpServletRequest request) {
        
        model.addAttribute(TgolKeyStore.USER_SIGN_UP_COMMAND_KEY,
                userSignUpCommand);
        return TgolKeyStore.SIGN_UP_VIEW_NAME;
    }

    /**
     * 
     * @param userSignUpCommand
     * @return
     * @throws Exception
     */
    private User createAndSaveUserFromFromData(UserSignUpCommand userSignUpCommand) throws Exception {
        User user = getUserDataService().create();
        user.setEmail1(userSignUpCommand.getEmail());
        user.setWebUrl1(userSignUpCommand.getSiteUrl());
        user.setFirstName(userSignUpCommand.getFirstName());
        user.setName(userSignUpCommand.getLastName());
        user.setPhoneNumber(userSignUpCommand.getPhoneNumber());
        user.setPassword(MD5Encoder.MD5(userSignUpCommand.getPassword()));
        user.setAccountActivation(false);
        user.setRole(userRole);
        getUserDataService().saveOrUpdate(user);
        return user;
    }

    /**
     * This method gets data from a property file to fill-in the inscription
     * e-mail
     * @param user
     */
    private void sendEmailInscription(User user) {
        String emailFrom =
            exposablePropertyPlaceholderConfigurer.getResolvedProps().get(EMAIL_FROM_KEY);
        String[] emailTo =
                exposablePropertyPlaceholderConfigurer.getResolvedProps().get(EMAIL_TO_KEY).split(",");
        Set<String> emailToSet = new HashSet<String>();
        emailToSet.addAll(Arrays.asList(emailTo));
        String emailSubject =
            exposablePropertyPlaceholderConfigurer.getResolvedProps().get(EMAIL_SUBJECT_KEY);
        String emailContent =
            exposablePropertyPlaceholderConfigurer.getResolvedProps().get(EMAIL_CONTENT_KEY);
        emailContent = emailContent.replace(EMAIL_KEY, user.getEmail1());
        emailContent = emailContent.replace(URL_KEY, user.getWebUrl1());
        if (user.getName() != null) {
            emailContent = emailContent.replace(LAST_NAME_KEY, user.getName());
        } else {
            emailContent = emailContent.replace(LAST_NAME_KEY, "");
        }
        if (user.getFirstName() != null) {
            emailContent = emailContent.replace(FIRST_NAME_KEY, user.getFirstName());
        } else {
            emailContent = emailContent.replace(FIRST_NAME_KEY, "");
        }
        if (user.getPhoneNumber() != null) {
            emailContent = emailContent.replace(PHONE_NUMBER_KEY, user.getPhoneNumber());
        } else {
            emailContent = emailContent.replace(PHONE_NUMBER_KEY, "");
        }
        emailSender.sendEmail(emailFrom, emailToSet, emailSubject, emailContent);
    }

}