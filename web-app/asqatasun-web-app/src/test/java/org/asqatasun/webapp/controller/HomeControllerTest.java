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
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.asqatasun.webapp.action.Action;
import org.asqatasun.webapp.action.voter.ActionHandler;
import org.asqatasun.webapp.command.helper.ContractSortCommandHelper;
import org.asqatasun.webapp.entity.contract.Act;
import org.asqatasun.webapp.entity.contract.Contract;
import org.asqatasun.webapp.entity.contract.ScopeEnum;
import org.asqatasun.webapp.entity.service.contract.ActDataService;
import org.asqatasun.webapp.entity.service.contract.ContractDataService;
import org.asqatasun.webapp.entity.user.User;
import org.asqatasun.webapp.form.builder.FormFieldBuilder;
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

/**
 *
 * @author jkowalczyk
 */
public class HomeControllerTest extends TestCase {
    
    private HomeController instance;
    private ContractDataService mockContractDataService;
    private ActDataService mockActDataService;
    private ActionHandler mockActionHandler;
    private Authentication mockAuthentication;
    private Contract mockContract;
    private User mockUser;
    
           
    public HomeControllerTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        instance = new HomeController();
        List<FormFieldBuilder> formFieldBuilderList = new ArrayList<FormFieldBuilder>();
        instance.setDisplayOptionFieldsBuilderList(formFieldBuilderList);
        ContractSortCommandHelper.setExclusionContractSortKey("label-exclusion-choice");
        ContractSortCommandHelper.setInclusionContractSortKey("label-inclusion-choice");
        ContractSortCommandHelper.setLastAuditDateSortValue("date");
        ContractSortCommandHelper.setLastAuditMarkSortValue("mark");
    }        
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
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
        if (mockAuthentication != null) {
            verify(mockAuthentication);
        }
        if (mockActionHandler != null) {
            verify(mockActionHandler);
        }
    }

    /**
     * Test of displayHomePage method, of class HomeController.
     */
    public void testDisplayHomePage() {
        System.out.println("displayHomePage");

        setUpUserDataService();
        setUpMockAuthenticationContext();
        setUpContractDataService();
        setUpActDataService();
        
        Model model = new ExtendedModelMap();
        String expResult = TgolKeyStore.HOME_VIEW_NAME;
        String result = instance.displayHomePage(model);
        assertEquals(expResult, result);
    }

    private void setUpUserDataService() {
        mockContract = createMock(Contract.class);
        Collection<Contract> contractSet = new HashSet<Contract>();
        contractSet.add(mockContract);

        expect(mockContract.getLabel()).andReturn("").times(3);

        expect(mockContract.getId()).andReturn(Long.valueOf(1)).times(2);

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(2030, 01, 01);

        expect(mockContract.getEndDate()).andReturn(calendar.getTime()).times(2);

        mockUser = createMock(User.class);
        
        expect(mockUser.getContractSet()).andReturn(contractSet).once();
        expect(mockUser.getEmail1()).andReturn("test1@test.com").once();
        expect(mockUser.getId()).andReturn(Long.valueOf(1)).once();
        expect(mockUser.getFirstName()).andReturn("").once();
        expect(mockUser.getName()).andReturn("").once();
        
        replay(mockUser);
        replay(mockContract);
    }
    
    private void setUpContractDataService() {

        mockContractDataService = createMock(ContractDataService.class); 
        expect(mockContractDataService.getUrlFromContractOption(mockContract)).andReturn("").once();
        expect(mockContractDataService.getPresetContractKeyContractOption(mockContract)).andReturn("").once();
        expect(mockContractDataService.read(Long.valueOf("1"))).andReturn(mockContract).once();
        
        replay(mockContractDataService);
        
        mockActionHandler = createMock(ActionHandler.class);
        List<Action> actionList = new ArrayList<Action>();
        expect(mockActionHandler.getActionList(mockContract)).andReturn(actionList).once();
        
        replay(mockActionHandler);
        
        instance.setContractDataService(mockContractDataService);
        ContractInfoFactory.getInstance().setContractDataService(mockContractDataService);
        ContractInfoFactory.getInstance().setActionHandler(mockActionHandler);
        DetailedContractInfoFactory.getInstance().setContractDataService(mockContractDataService);
    }
    
    private void setUpActDataService() {
        mockActDataService = createMock(ActDataService.class);
        
        expect(mockActDataService.getRunningActsByContract(mockContract)).andReturn(new HashSet<Act>()).once();
        expect(mockActDataService.getActsByContract(mockContract,1,2,null,false)).andReturn(new HashSet<Act>()).once();
        expect(mockActDataService.getActsByContract(mockContract,2,2, ScopeEnum.DOMAIN,true)).andReturn(new HashSet<Act>()).once();
    
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

    }
 
}
