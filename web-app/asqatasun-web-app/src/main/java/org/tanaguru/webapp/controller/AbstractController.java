/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.tanaguru.webapp.controller;

import java.util.Calendar;
import java.util.Collection;
import org.apache.log4j.Logger;
import org.tanaguru.webapp.entity.contract.Contract;
import org.tanaguru.webapp.entity.service.contract.ContractDataService;
import org.tanaguru.webapp.entity.service.user.UserDataService;
import org.tanaguru.webapp.entity.user.User;
import org.tanaguru.webapp.exception.ForbiddenUserException;
import org.tanaguru.webapp.presentation.factory.DetailedContractInfoFactory;
import org.tanaguru.webapp.security.userdetails.TgolUserDetails;
import org.tanaguru.webapp.util.TgolKeyStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


/**
 * This abstract controller handles methods to retrieve data about the user
 * authentication in the current session.
 * @author jkowalczyk
 */
@Controller
public abstract class AbstractController {
    
    private static final String ANONYMOUS_USER = "anonymousUser";

    private UserDataService userDataService;
    public UserDataService getUserDataService() {
        return userDataService;
    }

    @Autowired
    public final void setUserDataService(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    private ContractDataService contractDataService;
    public ContractDataService getContractDataService() {
        return contractDataService;
    }

    @Autowired
    public final void setContractDataService(ContractDataService contractDataService) {
        this.contractDataService = contractDataService;
    }

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
        Logger.getLogger(this.getClass()).info(SecurityContextHolder.getContext().getAuthentication().getName());
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     * This method determines whether the authenticated user of the current session
     * is a guest
     * @return
     *      true if the the authenticated user of the current session is a
     *      guest, otherwise false.
     */
    protected boolean isGuestUser() {
        Collection<? extends GrantedAuthority> authorities =
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (authorities.size() == 1
                && authorities.iterator().next().getAuthority().equalsIgnoreCase(TgolKeyStore.ROLE_GUEST_KEY)) {
            return true;
        }
        return false;
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
        if (authorities != null && authorities.size() == 1
                && authorities.iterator().next().getAuthority().equalsIgnoreCase(TgolKeyStore.ROLE_ADMIN_KEY)) {
            return true;
        }
        return false;
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
            return (Calendar.getInstance().getTime().after(contract.getEndDate())) ? true:false;
        } catch (NullPointerException npe) {
            throw new ForbiddenUserException(getCurrentUser());
        }
    }

    /**
     * To deal with contract expiration this method is defined here and accessible
     * from extended classes when needed.
     * The related jsp uses the IS_CONTRACT_EXPIRED_KEY to enable or not the launch
     * actions.
     * @param contract
     * @param model
     * @return
     */
    protected String displayContractView(Contract contract, Model model) {
        model.addAttribute(TgolKeyStore.CONTRACT_ID_VALUE, contract.getId());
        model.addAttribute(TgolKeyStore.DETAILED_CONTRACT_INFO,
                    DetailedContractInfoFactory.getInstance().getDetailedContractInfo(contract));
        model.addAttribute(TgolKeyStore.IS_CONTRACT_EXPIRED_KEY,
                isContractExpired(contract));
        return TgolKeyStore.CONTRACT_VIEW_NAME;
    }

}