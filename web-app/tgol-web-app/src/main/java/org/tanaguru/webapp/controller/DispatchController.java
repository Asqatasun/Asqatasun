/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.webapp.controller;

import org.tanaguru.util.TgolKeyStore;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
 *
 * @author jkowalczyk
 */
@Controller
public class DispatchController extends AbstractController{

    public DispatchController() {
        super();
    }

    /**
     * 
     * @param model
     * @return 
     */
    @RequestMapping(value = TgolKeyStore.DISPATCH_URL, method=RequestMethod.GET)
    public String displayMainPage (Model model) {
        if (!isAuthenticated()) {
            return displayLoginPage(model);
        } else {
            return displayHomePage(model);
        }
    }

    /**
     * 
     * @param model
     * @return 
     */
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String displayHomePage(Model model) {
        return TgolKeyStore.HOME_VIEW_REDIRECT_NAME;
    }

    /**
     * 
     * @param model
     * @return 
     */
    public String displayLoginPage(Model model) {
        return TgolKeyStore.LOGIN_VIEW_NAME;
    }

}