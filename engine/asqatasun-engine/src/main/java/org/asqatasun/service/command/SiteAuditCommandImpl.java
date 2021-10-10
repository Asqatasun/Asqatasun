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

package org.asqatasun.service.command;

import org.asqatasun.entity.audit.AuditStatus;
import org.asqatasun.entity.audit.Tag;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.service.audit.AuditDataService;
import org.asqatasun.service.CrawlerService;
import org.asqatasun.util.FileNaming;
import org.asqatasun.util.http.HttpRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

import static org.asqatasun.entity.contract.ScopeEnum.DOMAIN;

/**
 *
 * @author jkowalczyk
 */
public final class SiteAuditCommandImpl extends AuditCommandImpl {

     /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SiteAuditCommandImpl.class);
    /**
     * The crawlerService instance
     */
    private final CrawlerService crawlerService;

    /**
     * 
     * @param siteUrl
     * @param paramSet
     * @param auditDataService 
     */
    public SiteAuditCommandImpl(
            String siteUrl,
            Set<Parameter> paramSet,
            List<Tag> tagList,
            CrawlerService crawlerService,
            AuditDataService auditDataService) {
        super(paramSet, tagList, auditDataService, DOMAIN);
        this.crawlerService = crawlerService;
        try {
            URL baseURL = new URL(FileNaming.addProtocolToUrl(siteUrl));
            this.targetUrl = baseURL.getProtocol()+"://"+baseURL.getHost();
        } catch (MalformedURLException e) {
            LOGGER.warn("Malformed URL encountered : " + e.getMessage());
        }
    }

    @Override
    public void init() {
        if (HttpRequestHandler.getInstance().isUrlAccessible(this.targetUrl)) {
            super.init();
            setStatusToAudit(AuditStatus.CRAWLING);
        } else {
            super.init();
            createEmptySiteResource(this.targetUrl);
            setStatusToAudit(AuditStatus.ERROR);
        }
    }

    @Override
    public void crawl() {
        if (!getAudit().getStatus().equals(AuditStatus.CRAWLING)) {
            LOGGER.warn(
                "Audit status is " +
                    getAudit().getStatus() +
                    " while " +
                    AuditStatus.CRAWLING +
                    " was required.");
            return;
        }

        crawlerService.crawlSite(getAudit(), this.targetUrl);
        Long nbUrls = webResourceDataService.getChildWebResourceCount(getAudit().getSubject());

        if (nbUrls > 0) {
            setStatusToAudit(AuditStatus.SCENARIO_LOADING);
        } else {
            LOGGER.warn("Audit has no content");
            setStatusToAudit(AuditStatus.ERROR);
        }

    }

}
