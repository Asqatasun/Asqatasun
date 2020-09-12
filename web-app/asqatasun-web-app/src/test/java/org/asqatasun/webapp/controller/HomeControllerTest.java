/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
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
import org.asqatasun.entity.service.contract.ContractDataService;
import org.asqatasun.entity.user.User;
import org.asqatasun.webapp.security.userdetails.TgolUserDetails;
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

import java.util.Collections;

import static org.asqatasun.webapp.util.TgolKeyStore.HOME_VIEW_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 *
 * @author jkowalczyk
 */
@SpringBootTest
@ContextConfiguration(classes = TestConfiguration.class)
@ActiveProfiles({"integration"})
public class HomeControllerTest {

    @Autowired
    private HomeController instance;
    @MockBean
    private ContractDataService mockContractDataService;
    @MockBean
    private Authentication mockAuthentication;
    @MockBean
    private User mockUser;

    /**
     * Test of displayHomePage method, of class HomeController.
     */
    @Test
    public void testDisplayHomePage() {
        setUpMockUserAndAuthenticationContext();
        assertEquals(HOME_VIEW_NAME, instance.displayHomePage(new ExtendedModelMap()));
    }

    private void setUpMockUserAndAuthenticationContext(){
        when(mockUser.getEmail1()).thenReturn("test1@test.com");
        when(mockUser.getFirstName()).thenReturn("");
        when(mockUser.getName()).thenReturn("");
        // initialise the context with the user identified by the email
        // "test1@test.com" seen as authenticated

        TgolUserDetails tud = new TgolUserDetails(
            "test1@test.com", "", true, false, true, true, Collections.emptyList(), mockUser);

        SecurityContextImpl securityContextImpl = new SecurityContextImpl();
        securityContextImpl.setAuthentication(mockAuthentication);
        SecurityContextHolder.setContext(securityContextImpl);
        when(mockAuthentication.getName()).thenReturn("test1@test.com");
        when(mockAuthentication.getPrincipal()).thenReturn(tud);
    }
 
}
