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
package org.asqatasun.sebuilder.interpreter;

import com.sebuilder.interpreter.Script;
import com.sebuilder.interpreter.StepType;
import com.sebuilder.interpreter.TestRun;
import com.sebuilder.interpreter.Verify;
import com.sebuilder.interpreter.webdriverfactory.WebDriverFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.asqatasun.sebuilder.interpreter.exception.TestRunException;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * A single run of a test getScript().
 *
 * @author jkowalczyk
 */
public class TgTestRun extends TestRun {

    private static final Logger LOGGER = LoggerFactory.getLogger(TgTestRun.class);

    private boolean isStepOpenNewPage = false;
    private Map<String, String> jsScriptMap;
    
    public void setJsScriptMap(Map<String, String> jsScriptMap) {
        this.jsScriptMap = jsScriptMap;
    }
    
    private Collection<NewPageListener> newPageListeners;

    public void addNewPageListeners(Collection<NewPageListener> newPageListeners) {
        if (this.newPageListeners == null) {
            this.newPageListeners = new ArrayList<>();
        }
        this.newPageListeners.addAll(newPageListeners);
    }

    public void purgeNewPageListener() {
        if (newPageListeners != null) {
            this.newPageListeners.clear();
        }
    }

    @Override
    public RemoteWebDriver getDriver() {
        return super.getDriver();
    }

    /**
     * Constructor
     * @param script
     * @param log
     * @param webDriverFactory
     * @param webDriverConfig
     * @param implicitelyWaitDriverTimeout
     * @param pageLoadDriverTimeout 
     */
    public TgTestRun(
                Script script, 
                Log log, 
                WebDriverFactory webDriverFactory, 
                HashMap<String, String> webDriverConfig, 
                int implicitelyWaitDriverTimeout, 
                int pageLoadDriverTimeout) {
        super(script, 
              log, 
              webDriverFactory, 
              webDriverConfig, 
              implicitelyWaitDriverTimeout, 
              pageLoadDriverTimeout);
    }
    
    /**
     * Constructor
     * @param script
     * @param webDriverFactory
     * @param webDriverConfig
     * @param implicitelyWaitDriverTimeout
     * @param pageLoadDriverTimeout 
     */
    public TgTestRun(
                Script script, 
                WebDriverFactory webDriverFactory, 
                HashMap<String, String> webDriverConfig, 
                int implicitelyWaitDriverTimeout, 
                int pageLoadDriverTimeout) {
        super(script,
              LogFactory.getLog(TgTestRun.class),
              webDriverFactory,
              webDriverConfig, 
              implicitelyWaitDriverTimeout, 
              pageLoadDriverTimeout);
    }
    
    /**
     * @return True if there is another step to execute.
     */
    @Override
    public boolean hasNext() {
        boolean hasNext = stepIndex < getScript().steps.size() - 1;
        if (!hasNext && getDriver() != null) {
            properlyCloseWebDriver();
            purgeNewPageListener();
        }
        return hasNext;
    }

    /**
     * Executes the next step.
     *
     * @return True on success.
     */
    @Override
    public boolean next() {
        if (stepIndex == -1) {
            LOGGER.debug("Starting test run.");
        }

        initRemoteWebDriver();
        
        isStepOpenNewPage = false;

        LOGGER.debug("Running step " + (stepIndex+2) + ":"
                + getScript().steps.get(stepIndex+1).type.toString() + " step.");
        
        boolean result = false;
        String previousUrl = null;
        try {
            previousUrl = getDriver().getCurrentUrl();
            result = getScript().steps.get(++stepIndex).type.run(this);
            // wait a second to make sure the page is fully loaded
            Thread.sleep(500);
            if (!isStepOpenNewPage && !StringUtils.equals(previousUrl, getDriver().getCurrentUrl())) {
                fireNewPage();
            }
        } catch (TimeoutException te) {
            result = true;
            if (!isStepOpenNewPage && !StringUtils.equals(previousUrl, getDriver().getCurrentUrl())) {
                LOGGER.debug(" The page " + getDriver().getCurrentUrl() + " is fired as new page but"
                    + " is incomplete : " + te.getMessage());
                fireNewPage();
            }
        } catch (UnhandledAlertException uae) {
            LOGGER.warn(uae.getMessage());
            properlyCloseWebDriver();
            throw new TestRunException(currentStep() + " failed.", uae, currentStep().toString(), stepIndex);
        } catch (Exception e) {
            LOGGER.warn(e.getCause().getMessage());
            LOGGER.warn(e.getMessage());
            properlyCloseWebDriver();
            throw new TestRunException(currentStep() + " failed.", e, currentStep().toString(), stepIndex);
        }

        if (!result) {
            // If a verify failed, we just note this but continue.
            if (currentStep().type instanceof Verify) {
                LOGGER.error(currentStep() + " failed.");
                return false;
            }
            // In all other cases, we throw an exception to stop the run.
            RuntimeException e = new TestRunException(currentStep() + " failed.", currentStep().toString(), stepIndex);
            e.fillInStackTrace();
            LOGGER.error(e.getMessage());
            properlyCloseWebDriver();
            purgeNewPageListener();
            throw e;
        } else {
            return true;
        }
    }

    /**
     * 
     * @param urlSuffix 
     */
    public void fireNewPage(String urlSuffix) {
        isStepOpenNewPage = true;
        if (newPageListeners == null) {
            return;
        }
        getSourceCodeAndFireNewPage(getDriver().getCurrentUrl()+'#'+urlSuffix);
    }
    
    /**
     * 
     */
    public void fireNewPage() {
        isStepOpenNewPage = true;
        if (newPageListeners == null) {
            return;
        }
        getSourceCodeAndFireNewPage(getDriver().getCurrentUrl());
    }

    /**
     * 
     * @param url 
     */
    private void getSourceCodeAndFireNewPage(String url) {
        try {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                throw new TestRunException(currentStep() + " failed.", ex, currentStep().toString(), stepIndex);
            }
            String sourceCode = getDriver().getPageSource();
            
            /* ##############################################################
             * ACHTUNG !!!!!!!!!!!!!!!!!!!!!!!!!!
             * this sendKeys action is here to grab the focus on the page.
             * It is needed later by the js script to execute the focus()
             * method on each element. Without it, the focus is kept by the adress
             * bar.
             */
            WebElement body = getDriver().findElementByTagName("html");
            Map<String, String> jsScriptResult = Collections.EMPTY_MAP;
            try {
                body.sendKeys(Keys.TAB);
                jsScriptResult = executeJsScripts();
            } catch (WebDriverException wde) {
                LOGGER.warn(wde.getMessage());
            }
            /*##############################################################*/

            /* byte[] snapshot = createPageSnapshot();*/
            for (NewPageListener npl : newPageListeners) {
                npl.fireNewPage(url, sourceCode, null, jsScriptResult);
            }
        } catch (UnhandledAlertException uae) {
            LOGGER.warn(uae.getMessage());
            throw new TestRunException(currentStep() + " failed.", uae, currentStep().toString(), stepIndex);
        }
    }
    
    /**
     * 
     * @return 
     */
    private Map<String, String> executeJsScripts() {
        LOGGER.debug("Executing js");
        Map<String, String> jsScriptResult = new HashMap<>();
        for (Map.Entry<String, String> entry : jsScriptMap.entrySet()) {
            try {
             jsScriptResult.put(entry.getKey(), getDriver().executeScript(entry.getValue()).toString());
            } catch (WebDriverException wde) {
                LOGGER.warn("Script " + entry.getKey() + " has failed");
                LOGGER.warn(wde.getMessage());
            }
        }
        LOGGER.debug("Js executed");
        return jsScriptResult;
    }

    @Override
    public void initRemoteWebDriver() {
        if (getDriver() == null) {
            LOGGER.debug("Launching initialisation of WebDriver");
            super.initRemoteWebDriver();
            LOGGER.debug("WebDriver initialised");
        }
    }
 
    /**
     * Properly Returns the WebDriver Object to the pool when finished or
     * close and quit the driver if the object pool is not used
     */
    private void properlyCloseWebDriver() {
        LOGGER.debug("Closing Firefox driver.");
        try {
            getDriver().close();
            getDriver().quit();
        } catch (Exception e) {
            LOGGER.warn("An error occured while closing driver."
                    + " A defunct firefox process may run on the system. "
                    + " Trying to kill before leaving");
            if (getDriver() instanceof FirefoxDriver) {
                ((FirefoxDriver)getDriver()).kill();
            }
        }
    }

}
