/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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