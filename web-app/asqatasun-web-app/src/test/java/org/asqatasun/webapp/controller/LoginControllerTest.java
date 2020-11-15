/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
package org.asqatasun.webapp.controller;

import org.asqatasun.webapp.config.TestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.ExtendedModelMap;

import static org.asqatasun.webapp.util.TgolKeyStore.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


/**
 *
 * @author jkowalczyk
 */
@SpringBootTest
@ContextConfiguration(classes = TestConfiguration.class)
@ActiveProfiles({"integration"})
public class LoginControllerTest  {

    @Autowired
    private LoginController instance;
    @MockBean
    private Authentication mockAuthentication;

    @Test
    public void testDisplayLoginPage() {
        setUpMockAuthenticationContext(false);
        assertEquals(LOGIN_VIEW_NAME, instance.displayLoginPage());
    }

    @Test
    public void testHomePageWhenAlreadyAuthenticatedDisplayLoginPage() {
        setUpMockAuthenticationContext(true);
        assertEquals(HOME_VIEW_REDIRECT_NAME, instance.displayLoginPage());
    }

    /**
     * Test of displayAccessDeniedPage method, of class LoginController.
     */
    @Test
    public void testDisplayAccessDeniedPage() {
        assertEquals(ACCESS_DENIED_VIEW_NAME, instance.displayAccessDeniedPage());
    }

    private void setUpMockAuthenticationContext(boolean isAuthenticated){
        // initialise the context with the user identified by the email
        // "test1@test.com" seen as authenticated

        SecurityContextImpl securityContextImpl = new SecurityContextImpl();
        securityContextImpl.setAuthentication(mockAuthentication);
        SecurityContextHolder.setContext(securityContextImpl);
        when(mockAuthentication.isAuthenticated()).thenReturn(isAuthenticated);
        when(mockAuthentication.getName()).thenReturn("test1@test.com");
    }
}
