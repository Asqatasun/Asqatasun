/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015 Tanaguru.org
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
 *  Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.webapp.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.tanaguru.webapp.command.CreateUserCommand;
import org.tanaguru.webapp.command.factory.CreateUserCommandFactory;
import org.tanaguru.webapp.entity.contract.Contract;
import org.tanaguru.webapp.entity.service.user.RoleDataService;
import org.tanaguru.webapp.entity.service.user.UserDataService;
import org.tanaguru.webapp.entity.user.Role;
import org.tanaguru.webapp.entity.user.User;
import org.tanaguru.webapp.exception.ForbiddenUserException;
import org.tanaguru.webapp.security.userdetails.TgolUserDetails;
import org.tanaguru.webapp.util.TgolKeyStore;
import org.tanaguru.webapp.validator.CreateUserFormValidator;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

/**
 *
 * @author jkowalczyk
 */
public class UserManagementControllerTest extends TestCase {
    
    UserManagementController instance;
    UserDataService mockUserDataService;
    RoleDataService mockRoleDataService;
    User mockAdminUser;
    User mockUser;
    User mockNewUser;
    Role mockAdminRole;
    Role mockUserRole;
    Authentication mockAuthentication;

    public UserManagementControllerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        verify(mockAuthentication);
        verify(mockUserDataService);
        verify(mockAdminUser);
        verify(mockAdminRole);
        verify(mockUserRole);
        verify(mockRoleDataService);
        verify(mockUser);
        verify(mockNewUser);
        verify(mockAdminUser);
    }
    
    /**
     * Test of displayAdminPage method, of class UserManagementController.
     */
    public void testDisplayAdminPage() {
        System.out.println("displayAdminPage");
        
        instance = new UserManagementController();
        setUpMockRoleDataService();
        setUpMockUserDataService(false, false, false, false, false);
        setUpMockAuthenticationContext();
        instance.setUserDataService(mockUserDataService);
        
        HttpServletResponse response = new MockHttpServletResponse();
        MockHttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        String result = instance.displayAdminPage(request, response, model);
        assertEquals(TgolKeyStore.ADMIN_VIEW_NAME, result);
    }
    
    /**
     * Test of displayAdminPage method, of class UserManagementController.
     */
    public void testDisplayAdminPageWithDeletedUserMessage() {
        System.out.println("DisplayAdminPageWithDeletedUserMessage");
        
        instance = new UserManagementController();
        setUpMockRoleDataService();
        setUpMockUserDataService(false, false, false, false, false);
        setUpMockAuthenticationContext();
        instance.setUserDataService(mockUserDataService);
        
        HttpServletResponse response = new MockHttpServletResponse();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.getSession().setAttribute(TgolKeyStore.DELETED_USER_NAME_KEY, "DeletedUserName");
        Model model = new ExtendedModelMap();
        
        String result = instance.displayAdminPage(request, response, model);
        
        assertEquals(TgolKeyStore.ADMIN_VIEW_NAME, result);
        assertTrue(model.asMap().containsKey(TgolKeyStore.DELETED_USER_NAME_KEY));
        assertEquals(model.asMap().get(TgolKeyStore.DELETED_USER_NAME_KEY),"DeletedUserName");
    }

    /**
     * Test of editUserAdminPage method, of class UserManagementController.
     */
    public void testEditUserAdminPage() {
        System.out.println("editUserAdminPage");
        
        instance = new UserManagementController();
        setUpMockRoleDataService();
        setUpMockUserDataService(false, false, false, false, false);
        setUpMockAuthenticationContext();
        instance.setUserDataService(mockUserDataService);
        
        String userId = "";
        HttpServletResponse response = new MockHttpServletResponse();
        MockHttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        
        try {
            instance.displayEditUserAdminPage(userId, request, response, model);
            assertTrue(false);
        } catch (ForbiddenUserException nue) {
            assertTrue(true);
        }
        
        userId = "4";
        String result = instance.displayEditUserAdminPage(userId, request, response, model);
        assertEquals(TgolKeyStore.EDIT_USER_VIEW_NAME, result);
        CreateUserCommand cuc = ((CreateUserCommand)model.asMap().get(TgolKeyStore.CREATE_USER_COMMAND_KEY));
        assertNotNull(cuc);
        assertTrue(cuc.getActivated());
        assertTrue(cuc.getAdmin());
        assertEquals("admin@test.com",cuc.getEmail());
        assertEquals("http://www.admin.com",cuc.getSiteUrl());
        assertEquals("0123456789",cuc.getPhoneNumber());
        assertEquals("nameAdmin",cuc.getLastName());
        assertEquals("firstNameAdmin",cuc.getFirstName());
        assertEquals(Long.valueOf(4),request.getSession().getAttribute(TgolKeyStore.USER_ID_KEY));
        
        userId = "5";
        result = instance.displayEditUserAdminPage(userId, request, response, model);
        assertEquals(TgolKeyStore.EDIT_USER_VIEW_NAME, result);
        cuc = ((CreateUserCommand)model.asMap().get(TgolKeyStore.CREATE_USER_COMMAND_KEY));
        assertNotNull(cuc);
        assertFalse(cuc.getActivated());
        assertFalse(cuc.getAdmin());
        assertEquals("user@test.com",cuc.getEmail());
        assertEquals("http://www.user.com",cuc.getSiteUrl());
        assertEquals("9876543210",cuc.getPhoneNumber());
        assertEquals("nameUser",cuc.getLastName());
        assertEquals("firstNameUser",cuc.getFirstName());
        assertEquals(Long.valueOf(5),request.getSession().getAttribute(TgolKeyStore.USER_ID_KEY));
    }

    /**
     * Test of submitEditUserForm method, of class UserManagementController.
     */
    public void testSubmitEditUserForm() throws Exception {
        System.out.println("submitEditUserForm");
        
        instance = new UserManagementController();
        setUpMockRoleDataService();
        setUpMockUserDataService(false, false, false, true, false);
        setUpMockAuthenticationContext();
        instance.setUserDataService(mockUserDataService);
        CreateUserFormValidator createUserFormValidator = new CreateUserFormValidator();
        createUserFormValidator.setUserDataService(mockUserDataService);
        instance.setCreateUserFormValidator(createUserFormValidator);
        
        // Finally the form is conform and the admin page is returned
        CreateUserCommand createUserCommand = CreateUserCommandFactory.getInstance().getNewCreateUserCommand();
        createUserCommand.setEmail("user@test.com");
        createUserCommand.setLastName("newName");
        createUserCommand.setFirstName("newFirstName");
        createUserCommand.setPhoneNumber("0102030405");
        createUserCommand.setActivated(false);
        createUserCommand.setAdmin(true);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.getSession().setAttribute(TgolKeyStore.USER_ID_KEY, Long.valueOf(5));
        BindingResult bindingResult = new BeanPropertyBindingResult(createUserCommand,"createUserCommand");
        Model model = new ExtendedModelMap();
        String result = instance.submitEditUserForm(
                createUserCommand, 
                bindingResult, 
                request,
                model);
        
        assertEquals(TgolKeyStore.ADMIN_VIEW_NAME, result);
        assertFalse(bindingResult.hasErrors());
        assertTrue(bindingResult.getFieldErrors().isEmpty());
        assertEquals(2, model.asMap().size());
        assertEquals("user@test.com",model.asMap().get(TgolKeyStore.UPDATED_USER_NAME_KEY));
        assertTrue(((List<User>)model.asMap().get(TgolKeyStore.USER_LIST_KEY)).isEmpty());
    }
    
    /**
     * Test of submitEditUserForm method, of class UserManagementController.
     */
    public void testSubmitEditCurrentUserForm() throws Exception {
        System.out.println("testSubmitEditCurrentUserForm");
        
        instance = new UserManagementController();
        setUpMockRoleDataService();
        setUpMockUserDataService(false, false, false, false, true);
        setUpMockAuthenticationContext();
        instance.setUserDataService(mockUserDataService);
        CreateUserFormValidator createUserFormValidator = new CreateUserFormValidator();
        createUserFormValidator.setUserDataService(mockUserDataService);
        instance.setCreateUserFormValidator(createUserFormValidator);
        
        // Finally the form is conform and the admin page is returned
        CreateUserCommand createUserCommand = CreateUserCommandFactory.getInstance().getNewCreateUserCommand();
        createUserCommand.setEmail("admin@test.com");
        createUserCommand.setLastName("newName");
        createUserCommand.setFirstName("newFirstName");
        createUserCommand.setPhoneNumber("0102030405");
        createUserCommand.setActivated(false);
        createUserCommand.setAdmin(false);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.getSession().setAttribute(TgolKeyStore.USER_ID_KEY, Long.valueOf(4));
        BindingResult bindingResult = new BeanPropertyBindingResult(createUserCommand,"createUserCommand");
        Model model = new ExtendedModelMap();
        String result = instance.submitEditUserForm(
                createUserCommand, 
                bindingResult, 
                request,
                model);
        
        assertEquals(TgolKeyStore.ADMIN_VIEW_NAME, result);
        assertFalse(bindingResult.hasErrors());
        assertTrue(bindingResult.getFieldErrors().isEmpty());
        assertEquals(2, model.asMap().size());
        assertEquals("admin@test.com",model.asMap().get(TgolKeyStore.UPDATED_USER_NAME_KEY));
        assertTrue(((List<User>)model.asMap().get(TgolKeyStore.USER_LIST_KEY)).isEmpty());
    }
    
    /**
     * Test of submitEditUserForm method, of class UserManagementController.
     */
    public void testSubmitEditUserChangeToExistingUserForm() throws Exception {
        System.out.println("SubmitEditUserChangeToExistingUserForm");
        
        instance = new UserManagementController();
        setUpMockRoleDataService();
        setUpMockUserDataService(false, false, false, false, false);
        setUpMockAuthenticationContext();
        instance.setUserDataService(mockUserDataService);
        CreateUserFormValidator createUserFormValidator = new CreateUserFormValidator();
        createUserFormValidator.setUserDataService(mockUserDataService);
        instance.setCreateUserFormValidator(createUserFormValidator);
        
        // Finally the form is conform and the admin page is returned
        CreateUserCommand createUserCommand = CreateUserCommandFactory.getInstance().getNewCreateUserCommand();
        createUserCommand.setSiteUrl("http://www.newSite.com/");
        createUserCommand.setEmail("admin@test.com");
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.getSession().setAttribute(TgolKeyStore.USER_ID_KEY, Long.valueOf(5));
        BindingResult bindingResult = new BeanPropertyBindingResult(createUserCommand,"createUserCommand");
        Model model = new ExtendedModelMap();
        String result = instance.submitEditUserForm(
                createUserCommand, 
                bindingResult, 
                request,
                model);
        
        assertEquals(TgolKeyStore.EDIT_USER_VIEW_NAME, result);
        assertTrue(bindingResult.hasErrors());
        assertEquals(1,bindingResult.getFieldErrors().size());
        assertNotNull(bindingResult.getFieldErrors("email"));
        assertEquals(1,bindingResult.getFieldErrors("email").size());
        assertEquals("sign-up.existingAccountWithEmail",bindingResult.getFieldErrors("email").get(0).getCode());
    }

    /**
     * Test of addUserAdminPage method, of class UserManagementController.
     */
    public void testAddUserAdminPage() {
        System.out.println("addUserAdminPage");
        
        instance = new UserManagementController();
        setUpMockRoleDataService();
        setUpMockUserDataService(false, false, false, false, false);
        setUpMockAuthenticationContext();
        instance.setUserDataService(mockUserDataService);
        
        HttpServletResponse response = new MockHttpServletResponse();
        MockHttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        
        String result = instance.displayAddUserAdminPage(request, response, model);
        
        assertEquals(TgolKeyStore.ADD_USER_VIEW_NAME, result);
        assertEquals(1, model.asMap().size());

        CreateUserCommand cuc = ((CreateUserCommand)model.asMap().get(TgolKeyStore.CREATE_USER_COMMAND_KEY));
        assertNotNull(cuc);
        assertFalse(cuc.getActivated());
        assertFalse(cuc.getAdmin());
        assertNull(cuc.getEmail());
        assertEquals("http://",cuc.getSiteUrl());
        assertNull(cuc.getPhoneNumber());
        assertNull(cuc.getLastName());
        assertNull(cuc.getFirstName());
        assertNull(cuc.getPassword());
        assertNull(cuc.getConfirmPassword());
    }

    /**
     * Test of submitAddUserForm method, of class UserManagementController.
     */
    public void testSubmitAddUserForm() throws Exception {
        System.out.println("submitAddUserForm");
        
        instance = new UserManagementController();
        setUpMockRoleDataService();
        setUpMockUserDataService(false, true, false, false, false);
        setUpMockAuthenticationContext();
        instance.setUserDataService(mockUserDataService);
        CreateUserFormValidator createUserFormValidator = new CreateUserFormValidator();
        createUserFormValidator.setUserDataService(mockUserDataService);
        instance.setCreateUserFormValidator(createUserFormValidator);
        
        // Finally the form is conform and the admin page is returned
        CreateUserCommand createUserCommand = CreateUserCommandFactory.getInstance().getNewCreateUserCommand();
        createUserCommand.setSiteUrl("http://www.newSite.com/");
        createUserCommand.setEmail("newUser@test.com");
        createUserCommand.setPassword("P4sSw0rD");
        createUserCommand.setConfirmPassword("P4sSw0rD");
        BindingResult bindingResult = new BeanPropertyBindingResult(createUserCommand,"createUserCommand");
        Model model = new ExtendedModelMap();
        String result = instance.submitAddUserForm(createUserCommand, bindingResult, model);
        
        assertEquals(TgolKeyStore.ADMIN_VIEW_NAME, result);
        assertFalse(bindingResult.hasErrors());
        assertTrue(bindingResult.getFieldErrors().isEmpty());
        assertEquals(2, model.asMap().size());
        assertEquals("newUser@test.com",model.asMap().get(TgolKeyStore.ADDED_USER_NAME_KEY));
        assertTrue(((List<User>)model.asMap().get(TgolKeyStore.USER_LIST_KEY)).isEmpty());
    }
    
    /**
     * Test of submitAddUserForm method, of class UserManagementController.
     */
    public void testSubmitAddAdminUserForm() throws Exception {
        System.out.println("SubmitAddAdminUserForm");
        
        instance = new UserManagementController();
        setUpMockRoleDataService();
        setUpMockUserDataService(false, true, true, false, false);
        setUpMockAuthenticationContext();
        instance.setUserDataService(mockUserDataService);
        CreateUserFormValidator createUserFormValidator = new CreateUserFormValidator();
        createUserFormValidator.setUserDataService(mockUserDataService);
        instance.setCreateUserFormValidator(createUserFormValidator);
        
        // Finally the form is conform and the admin page is returned
        CreateUserCommand createUserCommand = CreateUserCommandFactory.getInstance().getNewCreateUserCommand();
        createUserCommand.setSiteUrl("http://www.newSite.com/");
        createUserCommand.setEmail("newUser@test.com");
        createUserCommand.setPassword("P4sSw0rD");
        createUserCommand.setConfirmPassword("P4sSw0rD");
        createUserCommand.setAdmin(true);
        createUserCommand.setActivated(true);
        BindingResult bindingResult = new BeanPropertyBindingResult(createUserCommand,"createUserCommand");
        Model model = new ExtendedModelMap();
        String result = instance.submitAddUserForm(createUserCommand, bindingResult, model);
        
        assertEquals(TgolKeyStore.ADMIN_VIEW_NAME, result);
        assertFalse(bindingResult.hasErrors());
        assertTrue(bindingResult.getFieldErrors().isEmpty());
        assertEquals(2, model.asMap().size());
        assertEquals("newUser@test.com",model.asMap().get(TgolKeyStore.ADDED_USER_NAME_KEY));
        assertTrue(((List<User>)model.asMap().get(TgolKeyStore.USER_LIST_KEY)).isEmpty());
    }
    
    /**
     * Test of submitAddUserForm method, of class UserManagementController.
     */
    public void testSubmitAddUserFormWithErrors() throws Exception {
        System.out.println("SubmitAddUserFormWithErrors");
        
        instance = new UserManagementController();
        setUpMockRoleDataService();
        setUpMockUserDataService(false, false, false, false, false);
        setUpMockAuthenticationContext();
        instance.setUserDataService(mockUserDataService);
        CreateUserFormValidator createUserFormValidator = new CreateUserFormValidator();
        createUserFormValidator.setUserDataService(mockUserDataService);
        instance.setCreateUserFormValidator(createUserFormValidator);
        
        // First the form is sent back empty
        CreateUserCommand createUserCommand = CreateUserCommandFactory.getInstance().getNewCreateUserCommand();
        BindingResult bindingResult = new BeanPropertyBindingResult(createUserCommand,"createUserCommand");
        Model model = new ExtendedModelMap();
        String result = instance.submitAddUserForm(createUserCommand, bindingResult, model);
        
        assertEquals(TgolKeyStore.ADD_USER_VIEW_NAME, result);
        assertTrue(bindingResult.hasErrors());
        assertEquals(3,bindingResult.getFieldErrors().size());
        assertNotNull(bindingResult.getFieldErrors("email"));
        assertEquals(1,bindingResult.getFieldErrors("email").size());
        assertEquals("sign-up.missingEmail",bindingResult.getFieldErrors("email").get(0).getCode());
        assertNotNull(bindingResult.getFieldErrors("password"));
        assertEquals(1,bindingResult.getFieldErrors("password").size());
        assertEquals("sign-up.missingPassword",bindingResult.getFieldErrors("password").get(0).getCode());
        assertNotNull(bindingResult.getFieldErrors("generalErrorMsg"));
        assertEquals(1,bindingResult.getFieldErrors("generalErrorMsg").size());
        assertEquals("sign-up.mandatoryField",bindingResult.getFieldErrors("generalErrorMsg").get(0).getCode());
        
        // First the form is sent back with passwords that don't match
        createUserCommand = CreateUserCommandFactory.getInstance().getNewCreateUserCommand();
        createUserCommand.setEmail("user@test.com");
        createUserCommand.setPassword("password");
        createUserCommand.setConfirmPassword("confirmation");
        bindingResult = new BeanPropertyBindingResult(createUserCommand,"createUserCommand");
        model = new ExtendedModelMap();
        result = instance.submitAddUserForm(createUserCommand, bindingResult, model);
        
        assertEquals(TgolKeyStore.ADD_USER_VIEW_NAME, result);
        assertTrue(bindingResult.hasErrors());
        assertEquals(3,bindingResult.getFieldErrors().size());
        assertNotNull(bindingResult.getFieldErrors("email"));
        assertEquals(1,bindingResult.getFieldErrors("email").size());
        assertEquals("sign-up.existingAccountWithEmail",bindingResult.getFieldErrors("email").get(0).getCode());
        assertNotNull(bindingResult.getFieldErrors("password"));
        assertEquals(1,bindingResult.getFieldErrors("password").size());
        assertEquals("sign-up.passwordNotIdentical",bindingResult.getFieldErrors("password").get(0).getCode());
        assertNotNull(bindingResult.getFieldErrors("generalErrorMsg"));
        assertEquals(1,bindingResult.getFieldErrors("generalErrorMsg").size());
        assertEquals("sign-up.mandatoryField",bindingResult.getFieldErrors("generalErrorMsg").get(0).getCode());
        
        // Then the url is not conform but in this context it is not tested, so ignored
        // The Email1 is not conform regardin the pattern EMAIL_CHECKER_REGEXP
        // The passwords match but don't respect the constraints
        createUserCommand = CreateUserCommandFactory.getInstance().getNewCreateUserCommand();
        createUserCommand.setEmail("null");
        createUserCommand.setPassword("password");
        createUserCommand.setConfirmPassword("password");
        bindingResult = new BeanPropertyBindingResult(createUserCommand,"createUserCommand");
        model = new ExtendedModelMap();
        result = instance.submitAddUserForm(createUserCommand, bindingResult, model);
        
        assertEquals(TgolKeyStore.ADD_USER_VIEW_NAME, result);
        assertTrue(bindingResult.hasErrors());
        assertEquals(3,bindingResult.getFieldErrors().size());
        assertNotNull(bindingResult.getFieldErrors("email"));
        assertEquals(1,bindingResult.getFieldErrors("email").size());
        assertEquals("sign-up.invalidEmail",bindingResult.getFieldErrors("email").get(0).getCode());
        assertNotNull(bindingResult.getFieldErrors("password"));
        assertEquals(1,bindingResult.getFieldErrors("password").size());
        assertEquals("sign-up.invalidPassword",bindingResult.getFieldErrors("password").get(0).getCode());
        assertNotNull(bindingResult.getFieldErrors("generalErrorMsg"));
        assertEquals(1,bindingResult.getFieldErrors("generalErrorMsg").size());
        assertEquals("sign-up.mandatoryField",bindingResult.getFieldErrors("generalErrorMsg").get(0).getCode());
        
    }

    /**
     * Test of deleteUserPage method, of class UserManagementController.
     */
    public void testDeleteUserPage() {
        System.out.println("deleteUserPage");
        
        instance = new UserManagementController();
        setUpMockRoleDataService();
        setUpMockUserDataService(false, false, false, false, false);
        setUpMockAuthenticationContext();
        instance.setUserDataService(mockUserDataService);
        
        HttpServletResponse response = new MockHttpServletResponse();
        MockHttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        
        String idToRemove="idToRemove";

        try {
            instance.displayDeleteUserPage(idToRemove, request, response, model);
            assertTrue(false);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
        
        idToRemove = "4";
        String result = instance.displayDeleteUserPage(idToRemove, request, response, model);
        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, result);
        
        idToRemove = "6";
        result = instance.displayDeleteUserPage(idToRemove, request, response, model);
        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, result);
        
        idToRemove = "5";
        result = instance.displayDeleteUserPage(idToRemove, request, response, model);
        assertEquals(TgolKeyStore.DELETE_USER_VIEW_NAME, result);
        assertEquals("user@test.com", model.asMap().get(TgolKeyStore.USER_NAME_TO_DELETE_KEY));
        assertEquals(Long.valueOf(5), request.getSession().getAttribute(TgolKeyStore.USER_ID_TO_DELETE_KEY));
        assertEquals(1, model.asMap().size());
    }

    /**
     * Test of deleteUserConfirmation method, of class UserManagementController.
     */
    public void testDeleteUserConfirmation() {
        System.out.println("deleteUserConfirmation");
        
        instance = new UserManagementController();
        setUpMockRoleDataService();
        setUpMockUserDataService(true, false, false, false, false);
        setUpMockAuthenticationContext();
        instance.setUserDataService(mockUserDataService);
        
        HttpServletResponse response = new MockHttpServletResponse();
        MockHttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();
        
        String idToRemove="idToRemove";
        request.getSession().setAttribute(TgolKeyStore.USER_ID_TO_DELETE_KEY, idToRemove);
        try {
            instance.displayDeleteUserConfirmation(request, response, model);
            assertTrue(false);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
        
        idToRemove = "4";
        request.getSession().setAttribute(TgolKeyStore.USER_ID_TO_DELETE_KEY, idToRemove);
        String result = instance.displayDeleteUserConfirmation(request, response, model);
        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, result);
        
        request.getSession().setAttribute(TgolKeyStore.USER_ID_TO_DELETE_KEY, Long.valueOf(6));
        result = instance.displayDeleteUserConfirmation(request, response, model);
        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, result);
        
        request.getSession().setAttribute(TgolKeyStore.USER_ID_TO_DELETE_KEY, Long.valueOf(5));
        result = instance.displayDeleteUserConfirmation(request, response, model);
        assertEquals(TgolKeyStore.ADMIN_VIEW_REDIRECT_NAME, result);
        assertTrue(model.asMap().isEmpty());
        assertEquals("user@test.com", request.getSession().getAttribute(TgolKeyStore.DELETED_USER_NAME_KEY));
    }

    private void setUpMockAuthenticationContext(){
        // initialise the context with the user identified by the email 
        // "test1@test.com" seen as authenticated
        Collection<GrantedAuthority> gac = new ArrayList<GrantedAuthority>();
        TgolUserDetails tud = new TgolUserDetails("test1@test.com", "", true, false, true, true, gac, mockAdminUser);
        mockAuthentication = createMock(Authentication.class);
        
        SecurityContextImpl securityContextImpl = new SecurityContextImpl();
        securityContextImpl.setAuthentication(mockAuthentication);
        SecurityContextHolder.setContext(securityContextImpl);
        
        expect(mockAuthentication.getName()).andReturn("admin@test.com").anyTimes();
        expect(mockAuthentication.getPrincipal()).andReturn(tud).anyTimes();
        expect(mockAuthentication.getAuthorities()).andReturn(null).anyTimes();
        
        replay(mockAuthentication);
    }
    
    private void setUpMockUserDataService(
            boolean hasDeleteCall, 
            boolean hasCreateCall,
            boolean activatedAndAdmin,
            boolean hasUpdateUserCall,
            boolean hasUpdateAdminCall) {
        // initialise the context with the user identified by the email 
        // "test1@test.com" seen as authenticated
        Collection<User> userCollection = new ArrayList<User>();
        mockUserDataService = createMock(UserDataService.class);
        mockAdminUser = createMock(User.class);
        mockUser = createMock(User.class);
        mockNewUser = createMock(User.class);
        
        expect(mockUserDataService.findAll()).andReturn(userCollection).anyTimes();
        
        expect(mockUserDataService.read(Long.valueOf(4))).andReturn(mockAdminUser).anyTimes();
        expect(mockUserDataService.getUserFromEmail("admin@test.com")).andReturn(mockAdminUser).anyTimes();
        
        expect(mockUserDataService.read(Long.valueOf(5))).andReturn(mockUser).anyTimes();
        expect(mockUserDataService.getUserFromEmail("user@test.com")).andReturn(mockUser).anyTimes();
        
        expect(mockUserDataService.read(Long.valueOf(6))).andReturn(null).anyTimes();
        expect(mockUserDataService.getUserFromEmail("null")).andReturn(null).anyTimes();
        
        expect(mockUserDataService.getUserFromEmail("newUser@test.com")).andReturn(null).anyTimes();
        
        if (hasDeleteCall) {
            mockUserDataService.delete(Long.valueOf(5));
            expectLastCall().once();
        }
        if (hasCreateCall) {
            expect(mockUserDataService.create()).andReturn(mockNewUser).once();
            mockNewUser.setEmail1("newUser@test.com");
            expectLastCall().once();
            mockNewUser.setPassword("6eccfd91aa8a36582fbf64b8ea533692");
            expectLastCall().once();
            mockNewUser.setWebUrl1("http://www.newSite.com/");
            expectLastCall().once();
            mockNewUser.setPhoneNumber(null);
            expectLastCall().once();
            mockNewUser.setName(null);
            expectLastCall().once();
            mockNewUser.setFirstName(null);
            expectLastCall().once();
            if (activatedAndAdmin) {
                mockNewUser.setAccountActivation(true);
                expectLastCall().once();
                mockNewUser.setRole(mockAdminRole);
                expectLastCall().once();
            } else {
                mockNewUser.setAccountActivation(false);
                expectLastCall().once();
                mockNewUser.setRole(mockUserRole);
                expectLastCall().once();
            }
            expect(mockUserDataService.saveOrUpdate(mockNewUser)).andReturn(mockNewUser).once();
            expect(mockNewUser.getEmail1()).andReturn("newUser@test.com").once();
        }
        if (hasUpdateUserCall) {
            mockUser.setEmail1("user@test.com");
            expectLastCall().once();
            mockUser.setName("newName");
            expectLastCall().once();
            mockUser.setFirstName("newFirstName");
            expectLastCall().once();
            mockUser.setRole(mockAdminRole);
            expectLastCall().once();
            mockUser.setAccountActivation(false);
            expectLastCall().once();
            mockUser.setPhoneNumber("0102030405");
            expectLastCall().once();
            expect(mockUserDataService.saveOrUpdate(mockUser)).andReturn(mockUser).once();
            expect(mockUser.getEmail1()).andReturn("user@test.com").once();
        }
        if (hasUpdateAdminCall) {
//            mockAdminUser.setEmail1("admin@test.com");
//            expectLastCall().once();
            mockAdminUser.setName("newName");
            expectLastCall().once();
            mockAdminUser.setFirstName("newFirstName");
            expectLastCall().once();
            // never called cause the current user is the modified user
//            mockAdminUser.setRole(mockAdminRole);
//            expectLastCall().once();
//            mockAdminUser.setAccountActivation(false);
//            expectLastCall().once();
            mockAdminUser.setPhoneNumber("0102030405");
            expectLastCall().once();
            expect(mockUserDataService.saveOrUpdate(mockAdminUser)).andReturn(mockAdminUser).once();
            expect(mockAdminUser.getEmail1()).andReturn("admin@test.com").once();
        }
        expect(mockAdminUser.getEmail1()).andReturn("admin@test.com").anyTimes();
        expect(mockAdminUser.getWebUrl1()).andReturn("http://www.admin.com").anyTimes();
        expect(mockAdminUser.getFirstName()).andReturn("firstNameAdmin").anyTimes();
        expect(mockAdminUser.getName()).andReturn("nameAdmin").anyTimes();
        expect(mockAdminUser.getPhoneNumber()).andReturn("0123456789").anyTimes();
        expect(mockAdminUser.isAccountActivated()).andReturn(true).anyTimes();
        expect(mockAdminUser.getId()).andReturn(Long.valueOf(4)).anyTimes();
        expect(mockAdminUser.getRole()).andReturn(mockAdminRole).anyTimes();
        
        expect(mockUser.getEmail1()).andReturn("user@test.com").anyTimes();
        expect(mockUser.getWebUrl1()).andReturn("http://www.user.com").anyTimes();
        expect(mockUser.getFirstName()).andReturn("firstNameUser").anyTimes();
        expect(mockUser.getName()).andReturn("nameUser").anyTimes();
        expect(mockUser.getPhoneNumber()).andReturn("9876543210").anyTimes();
        expect(mockUser.isAccountActivated()).andReturn(false).anyTimes();
        expect(mockUser.getId()).andReturn(Long.valueOf(5)).anyTimes();
        expect(mockUser.getRole()).andReturn(mockUserRole).anyTimes();

        expect(mockUser.getContractSet()).andReturn(new ArrayList<Contract>()).anyTimes();
        
        replay(mockUserDataService);
        replay(mockAdminUser);
        replay(mockUser);
        replay(mockNewUser);
    }

    private void setUpMockRoleDataService() {
        mockRoleDataService = createMock(RoleDataService.class);
        mockUserRole = createMock(Role.class);
        mockAdminRole = createMock(Role.class);
        
        expect(mockRoleDataService.read(Long.valueOf(2))).andReturn(mockUserRole).anyTimes();
        expect(mockRoleDataService.read(Long.valueOf(3))).andReturn(mockAdminRole).anyTimes();
        expect(mockAdminRole.getId()).andReturn(Long.valueOf(3)).anyTimes();
        expect(mockUserRole.getId()).andReturn(Long.valueOf(2)).anyTimes();

        replay(mockAdminRole);
        replay(mockUserRole);        
        replay(mockRoleDataService);
        
        CreateUserCommandFactory.getInstance().setRoleDataService(mockRoleDataService);
    }

}
