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

import edu.uci.ics.crawler4j.crawler.Page
import edu.uci.ics.crawler4j.crawler.WebCrawler
import edu.uci.ics.crawler4j.parser.HtmlParseData
import edu.uci.ics.crawler4j.url.WebURL
import org.apache.http.Header
import org.springframework.web.util.UriUtils
import java.net.URL
import java.util.*
import java.util.regex.Pattern

class AsqatasunWebCrawlerImpl(private val siteUrl: URL,
                              private val crawler: CrawlerImpl,
                              private val maxDuration: Int,
                              private val exclusionRegexp: String?,
                              private val inclusionRegexp: String?): WebCrawler() {

    private var startedTime: Long = 0

    companion object {
        private val BLACKLIST = Pattern.compile(".*(\\.(mp3|mp4|zip|gz|txt|css|js|xml|jpg|jpeg|png|gif|pdf))$")
    }

    override fun onStart() {
        startedTime = Date().time / 1000
    }

    /**
     * You should implement this function to specify whether the given url
     * should be crawled or not (based on your crawling logic).
     */
    override fun shouldVisit(referringPage: Page?, url: WebURL): Boolean {
        if (!super.shouldVisit(referringPage, url)) {
            return false
        }
        val href = url.url.toLowerCase()

        if (BLACKLIST.matcher(href).matches()) return false
        // Ignore the url if it has an extension that matches our defined set of image extensions.
        if (!exclusionRegexp.isNullOrBlank() && Pattern.compile(exclusionRegexp).matcher(href).matches()) return false
        if (!inclusionRegexp.isNullOrBlank() && Pattern.compile(inclusionRegexp).matcher(href).matches()) return true

        val currentHost = if(url.subDomain.isNotBlank()) url.subDomain+"."+url.domain else url.domain
        return currentHost.equals(siteUrl.host)
    }

    /**
     * This function is called when a page is fetched and ready to be processed
     * by your program.
     */
    override fun visit(page: Page) {
        val docid: Int = page.webURL.docid
        val url: String = page.webURL.url
        val domain: String = page.webURL.domain
        val path: String = page.webURL.path
        val subDomain: String = page.webURL.subDomain
        logger.debug("Docid: {}", docid)
        logger.debug("Domain: '{}'", domain)
        logger.debug("Sub-domain: '{}'", subDomain)
        logger.debug("Path: '{}'", path)

        // @TODO Deal with status code

        if(page.contentType == null
            || !page.contentType.contains("text/html")){
            logger.error("Page content does not match html");
            return;
        }

        if (page.parseData is HtmlParseData) {
            val htmlParseData = page.parseData as HtmlParseData
            val text = htmlParseData.text
            val html = htmlParseData.html
            val links = htmlParseData.outgoingUrls
            logger.debug("Text length: {}", text.length)
            logger.debug("Html length: {}", html.length)
            logger.debug("Number of outgoing links: {}", links.size)
        }
        val responseHeaders: Array<Header> = page.fetchResponseHeaders
        logger.debug("Response headers:")
        for (header in responseHeaders) {
            logger.debug("\t{}: {}", header.name, header.value)
        }
        crawler.fireNewPage(url)
        checkDurationLimit()
    }

    override fun handleUrlBeforeProcess(curURL: WebURL): WebURL {
        curURL.url = UriUtils.encodePath(curURL.url, "UTF-8")
        return curURL
    }

    private fun checkDurationLimit() {
        if (Date().time /1000 - startedTime >= maxDuration) {
            logger.info("[CRAWLER - " + getMyId() + "] Crawler time over, stop crawling...")
            myController.shutdown()
        }
    }

}
