/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
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
package org.opens.tanaguru.webapp.test;

import com.thoughtworks.selenium.SeleneseTestCase;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import java.util.ResourceBundle;
import java.util.logging.Level;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.webapp.test.data.KrashtestResult;

/**
 * This class uses Selenium to automatically test tanaguru in web
 * application mode.
 * The test.properties file defines the url and the name of the webapp to test
 * @author jkowalczyk
 */
public abstract class AbstractTanaguruOnlineTest extends SeleneseTestCase {

    private static final String LOADING_ERROR_STR_EN =
            "A problem occured while loading the content of the page";
    private static final String PROCESSING_ERROR_STR_EN =
            "A problem occured while adapting the page";
    private static final String LOADING_ERROR_STR_FR =
            "Une erreur est survenue lors du chargement de la page";
    private static final String PROCESSING_ERROR_STR_FR =
            "Une erreur est survenue lors de l'adaptation de la page";
    private static final String PASSED_KEY ="passed";
    private static final String FAILED_KEY ="failed";
    private static final String NMI_KEY ="nmi";
    private static final String NA_KEY ="na";
    private static final String SUCCESS_STR1 = "tgm-result-page"; // class associated with result page
    private static final String SUCCESS_STR2 = "tgm-page-list-f2xx"; // class associated with page-list-f2xx
    private static final String BUNDLE_NAME = "test";
    private static final String RESULT_PATH_KEY = "result_path";
    private static final String USER_CONFIG_PATH_KEY = "user_config_path";
    private static final String HOST_LOCATION_KEY = "host_location";
    private static final String LOGIN_URL_KEY = "login_url";
    private static final String LOGOUT_URL_KEY = "logout_url";
    private static final String BROWSER_NAME_KEY = "browser_name";
    private static final String FIELD_NAME_KEY = "field_name";
    private static final String USER_FIELD_NAME_KEY = "user_field_name";
    private static final String PASSWORD_FIELD_NAME_KEY = "password_field_name";
    private static final String USER_KEY = "user";
    private static final String PASSWORD_KEY = "password";
    private static final String FORM_URL_KEY = "form_url";
    private static final String TEXT_EXTENSION = ".txt";
    private static final String FORM_PAGE_LOAD_TIMER = "1000";
    private static final String LOGIN_TIMER = "1000";
    private static final String RESULT_PAGE_LOAD_TIMER = "500000";
    private static final String SUBMIT_BUTTON_NAME = "input-submit";
    private static final String LOGIN_BUTTON_NAME = "Login";
    public static final String RULE_NOT_YET_IMPLEMENTED =
            "RULE_NOT_YET_IMPLEMENTED";
    private static final char DOUBLE_QUOTE_CHAR = '"';
    private static final String RULE_RESULT_EXPRESSION = "rule-result ";
    protected String resultFilePath;
    protected String userConfigFilePath;
    protected String hostLocation;
    protected String loginUrl;
    protected String logoutUrl;
    protected String formUrl;
    protected String browserName;
    protected String fieldName;
    protected String userFieldName;
    protected String passwordFieldName;
    protected String user;
    protected String password;

    /**
     * Default constructor
     */
    public AbstractTanaguruOnlineTest(){
        initialize();
    }

    @Override
    public void setUp() throws Exception {
        setUp(hostLocation, browserName);
    }

    /**
     *
     */
    private void initialize(){
        ResourceBundle parametersBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        resultFilePath = parametersBundle.getString(RESULT_PATH_KEY);
        userConfigFilePath = parametersBundle.getString(USER_CONFIG_PATH_KEY);
        userFieldName = parametersBundle.getString(USER_FIELD_NAME_KEY);
        passwordFieldName = parametersBundle.getString(PASSWORD_FIELD_NAME_KEY);
        browserName = parametersBundle.getString(BROWSER_NAME_KEY);
        fieldName = parametersBundle.getString(FIELD_NAME_KEY);
        PropertiesConfiguration config = null;
        try {
            config = new PropertiesConfiguration(userConfigFilePath);
        } catch (ConfigurationException ex) {
            java.util.logging.Logger.
                    getLogger(AbstractTanaguruOnlineTest.class.getName()).
                        log(Level.SEVERE, null, ex);
        }
        user = config.getString(USER_KEY);
        password = config.getString(PASSWORD_KEY);
        hostLocation = config.getString(HOST_LOCATION_KEY);
        loginUrl = config.getString(LOGIN_URL_KEY);
        formUrl = config.getString(FORM_URL_KEY);
        logoutUrl = config.getString(LOGOUT_URL_KEY);
    }

    /**
     * This method launches an audit (via selenium) for a given url
     * @param siteName
     * @param url
     * @return
     */
    protected String launchTanaguru(String siteName, String[] url) {
        login();
        selenium.open(formUrl);
        for (int i=0 ; i<url.length ; i++) {
            selenium.type(fieldName+i, url[i]);
        }
        selenium.click(SUBMIT_BUTTON_NAME);
        selenium.waitForPageToLoad(RESULT_PAGE_LOAD_TIMER);
        String responseBody = selenium.getHtmlSource();
        selenium.open(logoutUrl);
        selenium.waitForPageToLoad(FORM_PAGE_LOAD_TIMER);
        return responseBody;
    }

    /**
     * This method determines whether an audit terminates successfully
     * @param bodyText
     * @return
     */
    protected String computeWebappResult(String bodyText){
        KrashtestResult webappResult = KrashtestResult.KRASH;
        if (bodyText.contains(SUCCESS_STR1) || bodyText.contains(SUCCESS_STR2)){
            webappResult = KrashtestResult.SUCCESS;
        } else if (bodyText.contains(LOADING_ERROR_STR_FR) ||
                bodyText.contains(LOADING_ERROR_STR_EN)) {
            webappResult = KrashtestResult.ERROR_WHILE_LOADING;
        } else if (bodyText.contains(PROCESSING_ERROR_STR_FR) ||
                bodyText.contains(PROCESSING_ERROR_STR_EN)) {
            webappResult = KrashtestResult.ERROR_WHILE_PROCESSING;
        }
        return webappResult.toString();
    }

    /**
     * This method extracts the result for a given test from the tanaguru
     * response
     * @param bodyText
     * @param testName
     * @return
     */
    protected String computeWebappResult(String bodyText, String testName){
        int indexFrom = bodyText.indexOf(testName);
        if (indexFrom != -1) {
            indexFrom = bodyText.indexOf(RULE_RESULT_EXPRESSION, indexFrom);
            indexFrom = indexFrom+RULE_RESULT_EXPRESSION.length();
            int indexTo = bodyText.indexOf(DOUBLE_QUOTE_CHAR, indexFrom);
            String result = bodyText.substring(indexFrom, indexTo);
            if (result.equalsIgnoreCase(PASSED_KEY)) {
                return TestSolution.PASSED.name();
            } else if (result.equalsIgnoreCase(FAILED_KEY)) {
                return TestSolution.FAILED.name();
            } else if (result.equalsIgnoreCase(NMI_KEY)) {
                return TestSolution.NEED_MORE_INFO.name();
            } else if (result.equalsIgnoreCase(NA_KEY)) {
                return TestSolution.NOT_APPLICABLE.name();
            } else {
                return RULE_NOT_YET_IMPLEMENTED;
            }
        } else {
            return RULE_NOT_YET_IMPLEMENTED;
        } 
    }

    /**
     * This method copies the tanaguru response in a file.
     * (usefull for debugging)
     * @param fileName
     * @param content
     */
    private void writeResultInFile(String fileName, String content){
        try {
            FileWriter fw = new FileWriter(resultFilePath + fileName
                    + '-' + new Date().getTime() + TEXT_EXTENSION);
            fw.write(content);
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(AbstractTanaguruOnlineTest.class.getName()).
                    warn(ex.getMessage());
        }
    }

    private void login (){
        selenium.open(loginUrl);
        selenium.type(userFieldName, user);
        selenium.type(passwordFieldName, password);
        selenium.click(LOGIN_BUTTON_NAME);
        selenium.waitForPageToLoad(LOGIN_TIMER);
    }

}