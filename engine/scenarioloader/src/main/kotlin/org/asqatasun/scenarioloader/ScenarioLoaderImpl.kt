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

import jp.vmi.selenium.selenese.Context
import jp.vmi.selenium.selenese.Parser
import jp.vmi.selenium.selenese.Runner
import jp.vmi.selenium.selenese.TestProject
import jp.vmi.selenium.selenese.command.Echo
import jp.vmi.selenium.selenese.command.ICommand
import jp.vmi.selenium.selenese.inject.AbstractDoCommandInterceptor
import jp.vmi.selenium.selenese.inject.Binder
import jp.vmi.selenium.selenese.result.Result
import org.aopalliance.intercept.MethodInvocation
import org.apache.commons.io.IOUtils
import org.apache.http.HttpStatus.SC_OK
import org.asqatasun.entity.audit.Content
import org.asqatasun.entity.service.audit.ContentDataService
import org.asqatasun.entity.service.audit.PreProcessResultDataService
import org.asqatasun.entity.service.subject.WebResourceDataService
import org.asqatasun.entity.subject.Page
import org.asqatasun.entity.subject.Site
import org.asqatasun.entity.subject.WebResource
import org.asqatasun.util.factory.DateFactory
import org.asqatasun.util.http.HttpRequestHandler
import org.openqa.selenium.*
import org.openqa.selenium.support.ui.WebDriverWait
import org.slf4j.LoggerFactory
import java.io.ByteArrayInputStream
import java.io.IOException
import java.time.Duration
import java.util.*

/**
 *
 * @author jkowalczyk
 */
open class ScenarioLoaderImpl(private val webResource: WebResource,
                         private val scenario: String,
                         private val remoteWebDriverFactory: RemoteWebDriverFactory,
                         private val webResourceDataService: WebResourceDataService,
                         private val contentDataService: ContentDataService,
                         private val preProcessResultDataService: PreProcessResultDataService,
                         private val dateFactory: DateFactory,
                         private val jsScriptMap: Map<String, String>,
                         private val pageLoadDriverTimeout: Int) : ScenarioLoader, AbstractDoCommandInterceptor() {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(ScenarioLoaderImpl::class.java)
        private const val UFT8 = "UTF-8"
    }

    private val driver = remoteWebDriverFactory.createDriver(pageLoadDriverTimeout.toLong())
    private val result = ArrayList<Content>()
    private var pageRank = 1
    private var auditStateIndex = 1

    override fun getResult(): MutableList<Content> = result

    override fun run() {
        try {
            // necessary to record current class as new AbstractDoCommandInterceptor
            Binder.replaceBindModule(AsqaBindModule(this))

            val runner = Runner().apply {
                timeout = pageLoadDriverTimeout * 1000
                setDriver(driver)
            }
            val script =
                Parser.parse("check.side", ByteArrayInputStream(scenario.toByteArray()), runner.commandFactory)

            (script as TestProject).seleneseList.forEach { test -> runner.execute(test) }
            driver.quit()
        } catch (e: Exception) {
            LOGGER.info("error occurred while loading page " + e.message)
        }
    }

    private var lastVisitedUrl = "about:blank"

    /**
     * AbstractDoCommandInterceptor invoke method implementation.
     * Called every time a new command is executed
     */
    override fun invoke(invocation: MethodInvocation?, context: Context?, command: ICommand?, curArgs: Array<out String>?) =
        (invocation!!.proceed() as Result).also {
            waitPageFullyLoaded()
            if (command is Echo && (command).arguments[0].equals("audit", true)) {
                LOGGER.info("Fire new page ${driver.currentUrl} with echo command")
                fireNewPage(driver.currentUrl+"#"+auditStateIndex, driver.pageSource, executeJsScripts(), driver.getScreenshotAs(OutputType.BYTES))
                auditStateIndex++
            }
            if (lastVisitedUrl != driver.currentUrl) {
                LOGGER.info("Fire new page ${driver.currentUrl}, last visited Url is $lastVisitedUrl")
                    fireNewPage(driver.currentUrl, driver.pageSource, executeJsScripts(), driver.getScreenshotAs(OutputType.BYTES))
            }
            lastVisitedUrl = driver.currentUrl
        }


    /**
     *
     * @param url
     * @param source
     * @param scriptResult
     * @param snapshot
     */
    private fun fireNewPage(url: String, source: String, scriptResult: Map<String, String>, snapshot: ByteArray) {
        LOGGER.debug("fire New SSP $url")
        if (source.isNullOrBlank()) {
            LOGGER.debug("Empty SSP $$url not saved")
            return
        }
        var charset: String? = UFT8
        try {
            charset = HttpRequestHandler.extractCharset(IOUtils.toInputStream(source))
        } catch (ex: IOException) {
            LOGGER.warn(ex.message)
        }
        getWebResource(url)
                .also { page ->
                    contentDataService.getSSP(dateFactory.createDate(), url, source, page, SC_OK)
                            .let { ssp ->
                                ssp.charset = charset
                                ssp.audit = getAudit()
                                ssp.doctype = scriptResult.filterKeys { it == "doctypeExtractor" }.entries.first().value
                                contentDataService.saveOrUpdate(ssp)
                                result.add(ssp)
                            }
                }.also {page ->
                    scriptResult.filterKeys { it != "doctypeExtractor" }.forEach {
                        preProcessResultDataService.getPreProcessResult(it.key, it.value, getAudit(), page).let {
                            ppr -> preProcessResultDataService.saveOrUpdate(ppr)
                        }
                    }
                }

    }

    /**
     *
     * @param url
     * @return the page instance for a given URL
     */
    private fun getWebResource(url: String): Page? {
        var page: Page? = null
        when (webResource) {
            is Page -> {
                if (url != webResource.url) webResource.url = url
                page = webResource
            }
            is Site -> page = webResourceDataService.createPage(url).also { webResource.addChild(it) }
        }

        page!!.rank = pageRank
        pageRank++
        return webResourceDataService.saveOrUpdate(page) as Page
    }

    @Throws(WebDriverException::class)
    private fun executeJsScripts() =
        try {
            /* ##############################################################
             * ACHTUNG !!!!!!!!!!!!!!!!!!!!!!!!!!
             * this sendKeys action is here to grab the focus on the page.
             * It is needed later by the js script to execute the focus()
             * method on each element. Without it, the focus is kept by the address
             * bar.
             ############################################################## */
            driver.findElementByTagName("html").sendKeys(Keys.TAB)
            LOGGER.debug("Executing js")
            jsScriptMap.map { it.key to driver.executeScript(it.value).toString() }.toMap()
        } catch (e: WebDriverException) {
            LOGGER.info("${e.message}")
            Collections.emptyMap<String, String>()
        }

    /**
     * Periodically execute document.readyState to determine whether the page is fully loaded
     */
    private fun waitPageFullyLoaded() =
        try {
            WebDriverWait(driver, Duration.ofMillis(30000), Duration.ofMillis(200)).until {
                driver.executeScript("return document.readyState").toString() == "complete"
            }
        } catch (e: TimeoutException) {
            LOGGER.info("$e")
            false
        }

    private fun getAudit() = webResource.audit
}


