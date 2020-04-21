/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2020  Asqatasun.org
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

package org.asqatasun.scenarioloader

import org.openqa.selenium.Platform
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.net.URL
import java.util.concurrent.TimeUnit

@Component
class RemoteWebDriverFactory(
    @Value("\${app.engine.loader.selenium.headless:true}") private val headless: Boolean,
    @Value("\${app.engine.loader.selenium.hubUrl:}") private val hubUrl: String,
    @Value("\${app.engine.loader.proxy.host:}") private val proxyHost: String,
    @Value("\${app.engine.loader.proxy.port:}") private val proxyPort: String) {

    companion object {
        private const val SCENARIO_IMPLICITELY_WAIT_TIMEOUT = 60L
    }

    fun createDriver(pageLoadDriverTimeout: Long) =
        chooseDriver().apply {
            manage().timeouts().pageLoadTimeout(pageLoadDriverTimeout, TimeUnit.SECONDS)
            manage().timeouts().implicitlyWait(SCENARIO_IMPLICITELY_WAIT_TIMEOUT, TimeUnit.SECONDS)
            manage().deleteAllCookies()
        }

    private fun chooseDriver() =
        if (hubUrl.isNullOrBlank()) createFirefoxDriver() else createRemoteDriver()


    private fun createFirefoxDriver() =
        FirefoxOptions()
            .apply {
                setHeadless(headless)
                setAcceptInsecureCerts(true)
                profile = createProfile(true) }
            .let { FirefoxDriver(it) }

    private fun createRemoteDriver() =
        DesiredCapabilities()
            .apply {
                platform = Platform.LINUX
                browserName = "firefox"}
            .let{ capabilities -> RemoteWebDriver(URL(hubUrl), capabilities) }


    private fun createProfile(loadImage: Boolean): FirefoxProfile {
        val profile = FirefoxProfile()
        // to have a blank page on start-up
        profile.apply {
            setPreference("browser.startup.page", 0)
            setPreference("browser.cache.disk.capacity", 0)
            setPreference("browser.cache.disk.enable", false)
            setPreference("browser.cache.disk.smart_size.enabled", false)
            setPreference("browser.cache.disk.smart_size.first_run", false)
            setPreference("browser.cache.disk.smart_size_cached_value", 0)
            setPreference("browser.cache.memory.enable", false)
            setPreference("browser.shell.checkDefaultBrowser", false)
            setPreference("browser.startup.homepage_override.mstone", "ignore")
            setPreference("browser.preferences.advanced.selectedTabIndex", 0)
            setPreference("browser.privatebrowsing.autostart", false)
            setPreference("browser.link.open_newwindow", 2)
            setPreference("Network.cookie.cookieBehavior", 1)
            setPreference("signon.autologin.proxy", true)
            setPreference("browser.search.update", false)
            setPreference("browser.tabs.remote.autostart", false)
            setPreference("browser.tabs.remote.autostart.1", false)
            setPreference("browser.tabs.remote.autostart.2", false)
            setPreference("browser.tabs.remote.force-enable", "false")
        }
        if (loadImage) {
            // To enable the load of images
            profile.setPreference("permissions.default.image", 1)
        } else {
            // To disable the load of images
            profile.setPreference("permissions.default.image", 2)
        }
        return profile
    }

}
