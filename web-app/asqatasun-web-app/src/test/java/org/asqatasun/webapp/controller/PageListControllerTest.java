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
import org.apache.commons.lang3.StringUtils;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.parameterization.ParameterElement;
import org.asqatasun.entity.parameterization.ParameterElementImpl;
import org.asqatasun.entity.parameterization.ParameterImpl;
import org.asqatasun.entity.reference.*;
import org.asqatasun.entity.service.audit.AuditDataService;
import org.asqatasun.entity.service.parameterization.ParameterDataService;
import org.asqatasun.entity.service.reference.ThemeDataService;
import org.asqatasun.entity.service.subject.WebResourceDataService;
import org.asqatasun.entity.subject.Page;
import org.asqatasun.entity.subject.Site;
import org.asqatasun.webapp.config.TestConfiguration;
import org.asqatasun.entity.contract.Act;
import org.asqatasun.entity.contract.Contract;
import org.asqatasun.entity.contract.Scope;
import org.asqatasun.entity.contract.ScopeEnum;
import org.asqatasun.entity.service.contract.ActDataService;
import org.asqatasun.entity.user.User;
import org.asqatasun.webapp.exception.AuditParameterMissingException;
import org.asqatasun.webapp.exception.ForbiddenPageException;
import org.asqatasun.webapp.security.userdetails.TgolUserDetails;
import org.asqatasun.webapp.statistics.service.StatisticsDataService;
import org.asqatasun.webapp.util.HttpStatusCodeFamily;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.ExtendedModelMap;

import static org.asqatasun.webapp.util.TgolKeyStore.AUDIT_ID_KEY;
import static org.asqatasun.webapp.util.TgolKeyStore.PAGE_LIST_VIEW_NAME;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 *
 * @author jkowalczyk
 */
@SpringBootTest
@ContextConfiguration(classes = TestConfiguration.class)
@ActiveProfiles({"integration"})
public class PageListControllerTest  {

    @Autowired
    private PageListController instance;
    @MockBean
    private Authentication mockAuthentication;
    @MockBean
    private WebResourceDataService mockWebResourceDataService;
    @MockBean
    private StatisticsDataService mockStatisticsDataService;
    @MockBean
    private AuditDataService mockAuditDataService;
    @MockBean
    private Audit mockAudit;
    @MockBean
    private ActDataService mockActDataService;
    @MockBean
    private ParameterDataService mockParameterDataService;
    @MockBean
    private ThemeDataService mockThemeDataService;
    @MockBean
    private Site mockSite;
    @MockBean
    private Page mockPage;
    private User mockUser;

    private static final int UNKNOWN_AUDIT_ID = 100;
    private static final int PAGE_AUDIT_ID = 1;
    private static final int UNAUTHORIZED_SCOPE_AUDIT_ID = 2;
    private static final int SITE_AUDIT_GENERAL_PAGE_LIST_ID = 3;
    private static final int SITE_AUDIT_2XX_PAGE_LIST_ID = 4;
    private static final int SITE_AUDIT_3XX_PAGE_LIST_ID = 5;
    private static final int SITE_AUDIT_4XX_PAGE_LIST_ID = 6;
    
    /**
     * The servlet is supposed to embed the audit id the page is
     * about. If not, the ForbiddenPageException is caught.
     */
    @Test
    public void testDisplayPageListWithoutAuditId() {
        // The servlet is supposed to embed the webresource id the page is
        // about. If not, the access denied page is returned
        assertThrows(AuditParameterMissingException.class,
            () -> instance.displayPageList(new MockHttpServletRequest(), new ExtendedModelMap()));

    }

    /**
     * if the id cannot be converted as Long, the ForbiddenPageException is
     * caught.
     *
     */
    @Test
    public void testDisplayPageListWithWrongAuditId() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(AUDIT_ID_KEY, "wrongId");
        Exception exception = assertThrows(ForbiddenPageException.class,
            () -> instance.displayPageList(request, new ExtendedModelMap()));

        assertTrue(StringUtils.equals(
                "java.lang.NumberFormatException: For input string: \"wrongId\"",
                exception.getCause().toString()));
    }

    /**
     * The PageList cannot be displayed when the webResource is a Page
     * instance. The returned view is an access denied in this case.
     *
     */
    @Test
    public void testDisplayPageListWithPageAudit() {
        // The audit with Id 1 is associated with a Page instance
        setUpMockAuditDataService(PAGE_AUDIT_ID);
        setUpMockUserDataService();
        setUpActDataService(false);
        setUpMockAuthenticationContext();

        // The exception is caught when testing if audit.getSubject() is
        // an instance of Page
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(AUDIT_ID_KEY, String.valueOf(PAGE_AUDIT_ID));
        assertThrows(ForbiddenPageException.class,
            () -> instance.displayPageList(request, new ExtendedModelMap()));
    }

    /**
     * The mockWebResourceDataService contains only 2 WebResource. One has
     * Id=1 and is a Page instance, the second has Id=2 and is a Site instance.
     * If a webresource with an id different from 1 or 2 is requested, the
     * ForbiddenPageException is caught
     *
     * @throws Exception
     */
    @Test
    public void testDisplayPageListWithUnknownAuditId() throws Exception {
        setUpMockAuditDataService(UNKNOWN_AUDIT_ID);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(AUDIT_ID_KEY, String.valueOf(UNKNOWN_AUDIT_ID));

        // The auditDataService catch the NoResultException and return null.
        // Then if the audit is null, a ForbiddenPageException is caught
        assertThrows(ForbiddenPageException.class,
            () -> instance.displayPageList(new MockHttpServletRequest(), new ExtendedModelMap()));
    }

    /**
     * The PageList is displayed when the webResource is a Site instance.
     * A mechanism is implemented into that controller that enables to display
     * the page only when the scope of the act related with the webResource
     * belongs to a list of authorized scope. In this case the list only contains
     * DOMAIN as authorized scope, whereas the scope of the audit is
     * GROUP_OF_PAGE, so the page cannot be displayed.
     *
     * @throws Exception
     */
    @Test
    public void testDisplayPageListWithUnauthorizedActScope() {
        setUpMockAuditDataService(UNAUTHORIZED_SCOPE_AUDIT_ID);
        setUpMockUserDataService();
        setUpActDataService(false);
        setUpMockAuthenticationContext();

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(AUDIT_ID_KEY, String.valueOf(UNAUTHORIZED_SCOPE_AUDIT_ID));

        List<String> authorizedScopeForPageList = new ArrayList();
        authorizedScopeForPageList.add("DOMAIN");
        assertThrows(ForbiddenPageException.class,
            () -> instance.displayPageList(new MockHttpServletRequest(), new ExtendedModelMap()));
    }

    /**
     * The PageList is displayed when the webResource is a Site instance.
     * When the request has no TgolKeyStore.STATUS_KEY parameter set,
     * the page that lists the number of page by Http Status Code has to be
     * returned
     *
     * @throws Exception
     */
    @Test
    public void testDisplayPageList() throws Exception {
        setUpMockAuditDataService(SITE_AUDIT_GENERAL_PAGE_LIST_ID);
        setUpMockUserDataService();
        setUpActDataService(true);
        setUpMockAuthenticationContext();

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter(AUDIT_ID_KEY, String.valueOf(SITE_AUDIT_GENERAL_PAGE_LIST_ID));
        assertEquals(PAGE_LIST_VIEW_NAME, instance.displayPageList(request, new ExtendedModelMap()));
    }

    /**
     * Create a user with Id=1, email=test1@test.com and empty name and first name
     */
    private void setUpMockUserDataService() {
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setEmail1("test1@test.com");
        mockUser.setName("");
        mockUser.setFirstName("");
    }

    /**
     *
     * @param isActSiteScope
     */
    private void setUpActDataService(boolean isActSiteScope) {

        Scope mockGroupOfPagesScope = new Scope();
        mockGroupOfPagesScope.setCode(ScopeEnum.GROUPOFPAGES);
        Scope mockSiteScope = new Scope();
        mockSiteScope.setCode(ScopeEnum.DOMAIN);
        Contract mockContract = new Contract();
        Act mockAct = new Act();

        if (isActSiteScope) {
            mockAct.setScope(mockSiteScope);
        } else {
            mockAct.setScope(mockGroupOfPagesScope);
        }

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(2030, 01, 01);

        mockContract.setEndDate(calendar.getTime());
        mockContract.setUser(mockUser);
        mockContract.setLabel("CONTRACT LABEL");
        mockContract.setId(1L);
        mockAct.setContract(mockContract);
        when(mockActDataService.getActFromAudit(mockAudit)).thenReturn(mockAct);

    }

    /**
     *
     * @param getIdValue
     */
    private void setUpMockAuditDataService(int getIdValue) {
        switch (getIdValue) {
            case PAGE_AUDIT_ID :
                when(mockAuditDataService.read((long) getIdValue)).thenReturn(mockAudit);
                when(mockAudit.getSubject()).thenReturn(mockPage);
                break;
            case UNAUTHORIZED_SCOPE_AUDIT_ID:
                when(mockAuditDataService.read((long) getIdValue)).thenReturn(mockAudit);
                when(mockAudit.getSubject()).thenReturn(mockSite);
                break;
            case SITE_AUDIT_GENERAL_PAGE_LIST_ID:
            case SITE_AUDIT_2XX_PAGE_LIST_ID:
            case SITE_AUDIT_3XX_PAGE_LIST_ID:
            case SITE_AUDIT_4XX_PAGE_LIST_ID:
                when(mockAuditDataService.read((long) getIdValue)).thenReturn(mockAudit);
                when(mockAudit.getSubject()).thenReturn(mockSite);
                when(mockAudit.getId()).thenReturn((long) getIdValue);
                // set mock data Service cause the data are all collected
                setUpMockWebResourceDataService((long) getIdValue);
                break;
            case UNKNOWN_AUDIT_ID :
                when(mockAuditDataService.read((long) getIdValue)).thenReturn(null);
                break;
        }
    }

    /**
     *
     */
    private void setUpMockWebResourceDataService (Long idAudit) {
        when(mockSite.getAudit()).thenReturn(mockAudit);
        when(mockSite.getURL()).thenReturn("http://www.test.org");
        when(mockAudit.getDateOfCreation()).thenReturn(Calendar.getInstance().getTime());

        when(mockStatisticsDataService.getWebResourceCountByAuditAndHttpStatusCode(idAudit, HttpStatusCodeFamily.f2xx, null, null)).
                thenReturn(1L);
        when(mockStatisticsDataService.getWebResourceCountByAuditAndHttpStatusCode(idAudit, HttpStatusCodeFamily.f3xx, null, null)).
                thenReturn(2L);
        when(mockStatisticsDataService.getWebResourceCountByAuditAndHttpStatusCode(idAudit, HttpStatusCodeFamily.f4xx, null, null)).
                thenReturn(3L);
        when(mockStatisticsDataService.getWebResourceCountByAuditAndHttpStatusCode(idAudit, HttpStatusCodeFamily.f9xx, null, null)).
                thenReturn(4L);
        when(mockStatisticsDataService.getMarkByWebResourceAndAudit(mockSite, false , false)).
                thenReturn(55.0f);
        when(mockStatisticsDataService.getMarkByWebResourceAndAudit(mockSite, true , false)).
                thenReturn(75.0f);
        when(mockWebResourceDataService.getChildWebResourceCount(mockSite)).
                thenReturn(10L);
        when(mockStatisticsDataService.getResultCountByResultType(mockSite, mockAudit, TestSolution.PASSED)).
                thenReturn(3L);
        when(mockStatisticsDataService.getResultCountByResultType(mockSite, mockAudit, TestSolution.FAILED)).
                thenReturn(3L);
        when(mockStatisticsDataService.getResultCountByResultType(mockSite, mockAudit, TestSolution.NEED_MORE_INFO)).
                thenReturn(3L);
        when(mockStatisticsDataService.getResultCountByResultType(mockSite, mockAudit, TestSolution.NOT_APPLICABLE)).
                thenReturn(1L);

        setUpThemeDataService();

        Set<Parameter> paramSet = new HashSet();
        Parameter mockParameter=  new ParameterImpl();
        ParameterElement mockParameterElement =  new ParameterElementImpl();
        mockParameterElement.setParameterElementCode("LEVEL");
        mockParameter.setParameterElement(mockParameterElement);
        mockParameter.setValue("AW21");
        paramSet.add(mockParameter);

        when(mockParameterDataService.getParameterSetFromAudit(mockAudit)).thenReturn(paramSet);
        when(mockParameterDataService.getReferentialKeyFromAudit(mockAudit)).thenReturn("AW21");
    }

    /**
     *
     */
    private void setUpThemeDataService() {

        Theme mockTheme = new ThemeImpl();
        Collection<Theme> themeCollection = new ArrayList();
        themeCollection.add(mockTheme);

        Reference mockReference = new ReferenceImpl();
        mockReference.setCode("AW21");

        Criterion mockCriterion = new CriterionImpl();
        mockCriterion.setReference(mockReference);
        Collection<Criterion> criterionCollection = new ArrayList();
        criterionCollection.add(mockCriterion);

        mockTheme.setCriterionList(criterionCollection);
        when(mockThemeDataService.findAll()).thenReturn(themeCollection);
    }

    private void setUpMockAuthenticationContext(){
        TgolUserDetails tud = new TgolUserDetails(
            "test1@test.com",
            "",
            true,
            false,
            true,
            true,
            Collections.emptyList(),
            mockUser);

        SecurityContextImpl securityContextImpl = new SecurityContextImpl();
        securityContextImpl.setAuthentication(mockAuthentication);
        SecurityContextHolder.setContext(securityContextImpl);
        when(mockAuthentication.getName()).thenReturn("test1@test.com");
        when(mockAuthentication.getPrincipal()).thenReturn(tud);
    }
}
