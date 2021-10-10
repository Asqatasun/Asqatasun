/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2021  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.crawler

import edu.uci.ics.crawler4j.crawler.CrawlConfig
import edu.uci.ics.crawler4j.crawler.CrawlController
import edu.uci.ics.crawler4j.crawler.CrawlController.WebCrawlerFactory
import edu.uci.ics.crawler4j.fetcher.PageFetcher
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer
import org.apache.commons.lang3.RandomStringUtils
import org.asqatasun.entity.audit.Audit
import org.asqatasun.entity.parameterization.Parameter
import org.asqatasun.entity.service.subject.WebResourceDataService
import org.asqatasun.entity.subject.Site
import org.asqatasun.entity.subject.WebResource
import org.slf4j.LoggerFactory
import org.springframework.web.util.UriUtils
import java.net.URL

class CrawlerImpl(private val audit: Audit,
                  private val siteURL: String,
                  private val webResourceDataService: WebResourceDataService,
                  private val proxyHost: String?,
                  private val proxyPort: String?,
                  private val proxyUsername: String?,
                  private val proxyPassword: String?,
                  private val proxyExclusionUrl: List<String>,
                  private val politenessDelay: Int): Crawler {

    private lateinit var mainWebResource: Site
    private var urlList: ArrayList<String> = ArrayList()

    companion object {
        private val LOGGER = LoggerFactory.getLogger(CrawlerImpl::class.java)
        private const val MAX_DEPTH_PARAM_KEY = "DEPTH"
        private const val MAX_DOCUMENTS_PARAM_KEY = "MAX_DOCUMENTS"
        private const val MAX_DURATION_PARAM_KEY = "MAX_DURATION"
        private const val EXCLUSION_REGEX_PARAM_KEY = "EXCLUSION_REGEXP"
        private const val INCLUSION_REGEX_PARAM_KEY = "INCLUSION_REGEXP"
        private const val ROBOTS_TXT_ACTIVATION = "ROBOTS_TXT_ACTIVATION"
        // Number of threads to use during crawling. Increasing this typically makes crawling faster. But crawling
        // speed depends on many other factors as well. You can experiment with this to figure out what number of
        // threads works best for you.
        // Number of threads to use during crawling. Increasing this typically makes crawling faster. But crawling
        // speed depends on many other factors as well. You can experiment with this to figure out what number of
        // threads works best for you.
        private const val NUMBER_OF_CRAWLERS = 4
        private const val USER_AGENT = "asqatasun + asqatasun@asqatasun.org"
    }

    override fun getParameterSet(): MutableSet<Parameter> = audit.parameterSet as MutableSet<Parameter>

    override fun setParameterSet(paramSet: MutableSet<Parameter>?) {
        TODO("Not yet implemented")
    }

    override fun getResult(): WebResource {
        LOGGER.info("fetch " + urlList.size + " urls")
        return mainWebResource
    }

    override fun run() {
        mainWebResource = webResourceDataService.createSite(siteURL)
        mainWebResource = webResourceDataService.saveOrUpdate(mainWebResource) as Site

        // The factory which creates instances of crawlers.
        val factory: WebCrawlerFactory<AsqatasunWebCrawlerImpl> =
            WebCrawlerFactory {
                AsqatasunWebCrawlerImpl(URL(siteURL), this,
                    Integer.valueOf(getParameterValue(MAX_DURATION_PARAM_KEY)),
                    getParameterValue(EXCLUSION_REGEX_PARAM_KEY),
                    getParameterValue(INCLUSION_REGEX_PARAM_KEY)) }

        val crawlConfig = initCrawlConfig()
        val controller = initCrawlController(crawlConfig)
        // Start the crawl. This is a blocking operation, meaning that your code
        // will reach the line after this only when crawling is finished.
        controller.start(factory, NUMBER_OF_CRAWLERS)
        controller.waitUntilFinish()
    }

    fun fireNewPage(pageUrl: String) {
        LOGGER.info("fire New Page ${UriUtils.decode(pageUrl, "UTF-8")} " + mainWebResource.url)
        val pageWebResource = webResourceDataService.createPage(pageUrl)
        pageWebResource.parent = mainWebResource
        webResourceDataService.saveOrUpdate(pageWebResource)
        urlList.add(pageUrl)
    }

    private fun initCrawlConfig(): CrawlConfig {
        val config = CrawlConfig()

        // Set the folder where intermediate crawl data is stored (e.g. list of urls that are extracted from previously
        // fetched pages and need to be crawled later).
        config.crawlStorageFolder = "/tmp/asqatasun/"+ RandomStringUtils.randomAlphanumeric(6)+"/";

        // Be polite: Make sure that we don't send more than 1 request per 100 milliseconds.
        // Otherwise it may overload the target servers.
        // This parameter can be overridden using the spring property app.engine.loader.politenessDelay
        config.politenessDelay = politenessDelay

        // You can set the maximum crawl depth here. The default value is -1 for unlimited depth.
        config.maxDepthOfCrawling = Integer.valueOf(getParameterValue(MAX_DEPTH_PARAM_KEY))

        // You can set the maximum number of pages to crawl. The default value is -1 for unlimited number of pages.
        config.maxPagesToFetch = Integer.valueOf(getParameterValue(MAX_DOCUMENTS_PARAM_KEY)) + 1

        config.userAgentString = USER_AGENT

        // Should binary data should also be crawled? example: the contents of pdf, or the metadata of images etc
        config.isIncludeBinaryContentInCrawling = false

        config.cleanupDelaySeconds = 1
        config.threadMonitoringDelaySeconds = 1
        config.threadShutdownDelaySeconds = 1

        val url = URL(siteURL)
        // Do you need to set a proxy? If so, you can use:
        if (!proxyExclusionUrl.contains(url.host) && !proxyHost.isNullOrBlank() && !proxyPort.isNullOrBlank()) {
            config.proxyHost = proxyHost
            config.proxyPort = Integer.valueOf(proxyPort)
            // If your proxy also needs authentication:
            if (!proxyUsername.isNullOrBlank() && !proxyPassword.isNullOrBlank()) {
                config.proxyUsername = proxyUsername
                config.proxyPassword = proxyPassword
            }
        }

        return config
    }

    private fun initCrawlController(config: CrawlConfig): CrawlController {
        // Instantiate the controller for this crawl.
        val pageFetcher = PageFetcher(config)
        val robotsTxtConfig = RobotstxtConfig()
        robotsTxtConfig.userAgentName = USER_AGENT
        if (!getParameterValue(ROBOTS_TXT_ACTIVATION)!!.toBoolean()) {
            robotsTxtConfig.isEnabled = false
            robotsTxtConfig.ignoreUADiscrimination = true
        }
        val robotsTxtServer = RobotstxtServer(robotsTxtConfig, pageFetcher)
        val controller = CrawlController(config, pageFetcher, robotsTxtServer)

        // For each crawl, you need to add some seed urls. These are the first
        // URLs that are fetched and then the crawler starts following links
        // which are found in these pages
        controller.addSeed(siteURL)

        return controller
    }

    private fun getParameterValue(parameterCode: String) =
        parameterSet.first { p -> p.parameterElement.parameterElementCode.equals(parameterCode) }.value

}

