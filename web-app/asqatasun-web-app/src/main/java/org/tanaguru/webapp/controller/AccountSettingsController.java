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

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.tanaguru.entity.reference.Reference;
import org.tanaguru.entity.reference.Test;
import org.tanaguru.entity.service.reference.ReferenceDataService;
import org.tanaguru.entity.service.reference.TestDataService;
import org.tanaguru.webapp.command.ChangeTestWeightCommand;
import org.tanaguru.webapp.command.CreateUserCommand;
import org.tanaguru.webapp.command.factory.ChangeTestWeightCommandFactory;
import org.tanaguru.webapp.entity.user.User;
import org.tanaguru.webapp.exception.ForbiddenPageException;
import org.tanaguru.webapp.presentation.menu.SecondaryLevelMenuDisplayer;
import org.tanaguru.webapp.util.TgolKeyStore;
import org.tanaguru.webapp.validator.ChangeTestWeightFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

/** 
 *
 * @author jkowalczyk
 */
@Controller
public class AccountSettingsController extends AbstractUserAndContractsController {

    List<String> forbiddenUserList = new ArrayList();
    public void setForbiddenUserList(List<String> forbiddenUserList) {
        this.forbiddenUserList = forbiddenUserList;
    }
    
    private TestDataService testDataService;
    @Autowired
    public void setTestDataService(TestDataService testDataService) {
        this.testDataService = testDataService;
    }
    
    private final Map<String, Reference> refMap = new HashMap();
    @Autowired
    public void setReferenceDataService(ReferenceDataService referenceDataService) {
        for (Reference ref : referenceDataService.findAll()) {
            refMap.put(ref.getCode(), ref);
        }
    }

    private LocaleResolver localeResolver;
    public LocaleResolver getLocaleResolver() {
        return localeResolver;
    }
    
    @Autowired
    public final void setLocaleResolver(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }
    
    private ChangeTestWeightFormValidator changeTestWeightFormValidator;

    public final void setChangeTestWeightFormValidator(ChangeTestWeightFormValidator changeTestWeightFormValidator) {
        this.changeTestWeightFormValidator = changeTestWeightFormValidator;
    }
    
    private SecondaryLevelMenuDisplayer secondaryLevelMenuDisplayer;
    @Autowired
    public void setSecondaryLevelMenuDisplayer(SecondaryLevelMenuDisplayer secondaryLevelMenuDisplayer) {
        this.secondaryLevelMenuDisplayer = secondaryLevelMenuDisplayer;
    }
    
    /**
     * Constructor
     */
    public AccountSettingsController() {
        super();
    }
    
    /**
     * This method displays the form for an authenticated user
     * 
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
        secondaryLevelMenuDisplayer.setModifiableReferentialsForUserToModel(user, model);
        return prepateDataAndReturnCreateUserView(
                model,
                user,
                TgolKeyStore.ACCOUNT_SETTINGS_VIEW_NAME);
    }

    /**
     * This methods controls the validity of the edit user form.
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
        
        secondaryLevelMenuDisplayer.setModifiableReferentialsForUserToModel(user, model);
        
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

    /**
     * This method displays the Change Test Weight page for the authentified user.
     * This page is displayed if and only if the current user owns at least 
     * one contract on the wished referential. 
     * 
     * @param refCode
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = TgolKeyStore.TEST_WEIGHT_URL, method = RequestMethod.GET)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String displayChangeTestWeight(
            @RequestParam(TgolKeyStore.REFERENTIAL_CD_KEY) String refCode,
            HttpServletRequest request,
            Model model) {

        Reference referential = refMap.get(refCode);
        List<Test> testList = addTestListAndModifiableRefToModel(referential, model);

        model.addAttribute(TgolKeyStore.CHANGE_TEST_WEIGHT_COMMAND_KEY, 
                ChangeTestWeightCommandFactory.getInstance().getChangeTestWeightCommand(
                    getCurrentUser(), 
                    getLocaleResolver().resolveLocale(request),
                    testList, 
                    refCode));
        return TgolKeyStore.TEST_WEIGHT_VIEW_NAME;
    }
    
    /**
     * 
     * @param refCode
     * @param changeTestWeightCommand
     * @param result
     * @param model
     * @param request
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = TgolKeyStore.TEST_WEIGHT_URL, method = RequestMethod.POST)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String submitChangeTestWeight (
            @RequestParam(TgolKeyStore.REFERENTIAL_CD_KEY) String refCode,
            @ModelAttribute(TgolKeyStore.CHANGE_TEST_WEIGHT_COMMAND_KEY) ChangeTestWeightCommand changeTestWeightCommand,
            BindingResult result,
            Model model,
            HttpServletRequest request)
            throws Exception {

        Reference referential = refMap.get(refCode);
        if (referential == null || 
                !secondaryLevelMenuDisplayer.isRequestedReferentialModifiable(refCode)) {
            throw new ForbiddenPageException();
        }

        // We check whether the form is valid
        changeTestWeightFormValidator.validate(changeTestWeightCommand, result);
        // If the form has some errors, we display it again with errors' details
        addTestListAndModifiableRefToModel(referential, model);

        model.addAttribute(TgolKeyStore.CHANGE_TEST_WEIGHT_COMMAND_KEY, changeTestWeightCommand);

        if (!result.hasErrors()) {
            ChangeTestWeightCommandFactory.getInstance().updateUserTestWeight(
                getCurrentUser(),
                changeTestWeightCommand);
            model.addAttribute(TgolKeyStore.TEST_WEIGHT_SUCCESSFULLY_UPDATED_KEY, true);
        }

        return TgolKeyStore.TEST_WEIGHT_VIEW_NAME;
    }
    
    /**
     * 
     * @param ref
     * @param model
     * @return 
     */
    private List<Test> addTestListAndModifiableRefToModel(Reference ref, Model model) {
        List<Test> testList = testDataService.findAll(ref);
        sortTestListByCode(testList);
        model.addAttribute(TgolKeyStore.TEST_LIST_KEY, testList);
        
        secondaryLevelMenuDisplayer.setModifiableReferentialsForUserToModel(
                        getCurrentUser(), 
                        model);

        return testList;
    }
    
    /**
     * This method sorts the test list elements regarding their code
     * 
     * @param processResultList
     */
    private void sortTestListByCode(List<Test> testList) {
        Collections.sort(testList, new Comparator<Test>() {
            @Override
            public int compare(Test t1, Test t2) {
                return String.CASE_INSENSITIVE_ORDER.compare(
                        t1.getCode(),
                        t2.getCode());
            }
        });
    }
    
}