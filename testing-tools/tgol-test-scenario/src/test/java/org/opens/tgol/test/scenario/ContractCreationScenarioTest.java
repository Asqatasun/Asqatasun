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

import org.openqa.selenium.By;

/**
 *
 * @author jkowalczyk
 */
public class ContractCreationScenarioTest extends AbstractWebDriverTestClass {
    
    private static String NO_AUDIT_KEY= "No audit";
    
    private static String AUDIT_PAGES_ELEMENT_NAME= "functionalityMapPAGES1";
    private static String AUDIT_SITE_ELEMENT_NAME= "functionalityMapDOMAIN1";
    private static String AUDIT_UPLOAD_ELEMENT_NAME= "functionalityMapUPLOAD1";
    private static String AUDIT_SCENARIO_ELEMENT_NAME= "functionalityMapSCENARIO1";
    
    private static String CONTRACT_LABEL_XPATH_LOCATION = 
            "/html/body/div[2]/div[2]/div[2]/div/div[2]/h2/a";
    
    private static String AUDIT_PAGES_XPATH_LOCATION = 
            "//table[@id='audit-actions']/tbody/tr/td[1]/div";
    
    private static String AUDIT_PAGES_STR = "Pages audit";
    
    private static String AUDIT_SITE_XPATH_LOCATION = 
            "//table[@id='audit-actions']/tbody/tr/td[2]/div";
    
    private static String AUDIT_SITE_STR = "Full-site audit";
    
    private static String AUDIT_UPLOAD_XPATH_LOCATION = 
            "//table[@id='audit-actions']/tbody/tr/td[3]/div";
    
    private static String AUDIT_UPLOAD_STR = "Files audit (offline)";
    
    private static String AUDIT_SCENARIO_XPATH_LOCATION = 
            "//table[@id='audit-actions']/tbody/tr/td[4]/div";
    
    private static String AUDIT_SCENARIO_STR = "Scenario audit";
    
    private static String DISABLED_STR = " (disabled)";
    
    private static String NEW_CONTRACT_LINK_XPATH_LOCATION = 
            "//div[@id='project-0']/div[2]/h2/a";

    /**
     * 
     */
    public void testContractCreationWithErrors() {

        loginAsRoot();
        createNewContract();
        
        logout();
        
        loginAsNewUser();
        checkTextPresence(NO_AUDIT_KEY);
        checkElementTextPresence(CONTRACT_LABEL_XPATH_LOCATION,NEW_CONTRACT_LABEL);
        
        driver.findElement(By.xpath(NEW_CONTRACT_LINK_XPATH_LOCATION)).click();
        extractedIdOfNewContract();
        
        checkElementTextPresence(AUDIT_PAGES_XPATH_LOCATION, AUDIT_PAGES_STR+DISABLED_STR);
        checkElementTextPresence(AUDIT_SITE_XPATH_LOCATION, AUDIT_SITE_STR+DISABLED_STR);
        checkElementTextPresence(AUDIT_UPLOAD_XPATH_LOCATION, AUDIT_UPLOAD_STR+DISABLED_STR);
        checkElementTextPresence(AUDIT_SCENARIO_XPATH_LOCATION, AUDIT_SCENARIO_STR+DISABLED_STR);
        
        goToAuditPagesSetUpContractPage();
        checkTextPresence(NOT_ALLOWED_MSG);
        goToAuditSiteSetUpContractPage();
        checkTextPresence(NOT_ALLOWED_MSG);
        goToAuditScenarioSetUpContractPage();
        checkTextPresence(NOT_ALLOWED_MSG);
        goToAuditUploadSetUpContractPage();
        checkTextPresence(NOT_ALLOWED_MSG);
        
        logout();
        loginAsRoot();
        
        goToEditUserContractPage();
        selectWebElement(AUDIT_PAGES_ELEMENT_NAME);
        selectWebElement(AUDIT_SITE_ELEMENT_NAME);
        selectWebElement(AUDIT_UPLOAD_ELEMENT_NAME);
        selectWebElement(AUDIT_SCENARIO_ELEMENT_NAME);
        submitEditUserContract();
        
        logout();
        loginAsNewUser();
        
        goToNewContractPage();
        checkElementTextPresence(AUDIT_PAGES_XPATH_LOCATION, AUDIT_PAGES_STR);
        checkElementTextPresence(AUDIT_SITE_XPATH_LOCATION, AUDIT_SITE_STR);
        checkElementTextPresence(AUDIT_UPLOAD_XPATH_LOCATION, AUDIT_UPLOAD_STR);
        checkElementTextPresence(AUDIT_SCENARIO_XPATH_LOCATION, AUDIT_SCENARIO_STR);
        
        goToAuditPagesSetUpContractPage();
        checkTextAbscence(NOT_ALLOWED_MSG);
        goToAuditSiteSetUpContractPage();
        checkTextAbscence(NOT_ALLOWED_MSG);
        goToAuditScenarioSetUpContractPage();
        checkTextAbscence(NOT_ALLOWED_MSG);
        goToAuditUploadSetUpContractPage();
        checkTextAbscence(NOT_ALLOWED_MSG);
        
        logout();
        loginAsRoot();
        
        // forbidden for the user, even root, cause the contract does not belong
        // to him
        goToNewContractPage();
        checkTextPresence(NOT_ALLOWED_MSG);
        goToAuditPagesSetUpContractPage();
        checkTextPresence(NOT_ALLOWED_MSG);
        goToAuditSiteSetUpContractPage();
        checkTextPresence(NOT_ALLOWED_MSG);
        goToAuditScenarioSetUpContractPage();
        checkTextPresence(NOT_ALLOWED_MSG);
        goToAuditUploadSetUpContractPage();
        checkTextPresence(NOT_ALLOWED_MSG);
        
        deleteNewUser();
        
        logout();

    }
    
}