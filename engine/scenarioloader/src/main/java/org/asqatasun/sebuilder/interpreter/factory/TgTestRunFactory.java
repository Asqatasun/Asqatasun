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

package org.asqatasun.sebuilder.interpreter.factory;

import com.sebuilder.interpreter.Script;
import com.sebuilder.interpreter.TestRun;
import com.sebuilder.interpreter.factory.TestRunFactory;
import com.sebuilder.interpreter.webdriverfactory.WebDriverFactory;
import org.apache.commons.logging.Log;
import org.asqatasun.sebuilder.interpreter.NewPageListener;
import org.asqatasun.sebuilder.interpreter.TgTestRun;
import org.asqatasun.sebuilder.interpreter.webdriverfactory.FirefoxDriverFactory;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jkowalczyk
 */
public class TgTestRunFactory extends TestRunFactory {

    /**
     * The firefox profile used when the browser is loaded
     */
    WebDriverFactory webDriverFactory = new FirefoxDriverFactory();
//    WebDriverFactory webDriverFactory = new PhantomJsFactory();

    /**
     * The firefox profile used when the browser is loaded
     */
    FirefoxProfile firefoxProfile = new FirefoxProfile();
    public void setFirefoxProfile(FirefoxProfile firefoxProfile) {
        this.firefoxProfile = firefoxProfile;
    }
    
    int screenWidth=-1;
    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }
    
    int screenHeight=-1;
    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }
    
    /**
     * the map that handles js scripts executed when page is loaded
     */
    private Map<String, String> jsScriptMap;
    public Map<String, String> getJsScriptMap() {
        return jsScriptMap;
    }
    public void setJsScriptMap(Map<String, String> jsScriptMap) {
        this.jsScriptMap = jsScriptMap;
    }
    
    /**
     * The new page listeners
     */
    private Collection<NewPageListener> newPageListeners;
    public void addNewPageListener(NewPageListener newPageListener) {
        if (newPageListeners == null) {
            newPageListeners = new ArrayList<>();
        }
        this.newPageListeners.add(newPageListener);
    }

    public void addNewPageListeners(Collection<NewPageListener> newPageListeners) {
        if (this.newPageListeners == null) {
            this.newPageListeners = new ArrayList<>();
        }
        this.newPageListeners.addAll(newPageListeners);
    }

    public void removeNewPageListener(NewPageListener newPageListener) {
        if (newPageListeners != null) {
            this.newPageListeners.remove(newPageListener);
        }
    }

    public void removeNewPageListeners(Collection<NewPageListener> newPageListeners) {
        if (newPageListeners != null) {
            this.newPageListeners.removeAll(newPageListeners);
        }
    }

//    private FirefoxDriverObjectPool fdop;
//    public void setFirefoxDriverObjectPool(FirefoxDriverObjectPool fdop) {
//        this.fdop = fdop;
//    }
    
    @Override
    public TestRun createTestRun(Script script) {
        initialiseWebDriverFactory();
        TgTestRun testRun = new TgTestRun(
                script, 
                webDriverFactory, 
                new HashMap<String, String>(), 
                getImplicitlyWaitDriverTimeout(), 
                getPageLoadDriverTimeout());
        testRun.addNewPageListeners(newPageListeners);
        testRun.setJsScriptMap(jsScriptMap);
        return testRun;
    }
    
    @Override
    public TestRun createTestRun(Script script, Log log, WebDriverFactory webDriverFactory, HashMap<String, String> webDriverConfig) {
        initialiseWebDriverFactory();
        TgTestRun testRun = 
                new TgTestRun(
                    script, 
                    log, 
                    webDriverFactory, 
                    webDriverConfig, 
                    getImplicitlyWaitDriverTimeout(), 
                    getPageLoadDriverTimeout());
        testRun.addNewPageListeners(newPageListeners);
        testRun.setJsScriptMap(jsScriptMap);
        return testRun;
    }

    /**
     * initialise WebDriverFactory with profile and screen size values
     */
    private void initialiseWebDriverFactory() {
        if (webDriverFactory instanceof FirefoxDriverFactory) {
            ((FirefoxDriverFactory)webDriverFactory).setFirefoxProfile(firefoxProfile);
            if (screenHeight != -1 && screenWidth!= -1) {
                ((FirefoxDriverFactory)webDriverFactory).setScreenHeight(screenHeight);
                ((FirefoxDriverFactory)webDriverFactory).setScreenWidht(screenWidth);
            }
        }
    }
}