/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2012  Open-S Company
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
 *  Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tgol.test.scenario;

import java.util.ResourceBundle;
import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 *
 * @author jkowalczyk
 */
public class AbstractWebDriverTestClass extends TestCase {

    protected static final String LOGIN_BUTTON_NAME = "Login";
    protected static String EMAIL_FIELD_NAME = "email";
    protected static String NAME_FIELD_NAME = "lastName";
    protected static String FIRST_NAME_FIELD_NAME = "firstName";
    protected static String PASSWORD_FIELD_NAME = "password";
    protected static String CONFIRM_PASSWORD_FIELD_NAME = "confirmPassword";
    protected static String LABEL_FIELD_NAME = "label";
    protected static String ADMIN_ELEMENT_ID = "admin1";
    protected static String ACTIVATED_ELEMENT_ID = "activated1";
    private static final String HTML_TAG_NAME = "html";
    
    protected static String ADD_USER_FORM_SUBMIT_XPATH_LOCATION = 
            "//div[@id='sign-up-submit']/input";
    protected static String DELETE_USER_FORM_SUBMIT_XPATH_LOCATION = 
            "//div[@class='alert-actions']/form/input";
    protected static String NEW_USER_CONTRACT_MNGT_XPATH_LOCATION = 
            "//table[@id='user-list-table']/tbody/tr[x]/td[7]/a/img";
    protected static String USER_TABLE_BODY_XPATH_LOCATION = 
            "//table[@id='user-list-table']/tbody";
    private static String EDIT_CONTRACT_FORM_SUBMIT_XPATH_LOCATION = 
            "//div[@id='edit-contract-form-submit']/input";
    
    protected static String NOT_ALLOWED_MSG = 
            "You are not allowed to access this resource.";
    
    protected static final String ASSERT_TEXT_FAILED = "assertText failed";
    protected static final String ASSERT_TEXT_PRESENT_FAILED = "assertTextPresent failed";
    protected static final String ASSERT_TEXT_ABSENT_FAILED = "assertTextAbsence failed";
    
    protected static final String BUNDLE_NAME = "test";
    
    protected static final String NEW_USER_EMAIL = "newUser@open-s.com";
    protected static final String NEW_USER_NAME = "UserName";
    protected static final String NEW_USER_FIRST_NAME = "UserFirstName";
    protected static final String NEW_USER_PASSWORD = "AabB1234";
    protected static final String NEW_CONTRACT_LABEL = "CONTRACT LABEL";
    
    private static final String LOGIN_URL_KEY = "login_url";
    private static final String LOGOUT_URL_KEY = "logout_url";
    private static final String ADD_USER_URL_KEY = "add_user_url";
    private static final String EDIT_USER_URL_KEY = "edit_user_url";
    private static final String DELETE_USER_URL_KEY = "delete_user_url";
    private static final String ADD_CONTRACT_URL_KEY = "add_contract_url";
    private static final String CONTRACT_URL_KEY = "contract_url";
    private static final String AUDIT_PAGES_URL_KEY = "audit_pages_url";
    private static final String AUDIT_SITE_URL_KEY = "audit_site_url";
    private static final String AUDIT_UPLOAD_URL_KEY = "audit_upload_url";
    private static final String AUDIT_SCENARIO_URL_KEY = "audit_scenario_url";
    private static final String ADD_USER_CONTRACT_URL_KEY = "add_user_contract_url";
    private static final String EDIT_USER_CONTRACT_URL_KEY = "edit_user_contract_url";
    private static final String ADMIN_URL_KEY = "admin_url";
    
    private static final String USER_FIELD_NAME_KEY = "user_field_name";
    private static final String PASSWORD_FIELD_NAME_KEY = "password_field_name";
    
    private static final String USER_KEY = "admin_user";
    private static final String HOST_LOCATION_KEY = "host_location";
    private static final String PASSWORD_KEY = "admin_password";
    
    /*
     * Context info
     */
    private String hostLocation;
    public String getHostLocation() {
        return hostLocation;
    }

    // Url info
    private String loginUrl;
    private String logoutUrl;
    private String adminUrl;
    private String addUserUrl;
    private String editUserUrl;
    private String deleteUserUrl;
    private String addContractUrl;
    private String contractUrl;
    private String auditPagesSetupUrl;
    private String auditSiteSetupUrl;
    private String auditUploadSetupUrl;
    private String auditScenarioSetupUrl;
    private String addUserContractUrl;
    private String editUserContractUrl;
    
    private String userFieldName;
    private String passwordFieldName;
    
    private String user;
    private String password;

    FirefoxDriver driver;
    
    private String newUserId;
    private String newContractId;
    
    @Override
    public void setUp() throws Exception {
        initialize();
    }
    
    @Override
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.close();
        }
    }
    
    /**
     *
     */
    private void initialize(){

        // These parameters has to passed as JVM argument
        user = System.getProperty(USER_KEY);
        password = System.getProperty(PASSWORD_KEY);
        hostLocation = System.getProperty(HOST_LOCATION_KEY);
        
        ResourceBundle parametersBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        
        userFieldName = parametersBundle.getString(USER_FIELD_NAME_KEY);
        passwordFieldName = parametersBundle.getString(PASSWORD_FIELD_NAME_KEY);
        loginUrl = hostLocation + parametersBundle.getString(LOGIN_URL_KEY);
        logoutUrl = hostLocation + parametersBundle.getString(LOGOUT_URL_KEY);
        adminUrl = hostLocation + parametersBundle.getString(ADMIN_URL_KEY);
        addUserUrl = hostLocation + parametersBundle.getString(ADD_USER_URL_KEY);
        editUserUrl = hostLocation + parametersBundle.getString(EDIT_USER_URL_KEY);
        deleteUserUrl = hostLocation + parametersBundle.getString(DELETE_USER_URL_KEY);
        addContractUrl = hostLocation + parametersBundle.getString(ADD_CONTRACT_URL_KEY);
        contractUrl = hostLocation + parametersBundle.getString(CONTRACT_URL_KEY);
        auditPagesSetupUrl = hostLocation + parametersBundle.getString(AUDIT_PAGES_URL_KEY);
        auditSiteSetupUrl = hostLocation + parametersBundle.getString(AUDIT_SITE_URL_KEY);
        auditUploadSetupUrl = hostLocation + parametersBundle.getString(AUDIT_UPLOAD_URL_KEY);
        auditScenarioSetupUrl = hostLocation + parametersBundle.getString(AUDIT_SCENARIO_URL_KEY);
        addUserContractUrl = hostLocation + parametersBundle.getString(ADD_USER_CONTRACT_URL_KEY);
        editUserContractUrl = hostLocation + parametersBundle.getString(EDIT_USER_CONTRACT_URL_KEY);
        
        if (driver == null) {
            driver = new FirefoxDriver(new FirefoxProfile());
        }
    }
    
    /**
     * 
     */
    protected void logout() {
        driver.get(logoutUrl);
    }
    
    /**
     * 
     */
    protected void loginAsRoot() {
        driver.get(loginUrl);
        loginAs(user, password);
    }
    
    /**
     * 
     * @param userName
     * @param password 
     */
    protected void loginAs(String userName, String password) {
        driver.get(loginUrl);
        editUserField(userName);
        editPasswordField(password);
        driver.findElement(By.name(LOGIN_BUTTON_NAME)).click();
    }
    
    /**
     * 
     */
    protected void loginAsNewUser() {
        loginAs(NEW_USER_EMAIL, NEW_USER_PASSWORD);
    }
    
    /**
     * 
     */
    protected void goToAdminPage() {
        driver.get(adminUrl);
    }
    
    /**
     * 
     */
    protected void goToAddUserPage() {
        driver.get(addUserUrl);
    }
    
    /**
     * 
     */
    protected void goToEditUserPage() {
        driver.get(editUserUrl+newUserId);
    }
    
    /**
     * 
     */
    protected void goToAddUserContractPage() {
        driver.get(addUserContractUrl+newUserId);
    }
    
    /**
     * 
     */
    protected void goToEditUserContractPage() {
        driver.get(editUserContractUrl+newContractId);
    }
    
    /**
     * 
     */
    protected void goToNewContractPage() {
        driver.get(contractUrl+newContractId);
    }
    
    /**
     * 
     */
    protected void goToAuditPagesSetUpContractPage() {
        driver.get(auditPagesSetupUrl+newContractId);
    }
    
    /**
     * 
     */
    protected void goToAuditSiteSetUpContractPage() {
        driver.get(auditSiteSetupUrl+newContractId);
    }
    
    /**
     * 
     */
    protected void goToAuditUploadSetUpContractPage() {
        driver.get(auditUploadSetupUrl+newContractId);
    }
    
    /**
     * 
     */
    protected void goToAuditScenarioSetUpContractPage() {
        driver.get(auditScenarioSetupUrl+newContractId);
    }
    
    /**
     * 
     */
    protected void goToDeleteUserPage() {
        driver.get(deleteUserUrl+newUserId);
    }
    
    /**
     * 
     */
    protected void goToAddContractPage() {
        driver.get(addContractUrl);
    }
    
    /**
     * 
     * @param elementName
     * @return 
     */
    protected WebElement getWebElementById(String elementName) {
        return driver.findElement(By.id(elementName));
    }
    
    /**
     * 
     * @param userValue 
     */
    private void editUserField(String userValue) {
        editWebElement(userFieldName, userValue);
    }
    
    /**
     * 
     * @param passwordValue 
     */
    private void editPasswordField(String passwordValue) {
        editWebElement(passwordFieldName, passwordValue);
    }
    
    /**
     * 
     * @param elementName
     * @param elementValue 
     */
    protected void editWebElement(String elementName, String elementValue) {
        WebElement webElement = getWebElementById(elementName);
        webElement.click();
        webElement.clear();
        webElement.sendKeys(elementValue);
    }
    
    /**
     * 
     * @param elementName
     * @param elementValue 
     */
    protected void selectWebElement(String elementName) {
        if (!driver.findElement(By.id(elementName)).isSelected()) {
            driver.findElement(By.id(elementName)).click();
        }
    }
    
    /**
     * 
     * @param text 
     */
    protected void checkTextPresence(String text) {
        if (!driver.findElement(By.tagName(HTML_TAG_NAME)).getText().contains(text)) {
            driver.close();
            throw new RuntimeException(ASSERT_TEXT_PRESENT_FAILED);
        }
    }
    
    /**
     * 
     * @param text 
     */
    protected void checkElementTextPresence(String element,String value) {
        if (!driver.findElement(By.xpath(element)).getText().contains(value)) {
            driver.close();
            throw new RuntimeException(ASSERT_TEXT_FAILED);
        }
    }
    
    /**
     * 
     * @param text 
     */
    protected void checkElementTextAbsence(String element,String value) {
        if (driver.findElement(By.xpath(element)).getText().contains(value)) {
            driver.close();
            throw new RuntimeException(ASSERT_TEXT_FAILED);
        }
    }
    
    /**
     * 
     * @param text 
     */
    protected void checkElementTextPresenceByCssSelector(String element,String value) {
        if (!driver.findElement(By.cssSelector(element)).getText().contains(value)) {
            driver.close();
            throw new RuntimeException(ASSERT_TEXT_FAILED);
        }
    }
    
    /**
     * 
     * @param text 
     */
    protected void checkTextAbscence(String text) {
        if (driver.findElement(By.tagName(HTML_TAG_NAME)).getText().contains(text)) {
            driver.close();
            throw new RuntimeException(ASSERT_TEXT_ABSENT_FAILED);
        }
    }
 
    /**
     * The id of the new user is not predictible. Thus, once a page with the
     * user parameter is displayed, we extract the value, and reuse it for the 
     * end of the test
     */
    protected void extractedIdOfNewUser() {
        newUserId = driver.getCurrentUrl().substring(driver.getCurrentUrl().indexOf("=")+1);
    }
    
    /**
     * The id of the new contract is not predictible. Thus, once a page with the
     * cr parameter is displayed, we extract the value, and reuse it for the 
     * end of the test
     */
    protected void extractedIdOfNewContract() {
        newContractId = driver.getCurrentUrl().substring(driver.getCurrentUrl().indexOf("=")+1);
    }
    
    /**
     * 
     * @param activateUser 
     */
    protected void createNewUser(boolean activateUser) {
        goToAddUserPage();
        editWebElement(EMAIL_FIELD_NAME,NEW_USER_EMAIL);
        editWebElement(PASSWORD_FIELD_NAME , NEW_USER_PASSWORD);
        editWebElement(CONFIRM_PASSWORD_FIELD_NAME , NEW_USER_PASSWORD);
        editWebElement(NAME_FIELD_NAME,NEW_USER_NAME);
        editWebElement(FIRST_NAME_FIELD_NAME,NEW_USER_FIRST_NAME);
        if (activateUser) {
            selectWebElement(ACTIVATED_ELEMENT_ID);
        }
        driver.findElement(By.xpath(ADD_USER_FORM_SUBMIT_XPATH_LOCATION)).click();
    }

    /**
     * 
     */
    protected void deleteNewUser() {
        goToDeleteUserPage();
        // validate the user deletion
        driver.findElement(By.xpath(DELETE_USER_FORM_SUBMIT_XPATH_LOCATION)).click();
    }
    
    /**
     * 
     */
    protected void submitEditUserContract() {
        driver.findElement(By.xpath(EDIT_CONTRACT_FORM_SUBMIT_XPATH_LOCATION)).click();
    }
    
    /**
     * 
     */
    protected void createNewContract() {
        createNewUser(true);
        // go to the management contracts page of the new user
        
        driver.findElement(By.xpath(NEW_USER_CONTRACT_MNGT_XPATH_LOCATION.replaceAll("x",findNewUserRowIndexLocation()))).click();
        //----- At this time the id of the new user appears in an url for the first time
        extractedIdOfNewUser();
        goToAddUserContractPage();
        
        editWebElement(LABEL_FIELD_NAME,NEW_CONTRACT_LABEL);
        submitEditUserContract();
    }
    
    protected String findNewUserRowIndexLocation () {
        WebElement we = driver.findElement(By.xpath(USER_TABLE_BODY_XPATH_LOCATION));
        if (!StringUtils.equals(we.getTagName(), "tbody")) {
            throw new RuntimeException();
        }
        return String.valueOf(we.findElements(By.tagName("tr")).size());
    }
    
    protected String findAdminUserRowIndexLocation () {
        WebElement we = driver.findElement(By.xpath(USER_TABLE_BODY_XPATH_LOCATION));
        if (!StringUtils.equals(we.getTagName(), "tbody")) {
            throw new RuntimeException();
        }
        int rowIndex = 0;
        for (WebElement wec : we.findElements(By.tagName("tr"))) {
            rowIndex++;
            String userName = wec.findElement(By.className("col01")).getText();
            if (StringUtils.equals(userName, user)) {
                break;
            }
        }
        return String.valueOf(rowIndex);
    }
    
}