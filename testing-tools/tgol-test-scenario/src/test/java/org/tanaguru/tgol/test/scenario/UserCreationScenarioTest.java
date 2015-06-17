/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Tanaguru.org
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.tgol.test.scenario;

import org.openqa.selenium.By;

/**
 *
 * @author jkowalczyk
 */
public class UserCreationScenarioTest extends AbstractWebDriverTestClass {

    private static String IMPOSSIBLE_ACTION = "Impossible action";

    private static String ADMIN_PAGE_TITLE = "Administration";
    private static String USER_CREATION_SUCCESS_MSG = 
            "The user "+NEW_USER_EMAIL +" has been added with success";
    private static String USER_DELETION_SUCCESS_MSG = 
            "The user "+NEW_USER_EMAIL+" has been deleted with success";
    private static String USER_DELETION_CONFIRMATION_MSG = 
            "You are about to delete the user "+NEW_USER_EMAIL+". Are you sure?";
    private static String USER_MODIFICATION_SUCCESS_MSG = 
            "The user "+NEW_USER_EMAIL+" has been modified with success";
    private static String ACCOUNT_NOT_ACTIVATED_MSG = 
            "Account not activated.";
    
    private static String ADMIN_USER_DELETE_LINK_XPATH_LOCATION = 
            "//table[@id='user-list-table']/tbody/tr[x]/td[6]";
    
    private static String NEW_USER_EDIT_LINK_XPATH_LOCATION = 
            "//table[@id='user-list-table']/tbody/tr[x]/td[5]/a/img";
    
    private static String EDIT_USER_FORM_SUBMIT_XPATH_LOCATION = 
            "//div[@id='account-settings-form-submit']/input";
    
    private static String NEW_USER_ACTIVATION_INFO_XPATH_LOCATION = 
            "//table[@id='user-list-table']/tbody/tr[x]/td[4]";
    
    /**
     * 
     */
    public void testUserCreation() {

        loginAsRoot();

        goToAdminPage();
        checkTextAbscence(NEW_USER_EMAIL);
        checkTextPresence(ADMIN_PAGE_TITLE);
        String adminUserDeleteLinkXpath = ADMIN_USER_DELETE_LINK_XPATH_LOCATION.replaceAll("x", findAdminUserRowIndexLocation());
        if (!driver.findElement(By.xpath(adminUserDeleteLinkXpath)).getText().equals(IMPOSSIBLE_ACTION)) {
            driver.close();
            throw new RuntimeException(ASSERT_TEXT_FAILED);
        }

        // go to the add user page to create a new user
        createNewUser(false);
        
        checkTextPresence(USER_CREATION_SUCCESS_MSG);
        checkTextPresence(NEW_USER_EMAIL);
        checkTextPresence(NEW_USER_NAME);
        checkTextPresence(NEW_USER_FIRST_NAME);
        String newUserActivationXpath = NEW_USER_ACTIVATION_INFO_XPATH_LOCATION.replaceAll("x", findNewUserRowIndexLocation());
        if (!driver.findElement(By.xpath(newUserActivationXpath)).getText().equals("no")) {
            driver.close();
            throw new RuntimeException(ASSERT_TEXT_FAILED);
        }

        logout();
        // The login of the new user fails, the account is not activated
        loginAsNewUser();
        checkTextPresence(ACCOUNT_NOT_ACTIVATED_MSG);
        
        loginAsRoot();
        goToAdminPage();
        
        String newUserEditLinkXpath = NEW_USER_EDIT_LINK_XPATH_LOCATION.replaceAll("x", findNewUserRowIndexLocation());
        // activate the new user
        driver.findElement(By.xpath(newUserEditLinkXpath)).click();
        //----- At this time the id of the new user appears in an url for the first time
        extractedIdOfNewUser();
        //-----
        selectWebElement(ACTIVATED_ELEMENT_ID);
        driver.findElement(By.xpath(EDIT_USER_FORM_SUBMIT_XPATH_LOCATION)).click();
        
        checkTextPresence(USER_MODIFICATION_SUCCESS_MSG);
        checkTextPresence(NEW_USER_NAME);
        checkTextPresence(NEW_USER_FIRST_NAME);
        if (!driver.findElement(By.xpath(newUserActivationXpath)).getText().equals("yes")) {
            driver.close();
            throw new RuntimeException(ASSERT_TEXT_FAILED);
        }
        
        logout();
        loginAsNewUser();
        
        // The current user is activated but not admin. All the admin resources
        // are inaccessible
        goToAdminPage();
        checkTextPresence(NOT_ALLOWED_MSG);
        goToAddUserPage();
        checkTextPresence(NOT_ALLOWED_MSG);
        goToAddContractPage();
        checkTextPresence(NOT_ALLOWED_MSG);

        logout();
        loginAsRoot();
        
        goToAdminPage();
        
        // go to the edit user page and set the user as admin
        goToEditUserPage();
        selectWebElement(ADMIN_ELEMENT_ID);
        driver.findElement(By.xpath(EDIT_USER_FORM_SUBMIT_XPATH_LOCATION)).click();
        
        logout();
        loginAsNewUser();

        goToAdminPage();
        checkTextPresence(ADMIN_PAGE_TITLE);
        
        logout();
        loginAsRoot();

        goToAdminPage();

        // go to the delete user page
        goToDeleteUserPage();
        checkTextPresence(USER_DELETION_CONFIRMATION_MSG);

        // validate the user deletion
        driver.findElement(By.xpath(DELETE_USER_FORM_SUBMIT_XPATH_LOCATION)).click();
        
        checkTextPresence(USER_DELETION_SUCCESS_MSG);
        checkTextAbscence(NEW_USER_NAME);
        
        logout();

    }
    
}