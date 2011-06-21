/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.opens.tanaguru.contentadapter.AdaptationListener;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.audit.ProcessResultDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.util.MD5Encoder;

/**
 *
 * @author enzolalay
 */
public class AuditServiceThreadImpl implements AuditServiceThread {

    private static Logger LOGGER = Logger.getLogger(AuditServiceThreadImpl.class);
    private static final int ANALYSE_TREATMENT_WINDOW = 50;
    private static final int PROCESSING_TREATMENT_WINDOW = 25;
    private static final int ADAPTATION_TREATMENT_WINDOW = 25;
    private static final int CONSOLIDATION_TREATMENT_WINDOW = 50;
    protected Audit audit;
    private final AuditDataService auditDataService;

    public AuditDataService getAuditDataService() {
        return auditDataService;
    }
    private final ContentDataService contentDataService;
    private final ProcessResultDataService processResultDataService;
    private final WebResourceDataService webResourceDataService;

    public WebResourceDataService getWebResourceDataService() {
        return webResourceDataService;
    }
    private final CrawlerService crawlerService;
    private final ContentAdapterService contentAdapterService;
    private final AdaptationListener adaptationListener;
    private final ProcessorService processorService;
    private final ConsolidatorService consolidatorService;
    private final AnalyserService analyserService;

    public AnalyserService getAnalyserService() {
        return analyserService;
    }
    private Set<AuditServiceThreadListener> listeners;

    /**
     * 
     * @param auditDataService
     * @param contentDataService
     * @param processResultDataService
     * @param webResourceDataService
     * @param crawlerService
     * @param contentAdapterService
     * @param processorService
     * @param consolidatorService
     * @param analyserService
     * @param audit
     * @param adaptationListener
     */
    public AuditServiceThreadImpl(
            AuditDataService auditDataService,
            ContentDataService contentDataService,
            ProcessResultDataService processResultDataService,
            WebResourceDataService webResourceDataService,
            CrawlerService crawlerService,
            ContentAdapterService contentAdapterService,
            ProcessorService processorService,
            ConsolidatorService consolidatorService,
            AnalyserService analyserService,
            Audit audit,
            AdaptationListener adaptationListener) {
        super();
        this.auditDataService = auditDataService;
        this.contentDataService = contentDataService;
        this.processResultDataService = processResultDataService;
        this.webResourceDataService = webResourceDataService;
        this.crawlerService = crawlerService;
        this.contentAdapterService = contentAdapterService;
        this.processorService = processorService;
        this.consolidatorService = consolidatorService;
        this.analyserService = analyserService;
        this.audit = audit;
        this.adaptationListener = adaptationListener;
    }

    @Override
    public Audit getAudit() {
        return audit;
    }

    @Override
    public void add(AuditServiceThreadListener listener) {
        if (listeners == null) {
            listeners = new HashSet<AuditServiceThreadListener>();
        }
        listeners.add(listener);
    }

    @Override
    public void remove(AuditServiceThreadListener listener) {
        if (listeners == null) {
            return;
        }
        listeners.remove(listener);
    }

    @Override
    public void run() {
        try {
            init();
            crawl();
            loadContent();
            adaptContent();
            process();
            consolidate();
            analyse();
            fireAuditCompleted();
        } catch (Throwable t) {
            fireAuditException(t);
        }
    }

    @Override
    public void init() {
        if (audit.getSubject() == null || audit.getTestList().isEmpty()) {
            LOGGER.warn("Audit is not well initialized");
            return;
        }
        if (audit.getStatus().equals(AuditStatus.INITIALISATION)) {
            audit.setStatus(AuditStatus.CRAWLING);
            audit = auditDataService.saveOrUpdate(audit);
        }
    }

    @Override
    public void crawl() {
        if (!audit.getStatus().equals(AuditStatus.CRAWLING)) {
            LOGGER.warn(
                    "Audit status is "
                    + audit.getStatus()
                    + " while "
                    + AuditStatus.CRAWLING
                    + " was required");
            return;
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
    }

    @Override
    public void loadContent() {
    }

    public void adaptContent() {
        audit = auditDataService.getAuditWithWebResource(audit.getId());
        if (!audit.getStatus().equals(AuditStatus.CONTENT_ADAPTING)) {
            LOGGER.warn(
                    "Audit status is "
                    + audit.getStatus()
                    + " while "
                    + AuditStatus.CONTENT_ADAPTING
                    + " was required");
            return;
        }

        // debug tools
        Date beginProcessDate = null;
        Date endRetrieveDate = null;
        Date endProcessDate = null;
        Date endPersistDate = null;
        Long persistenceDuration = Long.valueOf(0);
        //

        boolean hasCorrectedDOM = false;
        Long nbOfContent = contentDataService.findNumberOfSSPContentFromAudit(audit);
        Long i = Long.valueOf(0);
        Long webResourceId = audit.getSubject().getId();
        List<Long> contentIdList = new ArrayList<Long>();
        List<Content> contentList = new ArrayList<Content>();
        // Some actions have to be realized when the adaptation starts
        if (adaptationListener != null) {
            adaptationListener.adaptationStarted(audit);
        }
        while (i.compareTo(nbOfContent) < 0) {
            if (LOGGER.isDebugEnabled()) {
                beginProcessDate = Calendar.getInstance().getTime();
                LOGGER.debug("Adapt ssp from  "
                        + i + " to " + (i + ADAPTATION_TREATMENT_WINDOW));
            }
            contentIdList.clear();
            contentIdList =
                    contentDataService.getSSPFromWebResource(
                    webResourceId,
                    i.intValue(),
                    ADAPTATION_TREATMENT_WINDOW);
            contentList.clear();
            for (Long id : contentIdList) {
                Content content = contentDataService.read(id);
                if (content != null) {
                    contentList.add(content);
                }
            }
            if (LOGGER.isDebugEnabled()) {
                long length = 0;
                for (Content content : contentList) {
                    if (((SSP) content).getSource() != null) {
                        length += ((SSP) content).getSource().length();
                    }
                }
                endRetrieveDate = Calendar.getInstance().getTime();
                LOGGER.debug("Retrieving  " + ADAPTATION_TREATMENT_WINDOW + " SSP took "
                        + (endRetrieveDate.getTime() - beginProcessDate.getTime())
                        + " ms and working on " + length + " characters");
            }
            Set<Content> contentSet = new HashSet<Content>();
            contentSet.addAll(contentAdapterService.adaptContent(contentList));
            if (LOGGER.isDebugEnabled()) {
                endProcessDate = Calendar.getInstance().getTime();
                LOGGER.debug("Adapting  " + ADAPTATION_TREATMENT_WINDOW + " SSP took "
                        + (endProcessDate.getTime() - endRetrieveDate.getTime())
                        + " ms " + contentSet.size());
            }

            for (Content content : contentSet) {
//                contentDataService.saveOrUpdate(content);
                if (content instanceof SSP) {
                    if (!((SSP) content).getDOM().isEmpty()) {
                        if (!hasCorrectedDOM) {
                            hasCorrectedDOM = true;
                        }
                        try {
                            ((SSP) content).setSource(MD5Encoder.MD5(((SSP) content).getSource()));
                        } catch (NoSuchAlgorithmException ex) {
                            LOGGER.warn(ex);
                        } catch (UnsupportedEncodingException ex) {
                            LOGGER.warn(ex);
                        }
                    }
                }
                contentDataService.saveOrUpdate(content);
            }
            contentSet.clear();
            contentList.clear();
            if (LOGGER.isDebugEnabled()) {
                endPersistDate = Calendar.getInstance().getTime();
                LOGGER.debug("Persisting  " + ADAPTATION_TREATMENT_WINDOW + " SSP took "
                        + (endPersistDate.getTime() - endProcessDate.getTime())
                        + " ms");
                persistenceDuration = persistenceDuration
                        + (endPersistDate.getTime() - endProcessDate.getTime());
            }
            i = i + ADAPTATION_TREATMENT_WINDOW;
            // explicit call of the Gc
            System.gc();
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
        // Some actions have to be realized when the adaptation is completed
        if (adaptationListener != null) {
            adaptationListener.adaptationCompleted(audit);
        }
        audit = auditDataService.saveOrUpdate(audit);
    }

    @Override
    public void process() {
        audit = auditDataService.getAuditWithTest(audit.getId());
        if (!audit.getStatus().equals(AuditStatus.PROCESSING)) {
            LOGGER.warn(
                    "Audit status is "
                    + audit.getStatus()
                    + " while "
                    + AuditStatus.PROCESSING
                    + " was required");
            return;
        }

        // debug tools
        Date beginProcessDate = null;
        Date endProcessDate = null;
        Date endPersistDate = null;
        Date endRetrieveDate = null;
        Long persistenceDuration = Long.valueOf(0);
        //

        Long nbOfContent = contentDataService.findNumberOfSSPContentFromAudit(audit);
        Long i = Long.valueOf(0);
        Long webResourceId = audit.getSubject().getId();
        List<Long> contentIdList = new ArrayList<Long>();
        List<Content> contentList = new ArrayList<Content>();
        Set<ProcessResult> processResultSet = new HashSet<ProcessResult>();
        while (i.compareTo(nbOfContent) < 0) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Processing from " + i + " to " + (i + PROCESSING_TREATMENT_WINDOW));
                beginProcessDate = Calendar.getInstance().getTime();
            }
            contentIdList.clear();
            contentIdList =
                    contentDataService.getSSPFromWebResource(
                    webResourceId,
                    i.intValue(),
                    PROCESSING_TREATMENT_WINDOW);
            contentList.clear();
            for (Long id : contentIdList) {
                Content content = contentDataService.readWithRelatedContent(id);
                if (content != null) {
                    contentList.add(content);
                }
            }
            if (LOGGER.isDebugEnabled()) {
                long length = 0;
                int nbOfResources = 0;
                for (Content content : contentList) {
                    if (((SSP) content).getSource() != null) {
                        length += ((SSP) content).getAdaptedContent().length();
                        nbOfResources += ((SSP) content).getRelatedContentSet().size();
                    }
                }
                endRetrieveDate = Calendar.getInstance().getTime();
                LOGGER.debug("Retrieving  " + PROCESSING_TREATMENT_WINDOW + " SSP took "
                        + (endRetrieveDate.getTime() - beginProcessDate.getTime())
                        + " ms and working on " + contentList.size()
                        + " SSP, (" + length + " characters)"
                        + nbOfResources + " relatedContent ");
            }
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

                LOGGER.debug("Persist processing of "
                        + PROCESSING_TREATMENT_WINDOW
                        + " elements took "
                        + (endPersistDate.getTime() - endProcessDate.getTime())
                        + " ms");
                persistenceDuration = persistenceDuration
                        + (endPersistDate.getTime() - endProcessDate.getTime());
            }
            i = i + PROCESSING_TREATMENT_WINDOW;
            System.gc();
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
    }

    @Override
    public void consolidate() {
        audit = auditDataService.getAuditWithTest(audit.getId());
        if (!audit.getStatus().equals(AuditStatus.CONSOLIDATION)) {
            LOGGER.warn(
                    "Audit status is "
                    + audit.getStatus()
                    + " while "
                    + AuditStatus.CONSOLIDATION
                    + " was required");
            return;
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
        // To avoid errors with processResult of Site Type in case of page audit
        Set<ProcessResult> resultToRemoveSet = new HashSet<ProcessResult>();
        for (ProcessResult processResult : processResultSet) {
            if (processResult.getTest().getScope().getCode().equalsIgnoreCase("site")
                    && processResult.getSubject() instanceof Page) {
                resultToRemoveSet.add(processResult);
            } else {
                processResult.setNetResultAudit(audit);
            }
        }
        for (ProcessResult resultToRemove : resultToRemoveSet) {
            processResultSet.remove(resultToRemove);
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
        Iterator<ProcessResult> iter = processResultSet.iterator();
        Set<ProcessResult> processResultSubset = new HashSet<ProcessResult>();
        int i = 0;
        while (iter.hasNext()) {
            processResultSubset.add(iter.next());
            i++;
            if (i % CONSOLIDATION_TREATMENT_WINDOW == 0) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Persisting Consolidation from " + i + " to "
                            + CONSOLIDATION_TREATMENT_WINDOW);
                }
                processResultDataService.saveOrUpdate(processResultSubset);
                processResultSubset.clear();
            }
        }
        processResultDataService.saveOrUpdate(processResultSubset);
        audit = auditDataService.saveOrUpdate(audit);
        if (LOGGER.isDebugEnabled()) {
            endPersistDate = Calendar.getInstance().getTime();
            LOGGER.debug("Persisting Consolidation of the audit took"
                    + (endPersistDate.getTime() - endProcessDate.getTime())
                    + " ms");
        }
    }

    @Override
    public void analyse() {
        audit = auditDataService.getAuditWithWebResource(audit.getId());
        if (!audit.getStatus().equals(AuditStatus.ANALYSIS)) {
            LOGGER.warn(
                    "Audit status is "
                    + audit.getStatus()
                    + " while "
                    + AuditStatus.ANALYSIS
                    + " was required");
            return;
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
                LOGGER.debug("Persisting Analysis results of scope site  "
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
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Analysing " + nbOfContent + " elements ");
            }
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
                for (WebResource webResource : webResourceList) {
                    webResourceNetResultList = (List<ProcessResult>) processResultDataService.getNetResultFromAuditAndWebResource(audit, webResource);
                    // In case of webresource with ssp result different from 200,
                    // there is no result to compute to determine the mark,
                    // In this case, the mark is kept as O.
                    if (!webResourceNetResultList.isEmpty()) {
                        webResource.setMark(analyserService.analyse(webResourceNetResultList));
                        if (LOGGER.isDebugEnabled()) {
                            endProcessDate = Calendar.getInstance().getTime();
                            LOGGER.debug("Analysing results for page "
                                    + webResource.getURL()
                                    + " took "
                                    + (endProcessDate.getTime() - beginProcessDate.getTime())
                                    + " ms");
                        }
                        webResourceDataService.saveOrUpdate(webResource);
                        if (LOGGER.isDebugEnabled()) {
                            endPersistDate = Calendar.getInstance().getTime();

                            LOGGER.debug("Persisting Analysis results for page "
                                    + webResource.getURL()
                                    + " took "
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
    }

    private void fireAuditCompleted() {
        if (listeners == null) {
            return;
        }
        for (AuditServiceThreadListener listener : listeners) {
            listener.auditCompleted(this);
        }
    }

    private void fireAuditException(Throwable t) {
        if (listeners == null) {
            return;
        }
        for (AuditServiceThreadListener listener : listeners) {
            listener.auditCrashed(this, t);
        }
    }
}
