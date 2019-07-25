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
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.parameterization.ParameterElement;
import org.asqatasun.entity.service.parameterization.ParameterElementDataService;
import org.asqatasun.webapp.command.factory.AuditSetUpCommandFactory;
import org.asqatasun.webapp.entity.contract.Contract;
import org.asqatasun.webapp.entity.contract.ScopeEnum;
import org.asqatasun.webapp.entity.decorator.asqatasun.parameterization.ParameterDataServiceDecorator;
import org.asqatasun.webapp.entity.functionality.Functionality;
import org.asqatasun.webapp.entity.option.Option;
import org.asqatasun.webapp.entity.option.OptionElement;
import org.asqatasun.webapp.entity.referential.Referential;
import org.asqatasun.webapp.entity.service.contract.ActDataService;
import org.asqatasun.webapp.entity.service.contract.ContractDataService;
import org.asqatasun.webapp.entity.service.user.UserDataService;
import org.asqatasun.webapp.entity.user.User;
import org.asqatasun.webapp.exception.ForbiddenPageException;
import org.asqatasun.webapp.form.FormField;
import org.asqatasun.webapp.form.builder.*;
import org.asqatasun.webapp.form.parameterization.builder.AuditSetUpFormFieldBuilderImpl;
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
public class AuditSetUpControllerTest extends TestCase {

    private AuditSetUpController instance;

    Authentication mockAuthentication;
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
            mockAuthentication = null;
        }
        verify(mockActDataService);
        mockActDataService=null;
        if (mockUser != null) {
            verify(mockUser);
            mockUser=null;
        }
        if (mockUserDataService != null) {
            verify(mockUserDataService);
            mockUserDataService=null;
        }
        verify(mockContract);
        mockContract = null;
        verify(mockContractDataService);
        mockContractDataService=null;
        if (mockFunctionality != null) {
            verify(mockFunctionality);
            mockFunctionality=null;
        }
        if (mockReferential != null) {
            verify(mockReferential);
            mockReferential=null;
        }
        if (mockOption != null) {
            verify(mockOption);
            mockOption=null;
        }
        if (mockOptionElement != null) {
            verify(mockOptionElement);
            mockOptionElement=null;
        }
        verify(mockParameterElementDataService);
        mockParameterElementDataService=null;
        verify(mockParameterElementLevel);
        mockParameterElementLevel=null;
        verify(mockParameterElementTextualFormField);
        mockParameterElementTextualFormField=null;
        if (mockParameterDataService != null) {
            verify(mockParameterDataService);
            mockParameterDataService=null;
        }
        if (mockParameter1 != null) {
            verify(mockParameter1);
            mockParameter1=null;
        }
        if (mockParameter2 != null) {
            verify(mockParameter2);
            mockParameter2=null;
        }
    }
    
    public void testDisplayPageAuditPageSetUp() {
        System.out.println("testDisplayPageAuditPageSetUp");
        
        // set-up
        setUpMockUserDataServiceAndUser();
        setUpMockAuthenticationContext();
        setUpMockContractDataService(1,"Contract1");
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
        setUpMockContractDataService(1,"Contract1");
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
        setUpMockContractDataService(1,"Contract1");
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
        setUpMockAuthenticationContext();
        setUpMockContractDataService(1,"Contract1");
        
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
        setUpMockAuthenticationContext();
        setUpMockContractDataService(1,"Contract1");
        
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
        setUpMockAuthenticationContext();
        setUpMockContractDataService(1,"Contract1");
        
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
        setUpMockAuthenticationContext();
        setUpMockContractDataService(2,"Contract1");
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
        setUpMockAuthenticationContext();
        setUpMockContractDataService(2,"Contract1");
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
        setUpMockAuthenticationContext();
        setUpMockContractDataService(2,"Contract1");
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
    
    /**
     * 
     * @param contractId
     * @param contractLabel 
     */
    private void setUpMockContractDataService(
            int contractId, 
            String contractLabel) {
        
        mockContract = createMock(Contract.class);

        expect(mockContract.getUser()).andReturn(mockUser).anyTimes();
        expect(mockContract.getId()).andReturn(Long.valueOf(contractId)).anyTimes();
        expect(mockContract.getLabel()).andReturn(contractLabel).anyTimes();
        expect(mockContract.getFunctionalitySet()).andReturn(setUpMockFunctionalitySet()).anyTimes();
        expect(mockContract.getReferentialSet()).andReturn(setUpMockReferentialSet()).anyTimes();
        expect(mockContract.getOptionElementSet()).andReturn(setUpMockOptionElementSet()).anyTimes();
        mockContractDataService = createMock(ContractDataService.class);
        
        expect(mockContractDataService.read(Long.valueOf(contractId))).andReturn(mockContract).anyTimes();
        expect(mockContractDataService.getUrlFromContractOption(mockContract)).andReturn("http://www.test1.com").anyTimes();
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
        expect(mockOptionElement.getOption()).andReturn(mockOption).anyTimes();
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
            expect(mockParameterDataService.getDefaultLevelParameter()).andReturn(mockParameter1).anyTimes();
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
