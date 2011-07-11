package org.opens.tanaguru.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.opens.tanaguru.crawler.Crawler;
import org.opens.tanaguru.crawler.CrawlerFactory;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.RelatedContent;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.parameterization.Parameter;
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
    /**
     * The path of the crawl configuration file
     */
    private String crawlConfigFilePath;
    @Override
    public void setCrawlConfigFilePath(String crawlConfigFilePath) {
        this.crawlConfigFilePath = crawlConfigFilePath;
    }

    @Override
    public String getCrawlConfigFilePath() {
        return crawlConfigFilePath;
    }

    /**
     * The auditDataService instance
     */
    private AuditDataService auditDataService;
    @Override
    public AuditDataService getAuditDataService() {
        return auditDataService;
    }

    @Override
    public void setAuditDataService(AuditDataService auditDataService) {
        this.auditDataService = auditDataService;
    }

    /**
     * The contentDataService instance
     */
    private ContentDataService contentDataService;
    public ContentDataService getContentDataService() {
        return contentDataService;
    }

    @Override
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
    public void setWebResourceDataService(WebResourceDataService webResourceDataService) {
        this.webResourceDataService = webResourceDataService;
    }

    /**
     * The crawler factory instance
     */
    private CrawlerFactory crawlerFactory;
    @Override
    public void setCrawlerFactory(CrawlerFactory crawlerFactory) {
        this.crawlerFactory = crawlerFactory;
    }

    /**
     * The webResource factory instance
     */
    private WebResourceFactory webResourceFactory;
    @Override
    public void setWebResourceFactory(WebResourceFactory webResourceFactory) {
        this.webResourceFactory = webResourceFactory;
    }

    /**
     * The content factory instance
     */
    private ContentFactory contentFactory;
    @Override
    public ContentFactory getContentFactory() {
        return contentFactory;
    }

    @Override
    public void setContentFactory(ContentFactory contentFactory) {
        this.contentFactory = contentFactory;
    }

    /**
     * The output directory needed by heritrix to create temporary files
     * during the crawl.
     */
    private String outputDir;
    @Override
    public String getOutputDir() {
        return outputDir;
    }

    @Override
    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    /**
     * Default constructor
     */
    public CrawlerServiceImpl() {
        super();
    }

    @Override
    public Page crawl(Page page) {
        Audit audit = page.getAudit();
        Crawler crawler = getCrawlerInstance((List<Parameter>)audit.getParameterSet());
        crawler.setPageURL(page.getURL());
        crawler.run();
        page = (Page) crawler.getResult();
        page.setAudit(audit);
        audit.setSubject(page);
        //the relation from webresource to audit is refresh, the audit has to
        // be persisted first
        auditDataService.saveOrUpdate(audit);
        setAuditToContent(page, audit);
        removeSummerRomance(page, audit);
        return page;
    }

    /**
     * Calls the crawler component process then updates the site.
     * @param site the site to crawl
     * @return returns the site after modification
     */
    @Override
    public Site crawl(Site site) {
        Audit audit = site.getAudit();
        Crawler crawler = getCrawlerInstance((List<Parameter>)audit.getParameterSet());

        int componentListSize = site.getComponentList().size();
        if (componentListSize == 0) {
            crawler.setSiteURL(site.getURL());
        } else {
            String[] urlPage = new String[componentListSize];
            Object[] webresourceTab = site.getComponentList().toArray();
            for (int i = 0; i < componentListSize; i++) {
                if (webresourceTab[i] instanceof WebResource) {
                    urlPage[i] = ((WebResource) webresourceTab[i]).getURL();
                }
            }
            crawler.setSiteURL(site.getURL(), urlPage);
        }
        crawler.run();
        site = (Site) crawler.getResult();
        site.setAudit(audit);
        audit.setSubject(site);
        //the relation from webresource to audit is refresh, the audit has to
        // be persisted first
        auditDataService.saveOrUpdate(audit);
        setAuditToContent(site, audit);
        removeSummerRomance(site, audit);
        return site;
    }

    /**
     * 
     * @param crawlParameters
     * @return
     */
    @Override
    public WebResource crawl(Audit audit) {
        Crawler crawler = getCrawlerInstance((List<Parameter>)audit.getParameterSet());
        crawler.run();
        WebResource webResource = crawler.getResult();
        webResource.setAudit(audit);
        audit.setSubject(webResource);
        auditDataService.saveOrUpdate(audit);
        setAuditToContent(webResource, audit);
        removeSummerRomance(webResource, audit);
        return webResource;
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
        Date endPersistDate = null;
        LOGGER.debug("Number Of SSP From WebResource " + wr.getURL() + " : " + nbOfContent);
        while (i.compareTo(nbOfContent) < 0) {
            if (LOGGER.isDebugEnabled()) {
                beginProcessDate = Calendar.getInstance().getTime();
                LOGGER.debug("Set audit to ssp from  "
                        + i + " to " + (i + PROCESS_WINDOW));
            }
            List<Long> contentIdList =
                    contentDataService.getSSPFromWebResource(wr.getId(), httpStatusCode, i.intValue(), PROCESS_WINDOW);
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
            i = i + PROCESS_WINDOW;
        }
        webResourceDataService.saveOrUpdate(wr);
    }

    /**
     * During the crawl, Webresources and Contents are created. Contents can
     * be of 2 types : SSP or relatedContent. A SSP is linked to a webResource and
     * a relatedContent is linked to a SSP. The relation between a ssp and a 
     * relatedContent is not known when fetching. So we need to link all the 
     * relatedContent to any SSP to be able to link them to the current audit.
     * At the end of the crawl, after the creation of the relation between a content 
     * and an audit, we can "clean" this fake relation.
     *
     * This method were supposed to be called removedFakeRelation but thanks to
     * the scottish guy, this method is now called removerSummerRomance.
     * @param webResource
     * @param audit
     */
    private void removeSummerRomance(WebResource webResource, Audit audit){
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
     * @return
     *       a crawler instance.
     */
    private Crawler getCrawlerInstance(List<Parameter> paramList) {
        Set<Parameter> paramSet = new HashSet<Parameter>();
        paramSet.addAll(paramList);
        return crawlerFactory.create(
                webResourceFactory,
                webResourceDataService,
                contentFactory,
                contentDataService,
                paramSet,
                outputDir,
                crawlConfigFilePath);
    }

}