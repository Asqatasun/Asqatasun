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

import java.util.*;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.service.parameterization.ParameterElementDataService;
import org.opens.tgol.command.factory.AuditSetUpCommandFactory;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.contract.ScopeEnum;
import org.opens.tgol.entity.decorator.tanaguru.parameterization.ParameterDataServiceDecorator;
import org.opens.tgol.entity.functionality.Functionality;
import org.opens.tgol.entity.option.Option;
import org.opens.tgol.entity.option.OptionElement;
import org.opens.tgol.entity.referential.Referential;
import org.opens.tgol.entity.service.contract.ActDataService;
import org.opens.tgol.entity.service.contract.ContractDataService;
import org.opens.tgol.entity.service.user.UserDataService;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.exception.ForbiddenPageException;
import org.opens.tgol.form.FormField;
import org.opens.tgol.form.builder.*;
import org.opens.tgol.form.parameterization.builder.AuditSetUpFormFieldBuilderImpl;
import org.opens.tgol.security.userdetails.TgolUserDetails;
import org.opens.tgol.util.TgolKeyStore;
import org.springframework.security.authentication.AuthenticationDetails;
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
public class AuditSetUpControllerTest extends TestCase {

    private AuditSetUpController instance;

    Authentication mockAuthentication;
    AuthenticationDetails mockAuthenticationDetails;
    ActDataService mockActDataService;
    User mockUser;
    UserDataService mockUserDataService;
    Contract mockContract;
    ContractDataService mockContractDataService;
    Functionality mockFunctionality;
    Referential mockReferential;
    Option mockOption;
    OptionElement mockOptionElement;
    
    ParameterElementDataService mockParameterElementDataService;
    ParameterElement mockParameterElementLevel;
    ParameterElement mockParameterElementTextualFormField;
    Parameter mockParameter1;
    Parameter mockParameter2;
    ParameterDataServiceDecorator mockParameterDataService;
    
    public AuditSetUpControllerTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        instance = new AuditSetUpController();
        setUpMockActDataService();
        setUpFormFieldBuilder();
    }
    
    @Override
    protected void tearDown() throws Exception {
        if (mockAuthentication != null) {
            verify(mockAuthentication);
        }
        if (mockAuthenticationDetails != null) {
            verify(mockAuthenticationDetails);
        }
        verify(mockActDataService);
        if (mockUser != null) {
            verify(mockUser);
        }
        if (mockUserDataService != null) {
            verify(mockUserDataService);
        }
        verify(mockContract);
        verify(mockContractDataService);
        if (mockFunctionality != null) {
            verify(mockFunctionality);
        }
        if (mockReferential != null) {
            verify(mockReferential);
        }
        if (mockOption != null) {
            verify(mockOption);
        }
        if (mockOptionElement != null) {
            verify(mockOptionElement);
        }
        verify(mockParameterElementDataService);
        verify(mockParameterElementLevel);
        verify(mockParameterElementTextualFormField);
        if (mockParameterDataService != null) {
            verify(mockParameterDataService);
        }
        if (mockParameter1 != null) {
            verify(mockParameter1);
        }
        if (mockParameter2 != null) {
            verify(mockParameter2);
        }
    }
    
    public void testDisplayPageAuditPageSetUp() {
        System.out.println("testDisplayPageAuditPageSetUp");
        
        // set-up
        setUpMockUserDataServiceAndUser();
        setUpMockAuthenticationContext();
        setUpMockContractDataService(2,1,1,"Contract1", 1,1,1,1,1,1,2);
        setUpViewFunctionalityBindingMap();
        setUpAuditSetUpCommandFactory();
        Model model = new ExtendedModelMap();
        
        // test
        String returnedView = instance.displayPageAuditSetUp("1", null, null, model);
        
        // assertions
        assertEquals(TgolKeyStore.AUDIT_PAGE_SET_UP_VIEW_NAME, returnedView);
        assertEquals("http://www.test1.com", model.asMap().get(TgolKeyStore.URL_KEY));
        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));

        // TO DO : write test to control the integrity of data of the AuditSetUpCommand
        // regarding the option/functionality rules
        
    }

    public void testDisplayPageAuditSiteSetUp() {
        System.out.println("testDisplayPageAuditSiteSetUp");
        
        // Set-up
        setUpMockUserDataServiceAndUser();
        setUpMockAuthenticationContext();
        setUpMockContractDataService(1,3,1,"Contract1",1,1,1,1,1,1,1);
        setUpViewFunctionalityBindingMap();
        setUpAuditSetUpCommandFactory();
        Model model = new ExtendedModelMap();
        
        String returnedView = instance.displaySiteAuditSetUp("1", null, null, model);
        
        // assertions
        assertEquals(TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME, returnedView);
        assertEquals("http://www.test1.com", model.asMap().get(TgolKeyStore.URL_KEY));
        assertEquals(true, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
    }
    
    public void testDisplayPageAuditUploadSetUp() {
        System.out.println("testDisplayPageAuditUploadSetUp"); 
        
        // Set-up
        setUpMockUserDataServiceAndUser();
        setUpMockAuthenticationContext();
        setUpMockContractDataService(2,1,1,"Contract1", 1,1,1,1,1,1,1);
        setUpViewFunctionalityBindingMap();
        setUpAuditSetUpCommandFactory();
        Model model = new ExtendedModelMap();
        
        // test
        String returnedView = instance.displayUploadAuditSetUp("1", null, null, model);
        
        // assertions
        assertEquals(TgolKeyStore.AUDIT_UPLOAD_SET_UP_VIEW_NAME, returnedView);
        assertEquals("http://www.test1.com", model.asMap().get(TgolKeyStore.URL_KEY));
        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
    }
    
    public void testDisplayPageAuditPageSetUpWithWrongContractId() {
        System.out.println("testDisplayPageAuditPageSetUpWithWrongContractId");
        
        // set-up
        setUpMockUserDataServiceAndUser();
        setUpMockContractDataService(0,0,1,"Contract1", 0,0,0,0,1,0,0);
        
        // the contract Id cannot be converted as a Long. An exception is caught
        try {
            instance.displayPageAuditSetUp("Not a number", null, null, new ExtendedModelMap());
            assertTrue(false);
        } catch (ForbiddenPageException fue) {
            assertTrue(true);
        }
    }
    
    public void testDisplayPageAuditUploadSetUpWithWrongContractId() {
        System.out.println("testDisplayPageAuditUploadSetUpWithWrongContractId");
        
        // set-up
        setUpMockUserDataServiceAndUser();
        setUpMockContractDataService(0,0,1,"Contract1", 0,0,0,0,1,0,0);
        
        // the contract Id cannot be converted as a Long. An exception is caught
        try {
            instance.displayUploadAuditSetUp("Not a number", null, null, new ExtendedModelMap());
            assertTrue(false);
        } catch (ForbiddenPageException fue) {
            assertTrue(true);
        }
    }
    
    public void testDisplayPageAuditSiteSetUpWithWrongContractId() {
        System.out.println("testDisplayPageAuditSiteSetUpWithWrongContractId");

        // set-up
        setUpMockUserDataServiceAndUser();
        setUpMockContractDataService(0,0,1,"Contract1", 0,0,0,0,1,0,0);
        
        // the contract Id cannot be converted as a Long. An exception is caught
        try {
            instance.displaySiteAuditSetUp("Not a number", null, null, new ExtendedModelMap());
            assertTrue(false);
        } catch (ForbiddenPageException fue) {
            assertTrue(true);
        }
    }
    
    public void testDisplayPageAuditPageSetUpWithUnauthorisedFunctionality() {
        System.out.println("testDisplayPageAuditPageSetUpWithUnauthorisedFunctionality");
        
        setUpMockUserDataServiceAndUser();
        setUpMockContractDataService(1,0,2,"Contract1", 0,1,0,0,2,1,0);
        setUpEmptyViewFunctionalityBindingMap();
        
        // the functionality associated with the contract is not allowed 
        // regarding the viewFunctionalityBindingMap. An exception is caught
        try {
            instance.displayPageAuditSetUp("2", null, null, new ExtendedModelMap());
            assertTrue(false);
        } catch (ForbiddenPageException fue) {
            assertTrue(true);
        }
    }
    
    public void testDisplayPageAuditSiteSetUpWithUnauthorisedFunctionality() {
        System.out.println("testDisplayPageAuditSiteSetUpWithUnauthorisedFunctionality");
        
        setUpMockUserDataServiceAndUser();
        setUpMockContractDataService(1,0,2,"Contract1", 0,1,0,0,2,1,0);
        setUpEmptyViewFunctionalityBindingMap();

        // the functionality associated with the contract is not allowed 
        // regarding the viewFunctionalityBindingMap. An exception is caught
        try {
            instance.displaySiteAuditSetUp("2", null, null, new ExtendedModelMap());
            assertTrue(false);
        } catch (ForbiddenPageException fue) {
            assertTrue(true);
        }
    }
    
    public void testDisplayPageAuditUploadSetUpWithUnauthorisedFunctionality() {
        System.out.println("testDisplayPageAuditUploadSetUpWithUnauthorisedFunctionality");
        
        setUpMockUserDataServiceAndUser();
        setUpMockContractDataService(1,0,2,"Contract1", 0,1,0,0,2,1,0);
        setUpEmptyViewFunctionalityBindingMap();
        
        // the functionality associated with the contract is not allowed 
        // regarding the viewFunctionalityBindingMap. An exception is caught
        try {
            instance.displayUploadAuditSetUp("2", null, null, new ExtendedModelMap());
            assertTrue(false);
        } catch (ForbiddenPageException fue) {
            assertTrue(true);
        }
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
    
    private void setUpMockActDataService(){
        mockActDataService = createMock(ActDataService.class);
        instance.setActDataService(mockActDataService);
        replay(mockActDataService);
    }
    
    private void setUpMockUserDataServiceAndUser() {
        mockUser = createMock(User.class);

        expect(mockUser.getId()).andReturn(Long.valueOf(1)).anyTimes();
        expect(mockUser.getEmail1()).andReturn("test1@test.com").anyTimes();
        expect(mockUser.getName()).andReturn("").anyTimes();
        expect(mockUser.getFirstName()).andReturn("").anyTimes();
        
        mockUserDataService = createMock(UserDataService.class);

        replay(mockUser);
        replay(mockUserDataService);
        instance.setUserDataService(mockUserDataService);
    }
    
    private void setUpMockContractDataService(
            int getContractUserCount, 
            int getContractIdCount,
            int getContractIdValue, 
            String contractLabel,
            int getContractLabelCount, 
            int getFunctionalitySetCount, 
            int getReferentialSetCount, 
            int getOptionElementSetCount, 
            int readContractId, 
            int getReadContractIdCount, 
            int getUrlFromContractOption) {
        
        mockContract = createMock(Contract.class);

        if (getContractUserCount > 0) {
            expect(mockContract.getUser()).andReturn(mockUser).times(getContractUserCount);
        }
        if (getContractIdCount > 0) {
            expect(mockContract.getId()).andReturn(Long.valueOf(getContractIdValue)).times(getContractIdCount);
        }
        if (getContractLabelCount > 0) {
            expect(mockContract.getLabel()).andReturn(contractLabel).times(getContractLabelCount);
        }
        if (getFunctionalitySetCount > 0) {
            expect(mockContract.getFunctionalitySet()).andReturn(setUpMockFunctionalitySet()).times(getFunctionalitySetCount);
        }
        if (getReferentialSetCount > 0) {
            expect(mockContract.getReferentialSet()).andReturn(setUpMockReferentialSet()).times(getReferentialSetCount);
        }
        if (getOptionElementSetCount > 0) {
            expect(mockContract.getOptionElementSet()).andReturn(setUpMockOptionElementSet()).times(getOptionElementSetCount);
        }
        mockContractDataService = createMock(ContractDataService.class);
        
        if (getReadContractIdCount > 0) {
            expect(mockContractDataService.read(Long.valueOf(readContractId))).andReturn(mockContract).times(getReadContractIdCount);
        }
        if (getUrlFromContractOption > 0) {
            expect(mockContractDataService.getUrlFromContractOption(mockContract)).andReturn("http://www.test1.com").times(getUrlFromContractOption);
        }
        replay(mockContract);
        replay(mockContractDataService);
        instance.setContractDataService(mockContractDataService);
    }
    
    private Set<Functionality> setUpMockFunctionalitySet() {
        mockFunctionality = createMock(Functionality.class);
        expect(mockFunctionality.getCode()).andReturn("FUNCTIONALITY1").anyTimes();
        Set<Functionality> mockFunctionalitySet = new HashSet<Functionality>();
        mockFunctionalitySet.add(mockFunctionality);
        replay(mockFunctionality);
        return mockFunctionalitySet;
    }
    
    private Set<Referential> setUpMockReferentialSet() {
        mockReferential = createMock(Referential.class);
        expect(mockReferential.getCode()).andReturn("").anyTimes();
        Set<Referential> mockReferentialSet = new HashSet<Referential>();
        mockReferentialSet.add(mockReferential);
        replay(mockReferential);
        return mockReferentialSet;
    }
    
    private Set<OptionElement> setUpMockOptionElementSet() {
        mockOption = createMock(Option.class);
        mockOptionElement = createMock(OptionElement.class);
        expect(mockOptionElement.getOption()).andReturn(mockOption);
        expect(mockOption.getCode()).andReturn("").anyTimes();
        
        Set<OptionElement> mockOptionElementSet = new HashSet<OptionElement>();
        mockOptionElementSet.add(mockOptionElement);
        
        replay(mockOptionElement);
        replay(mockOption);
        
        return mockOptionElementSet;
    }

    /**
     * the AuditSetUpController needs a map to be set-up to determine whether
     * a user is allowed to display a set-up form regarding the functionalities
     * of the contract
     */
    private void setUpViewFunctionalityBindingMap() {
        Map<String, String> viewFunctionalityBindingMap =  new HashMap<String, String>();
        // The contracts are initialised with a functionality whose code is 
        // FUNCTIONALITY1. We allow all the audit set-up forms for this 
        // functionality by populating the viewFunctionalityBindingMap
        viewFunctionalityBindingMap.put("audit-upload-set-up", "FUNCTIONALITY1");
        viewFunctionalityBindingMap.put("audit-site-set-up", "FUNCTIONALITY1");
        viewFunctionalityBindingMap.put("audit-page-set-up", "FUNCTIONALITY1");
        instance.setViewFunctionalityBindingMap(viewFunctionalityBindingMap);
    }
    
    /**
     * the AuditSetUpController needs a map to be set-up to determine whether
     * a user is allowed to display a set-up form regarding the functionalities
     * of the contract
     */
    private void setUpEmptyViewFunctionalityBindingMap() {
        Map<String, String> viewFunctionalityBindingMap =  new HashMap<String, String>();
        instance.setViewFunctionalityBindingMap(viewFunctionalityBindingMap);
    }
     
    /**
     * The Controller needs a list of AuditSetUpFormFieldBuilder that 
     * deal with the referential and level selection
     */
    private void setUpFormFieldBuilder() {
        instance.setReferentialAndLevelFormFieldBuilderList(buildMockRefAndLevelSelectFormFieldList());
        instance.setPageOptionFormFieldBuilderMap(buildMockOptionAuditSetUpFormFieldList());
        instance.setSiteOptionFormFieldBuilderMap(buildMockOptionAuditSetUpFormFieldList());
        instance.setUploadOptionFormFieldBuilderMap(buildMockOptionAuditSetUpFormFieldList());
    }
    
    /**
     * The AuditSetUpCommandFactory factory needs to be initialised with 
     * parameterDataService instance and contractDataService
     */
    private void setUpAuditSetUpCommandFactory() {
        AuditSetUpCommandFactory.getInstance().setParameterDataService(getParameterDataService());
        AuditSetUpCommandFactory.getInstance().setParameterElementDataService(getParameterElementDataService());
        AuditSetUpCommandFactory.getInstance().setContractDataService(mockContractDataService);
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

        ParameterElementDataService parameterElementDataService = 
                getParameterElementDataService();

        mockAuditSetUpFormFieldBuilder.setParameterElementDataService(
                parameterElementDataService);
        
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
    
    private ParameterElementDataService getParameterElementDataService() {
        if (mockParameterElementDataService == null && 
                mockParameterElementTextualFormField == null && 
                mockParameterElementLevel == null) {
            mockParameterElementDataService = createMock(ParameterElementDataService.class);
            mockParameterElementTextualFormField = createMock(ParameterElement.class);
            mockParameterElementLevel = createMock(ParameterElement.class);
            expect(mockParameterElementDataService.getParameterElement("TEXTUAL_FORMFIELD")).andReturn(mockParameterElementTextualFormField).anyTimes();
            expect(mockParameterElementDataService.getParameterElement("LEVEL")).andReturn(mockParameterElementLevel).anyTimes();
            expect(mockParameterElementTextualFormField.getParameterElementCode()).andReturn("TEXTUAL_FORMFIELD").anyTimes();
            expect(mockParameterElementLevel.getParameterElementCode()).andReturn("LEVEL").anyTimes();
            replay(mockParameterElementDataService);
            replay(mockParameterElementLevel);
            replay(mockParameterElementTextualFormField);
        }
        return mockParameterElementDataService;
    }
    
    private ParameterDataServiceDecorator getParameterDataService() {
        if (mockParameterDataService == null) {
            mockParameter1 =  createMock(Parameter.class);
            mockParameter2 =  createMock(Parameter.class);
            expect(mockParameter1.getValue()).andReturn("PARAMETER1").anyTimes();
            expect(mockParameter2.getValue()).andReturn("PARAMETER2").anyTimes();
            Set<Parameter> paramSet = new HashSet<Parameter>();
            paramSet.add(mockParameter1);
            paramSet.add(mockParameter2);
            replay(mockParameter1);
            replay(mockParameter2);

            mockParameterDataService =  createMock(ParameterDataServiceDecorator.class);
            expect(mockParameterDataService.getDefaultParameterSet()).andReturn(paramSet).anyTimes();
            expect(mockParameterDataService.getDefaultParameter(mockParameterElementLevel)).andReturn(mockParameter1).anyTimes();
            expect(mockParameterDataService.getDefaultParameter(mockParameterElementTextualFormField)).andReturn(mockParameter2).anyTimes();
            expect(mockParameterDataService.getLastParameterValueFromUser(
                    Long.valueOf(1), 
                    mockParameterElementLevel, 
                    ScopeEnum.DOMAIN)).andReturn("PARAMETER1").anyTimes();
            expect(mockParameterDataService.getLastParameterValueFromUser(
                    Long.valueOf(2), 
                    mockParameterElementLevel, 
                    ScopeEnum.DOMAIN)).andReturn("PARAMETER1").anyTimes();
            expect(mockParameterDataService.getLastParameterValueFromUser(
                    Long.valueOf(1), 
                    mockParameterElementTextualFormField, 
                    ScopeEnum.DOMAIN)).andReturn("PARAMETER2").anyTimes();
            expect(mockParameterDataService.getLastParameterValueFromUser(
                    Long.valueOf(2), 
                    mockParameterElementTextualFormField, 
                    ScopeEnum.DOMAIN)).andReturn("PARAMETER2").anyTimes();
            replay(mockParameterDataService);
            instance.setParameterDataService(mockParameterDataService);
        }
        return mockParameterDataService;
    }
    /**
     * The auditSetUp view is set up with an element to select the referential 
     * and level. This element is enabled only if the contract is set up with 
     * this referential (code value for this referential is REFERENTIAL1)
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