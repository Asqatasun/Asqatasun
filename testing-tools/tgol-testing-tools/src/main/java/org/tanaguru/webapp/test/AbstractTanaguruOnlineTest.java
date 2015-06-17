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
package org.tanaguru.webapp.test;

import java.util.ResourceBundle;
import java.util.logging.Level;
import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.webapp.test.data.KrashtestResult;

/**
 * This class uses Selenium to automatically test tanaguru in web application
 * mode. The url of the webapp to test, the user and password to login, the
 * contract id, the firefox binary location and the xvfb display value are
 * passed as JVM arguments
 *
 * @author jkowalczyk
 */
public abstract class AbstractTanaguruOnlineTest extends TestCase {

    private final Logger LOGGER = Logger.getLogger(this.getClass());
    private static final String LOADING_ERROR_STR_EN =
            "A problem occured while loading the content of the page";
    private static final String PROCESSING_ERROR_STR_EN =
            "A problem occured while adapting the page";
    /**
     * Keys in result page that enable to determine the result of the test
     */
    private static final String PASSED_KEY = "Passed";
    private static final String FAILED_KEY = "Failed";
    private static final String NMI_KEY = "Need More Information";
    private static final String NA_KEY = "Not Applicable";
    private static final String NT_KEY = "Not tested";
    private static final String SUCCESS_STR1 = "tgm-result-page"; // class associated with result page
    private static final String SUCCESS_STR2 = "tgm-page-list-f2xx"; // class associated with page-list-f2xx
    public static final String RULE_NOT_YET_IMPLEMENTED =
            "RULE_NOT_YET_IMPLEMENTED";
    /**
     * Parameters keys retrieved through property file
     */
    protected static final String BUNDLE_NAME = "test";
    protected static final String LOGIN_URL_KEY = "login_url";
    protected static final String LOGOUT_URL_KEY = "logout_url";
    protected static final String FORM_URL_KEY = "form_url";
    protected static final String FIELD_NAME_KEY = "field_name";
    protected static final String USER_FIELD_NAME_KEY = "user_field_name";
    protected static final String PASSWORD_FIELD_NAME_KEY = "password_field_name";
    /**
     * Parameters keys passed as JVM arguments
     */
    protected static final String USER_KEY = "user";
    protected static final String PASSWORD_KEY = "password";
    protected static final String CONTRACT_ID_KEY = "contract.id";
    protected static final String HOST_LOCATION_KEY = "host.location";
    protected static final String XVFB_DISPLAY_KEY = "xvfb.display";
    /**
     * Submit elements in the page
     */
    protected static final String SUBMIT_BUTTON_NAME = "launch-audit-submit";
    protected static final String LOGIN_BUTTON_NAME = "Login";
    // Application urls used to navigate
    protected String loginUrl;
    protected String logoutUrl;
    protected String formUrl;
    // field names to edit user info and login
    protected String fieldName;
    protected String userFieldName;
    protected String passwordFieldName;
    /**
     * Xvfb display port value used in headless mode
     */
    protected String xvfbDisplay;
    /**
     * User info
     */
    protected String user;
    protected String password;
    protected String contractId;
    /**
     * User info
     */
    protected String hostLocation;
    /**
     * The firefox driver. The webdriver.firefox.bin is supposed to passed as
     * JVM argument.
     */
    protected FirefoxDriver driver;

    /**
     * Default constructor
     */
    public AbstractTanaguruOnlineTest() {
        initialize();
    }

    @Override
    public void setUp() throws Exception {
    }

    @Override
    public void tearDown() throws Exception {
        driver.get(logoutUrl);
    }

    /**
     *
     */
    private void initialize() {
        // These parameters has to passed as JVM argument
        user = System.getProperty(USER_KEY);
        password = System.getProperty(PASSWORD_KEY);
        hostLocation = System.getProperty(HOST_LOCATION_KEY);
        contractId = System.getProperty(CONTRACT_ID_KEY);
        xvfbDisplay = System.getProperty(XVFB_DISPLAY_KEY);

        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME);
        userFieldName = bundle.getString(USER_FIELD_NAME_KEY);
        passwordFieldName = bundle.getString(PASSWORD_FIELD_NAME_KEY);
        fieldName = bundle.getString(FIELD_NAME_KEY);
        loginUrl = hostLocation + bundle.getString(LOGIN_URL_KEY);
        formUrl = hostLocation + bundle.getString(FORM_URL_KEY) + contractId;
        logoutUrl = hostLocation + bundle.getString(LOGOUT_URL_KEY);

        driver = WebDriverFactory.getInstance().getFirefoxDriver(xvfbDisplay);
    }

    /**
     * This method launches an audit (via selenium) for a given url
     *
     * @param siteName
     * @param url
     * @return
     */
    protected String launchTanaguru(String siteName, String[] url, boolean displayAllResult) {
        login();
        driver.get(formUrl);
        if (url.length > 1) {
            driver.findElementByCssSelector("form#auditSetUpCommand fieldset div.clearfix div.url-input a").click();
        }
        for (int i = 0; i < url.length; i++) {
            LOGGER.info("testing   " + url[i]);
            driver.findElementById(fieldName + i).sendKeys(url[i]);
        }
        driver.findElementById(SUBMIT_BUTTON_NAME).submit();
        if (displayAllResult) {
            driver.findElementById("sortOptionMaptest-result5").click();
            driver.findElementByCssSelector("#result-option-console-update input").submit();
            driver.findElementByCssSelector("#expand-all").click();
        }
        String responseBody = driver.getPageSource();
        return responseBody;
    }

    /**
     * This method determines whether an audit terminates successfully
     *
     * @param bodyText
     * @return
     */
    protected String computeWebappResult(String bodyText) {
        KrashtestResult webappResult = KrashtestResult.KRASH;
        if (bodyText.contains(SUCCESS_STR1) || bodyText.contains(SUCCESS_STR2)) {
            webappResult = KrashtestResult.SUCCESS;
        } else if (bodyText.contains(LOADING_ERROR_STR_EN)) {
            webappResult = KrashtestResult.ERROR_WHILE_LOADING;
        } else if (bodyText.contains(PROCESSING_ERROR_STR_EN)) {
            webappResult = KrashtestResult.ERROR_WHILE_PROCESSING;
        }
        LOGGER.info(webappResult.toString());
        return webappResult.toString();
    }

    /**
     * This method extracts the result for a given test from the tanaguru
     * response
     *
     * @param testName
     * @return
     */
    protected String computeTestResult(String testName) {
        LOGGER.info("Searching result for test " + testName);
        String result = driver.findElementByXPath(
                "//h4[text()='"
                + testName
                + "']/parent::*/parent::*/child::*/img").getAttribute("alt");
        LOGGER.info("Found Result " + result);
        if (result.contains(PASSED_KEY)) {
            return TestSolution.PASSED.name();
        } else if (result.contains(FAILED_KEY)) {
            return TestSolution.FAILED.name();
        } else if (result.contains(NMI_KEY)) {
            return TestSolution.NEED_MORE_INFO.name();
        } else if (result.contains(NA_KEY)) {
            return TestSolution.NOT_APPLICABLE.name();
        } else if (result.contains(NT_KEY)) {
            return TestSolution.NOT_TESTED.name();
        } else {
            return RULE_NOT_YET_IMPLEMENTED;
        }
    }

    /**
     *
     */
    protected void login() {
        driver.get(loginUrl);
        try {

            driver.findElementById(userFieldName).sendKeys(user);
            driver.findElementById(passwordFieldName).sendKeys(password);
            driver.findElementByName(LOGIN_BUTTON_NAME).submit();
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(AbstractTanaguruOnlineTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}