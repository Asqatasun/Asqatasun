/*
 * Copyright 2012 Sauce Labs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.opens.tanaguru.sebuilder.interpreter.webdriverfactory;

import com.sebuilder.interpreter.webdriverfactory.WebDriverFactory;
import java.util.HashMap;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

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