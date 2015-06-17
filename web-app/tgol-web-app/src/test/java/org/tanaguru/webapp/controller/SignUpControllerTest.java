/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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

import java.util.*;
import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import static org.easymock.EasyMock.*;
import org.tanaguru.emailsender.EmailSender;
import org.tanaguru.webapp.command.CreateUserCommand;
import org.tanaguru.webapp.command.factory.CreateUserCommandFactory;
import org.tanaguru.webapp.entity.service.user.RoleDataService;
import org.tanaguru.webapp.entity.service.user.UserDataService;
import org.tanaguru.webapp.entity.user.Role;
import org.tanaguru.webapp.entity.user.User;
import org.tanaguru.webapp.util.TgolKeyStore;
import org.tanaguru.webapp.util.webapp.ExposablePropertyPlaceholderConfigurer;
import org.tanaguru.webapp.validator.CreateUserFormValidator;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

/**
 *
 * @author jkowalczyk
 */
public class SignUpControllerTest extends TestCase {
    
    private SignUpController instance;
    private CreateUserFormValidator createUserFormValidator;
    private UserDataService mockUserDataService;
    private User mockUser;
    private EmailSender mockEmailSender;
    private ExposablePropertyPlaceholderConfigurer mockExposablePropertyPlaceholderConfigurer;
    private CreateUserCommand mockValidCreateUserCommand;
    private CreateUserCommand mockInvalidCreateUserCommand;
    private BindingResult mockInvalidBindingResult;
    private BindingResult mockValidBindingResult;
    Role mockUserRole;
    RoleDataService mockRoleDataService;
    
    public SignUpControllerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        instance = new SignUpController();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        
        if (mockEmailSender != null) {
            verify(mockEmailSender);
        }
        if (mockUserDataService != null) {
            verify(mockUserDataService);
        }
        if (mockUser != null) {
            verify(mockUser);
        }
        if (mockExposablePropertyPlaceholderConfigurer != null) {
            verify(mockExposablePropertyPlaceholderConfigurer);
        }
        if (mockInvalidBindingResult != null) {
            verify(mockInvalidBindingResult);
        }
        if (mockValidBindingResult != null) {
            verify(mockValidBindingResult);
        }
        if (mockUserRole != null) {
            verify(mockUserRole);
        }
        if (mockRoleDataService != null) {
            verify(mockRoleDataService);
        }
    }

    /**
     * Test of setUpSignUpPage method, of class SignUpController.
     */
    public void testSetUpSignUpPage() {
        System.out.println("setUpSignUpPage");
        
        Model model = new ExtendedModelMap();
        String expResult = TgolKeyStore.SIGN_UP_VIEW_NAME;
        String result = instance.setUpSignUpPage(model);
        // the returned view is the sign-up view name
        assertEquals(expResult, result);
        // the model contains a UserSignUpCommand instance to maps the data of
        // the form of the view
        assertTrue(model.asMap().get(TgolKeyStore.CREATE_USER_COMMAND_KEY) instanceof 
                CreateUserCommand);
    }

    /**
     * Test of submitForm method, of class SignUpController.
     * @throws java.lang.Exception
     */
    public void testSubmitForm() throws Exception {
        System.out.println("submitForm");

        setUpMockRoleDataService();
        setUpUserDataService();
        setUpMockExposablePropertyPlaceholderConfigurer();
        setUpValidatorAndBindingResult();
        setUpMockEmailSender();
                
        // Set up instance dependences
        Model model = new ExtendedModelMap();

        // the returned UserSignUpCommand is seen as valid regarding the validator
        // the CONFIRMATION sign-up page is displayed
        String expResult = TgolKeyStore.SIGN_UP_CONFIRMATION_VIEW_REDIRECT_NAME;
        String result = instance.submitSignUpForm(mockValidCreateUserCommand, mockValidBindingResult, model);
        assertEquals(expResult, result);
        
        // the returned UserSignUpCommand is seen as invalid regarding the validator
        // the sign-up form is displayed again
        
        expResult = TgolKeyStore.SIGN_UP_VIEW_NAME;
        result = instance.submitSignUpForm(mockInvalidCreateUserCommand, mockInvalidBindingResult, model);
        assertEquals(expResult, result);
        assertSame(model.asMap().get(TgolKeyStore.CREATE_USER_COMMAND_KEY),
                mockInvalidCreateUserCommand);
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
        assertTrue(model.asMap().get(TgolKeyStore.CREATE_USER_COMMAND_KEY) instanceof 
                CreateUserCommand);
    }

    /**
     * Create an valid instance (the email attribute is initialised) of
     * UserSignUpCommand
     * 
     * @return 
     */
    private CreateUserCommand createValidUserSignUpCommand() {  
        CreateUserCommand createUserCommand = new CreateUserCommand();
        createUserCommand.setEmail("test@test.com");
        createUserCommand.setSiteUrl("http://mysite.org");
        createUserCommand.setPassword("password");
        createUserCommand.setConfirmPassword("password");
        return createUserCommand;
    }
    
    /**
     * Create an invalid instance (the email attribute is not initialised) of
     * UserSignUpCommand
     * 
     * @return 
     */
    private CreateUserCommand createInvalidUserSignUpCommand() {
        return new CreateUserCommand();
    }
 
    
   private void setUpUserDataService() {
       mockUser = createMock(User.class);
       mockUser.setEmail1("test@test.com");
       expectLastCall();
       expect(mockUser.getEmail1()).andReturn("test@test.com").once();
       mockUser.setWebUrl1("http://mysite.org");
       expectLastCall();
       expect(mockUser.getWebUrl1()).andReturn("http://mysite.org").once();
       mockUser.setFirstName(null);
       expectLastCall();
       expect(mockUser.getFirstName()).andReturn(null).once();
       mockUser.setName(null);
       expectLastCall();
       expect(mockUser.getName()).andReturn(null).once();
       mockUser.setPhoneNumber(null);
       expectLastCall();
       expect(mockUser.getPhoneNumber()).andReturn(null).once();
       mockUser.setPassword("5f4dcc3b5aa765d61d8327deb882cf99");
       expectLastCall();
       mockUser.setAccountActivation(false);
       expectLastCall();
       mockUser.setRole(mockUserRole);
       expectLastCall();
       mockUserDataService = createMock(UserDataService.class);
       expect(mockUserDataService.create()).andReturn(mockUser).anyTimes();
       expect(mockUserDataService.getUserFromEmail("test@test.com")).andReturn(mockUser).anyTimes();
       expect(mockUserDataService.saveOrUpdate(mockUser)).andReturn(mockUser).anyTimes();
       
       replay(mockUser);
       replay(mockUserDataService);
       
       instance.setUserDataService(mockUserDataService);
   }

   private void setUpValidatorAndBindingResult() {
       mockValidCreateUserCommand = createValidUserSignUpCommand();
       mockInvalidCreateUserCommand = createInvalidUserSignUpCommand();
       createUserFormValidator = new CreateUserFormValidator();
       createUserFormValidator.setUserDataService(mockUserDataService);
       mockInvalidBindingResult = createMock(BindingResult.class);
       mockValidBindingResult = createMock(BindingResult.class);
       createUserFormValidator.validate(mockValidCreateUserCommand, mockValidBindingResult);
       createUserFormValidator.validate(mockInvalidCreateUserCommand, mockInvalidBindingResult);
       
       expectLastCall();
       expect(mockValidBindingResult.hasErrors()).andReturn(false).once();
       expect(mockInvalidBindingResult.hasErrors()).andReturn(true).once();
       
       replay(mockValidBindingResult);
       replay(mockInvalidBindingResult);
       
       instance.setCreateUserFormValidator(createUserFormValidator);
   }
 
   private void setUpMockExposablePropertyPlaceholderConfigurer() {
       mockExposablePropertyPlaceholderConfigurer = createMock(ExposablePropertyPlaceholderConfigurer.class);
       Map<String, String> props = new HashMap();
       props.put(TgolKeyStore.EMAIL_FROM_KEY, "from@user.com");
       props.put(TgolKeyStore.EMAIL_TO_KEY, "to@user.com");
       props.put(TgolKeyStore.EMAIL_SUBJECT_KEY, "subject");
       props.put(TgolKeyStore.EMAIL_CONTENT_KEY, "content");
       
       expect(mockExposablePropertyPlaceholderConfigurer.getResolvedProps()).andReturn(props).times(4);
       replay(mockExposablePropertyPlaceholderConfigurer);
       
       instance.setExposablePropertyPlaceholderConfigurer(mockExposablePropertyPlaceholderConfigurer);
   }
   
   private void setUpMockEmailSender() {
       mockEmailSender = createMock(EmailSender.class);
       Set<String> toUserList = new HashSet();
       toUserList.add("to@user.com");
       mockEmailSender.sendEmail(
               "from@user.com", 
               toUserList, 
               Collections.EMPTY_SET, 
               StringUtils.EMPTY, 
               "subject", 
               "content");
       expectLastCall();
       replay(mockEmailSender);
       
       instance.setEmailSender(mockEmailSender);
   }
 
   private void setUpMockRoleDataService() {
        mockRoleDataService = createMock(RoleDataService.class);
        mockUserRole = createMock(Role.class);
         
        expect(mockRoleDataService.read(Long.valueOf(2))).andReturn(mockUserRole).anyTimes();
        expect(mockRoleDataService.read(Long.valueOf(3))).andReturn(null).anyTimes();
        expect(mockUserRole.getId()).andReturn(Long.valueOf(2)).anyTimes();

        replay(mockUserRole);        
        replay(mockRoleDataService);
        
        CreateUserCommandFactory.getInstance().setRoleDataService(mockRoleDataService);
    }

}