/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015 Tanaguru.org
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
package org.tanaguru.krashtest;

import java.util.Arrays;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.tanaguru.webapp.test.AbstractTanaguruOnlineTest;

/**
 *
 * @author jkowalczyk
 */
public class SiteKrashTest extends AbstractTanaguruOnlineTest {

    protected Collection<String> contractIds;

    /**
     * Default Constructor
     */
    public SiteKrashTest() {
        super();
        initialize();
    }

    /**
     *
     */
    private void initialize() {
        // These parameters has to passed as JVM argument
        System.out.println(contractId);
        contractIds = Arrays.asList(contractId.split("-"));

        ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME);
        formUrl = hostLocation + bundle.getString(FORM_URL_KEY);
    }

    /**
     * Login and launch all the site audit regarding the contract ids set as
     * JVM parameter.
     *
     */
    public void testSiteAuditLaunch() {
        login();
        for (String cId : contractIds) {
            driver.get(formUrl + cId);
            driver.findElementById(SUBMIT_BUTTON_NAME).submit();
        }
    }
    
}