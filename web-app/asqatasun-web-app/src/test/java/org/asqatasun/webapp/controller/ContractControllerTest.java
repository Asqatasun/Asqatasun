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
import org.asqatasun.entity.contract.Contract;
import org.asqatasun.entity.service.contract.ActDataService;
import org.asqatasun.entity.service.contract.ContractDataService;
import org.asqatasun.entity.user.User;
import org.asqatasun.webapp.exception.ForbiddenPageException;
import org.asqatasun.webapp.exception.ForbiddenUserException;
import org.asqatasun.webapp.security.userdetails.TgolUserDetails;
import org.asqatasun.webapp.util.TgolKeyStore;
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
import org.springframework.ui.Model;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashSet;

import static org.asqatasun.webapp.util.TgolKeyStore.CONTRACT_WITH_MANUAL_AUDIT_KEY;
import static org.asqatasun.webapp.util.TgolKeyStore.DISPLAY_RESULT_TREND_KEY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author jkowalczyk
 */
@SpringBootTest
@ContextConfiguration(classes = TestConfiguration.class)
@ActiveProfiles({"integration"})
public class ContractControllerTest  {

    @Autowired
    private ContractController instance;
    @MockBean
    private ContractDataService mockContractDataService;
    @MockBean
    private LocaleResolver localeResolver;
    @MockBean
    private ActDataService actDataService;
    @MockBean
    private Authentication mockAuthentication;
    @MockBean
    private Contract mockContract;
    @MockBean
    private User mockUser;

    /**
     * Test of displayContractPage method, of class HomeController.
     */
    @Test
    public void testDisplayContractPage_4args() {

        setUpMockUserAndAuthenticationContext();
        setUpContractDataService(false);

        // contractId cannot be converted as a long, the ForbiddenUserException is caught
        Model model = new ExtendedModelMap();

        assertThrows(ForbiddenPageException.class, () -> instance.displayContractPage("wrongId", null, model));

        String result = instance.displayContractPage("1", null, model);
        assertEquals(TgolKeyStore.CONTRACT_VIEW_NAME, result);
        assertNull(model.getAttribute(DISPLAY_RESULT_TREND_KEY));
        assertNull(model.getAttribute(CONTRACT_WITH_MANUAL_AUDIT_KEY));
        verifyMockContractDataService();
    }

    @Test
    public void testDisplayContractExpiredPage_4args() {
        setUpMockUserAndAuthenticationContext();
        setUpContractDataService(true);

        // contractId cannot be converted as a long, the ForbiddenUserException is caught
        assertThrows(ForbiddenUserException.class,
            () -> instance.displayContractPage("1", null, new ExtendedModelMap()));

    }

    private void setUpContractDataService(boolean expired) {
        when(mockContract.getLabel()).thenReturn("");
        when(mockContract.getFunctionalitySet()).thenReturn(new HashSet<>());
        when(mockContract.getId()).thenReturn(1L);

        GregorianCalendar calendar = new GregorianCalendar();
        if (expired) {
            calendar.set(2011, 01, 01);
        } else {
            calendar.set(2030, 01, 01);
        }
        when(mockContract.getEndDate()).thenReturn(calendar.getTime());
        calendar.set(2010, 01, 01);
        when(mockContract.getBeginDate()).thenReturn(calendar.getTime());
        when(mockContract.getOptionElementSet()).thenReturn(new HashSet<>());

        when(mockContractDataService.getAllContractsByUser(mockUser)).thenReturn(new HashSet<Contract>(){{add(mockContract);}});
        when(mockContractDataService.getUrlFromContractOption(mockContract)).thenReturn("");
        when(mockContractDataService.getPresetContractKeyContractOption(mockContract)).thenReturn("");
        when(mockContractDataService.doesContractHaveFunctionality(mockContract,"MANUAL")).thenReturn(true);
        when(mockContractDataService.read(1L)).thenReturn(mockContract);
    }

    private void verifyMockContractDataService() {
        verify(mockContractDataService, times(1)).read(1L);
        verify(mockContractDataService, times(1)).getAllContractsByUser(mockUser);
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
