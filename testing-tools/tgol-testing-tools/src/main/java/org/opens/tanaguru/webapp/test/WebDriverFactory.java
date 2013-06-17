/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2011  Open-S Company
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

package org.opens.tanaguru.webapp.test;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.opens.tanaguru.sebuilder.tools.ProfileFactory;

/**
 * WebDriverFactory that guarantees that only one instance of webdriver is used 
 * for a whole campaign.
 * 
 * @author jkowalczyk
 */
public class WebDriverFactory {

    private FirefoxDriver webDriver;
    private static WebDriverFactory webDriverFactory;
    
    private WebDriverFactory(){}
    
    public static WebDriverFactory getInstance() {
        if (webDriverFactory == null) {
            webDriverFactory = new WebDriverFactory();
        }
        return webDriverFactory;
    }
    
    public FirefoxDriver getFirefoxDriver(String xvfbDisplay) {
        if (webDriver == null) {
            FirefoxBinary ffBinary = new FirefoxBinary();
            if (xvfbDisplay != null) {
                Logger.getLogger(this.getClass()).info("Setting Xvfb display with value " + xvfbDisplay);
                ffBinary.setEnvironmentProperty("DISPLAY", xvfbDisplay);
            }
            ProfileFactory pf = ProfileFactory.getInstance();
            webDriver = new FirefoxDriver(ffBinary, pf.getOnlineProfile());
            webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        }
        return webDriver;
    }
    
}
