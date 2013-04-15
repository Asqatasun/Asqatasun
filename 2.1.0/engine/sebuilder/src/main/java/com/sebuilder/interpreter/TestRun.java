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
package com.sebuilder.interpreter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 * A single run of a test script.
 *
 * @author zarkonnen
 */
public class TestRun implements NewPageListener {

    private static final int RETRY_MAX_VALUE = 3;
    private HashMap<String, String> vars = new HashMap<String, String>();
    public HashMap<String, String> getVars() {
        return vars;
    }
    
    public void addVar(String key, String value) {
        vars.put(key, value);
    }
    
    private Script script;
    private int stepIndex = -1;
    private boolean isStepOpenNewPage = false;
    private int retryIndex = 0;
    private FirefoxDriver driver;

    public FirefoxDriver getDriver() {
        return driver;
    }
    
    private Log log;
    private FirefoxProfile firefoxProfile;

    private Collection<NewPageListener> newPageListeners;
    private int foundPageCounter = 0;

    // attributes needed by NetExport. Commented in version 1.6.0
//    private static final Pattern HAR_EXTENSION_PATTERN = Pattern.compile(".*(?i)(\\.(har))$");
//    String exportPath;
//    private FileFilter ff = new RegexFileFilter(HAR_EXTENSION_PATTERN);

    public TestRun(Script script) {
        this.script = script;
        log = LogFactory.getFactory().getInstance(TestRun.class);
    }

    public TestRun(Script script, FirefoxProfile firefoxProfile) {
        this.script = script;
        this.firefoxProfile = firefoxProfile;
        log = LogFactory.getFactory().getInstance(TestRun.class);
//        this.exportPath = ProfileFactory.getInstance().getNetExportPath(firefoxProfile);
    }

    public TestRun(Script script, Log log) {
        this.script = script;
        this.log = log;
    }

    public void addNewPageListener(NewPageListener newPageListener) {
        if (newPageListeners == null) {
            newPageListeners = new ArrayList<NewPageListener>();
        }
        this.newPageListeners.add(newPageListener);
    }

    public void addNewPageListeners(Collection<NewPageListener> newPageListeners) {
        if (this.newPageListeners == null) {
            this.newPageListeners = new ArrayList<NewPageListener>();
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

    /**
     * @return True if there is another step to execute.
     */
    public boolean hasNext() {
        boolean hasNext = stepIndex < script.getSteps().size() - 1;
        if (!hasNext && driver != null) {
//            while (!isPageLoaded()) {
            // sleep a second before we close the driver
            try {
                log.debug("Sleep before closing firefoxDriver");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                log.error(ex);
            }
//            }
            log.debug("Closing FirefoxDriver.");
            driver.quit();
        }
        return hasNext;
    }

    /**
     * Executes the next step.
     *
     * @return True on success.
     */
    public boolean next() {
        if (stepIndex == -1) {
            log.debug("Starting test run.");
        }

        initDriver();
        isStepOpenNewPage = false;

        log.debug("Running step " + (stepIndex+2) + ":"
                + script.getSteps().get(stepIndex+1).type.toString() + " step.");
        
        boolean result = false;
        String beforeUrl = null;
        try {
            beforeUrl = driver.getCurrentUrl();
            result = script.getSteps().get(stepIndex+1).type.run(this);
            // wait a second to make sure the page is fully loaded
            Thread.sleep(500);
            if (!isStepOpenNewPage && !StringUtils.equals(beforeUrl, driver.getCurrentUrl())) {
                fireNewPage();
            }
            // for the last step, we need to sleep a while to leave enough time
            // to netExport to write
        } catch (TimeoutException te) {
            result = true;
            if (!isStepOpenNewPage && !StringUtils.equals(beforeUrl, driver.getCurrentUrl())) {
                fireNewPage();
            }
        } catch (Exception e) {
            if (e instanceof NoSuchElementException) {
                log.warn(currentStep() + " failed.", e);
                // if the element is not found we retry it
                if (retryIndex <= RETRY_MAX_VALUE) {
                    retryIndex++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TestRun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    next();
                }
            }
            log.error(currentStep() + " failed.", e);

            // Now you can do whatever you need to do with it, for example copy somewhere
//            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            try {
//                FileUtils.copyFile(scrFile, new File("/tmp/MySnapshot.png"));
//            } catch (IOException ioe) {
//            }

            reset();
            throw new RuntimeException(currentStep() + " failed.", e);
        }

        if (!result) {
            // If a verify failed, we just note this but continue.
            if (currentStep().type instanceof Verify) {
                log.error(currentStep() + " failed.");
                return false;
            }
            // In all other cases, we throw an exception to stop the run.
            RuntimeException e = new RuntimeException(currentStep() + " failed.");
            e.fillInStackTrace();
            log.error(e);
            reset();
            throw e;
        } else {
            stepIndex++;
            retryIndex = 0;
            return true;
        }
    }

    /**
     * Resets the script's progress and closes the driver if needed.
     */
    public void reset() {
        log.debug("Resetting test run.");
        vars.clear();
        stepIndex = -1;
        if (driver != null) {
            driver.close();
        }
    }

    /**
     * Runs the entire (rest of the) script.
     *
     * @return True if the script ran successfully, false if a verification
     * failed. Any other failure throws an exception.
     * @throws RuntimeException if the script failed.
     */
    public boolean finish() {
        boolean success = true;
        try {
            while (hasNext()) {
                success = next() && success;
            }
        } catch (RuntimeException e) {
            e.fillInStackTrace();
            // If the script terminates, the driver will be closed automatically.
            try {
                driver.close();
            } catch (Exception e2) {}
            throw e;
        }
        return success;
    }

    /**
     * @return The step that is being/has just been executed.
     */
    public Script.Step currentStep() {
        return script.getSteps().get(stepIndex+1);
    }

    /**
     * @return The logger being used.
     */
    public Log getLog() {
        return log;
    }

    /**
     * Fetches a String parameter from the current step.
     *
     * @param paramName The parameter's name.
     * @return The parameter's value.
     */
    public String string(String paramName) {
        String s = currentStep().stringParams.get(paramName);
        if (s == null) {
            throw new RuntimeException("Missing parameter \"" + paramName + "\" at step #"
                    + (stepIndex + 1) + ".");
        }
        // This kind of variable substitution makes for short code, but it's inefficient.
        for (Map.Entry<String, String> v : vars.entrySet()) {
            s = s.replace("${" + v.getKey() + "}", v.getValue());
        }
        return s;
    }

    /**
     * Fetches a Locator parameter from the current step.
     *
     * @param paramName The parameter's name.
     * @return The parameter's value.
     */
    public Locator locator(String paramName) {
        Locator l = new Locator(currentStep().locatorParams.get(paramName));
        if (l == null) {
            throw new RuntimeException("Missing parameter \"" + paramName + "\" at step #"
                    + (stepIndex + 1) + ".");
        }
        // This kind of variable substitution makes for short code, but it's inefficient.
        for (Map.Entry<String, String> v : vars.entrySet()) {
            l.setValue(l.getValue().replace("${" + v.getKey() + "}", v.getValue()));
        }
        return l;
    }

    @Override
    public void fireNewPage(String url, String sourceCode) {
        if (newPageListeners == null) {
            return;
        }
        for (NewPageListener npl : newPageListeners) {
            npl.fireNewPage(url, sourceCode);
        }
    }
    
    public void fireNewPage() {
        isStepOpenNewPage = true;
        if (newPageListeners == null) {
            return;
        }
        try {
            Thread.sleep(1000);
            foundPageCounter++;
//            while (!isPageLoaded()) {
//                Thread.sleep(100);
//                log.debug("Page not loaded yet");
//            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TestRun.class.getName()).log(Level.SEVERE, null, ex);
        }
        String url = driver.getCurrentUrl();
        String sourceCode = driver.getPageSource();
        for (NewPageListener npl : newPageListeners) {
            npl.fireNewPage(url, sourceCode);
        }
    }

//    /**
//     * To determine whether a page is completed loaded, we check whether
//     * NetExport has written the file.
//     *
//     * @return
//     */
//    private boolean isPageLoaded() {
//        File[] files = new File(exportPath).listFiles(ff);
//        log.debug("foundPageCounter " + foundPageCounter);
//        if (files == null || files.length < foundPageCounter) {
//            return false;
//        }
//        log.debug("files.length " + files.length);
//        return true;
//    }

    private void initDriver() {
        if (driver == null) {
            if (firefoxProfile == null) {
                firefoxProfile = new FirefoxProfile();
            }
            driver = new FirefoxDriver(firefoxProfile);
            driver.manage().timeouts().implicitlyWait(Long.valueOf(2), TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(Long.valueOf(10), TimeUnit.SECONDS);
        }
    }

}