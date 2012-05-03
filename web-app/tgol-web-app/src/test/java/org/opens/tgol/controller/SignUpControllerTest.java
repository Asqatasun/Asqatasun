/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2012  Open-S Company
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

import javax.servlet.http.HttpServletRequest;
import junit.framework.TestCase;
import org.opens.tgol.command.UserSignUpCommand;
import org.opens.tgol.emailsender.MockEmailSender;
import org.opens.tgol.entity.service.user.MockUserDataService;
import org.opens.tgol.mock.MockBindingResult;
import org.opens.tgol.mock.MockFormValidator;
import org.opens.tgol.util.TgolKeyStore;
import org.opens.tgol.util.webapp.MockExposablePropertyPlaceholderConfigurer;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 *
 * @author jkowalczyk
 */
public class SignUpControllerTest extends TestCase {
    
    private SignUpController instance = new SignUpController();
    
    public SignUpControllerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        instance.setSignUpFormValidator(new MockFormValidator());
        instance.setUserDataService(new MockUserDataService());
        instance.setExposablePropertyPlaceholderConfigurer(
                new MockExposablePropertyPlaceholderConfigurer());
        instance.setEmailSender(new MockEmailSender());
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of setUpSignUpPage method, of class SignUpController.
     */
    public void testSetUpSignUpPage() {
        System.out.println("setUpSignUpPage");
        Model model = new ExtendedModelMap();
        SignUpController instance = new SignUpController();
        String expResult = TgolKeyStore.SIGN_UP_VIEW_NAME;
        String result = instance.setUpSignUpPage(model);
        // the returned view is the sign-up view name
        assertEquals(expResult, result);
        // the model contains a UserSignUpCommand instance to maps the data of
        // the form of the view
        assertTrue(model.asMap().get(TgolKeyStore.USER_SIGN_UP_COMMAND_KEY) instanceof 
                UserSignUpCommand);
    }

    /**
     * Test of submitForm method, of class SignUpController.
     */
    public void testSubmitForm() throws Exception {
        System.out.println("submitForm");
        
        // Set up instance dependences
        BindingResult bindingResult = new MockBindingResult() ;
        Model model = new ExtendedModelMap();
        HttpServletRequest request = null;
        
        // the returned UserSignUpCommand is seen as valid regarding the validator
        // the CONFIRMATION sign-up page is displayed
        UserSignUpCommand userSignUpCommand = createValidUserSignUpCommand();
        String expResult = TgolKeyStore.SIGN_UP_CONFIRMATION_VIEW_REDIRECT_NAME;
        String result = instance.submitForm(userSignUpCommand, bindingResult, model, request);
        assertEquals(expResult, result);
        
        // the returned UserSignUpCommand is seen as invalid regarding the validator
        // the sign-up form is displayed again
        bindingResult = new MockBindingResult() ;
        userSignUpCommand = createInvalidUserSignUpCommand();
        expResult = TgolKeyStore.SIGN_UP_VIEW_NAME;
        result = instance.submitForm(userSignUpCommand, bindingResult, model, request);
        assertEquals(expResult, result);
        assertSame(model.asMap().get(TgolKeyStore.USER_SIGN_UP_COMMAND_KEY),
                userSignUpCommand);
    }

    /**
     * Test of setUpSignUpConfirmationPage method, of class SignUpController.
     */
    public void testSetUpSignUpConfirmationPage() {
        System.out.println("setUpSignUpConfirmationPage");
        Model model = new ExtendedModelMap();
        String expResult = TgolKeyStore.SIGN_UP_CONFIRMATION_VIEW_NAME;
        String result = instance.setUpSignUpConfirmationPage(model);
        // the returned view is the sign-up confirmation view name
        assertEquals(expResult, result);
        // the model contains a UserSignUpCommand instance to maps the data of
        // the form of the view
        assertTrue(model.asMap().get(TgolKeyStore.USER_SIGN_UP_COMMAND_KEY) instanceof 
                UserSignUpCommand);
    }

    /**
     * Create an valid instance (the email attribute is initialised) of
     * UserSignUpCommand
     * 
     * @return 
     */
    private UserSignUpCommand createValidUserSignUpCommand() {
        UserSignUpCommand userSignUpCommand = new UserSignUpCommand();
        userSignUpCommand.setEmail("test@test.com");
        userSignUpCommand.setPassword("password");
        userSignUpCommand.setConfirmPassword("password");
        return userSignUpCommand;
    }
    
    /**
     * Create an invalid instance (the email attribute is not initialised) of
     * UserSignUpCommand
     * 
     * @return 
     */
    private UserSignUpCommand createInvalidUserSignUpCommand() {
        return new UserSignUpCommand();
    }
    
}