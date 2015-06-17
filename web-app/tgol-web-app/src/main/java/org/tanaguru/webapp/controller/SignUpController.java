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

import org.tanaguru.webapp.command.CreateUserCommand;
import org.tanaguru.webapp.util.TgolKeyStore;
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
public class SignUpController extends AbstractUserAndContractsController {

    public SignUpController() {
        super();
    }

    /**
     * This method displays the one page form for an authenticated user
     * @param model
     * @return
     */
    @RequestMapping(value = TgolKeyStore.SIGN_UP_URL, method = RequestMethod.GET)
    public String setUpSignUpPage(Model model) {
        return prepateDataAndReturnCreateUserView(
                model,
                null,
                TgolKeyStore.SIGN_UP_VIEW_NAME);

    }

    /**
     * This methods controls the validity of the form and launch an audit with
     * values populated by the user. In case of audit failure, an appropriate
     * message is displayed
     * @param createUserCommand
     * @param result
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = TgolKeyStore.SIGN_UP_URL,method = RequestMethod.POST)
    protected String submitSignUpForm(
            @ModelAttribute(TgolKeyStore.CREATE_USER_COMMAND_KEY) CreateUserCommand createUserCommand,
            BindingResult result,
            Model model)
            throws Exception {
        // To avoid the creation of admin account, this property is set to false
        // in all the cases.
        createUserCommand.setAdmin(false);
        createUserCommand.setActivated(false);
        return submitCreateUserForm(
                createUserCommand, 
                result, 
                model,
                TgolKeyStore.SIGN_UP_CONFIRMATION_VIEW_REDIRECT_NAME,
                TgolKeyStore.SIGN_UP_VIEW_NAME,
                false,
                null);
    }

    /**
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = TgolKeyStore.SIGN_UP_CONFIRMATION_URL, method = RequestMethod.GET)
    public String setUpSignUpConfirmationPage(Model model) {
        model.addAttribute(TgolKeyStore.CREATE_USER_COMMAND_KEY,
                new CreateUserCommand());
        return TgolKeyStore.SIGN_UP_CONFIRMATION_VIEW_NAME;
    }

}