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

import java.util.ArrayList;
import java.util.List;
import org.opens.tgol.command.CreateUserCommand;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.exception.ForbiddenPageException;
import org.opens.tgol.util.TgolKeyStore;
import org.springframework.security.access.annotation.Secured;
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
public class AccountSettingsController extends AbstractUserAndContractsController {

    List<String> forbiddenUserList = new ArrayList<String>();
    public void setForbiddenUserList(List<String> forbiddenUserList) {
        this.forbiddenUserList = forbiddenUserList;
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
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String displayAccountSettingsPage(Model model) {
        User user = getCurrentUser();
        if (this.forbiddenUserList.contains(user.getEmail1())) {
            throw new ForbiddenPageException();
        }
        return prepateDataAndReturnCreateUserView(
                model,
                user,
                TgolKeyStore.ACCOUNT_SETTINGS_VIEW_NAME);
    }

    /**
     * This methods controls the validity of the edit user form in case.
     * If the user tries to modidy its email, or try to desactivate its account
     * or try to set him as admin where he's not admin, return attack message.
     * 
     * @param createUserCommand
     * @param result
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = TgolKeyStore.ACCOUNT_SETTINGS_URL,method = RequestMethod.POST)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    protected String submitAccountSettingForm(
            @ModelAttribute(TgolKeyStore.CREATE_USER_COMMAND_KEY) CreateUserCommand createUserCommand,
            BindingResult result,
            Model model)
            throws Exception {
        User user = getCurrentUser();
        if (this.forbiddenUserList.contains(user.getEmail1())) {
            throw new ForbiddenPageException();
        }
        if (!createUserCommand.getEmail().equals(user.getEmail1()) ||
                (createUserCommand.getAdmin() && !isUserAdmin(user))) {
            model.addAttribute(TgolKeyStore.CREATE_USER_ATTACK_COMMAND_KEY, true);
            return prepateDataAndReturnCreateUserView(
                model,
                user,
                TgolKeyStore.ACCOUNT_SETTINGS_VIEW_NAME);
        }
        
        return submitUpdateUserForm(
                createUserCommand, 
                result, 
                null,
                model, 
                user,
                TgolKeyStore.ACCOUNT_SETTINGS_VIEW_NAME, 
                TgolKeyStore.ACCOUNT_SETTINGS_VIEW_NAME,
                false,
                false,
                TgolKeyStore.UPDATED_USER_NAME_KEY); 
    }

}