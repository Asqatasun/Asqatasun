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
package org.opens.tgol.controller;

import junit.framework.TestCase;
import org.opens.tgol.util.TgolKeyStore;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

/**
 *
 * @author jkowalczyk
 */
public class LoginControllerTest extends TestCase {
    
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
        LoginController instance = new LoginController();
        String expResult = TgolKeyStore.LOGIN_VIEW_NAME;
        String result = instance.displayLoginPage(model);
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
    
}