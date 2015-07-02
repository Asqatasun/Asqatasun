/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.service;

import java.util.*;
import org.apache.log4j.Logger;
import org.tanaguru.crawler.Crawler;
import org.tanaguru.crawler.CrawlerFactory;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.parameterization.Parameter;
import org.tanaguru.entity.service.audit.AuditDataService;
import org.tanaguru.entity.service.audit.ContentDataService;
import org.tanaguru.entity.service.subject.WebResourceDataService;
import org.tanaguru.entity.subject.WebResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.tanaguru.entity.audit.Content;
import org.tanaguru.entity.audit.RelatedContent;
import org.tanaguru.entity.audit.SSP;
import org.tanaguru.entity.service.parameterization.ParameterDataService;

/**
 * Implementation of the crawler service.
 *
 * @author jkowalczyk
 */
public class CrawlerServiceImpl implements CrawlerService {

    private static final Logger LOGGER = Logger.getLogger(CrawlerServiceImpl.class);
    private static final int PROCESS_WINDOW = 20;

    /**
     * The auditDataService instance
     */
    private AuditDataService auditDataService;

    @Override
    @Autowired
    public void setAuditDataService(AuditDataService auditDataService) {
        this.auditDataService = auditDataService;
    }

    /**
     * The auditDataService instance
     */
    private ParameterDataService parameterDataService;

    @Autowired
    public void setParameterDataService(ParameterDataService parameterDataService) {
        this.parameterDataService = parameterDataService;
    }

    /**
     * The contentDataService instance
     */
    private ContentDataService contentDataService;

    public ContentDataService getContentDataService() {
        return contentDataService;
    }

    @Override
    @Autowired
    public void setContentDataService(ContentDataService contentDataService) {
        this.contentDataService = contentDataService;
    }

    /**
     * The webResourceDataService instance
     */
    private WebResourceDataService webResourceDataService;

    @Override
    public WebResourceDataService getWebResourceDataService() {
        return webResourceDataService;
    }

    @Override
    @Autowired
    public void setWebResourceDataService(WebResourceDataService webResourceDataService) {
        this.webResourceDataService = webResourceDataService;
    }

    /**
     * The crawler factory instance
     */
    private CrawlerFactory crawlerFactory;

    @Override
    @Autowired
    public void setCrawlerFactory(CrawlerFactory crawlerFactory) {
        this.crawlerFactory = crawlerFactory;
    }

    /**
     * Default constructor
     */
    public CrawlerServiceImpl() {
        super();
    }

    @Override
    public WebResource crawlPage(Audit audit, String pageUrl) {
        Crawler crawler = getCrawlerInstance((Set<Parameter>) audit.getParameterSet(), true);
        crawler.setPageURL(pageUrl);
        return crawl(crawler, audit, true);
    }

    /**
     * Calls the crawler component process then updates the site.
     *
     * @param siteUrl the site to crawl
     * @param audit the current audit
     *
     * @return returns the site after modification
     */
    @Override
    public WebResource crawlSite(Audit audit, String siteUrl) {
        Crawler crawler = getCrawlerInstance((Set<Parameter>) audit.getParameterSet(), true);
        crawler.setSiteURL(siteUrl);
        return crawl(crawler, audit, true);
    }

    @Override
    public WebResource crawlGroupOfPages(Audit audit, String siteUrl, List<String> urlList) {
        Crawler crawler = getCrawlerInstance((Set<Parameter>) audit.getParameterSet(), true);
        crawler.setSiteURL(siteUrl, urlList);
        return crawl(crawler, audit, true);
    }

    /**
     *
     * @param crawler
     * @param audit
     * @return
     */
    private WebResource crawl(Crawler crawler, Audit audit, boolean persistOnTheFly) {
        crawler.run();
        WebResource wr = crawler.getResult();
        wr.setAudit(audit);
        audit.setSubject(wr);
        if (persistOnTheFly) {
            //the relation from webresource to audit is refresh, the audit has to
            // be persisted first
            auditDataService.saveOrUpdate(audit);
            setAuditToContent(wr, audit);
            removeSummerRomance(audit);
            removePageExcedent(wr, audit);
        }
        return wr;
    }

    /**
     * This method created the relation between the fetched contents and the
     * current audit.
     *
     * @param wr
     * @param audit
     */
    private void setAuditToContent(WebResource wr, Audit audit) {
        // httpStatusCode = -1 means all.
        int httpStatusCode = -1;
        Long nbOfContent = contentDataService.getNumberOfSSPFromWebResource(wr, httpStatusCode);
        Long i = Long.valueOf(0);
        Date endProcessDate = null;
        Date beginProcessDate = null;
        Date endPersistDate;

        LOGGER.debug("Number Of SSP From WebResource " + wr.getURL() + " : " + nbOfContent);
        while (i.compareTo(nbOfContent) < 0) {
            if (LOGGER.isDebugEnabled()) {
                beginProcessDate = Calendar.getInstance().getTime();
                LOGGER.debug("Set audit to ssp from  "
                        + i + " to " + (i + PROCESS_WINDOW));
            }
            Collection<Long> contentIdList
                    = contentDataService.getSSPIdsFromWebResource(
                            wr.getId(),
                            httpStatusCode,
                            i.intValue(),
                            PROCESS_WINDOW);
            if (LOGGER.isDebugEnabled()) {
                endProcessDate = Calendar.getInstance().getTime();
                LOGGER.debug("Retrieving  " + PROCESS_WINDOW + " SSP took "
                        + (endProcessDate.getTime() - beginProcessDate.getTime())
                        + " ms");
            }
            for (Long id : contentIdList) {
                contentDataService.saveAuditToContent(id, audit.getId());
            }
            if (LOGGER.isDebugEnabled()) {
                endPersistDate = Calendar.getInstance().getTime();
                LOGGER.debug("Persisting  " + PROCESS_WINDOW + " SSP took "
                        + (endPersistDate.getTime() - endProcessDate.getTime())
                        + " ms");
            }
            i = i + PROCESS_WINDOW;
        }
        nbOfContent = contentDataService.getNumberOfRelatedContentFromWebResource(wr);
        LOGGER.debug("Number Of Related Content From WebResource?" + wr.getURL() + " : " + nbOfContent);
        i = Long.valueOf(0);
        while (i.compareTo(nbOfContent) < 0) {
            if (LOGGER.isDebugEnabled()) {
                beginProcessDate = Calendar.getInstance().getTime();
                LOGGER.debug("Set audit to relatedContent from  "
                        + i + " to " + (i + PROCESS_WINDOW));
            }
            Collection<Long> contentIdList
                    = contentDataService.getRelatedContentIdsFromWebResource(
                            wr.getId(),
                            i.intValue(),
                            PROCESS_WINDOW);

            if (LOGGER.isDebugEnabled()) {
                endProcessDate = Calendar.getInstance().getTime();
                LOGGER.debug("Retrieving  " + PROCESS_WINDOW + " relatedContent took "
                        + (endProcessDate.getTime() - beginProcessDate.getTime())
                        + " ms");
            }
            for (Long id : contentIdList) {
                contentDataService.saveAuditToContent(id, audit.getId());
            }
            if (LOGGER.isDebugEnabled()) {
                endPersistDate = Calendar.getInstance().getTime();
                LOGGER.debug("Persisting  " + PROCESS_WINDOW + " relatedContent took "
                        + (endPersistDate.getTime() - endProcessDate.getTime())
                        + " ms");
            }
            i = i + PROCESS_WINDOW;
        }
        webResourceDataService.saveOrUpdate(wr);
    }

    /**
     * During the crawl, more Webresources and Contents than expected may
     * be retrieved. This methods delete them.
     *
     * @param webResource
     * @param audit
     */
    private void removePageExcedent(WebResource wr, Audit audit){
        int maxNumberOfCrawlPage = getMaxNumberOfCrawlPageFromAuditParameter(audit);
        // httpStatusCode = -1 means all.
        int httpStatusCode = -1;
        Long nbOfContent = contentDataService.getNumberOfSSPFromWebResource(wr, httpStatusCode);
        if (maxNumberOfCrawlPage == -1 || nbOfContent < maxNumberOfCrawlPage) {
            return;
        }
        Long i = (long) maxNumberOfCrawlPage +1 ;
        Long fromValue = (long) maxNumberOfCrawlPage ;
        LOGGER.info("Deleting "+ (nbOfContent - maxNumberOfCrawlPage)+" content excedent regarding user limit");
        while (i.compareTo(nbOfContent) < 0) {
            Collection<Long> contentIdList
                    = contentDataService.getSSPIdsFromWebResource(
                            wr.getId(),
                            httpStatusCode,
                            fromValue.intValue(),
                            PROCESS_WINDOW);
            LOGGER.info("Delete excedent content from " +i + " to "+(i+PROCESS_WINDOW));
            for (Long contentId : contentIdList){
                Content content = contentDataService.read(contentId);
                if (content instanceof SSP) {
                    webResourceDataService.delete(((SSP)content).getPage().getId());
                }
                contentDataService.delete(contentId);
            }
            i = i + PROCESS_WINDOW;
        }
    }
    
    /**
     * During the crawl, Webresources and Contents are created. Contents can be
     * of 2 types : SSP or relatedContent. A SSP is linked to a webResource and
     * a relatedContent is linked to a SSP. The relation between a ssp and a
     * relatedContent is not known when fetching. So we need to link all the
     * relatedContent to any SSP to be able to link them to the current audit.
     * At the end of the crawl, after the creation of the relation between a
     * content and an audit, we can "clean" this fake relation.
     *
     * This method were supposed to be called removedFakeRelation but thanks to
     * the scottish guy, this method is now called removerSummerRomance.
     *
     * @param webResource
     * @param audit
     */
    private void removeSummerRomance(Audit audit){
        Set<RelatedContent> relatedContentSet =
                (Set<RelatedContent>)contentDataService.getRelatedContentFromAudit(audit);
        for (RelatedContent relatedContent : relatedContentSet) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(" deleteContentRelationShip between of " + ((Content)relatedContent).getURI());
                contentDataService.deleteContentRelationShip(((Content)relatedContent).getId());
            }
        }
    }
    
    /**
     *
     * @return a crawler instance.
     */
    private Crawler getCrawlerInstance(Set<Parameter> paramSet, boolean persistOnTheFly) {
        return crawlerFactory.create(paramSet, persistOnTheFly);
    }

    private int getMaxNumberOfCrawlPageFromAuditParameter(Audit audit) {
        try {
            if (parameterDataService.getParameter(audit, "MAX_DOCUMENTS") != null) {
                return Integer.valueOf(parameterDataService.getParameter(audit, "MAX_DOCUMENTS").getValue());
            }
        } catch (NumberFormatException nfe) {
            return -1;
        }
        return -1;
    }
}
