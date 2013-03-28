/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2012 Open-S Company
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
import javax.servlet.http.HttpServletResponse;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.entity.reference.Reference;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.service.parameterization.ParameterDataService;
import org.opens.tanaguru.entity.service.reference.ThemeDataService;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tgol.entity.contract.Act;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.contract.Scope;
import org.opens.tgol.entity.contract.ScopeEnum;
import org.opens.tgol.entity.decorator.tanaguru.subject.WebResourceDataServiceDecorator;
import org.opens.tgol.entity.service.contract.ActDataService;
import org.opens.tgol.entity.service.user.UserDataService;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.exception.ForbiddenPageException;
import org.opens.tgol.presentation.factory.AuditStatisticsFactory;
import org.opens.tgol.security.userdetails.TgolUserDetails;
import org.opens.tgol.util.HttpStatusCodeFamily;
import org.opens.tgol.util.TgolKeyStore;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
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
public class PageListControllerTest extends TestCase {
    
    private PageListController instance;
    
    private UserDataService mockUserDataService;
    private AuthenticationDetails mockAuthenticationDetails;
    private Authentication mockAuthentication;
    private Contract mockContract;
    private User mockUser;
    private WebResourceDataServiceDecorator mockWebResourceDataServiceDecorator;
    private Site mockSite;
    private ActDataService mockActDataService;
    private ParameterDataService mockParameterDataService;
    private ThemeDataService mockThemeDataService;
    private Theme mockTheme;
    private Act mockAct;
    private Audit mockAudit;
    private Scope mockPageScope;
    private Scope mockSiteScope;
    
    public PageListControllerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        instance = new PageListController();
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
        if (mockSite != null) {
            verify(mockSite);
        }
        if (mockWebResourceDataServiceDecorator != null) {
            verify(mockWebResourceDataServiceDecorator);
        }
        if (mockActDataService != null) {
            verify(mockActDataService);
        }
        if (mockAct != null) {
            verify(mockAct);
        }
        if (mockAudit != null) {
            verify(mockAudit);
        }
        if (mockParameterDataService != null) {
            verify(mockParameterDataService);
        }
        if (mockThemeDataService != null) {
            verify(mockThemeDataService);
        }
        if (mockTheme != null) {
            verify(mockTheme);
        }
        if (mockSiteScope != null) {
            verify(mockSiteScope);
        }
        if (mockPageScope != null) {
            verify(mockPageScope);
        }
        if (mockAuthenticationDetails != null) {
            verify(mockAuthenticationDetails);
        }
        if (mockAuthentication != null) {
            verify(mockAuthentication);
        }
    }

    /**
     * The servlet is supposed to embed the webresource id the page is 
     * about. If not, the access denied page is returned
     * @throws Exception 
     */
    public void testDisplayPageListWithoutWebResourceId() throws Exception {
        System.out.println("testDisplayPageListWithoutWebResourceId");
        
        setUpMockUserDataService();
        
        MockHttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        Model model = new ExtendedModelMap();
        String expResult = TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
        // The servlet is supposed to embed the webresource id the page is 
        // about. If not, the access denied page is returned
        String result = instance.displayPageList(request, response, model);
        assertEquals(expResult, result);
    } 
    
    /**
     * if the id cannot be converted as Long, the access denied page is 
     * returned.
     * 
     * @throws Exception 
     */
    public void testDisplayPageListWithWrongWebResourceId() throws Exception {
        System.out.println("testDisplayPageListWithWrongWebResourceId");

        setUpMockUserDataService();
        
        HttpServletResponse response = new MockHttpServletResponse();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(TgolKeyStore.WEBRESOURCE_ID_KEY, "wrongId");
        String expResult = TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
        String result = instance.displayPageList(request, response, new ExtendedModelMap());
        assertEquals(expResult, result);
    }
    
    /**
     * The mockWebResourceDataService contains only 2 WebResource. One has 
     * Id=1 and is a Page instance, the second has Id=2 and is a Site instance.
     * If a webresource with an id different from 1 or 2 is requested, the
     * ForbiddenPageException is caught
     * 
     * @throws Exception 
     */
    public void testDisplayPageListWithUnknownWebResourceId() throws Exception {
        System.out.println("testDisplayPageListWithUnknownWebResourceId");
        
        setUpMockWebResourceDataServiceDecorator(1, 100);
        setUpActDataService(false);
        setUpMockUserDataService();
        
        HttpServletResponse response = new MockHttpServletResponse();
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(TgolKeyStore.WEBRESOURCE_ID_KEY, "100");
        try {
            instance.displayPageList(request, response, new ExtendedModelMap());
            assertTrue(false);
        } catch (ForbiddenPageException fbe) {
            assertTrue(true);
        }
    }

    /**
     * The PageList cannot be displayed when the webResource is a Page 
     * instance. The returned view is an access denied in this case.
     * 
     * @throws Exception 
     */
    public void testDisplayPageListWithPageWebResource() throws Exception {
        System.out.println("testDisplayPageListWithPageWebResource");
        
        setUpMockWebResourceDataServiceDecorator(1, 1);
        setUpMockUserDataService();
        setUpActDataService(false);
        setUpMockAuthenticationContext();

        HttpServletResponse response = new MockHttpServletResponse();
        MockHttpServletRequest request = new MockHttpServletRequest();
        String expResult = TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
        request.addParameter(TgolKeyStore.WEBRESOURCE_ID_KEY, "1");
        
        String result = instance.displayPageList(request, response, new ExtendedModelMap());
        assertEquals(expResult, result);
    }

    /**
     * The PageList is displayed when the webResource is a Site instance. 
     * A mechanism is implemented into that controller that enables to display
     * the page only when the scope of the act related with the webResource
     * belongs to a list of authorized scope. In this case the list is set 
     * as empty, so the page cannot be displayed.
     * 
     * @throws Exception 
     */
    public void testDisplayPageListWithUnauthorizedActScope() throws Exception {
        System.out.println("testDisplayPageListWithUnauthorizedActScope");
       
        setUpMockWebResourceDataServiceDecorator(1, 2);
        setUpMockUserDataService();
        setUpActDataService(false);
        
        HttpServletResponse response = new MockHttpServletResponse();
        MockHttpServletRequest request = new MockHttpServletRequest();
        String expResult = TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
        request.addParameter(TgolKeyStore.WEBRESOURCE_ID_KEY, "2");
        
        String result = instance.displayPageList(request, response, new ExtendedModelMap());
        assertEquals(expResult, result);
    }
    
    /**
     * The PageList is displayed when the webResource is a Site instance. 
     * When the request has no TgolKeyStore.STATUS_KEY parameter set, 
     * the page that lists the number of page by Http Status Code has to be 
     * returned
     * 
     * @throws Exception 
     */
    public void testDisplayPageList() throws Exception {
        System.out.println("testDisplayPageList");
        
        setUpMockWebResourceDataServiceDecorator(1, 2);
        setUpMockUserDataService();
        setUpActDataService(true);
        setUpMockAuthenticationContext();
        setUpAuditStatisticsFactory();
        
        List<String> authorizedScopeForPageList = new ArrayList<String>();
        authorizedScopeForPageList.add("DOMAIN");
        instance.setAuthorizedScopeForPageList(authorizedScopeForPageList);
        
        HttpServletResponse response = new MockHttpServletResponse();
        MockHttpServletRequest request = new MockHttpServletRequest();
        String expResult = TgolKeyStore.PAGE_LIST_VIEW_NAME;
        request.addParameter(TgolKeyStore.WEBRESOURCE_ID_KEY, "2");
        
        String result = instance.displayPageList(request, response, new ExtendedModelMap());
        assertEquals(expResult, result);
    }
 
    /**
     * 
     * @param actDataService
     * @param webResourceDataServiceDecorator 
     */
    private void setUpAuditStatisticsFactory() {
        AuditStatisticsFactory.getInstance().setActDataService(mockActDataService);
        AuditStatisticsFactory.getInstance().setWebResourceDataService(mockWebResourceDataServiceDecorator);
        AuditStatisticsFactory.getInstance().setParameterDataService(mockParameterDataService);
        AuditStatisticsFactory.getInstance().setThemeDataService(mockThemeDataService);
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

    private void setUpMockUserDataService() {

        mockUser = createMock(User.class);
        mockUserDataService = createMock(UserDataService.class); 
        
        expect(mockUser.getId())
                    .andReturn(Long.valueOf(1))
                        .anyTimes();
        expect(mockUser.getEmail1())
                    .andReturn("test1@test.com")
                        .anyTimes();
        expect(mockUser.getName())
                    .andReturn("")
                        .anyTimes();
        expect(mockUser.getFirstName())
                    .andReturn("")
                        .anyTimes();
        
        replay(mockUser);
        replay(mockUserDataService);

        // the HomeController needs a UserDataService instance
        instance.setUserDataService(mockUserDataService);
    }
    
    private void setUpActDataService(boolean isActSiteScope) {
        
        mockPageScope = createMock(Scope.class);
        mockSiteScope = createMock(Scope.class);
        mockContract = createMock(Contract.class);
        Collection<Contract> contractSet = new HashSet<Contract>();
        contractSet.add(mockContract);
        
        mockActDataService = createMock(ActDataService.class);
        mockAct = createMock(Act.class);
        
        expect(mockActDataService.getActFromWebResource(mockSite))
                .andReturn(mockAct)
                    .anyTimes();
        
        expect(mockAct.getContract())
                .andReturn(mockContract)
                    .anyTimes();
        if (isActSiteScope) {
            expect(mockSiteScope.getCode()).andReturn(ScopeEnum.DOMAIN).anyTimes();
            expect(mockAct.getScope())
                .andReturn(mockSiteScope)
                    .anyTimes();
        } else {
            expect(mockPageScope.getCode()).andReturn(ScopeEnum.PAGE).anyTimes();
            expect(mockAct.getScope())
                .andReturn(mockPageScope)
                    .anyTimes();
        }
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(2030, 01, 01);
        
        expect(mockContract.getEndDate())
                .andReturn(calendar.getTime())
                    .anyTimes();
        
        expect(mockContract.getUser())
                .andReturn(mockUser)
                    .anyTimes();
        
        expect(mockContract.getLabel())
                .andReturn("CONTRACT LABEL")
                    .anyTimes();
        
        expect(mockContract.getId())
                .andReturn(Long.valueOf(1))
                    .anyTimes();
        
        replay(mockActDataService);
        replay(mockAct);
        replay(mockContract);
        replay(mockSiteScope);
        replay(mockPageScope);
        
        instance.setActDataService(mockActDataService);
        AuditStatisticsFactory.getInstance().setActDataService(mockActDataService);
    }
    
    private void setUpMockWebResourceDataServiceDecorator(
            int getLightReadCounter,
            int getIdValue) {

        mockWebResourceDataServiceDecorator = createMock(WebResourceDataServiceDecorator.class);
        mockSite = createMock(Site.class);
        mockAudit = createMock(Audit.class);
        mockParameterDataService = createMock(ParameterDataService.class);
        
        if (getLightReadCounter > 0) {
            if (getIdValue == 100) {
                expect(mockWebResourceDataServiceDecorator.ligthRead(Long.valueOf(getIdValue)))
                    .andReturn(null)
                        .times(getLightReadCounter);
            } else {
                expect(mockWebResourceDataServiceDecorator.ligthRead(Long.valueOf(getIdValue)))
                    .andReturn(mockSite)
                        .times(getLightReadCounter);
            }
        }
        
        expect(mockSite.getAudit()).andReturn(mockAudit).anyTimes();
        expect(mockSite.getURL()).andReturn("http://www.test.org").anyTimes();
        expect(mockAudit.getId()).andReturn(Long.valueOf(1)).anyTimes();
        expect(mockAudit.getDateOfCreation()).andReturn(Calendar.getInstance().getTime()).anyTimes();
        
        expect(mockWebResourceDataServiceDecorator.getWebResourceCountByAuditAndHttpStatusCode(Long.valueOf(1), HttpStatusCodeFamily.f2xx, null, null)).
                andReturn(Long.valueOf(1)).
                anyTimes();
        expect(mockWebResourceDataServiceDecorator.getWebResourceCountByAuditAndHttpStatusCode(Long.valueOf(1), HttpStatusCodeFamily.f3xx, null, null)).
                andReturn(Long.valueOf(2)).
                anyTimes();
        expect(mockWebResourceDataServiceDecorator.getWebResourceCountByAuditAndHttpStatusCode(Long.valueOf(1), HttpStatusCodeFamily.f4xx, null, null)).
                andReturn(Long.valueOf(3)).
                anyTimes();
        expect(mockWebResourceDataServiceDecorator.getMarkByWebResourceAndAudit(mockSite, mockAudit, false)).
                andReturn(Float.valueOf(55)).
                anyTimes();
        expect(mockWebResourceDataServiceDecorator.getMarkByWebResourceAndAudit(mockSite, mockAudit, true)).
                andReturn(Float.valueOf(75)).
                anyTimes();
        expect(mockWebResourceDataServiceDecorator.getChildWebResourceCount(mockSite)).
                andReturn(Long.valueOf(10)).
                anyTimes();
        expect(mockWebResourceDataServiceDecorator.getResultCountByResultType(mockSite, mockAudit, TestSolution.PASSED)).
                andReturn(Long.valueOf(3)).
                anyTimes();
        expect(mockWebResourceDataServiceDecorator.getResultCountByResultType(mockSite, mockAudit, TestSolution.FAILED)).
                andReturn(Long.valueOf(3)).
                anyTimes();
        expect(mockWebResourceDataServiceDecorator.getResultCountByResultType(mockSite, mockAudit, TestSolution.NEED_MORE_INFO)).
                andReturn(Long.valueOf(3)).
                anyTimes();
        expect(mockWebResourceDataServiceDecorator.getResultCountByResultType(mockSite, mockAudit, TestSolution.NOT_APPLICABLE)).
                andReturn(Long.valueOf(1)).
                anyTimes();
        
        setUpThemeDataService();
        
        expect(mockWebResourceDataServiceDecorator.getResultCountByResultTypeAndTheme(mockSite, mockAudit, TestSolution.PASSED, mockTheme)).
                andReturn(Long.valueOf(3)).
                anyTimes();
        expect(mockWebResourceDataServiceDecorator.getResultCountByResultTypeAndTheme(mockSite, mockAudit, TestSolution.FAILED, mockTheme)).
                andReturn(Long.valueOf(3)).
                anyTimes();
        expect(mockWebResourceDataServiceDecorator.getResultCountByResultTypeAndTheme(mockSite, mockAudit, TestSolution.NEED_MORE_INFO, mockTheme)).
                andReturn(Long.valueOf(3)).
                anyTimes();
        expect(mockWebResourceDataServiceDecorator.getResultCountByResultTypeAndTheme(mockSite, mockAudit, TestSolution.NOT_APPLICABLE, mockTheme)).
                andReturn(Long.valueOf(1)).
                anyTimes();
        expect(mockWebResourceDataServiceDecorator.getResultCountByResultTypeAndTheme(mockSite, mockAudit, TestSolution.NOT_TESTED, mockTheme)).
                andReturn(Long.valueOf(1)).
                anyTimes();
                
        Set<Parameter> paramSet = new HashSet<Parameter>();
        Parameter mockParameter=  createMock(Parameter.class);
        ParameterElement mockParameterElement=  createMock(ParameterElement.class);
        expect(mockParameterElement.getParameterElementCode()).andReturn("LEVEL").anyTimes();
        expect(mockParameter.getParameterElement()).andReturn(mockParameterElement).anyTimes();
        expect(mockParameter.getValue()).andReturn("AW21").anyTimes();
        paramSet.add(mockParameter);
        expect(mockParameterDataService.getParameterSetFromAudit(mockAudit)).andReturn(paramSet).anyTimes();
        
        replay(mockSite);
        replay(mockAudit);
        replay(mockWebResourceDataServiceDecorator);
        replay(mockParameterDataService);
        replay(mockParameter);
        replay(mockParameterElement);
        
        instance.setWebResourceDataService(mockWebResourceDataServiceDecorator);
    }
    
    private void setUpThemeDataService() {
        mockThemeDataService = createMock(ThemeDataService.class);
        
        mockTheme = createMock(Theme.class);
        Collection<Theme> themeCollection = new ArrayList<Theme>();
        themeCollection.add(mockTheme);
        
        Reference mockReference = createMock(Reference.class);
        
        Criterion mockCriterion = createMock(Criterion.class);
        Collection<Criterion> criterionCollection = new ArrayList<Criterion>();
        criterionCollection.add(mockCriterion);
        
        expect(mockThemeDataService.findAll()).andReturn(themeCollection).anyTimes();
        expect(mockTheme.getCriterionList()).andReturn(criterionCollection).anyTimes();
        expect(mockCriterion.getReference()).andReturn(mockReference).anyTimes();
        expect(mockReference.getCode()).andReturn("AW21").anyTimes();
        
        replay(mockTheme);
        replay(mockCriterion);
        replay(mockReference);
        replay(mockThemeDataService);
    }
}