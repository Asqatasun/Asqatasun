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
//        if (audit.getSubject() instanceof Site) {
        audit = crawl(audit);
//        }
//        audit = loadContent(audit);
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
            auditDataService.saveOrUpdate(audit);
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

        boolean hasContent = false;

        for (Content content : audit.getContentList()) {
            if (content instanceof SSP) {
                // We check that some content has been downloaded and has to
                // be adapted. We only deal with contents with a 200 Http Status
                // code
                if (content.getHttpStatusCode() == 200 && 
                        !((SSP)content).getSource().isEmpty() ) {
                    hasContent = true;
                    break;
                }
                
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

    @Override
    public Audit loadContent(Audit audit) {
//        if (!audit.getStatus().equals(AuditStatus.CONTENT_LOADING)) {
//            Logger.getLogger(AuditServiceImpl.class).warn(
//                    "Audit status is "
//                    + audit.getStatus()
//                    + " while "
//                    + AuditStatus.CONTENT_LOADING
//                    + " was required");
//            return audit;
//        }
//
//        audit.addAllContent(contentLoaderService.loadContent(audit.getSubject()));
//
//        boolean hasContent = false;
//        for (Content content : audit.getContentList()) {
//            if (content instanceof SSP) {
//                //We check that some content has been downloaded and has to
//                //be adapted. For the moment we ignore the returned error code @TODO
//                if (!((SSP)content).getSource().isEmpty()) {
//                    hasContent = true;
//                    break;
//                }
//            }
//        }
//        if (hasContent) {
//            audit.setStatus(AuditStatus.CONTENT_ADAPTING);
//        } else {
//            Logger.getLogger(AuditServiceImpl.class).warn("Audit has no content");
//            audit.setStatus(AuditStatus.ERROR);
//        }
//
//        audit = auditDataService.saveOrUpdate(audit);
        return audit;
    }

    @Override
    public Audit adaptContent(Audit audit) {
        audit.getContentList().clear();
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
        Long i=new Long(0);
        while (i.compareTo(nbOfContent)<0) {
            List<? extends Content> contentList =
                    contentAdapterService.adaptContent((List<Content>)contentDataService.findSSPContentWithRelatedContent(audit, i.intValue(), 1));
            for (Content content : contentList) {
                if (!hasCorrectedDOM && content instanceof SSP && !((SSP) content).getDOM().isEmpty()) {
                    hasCorrectedDOM = true;
                }
                contentDataService.saveOrUpdate(content);
            }
            i++;
        }
//        audit.setContentList(contentAdapterService.adaptContent((List<Content>) audit.getContentList()));

//        boolean hasCorrectedDOM = false;
//        for (Content content : audit.getContentList()) {
//            if (content instanceof SSP) {
//                if (!((SSP) content).getDOM().isEmpty()) {
//                    hasCorrectedDOM = true;
//                    break;
//                }
//            }
//        }
        if (hasCorrectedDOM) {
            audit.setStatus(AuditStatus.PROCESSING);
        } else {
            Logger.getLogger(AuditServiceImpl.class).warn("Audit has no corrected DOM");
            audit.setStatus(AuditStatus.ERROR);
        }
        audit.getContentList().clear();
        audit = auditDataService.saveOrUpdate(audit);
        return audit;
    }

    @Override
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

        Long nbOfContent = contentDataService.findNumberOfSSPContentFromAudit(audit);
        Long i=new Long(0);
        audit.getGrossResultList().clear();
        while (i.compareTo(nbOfContent)<0) {
            List<Content> contentList =
                    contentAdapterService.adaptContent((List<Content>)contentDataService.findSSPContentWithRelatedContent(audit, i.intValue(), 1));
            audit.setGrossResultList(processorService.process(contentList, (List<Test>) audit.getTestList()));
            auditDataService.saveOrUpdate(audit);
            i++;
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
        if (!audit.getStatus().equals(AuditStatus.CONSOLIDATION)) {
            Logger.getLogger(AuditServiceImpl.class).warn(
                    "Audit status is "
                    + audit.getStatus()
                    + " while "
                    + AuditStatus.CONSOLIDATION
                    + " was required");
            return audit;
        }

        audit.getNetResultList().clear();
        List<ProcessResult> netResultList = consolidatorService.consolidate(
                (List<ProcessResult>) processResultDataService.getGrossResultFromAudit(audit), (List<Test>) audit.getTestList());
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
        audit.getNetResultList().clear();
        audit = auditDataService.saveOrUpdate(audit);
        return audit;
    }

    @Override
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
        if (audit.getSubject() instanceof Page) {
            audit.getSubject().setMark(analyserService.analyse((List<ProcessResult>) processResultDataService.getNetResultFromAudit(audit)));
        } else if (audit.getSubject() instanceof Site) {
            audit.getSubject().setMark(analyserService.analyse((List<ProcessResult>) processResultDataService.getNetResultFromAudit(audit)));
            for (WebResource webresource : ((Site)audit.getSubject()).getComponentList()) {
                List<ProcessResult> webResourceNetResultList = (List<ProcessResult>)processResultDataService.getNetResultFromAuditAndWebResource(audit, webresource);
//                for (ProcessResult processResult : audit.getNetResultList()) {
//                    if (processResult instanceof DefiniteResult && processResult.getSubject().equals(webresource)) {
//                        webResourceNetResultList.add(processResult);
//                    }
//                }
                webresource.setMark(analyserService.analyse(webResourceNetResultList));
                webResourceDataService.saveOrUpdate(webresource);
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
