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

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.opens.tgol.action.Action;
import org.opens.tgol.action.voter.ActionHandler;
import org.opens.tgol.entity.contract.Act;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.contract.ScopeEnum;
import org.opens.tgol.entity.functionality.Functionality;
import org.opens.tgol.entity.option.OptionElement;
import org.opens.tgol.entity.service.contract.ActDataService;
import org.opens.tgol.entity.service.contract.ContractDataService;
import org.opens.tgol.entity.service.user.UserDataService;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.exception.ForbiddenPageException;
import org.opens.tgol.presentation.factory.ContractInfoFactory;
import org.opens.tgol.presentation.factory.DetailedContractInfoFactory;
import org.opens.tgol.security.userdetails.TgolUserDetails;
import org.opens.tgol.util.TgolKeyStore;
import org.springframework.security.authentication.AuthenticationDetails;
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
    private AuthenticationDetails mockAuthenticationDetails;
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
        if (mockAuthenticationDetails != null) {
            verify(mockAuthenticationDetails);
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
        
        setUpUserDataService(1,1,1,1,3,2,3,1,false);
        setUpContractDataService(1,1);
        setUpActDataService(1,1,2,1,1);
        setUpLocaleResolver(1);
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
        
        setUpUserDataService(0,0,1,0,0,0,0,0,true);
        setUpContractDataService(0,0);
        setUpActDataService(0,0,0,0,0);
        setUpLocaleResolver(0);
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

    private void setUpUserDataService(
            int getContractLabelCounter, 
            int getContractSetCounter, 
            int getEmailCounter,
            int getBeginDateCounter,
            int getEndDateCounter,
            int getFunctionalitySetCounter,
            int getContractIdCounter,
            int getContractOptionElementCounter,
            boolean expired) {
        mockContract = createMock(Contract.class);
        Collection<Contract> contractSet = new HashSet<Contract>();
        contractSet.add(mockContract);
        if (getContractLabelCounter > 0) {
            expect(mockContract.getLabel()).andReturn("").times(getContractLabelCounter);
        }
        if (getFunctionalitySetCounter > 0) {
            expect(mockContract.getFunctionalitySet()).andReturn(new HashSet<Functionality>()).times(getFunctionalitySetCounter);
        }
        if (getContractIdCounter > 0) {
            expect(mockContract.getId()).andReturn(Long.valueOf(1)).times(getContractIdCounter);
        }
        GregorianCalendar calendar = new GregorianCalendar();
        if (expired) {
            calendar.set(2011, 01, 01);
        } else {
            calendar.set(2030, 01, 01);
        }
        if (getEndDateCounter > 0) {
            expect(mockContract.getEndDate()).andReturn(calendar.getTime()).times(getEndDateCounter);
        }
        if (getBeginDateCounter > 0) {
            calendar.set(2010, 01, 01);
            expect(mockContract.getBeginDate()).andReturn(calendar.getTime()).times(getBeginDateCounter);
        }
        if (getContractOptionElementCounter > 0) {
            expect(mockContract.getOptionElementSet()).andReturn(new HashSet<OptionElement>()).times(getContractOptionElementCounter);
        }
        mockUser = createMock(User.class);
        if (getContractSetCounter > 0) {
            expect(mockUser.getContractSet()).andReturn(contractSet).times(getContractSetCounter);
        }
        if (getEmailCounter > 0) {
            expect(mockUser.getEmail1()).andReturn("test1@test.com").times(getEmailCounter);
        }
        expect(mockUser.getFirstName()).andReturn("").once();
        expect(mockUser.getName()).andReturn("").once();
        
        mockUserDataService = createMock(UserDataService.class); 
        
        replay(mockUser);
        replay(mockContract);
        replay(mockUserDataService);

        // the HomeController needs a UserDataService instance
        instance.setUserDataService(mockUserDataService);
    }
    
    private void setUpContractDataService(
            int getUrlFromContractOption,
            int getReadContractIdCounter) {
        // the HomeController needs a ContractDataService instance
        mockContractDataService = createMock(ContractDataService.class); 
        if (getUrlFromContractOption > 0  ) {
            expect(mockContractDataService.getUrlFromContractOption(mockContract)).andReturn("").times(getUrlFromContractOption);
            expect(mockContractDataService.getPresetContractKeyContractOption(mockContract)).andReturn("").times(getUrlFromContractOption);
        }
        if (getReadContractIdCounter > 0) {
            expect(mockContractDataService.read(Long.valueOf(1))).andReturn(mockContract).times(getReadContractIdCounter);
        }
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
            expect(mockActDataService.getNumberOfAct(mockContract)).andReturn(Integer.valueOf(1)).times(getNumberOfActCounter);
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
        Collection<GrantedAuthority> gac = new ArrayList<GrantedAuthority>();
        TgolUserDetails tud = new TgolUserDetails("test1@test.com", "", true, false, true, true, gac, mockUser);
        
        mockAuthentication = createMock(Authentication.class);
        SecurityContextImpl securityContextImpl = new SecurityContextImpl();
        securityContextImpl.setAuthentication(mockAuthentication);
        SecurityContextHolder.setContext(securityContextImpl);
        expect(mockAuthentication.getName()).andReturn("test1@test.com").anyTimes();
        expect(mockAuthentication.getPrincipal()).andReturn(tud).anyTimes();
        expect(mockAuthentication.getAuthorities()).andReturn(null).anyTimes();
        replay(mockAuthentication);
        
        mockAuthenticationDetails = createMock(AuthenticationDetails.class);
        expect(mockAuthenticationDetails.getContext()).andReturn("test1@test.com").anyTimes();
        replay(mockAuthenticationDetails);
    }
 
    private void setUpLocaleResolver(int resolveLocaleCounter) {
        mockLocaleResolver = createMock(LocaleResolver.class);
        if (resolveLocaleCounter  > 0) {
            expect(mockLocaleResolver.resolveLocale(null)).andReturn(Locale.FRANCE).times(resolveLocaleCounter);
        }
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