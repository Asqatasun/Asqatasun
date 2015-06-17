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
public class ContractCreationWithErrorsScenarioTest extends AbstractWebDriverTestClass {


    private static String MANDATORY_ELEMENTS_MISSING_MSG = 
            "Some required fields are empty or invalid";
    private static String LABEL_MISSING_MSG = "Please enter a label.";
    private static String WRONG_URL_MSG = 
            "Please enter a valid URL (ex: http(s)://example.com)";
    private static String OUT_OF_BOUND_MSG = 
            "Out of bound value";
    private static String WRONG_NB_OF_AUDITS_MSG = 
            "Please enter a valid number of audits per contract.";
    private static String WRONG_NB_OF_ACTS_MSG = 
            "Please enter a valid number of audits to display";
    private static String BEGIN_DATE_MISSING_MSG = 
            "Please enter a begin date";
    private static String END_DATE_ANTERIOR_TO_BEGIN_DATE_MISSING_MSG = 
            "Please choose an end date that is not anterior to the begin date";

   /**
     * 
     */
    public void testContractCreationWithErrors() {

        loginAsRoot();
        createNewUser(true);

        // go to the management contracts page of the new user
        driver.findElement(By.xpath(NEW_USER_CONTRACT_MNGT_XPATH_LOCATION.replaceAll("x",findNewUserRowIndexLocation()))).click();
        //----- At this time the id of the new user appears in an url for the first time
        extractedIdOfNewUser();
        
        //-----
        checkTextPresence("Manage contracts");
        checkTextPresence("Contract list");
        checkTextPresence("Add a contract");
        checkTextPresence("No contracts");
        
        goToAddUserContractPage();
        checkTextPresence("Add a contract to");
        
        submitEditUserContract();
        checkTextPresence(MANDATORY_ELEMENTS_MISSING_MSG);
        checkTextPresence(LABEL_MISSING_MSG);
        
        editWebElement("beginDate","");
        submitEditUserContract();
        checkTextPresence(MANDATORY_ELEMENTS_MISSING_MSG);
        checkTextPresence(LABEL_MISSING_MSG);
        checkTextPresence(BEGIN_DATE_MISSING_MSG);
        
        editWebElement("label","CONTRACT LABEL");
        editWebElement("beginDate","01/01/2010");
        editWebElement("endDate","");
        submitEditUserContract();
        checkTextPresence(MANDATORY_ELEMENTS_MISSING_MSG);

        editWebElement("endDate","01/01/2009");
        submitEditUserContract();
        checkTextPresence(MANDATORY_ELEMENTS_MISSING_MSG);
        checkTextPresence(END_DATE_ANTERIOR_TO_BEGIN_DATE_MISSING_MSG);
        
        editWebElement("endDate","01/01/2020");
        editWebElement("contractUrl","wrong url");
        submitEditUserContract();
        checkTextPresence(WRONG_URL_MSG);
        
        editWebElement("contractUrl","http://myUrl.com");
        editWebElement("nb-max-audits-per-contract","not a number");
        submitEditUserContract();
        checkTextPresence(WRONG_NB_OF_AUDITS_MSG);
        
        editWebElement("nb-max-audits-per-contract","");
        editWebElement("nb-max-acts-per-contract","not a number");
        submitEditUserContract();
        checkTextPresence(WRONG_NB_OF_ACTS_MSG);
        
        editWebElement("nb-max-acts-per-contract","");
        editWebElement("depth","30");
        submitEditUserContract();
        checkTextPresence(OUT_OF_BOUND_MSG);
        
        editWebElement("depth","not a number");
        submitEditUserContract();
        checkTextPresence(OUT_OF_BOUND_MSG);
        
        editWebElement("depth","");
        editWebElement("max-duration","not a number");
        submitEditUserContract();
        checkTextPresence(OUT_OF_BOUND_MSG);
        
        editWebElement("max-duration","100000");
        submitEditUserContract();
        checkTextPresence(OUT_OF_BOUND_MSG);
        
        editWebElement("max-duration","");
        editWebElement("max-documents","not a number");
        submitEditUserContract();
        checkTextPresence(OUT_OF_BOUND_MSG);
        
        editWebElement("max-documents","10001");
        submitEditUserContract();
        checkTextPresence(OUT_OF_BOUND_MSG);
        
        deleteNewUser();
        
        logout();

    }
    
}