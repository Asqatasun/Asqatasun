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

import org.asqatasun.webapp.util.TgolKeyStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static org.asqatasun.webapp.util.TgolKeyStore.*;

/** 
 *
 * @author jkowalczyk
 */
@Controller
public class LoginController extends AbstractUserAndContractsController{


    @GetMapping(value = {LOGIN_URL, "/"})
    public String displayLoginPage () {
        return (isAuthenticated())? HOME_VIEW_REDIRECT_NAME: LOGIN_VIEW_NAME;
    }

    @GetMapping(value = ACCESS_DENIED_URL)
    public String displayAccessDeniedPage() {
        return ACCESS_DENIED_VIEW_NAME;
    }


}
