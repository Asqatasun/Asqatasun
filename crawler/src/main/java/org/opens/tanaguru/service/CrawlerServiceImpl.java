package org.opens.tanaguru.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.opens.tanaguru.crawler.Crawler;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * Implementation of the crawler service.
 *
 * @author ADEX
 */
public class CrawlerServiceImpl implements CrawlerService {

    private static final Logger LOGGER = Logger.getLogger(CrawlerServiceImpl.class);
    private static final int PROCESS_WINDOW = 2000;
    private ContentDataService contentDataService;

    private WebResourceDataService webResourceDataService;
    public AuditDataService getAuditDataService() {
        return auditDataService;
    }

    public void setAuditDataService(AuditDataService auditDataService) {
        this.auditDataService = auditDataService;
    }

    public ContentDataService getContentDataService() {
        return contentDataService;
    }

    public void setContentDataService(ContentDataService contentDataService) {
        this.contentDataService = contentDataService;
    }

    public WebResourceDataService getWebResourceDataService() {
        return webResourceDataService;
    }

    public void setWebResourceDataService(WebResourceDataService webResourceDataService) {
        this.webResourceDataService = webResourceDataService;
    }

    private AuditDataService auditDataService;

    private Crawler crawler;
    @Override
    public void setCrawler(Crawler crawler) {
        this.crawler = crawler;
    }

    public CrawlerServiceImpl() {
        super();
    }

    @Override
    public Page crawl(Page page) {
        crawler.setPageURL(page.getURL());
        crawler.run();
        Audit audit = page.getAudit();
        page = (Page) crawler.getResult();
        page.setAudit(audit);
        audit.setSubject(page);
        //the relation from webresource to audit is refresh, the audit has to
        // be persisted first
        auditDataService.saveOrUpdate(audit);
//        webResourceDataService.saveOrUpdate(page);
        setAuditToContent(page, audit);
        return page;
    }

    /**
     * Calls the crawler component process then updates the site.
     * @param site the site to crawl
     * @return returns the site after modification
     */
    @Override
    public Site crawl(Site site) {
        int componentListSize = site.getComponentList().size();
        if (componentListSize == 0) {
            crawler.setSiteURL(site.getURL());
        } else {
            String[] urlPage = new String[componentListSize];
            Object[] webresourceTab = site.getComponentList().toArray();
            for (int i=0;i<componentListSize;i++) {
                if (webresourceTab[i] instanceof WebResource) {
                    urlPage[i] = ((WebResource)webresourceTab[i]).getURL();
                }
            }
            crawler.setSiteURL(site.getURL(), urlPage);
        }
        crawler.run();
        Audit audit = site.getAudit();
        site = (Site) crawler.getResult();
        site.setAudit(audit);
        audit.setSubject(site);
        //the relation from webresource to audit is refresh, the audit has to
        // be persisted first
        auditDataService.saveOrUpdate(audit);
        setAuditToContent(site, audit);
        return site;
    }

    public void setAuditToContent(WebResource wr, Audit audit) {
        Long nbOfContent = contentDataService.getNumberOfSSPFromWebResource(wr);
        Long i= Long.valueOf(0);
        Date endProcessDate = null;
        Date beginProcessDate = null;
        Date endPersistDate = null;
        LOGGER.debug("Number Of SSP From WebResource " + wr.getURL() + " : " +nbOfContent);
        while (i.compareTo(nbOfContent)<0) {
            if (LOGGER.isDebugEnabled()) {
                beginProcessDate = Calendar.getInstance().getTime();
                    LOGGER.debug("Set audit to ssp from  "
                            + i + " to " + (i + PROCESS_WINDOW));
            }
            List<Long> contentIdList =
                    contentDataService.getSSPFromWebResource(wr.getId(), i.intValue(), PROCESS_WINDOW);
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
            i = i+ PROCESS_WINDOW;
        }
        nbOfContent = contentDataService.getNumberOfRelatedContentFromWebResource(wr);
        LOGGER.debug("Number Of Related Content From WebResource?" + wr.getURL() + " : " + nbOfContent);
        i= Long.valueOf(0);
        while (i.compareTo(nbOfContent)<0) {
            if (LOGGER.isDebugEnabled()) {
                beginProcessDate = Calendar.getInstance().getTime();
                LOGGER.debug("Set audit to relatedContent from  "
                            + i + " to " + (i + PROCESS_WINDOW));
            }
            List<Long> contentIdList =
                    contentDataService.getRelatedContentFromWebResource(wr.getId(), i.intValue(), PROCESS_WINDOW);
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
            i = i+ PROCESS_WINDOW;
        }
        webResourceDataService.saveOrUpdate(wr);
    }
    
}