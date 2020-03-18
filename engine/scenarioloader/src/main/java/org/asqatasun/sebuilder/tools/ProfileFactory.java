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

import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author jkowalczyk
 */
@Component
public final class ProfileFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileFactory.class);

    private String netExportPath = ".";
    public void setNetExportPath(String netExportPath) {
        this.netExportPath = netExportPath;
    }
    
    private boolean deleteProfileData = false;
    public void setDeleteProfileData(boolean deleteProfileData) {
        this.deleteProfileData = deleteProfileData;
    }

    @Value("${app.engine.loader.proxy.port}")
    private String proxyPort;
    @Value("${app.engine.loader.proxy.host}")
    private String proxyHost;
    @Value("${app.engine.loader.pathToPreSetProfile:}")
    private String pathToPreSetProfile;
    @Value("${app.engine.loader.proxy.exclusionUrl:}")
    private String proxyExclusionUrl;
    
    /**
     * Each profile is started with a specific NetExport path (created randomly).
     * That map associated this path for a given profile
     */
    private Map<FirefoxProfile, String> netExportPathMap = new HashMap<>();
    
    /**
     * The holder that handles the unique instance of ProfileFactory
     */
    private static class ProfileFactoryHolder {
        private static final ProfileFactory INSTANCE = new ProfileFactory();
    }
    
    /**
     * Private constructor
     */
    private ProfileFactory() {}
    
    /**
     * Singleton pattern based on the "Initialization-on-demand 
     * holder idiom". See @http://en.wikipedia.org/wiki/Initialization_on_demand_holder_idiom
     * @return the unique instance of ProfileFactory
     */
    public static ProfileFactory getInstance() {
        return ProfileFactoryHolder.INSTANCE;
    }
    
    /**
     * 
     * @return 
     */
    public FirefoxProfile getScenarioProfile() {
        return getProfile(true);
    }
    
    /**
     * 
     * @return 
     */
    public FirefoxProfile getOnlineProfile() {
        return getProfile(true);
    }
    
    /**
     * 
     * @return 
     *      a set-up Firefox profile
     */
    private FirefoxProfile getProfile(boolean loadImage) {
        if (StringUtils.isNotBlank(pathToPreSetProfile)) {
            File presetProfileDir = new File(pathToPreSetProfile);
            if (presetProfileDir.exists() 
                  && presetProfileDir.canRead() 
                  && presetProfileDir.canExecute()
                  && presetProfileDir.canWrite()) {
                LOGGER.debug(
                        "Start firefox profile with path " 
                        + presetProfileDir.getAbsolutePath());
                return new FirefoxProfile(presetProfileDir);
            } else {
                LOGGER.debug(
                        "The profile with path " 
                        + presetProfileDir.getAbsolutePath()
                        + " doesn't exist or don't have permissions");
            }
        }
        LOGGER.debug("Start firefox with fresh new profile");
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        setUpPreferences(firefoxProfile, loadImage);
        setUpProxy(firefoxProfile);
        return firefoxProfile;
    }
    
    /**
     * 
     * @param firefoxProfile
     * @return 
     */
    public String getNetExportPath(FirefoxProfile firefoxProfile) {
        return netExportPathMap.get(firefoxProfile);
    }
    
    /**
     * 
     * @param firefoxProfile 
     */
    public void shutdownFirefoxProfile(FirefoxProfile firefoxProfile) {
        try {
            if (deleteProfileData) {
                FileDeleteStrategy.FORCE.delete(new File(netExportPathMap.get(firefoxProfile)));
            }
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
        netExportPathMap.remove(firefoxProfile);
    }
 
    /**
     * 
     * @param firefoxProfile 
     */
    private void setUpPreferences(FirefoxProfile firefoxProfile, boolean loadImage) {

        // to have a blank page on start-up
        firefoxProfile.setPreference("browser.startup.page", 0);
        firefoxProfile.setPreference("browser.cache.disk.capacity", 0);
        firefoxProfile.setPreference("browser.cache.disk.enable", false);
        firefoxProfile.setPreference("browser.cache.disk.smart_size.enabled", false);
        firefoxProfile.setPreference("browser.cache.disk.smart_size.first_run", false);
        firefoxProfile.setPreference("browser.cache.disk.smart_size_cached_value", 0);
        firefoxProfile.setPreference("browser.cache.memory.enable", false);
        firefoxProfile.setPreference("browser.shell.checkDefaultBrowser", false);
        firefoxProfile.setPreference("browser.startup.homepage_override.mstone", "ignore");
        firefoxProfile.setPreference("browser.preferences.advanced.selectedTabIndex", 0);
        firefoxProfile.setPreference("browser.privatebrowsing.autostart", false);
        firefoxProfile.setPreference("browser.link.open_newwindow", 2);
        firefoxProfile.setPreference("Network.cookie.cookieBehavior", 1);
        firefoxProfile.setPreference("signon.autologin.proxy", true);


        // to disable the update of search engines
        firefoxProfile.setPreference("browser.search.update", false);
        
        if (loadImage) {
            // To enable the load of images
            firefoxProfile.setPreference("permissions.default.image", 1);
        }else {
            // To disable the load of images
            firefoxProfile.setPreference("permissions.default.image", 2);
        }
    }
    
    private void setUpProxy(FirefoxProfile firefoxProfile) {
        if (!StringUtils.isEmpty(proxyPort) && !StringUtils.isEmpty(proxyHost)) {
            StringBuilder strb = new StringBuilder(proxyHost);
            strb.append(":");
            strb.append(proxyPort);
            Proxy proxy = new Proxy();
            proxy.setFtpProxy(strb.toString());
            proxy.setHttpProxy(strb.toString());
            proxy.setSslProxy(strb.toString());
            if (StringUtils.isNotEmpty(proxyExclusionUrl)) {
                proxy.setNoProxy(proxyExclusionUrl.replaceAll(";", ","));
            }
                firefoxProfile.setProxyPreferences(proxy);
        }
    }
    
    /**
     * 
     * @return 
     */
    private String createRandomNetExportPath(FirefoxProfile firefoxProfile) {
        StringBuilder strb = new StringBuilder(netExportPath);
        strb.append(new Random().nextLong());
        netExportPathMap.put(firefoxProfile, strb.toString());
        return strb.toString();
    }

}
