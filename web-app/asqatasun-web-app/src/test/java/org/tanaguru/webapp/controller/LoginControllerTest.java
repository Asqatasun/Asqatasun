/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.tanaguru.webapp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.tanaguru.webapp.util.TgolKeyStore;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

/**
 *
 * @author jkowalczyk
 */
public class LoginControllerTest extends TestCase {
    
    private Authentication mockAuthentication;
    
    public LoginControllerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of displayLoginPage method, of class LoginController.
     */
    public void testDisplayLoginPage() {
        System.out.println("displayLoginPage");
                Model model = new ExtendedModelMap();
        setUpMockAuthenticationContext();
        LoginController instance = new LoginController();
        String expResult = TgolKeyStore.LOGIN_VIEW_NAME;
        String result = instance.displayLoginPage("",new HttpServletRequest() {

            @Override
            public String getAuthType() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Cookie[] getCookies() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public long getDateHeader(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getHeader(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Enumeration getHeaders(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Enumeration getHeaderNames() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public int getIntHeader(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getMethod() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getPathInfo() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getPathTranslated() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getContextPath() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getQueryString() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getRemoteUser() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public boolean isUserInRole(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Principal getUserPrincipal() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getRequestedSessionId() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getRequestURI() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public StringBuffer getRequestURL() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getServletPath() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public HttpSession getSession(boolean bln) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public HttpSession getSession() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public boolean isRequestedSessionIdValid() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public boolean isRequestedSessionIdFromCookie() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public boolean isRequestedSessionIdFromURL() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public boolean isRequestedSessionIdFromUrl() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Object getAttribute(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Enumeration getAttributeNames() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getCharacterEncoding() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void setCharacterEncoding(String string) throws UnsupportedEncodingException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public int getContentLength() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getContentType() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public ServletInputStream getInputStream() throws IOException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getParameter(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Enumeration getParameterNames() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String[] getParameterValues(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Map getParameterMap() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getProtocol() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getScheme() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getServerName() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public int getServerPort() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public BufferedReader getReader() throws IOException {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getRemoteAddr() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getRemoteHost() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void setAttribute(String string, Object o) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void removeAttribute(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Locale getLocale() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public Enumeration getLocales() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public boolean isSecure() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public RequestDispatcher getRequestDispatcher(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getRealPath(String string) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public int getRemotePort() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getLocalName() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getLocalAddr() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public int getLocalPort() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }, model);
        assertEquals(expResult, result);
    }

    /**
     * Test of displayAccessDeniedPage method, of class LoginController.
     */
    public void testDisplayAccessDeniedPage() {
        System.out.println("displayAccessDeniedPage");
        Model model = new ExtendedModelMap();
        LoginController instance = new LoginController();
        String expResult = TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
        String result = instance.displayAccessDeniedPage(model);
        assertEquals(expResult, result);
    }
 
     private void setUpMockAuthenticationContext(){
        // initialise the context with the user identified by the email 
        // "test1@test.com" seen as authenticated
        
        mockAuthentication = createMock(Authentication.class);
        SecurityContextImpl securityContextImpl = new SecurityContextImpl();
        securityContextImpl.setAuthentication(mockAuthentication);
        SecurityContextHolder.setContext(securityContextImpl);
        expect(mockAuthentication.isAuthenticated()).andReturn(Boolean.FALSE);
        replay(mockAuthentication);
    }
}