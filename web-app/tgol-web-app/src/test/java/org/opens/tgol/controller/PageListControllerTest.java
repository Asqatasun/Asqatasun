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

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import junit.framework.TestCase;
import org.opens.tanaguru.entity.service.parameterization.MockParameterDataService;
import org.opens.tanaguru.entity.service.parameterization.ParameterDataService;
import org.opens.tanaguru.entity.service.reference.MockThemeDataService;
import org.opens.tanaguru.entity.service.reference.ThemeDataService;
import org.opens.tgol.entity.decorator.tanaguru.subject.MockWebResourceDataServiceDecorator;
import org.opens.tgol.entity.decorator.tanaguru.subject.WebResourceDataServiceDecorator;
import org.opens.tgol.entity.service.contract.ActDataService;
import org.opens.tgol.entity.service.contract.MockActDataService;
import org.opens.tgol.entity.service.contract.MockContractDataService;
import org.opens.tgol.entity.service.user.MockUserDataService;
import org.opens.tgol.entity.service.user.UserDataService;
import org.opens.tgol.exception.ForbiddenPageException;
import org.opens.tgol.mock.MockAuthenticationDetails;
import org.opens.tgol.presentation.factory.AuditStatisticsFactory;
import org.opens.tgol.util.TgolKeyStore;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

/**
 *
 * @author jkowalczyk
 */
public class PageListControllerTest extends TestCase {
    
    private PageListController instance = new PageListController();
    
    public PageListControllerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        WebResourceDataServiceDecorator webResourceDataServiceDecorator = new MockWebResourceDataServiceDecorator();
        instance.setWebResourceDataService(webResourceDataServiceDecorator);
        UserDataService userDataService =new MockUserDataService();
        instance.setUserDataService(userDataService);
        MockContractDataService contractDataService = new MockContractDataService();
        contractDataService.setUserDataService(userDataService);
        MockActDataService mockActDataService = new MockActDataService();
        mockActDataService.setContractDataService(contractDataService);
        instance.setActDataService(mockActDataService);
        MockAuthenticationDetails.initSecurityContextHolder("test1@test.com");
        mockAuditStatisticsFactory(mockActDataService, webResourceDataServiceDecorator);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of displayPageList method, of class PageListController.
     */
    public void testDisplayPageList() throws Exception {
        System.out.println("displayPageList");
        MockHttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        Model model = new ExtendedModelMap();
        String expResult = TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
        // The servlet is supposed to embed the webresource id the page is 
        // about. If not, the access denied page is returned
        String result = instance.displayPageList(request, response, model);
        assertEquals(expResult, result);
        
        // if the id cannot be converted as Long, the access denied page is 
        // returned
        request = new MockHttpServletRequest();
        request.addParameter(TgolKeyStore.WEBRESOURCE_ID_KEY, "wrongId");
        result = instance.displayPageList(request, response, model);
        assertEquals(expResult, result);
        
        // the mockWebResourceDataService contains only 2 WebResource. One has 
        // Id=1 and is a Page instance, the second has Id=2 and is a Site instance
        // If a webresource with an id different from 1 or 2 is requested, the
        // ForbiddenPageException is caught
        
        request = new MockHttpServletRequest();
        request.addParameter(TgolKeyStore.WEBRESOURCE_ID_KEY, "100");
        try {
            instance.displayPageList(request, response, model);
            assertTrue(false);
        } catch (ForbiddenPageException fbe) {
            assertTrue(true);
        }
        
        // the PageList cannot be displayed when the webResource is a Page 
        // instance. The returned view is an access denied in this case.
        expResult = TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
        request = new MockHttpServletRequest();
        request.addParameter(TgolKeyStore.WEBRESOURCE_ID_KEY, "1");
        result = instance.displayPageList(request, response, model);
        assertEquals(expResult, result);
        
        // the PageList is displayed when the webResource is a Site instance. 
        // A mechanism is implemented into that controller that enables to display
        // the page only when the scope of the act related with the webResource
        // belongs to a list of authorized scope. In this case the list is set 
        // as empty, so the page cannot be displayed.
        expResult = TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
        request = new MockHttpServletRequest();
        request.addParameter(TgolKeyStore.WEBRESOURCE_ID_KEY, "2");
        result = instance.displayPageList(request, response, model);
        assertEquals(expResult, result);
        
        // the PageList is displayed when the webResource is a Site instance. 
        // When the request has no TgolKeyStore.STATUS_KEY parameter set, 
        // the page that lists the number of page by Http Status Code has to be 
        // returned
        List<String> authorizedScopeForPageList = new ArrayList<String>();
        authorizedScopeForPageList.add("DOMAIN");
        instance.setAuthorizedScopeForPageList(authorizedScopeForPageList);
        expResult = TgolKeyStore.PAGE_LIST_VIEW_NAME;
        request = new MockHttpServletRequest();
        request.addParameter(TgolKeyStore.WEBRESOURCE_ID_KEY, "2");
        result = instance.displayPageList(request, response, model);
        assertEquals(expResult, result);
    }
 
    /**
     * 
     * @param actDataService
     * @param webResourceDataServiceDecorator 
     */
    private void mockAuditStatisticsFactory(
            ActDataService actDataService, 
            WebResourceDataServiceDecorator webResourceDataServiceDecorator){
        ParameterDataService parameterDataService = new MockParameterDataService();
        ThemeDataService themeDataService = new MockThemeDataService();
        AuditStatisticsFactory.getInstance().setActDataService(actDataService);
        AuditStatisticsFactory.getInstance().setWebResourceDataService(webResourceDataServiceDecorator);
        AuditStatisticsFactory.getInstance().setParameterDataService(parameterDataService);
        AuditStatisticsFactory.getInstance().setThemeDataService(themeDataService);
    }
    
}