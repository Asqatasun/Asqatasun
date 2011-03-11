package org.opens.tanaguru.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
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
import java.util.Set;
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

    private static Logger LOGGER = Logger.getLogger(AuditServiceImpl.class);
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
    private static final int ANALYSE_TREATMENT_WINDOW = 50;
    private static final int PROCESSING_TREATMENT_WINDOW = 25;
    private static final int ADAPTATION_TREATMENT_WINDOW = 200;

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
            LOGGER.warn("Audit is not well initialized");
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
            LOGGER.warn(
                    "Audit status is "
                    + audit.getStatus()
                    + " while "
                    + AuditStatus.CRAWLING
                    + " was required");
            return audit;
        }

        if (audit.getSubject() instanceof Site) {
            audit.setSubject(crawlerService.crawl((Site) audit.getSubject()));
        } else if (audit.getSubject() instanceof Page) {
            audit.setSubject(crawlerService.crawl((Page) audit.getSubject()));
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
            LOGGER.warn(
                    "Audit status is "
                    + audit.getStatus()
                    + " while "
                    + AuditStatus.CONTENT_ADAPTING
                    + " was required");
            return audit;
        }

        // debug tools
        Date beginProcessDate = null;
        Date endProcessDate = null;
        Date endPersistDate = null;
        Long persistenceDuration = Long.valueOf(0);
        //

        boolean hasCorrectedDOM = false;
        Long nbOfContent = contentDataService.findNumberOfSSPContentFromAudit(audit);
        Long i = Long.valueOf(0);
        while (i.compareTo(nbOfContent) < 0) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Adapting from " + i + " to " + (i + ADAPTATION_TREATMENT_WINDOW));
                beginProcessDate = Calendar.getInstance().getTime();
            }
            List<Content> contentList =
                    (List<Content>) contentDataService.findSSPContentWithRelatedContent(
                    audit,
                    i.intValue(),
                    ADAPTATION_TREATMENT_WINDOW);
            Set<Content> contentSet = new HashSet<Content>();
            contentSet.addAll(contentAdapterService.adaptContent(contentList));

            if (LOGGER.isDebugEnabled()) {
                endProcessDate = Calendar.getInstance().getTime();
                LOGGER.debug("Adaptation of "
                        + ADAPTATION_TREATMENT_WINDOW
                        + " elements took "
                        + (endProcessDate.getTime() - beginProcessDate.getTime())
                        + " ms");
            }
            contentDataService.saveOrUpdate(contentSet);

            if (LOGGER.isDebugEnabled()) {
                endPersistDate = Calendar.getInstance().getTime();

                LOGGER.debug("Persist adaptation of "
                        + ADAPTATION_TREATMENT_WINDOW
                        + " elements took "
                        + (endPersistDate.getTime() - endProcessDate.getTime())
                        + " ms");
                persistenceDuration = persistenceDuration
                        + (endPersistDate.getTime() - endProcessDate.getTime());
            }
            if (!hasCorrectedDOM) {
                for (Content content : contentList) {
                    if (content instanceof SSP && !((SSP) content).getDOM().isEmpty()) {
                        hasCorrectedDOM = true;
                        break;
                    }
                }
            }
            i = i + ADAPTATION_TREATMENT_WINDOW;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Application spent "
                    + persistenceDuration
                    + " ms to write in Disk while adapting");
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
            LOGGER.warn(
                    "Audit status is "
                    + audit.getStatus()
                    + " while "
                    + AuditStatus.PROCESSING
                    + " was required");
            return audit;
        }

        // debug tools
        Date beginProcessDate = null;
        Date endProcessDate = null;
        Date endPersistDate = null;
        Long persistenceDuration = Long.valueOf(0);
        //

        Long nbOfContent = contentDataService.findNumberOfSSPContentFromAudit(audit);
        Long i = Long.valueOf(0);
        List<Content> contentList;
        Set<ProcessResult> processResultSet = new HashSet<ProcessResult>();
        while (i.compareTo(nbOfContent) < 0) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Processing from " + i + " to " + (i + PROCESSING_TREATMENT_WINDOW));
                beginProcessDate = Calendar.getInstance().getTime();
            }

            contentList = (List<Content>) contentDataService.findSSPContentWithRelatedContent(
                    audit,
                    i.intValue(),
                    PROCESSING_TREATMENT_WINDOW);
            processResultSet.clear();
            processResultSet.addAll(processorService.process(contentList, (List<Test>) audit.getTestList()));
            for (ProcessResult processResult : processResultSet) {
                processResult.setGrossResultAudit(audit);
            }

            if (LOGGER.isDebugEnabled()) {
                endProcessDate = Calendar.getInstance().getTime();
                LOGGER.debug("Processing of "
                        + PROCESSING_TREATMENT_WINDOW
                        + " elements took "
                        + (endProcessDate.getTime() - beginProcessDate.getTime())
                        + " ms");
            }
            processResultDataService.saveOrUpdate(processResultSet);
            if (LOGGER.isDebugEnabled()) {
                endPersistDate = Calendar.getInstance().getTime();

                LOGGER.debug("Persist adaptation of "
                        + ADAPTATION_TREATMENT_WINDOW
                        + " elements took "
                        + (endPersistDate.getTime() - endProcessDate.getTime())
                        + " ms");
                persistenceDuration = persistenceDuration
                        + (endPersistDate.getTime() - endProcessDate.getTime());
            }
            i = i + PROCESSING_TREATMENT_WINDOW;
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Application spent "
                    + persistenceDuration
                    + " ms to write in Disk while processing");
        }

        if (processResultDataService.getNumberOfGrossResultFromAudit(audit) > 0) {
            audit.setStatus(AuditStatus.CONSOLIDATION);
        } else {
            LOGGER.error("Audit has no gross result");
            audit.setStatus(AuditStatus.ERROR);
        }

        auditDataService.saveOrUpdate(audit);
        return audit;
    }

    @Override
    public Audit consolidate(Audit audit) {
        audit = auditDataService.getAuditWithTest(audit.getId());
        if (!audit.getStatus().equals(AuditStatus.CONSOLIDATION)) {
            LOGGER.warn(
                    "Audit status is "
                    + audit.getStatus()
                    + " while "
                    + AuditStatus.CONSOLIDATION
                    + " was required");
            return audit;
        }

        // debug tools
        Date beginProcessDate = null;
        Date endProcessDate = null;
        Date endPersistDate = null;

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Consolidation");
            beginProcessDate = Calendar.getInstance().getTime();
        }
        Set<ProcessResult> processResultSet = new HashSet<ProcessResult>();
        processResultSet.addAll(consolidatorService.consolidate(
                (List<ProcessResult>) processResultDataService.getGrossResultFromAudit(audit),
                (List<Test>) audit.getTestList()));
        for (ProcessResult processResult : processResultSet) {
            processResult.setNetResultAudit(audit);
        }

        if (!processResultSet.isEmpty()) {
            audit.setStatus(AuditStatus.ANALYSIS);
        } else {
            LOGGER.warn("Audit has no net result");
            audit.setStatus(AuditStatus.ERROR);
        }

        if (LOGGER.isDebugEnabled()) {
            endProcessDate = Calendar.getInstance().getTime();
            LOGGER.debug("Consolidation of this audit took"
                    + (endProcessDate.getTime() - beginProcessDate.getTime())
                    + " ms");
        }
        processResultDataService.saveOrUpdate(processResultSet);
        audit = auditDataService.saveOrUpdate(audit);
        if (LOGGER.isDebugEnabled()) {
            endPersistDate = Calendar.getInstance().getTime();
            LOGGER.debug("Persist Consolidation of the audit took"
                    + (endPersistDate.getTime() - endProcessDate.getTime())
                    + " ms");
        }
        return audit;
    }

    @Override
    public Audit analyse(Audit audit) {
        audit = auditDataService.getAuditWithWebResource(audit.getId());
        if (!audit.getStatus().equals(AuditStatus.ANALYSIS)) {
            LOGGER.warn(
                    "Audit status is "
                    + audit.getStatus()
                    + " while "
                    + AuditStatus.ANALYSIS
                    + " was required");
            return audit;
        }
        // debug tools
        Date beginProcessDate = null;
        Date endProcessDate = null;
        Date endPersistDate = null;
        Long persistenceDuration = Long.valueOf(0);
        //

        WebResource parentWebResource = audit.getSubject();
        if (parentWebResource instanceof Page) {
            parentWebResource.setMark(
                    analyserService.analyse(
                    (List<ProcessResult>) processResultDataService.getNetResultFromAudit(audit)));
            webResourceDataService.saveOrUpdate(parentWebResource);
        } else if (parentWebResource instanceof Site) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Analysing results of scope site");
                beginProcessDate = Calendar.getInstance().getTime();
            }
            parentWebResource.setMark(
                    analyserService.analyse(
                    (List<ProcessResult>) processResultDataService.getNetResultFromAudit(audit)));
            if (LOGGER.isDebugEnabled()) {
                endProcessDate = Calendar.getInstance().getTime();
                LOGGER.debug("Analysing results of scope site took "
                        + (endProcessDate.getTime() - beginProcessDate.getTime())
                        + " ms");
            }
            webResourceDataService.saveOrUpdate(parentWebResource);

            if (LOGGER.isDebugEnabled()) {
                endPersistDate = Calendar.getInstance().getTime();
                LOGGER.debug("Persist Analysis results of scope site  "
                        + (endPersistDate.getTime() - endProcessDate.getTime())
                        + " ms");
                persistenceDuration = persistenceDuration
                        + (endPersistDate.getTime() - endProcessDate.getTime());
            }

            Long nbOfContent =
                    webResourceDataService.getNumberOfChildWebResource(parentWebResource);
            Long i = Long.valueOf(0);
            List<WebResource> webResourceList = null;
            List<ProcessResult> webResourceNetResultList = null;
            while (i.compareTo(nbOfContent) < 0) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Analysing results of scope page from "
                            + i + " to " + (i + ANALYSE_TREATMENT_WINDOW));
                    beginProcessDate = Calendar.getInstance().getTime();
                }
                webResourceList = webResourceDataService.getWebResourceFromItsParent(
                        parentWebResource,
                        i.intValue(),
                        ANALYSE_TREATMENT_WINDOW);
                for (WebResource webresource : webResourceList) {
                    webResourceNetResultList = (List<ProcessResult>) processResultDataService.getNetResultFromAuditAndWebResource(audit, webresource);
                    // In case of webresource with ssp result different from 200,
                    // there is no result to compute to determine the mark,
                    // In this case, the mark is kept as O.
                    if (!webResourceNetResultList.isEmpty()) {
                        webresource.setMark(analyserService.analyse(webResourceNetResultList));
                        if (LOGGER.isDebugEnabled()) {
                            endProcessDate = Calendar.getInstance().getTime();
                            LOGGER.debug("Analysing results of scope page for "
                                    + ANALYSE_TREATMENT_WINDOW
                                    + " elements took "
                                    + (endProcessDate.getTime() - beginProcessDate.getTime())
                                    + " ms");
                        }
                        webResourceDataService.saveOrUpdate(webresource);
                        if (LOGGER.isDebugEnabled()) {
                            endPersistDate = Calendar.getInstance().getTime();

                            LOGGER.debug("Persist Analysing results of scope page for "
                                    + ANALYSE_TREATMENT_WINDOW
                                    + " elements took "
                                    + (endPersistDate.getTime() - endProcessDate.getTime())
                                    + " ms");
                            persistenceDuration = persistenceDuration
                                    + (endPersistDate.getTime() - endProcessDate.getTime());
                        }
                    }
                }
                i = i + ANALYSE_TREATMENT_WINDOW;
            }
        }
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Application spent "
                    + persistenceDuration
                    + " ms to write in Disk while analysing");
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
