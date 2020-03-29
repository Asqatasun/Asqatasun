/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2019  Asqatasun.org
 * 
 *  This file is part of Asqatasun.
 * 
 *  Asqatasun is free software: you can redistribute it and/or modify
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
package org.asqatasun.webapp.controller;

import org.asqatasun.webapp.command.CreateUserCommand;
import org.asqatasun.webapp.command.factory.CreateUserCommandFactory;
import org.asqatasun.webapp.config.TestConfiguration;
import org.asqatasun.webapp.entity.service.user.RoleDataService;
import org.asqatasun.webapp.entity.service.user.UserDataService;
import org.asqatasun.webapp.entity.user.Role;
import org.asqatasun.webapp.entity.user.User;
import org.asqatasun.webapp.exception.ForbiddenUserException;
import org.asqatasun.webapp.security.userdetails.TgolUserDetails;
import org.asqatasun.webapp.util.TgolKeyStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.asqatasun.webapp.util.TgolKeyStore.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author jkowalczyk
 */
@SpringBootTest
@ContextConfiguration(classes = TestConfiguration.class)
@ActiveProfiles({"integration"})
public class UserManagementControllerTest  {

    @Autowired
    UserManagementController instance;
    @MockBean
    UserDataService mockUserDataService;
    @MockBean
    RoleDataService mockRoleDataService;
    @MockBean
    Authentication mockAuthentication;
    @Autowired
    private CreateUserCommandFactory createUserCommandFactory;
    User mockAdminUser;
    User mockUser;
    User mockNewUser;
    Role mockAdminRole;
    Role mockUserRole;

    /**
     * Test of displayAdminPage method, of class UserManagementController.
     */
    @Test
    public void testDisplayAdminPage() {
        setUpMockRoleDataService();
        setUpMockUserDataService(false, false, false);
        setUpMockAuthenticationContext();
        assertEquals(ADMIN_VIEW_NAME,
            instance.displayAdminPage(
                new MockHttpServletRequest(),
                new MockHttpServletResponse(),
                new ExtendedModelMap()));
    }

    /**
     * Test of displayAdminPage method, of class UserManagementController.
     */
    @Test
    public void testDisplayAdminPageWithDeletedUserMessage() {
        setUpMockRoleDataService();
        setUpMockUserDataService(false, false, false);
        setUpMockAuthenticationContext();

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
    @Test
    public void testEditUserAdminPage() {
        setUpMockRoleDataService();
        setUpMockUserDataService(false, false, false);
        setUpMockAuthenticationContext();

        HttpServletResponse response = new MockHttpServletResponse();
        MockHttpServletRequest request = new MockHttpServletRequest();
        // contractId cannot be converted as a long, the ForbiddenUserException is caught
        assertThrows(ForbiddenUserException.class,
            () -> instance.displayEditUserAdminPage("", request, response, new ExtendedModelMap()));

        String userId = "4";
        Model model = new ExtendedModelMap();
        String result = instance.displayEditUserAdminPage(userId, request, response, model);
        assertEquals(TgolKeyStore.EDIT_USER_VIEW_NAME, result);
        CreateUserCommand cuc = ((CreateUserCommand)model.asMap().get(CREATE_USER_COMMAND_KEY));
        assertNotNull(cuc);
        assertTrue(cuc.getActivated());
        assertTrue(cuc.getAdmin());
        assertEquals("admin@test.com",cuc.getEmail());
        assertEquals("http://www.admin.com",cuc.getSiteUrl());
        assertEquals("0123456789",cuc.getPhoneNumber());
        assertEquals("nameAdmin",cuc.getLastName());
        assertEquals("firstNameAdmin",cuc.getFirstName());
        assertEquals(4L,request.getSession().getAttribute(TgolKeyStore.USER_ID_KEY));

        userId = "5";
        result = instance.displayEditUserAdminPage(userId, request, response, model);
        assertEquals(TgolKeyStore.EDIT_USER_VIEW_NAME, result);
        cuc = ((CreateUserCommand)model.asMap().get(CREATE_USER_COMMAND_KEY));
        assertNotNull(cuc);
        assertFalse(cuc.getActivated());
        assertFalse(cuc.getAdmin());
        assertEquals("user@test.com",cuc.getEmail());
        assertEquals("http://www.user.com",cuc.getSiteUrl());
        assertEquals("9876543210",cuc.getPhoneNumber());
        assertEquals("nameUser",cuc.getLastName());
        assertEquals("firstNameUser",cuc.getFirstName());
        assertEquals(5L,request.getSession().getAttribute(TgolKeyStore.USER_ID_KEY));
    }

    /**
     * Test of submitEditUserForm method, of class UserManagementController.
     */
    @Test
    public void testSubmitEditUserForm() throws Exception {
        setUpMockRoleDataService();
        setUpMockUserDataService(false, true, false);
        setUpMockAuthenticationContext();

        // Finally the form is conform and the admin page is returned
        CreateUserCommand createUserCommand = createUserCommandFactory.getNewCreateUserCommand();
        createUserCommand.setEmail("user@test.com");
        createUserCommand.setLastName("newName");
        createUserCommand.setFirstName("newFirstName");
        createUserCommand.setPhoneNumber("0102030405");
        createUserCommand.setActivated(false);
        createUserCommand.setAdmin(true);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.getSession().setAttribute(TgolKeyStore.USER_ID_KEY, 5L);
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
        assertTrue(((List <User>)model.asMap().get(TgolKeyStore.USER_LIST_KEY)).isEmpty());
    }

    /**
     * Test of submitEditUserForm method, of class UserManagementController.
     */
    @Test
    public void testSubmitEditCurrentUserForm() throws Exception {
        setUpMockRoleDataService();
        setUpMockUserDataService(false, false, true);
        setUpMockAuthenticationContext();

        // Finally the form is conform and the admin page is returned
        CreateUserCommand createUserCommand = createUserCommandFactory.getNewCreateUserCommand();
        createUserCommand.setEmail("admin@test.com");
        createUserCommand.setLastName("newName");
        createUserCommand.setFirstName("newFirstName");
        createUserCommand.setPhoneNumber("0102030405");
        createUserCommand.setActivated(false);
        createUserCommand.setAdmin(false);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.getSession().setAttribute(TgolKeyStore.USER_ID_KEY, 4L);
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
    @Test
    public void testSubmitEditUserChangeToExistingUserForm() throws Exception {
        setUpMockRoleDataService();
        setUpMockUserDataService(false, false, false);
        setUpMockAuthenticationContext();

        // Finally the form is conform and the admin page is returned
        CreateUserCommand createUserCommand = createUserCommandFactory.getNewCreateUserCommand();
        createUserCommand.setSiteUrl("http://www.newSite.com/");
        createUserCommand.setEmail("admin@test.com");
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.getSession().setAttribute(USER_ID_KEY, Long.valueOf(5));
        BindingResult bindingResult = new BeanPropertyBindingResult(createUserCommand,"createUserCommand");
        String result = instance.submitEditUserForm(
                createUserCommand,
                bindingResult,
                request,
            new ExtendedModelMap());

        assertEquals(EDIT_USER_VIEW_NAME, result);
        assertTrue(bindingResult.hasErrors());
        assertEquals(1,bindingResult.getFieldErrors().size());
        assertNotNull(bindingResult.getFieldErrors("email"));
        assertEquals(1,bindingResult.getFieldErrors("email").size());
        assertEquals("sign-up.existingAccountWithEmail",bindingResult.getFieldErrors("email").get(0).getCode());
    }

    /**
     * Test of addUserAdminPage method, of class UserManagementController.
     */
    @Test
    public void testAddUserAdminPage() {
        setUpMockRoleDataService();
        setUpMockUserDataService(false, false, false);
        setUpMockAuthenticationContext();

        HttpServletResponse response = new MockHttpServletResponse();
        MockHttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();

        String result = instance.displayAddUserAdminPage(request, response, model);

        assertEquals(ADD_USER_VIEW_NAME, result);
        assertEquals(1, model.asMap().size());

        CreateUserCommand cuc = ((CreateUserCommand)model.asMap().get(CREATE_USER_COMMAND_KEY));
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
    @Test
    public void testSubmitAddUserForm() throws Exception {
        setUpMockRoleDataService();
        setUpMockUserDataService(true, false, false);
        setUpMockAuthenticationContext();

        // Finally the form is conform and the admin page is returned
        CreateUserCommand createUserCommand = createUserCommandFactory.getNewCreateUserCommand();
        createUserCommand.setSiteUrl("http://www.newSite.com/");
        createUserCommand.setEmail("newUser@test.com");
        createUserCommand.setPassword("P4sSw0rD");
        createUserCommand.setConfirmPassword("P4sSw0rD");
        BindingResult bindingResult = new BeanPropertyBindingResult(createUserCommand,"createUserCommand");
        Model model = new ExtendedModelMap();
        String result = instance.submitAddUserForm(createUserCommand, bindingResult, model);

        assertEquals(ADMIN_VIEW_NAME, result);
        assertFalse(bindingResult.hasErrors());
        assertTrue(bindingResult.getFieldErrors().isEmpty());
        assertEquals(2, model.asMap().size());
        assertEquals("newUser@test.com",model.asMap().get(ADDED_USER_NAME_KEY));
        assertTrue(((List<User>)model.asMap().get(TgolKeyStore.USER_LIST_KEY)).isEmpty());
    }

    /**
     * Test of submitAddUserForm method, of class UserManagementController.
     */
    @Test
    public void testSubmitAddAdminUserForm() throws Exception {
        setUpMockRoleDataService();
        setUpMockUserDataService(true, false, false);
        setUpMockAuthenticationContext();

        // Finally the form is conform and the admin page is returned
        CreateUserCommand createUserCommand = createUserCommandFactory.getNewCreateUserCommand();
        createUserCommand.setSiteUrl("http://www.newSite.com/");
        createUserCommand.setEmail("newUser@test.com");
        createUserCommand.setPassword("P4sSw0rD");
        createUserCommand.setConfirmPassword("P4sSw0rD");
        createUserCommand.setAdmin(true);
        createUserCommand.setActivated(true);
        BindingResult bindingResult = new BeanPropertyBindingResult(createUserCommand,"createUserCommand");
        Model model = new ExtendedModelMap();
        String result = instance.submitAddUserForm(createUserCommand, bindingResult, model);

        assertEquals(ADMIN_VIEW_NAME, result);
        assertFalse(bindingResult.hasErrors());
        assertTrue(bindingResult.getFieldErrors().isEmpty());
        assertEquals(2, model.asMap().size());
        assertEquals("newUser@test.com",model.asMap().get(ADDED_USER_NAME_KEY));
        assertTrue(((List<User>)model.asMap().get(TgolKeyStore.USER_LIST_KEY)).isEmpty());
    }

    /**
     * Test of submitAddUserForm method, of class UserManagementController.
     */
    @Test
    public void testSubmitAddUserFormWithErrors() throws Exception {
        setUpMockRoleDataService();
        setUpMockUserDataService(false, false, false);
        setUpMockAuthenticationContext();

        // First the form is sent back empty
        CreateUserCommand createUserCommand = createUserCommandFactory.getNewCreateUserCommand();
        BindingResult bindingResult = new BeanPropertyBindingResult(createUserCommand,"createUserCommand");
        Model model = new ExtendedModelMap();
        String result = instance.submitAddUserForm(createUserCommand, bindingResult, model);

        assertEquals(ADD_USER_VIEW_NAME, result);
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
        createUserCommand = createUserCommandFactory.getNewCreateUserCommand();
        createUserCommand.setEmail("user@test.com");
        createUserCommand.setPassword("password");
        createUserCommand.setConfirmPassword("confirmation");
        bindingResult = new BeanPropertyBindingResult(createUserCommand,"createUserCommand");
        model = new ExtendedModelMap();
        result = instance.submitAddUserForm(createUserCommand, bindingResult, model);

        assertEquals(ADD_USER_VIEW_NAME, result);
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
        createUserCommand = createUserCommandFactory.getNewCreateUserCommand();
        createUserCommand.setEmail("null");
        createUserCommand.setPassword("password");
        createUserCommand.setConfirmPassword("password");
        bindingResult = new BeanPropertyBindingResult(createUserCommand,"createUserCommand");
        model = new ExtendedModelMap();
        result = instance.submitAddUserForm(createUserCommand, bindingResult, model);

        assertEquals(ADD_USER_VIEW_NAME, result);
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
    @Test
    public void testDeleteUserPage() {
        setUpMockRoleDataService();
        setUpMockUserDataService(false, false, false);
        setUpMockAuthenticationContext();

        HttpServletResponse response = new MockHttpServletResponse();
        MockHttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();

        assertThrows(ForbiddenUserException.class,
            () -> instance.displayEditUserAdminPage("idToRemove", request, response, new ExtendedModelMap()));

        String id = "4";
        String result = instance.displayDeleteUserPage(id, request, response, model);
        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, result);

        id = "6";
        result = instance.displayDeleteUserPage(id, request, response, model);
        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, result);

        id = "5";
        result = instance.displayDeleteUserPage(id, request, response, model);
        assertEquals(TgolKeyStore.DELETE_USER_VIEW_NAME, result);
        assertEquals("user@test.com", model.asMap().get(TgolKeyStore.USER_NAME_TO_DELETE_KEY));
        assertEquals(Long.valueOf(5), request.getSession().getAttribute(TgolKeyStore.USER_ID_TO_DELETE_KEY));
        assertEquals(1, model.asMap().size());
    }

    /**
     * Test of deleteUserConfirmation method, of class UserManagementController.
     */
    @Test
    public void testDeleteUserConfirmation() {
        setUpMockRoleDataService();
        setUpMockUserDataService(true, false, false);
        setUpMockAuthenticationContext();

        HttpServletResponse response = new MockHttpServletResponse();
        MockHttpServletRequest request = new MockHttpServletRequest();
        Model model = new ExtendedModelMap();

        assertThrows(ForbiddenUserException.class,
            () -> instance.displayEditUserAdminPage("idToRemove", request, response, new ExtendedModelMap()));

        request.getSession().setAttribute(TgolKeyStore.USER_ID_TO_DELETE_KEY, 4L);
        String result = instance.displayDeleteUserConfirmation(request, response, model);
        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, result);

        request.getSession().setAttribute(TgolKeyStore.USER_ID_TO_DELETE_KEY, 6L);
        result = instance.displayDeleteUserConfirmation(request, response, model);
        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, result);

        request.getSession().setAttribute(TgolKeyStore.USER_ID_TO_DELETE_KEY, 5L);
        result = instance.displayDeleteUserConfirmation(request, response, model);
        assertEquals(TgolKeyStore.ADMIN_VIEW_REDIRECT_NAME, result);
        assertTrue(model.asMap().isEmpty());
        assertEquals("user@test.com", request.getSession().getAttribute(TgolKeyStore.DELETED_USER_NAME_KEY));
    }

    private void setUpMockAuthenticationContext(){
        // initialise the context with the user identified by the email
        // "test1@test.com" seen as authenticated
        // initialise the context with the user identified by the email
        // "test1@test.com" seen as authenticated

        TgolUserDetails tud = new TgolUserDetails(
            "test1@test.com",
            "",
            true,
            false,
            true,
            true,
            Collections.emptyList(),
            mockAdminUser);

        SecurityContextImpl securityContextImpl = new SecurityContextImpl();
        securityContextImpl.setAuthentication(mockAuthentication);
        SecurityContextHolder.setContext(securityContextImpl);
        when(mockAuthentication.getName()).thenReturn("test1@test.com");
        when(mockAuthentication.getPrincipal()).thenReturn(tud);
    }

    private void setUpMockUserDataService(
            boolean hasCreateCall,
            boolean hasUpdateUserCall,
            boolean hasUpdateAdminCall) {
        // initialise the context with the user identified by the email
        // "test1@test.com" seen as authenticated
        Collection<User> userCollection = new ArrayList<>();
        mockAdminUser = new User();
        mockUser = new User();
        mockNewUser = new User();

        when(mockUserDataService.findAll()).thenReturn(userCollection);

        when(mockUserDataService.read(4L)).thenReturn(mockAdminUser);
        when(mockUserDataService.getUserFromEmail("admin@test.com")).thenReturn(mockAdminUser);

        when(mockUserDataService.read(5L)).thenReturn(mockUser);
        when(mockUserDataService.getUserFromEmail("user@test.com")).thenReturn(mockUser);

        when(mockUserDataService.read(6L)).thenReturn(null);
        when(mockUserDataService.getUserFromEmail("null")).thenReturn(null);

        when(mockUserDataService.getUserFromEmail("newUser@test.com")).thenReturn(null);

        if (hasCreateCall) {
            when(mockUserDataService.create()).thenReturn(mockNewUser);
            mockNewUser.setEmail1("newUser@test.com");
            mockNewUser.setPassword("6eccfd91aa8a36582fbf64b8ea533692");
            mockNewUser.setWebUrl1("http://www.newSite.com/");
            mockNewUser.setPhoneNumber(null);
            mockNewUser.setName(null);
            mockNewUser.setFirstName(null);
            mockNewUser.setAccountActivation(false);
            mockNewUser.setRole(mockUserRole);
            when(mockUserDataService.saveOrUpdate(mockNewUser)).thenReturn(mockNewUser);
            mockNewUser.setEmail1("newUser@test.com");
        }
        if (hasUpdateUserCall) {
            mockUser.setEmail1("user@test.com");
            mockUser.setName("newName");
            mockUser.setFirstName("newFirstName");
            mockUser.setRole(mockAdminRole);
            mockUser.setAccountActivation(false);
            mockUser.setPhoneNumber("0102030405");
            when(mockUserDataService.saveOrUpdate(mockUser)).thenReturn(mockUser);
            mockUser.setEmail1("user@test.com");
        }
        mockAdminUser.setEmail1("admin@test.com");
        mockAdminUser.setWebUrl1("http://www.admin.com");
        mockAdminUser.setFirstName("firstNameAdmin");
        mockAdminUser.setName("nameAdmin");
        mockAdminUser.setPhoneNumber("0123456789");
        mockAdminUser.setAccountActivation(true);
        mockAdminUser.setId(4L);
        mockAdminUser.setRole(mockAdminRole);
        if (hasUpdateAdminCall) {
            mockAdminUser.setName("newName");
            mockAdminUser.setFirstName("newFirstName");
            mockAdminUser.setPhoneNumber("0102030405");
            when(mockUserDataService.saveOrUpdate(mockAdminUser)).thenReturn(mockAdminUser);
            mockAdminUser.setEmail1("admin@test.com");
        }

        mockUser.setEmail1("user@test.com");
        mockUser.setWebUrl1("http://www.user.com");
        mockUser.setFirstName("firstNameUser");
        mockUser.setName("nameUser");
        mockUser.setPhoneNumber("9876543210");
        mockUser.setAccountActivation(false);
        mockUser.setId(5L);
        mockUser.setRole(mockUserRole);
    }

    private void setUpMockRoleDataService() {
        mockUserRole = new Role();
        mockUserRole.setId(2L);
        mockAdminRole = new Role();
        mockAdminRole.setId(3L);
        when(mockRoleDataService.read(2L)).thenReturn(mockUserRole);
        when(mockRoleDataService.read(3L)).thenReturn(mockAdminRole);
    }

}
