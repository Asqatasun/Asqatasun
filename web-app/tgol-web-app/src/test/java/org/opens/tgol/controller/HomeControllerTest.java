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
import javax.servlet.http.HttpServletResponse;
import junit.framework.TestCase;
import org.opens.tgol.action.voter.MockActionHandler;
import org.opens.tgol.entity.service.contract.ContractDataService;
import org.opens.tgol.entity.service.contract.MockActDataService;
import org.opens.tgol.entity.service.contract.MockContractDataService;
import org.opens.tgol.entity.service.user.MockUserDataService;
import org.opens.tgol.entity.service.user.UserDataService;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.exception.ForbiddenPageException;
import org.opens.tgol.mock.MockAuthenticationDetails;
import org.opens.tgol.mock.MockLocaleResolver;
import org.opens.tgol.presentation.factory.ContractInfoFactory;
import org.opens.tgol.presentation.factory.DetailedContractInfoFactory;
import org.opens.tgol.util.TgolKeyStore;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

/**
 *
 * @author jkowalczyk
 */
public class HomeControllerTest extends TestCase {
    
    private HomeController instance;
    
    public HomeControllerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        instance = new HomeController();
        
        // the HomeController needs a UserDataService instance
        UserDataService userDataService = new MockUserDataService(); 
        instance.setUserDataService(userDataService);
        
        // the HomeController needs a ContractDataService instance
        ContractDataService contractDataService = new MockContractDataService(); 
        ((MockContractDataService)contractDataService).setUserDataService(userDataService);
        instance.setContractDataService(contractDataService);
        
        // the HomeController uses a ContractInfoFactory to prepare the data
        // to display. This factory needs a ActDataService instance to retrieve
        // the acts related with the current contract.
        ContractInfoFactory.getInstance().setActDataService(new MockActDataService());
        ContractInfoFactory.getInstance().setContractDataService(contractDataService);
        
        // the HomeController uses a DetailedContractInfoFactory to prepare the data
        // to display. This factory needs a ActDataService instance to retrieve
        // the acts related with the current contract.
        DetailedContractInfoFactory.getInstance().setActDataService(new MockActDataService());
        DetailedContractInfoFactory.getInstance().setContractDataService(contractDataService);
        
        // the HomeController needs a LocaleResolver
        instance.setLocaleResolver(new MockLocaleResolver());
        
        // the HomeController needs a actionHandler
        instance.setActionHandler(new MockActionHandler());
        MockAuthenticationDetails.initSecurityContextHolder("test1@test.com");
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of displayHomePage method, of class HomeController.
     */
    public void testDisplayHomePage() {
        System.out.println("displayHomePage");
        Model model = new ExtendedModelMap();
        String expResult = TgolKeyStore.HOME_VIEW_NAME;
        String result = instance.displayHomePage(model);
        assertEquals(expResult, result);
        User user = ((User)model.asMap().get(TgolKeyStore.AUTHENTICATED_USER_KEY));
        assertEquals("test1@test.com", user.getEmail1());
    }

    /**
     * Test of displayContractPage method, of class HomeController.
     */
    public void testDisplayContractPage_4args() {
        System.out.println("displayContractPage");
        // contractId cannot be converted as a long, the ForbiddenUserException 
        // is caught
        String contractId = "wrongId";
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        Model model = new ExtendedModelMap();
        
        try {
            instance.displayContractPage(contractId, request, response, model);
            // if the exception is not caught, the test is on error
            assertTrue(false);
        } catch (ForbiddenPageException fue) {
            assertTrue(true);
        }
        contractId = "1";
        String result = instance.displayContractPage(contractId, request, response, model);
        String expResult = TgolKeyStore.CONTRACT_VIEW_NAME;
        assertEquals(expResult, result);
    }

}