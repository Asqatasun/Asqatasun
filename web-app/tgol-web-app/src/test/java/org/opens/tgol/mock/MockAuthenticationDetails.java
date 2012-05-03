/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2012  Open-S Company
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
package org.opens.tgol.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

public class MockAuthenticationDetails implements Authentication {

    private static final long serialVersionUID = 1L;
    private Collection<GrantedAuthority> grantedAuthorities;
    private String userName = null;

    public MockAuthenticationDetails(GrantedAuthority[] grantedAuthorities, String userName) {
        this.grantedAuthorities = new ArrayList<GrantedAuthority>(Arrays.asList(grantedAuthorities));
        this.userName = userName;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public Object getCredentials() {

        return null;
    }

    @Override
    public Object getDetails() {

        return null;
    }

    @Override
    public Object getPrincipal() {

        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return userName != null? true : false;
    }

    @Override
    public void setAuthenticated(boolean arg0)
            throws IllegalArgumentException {
    }

    @Override
    public String getName() {
        return userName;
    }

    public void setAuthorities(GrantedAuthority[] grantedAuthorities) {
        this.grantedAuthorities = new ArrayList<GrantedAuthority>(Arrays.asList(grantedAuthorities));
    }
 
    /**
     * Initialise a mocked security context holder
     * @param userName 
     */
    public static void initSecurityContextHolder(String userName) {
        SecurityContextImpl securityContextImpl = new SecurityContextImpl();
        GrantedAuthority[] grantedAuthorities = new GrantedAuthorityImpl[]{new GrantedAuthorityImpl(
            "ROLE_TEST")};
        MockAuthenticationDetails authenticationDetails = new MockAuthenticationDetails(
                grantedAuthorities, userName);
        securityContextImpl.setAuthentication(authenticationDetails);
        SecurityContextHolder.setContext(securityContextImpl);
    }
    
}