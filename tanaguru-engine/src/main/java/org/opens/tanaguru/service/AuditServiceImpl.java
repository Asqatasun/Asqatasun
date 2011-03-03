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
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.audit.ProcessResultDataService;
import org.opens.tanaguru.entity.service.reference.TestDataService;
import org.opens.tanaguru.entity.subject.WebResource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author ADEX
 */
public class AuditServiceImpl implements AuditService {

    private AnalyserService analyserService;
    private AuditDataService auditDataService;
    private ConsolidatorService consolidatorService;
    private ContentAdapterService contentAdapterService;
    private CrawlerService crawlerService;
    private ProcessorService processorService;
    private ProcessResultDataService processResultDataService;
    private TestDataService testDataService;
    private WebResourceDataService webResourceDataService;
    private ContentDataService contentDataService;
    private static final int TREATMENT_WINDOW = 100;

    @Autowired
    public AuditServiceImpl(ContentDataService contentDataService) {
        super();
        this.contentDataService = contentDataService;
    }

    @Override
    public Audit auditPage(String pageUrl, String[] testCodeList) {
        Page page = webResourceDataService.createPage(pageUrl);

        List<Test> testList = testDataService.findAllByCode(testCodeList);

        Audit audit = auditDataService.create();
        audit.setSubject(page);
        audit.setTestList(testList);
        audit.setStatus(AuditStatus.CRAWLING);

        audit = audit(audit);
        return audit;
    }

    @Override
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

    @Override
    public Audit auditSite(String siteUrl, String[] pageUrlList, String[] testCodeList) {
        Site site = webResourceDataService.createSite(siteUrl);
        for (String pageUrl : pageUrlList) {
            site.addChild(webResourceDataService.createPage(pageUrl));
        }

        List<Test> testList = testDataService.findAllByCode(testCodeList);

        Audit audit = auditDataService.create();
        audit.setSubject(site);
        audit.setTestList(testList);
        audit.setStatus(AuditStatus.CRAWLING);

        audit = audit(audit);
        return audit;
    }

    @Override
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

    @Override
    public Audit init(Audit audit) {
        if (audit.getSubject() == null || audit.getTestList().isEmpty()) {
            Logger.getLogger(AuditServiceImpl.class).warn("Audit is not well initialized");
            return audit;
        }
        if (audit.getStatus().equals(AuditStatus.INITIALISATION)) {
            audit.setStatus(AuditStatus.CRAWLING);
            audit = auditDataService.saveOrUpdate(audit);
        }
        return audit;
    }

    @Override
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
            audit.setSubject(crawlerService.crawl((Site)audit.getSubject()));
        } else if (audit.getSubject() instanceof Page) {
            audit.setSubject(crawlerService.crawl((Page)audit.getSubject()));
        }

        if (contentDataService.hasContent(audit)) {
            audit.setStatus(AuditStatus.CONTENT_ADAPTING);
        } else {
            Logger.getLogger(AuditServiceImpl.class).warn("Audit has no content");
            audit.setStatus(AuditStatus.ERROR);
        }
        audit = auditDataService.saveOrUpdate(audit);
        return audit;
    }

    @Override
    public Audit loadContent(Audit audit) {
        return audit;
    }

    @Override
    public Audit adaptContent(Audit audit) {
        audit = auditDataService.read(audit.getId());
        if (!audit.getStatus().equals(AuditStatus.CONTENT_ADAPTING)) {
            Logger.getLogger(AuditServiceImpl.class).warn(
                    "Audit status is "
                    + audit.getStatus()
                    + " while "
                    + AuditStatus.CONTENT_ADAPTING
                    + " was required");
            return audit;
        }
        boolean hasCorrectedDOM = false;
        Long nbOfContent = contentDataService.findNumberOfSSPContentFromAudit(audit);
        Long i = Long.valueOf(0);
        while (i.compareTo(nbOfContent)<0) {
            List<? extends Content> contentList =
                    contentAdapterService.adaptContent((List<Content>)contentDataService.findSSPContentWithRelatedContent(audit, i.intValue(), TREATMENT_WINDOW));
            for (Content content : contentList) {
                if (!hasCorrectedDOM && content instanceof SSP && !((SSP) content).getDOM().isEmpty()) {
                    hasCorrectedDOM = true;
                }
                contentDataService.saveOrUpdate(content);
            }
            i = i +TREATMENT_WINDOW;
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

    @Override
    public Audit process(Audit audit) {
        audit = auditDataService.getAuditWithTest(audit.getId());
        if (!audit.getStatus().equals(AuditStatus.PROCESSING)) {
            Logger.getLogger(AuditServiceImpl.class).warn(
                    "Audit status is "
                    + audit.getStatus()
                    + " while "
                    + AuditStatus.PROCESSING
                    + " was required");
            return audit;
        }

        Long nbOfContent = contentDataService.findNumberOfSSPContentFromAudit(audit);
        Long i=Long.valueOf(0);
        while (i.compareTo(nbOfContent)<0) {
            List<Content> contentList =
                    (List<Content>)contentDataService.findSSPContentWithRelatedContent(audit, i.intValue(), TREATMENT_WINDOW);
            audit.setGrossResultList(processorService.process(contentList, (List<Test>) audit.getTestList()));
            auditDataService.saveOrUpdate(audit);
            i = i + TREATMENT_WINDOW;
            audit.getGrossResultList().clear();
        }
        if (processResultDataService.getNumberOfGrossResultFromAudit(audit)>0) {
            audit.setStatus(AuditStatus.CONSOLIDATION);
        } else {
            Logger.getLogger(AuditServiceImpl.class).warn("Audit has no gross result");
            audit.setStatus(AuditStatus.ERROR);
        }
        auditDataService.saveOrUpdate(audit);
        return audit;
    }

    @Override
    public Audit consolidate(Audit audit) {
        audit = auditDataService.getAuditWithTest(audit.getId());
        if (!audit.getStatus().equals(AuditStatus.CONSOLIDATION)) {
            Logger.getLogger(AuditServiceImpl.class).warn(
                    "Audit status is "
                    + audit.getStatus()
                    + " while "
                    + AuditStatus.CONSOLIDATION
                    + " was required");
            return audit;
        }
        List<ProcessResult> netResultList = consolidatorService.consolidate(
                (List<ProcessResult>) processResultDataService.getGrossResultFromAudit(audit), (List<Test>) audit.getTestList());
        audit.setNetResultList(netResultList);
        if (!audit.getNetResultList().isEmpty()) {
            audit.setStatus(AuditStatus.ANALYSIS);
        } else {
            Logger.getLogger(AuditServiceImpl.class).warn("Audit has no net result");
            audit.setStatus(AuditStatus.ERROR);
        }
        audit = auditDataService.saveOrUpdate(audit);
        return audit;
    }

    @Override
    public Audit analyse(Audit audit) {
        audit = auditDataService.getAuditWithWebResource(audit.getId());
        if (!audit.getStatus().equals(AuditStatus.ANALYSIS)) {
            Logger.getLogger(AuditServiceImpl.class).warn(
                    "Audit status is "
                    + audit.getStatus()
                    + " while "
                    + AuditStatus.ANALYSIS
                    + " was required");
            return audit;
        }
        WebResource parentWebResource = audit.getSubject();
        if (parentWebResource instanceof Page) {
            parentWebResource.setMark(
                    analyserService.analyse(
                    (List<ProcessResult>) processResultDataService.getNetResultFromAudit(audit)));
             webResourceDataService.saveOrUpdate(parentWebResource);
        } else if (parentWebResource instanceof Site) {
            parentWebResource.setMark(
                    analyserService.analyse(
                    (List<ProcessResult>) processResultDataService.getNetResultFromAudit(audit)));
            Long nbOfContent =
                    webResourceDataService.getNumberOfChildWebResource(parentWebResource);
            Long i = Long.valueOf(0);
            List<WebResource> webResourceList = null;
            List<ProcessResult> webResourceNetResultList = null;
            webResourceDataService.saveOrUpdate(parentWebResource);
            while (i.compareTo(nbOfContent)<0) {
                webResourceList = webResourceDataService.
                        getWebResourceFromItsParent(
                            parentWebResource,
                            i.intValue(),
                            TREATMENT_WINDOW);
                for (WebResource webresource : webResourceList) {
                    webResourceNetResultList = (List<ProcessResult>)processResultDataService.
                            getNetResultFromAuditAndWebResource(audit, webresource);
                    // In case of webresource with ssp result different from 200,
                    // there is no result to compute to determine the mark,
                    // In this case, the mark is kept as O.
                    if (!webResourceNetResultList.isEmpty()) {
                        webresource.setMark(analyserService.analyse(webResourceNetResultList));
                        webResourceDataService.saveOrUpdate(webresource);
                    }
                }
                i = i +TREATMENT_WINDOW;
            }
        }
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
