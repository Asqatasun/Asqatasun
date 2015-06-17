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
public class ContractCreationScenarioTest extends AbstractWebDriverTestClass {
    
    private static String NO_AUDIT_KEY= "No audit";
    
    private static String AUDIT_PAGES_ELEMENT_NAME= "functionalityMapPAGES1";
    private static String AUDIT_SITE_ELEMENT_NAME= "functionalityMapDOMAIN1";
    private static String AUDIT_UPLOAD_ELEMENT_NAME= "functionalityMapUPLOAD1";
    private static String AUDIT_SCENARIO_ELEMENT_NAME= "functionalityMapSCENARIO1";
    
    private static String CONTRACT_LABEL_CSS_SELECTOR = "#project-0 .project-name a";
    
    private static String AUDIT_PAGES_XPATH_LOCATION = 
            "//table[@id='audit-actions']/tbody/tr/td[1]/";
    
//    private static String AUDIT_PAGES_STR = "Audit\rPages\r";
    
    private static String AUDIT_SITE_XPATH_LOCATION = 
            "//table[@id='audit-actions']/tbody/tr/td[2]/";
    
//    private static String AUDIT_SITE_STR = "Audit\rFull-site\r";
    
    private static String AUDIT_UPLOAD_XPATH_LOCATION = 
            "//table[@id='audit-actions']/tbody/tr/td[3]/";
    
//    private static String AUDIT_UPLOAD_STR = "Audit\rFiles\r";
    
    private static String AUDIT_SCENARIO_XPATH_LOCATION = 
            "//table[@id='audit-actions']/tbody/tr/td[4]/";
    
//    private static String AUDIT_SCENARIO_STR = "Audit\rScenario\r";
    
    private static String DISABLED_STR = "Disabled";
    private static String SPAN_STR = "span";
    private static String A_STR = "a";
    
    /**
     * 
     */
    public void testContractCreation() {

        loginAsRoot();
        createNewContract();
        
        logout();
        
        loginAsNewUser();
        checkTextPresence(NO_AUDIT_KEY);
        checkElementTextPresenceByCssSelector(CONTRACT_LABEL_CSS_SELECTOR,NEW_CONTRACT_LABEL);
        
        driver.findElement(By.cssSelector(CONTRACT_LABEL_CSS_SELECTOR)).click();
        extractedIdOfNewContract();
        
        checkElementTextPresence(AUDIT_PAGES_XPATH_LOCATION+SPAN_STR, DISABLED_STR);
        checkElementTextPresence(AUDIT_SITE_XPATH_LOCATION+SPAN_STR, DISABLED_STR);
        checkElementTextPresence(AUDIT_UPLOAD_XPATH_LOCATION+SPAN_STR, DISABLED_STR);
        checkElementTextPresence(AUDIT_SCENARIO_XPATH_LOCATION+SPAN_STR, DISABLED_STR);
        
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

        checkElementTextAbsence(AUDIT_PAGES_XPATH_LOCATION+A_STR, DISABLED_STR);
        checkElementTextAbsence(AUDIT_SITE_XPATH_LOCATION+A_STR, DISABLED_STR);
        checkElementTextAbsence(AUDIT_UPLOAD_XPATH_LOCATION+A_STR, DISABLED_STR);
        checkElementTextAbsence(AUDIT_SCENARIO_XPATH_LOCATION+A_STR, DISABLED_STR);
        
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