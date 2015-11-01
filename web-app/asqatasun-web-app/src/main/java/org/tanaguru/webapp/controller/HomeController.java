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

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.tanaguru.webapp.action.voter.ActionHandler;
import org.tanaguru.webapp.command.ContractSortCommand;
import org.tanaguru.webapp.command.helper.ContractSortCommandHelper;
import org.tanaguru.webapp.entity.user.User;
import org.tanaguru.webapp.exception.ForbiddenUserException;
import org.tanaguru.webapp.form.builder.FormFieldBuilder;
import org.tanaguru.webapp.util.TgolKeyStore;
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
public class HomeController extends AbstractController {

    private ActionHandler actionHandler;
    public ActionHandler getActionHandler() {
        return actionHandler;
    }

    public void setActionHandler(ActionHandler contractActionHandler) {
        this.actionHandler = contractActionHandler;
    }

    List<FormFieldBuilder> displayOptionFieldsBuilderList;
    public final void setDisplayOptionFieldsBuilderList(final List<FormFieldBuilder> formFieldBuilderList) {
        this.displayOptionFieldsBuilderList = formFieldBuilderList;
    }
    
    public HomeController() {
        super();
    }

    @RequestMapping(value=TgolKeyStore.HOME_URL, method=RequestMethod.GET)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String displayHomePage(Model model) {
        User user = getCurrentUser();
        model.addAttribute(
                TgolKeyStore.CONTRACT_LIST_KEY, 
                ContractSortCommandHelper.prepareContractInfo(
                    user, 
                    null,
                    displayOptionFieldsBuilderList,
                    model));
        return TgolKeyStore.HOME_VIEW_NAME;
    }

    @RequestMapping(value=TgolKeyStore.HOME_URL, method = RequestMethod.POST)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    protected String submitForm(
            @ModelAttribute(TgolKeyStore.CONTRACT_SORT_COMMAND_KEY) ContractSortCommand contractDisplayCommand,
            BindingResult result,
            Model model,
            HttpServletRequest request) {
        User user = getCurrentUser();
        if (!user.getId().equals(contractDisplayCommand.getUserId())) {
            throw new ForbiddenUserException();
        }
        // The page is displayed with sort option. Form needs to be set up
        model.addAttribute(
                TgolKeyStore.CONTRACT_LIST_KEY, 
                ContractSortCommandHelper.prepareContractInfo(
                    user, 
                    contractDisplayCommand,
                    displayOptionFieldsBuilderList,
                    model));
        return TgolKeyStore.HOME_VIEW_NAME;
    }
    
}