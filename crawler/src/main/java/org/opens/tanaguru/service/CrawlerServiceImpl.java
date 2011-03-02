package org.opens.tanaguru.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.opens.tanaguru.crawler.Crawler;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.RelatedContent;
import org.opens.tanaguru.entity.audit.SSP;
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

    private void setAuditToContent(WebResource wr, Audit audit) {
        Long nbOfContent = contentDataService.getNumberOfSSPFromWebResource(wr);
        Long i=new Long(0);
        while (i.compareTo(nbOfContent)<0) {
            List<? extends Content> contentList =
                    contentDataService.getContentWithRelatedContentFromWebResource(wr, i.intValue(), 1);
            for (Content content : contentList) {
                content.setAudit(audit);
                contentDataService.saveOrUpdate(content);
                if (content instanceof SSP) {
                    for (RelatedContent relatedContent : ((SSP)content).getRelatedContentSet()) {
                        ((Content)relatedContent).setAudit(audit);
                        contentDataService.saveOrUpdate((Content)relatedContent);
                    }
                }
            }
            i++;
        }
        webResourceDataService.saveOrUpdate(wr);
    }
    
}