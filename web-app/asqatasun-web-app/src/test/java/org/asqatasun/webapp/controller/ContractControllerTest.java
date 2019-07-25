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

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.asqatasun.webapp.action.Action;
import org.asqatasun.webapp.action.voter.ActionHandler;
import org.asqatasun.webapp.entity.contract.Act;
import org.asqatasun.webapp.entity.contract.Contract;
import org.asqatasun.webapp.entity.contract.ScopeEnum;
import org.asqatasun.webapp.entity.functionality.Functionality;
import org.asqatasun.webapp.entity.option.OptionElement;
import org.asqatasun.webapp.entity.service.contract.ActDataService;
import org.asqatasun.webapp.entity.service.contract.ContractDataService;
import org.asqatasun.webapp.entity.service.user.UserDataService;
import org.asqatasun.webapp.entity.user.User;
import org.asqatasun.webapp.exception.ForbiddenPageException;
import org.asqatasun.webapp.presentation.factory.ContractInfoFactory;
import org.asqatasun.webapp.presentation.factory.DetailedContractInfoFactory;
import org.asqatasun.webapp.security.userdetails.TgolUserDetails;
import org.asqatasun.webapp.util.TgolKeyStore;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.servlet.LocaleResolver;

/**
 *
 * @author jkowalczyk
 */
public class ContractControllerTest extends TestCase {
    
    private ContractController instance;
    private UserDataService mockUserDataService;
    private ContractDataService mockContractDataService;
    private ActDataService mockActDataService;
    private LocaleResolver mockLocaleResolver;
    private ActionHandler mockActionHandler;
    private Authentication mockAuthentication;
    private Contract mockContract;
    private User mockUser;
    
           
    public ContractControllerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        instance = new ContractController();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if (mockUserDataService != null) {
            verify(mockUserDataService);
        }
        if (mockUser != null) {
            verify(mockUser);
        }
        if (mockContractDataService != null) {
            verify(mockContractDataService);
        }
        if (mockContract != null) {
            verify(mockContract);
        }
        if (mockActDataService != null) {
            verify(mockActDataService);
        }
        if (mockLocaleResolver != null) {
            verify(mockLocaleResolver);
        }
        if (mockActionHandler != null) {
            verify(mockActionHandler);
        }
        if (mockAuthentication != null) {
            verify(mockAuthentication);
        }
    }

    /**
     * Test of displayContractPage method, of class HomeController.
     */
    public void testDisplayContractPage_4args() {
        System.out.println("displayContractPage");
        
        setUpUserDataService(false);
        setUpContractDataService(1);
        setUpActDataService(1,1,2,1,1);
        setUpLocaleResolver();
        setUpActionHandler(1);
        setUpMockAuthenticationContext();
        
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
    
    public void testDisplayContractExpiredPage_4args() {
        System.out.println("displayContractExpiredPage");
        
        setUpUserDataService(true);
        setUpContractDataService(0);
        setUpActDataService(0,0,0,0,0);
        setUpLocaleResolver();
        setUpActionHandler(0);
        setUpMockAuthenticationContext();
        
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
        try {
            instance.displayContractPage(contractId, request, response, model);
            // if the exception is not caught, the test is on error
            assertTrue(false);
        } catch (ForbiddenPageException fue) {
            assertTrue(true);
        }
    }

    private void setUpUserDataService(boolean expired) {
        mockContract = createMock(Contract.class);
        Collection<Contract> contractSet = new HashSet<>();
        contractSet.add(mockContract);
        expect(mockContract.getLabel()).andReturn("").anyTimes();
        expect(mockContract.getFunctionalitySet()).andReturn(new HashSet<Functionality>()).anyTimes();
        expect(mockContract.getId()).andReturn(Long.valueOf(1)).anyTimes();

        GregorianCalendar calendar = new GregorianCalendar();
        if (expired) {
            calendar.set(2011, 01, 01);
        } else {
            calendar.set(2030, 01, 01);
        }
        expect(mockContract.getEndDate()).andReturn(calendar.getTime()).anyTimes();
        calendar.set(2010, 01, 01);
        expect(mockContract.getBeginDate()).andReturn(calendar.getTime()).anyTimes();
        expect(mockContract.getOptionElementSet()).andReturn(new HashSet<OptionElement>()).anyTimes();
        
        mockUser = createMock(User.class);
        expect(mockUser.getContractSet()).andReturn(contractSet).anyTimes();
        expect(mockUser.getEmail1()).andReturn("test1@test.com").anyTimes();
        
        expect(mockUser.getFirstName()).andReturn("").anyTimes();
        expect(mockUser.getName()).andReturn("").anyTimes();
        
        mockUserDataService = createMock(UserDataService.class); 
        
        replay(mockUser);
        replay(mockContract);
        replay(mockUserDataService);

        // the HomeController needs a UserDataService instance
        instance.setUserDataService(mockUserDataService);
    }
    
    private void setUpContractDataService(int getUrlFromContractOption) {
        // the HomeController needs a ContractDataService instance
        mockContractDataService = createMock(ContractDataService.class); 
        if (getUrlFromContractOption > 0  ) {
            expect(mockContractDataService.getUrlFromContractOption(mockContract)).andReturn("").times(getUrlFromContractOption);
            expect(mockContractDataService.getPresetContractKeyContractOption(mockContract)).andReturn("").times(getUrlFromContractOption);
        }
        expect(mockContractDataService.doesContractHaveFunctionality(mockContract,"MANUAL")).andReturn(true).anyTimes();
        expect(mockContractDataService.read(Long.valueOf(1))).andReturn(mockContract).anyTimes();
        replay(mockContractDataService);
        
        instance.setContractDataService(mockContractDataService);
        ContractInfoFactory.getInstance().setContractDataService(mockContractDataService);
        DetailedContractInfoFactory.getInstance().setContractDataService(mockContractDataService);
    }
    
    private void setUpActDataService(
            int getRunningActsByContract,
            int getNumberOfActCounter,
            int getActsByContract1,
            int getActsByContract2,
            int getActsByContract3) {
        mockActDataService = createMock(ActDataService.class);
        if (getRunningActsByContract > 0) {
            expect(mockActDataService.getRunningActsByContract(mockContract)).andReturn(new HashSet<Act>()).times(getRunningActsByContract);
        }
        if (getActsByContract1 > 0) {
            expect(mockActDataService.getActsByContract(mockContract,1,2,null,false)).andReturn(new HashSet<Act>()).times(getActsByContract1);
        }
        if (getActsByContract2 > 0) {
            expect(mockActDataService.getActsByContract(mockContract,2,2, ScopeEnum.DOMAIN,true)).andReturn(new HashSet<Act>()).times(getActsByContract2);
        }
        if (getActsByContract3 > 0) {
            expect(mockActDataService.getActsByContract(mockContract,-1,1, ScopeEnum.DOMAIN,true)).andReturn(new HashSet<Act>()).times(getActsByContract3);
        }
        if (getNumberOfActCounter > 0) {
            expect(mockActDataService.getNumberOfAct(mockContract)).andReturn(1).times(getNumberOfActCounter);
        }
        
        replay(mockActDataService);
        
        // the HomeController uses a ContractInfoFactory to prepare the data
        // to display. This factory needs a ActDataService instance to retrieve
        // the acts related with the current contract.
        ContractInfoFactory.getInstance().setActDataService(mockActDataService);
        
        // the HomeController uses a DetailedContractInfoFactory to prepare the data
        // to display. This factory needs a ActDataService instance to retrieve
        // the acts related with the current contract.
        DetailedContractInfoFactory.getInstance().setActDataService(mockActDataService);
    }

    private void setUpMockAuthenticationContext(){
        // initialise the context with the user identified by the email 
        // "test1@test.com" seen as authenticated
        Collection<GrantedAuthority> gac = new ArrayList();
        TgolUserDetails tud = new TgolUserDetails("test1@test.com", "", true, false, true, true, gac, mockUser);
        
        mockAuthentication = createMock(Authentication.class);
        SecurityContextImpl securityContextImpl = new SecurityContextImpl();
        securityContextImpl.setAuthentication(mockAuthentication);
        SecurityContextHolder.setContext(securityContextImpl);
        expect(mockAuthentication.getName()).andReturn("test1@test.com").anyTimes();
        expect(mockAuthentication.getPrincipal()).andReturn(tud).anyTimes();
        expect(mockAuthentication.getAuthorities()).andReturn(null).anyTimes();
        replay(mockAuthentication);

    }
 
    private void setUpLocaleResolver() {
        mockLocaleResolver = createMock(LocaleResolver.class);
        expect(mockLocaleResolver.resolveLocale(null)).andReturn(Locale.FRANCE).anyTimes();
        replay(mockLocaleResolver);

        instance.setLocaleResolver(mockLocaleResolver);
    }
    
    private void setUpActionHandler(int getActionListCounter) {
        // the HomeController needs a actionHandler
        mockActionHandler = createMock(ActionHandler.class);
        if (getActionListCounter > 0) {
            expect(mockActionHandler.getActionList(mockContract)).andReturn(new ArrayList<Action>()).times(getActionListCounter);
        }
        replay(mockActionHandler);
        ContractInfoFactory.getInstance().setActionHandler(mockActionHandler);
        DetailedContractInfoFactory.getInstance().setActionHandler(mockActionHandler);
    }
}
