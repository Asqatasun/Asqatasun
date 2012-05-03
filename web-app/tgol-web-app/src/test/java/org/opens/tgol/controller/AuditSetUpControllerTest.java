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

//    private static final String SPRING_FILE_PATH =
//            "src/test/resources/unit-test-context.xml";
    private AuditSetUpController instance;
    private ParameterElementDataService parameterElementDataService;
            
    public AuditSetUpControllerTest(String testName) {
        super(testName);
//        ApplicationContext springApplicationContext =
//                new FileSystemXmlApplicationContext(SPRING_FILE_PATH);
//        springBeanFactory = springApplicationContext;
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
        
        ParameterDataServiceDecorator parameterDataService =  new MockParameterDataService();
        instance.setParameterDataService(parameterDataService);
        
        parameterElementDataService = 
                new MockParameterElementDataService();
        
        AuditSetUpCommandFactory.getInstance().setParameterDataService(parameterDataService);
        AuditSetUpCommandFactory.getInstance().setContractDataService(contractDataService);
        
        MockAuthenticationDetails.initSecurityContextHolder("test1@test.com");
        
        buildParametersMap();
    }
    
    public void testDisplayPageAuditSetUp() {
        MockAuthenticationDetails.initSecurityContextHolder("test1@test.com");        
        Model model = new ExtendedModelMap();

        // The contract 1 is of DOMAIN type. The set-up page for a page audit
        // is allowed
//        String returnedView = instance.displayPageAuditSetUp("1", null, null, model);
//        assertEquals(TgolKeyStore.AUDIT_PAGE_SET_UP_VIEW_NAME, returnedView);
//        assertEquals("test1@test.com", ((User)model.asMap().get(TgolKeyStore.AUTHENTICATED_USER_KEY)).getEmail1());
//        assertEquals("http://www.test1.com", model.asMap().get(TgolKeyStore.URL_KEY));
//        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));

//        // The contract 2 is of DOMAIN type. The set-up page for a site audit
//        // is allowed. For a group of pages audit, the default parameter set attribute
//        // is always set to false due to DEPTH and MAX_DOCUMENTS parameters
//        // modifications
//        returnedView = instance.displayPageAuditSetUp("2", null, null, model);
//        assertEquals("test1@test.com", ((User)model.asMap().get(TgolKeyStore.AUTHENTICATED_USER_KEY)).getEmail1());
//        assertEquals("http://www.test2.com", model.asMap().get(TgolKeyStore.URL_KEY));
//        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
//        assertEquals(returnedView, TgolKeyStore.AUDIT_PAGE_SET_UP_VIEW_NAME);
//
//        // the contract has a GROUPOFPAGES scope.The access is denied
//        returnedView = instance.displayPageAuditSetUp("9", null, null, model);
//        assertEquals("test1@test.com", ((User)model.asMap().get(TgolKeyStore.AUTHENTICATED_USER_KEY)).getEmail1());
//        assertEquals("http://www.test9.com", model.asMap().get(TgolKeyStore.URL_KEY));
//        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
//        assertEquals(returnedView, TgolKeyStore.AUDIT_PAGE_SET_UP_VIEW_NAME);
//        
//        // the contract is not associated with the current user. The access is denied
//        MockAuthenticationDetails.initSecurityContextHolder("test2@test.com");
//        try {
//            instance.displayPageAuditSetUp("1", null, null, model);
//            assertTrue(false);
//        } catch (ForbiddenUserException fue) {
//            assertTrue(true);
//        }
////        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
//        try {
//            instance.displayPageAuditSetUp("2", null, null, model);
//            assertTrue(false);
//        } catch (ForbiddenUserException fue) {
//            assertTrue(true);
//        }
////        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
//        try {
//            instance.displayPageAuditSetUp("3", null, null, model);
//            assertTrue(false);
//        } catch (ForbiddenUserException fue) {
//            assertTrue(true);
//        }
////        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
//        try {
//            instance.displayPageAuditSetUp("4", null, null, model);
//            assertTrue(false);
//        } catch (ForbiddenUserException fue) {
//            assertTrue(true);
//        }
////        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
//        try {
//            instance.displayPageAuditSetUp("5", null, null, model);
//            assertTrue(false);
//        } catch (ForbiddenUserException fue) {
//            assertTrue(true);
//        }
////        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
//        try {
//            instance.displayPageAuditSetUp("6", null, null, model);
//            assertTrue(false);
//        } catch (ForbiddenUserException fue) {
//            assertTrue(true);
//        }
////        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
//        try {
//            instance.displayPageAuditSetUp("7", null, null, model);
//            assertTrue(false);
//        } catch (ForbiddenUserException fue) {
//            assertTrue(true);
//        }
////        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
//        try {
//            instance.displayPageAuditSetUp("8", null, null, model);
//            assertTrue(false);
//        } catch (ForbiddenUserException fue) {
//            assertTrue(true);
//        }
////        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
//        try {
//            instance.displayPageAuditSetUp("9", null, null, model);
//            assertTrue(false);
//        } catch (ForbiddenUserException fue) {
//            assertTrue(true);
//        }
//        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
    }

//    public void testDisplaySiteAuditSetUp() {
//        
//        Model model = new ExtendedModelMap();
//        
//        // The contract 1 is of DOMAIN type. The set-up page for a site audit
//        // is allowed
//        String returnedView = instance.displaySiteAuditSetUp("1", null, null, model);
//        assertEquals(TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME, returnedView);
//        assertEquals("test1@test.com", ((User)model.asMap().get(TgolKeyStore.AUTHENTICATED_USER_KEY)).getEmail1());
//        assertEquals("http://www.test1.com", model.asMap().get(TgolKeyStore.URL_KEY));
//        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
//
//        // The contract 2 is of DOMAIN type. The set-up page for a site audit
//        // is allowed
//        returnedView = instance.displaySiteAuditSetUp("2", null, null, model);
//        assertEquals(true, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
//        assertEquals("http://www.test2.com", model.asMap().get(TgolKeyStore.URL_KEY));
//        assertEquals(TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME, returnedView);
//        for (List<AuditSetUpFormField> asuffList : ((Map<String, List<AuditSetUpFormField>>)model.asMap().get(TgolKeyStore.PARAMETERS_MAP_KEY)).values()){
//            for (AuditSetUpFormField asuff : asuffList) {
//                if (asuff.getFormField() instanceof SelectFormField) {
//                    // a select form is set up with a restriction option. Initialy
//                        // all the select elements are enabled
//                    SelectFormField selectFormField= ((SelectFormField)asuff.getFormField());
//                    if (StringUtils.equals(selectFormField.getRestrictionCode(), "FORBIDDEN_REFERENTIAL")) {
//                        if (asuff.getFormField() instanceof SelectFormField) {
//                            List<SelectElement> seoSelectElementList = selectFormField.getSelectElementMap().get("Seo");
//                            for (SelectElement se : seoSelectElementList) {
//                                assertEquals(true, se.getEnabled());
//                            }
//                            List<SelectElement> aw21SelectElementList = selectFormField.getSelectElementMap().get("AW21");
//                            for (SelectElement se : aw21SelectElementList) {
//                                assertEquals(true, se.getEnabled());
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        // The contract 3 is of DOMAIN type. The set-up page for a site audit
//        // is allowed
//        returnedView = instance.displaySiteAuditSetUp("3", null, null, model);
//        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
//        assertEquals(TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME, returnedView);
//        assertEquals("http://www.test3.com", model.asMap().get(TgolKeyStore.URL_KEY));
//        for (List<AuditSetUpFormField> asuffList : ((Map<String, List<AuditSetUpFormField>>)model.asMap().get(TgolKeyStore.PARAMETERS_MAP_KEY)).values()){
//            for (AuditSetUpFormField asuff : asuffList) {
//                if (asuff.getFormField() instanceof SelectFormField) {
//                    // a select form is set up with a restriction option. Initialy
//                    // all the select elements are enabled
//                    SelectFormField selectFormField= ((SelectFormField)asuff.getFormField());
//                    if (StringUtils.equals(selectFormField.getRestrictionCode(), "FORBIDDEN_REFERENTIAL")) {
//                        List<SelectElement> seoSelectElementList = selectFormField.getSelectElementMap().get("Seo");
//                        for (SelectElement se : seoSelectElementList) {
//                            assertEquals(false, se.getEnabled());
//                            assertEquals(false, se.getDefault());
//                        }
//                        List<SelectElement> aw21SelectElementList = selectFormField.getSelectElementMap().get("AW21");
//                        for (SelectElement se : aw21SelectElementList) {
//                            assertEquals(true, se.getEnabled());
//                            // for this contract, no DEFAULT_LEVEL option is set.
//                            // The behaviour is to apply the default value to 
//                            // first element of the SelectFormField
//                            if (se.getValue().equals("AW21;Bz")) {
//                                assertEquals(true, se.getDefault());
//                            } else {
//                                assertEquals(false, se.getDefault());
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        // The contract 4 is of DOMAIN type. The set-up page for a site audit
//        // is allowed
//        returnedView = instance.displaySiteAuditSetUp("4", null, null, model);
//        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
//        assertEquals(TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME, returnedView);
//        assertEquals("http://www.test4.com", model.asMap().get(TgolKeyStore.URL_KEY));
//        for (List<AuditSetUpFormField> asuffList : ((Map<String, List<AuditSetUpFormField>>)model.asMap().get(TgolKeyStore.PARAMETERS_MAP_KEY)).values()){
//            for (AuditSetUpFormField asuff : asuffList) {
//                if (asuff.getFormField() instanceof SelectFormField) {
//                    // a select form is set up with a restriction option. Initialy
//                    // all the select elements are enabled
//                    SelectFormField selectFormField= ((SelectFormField)asuff.getFormField());
//                    if (StringUtils.equals(selectFormField.getRestrictionCode(), "FORBIDDEN_REFERENTIAL")) {
//                        List<SelectElement> seoSelectElementList = selectFormField.getSelectElementMap().get("Seo");
//                        for (SelectElement se : seoSelectElementList) {
//                            assertEquals(false, se.getEnabled());
//                            assertEquals(false, se.getDefault());
//                        }
//                        List<SelectElement> aw21SelectElementList = selectFormField.getSelectElementMap().get("AW21");
//                        for (SelectElement se : aw21SelectElementList) {
//                            // In this case all the elements are disables by restriction.
//                            // No Element is defined as default
//                            assertEquals(false, se.getEnabled());
//                            assertEquals(false, se.getDefault());
//                        }
//                    }
//                }
//            }
//        }
//
//        // The contract 5 is of DOMAIN type. The set-up page for a site audit
//        // is allowed
//        returnedView = instance.displaySiteAuditSetUp("5", null, null, model);
//        assertEquals(true, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
//        assertEquals(TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME, returnedView);
//        assertEquals("http://www.test5.com", model.asMap().get(TgolKeyStore.URL_KEY));
//        for (List<AuditSetUpFormField> asuffList : ((Map<String, List<AuditSetUpFormField>>)model.asMap().get(TgolKeyStore.PARAMETERS_MAP_KEY)).values()){
//            for (AuditSetUpFormField asuff : asuffList) {
//                if (asuff.getParameterElement().getParameterElementCode().equals("MAX_DOCUMENTS")
//                        && asuff.getFormField() instanceof NumericalFormField) {
//                    assertEquals("2",((NumericalFormField)asuff.getFormField()).getMaxValue());
//                }
//            }
//        }
//
//        // The contract 6 is of DOMAIN type. The set-up page for a site audit
//        // is allowed
//        returnedView = instance.displaySiteAuditSetUp("6", null, null, model);
//        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
//        assertEquals(TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME, returnedView);
//        assertEquals("http://www.test6.com", model.asMap().get(TgolKeyStore.URL_KEY));
//        for (List<AuditSetUpFormField> asuffList : ((Map<String, List<AuditSetUpFormField>>)model.asMap().get(TgolKeyStore.PARAMETERS_MAP_KEY)).values()){
//            for (AuditSetUpFormField asuff : asuffList) {
//                if (asuff.getParameterElement().getParameterElementCode().equals("MAX_DURATION")
//                        && asuff.getFormField() instanceof NumericalFormField) {
//                    assertEquals("20",((NumericalFormField)asuff.getFormField()).getMaxValue());
//                }
//            }
//        }
//
//        // The contract 7 is of DOMAIN type. The set-up page for a site audit
//        // is allowed
//        returnedView = instance.displaySiteAuditSetUp("7", null, null, model);
//        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
//        assertEquals(TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME, returnedView);
//        assertEquals("http://www.test7.com", model.asMap().get(TgolKeyStore.URL_KEY));
//        for (List<AuditSetUpFormField> asuffList : ((Map<String, List<AuditSetUpFormField>>)model.asMap().get(TgolKeyStore.PARAMETERS_MAP_KEY)).values()){
//            for (AuditSetUpFormField asuff : asuffList) {
//                if (asuff.getParameterElement().getParameterElementCode().equals("DEPTH")
//                        && asuff.getFormField() instanceof NumericalFormField) {
//                    assertEquals("5",((NumericalFormField)asuff.getFormField()).getMaxValue());
//                }
//            }
//        }
//
//        // The contract 8 is of DOMAIN type. The set-up page for a site audit
//        // is allowed
//        returnedView = instance.displaySiteAuditSetUp("8", null, null, model);
//        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
//        assertEquals(TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME, returnedView);
//        assertEquals("http://www.test8.com", model.asMap().get(TgolKeyStore.URL_KEY));
//        for (List<AuditSetUpFormField> asuffList : ((Map<String, List<AuditSetUpFormField>>)model.asMap().get(TgolKeyStore.PARAMETERS_MAP_KEY)).values()){
//            for (AuditSetUpFormField asuff : asuffList) {
//                if (asuff.getParameterElement().getParameterElementCode().equals("EXCLUSION_REGEXP")
//                        && asuff.getFormField() instanceof TextualFormField) {
//                    assertEquals("test_expression",((TextualFormField)asuff.getFormField()).getValue());
//                }
//            }
//        }
//
//        // The contract 10 is of DOMAIN type. The set-up page for a site audit 
//        // is allowed with AW21 activated and Seo disabled
//        returnedView = instance.displaySiteAuditSetUp("10", null, null, model);
//        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
//        assertEquals(TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME, returnedView);
//        assertEquals("http://www.test10.com", model.asMap().get(TgolKeyStore.URL_KEY));
//        for (List<AuditSetUpFormField> asuffList : ((Map<String, List<AuditSetUpFormField>>)model.asMap().get(TgolKeyStore.PARAMETERS_MAP_KEY)).values()){
//            for (AuditSetUpFormField asuff : asuffList) {
//                if (asuff.getFormField() instanceof SelectFormField) {
//                    SelectFormField selectFormField= ((SelectFormField)asuff.getFormField());
//                    // a select form is set up with an activation option. Initialy
//                    // all the select elements are disabled
//                    if (StringUtils.equals(selectFormField.getActivationCode(), "AUTHORIZED_REFERENTIAL")) {
//                        List<SelectElement> seoSelectElementList = ((SelectFormField)asuff.getFormField()).getSelectElementMap().get("Seo");
//                        for (SelectElement se : seoSelectElementList) {
//                            assertEquals(false, se.getEnabled());
//                            assertEquals(false, se.getDefault());
//                        }
//                        List<SelectElement> aw21SelectElementList = ((SelectFormField)asuff.getFormField()).getSelectElementMap().get("AW21");
//                        for (SelectElement se : aw21SelectElementList) {
//                            assertEquals(true, se.getEnabled());
//                            // the DEFAULT_LEVEL option is applied on an SelectElement
//                            // which is disabled. So the default value is to 
//                            // the first enabled SelectElement encountered
//                            if (se.getValue().equals("AW21;Bz")) {
//                                assertEquals(true, se.getDefault());
//                            } else {
//                                assertEquals(false, se.getDefault());
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        
//        // The contract 11 is of DOMAIN type. The set-up page for a site audit 
//        // is allowed with AW21 activated and Seo disabled
//        returnedView = instance.displaySiteAuditSetUp("11", null, null, model);
//        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
//        assertEquals(TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME, returnedView);
//        assertEquals("http://www.test11.com", model.asMap().get(TgolKeyStore.URL_KEY));
//        for (List<AuditSetUpFormField> asuffList : ((Map<String, List<AuditSetUpFormField>>)model.asMap().get(TgolKeyStore.PARAMETERS_MAP_KEY)).values()){
//            for (AuditSetUpFormField asuff : asuffList) {
//                if (asuff.getFormField() instanceof SelectFormField) {
//                    SelectFormField selectFormField= ((SelectFormField)asuff.getFormField());
//                    if (StringUtils.equals(selectFormField.getActivationCode(), "AUTHORIZED_REFERENTIAL")) {
//                        List<SelectElement> seoSelectElementList = selectFormField.getSelectElementMap().get("Seo");
//                        for (SelectElement se : seoSelectElementList) {
//                            assertEquals(true, se.getEnabled());
//                            // The DEFAULT_LEVEL is set to "Seo;Ar"
//                            if (se.getValue().equals("Seo;Ar")) {
//                                assertEquals(true, se.getDefault());
//                            } else {
//                                assertEquals(false, se.getDefault());
//                            }
//                        }
//                        List<SelectElement> aw21SelectElementList = selectFormField.getSelectElementMap().get("AW21");
//                        for (SelectElement se : aw21SelectElementList) {
//                            assertEquals(false, se.getEnabled());
//                            assertEquals(false, se.getDefault());
//                        }
//                    }
//                }
//            }
//        }
//        
//        // The contract 12 is of DOMAIN type. The set-up page for a site audit 
//        // is allowed with AW21 activated and Seo enabled
//        returnedView = instance.displaySiteAuditSetUp("12", null, null, model);
//        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
//        assertEquals(TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME, returnedView);
//        assertEquals("http://www.test12.com", model.asMap().get(TgolKeyStore.URL_KEY));
//        for (List<AuditSetUpFormField> asuffList : ((Map<String, List<AuditSetUpFormField>>)model.asMap().get(TgolKeyStore.PARAMETERS_MAP_KEY)).values()){
//            for (AuditSetUpFormField asuff : asuffList) {
//                if (asuff.getFormField() instanceof SelectFormField) {
//                    SelectFormField selectFormField= ((SelectFormField)asuff.getFormField());
//                    if (StringUtils.equals(selectFormField.getActivationCode(), "AUTHORIZED_REFERENTIAL")) {
//                        List<SelectElement> seoSelectElementList = selectFormField.getSelectElementMap().get("Seo");
//                        for (SelectElement se : seoSelectElementList) {
//                            assertEquals(true, se.getEnabled());
//                            assertEquals(false, se.getDefault());
//                        }
//                        List<SelectElement> aw21SelectElementList = selectFormField.getSelectElementMap().get("AW21");
//                        for (SelectElement se : aw21SelectElementList) {
//                            assertEquals(true, se.getEnabled());
//                            // The DEFAULT_OPTION is set to the AW21;Or selectElement
//                            if (se.getValue().equals("AW21;Or")) {
//                                assertEquals(true, se.getDefault());
//                            } else {
//                                assertEquals(false, se.getDefault());
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        
//        // the contract has a GROUPOFPAGES scope.The access is denied
//        returnedView = instance.displaySiteAuditSetUp("9", null, null, model);
//        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
//        
//        // the contracts are not associated with the current user. The access is denied
//        MockAuthenticationDetails.initSecurityContextHolder("test2@test.com");
//        try {
//            instance.displaySiteAuditSetUp("1", null, null, model);
//            assertTrue(false);
//        } catch (ForbiddenUserException fue) {
//            assertTrue(true);
//        }
////        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
//        try {
//            instance.displaySiteAuditSetUp("2", null, null, model);
//            assertTrue(false);
//        } catch (ForbiddenUserException fue) {
//            assertTrue(true);
//        }
////        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
//        try {
//            instance.displaySiteAuditSetUp("3", null, null, model);
//            assertTrue(false);
//        } catch (ForbiddenUserException fue) {
//            assertTrue(true);
//        }
////        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
//        try {
//            instance.displaySiteAuditSetUp("4", null, null, model);
//            assertTrue(false);
//        } catch (ForbiddenUserException fue) {
//            assertTrue(true);
//        }
////        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
//        try {
//            instance.displaySiteAuditSetUp("5", null, null, model);
//            assertTrue(false);
//        } catch (ForbiddenUserException fue) {
//            assertTrue(true);
//        }
////        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
//        try {
//            instance.displaySiteAuditSetUp("6", null, null, model);
//            assertTrue(false);
//        } catch (ForbiddenUserException fue) {
//            assertTrue(true);
//        }
////        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
//        try {
//            instance.displaySiteAuditSetUp("7", null, null, model);
//            assertTrue(false);
//        } catch (ForbiddenUserException fue) {
//            assertTrue(true);
//        }
////        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
//        try {
//            instance.displaySiteAuditSetUp("8", null, null, model);
//            assertTrue(false);
//        } catch (ForbiddenUserException fue) {
//            assertTrue(true);
//        }
////        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
//    }

    /**
     * The AuditSetUpController handles 2 parameters map, one for the page audit,
     * and another for the site audit. For each audit type, the map contains 
     * a key that will be associated with a fieldset in the view and a collection
     * of FormFieldBuilder that corresponds to the elements of the fieldset.
     * Each builder embed a mechanism to be automatically associated with a 
     * Parameter when the audit is launched. The value of each form field corresponds
     * to the default value of the associated parameter. 
     * In the case of textField elements, values can be bounded by contract's options.
     * 
     */
    private void buildParametersMap() {
        
        Map<String, List<AuditSetUpFormFieldBuilderImpl>> pageParametersMap = 
                new HashMap<String, List<AuditSetUpFormFieldBuilderImpl>>();
        List<AuditSetUpFormFieldBuilderImpl> auditSetUpFormFieldBuilderList = 
                new ArrayList<AuditSetUpFormFieldBuilderImpl>();
        
        auditSetUpFormFieldBuilderList.add(createAuditSetUpFormFieldBuilder("NUMERICAL_FORMFIELD_1"));
        
        pageParametersMap.put("formElements1", auditSetUpFormFieldBuilderList);
        
//        instance.setPageParametersMap(pageParametersMap);
    }
    
    private AuditSetUpFormFieldBuilderImpl createAuditSetUpFormFieldBuilder(
            String parameterCode)  {
        AuditSetUpFormFieldBuilderImpl auditSetUpFormFieldBuilderImpl = 
                new AuditSetUpFormFieldBuilderImpl();
        auditSetUpFormFieldBuilderImpl.setParameterElementDataService(parameterElementDataService);
        auditSetUpFormFieldBuilderImpl.setParameterCode(parameterCode);
        auditSetUpFormFieldBuilderImpl.setFormFieldBuilder(createNumericalFormFieldBuilder());
        return auditSetUpFormFieldBuilderImpl;
    }
 
    private TextualFormFieldBuilderImpl createTextualFormFieldBuilder() {
        TextualFormFieldBuilderImpl textualFormFieldBuilder = new TextualFormFieldBuilderImpl();
        return textualFormFieldBuilder;
    }
    
    private NumericalFormFieldBuilderImpl createNumericalFormFieldBuilder() {
        NumericalFormFieldBuilderImpl numericalFormFieldBuilder = new NumericalFormFieldBuilderImpl();
        numericalFormFieldBuilder.setMinValue("0");
        numericalFormFieldBuilder.setMaxValue("100");
        return numericalFormFieldBuilder;
    }
    
    private SelectFormFieldBuilderImpl createSelectFormFieldBuilder() {
        SelectFormFieldBuilderImpl selectFormFieldBuilder = new SelectFormFieldBuilderImpl();
//        selectFormFieldBuilder.getActivationCode()
        return selectFormFieldBuilder;
    }

    private SelectElementBuilderImpl createSelectElementBuilder(
            String value, 
            String i18nKey, 
            String errorI18nKey, 
            String defaultCode, 
            boolean isEnabled, 
            boolean isDefault) {
        SelectElementBuilderImpl selectElementBuilder = new SelectElementBuilderImpl();
        selectElementBuilder.setI18nKey(i18nKey);
        selectElementBuilder.setValue(value);
        selectElementBuilder.setErrorI18nKey(errorI18nKey);
        selectElementBuilder.setDefaultCode(defaultCode);
        selectElementBuilder.setDefault(isDefault);
        selectElementBuilder.setEnabled(isEnabled);
        return selectElementBuilder;
    }
    
}