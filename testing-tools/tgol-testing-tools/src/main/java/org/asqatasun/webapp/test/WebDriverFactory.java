/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2019  Asqatasun.org
 * 
 *  This file is part of Asqatasun.
 * 
 *  Asqatasun is free software: you can redistribute it and/or modify
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
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */

package org.asqatasun.webapp.test;

import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * WebDriverFactory that guarantees that only one instance of webdriver is used
 * for a whole campaign.
 *
 * @author jkowalczyk
 */
public class WebDriverFactory {

    private FirefoxDriver webDriver;

    /**
     * Private constructor
     */
    private WebDriverFactory() {
    }

    /**
     * Singleton pattern based on the "Initialization-on-demand
     * holder idiom". See @http://en.wikipedia.org/wiki/Initialization_on_demand_holder_idiom
     *
     * @return the unique instance of WebDriverFactory
     */
    public static WebDriverFactory getInstance() {
        return WebDriverFactoryHolder.INSTANCE;
    }

    /**
     * This methods creates a firefoxDriver instance and set a DISPLAY
     * environment variable
     *
     * @param display
     * @return an instance of firefoxDriver
     */
    public FirefoxDriver getFirefoxDriver(String display) {
        if (webDriver == null) {
            webDriver = new FirefoxDriver();
            webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            webDriver.manage().timeouts().pageLoadTimeout(310, TimeUnit.SECONDS);
        }
        return webDriver;
    }

    /**
     * The holder that handles the unique instance of WebDriverFactory
     */
    private static class WebDriverFactoryHolder {
        private static final WebDriverFactory INSTANCE = new WebDriverFactory();
    }

}
