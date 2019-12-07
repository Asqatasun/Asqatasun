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
package org.asqatasun.sebuilder.tools;

import org.apache.log4j.Logger;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Implementation of FirefoxDriver poolable factory. Each instance of 
 * webDriver is instanciated with the value of the display when passed as 
 * JVM argument, with an implicit wait driver timeout (set by spring configuration)
 * and an page load driver timeout(set by spring configuration)
 * 
 * @author jkowalczyk
 */
public class FirefoxDriverPoolableObjectFactory {
//public class FirefoxDriverPoolableObjectFactory implements PoolableObjectFactory<FirefoxDriver> {

    private static final String DISPLAY_PROPERTY_KEY = "display";
    
    private Long implicitelyWaitDriverTimeout;
    public Long getImplicitelyWaitDriverTimeout() {
        return implicitelyWaitDriverTimeout;
    }

    public void setImplicitelyWaitDriverTimeout(Long implicitelyWaitDriverTimeout) {
        this.implicitelyWaitDriverTimeout = implicitelyWaitDriverTimeout;
    }

    private Long pageLoadDriverTimeout;
    public Long getPageLoadDriverTimeout() {
        return pageLoadDriverTimeout;
    }

    public void setPageLoadDriverTimeout(Long pageLoadDriverTimeout) {
        this.pageLoadDriverTimeout = pageLoadDriverTimeout;
    }
    
//    @Override
    public FirefoxDriver makeObject() throws Exception {
        FirefoxBinary ffBinary = new FirefoxBinary();
        if (System.getProperty(DISPLAY_PROPERTY_KEY) != null) {
            Logger.getLogger(this.getClass()).info("Setting Xvfb display with value " + System.getProperty(DISPLAY_PROPERTY_KEY));
            ffBinary.setEnvironmentProperty("DISPLAY", System.getProperty(DISPLAY_PROPERTY_KEY));
        }
        FirefoxDriver fd = new FirefoxDriver(ffBinary, ProfileFactory.getInstance().getScenarioProfile());
        if (this.implicitelyWaitDriverTimeout != null) {
            fd.manage().timeouts().implicitlyWait(this.implicitelyWaitDriverTimeout.longValue(), TimeUnit.SECONDS);
        }
        if (this.pageLoadDriverTimeout != null) {
            fd.manage().timeouts().pageLoadTimeout(this.pageLoadDriverTimeout.longValue(), TimeUnit.SECONDS);
        }
        return fd;
    }

//    @Override
    public void destroyObject(FirefoxDriver t) throws Exception {
        t.close();
        t.quit();
        if (t != null) {
            t.kill();
        }
    }

//    @Override
    public boolean validateObject(FirefoxDriver t) {
        return true;
    }

//    @Override
    public void activateObject(FirefoxDriver t) throws Exception {
//        DO NOTHING HERE
    }

//    @Override
    public void passivateObject(FirefoxDriver t) throws Exception {
//        DO NOTHING HERE
    }

}