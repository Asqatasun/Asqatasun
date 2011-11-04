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
import org.opens.tgol.entity.user.User;
import org.opens.tgol.util.TgolKeyStore;
import org.opens.tgol.validator.SignUpFormValidator;
import javax.servlet.http.HttpServletRequest;
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
public class AccountSettingsController extends AbstractController {

    private SignUpFormValidator signUpFormValidator;
    @Autowired
    public final void setSignUpFormValidator(SignUpFormValidator signUpFormValidator) {
        this.signUpFormValidator = signUpFormValidator;
    }

    public AccountSettingsController() {
        super();
    }

    /**
     * This method displays the one page form for an authenticated user
     * @param contractId
     * @param model
     * @return
     */
    @RequestMapping(value = TgolKeyStore.ACCOUNT_SETTINGS_URL, method = RequestMethod.GET)
    public String setAccountSettingsPagePage(Model model) {
        model.addAttribute(TgolKeyStore.USER_SIGN_UP_COMMAND_KEY,
                getInitialisedUserSignUpCommand());
        model.addAttribute(TgolKeyStore.BREAD_CRUMB_KEY, HomeController.buildBreadCrumb());
        model.addAttribute(TgolKeyStore.AUTHENTICATED_USER_KEY, getCurrentUser());
        return TgolKeyStore.ACCOUNT_SETTINGS_VIEW_NAME;
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
        signUpFormValidator.validateUpdate(userSignUpCommand, result, getCurrentUser().getEmail1());
        // If the form has some errors, we display it again with errors' details
        if (result.hasErrors()) {
            return displayFormWithErrors(
                    model,
                    userSignUpCommand);
        }
        updateUserData(userSignUpCommand);
        model.addAttribute(TgolKeyStore.ACCOUNT_DATA_UPDATED_KEY, true);
        model.addAttribute(TgolKeyStore.BREAD_CRUMB_KEY, HomeController.buildBreadCrumb());
        model.addAttribute(TgolKeyStore.AUTHENTICATED_USER_KEY, getCurrentUser());
        return TgolKeyStore.ACCOUNT_SETTINGS_VIEW_NAME;
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
            UserSignUpCommand userSignUpCommand) {
        model.addAttribute(TgolKeyStore.USER_SIGN_UP_COMMAND_KEY,
                userSignUpCommand);
        return TgolKeyStore.ACCOUNT_SETTINGS_VIEW_NAME;
    }

    /**
     * 
     * @param userSignUpCommand
     * @return
     * @throws Exception
     */
    private UserSignUpCommand getInitialisedUserSignUpCommand() {
        UserSignUpCommand userSignUpCommand = new UserSignUpCommand();
        User user = getCurrentUser();
        userSignUpCommand.setEmail(user.getEmail1());
        userSignUpCommand.setSiteUrl(user.getWebUrl1());
        userSignUpCommand.setFirstName(user.getFirstName());
        userSignUpCommand.setLastName(user.getName());
        userSignUpCommand.setPhoneNumber(user.getPhoneNumber());
        return userSignUpCommand;
    }

    /**
     *
     * @param userSignUpCommand
     * @return
     * @throws Exception
     */
    private User updateUserData(UserSignUpCommand userSignUpCommand) throws Exception {
        User user = getCurrentUser();
        if (user !=  null) {
            user.setEmail1(userSignUpCommand.getEmail());
            user.setFirstName(userSignUpCommand.getFirstName());
            user.setName(userSignUpCommand.getLastName());
            user.setPhoneNumber(userSignUpCommand.getPhoneNumber());
            getUserDataService().saveOrUpdate(user);
        }
        return user;
    }

}