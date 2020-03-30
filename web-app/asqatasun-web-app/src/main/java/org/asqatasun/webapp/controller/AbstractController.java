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

import java.util.Calendar;
import java.util.Collection;
import org.asqatasun.webapp.entity.contract.Contract;
import org.asqatasun.webapp.entity.service.user.UserDataService;
import org.asqatasun.webapp.entity.user.User;
import org.asqatasun.webapp.exception.ForbiddenUserException;
import org.asqatasun.webapp.security.userdetails.TgolUserDetails;
import org.asqatasun.webapp.util.TgolKeyStore;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * This abstract controller handles methods to retrieve data about the user
 * authentication in the current session.
 * @author jkowalczyk
 */
public abstract class AbstractController {
    
    private static final String ANONYMOUS_USER = "anonymousUser";

    @Autowired
    protected UserDataService userDataService;

    public AbstractController() {}

    /**
     * This method determines whether a user is authenticated in the current
     * session
     * @return
     *      true if a user is authenticated in the current session, false
     *      otherwise.
     */
    protected boolean isAuthenticated() {
        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            return false;
        // By default, an unauthenticated user is authenticated by the application as
        // an anonymousUser
        } else if (SecurityContextHolder.getContext().getAuthentication().getName().
                equalsIgnoreCase(ANONYMOUS_USER)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method returns the name of the current authenticated user.
     * @return
     *      the name of the current authenticated user
     */
    protected String getAuthenticatedUsername() {
        LoggerFactory.getLogger(this.getClass()).info(SecurityContextHolder.getContext().getAuthentication().getName());
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     * This method determines whether the authenticated user of the current session
     * is an admin guest
     * @return
     *      true if the the authenticated user of the current session is a
     *      guest, otherwise false.
     */
    protected boolean isAdminUser() {
        Collection<? extends GrantedAuthority> authorities =
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return authorities != null && authorities.size() == 1
            && authorities.iterator().next().getAuthority().equalsIgnoreCase(TgolKeyStore.ROLE_ADMIN_KEY);
    }

    /**
     * @return the authenticated user of the current session.
     *
     */
    protected User getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof TgolUserDetails) {
            return ((TgolUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        }
        return null;
    }
    
    /**
     * 
     * @param user 
     */
    protected void updateCurrentUser(User user) {
        TgolUserDetails userDetails = ((TgolUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        userDetails.updateUser(user);
    }

    /**
     *
     * @return
     *      the ip of the connected client
     */
    protected String getClientIpAddress() {
        WebAuthenticationDetails details =
                (WebAuthenticationDetails)SecurityContextHolder.getContext().getAuthentication().getDetails();
        return details.getRemoteAddress();
    }

    /**
     * 
     * @param contract
     * @return
     */
    protected boolean isContractExpired(Contract contract) {
        try {
            return Calendar.getInstance().getTime().after(contract.getEndDate());
        } catch (NullPointerException npe) {
            throw new ForbiddenUserException(getCurrentUser());
        }
    }

}
