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
import com.sebuilder.interpreter.TestRun;
import com.sebuilder.interpreter.Verify;
import com.sebuilder.interpreter.webdriverfactory.WebDriverFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.asqatasun.sebuilder.interpreter.exception.TestRunException;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * A single run of a test getScript().
 *
 * @author jkowalczyk
 */
public class TgTestRun extends TestRun {

    private boolean isStepOpenNewPage = false;
    private Map<String, String> jsScriptMap;
    public Map<String, String> getJsScriptMap() {
        return jsScriptMap;
    }
    
    public void setJsScriptMap(Map<String, String> jsScriptMap) {
        this.jsScriptMap = jsScriptMap;
    }
    
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
    
    private RemoteWebDriver rwd;
    @Override
    public RemoteWebDriver getDriver() {
        return super.getDriver();
//        if (firefoxDriverObjectPool != null) {
//            return rwd;
//        } else {
//            // keep the ability not to use an object pool
//            return super.getDriver();
//        }
    }
    
//    private FirefoxDriverObjectPool firefoxDriverObjectPool;
//    public void setFirefoxDriverObjectPool(FirefoxDriverObjectPool firefoxDriverObjectPool) {
//        this.firefoxDriverObjectPool = firefoxDriverObjectPool;
//    }

    /**
     * Constructor
     * @param script
     * @param implicitelyWaitDriverTimeout
     * @param pageLoadDriverTimeout 
     */
    public TgTestRun(Script script, int implicitelyWaitDriverTimeout, int pageLoadDriverTimeout) {
        super(script, implicitelyWaitDriverTimeout, pageLoadDriverTimeout);
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
            getLog().debug("Starting test run.");
        }

        initRemoteWebDriver();
        
        isStepOpenNewPage = false;

        getLog().debug("Running step " + (stepIndex+2) + ":"
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
                getLog().debug(" The page " + getDriver().getCurrentUrl() + " is fired as new page but"
                    + " is incomplete : " + te.getMessage());
                fireNewPage();
            }
        } catch (UnhandledAlertException uae) {
            getLog().warn(uae.getMessage());
            properlyCloseWebDriver();
            throw new TestRunException(currentStep() + " failed.", uae, currentStep().toString(), stepIndex);
        } catch (Exception e) {
            getLog().warn(e.getCause());
            getLog().warn(e.getMessage());
            properlyCloseWebDriver();
            throw new TestRunException(currentStep() + " failed.", e, currentStep().toString(), stepIndex);
        }

        if (!result) {
            // If a verify failed, we just note this but continue.
            if (currentStep().type instanceof Verify) {
                getLog().error(currentStep() + " failed.");
                return false;
            }
            // In all other cases, we throw an exception to stop the run.
            RuntimeException e = new TestRunException(currentStep() + " failed.", currentStep().toString(), stepIndex);
            e.fillInStackTrace();
            getLog().error(e);
            properlyCloseWebDriver();
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
                getLog().warn(wde.getMessage());
            }
            /*##############################################################*/

            /* byte[] snapshot = createPageSnapshot();*/
            for (NewPageListener npl : newPageListeners) {
                npl.fireNewPage(url, sourceCode, null, jsScriptResult);
            }
        } catch (UnhandledAlertException uae) {
            getLog().warn(uae.getMessage());
            throw new TestRunException(currentStep() + " failed.", uae, currentStep().toString(), stepIndex);
        }
    }
    
    /**
     * 
     * @return 
     */
    private Map<String, String> executeJsScripts() {
        getLog().debug("Executing js");
        Map<String, String> jsScriptResult = new HashMap<>();
        for (Map.Entry<String, String> entry : jsScriptMap.entrySet()) {
            try {
             jsScriptResult.put(entry.getKey(), getDriver().executeScript(entry.getValue()).toString());
            } catch (WebDriverException wde) {
                getLog().warn("Script " + entry.getKey() + " has failed");
                getLog().warn(wde.getMessage());
            }
        }
        getLog().debug("Js executed");
        return jsScriptResult;
    }
    
    /**
     * This methods uses the screenshot service of Webdriver to create the page
     * snapshot.
     * 
     * @return 
     */
    private byte[] createPageSnapshot() {
        if (!(getDriver() instanceof TakesScreenshot)) {
            return null;
        }
        byte[] snapshot = null;
        try {
            getLog().debug("Creating snapshot");
            snapshot = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES); 
            BufferedImage snapshotImg = ImageIO.read(new ByteArrayInputStream(snapshot));
            BufferedImage scaledImg = resizeImage(snapshotImg);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(scaledImg, "png", baos);
            baos.flush();
            snapshot = baos.toByteArray();
            baos.close();
        } catch (IOException ex) {
            getLog().error("Error when creating snapshot");
            getLog().error(ex);
        }
        getLog().error("Snapshot created");
        return snapshot;
    }
    
    /**
     * Resize the snapshot image to 270*170
     * 
     * @param originalImage
     * @return 
     */
    private BufferedImage resizeImage(BufferedImage originalImage) {
        float scaleRatio =  (float)270 / originalImage.getWidth();
        int resizedImageHeight = Float.valueOf(originalImage.getHeight() * scaleRatio).intValue();
	BufferedImage resizedImage = new BufferedImage(270, resizedImageHeight, originalImage.getType());
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, 270, resizedImageHeight, null);
	g.dispose();
 
	return resizedImage.getSubimage(0, 0, 270, 170);
    }

    @Override
    public void initRemoteWebDriver() {
        if (getDriver() == null) {
            getLog().debug("Launching initialisation of WebDriver");
            super.initRemoteWebDriver();
            getLog().debug("WebDriver initialised");
        }
//        if (firefoxDriverObjectPool != null && getDriver() == null) {
//            getLog().debug("Initialisation FirefoxDriver terminated ");
//            try {
//                rwd = firefoxDriverObjectPool.borrowObject();
//            } catch (Exception ex) {
//                getLog().warn("Firefox driver cannot be borrowed due to  " + ex.getMessage());
//            }
//        // if the firefoxDriver object pool is not set, keep the default behaviour
//        } else if (getDriver() == null) {
//            getLog().debug("Launching initialisation of WebDriver");
//            super.initRemoteWebDriver();
//            getLog().debug("WebDriver initialised");
//        }
    }
 
    /**
     * Properly Returns the WebDriver Object to the pool when finished or
     * close and quit the driver if the object pool is not used
     */
    private void properlyCloseWebDriver() {
        getLog().debug("Closing Firefox driver.");
        try {
            getDriver().close();
            getDriver().quit();
        } catch (Exception e) {
            getLog().warn("An error occured while closing driver."
                    + " A defunct firefox process may run on the system. "
                    + " Trying to kill before leaving");
            if (getDriver() instanceof FirefoxDriver) {
                ((FirefoxDriver)getDriver()).kill();
            }
        }
//        if (firefoxDriverObjectPool != null &&
//                getDriver() instanceof FirefoxDriver) {
//            //set the blank page before returning the webDriver instance
//            getDriver().get("");
//            try {
//                firefoxDriverObjectPool.returnObject((FirefoxDriver)getDriver());
//            } catch (Exception ex) {
//                getLog().warn("Firefox driver cannot be returned due to  " + ex.getMessage());
//            }
//        } else {
//            try {
//                getDriver().close();
//                getDriver().quit();
//            } catch (Exception e) {
//                getLog().warn("An error occured while closing driver."
//                        + " A defunct firefox process may run on the system. "
//                        + " Trying to kill before leaving");
//                if (getDriver() instanceof FirefoxDriver) {
//                    ((FirefoxDriver)getDriver()).kill();
//                }
//            }
//        }
    }

}