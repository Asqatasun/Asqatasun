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
package org.asqatasun.webapp.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.asqatasun.webapp.entity.contract.Contract;
import org.asqatasun.webapp.security.userdetails.TgolUserDetailsService;
import org.asqatasun.webapp.util.TgolKeyStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

/** 
 *
 * @author jkowalczyk
 */
@Controller
public class LoginController extends AbstractUserAndContractsController{

    private AuthenticationManager authenticationManager;
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    UserDetails guestUserDetails;

    private String guestPassword;
    public void setGuestPassword(String guestPassword) {
        this.guestPassword = guestPassword;
    }
    
    TgolUserDetailsService tgolUserDetailsService;
    @Autowired
    public void setTgolUserDetails(TgolUserDetailsService tgolUserDetailsService) {
        this.tgolUserDetailsService = tgolUserDetailsService;
    }
    
    private LocaleResolver localeResolver;
    @Autowired
    public final void setLocaleResolver(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }
    
    private final Map<String, String> guestListByLang = new LinkedHashMap<>();
    public Map<String, String> getGuestListByLang() {
        return guestListByLang;
    }

    public void setGuestListByLang(Map<String, String> guestListByLang) {
        this.guestListByLang.putAll(guestListByLang);
    }
    
    private final List<String> forbiddenLangForOnlineDemo = new ArrayList<>();
    public List<String> getForbiddenLangForOnlineDemo() {
        return forbiddenLangForOnlineDemo;
    }

    public void setForbiddenLangForOnlineDemo(List<String> forbiddenLangForOnlineDemo) {
        this.forbiddenLangForOnlineDemo.addAll(forbiddenLangForOnlineDemo);
    }
    
    @RequestMapping(value = TgolKeyStore.LOGIN_URL, method=RequestMethod.GET)
    public String displayLoginPage (
            @RequestParam(value=TgolKeyStore.EMAIL_KEY, required=false) String email,
            HttpServletRequest request,
            Model model) {
        if (isAuthenticated()) {
            if (StringUtils.isNotBlank(email)){
                logoutCurrentUser(request);
            } else if (guestListByLang.containsValue(getCurrentUser().getEmail1())) {
                logoutCurrentUser(request);
            } else {
                return TgolKeyStore.HOME_VIEW_REDIRECT_NAME;
            }
        }
        return TgolKeyStore.LOGIN_VIEW_NAME;
    }

    @RequestMapping(value = TgolKeyStore.ACCESS_DENIED_URL, method=RequestMethod.GET)
    public String displayAccessDeniedPage(Model model) {
        return TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
    }
    
    @RequestMapping(value = TgolKeyStore.DEMO_URL, method=RequestMethod.GET)
    public String displayDemoPage(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        Locale locale = localeResolver.resolveLocale(request);
        String languageKey = locale.getLanguage().toLowerCase();
        String lGuestUser=null;

        if (guestListByLang.containsKey(languageKey)) {
            lGuestUser = guestListByLang.get(languageKey);
        } else if (guestListByLang.containsKey("default")) {
            lGuestUser = guestListByLang.get("default");
        }

        if (StringUtils.isBlank(lGuestUser) || StringUtils.isBlank(guestPassword)) {
            return TgolKeyStore.NO_DEMO_AVAILABLE_VIEW_NAME;
        }
        if (isAuthenticated()) {
            return TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
        }
        if (guestUserDetails == null) {
            try {
                guestUserDetails = tgolUserDetailsService.loadUserByUsername(lGuestUser);
            } catch (UsernameNotFoundException unfe) {
                return TgolKeyStore.NO_DEMO_AVAILABLE_VIEW_NAME;
            }
        }

        doGuestAutoLogin(request, lGuestUser);

        if (forbiddenLangForOnlineDemo.contains(languageKey)) {
            return TgolKeyStore.HOME_VIEW_REDIRECT_NAME;
        }
        Collection<Contract> contractSet = getContractDataService().getAllContractsByUser(getCurrentUser());
        if (contractSet == null || contractSet.isEmpty()) {
            return TgolKeyStore.NO_DEMO_AVAILABLE_VIEW_NAME;
        }
        String contractId = contractSet.iterator().next().getId().toString();
        model.addAttribute(TgolKeyStore.CONTRACT_ID_KEY, contractId);
        return TgolKeyStore.AUDIT_PAGE_SET_UP_REDIRECT_NAME;
   }
    
    private void doGuestAutoLogin(HttpServletRequest request, String guestUser) {
        try {
            // Must be called from request filtered by Spring Security, otherwise SecurityContextHolder is not updated
            UsernamePasswordAuthenticationToken token = 
                    new UsernamePasswordAuthenticationToken(guestUser, guestPassword);
            token.setDetails(new WebAuthenticationDetails(request));
            Authentication guest = authenticationManager.authenticate(token);
            Logger.getLogger(this.getClass()).debug("Logging in with [{}]" + guest.getPrincipal());
            SecurityContextHolder.getContext().setAuthentication(guest);
        } catch (Exception e) {
            SecurityContextHolder.getContext().setAuthentication(null);
            Logger.getLogger(this.getClass()).debug("Failure in autoLogin",  e);
        }
    }
    
    /**
     * 
     * @param request 
     */
    private void logoutCurrentUser(HttpServletRequest request) {
         SecurityContextHolder.clearContext();
         HttpSession session = request.getSession(false);
         if (session != null) {
             session.invalidate();
         }
    }

}