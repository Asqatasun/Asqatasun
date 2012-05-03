/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import junit.framework.TestCase;
import org.opens.tanaguru.entity.service.parameterization.MockParameterDataService;
import org.opens.tanaguru.entity.service.parameterization.MockParameterElementDataService;
import org.opens.tanaguru.entity.service.parameterization.ParameterElementDataService;
import org.opens.tgol.command.factory.AuditSetUpCommandFactory;
import org.opens.tgol.entity.decorator.tanaguru.parameterization.ParameterDataServiceDecorator;
import org.opens.tgol.entity.service.contract.ContractDataService;
import org.opens.tgol.entity.service.contract.MockActDataService;
import org.opens.tgol.entity.service.contract.MockContractDataService;
import org.opens.tgol.entity.service.user.MockUserDataService;
import org.opens.tgol.entity.service.user.UserDataService;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.exception.ForbiddenPageException;
import org.opens.tgol.form.FormField;
import org.opens.tgol.form.builder.*;
import org.opens.tgol.form.parameterization.builder.AuditSetUpFormFieldBuilderImpl;
import org.opens.tgol.mock.MockAuthenticationDetails;
import org.opens.tgol.util.TgolKeyStore;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

/**
 *
 * @author jkowalczyk
 */
public class AuditSetUpControllerTest extends TestCase {

    private AuditSetUpController instance;
            
    public AuditSetUpControllerTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        instance = new AuditSetUpController();
        
        instance.setActDataService(new MockActDataService());
        
        UserDataService userDataService = new MockUserDataService();
        instance.setUserDataService(userDataService);
        
        // the AuditSetUpController needs a ContractDataService instance
        ContractDataService contractDataService = new MockContractDataService(); 
        ((MockContractDataService)contractDataService).setUserDataService(userDataService);
        instance.setContractDataService(contractDataService);
        
        // the AuditSetUpController needs a map to be set-up to determine whether
        // a user is allowed to display a set-up form regarding the functionalities
        // of the contract
        
        Map<String, String> viewFunctionalityBindingMap =  new HashMap<String, String>();
        // The contracts are initialised with a functionality whose code is 
        // FUNCTIONALITY1. We allow all the audit set-up forms for this 
        // functionality by populating the viewFunctionalityBindingMap
        viewFunctionalityBindingMap.put("audit-upload-set-up", "FUNCTIONALITY1");
        viewFunctionalityBindingMap.put("audit-site-set-up", "FUNCTIONALITY1");
        viewFunctionalityBindingMap.put("audit-page-set-up", "FUNCTIONALITY1");
        instance.setViewFunctionalityBindingMap(viewFunctionalityBindingMap);

        // The Controller needs a list of AuditSetUpFormFieldBuilder that 
        // deal with the referential and level selection
        instance.setReferentialAndLevelFormFieldBuilderList(buildMockRefAndLevelSelectFormFieldList());
        
        instance.setPageOptionFormFieldBuilderMap(buildMockOptionAuditSetUpFormFieldList());
        instance.setSiteOptionFormFieldBuilderMap(buildMockOptionAuditSetUpFormFieldList());
        instance.setUploadOptionFormFieldBuilderMap(buildMockOptionAuditSetUpFormFieldList());
        
        ParameterDataServiceDecorator parameterDataService =  new MockParameterDataService();
        instance.setParameterDataService(parameterDataService);
        
        // The AuditSetUpCommandFactory factory needs to be initialised with 
        // parameterDataService instance and contractDataService
        AuditSetUpCommandFactory.getInstance().setParameterDataService(parameterDataService);
        AuditSetUpCommandFactory.getInstance().setParameterElementDataService(new MockParameterElementDataService());
        AuditSetUpCommandFactory.getInstance().setContractDataService(contractDataService);
        
        // initialise the context with the user identified by the email 
        // "test1@test.com" seen as authenticated
        MockAuthenticationDetails.initSecurityContextHolder("test1@test.com");
    }
    
    public void testDisplayPageAuditSetUp() {
        MockAuthenticationDetails.initSecurityContextHolder("test1@test.com");        
        Model model = new ExtendedModelMap();

        String returnedView = instance.displayPageAuditSetUp("1", null, null, model);
        assertEquals(TgolKeyStore.AUDIT_PAGE_SET_UP_VIEW_NAME, returnedView);
        assertEquals("test1@test.com", ((User)model.asMap().get(TgolKeyStore.AUTHENTICATED_USER_KEY)).getEmail1());
        assertEquals("http://www.test1.com", model.asMap().get(TgolKeyStore.URL_KEY));
        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
        
        returnedView = instance.displaySiteAuditSetUp("1", null, null, model);
        assertEquals(TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME, returnedView);
        assertEquals("test1@test.com", ((User)model.asMap().get(TgolKeyStore.AUTHENTICATED_USER_KEY)).getEmail1());
        assertEquals("http://www.test1.com", model.asMap().get(TgolKeyStore.URL_KEY));
        assertEquals(true, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
        
        returnedView = instance.displayUploadAuditSetUp("1", null, null, model);
        assertEquals(TgolKeyStore.AUDIT_UPLOAD_SET_UP_VIEW_NAME, returnedView);
        assertEquals("test1@test.com", ((User)model.asMap().get(TgolKeyStore.AUTHENTICATED_USER_KEY)).getEmail1());
        assertEquals("http://www.test1.com", model.asMap().get(TgolKeyStore.URL_KEY));
        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));

        // the contract Id cannot be converted as a Long. An exception is caught
        try {
            instance.displaySiteAuditSetUp("Not a number", null, null, model);
            assertTrue(false);
        } catch (ForbiddenPageException fue) {
            assertTrue(true);
        }
        try {
            instance.displayPageAuditSetUp("Not a number", null, null, model);
            assertTrue(false);
        } catch (ForbiddenPageException fue) {
            assertTrue(true);
        }
        try {
            instance.displayUploadAuditSetUp("Not a number", null, null, model);
            assertTrue(false);
        } catch (ForbiddenPageException fue) {
            assertTrue(true);
        }
        
        // the functionality associated with the contract is not allowed 
        // regarding the viewFunctionalityBindingMap. An exception is caught
        try {
            instance.displayPageAuditSetUp("2", null, null, model);
            assertTrue(false);
        } catch (ForbiddenPageException fue) {
            assertTrue(true);
        }
        try {
            instance.displaySiteAuditSetUp("2", null, null, model);
            assertTrue(false);
        } catch (ForbiddenPageException fue) {
            assertTrue(true);
        }
        try {
            instance.displayUploadAuditSetUp("2", null, null, model);
            assertTrue(false);
        } catch (ForbiddenPageException fue) {
            assertTrue(true);
        }
     
        // TO DO : write test to control the integrity of data of the AuditSetUpCommand
        // regarding the option/functionality rules
        
    }

    /**
     * 
     * @return 
     */
    private Map<String, List<AuditSetUpFormFieldBuilderImpl>> buildMockOptionAuditSetUpFormFieldList() {
        
        AbstractGenericFormFieldBuilder<? extends FormField> mockFormFieldBuilder = 
                new TextualFormFieldBuilderImpl();

        AuditSetUpFormFieldBuilderImpl mockAuditSetUpFormFieldBuilder = 
                new AuditSetUpFormFieldBuilderImpl();
        mockAuditSetUpFormFieldBuilder.setFormFieldBuilder(mockFormFieldBuilder);
        mockAuditSetUpFormFieldBuilder.setParameterElementDataService(new MockParameterElementDataService());
        // to fit with the mock of the ParameterElementDataService
        mockAuditSetUpFormFieldBuilder.setParameterCode("TEXTUAL_FORMFIELD");
                
        List<AuditSetUpFormFieldBuilderImpl> mockAuditSetUpFormFieldBuilderList = 
                new ArrayList<AuditSetUpFormFieldBuilderImpl>();
        mockAuditSetUpFormFieldBuilderList.add(mockAuditSetUpFormFieldBuilder);
                
        Map<String, List<AuditSetUpFormFieldBuilderImpl>> mockOptionAuditSetUpFormFieldList = 
                new HashMap<String, List<AuditSetUpFormFieldBuilderImpl>>();
        mockOptionAuditSetUpFormFieldList.put("Option1", mockAuditSetUpFormFieldBuilderList);
        return mockOptionAuditSetUpFormFieldList;
    }
    
    /**
     * 
     * @return 
     */
    private List<SelectFormFieldBuilderImpl> buildMockRefAndLevelSelectFormFieldList(){
        
        SelectElementBuilder mockSelectElementBuilder = new SelectElementBuilderImpl();
        mockSelectElementBuilder.setValue("MockRef;MockLevel");
        
        // the mock select Element list
        List<SelectElementBuilder> selectElementBuilderList = new ArrayList<SelectElementBuilder>();
        selectElementBuilderList.add(mockSelectElementBuilder);
        
        Map<String,List<SelectElementBuilder>> selectElementBuilderMap = 
                new HashMap<String,List<SelectElementBuilder>>();
        
        selectElementBuilderMap.put("REFERENTIAL1", selectElementBuilderList);

        SelectFormFieldBuilderImpl selectFormFieldBuilder =  new SelectFormFieldBuilderImpl();
        selectFormFieldBuilder.setSelectElementBuilderMap(selectElementBuilderMap);
        
        List<SelectFormFieldBuilderImpl> mockSelectRefFormFieldList = 
                new ArrayList<SelectFormFieldBuilderImpl>();
        mockSelectRefFormFieldList.add(selectFormFieldBuilder);
        
        return mockSelectRefFormFieldList;
    }
    
}