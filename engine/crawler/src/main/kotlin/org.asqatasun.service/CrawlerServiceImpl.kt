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
package org.asqatasun.service;

import org.asqatasun.crawler.Crawler
import org.asqatasun.crawler.CrawlerImpl
import org.asqatasun.entity.audit.Audit
import org.asqatasun.entity.service.audit.AuditDataService
import org.asqatasun.entity.service.subject.WebResourceDataService
import org.asqatasun.entity.subject.WebResource
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

/**
 * Implementation of the crawler service.
 *
 * @author jkowalczyk
 */
@Service("crawlerService")
class CrawlerServiceImpl(
    private val auditDataService: AuditDataService,
    private val webResourceDataService: WebResourceDataService,
    @Value("\${app.engine.loader.proxy.host:}") private val proxyHost: String,
    @Value("\${app.engine.loader.proxy.port:}") private val proxyPort: String,
    @Value("\${app.engine.loader.proxy.user:}") private val proxyUser: String,
    @Value("\${app.engine.loader.proxy.password:}") private val proxyPassword: String,
    @Value("\${app.engine.loader.proxy.exclusionUrl:}") private val proxyExclusionUrl: String) : CrawlerService {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(CrawlerServiceImpl::class.java)
    }

    /**
     * Calls the crawler component process then updates the site.
     *
     * @param siteUrl the site to crawl
     * @param audit the current audit
     *
     * @return returns the site after modification
     */
    override fun crawlSite(audit: Audit, siteUrl: String): WebResource {
        val crawler = CrawlerImpl(audit, siteUrl, webResourceDataService,
            proxyHost, proxyPort, proxyUser, proxyPassword, proxyExclusionUrl.trim().split(","))
        return crawl(audit, crawler)
    }

    /**
     *
     * @param crawler
     * @param audit
     * @return
     */
    private fun crawl(audit: Audit, crawler: Crawler): WebResource {
        crawler.run()
        val wr = crawler.result
        wr.audit = audit
        audit.subject = wr
        // the relation from webResource to audit is refresh, the audit has to be persisted first
        auditDataService.saveOrUpdate(audit)
        return wr
    }

}
