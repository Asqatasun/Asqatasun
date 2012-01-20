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

import org.opens.tgol.entity.user.User;
import org.opens.tgol.form.NumericalFormField;
import org.opens.tgol.form.SelectElement;
import org.opens.tgol.form.SelectFormField;
import org.opens.tgol.form.TextualFormField;
import org.opens.tgol.form.parameterization.AuditSetUpFormField;
import org.opens.tgol.util.TgolKeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import junit.framework.TestCase;
import org.opens.tgol.exception.ForbiddenUserException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

/**
 *
 * @author jkowalczyk
 */
public class AuditSetUpControllerTest extends TestCase {

    private static final String SPRING_FILE_PATH =
            "src/test/resources/unit-test-context.xml";
    private AuditSetUpController asuc = null;
    protected BeanFactory springBeanFactory;

    public AuditSetUpControllerTest(String testName) {
        super(testName);
        ApplicationContext springApplicationContext =
                new FileSystemXmlApplicationContext(SPRING_FILE_PATH);
        springBeanFactory = springApplicationContext;
        asuc = (AuditSetUpController) springBeanFactory.getBean("auditSetUpController");
        initTgsiContext();
    }

    public void testDisplayPageAuditSetUp() {
        initSecurityContextHolderContext("test1@test.com");
        Model model = new ExtendedModelMap();

        // The contract 1 is of DOMAIN type. The set-up page for a page audit
        // is allowed
        String returnedView = asuc.displayPageAuditSetUp("1", null, null, model);
        assertEquals(TgolKeyStore.AUDIT_PAGE_SET_UP_VIEW_NAME, returnedView);
        assertEquals("test1@test.com", ((User)model.asMap().get(TgolKeyStore.AUTHENTICATED_USER_KEY)).getEmail1());
        assertEquals("http://www.test1.com", model.asMap().get(TgolKeyStore.URL_KEY));
        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));

        // The contract 2 is of DOMAIN type. The set-up page for a site audit
        // is allowed. For a group of pages audit, the default parameter set attribute
        // is always set to false due to DEPTH and MAX_DOCUMENTS parameters
        // modifications
        returnedView = asuc.displayPageAuditSetUp("2", null, null, model);
        assertEquals("test1@test.com", ((User)model.asMap().get(TgolKeyStore.AUTHENTICATED_USER_KEY)).getEmail1());
        assertEquals("http://www.test2.com", model.asMap().get(TgolKeyStore.URL_KEY));
        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));

        // the contract has a GROUPOFPAGES scope.The access is denied
        returnedView = asuc.displayPageAuditSetUp("9", null, null, model);
        assertEquals("test1@test.com", ((User)model.asMap().get(TgolKeyStore.AUTHENTICATED_USER_KEY)).getEmail1());
        assertEquals("http://www.test9.com", model.asMap().get(TgolKeyStore.URL_KEY));
        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));

        // the contract is not associated with the current user. The access is denied
        initSecurityContextHolderContext("test2@test.com");
        try {
            returnedView = asuc.displayPageAuditSetUp("1", null, null, model);
            assertTrue(false);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
//        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
        try {
            returnedView = asuc.displayPageAuditSetUp("2", null, null, model);
            assertTrue(false);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
//        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
        try {
            returnedView = asuc.displayPageAuditSetUp("3", null, null, model);
            assertTrue(false);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
//        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
        try {
            returnedView = asuc.displayPageAuditSetUp("4", null, null, model);
            assertTrue(false);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
//        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
        try {
            returnedView = asuc.displayPageAuditSetUp("5", null, null, model);
            assertTrue(false);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
//        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
        try {
            returnedView = asuc.displayPageAuditSetUp("6", null, null, model);
            assertTrue(false);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
//        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
        try {
            returnedView = asuc.displayPageAuditSetUp("7", null, null, model);
            assertTrue(false);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
//        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
        try {
            returnedView = asuc.displayPageAuditSetUp("8", null, null, model);
            assertTrue(false);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
//        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
        try {
            returnedView = asuc.displayPageAuditSetUp("9", null, null, model);
            assertTrue(false);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
//        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
    }

    public void testDisplaySiteAuditSetUp() {
        initSecurityContextHolderContext("test1@test.com");
        Model model = new ExtendedModelMap();

        // The contract 1 is of DOMAIN type. The set-up page for a site audit
        // is allowed
        String returnedView = asuc.displaySiteAuditSetUp("1", null, null, model);
        assertEquals(TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME, returnedView);
        assertEquals("test1@test.com", ((User)model.asMap().get(TgolKeyStore.AUTHENTICATED_USER_KEY)).getEmail1());
        assertEquals("http://www.test1.com", model.asMap().get(TgolKeyStore.URL_KEY));
        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));

        // The contract 2 is of DOMAIN type. The set-up page for a site audit
        // is allowed
        returnedView = asuc.displaySiteAuditSetUp("2", null, null, model);
        assertEquals(true, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
        assertEquals("http://www.test2.com", model.asMap().get(TgolKeyStore.URL_KEY));
        assertEquals(TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME, returnedView);
        for (List<AuditSetUpFormField> asuffList : ((Map<String, List<AuditSetUpFormField>>)model.asMap().get(TgolKeyStore.PARAMETERS_MAP_KEY)).values()){
            for (AuditSetUpFormField asuff : asuffList) {
                if (asuff.getFormField() instanceof SelectFormField) {
                    List<SelectElement> seoSelectElementList = ((SelectFormField)asuff.getFormField()).getSelectElementMap().get("Seo");
                    for (SelectElement se : seoSelectElementList) {
                        assertEquals(true, se.getEnabled());
                    }
                    List<SelectElement> aw21SelectElementList = ((SelectFormField)asuff.getFormField()).getSelectElementMap().get("AW21");
                    for (SelectElement se : aw21SelectElementList) {
                        assertEquals(true, se.getEnabled());
                    }
                }
            }
        }

        // The contract 3 is of DOMAIN type. The set-up page for a site audit
        // is allowed
        returnedView = asuc.displaySiteAuditSetUp("3", null, null, model);
        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
        assertEquals(TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME, returnedView);
        assertEquals("http://www.test3.com", model.asMap().get(TgolKeyStore.URL_KEY));
        for (List<AuditSetUpFormField> asuffList : ((Map<String, List<AuditSetUpFormField>>)model.asMap().get(TgolKeyStore.PARAMETERS_MAP_KEY)).values()){
            for (AuditSetUpFormField asuff : asuffList) {
                if (asuff.getFormField() instanceof SelectFormField) {
                    List<SelectElement> seoSelectElementList = ((SelectFormField)asuff.getFormField()).getSelectElementMap().get("Seo");
                    for (SelectElement se : seoSelectElementList) {
                        assertEquals(false, se.getEnabled());
                    }
                    List<SelectElement> aw21SelectElementList = ((SelectFormField)asuff.getFormField()).getSelectElementMap().get("AW21");
                    for (SelectElement se : aw21SelectElementList) {
                        assertEquals(true, se.getEnabled());
                    }
                }
            }
        }

        // The contract 4 is of DOMAIN type. The set-up page for a site audit
        // is allowed
        returnedView = asuc.displaySiteAuditSetUp("4", null, null, model);
        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
        assertEquals(TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME, returnedView);
        assertEquals("http://www.test4.com", model.asMap().get(TgolKeyStore.URL_KEY));
        for (List<AuditSetUpFormField> asuffList : ((Map<String, List<AuditSetUpFormField>>)model.asMap().get(TgolKeyStore.PARAMETERS_MAP_KEY)).values()){
            for (AuditSetUpFormField asuff : asuffList) {
                if (asuff.getFormField() instanceof SelectFormField) {
                    List<SelectElement> seoSelectElementList = ((SelectFormField)asuff.getFormField()).getSelectElementMap().get("Seo");
                    for (SelectElement se : seoSelectElementList) {
                        assertEquals(false, se.getEnabled());
                    }
                    List<SelectElement> aw21SelectElementList = ((SelectFormField)asuff.getFormField()).getSelectElementMap().get("AW21");
                    for (SelectElement se : aw21SelectElementList) {
                        assertEquals(false, se.getEnabled());
                    }
                }
            }
        }

        // The contract 5 is of DOMAIN type. The set-up page for a site audit
        // is allowed
        System.out.println("avant erreur");
        returnedView = asuc.displaySiteAuditSetUp("5", null, null, model);
        System.out.println("apres erreur");
        assertEquals(true, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
        assertEquals(TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME, returnedView);
        assertEquals("http://www.test5.com", model.asMap().get(TgolKeyStore.URL_KEY));
        for (List<AuditSetUpFormField> asuffList : ((Map<String, List<AuditSetUpFormField>>)model.asMap().get(TgolKeyStore.PARAMETERS_MAP_KEY)).values()){
            for (AuditSetUpFormField asuff : asuffList) {
                if (asuff.getParameterElement().getParameterElementCode().equals("MAX_DOCUMENTS")
                        && asuff.getFormField() instanceof NumericalFormField) {
                    assertEquals("2",((NumericalFormField)asuff.getFormField()).getMaxValue());
                }
            }
        }

        // The contract 6 is of DOMAIN type. The set-up page for a site audit
        // is allowed
        returnedView = asuc.displaySiteAuditSetUp("6", null, null, model);
        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
        assertEquals(TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME, returnedView);
        assertEquals("http://www.test6.com", model.asMap().get(TgolKeyStore.URL_KEY));
        for (List<AuditSetUpFormField> asuffList : ((Map<String, List<AuditSetUpFormField>>)model.asMap().get(TgolKeyStore.PARAMETERS_MAP_KEY)).values()){
            for (AuditSetUpFormField asuff : asuffList) {
                if (asuff.getParameterElement().getParameterElementCode().equals("MAX_DURATION")
                        && asuff.getFormField() instanceof NumericalFormField) {
                    assertEquals("20",((NumericalFormField)asuff.getFormField()).getMaxValue());
                }
            }
        }

        // The contract 7 is of DOMAIN type. The set-up page for a site audit
        // is allowed
        returnedView = asuc.displaySiteAuditSetUp("7", null, null, model);
        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
        assertEquals(TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME, returnedView);
        assertEquals("http://www.test7.com", model.asMap().get(TgolKeyStore.URL_KEY));
        for (List<AuditSetUpFormField> asuffList : ((Map<String, List<AuditSetUpFormField>>)model.asMap().get(TgolKeyStore.PARAMETERS_MAP_KEY)).values()){
            for (AuditSetUpFormField asuff : asuffList) {
                if (asuff.getParameterElement().getParameterElementCode().equals("DEPTH")
                        && asuff.getFormField() instanceof NumericalFormField) {
                    assertEquals("5",((NumericalFormField)asuff.getFormField()).getMaxValue());
                }
            }
        }

        // The contract 8 is of DOMAIN type. The set-up page for a site audit
        // is allowed
        returnedView = asuc.displaySiteAuditSetUp("8", null, null, model);
        assertEquals(false, model.asMap().get(TgolKeyStore.DEFAULT_PARAM_SET_KEY));
        assertEquals(TgolKeyStore.AUDIT_SITE_SET_UP_VIEW_NAME, returnedView);
        assertEquals("http://www.test8.com", model.asMap().get(TgolKeyStore.URL_KEY));
        for (List<AuditSetUpFormField> asuffList : ((Map<String, List<AuditSetUpFormField>>)model.asMap().get(TgolKeyStore.PARAMETERS_MAP_KEY)).values()){
            for (AuditSetUpFormField asuff : asuffList) {
                if (asuff.getParameterElement().getParameterElementCode().equals("EXCLUSION_REGEXP")
                        && asuff.getFormField() instanceof TextualFormField) {
                    assertEquals("test_expression",((TextualFormField)asuff.getFormField()).getValue());
                }
            }
        }

        // the contract has a GROUPOFPAGES scope.The access is denied
        returnedView = asuc.displaySiteAuditSetUp("9", null, null, model);
        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
        
        // the contracts are not associated with the current user. The access is denied
        initSecurityContextHolderContext("test2@test.com");
        try {
            returnedView = asuc.displaySiteAuditSetUp("1", null, null, model);
            assertTrue(false);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
//        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
        try {
            returnedView = asuc.displaySiteAuditSetUp("2", null, null, model);
            assertTrue(false);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
//        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
        try {
            returnedView = asuc.displaySiteAuditSetUp("3", null, null, model);
            assertTrue(false);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
//        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
        try {
            returnedView = asuc.displaySiteAuditSetUp("4", null, null, model);
            assertTrue(false);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
//        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
        try {
            returnedView = asuc.displaySiteAuditSetUp("5", null, null, model);
            assertTrue(false);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
//        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
        try {
            returnedView = asuc.displaySiteAuditSetUp("6", null, null, model);
            assertTrue(false);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
//        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
        try {
            returnedView = asuc.displaySiteAuditSetUp("7", null, null, model);
            assertTrue(false);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
//        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
        try {
            returnedView = asuc.displaySiteAuditSetUp("8", null, null, model);
            assertTrue(false);
        } catch (ForbiddenUserException fue) {
            assertTrue(true);
        }
//        assertEquals(TgolKeyStore.ACCESS_DENIED_VIEW_NAME, returnedView);
    }

    private void initTgsiContext(){
        
    }

    private void initSecurityContextHolderContext(String userName) {
        SecurityContextImpl securityContextImpl = new SecurityContextImpl();
        GrantedAuthority[] grantedAuthorities = new GrantedAuthorityImpl[]{new GrantedAuthorityImpl(
            "ROLE_TEST")};
        AuthenticationDetails authenticationDetails = new AuthenticationDetails(
                grantedAuthorities, userName);
        securityContextImpl.setAuthentication(authenticationDetails);
        SecurityContextHolder.setContext(securityContextImpl);
    }

    private class AuthenticationDetails implements Authentication {

        private static final long serialVersionUID = 1L;
        private Collection<GrantedAuthority> grantedAuthorities;
        private String userName;

        public AuthenticationDetails(GrantedAuthority[] grantedAuthorities, String userName) {
            this.grantedAuthorities = new ArrayList<GrantedAuthority>(Arrays.asList(grantedAuthorities));
            this.userName = userName;
        }

        public Collection<GrantedAuthority> getAuthorities() {
            return grantedAuthorities;
        }

        public Object getCredentials() {

            return null;
        }

        public Object getDetails() {

            return null;
        }

        public Object getPrincipal() {

            return null;
        }

        public boolean isAuthenticated() {

            return false;
        }

        public void setAuthenticated(boolean arg0)
                throws IllegalArgumentException {
        }

        public String getName() {
            return userName;
        }

        public void setAuthorities(GrantedAuthority[] grantedAuthorities) {
            this.grantedAuthorities = new ArrayList<GrantedAuthority>(Arrays.asList(grantedAuthorities));
        }
    }

}