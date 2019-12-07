/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2015  Asqatasun.org
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
package org.asqatasun.sebuilder.interpreter.webdriverfactory;

import com.sebuilder.interpreter.webdriverfactory.WebDriverFactory;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;

public class PhantomJsFactory implements WebDriverFactory {

    private static final String PHANTOMJS_PATH_PROPERTY = "phantom-path";
    private String path = "/opt/phantomjs/bin/phantomjs";
    
    /**
     * 
     * @param config
     * @return A FirefoxDriver.
     */
    @Override
    public RemoteWebDriver make(HashMap<String, String> config) {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        if (System.getProperty(PHANTOMJS_PATH_PROPERTY) != null) {
            path = System.getProperty(PHANTOMJS_PATH_PROPERTY);
        }
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                        path);
        return new PhantomJSDriver(caps);
    }

}