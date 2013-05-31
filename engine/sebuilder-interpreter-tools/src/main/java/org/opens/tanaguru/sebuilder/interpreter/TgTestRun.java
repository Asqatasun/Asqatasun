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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 * A single run of a test getScript().
 *
 * @author zarkonnen
 */
public class TgTestRun extends TestRun {

    private static final int RETRY_MAX_VALUE = 3;

    private boolean isStepOpenNewPage = false;
    private int retryIndex = 0;
    
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
            try {
                getLog().debug("Sleep before closing firefoxDriver");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                getLog().error(ex);
            }
            getLog().debug("Closing FirefoxgetDriver().");
            getDriver().quit();
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
            // for the last step, we need to sleep a while to leave enough time
            // to netExport to write
        } catch (TimeoutException te) {
            result = true;
            if (!isStepOpenNewPage && !StringUtils.equals(beforeUrl, getDriver().getCurrentUrl())) {
                fireNewPage();
            }
        } catch (Exception e) {
            if (e instanceof NoSuchElementException) {
                getLog().warn(currentStep() + " failed.", e);
                // if the element is not found we retry it
                if (retryIndex <= RETRY_MAX_VALUE) {
                    retryIndex++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(TgTestRun.class.getName()).error(ex);
                    }
                    next();
                }
            }
            getLog().error(currentStep() + " failed.", e);

            reset();
            throw new RuntimeException(currentStep() + " failed.", e);
        }

        if (!result) {
            // If a verify failed, we just note this but continue.
            if (currentStep().getType() instanceof Verify) {
                getLog().error(currentStep() + " failed.");
                return false;
            }
            // In all other cases, we throw an exception to stop the run.
            RuntimeException e = new RuntimeException(currentStep() + " failed.");
            e.fillInStackTrace();
            getLog().error(e);
            reset();
            throw e;
        } else {
//            stepIndex++;
            retryIndex = 0;
            return true;
        }
    }

    public void fireNewPage() {
        isStepOpenNewPage = true;
        if (newPageListeners == null) {
            return;
        }
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(TgTestRun.class.getName()).error(ex);
//        }
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

}