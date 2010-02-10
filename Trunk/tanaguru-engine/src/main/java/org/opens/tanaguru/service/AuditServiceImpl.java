package org.opens.tanaguru.service;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import java.util.List;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.service.audit.ProcessResultDataService;
import org.opens.tanaguru.entity.service.reference.TestDataService;

/**
 * 
 * @author ADEX
 */
public class AuditServiceImpl implements AuditService {

    private AnalyserService analyserService;
    private AuditDataService auditDataService;
    private ConsolidatorService consolidatorService;
    private ContentAdapterService contentAdapterService;
    private ContentLoaderService contentLoaderService;
    private CrawlerService crawlerService;
    private ProcessorService processorService;
    private ProcessResultDataService processResultDataService;
    private TestDataService testDataService;
    private WebResourceDataService webResourceDataService;

    public AuditServiceImpl() {
        super();
    }

    public Audit auditPage(String pageUrl, String[] testCodeList) {
        Page page = webResourceDataService.createPage(pageUrl);

        List<Test> testList = testDataService.findAllByCode(testCodeList);

        Audit audit = auditDataService.create();
        audit.setSubject(page);
        audit.setTestList(testList);
        audit.setStatus(AuditStatus.CONTENT_LOADING);

        audit = audit(audit);
        return audit;
    }

    public Audit auditSite(String siteUrl, String[] testCodeList) {
        Site site = webResourceDataService.createSite(siteUrl);

        List<Test> testList = testDataService.findAllByCode(testCodeList);

        Audit audit = auditDataService.create();
        audit.setSubject(site);
        audit.setTestList(testList);
        audit.setStatus(AuditStatus.CRAWLING);

        audit = audit(audit);
        return audit;
    }

    public Audit auditSite(String siteUrl, String[] pageUrlList, String[] testCodeList) {
        Site site = webResourceDataService.createSite(siteUrl);
        for (String pageUrl : pageUrlList) {
            site.addChild(webResourceDataService.createPage(pageUrl));
        }

        List<Test> testList = testDataService.findAllByCode(testCodeList);

        Audit audit = auditDataService.create();
        audit.setSubject(site);
        audit.setTestList(testList);
        audit.setStatus(AuditStatus.CONTENT_LOADING);

        audit = audit(audit);
        return audit;
    }

    public Audit audit(Audit audit) {
        audit = init(audit);
        audit = crawl(audit);
        audit = loadContent(audit);
        audit = adaptContent(audit);
        audit = process(audit);
        audit = consolidate(audit);
        audit = analyse(audit);
        return audit;
    }

    public Audit init(Audit audit) {
        if (audit.getSubject() == null || audit.getTestList().isEmpty()) {
            Logger.getLogger(AuditServiceImpl.class).warn("Audit is not well initialized");
            return audit;
        }
        if (audit.getStatus().equals(AuditStatus.INITIALISATION)) {
            audit.setStatus(AuditStatus.CRAWLING);
            auditDataService.saveOrUpdate(audit);
        }
        return audit;
    }

    public Audit crawl(Audit audit) {
        if (!audit.getStatus().equals(AuditStatus.CRAWLING)) {
            Logger.getLogger(AuditServiceImpl.class).warn(
                    "Audit status is "
                    + audit.getStatus()
                    + " while "
                    + AuditStatus.CRAWLING
                    + " was required");
            return audit;
        }

        if (audit.getSubject() instanceof Site) {
            crawlerService.crawl((Site) audit.getSubject());
        }

        audit.setStatus(AuditStatus.CONTENT_LOADING);

        audit = auditDataService.saveOrUpdate(audit);
        return audit;
    }

    public Audit loadContent(Audit audit) {
        if (!audit.getStatus().equals(AuditStatus.CONTENT_LOADING)) {
            Logger.getLogger(AuditServiceImpl.class).warn(
                    "Audit status is "
                    + audit.getStatus()
                    + " while "
                    + AuditStatus.CONTENT_LOADING
                    + " was required");
            return audit;
        }

        audit.addAllContent(contentLoaderService.loadContent(audit.getSubject()));

        boolean hasContent = false;
        for (Content content : audit.getContentList()) {
            if (content instanceof SSP) {
                hasContent = true;
                break;
            }
        }
        if (hasContent) {
            audit.setStatus(AuditStatus.CONTENT_ADAPTING);
        } else {
            Logger.getLogger(AuditServiceImpl.class).warn("Audit has no content");
            audit.setStatus(AuditStatus.ERROR);
        }

        audit = auditDataService.saveOrUpdate(audit);
        return audit;
    }

    public Audit adaptContent(Audit audit) {
        if (!audit.getStatus().equals(AuditStatus.CONTENT_ADAPTING)) {
            Logger.getLogger(AuditServiceImpl.class).warn(
                    "Audit status is "
                    + audit.getStatus()
                    + " while "
                    + AuditStatus.CONTENT_ADAPTING
                    + " was required");
            return audit;
        }

        audit.setContentList(contentAdapterService.adaptContent((List<Content>) audit.getContentList()));

        boolean hasCorrectedDOM = false;
        for (Content content : audit.getContentList()) {
            if (content instanceof SSP) {
                if (!((SSP) content).getDOM().isEmpty()) {
                    hasCorrectedDOM = true;
                    break;
                }
            }
        }
        if (hasCorrectedDOM) {
            audit.setStatus(AuditStatus.PROCESSING);
        } else {
            Logger.getLogger(AuditServiceImpl.class).warn("Audit has no corrected DOM");
            audit.setStatus(AuditStatus.ERROR);
        }

        audit = auditDataService.saveOrUpdate(audit);
        return audit;
    }

    public Audit process(Audit audit) {
        if (!audit.getStatus().equals(AuditStatus.PROCESSING)) {
            Logger.getLogger(AuditServiceImpl.class).warn(
                    "Audit status is "
                    + audit.getStatus()
                    + " while "
                    + AuditStatus.PROCESSING
                    + " was required");
            return audit;
        }

        audit.setGrossResultList(processorService.process((List<Content>) audit.getContentList(), (List<Test>) audit.getTestList()));

        if (!audit.getGrossResultList().isEmpty()) {
            audit.setStatus(AuditStatus.CONSOLIDATION);
        } else {
            Logger.getLogger(AuditServiceImpl.class).warn("Audit has no gross result");
            audit.setStatus(AuditStatus.ERROR);
        }

        audit = auditDataService.saveOrUpdate(audit);

        return audit;
    }

    public Audit consolidate(Audit audit) {
        if (!audit.getStatus().equals(AuditStatus.CONSOLIDATION)) {
            Logger.getLogger(AuditServiceImpl.class).warn(
                    "Audit status is "
                    + audit.getStatus()
                    + " while "
                    + AuditStatus.CONSOLIDATION
                    + " was required");
            return audit;
        }

        // XXX Chargement explicite des resultats bruts, pour forcer le lazy loading
//        for (ProcessResult grossResult : audit.getGrossResultList()) {
//        }

        List<ProcessResult> netResultList = consolidatorService.consolidate(
                (List<ProcessResult>) audit.getGrossResultList(), (List<Test>) audit.getTestList());
        audit.setNetResultList(netResultList);

        if (!audit.getNetResultList().isEmpty()) {
            for (ProcessResult netResult : audit.getNetResultList()) {
                processResultDataService.saveOrUpdate(netResult);
            }

            audit.setStatus(AuditStatus.ANALYSIS);
        } else {
            Logger.getLogger(AuditServiceImpl.class).warn("Audit has no net result");
            audit.setStatus(AuditStatus.ERROR);
        }

        audit = auditDataService.saveOrUpdate(audit);
        return audit;
    }

    public Audit analyse(Audit audit) {
        if (!audit.getStatus().equals(AuditStatus.ANALYSIS)) {
            Logger.getLogger(AuditServiceImpl.class).warn(
                    "Audit status is "
                    + audit.getStatus()
                    + " while "
                    + AuditStatus.ANALYSIS
                    + " was required");
            return audit;
        }

        audit.setMark(analyserService.analyse((List<ProcessResult>) audit.getNetResultList()));

        audit.setStatus(AuditStatus.COMPLETED);

        audit = auditDataService.saveOrUpdate(audit);
        return audit;
    }

    public void setAnalyserService(AnalyserService analyserService) {
        this.analyserService = analyserService;
    }

    public void setAuditDataService(AuditDataService auditDataService) {
        this.auditDataService = auditDataService;
    }

    public void setConsolidatorService(ConsolidatorService consolidatorService) {
        this.consolidatorService = consolidatorService;
    }

    public void setContentAdapterService(
            ContentAdapterService contentAdapterService) {
        this.contentAdapterService = contentAdapterService;
    }

    public void setContentLoaderService(
            ContentLoaderService contentLoaderService) {
        this.contentLoaderService = contentLoaderService;
    }

    public void setCrawlerService(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }

    public void setProcessorService(ProcessorService processorService) {
        this.processorService = processorService;
    }

    public void setProcessResultDataService(
            ProcessResultDataService processResultDataService) {
        this.processResultDataService = processResultDataService;
    }

    public void setTestDataService(TestDataService testDataService) {
        this.testDataService = testDataService;
    }

    public void setWebResourceDataService(
            WebResourceDataService webResourceDAOService) {
        this.webResourceDataService = webResourceDAOService;
    }
}
