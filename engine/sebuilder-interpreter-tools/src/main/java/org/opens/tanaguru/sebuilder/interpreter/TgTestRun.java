/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2013  Open-S Company
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
package org.opens.tanaguru.sebuilder.interpreter;

import com.sebuilder.interpreter.Script;
import com.sebuilder.interpreter.TestRun;
import com.sebuilder.interpreter.Verify;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.opens.tanaguru.sebuilder.interpreter.exception.TestRunException;
import org.opens.tanaguru.sebuilder.tools.FirefoxDriverObjectPool;

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
    
    private FirefoxDriver fd;
    @Override
    public FirefoxDriver getDriver() {
        if (firefoxDriverObjectPool != null) {
            return fd;
        } else {
            // keep the ability not to use an object pool
            return super.getDriver();
        }
    }
    
    private FirefoxDriverObjectPool firefoxDriverObjectPool;
    public void setFirefoxDriverObjectPool(FirefoxDriverObjectPool firefoxDriverObjectPool) {
        this.firefoxDriverObjectPool = firefoxDriverObjectPool;
    }

    /**
     * 
     * @param script 
     */
    public TgTestRun(Script script) {
        super(script);
    }

    public TgTestRun(Script script, FirefoxProfile firefoxProfile, Map<String, String> jsScriptMap) {
        super(script);
        setFirefoxProfile(firefoxProfile);
        this.jsScriptMap = jsScriptMap;
    }
    
    /**
     * @return True if there is another step to execute.
     */
    @Override
    public boolean hasNext() {
        boolean hasNext = stepIndex < getScript().getSteps().size() - 1;
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

        initDriver();
        isStepOpenNewPage = false;

        getLog().debug("Running step " + (stepIndex+2) + ":"
                + getScript().getSteps().get(stepIndex+1).getType().toString() + " step.");
        
        boolean result = false;
        String beforeUrl = null;
        try {
            beforeUrl = getDriver().getCurrentUrl();
            result = getScript().getSteps().get(++stepIndex).getType().run(this);
            // wait a second to make sure the page is fully loaded
            Thread.sleep(500);
            if (!isStepOpenNewPage && !StringUtils.equals(beforeUrl, getDriver().getCurrentUrl())) {
                fireNewPage();
            }
        } catch (TimeoutException te) {
            result = true;
            if (!isStepOpenNewPage && !StringUtils.equals(beforeUrl, getDriver().getCurrentUrl())) {
                fireNewPage();
            }
        } catch (Exception e) {
            properlyCloseWebDriver();
            throw new TestRunException(currentStep() + " failed.", e, currentStep().toString(), stepIndex);
        }

        if (!result) {
            // If a verify failed, we just note this but continue.
            if (currentStep().getType() instanceof Verify) {
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

    public void fireNewPage() {
        isStepOpenNewPage = true;
        if (newPageListeners == null) {
            return;
        }
        try {
            String url = getDriver().getCurrentUrl();
            String sourceCode = getDriver().getPageSource();

            Map<String, String> jsScriptResult = executeJsScripts();
    //        byte[] snapshot = createPageSnapshot();
            for (NewPageListener npl : newPageListeners) {
                npl.fireNewPage(url, sourceCode, null, jsScriptResult);
            }
        } catch (UnhandledAlertException uae) {
            Logger.getLogger(this.getClass()).warn(uae.getMessage());
            Logger.getLogger(this.getClass()).warn(uae.getAlertText());
        }
    }

    /**
     * 
     * @return 
     */
    private Map<String, String> executeJsScripts() {
        getLog().debug("Executing js");
        Map<String, String> jsScriptResult = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : jsScriptMap.entrySet()) {
            jsScriptResult.put(entry.getKey(), getDriver().executeScript(entry.getValue()).toString());
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
        byte[] snapshot = null;
        try {
            getLog().debug("Creating snapshot");
            snapshot = getDriver().getScreenshotAs(OutputType.BYTES); 
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
    public void initDriver() {
        if (firefoxDriverObjectPool != null && getDriver() == null) {
            getLog().debug("Initialisation FirefoxDriver terminated ");
            try {
                fd = firefoxDriverObjectPool.borrowObject();
            } catch (Exception ex) {
                getLog().warn("Firefox driver cannot be borrowed due to  " + ex.getMessage());
            }
        // if the firefoxDriver object pool is not set, keep the default behaviour
        } else if (getDriver() == null) {
            super.initDriver();
        }
    }
 
    /**
     * Properly Returns the WebDriver Object to the pool when finished or
     * close and quit the driver if the object pool is not used
     */
    private void properlyCloseWebDriver() {
        getLog().debug("Closing Firefox driver.");
        if (firefoxDriverObjectPool != null && getDriver() != null) {
            try {
                //set the blank page before returning the webDriver instance
                getDriver().get("");
                firefoxDriverObjectPool.returnObject(getDriver());
            } catch (Exception ex) {
                getLog().warn("Firefox driver cannot be returned due to  " + ex.getMessage());
            }
        } else {
            getDriver().close();
            getDriver().quit();
        }
    }
}