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

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import javax.persistence.PersistenceException;
import org.apache.http.HttpStatus;
import org.asqatasun.analyser.AnalyserService;
import org.asqatasun.consolidator.ConsolidatorService;
import org.asqatasun.contentadapter.AdaptationListener;
import org.asqatasun.entity.audit.*;
import org.asqatasun.entity.contract.ScopeEnum;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.reference.Test;
import org.asqatasun.entity.service.audit.*;
import org.asqatasun.entity.service.parameterization.ParameterDataService;
import org.asqatasun.entity.service.reference.TestDataService;
import org.asqatasun.entity.service.subject.WebResourceDataService;
import org.asqatasun.entity.subject.Page;
import org.asqatasun.entity.subject.Site;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.service.*;
import org.asqatasun.util.MD5Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.asqatasun.entity.contract.ScopeEnum.PAGE;

/**
 *
 * @author jkowalczyk
 */
public abstract class AuditCommandImpl implements AuditCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditCommandImpl.class);
    
    public static final String AUDIT_STATUS_IS_LOGGER_STR = "Audit status is";
    public static final String WHILE_LOGGER_STR = " while";
    public static final String WAS_REQUIRED_LOGGER_STR = " was required";
    public static final String TO_LOGGER_STR = " to ";
    public static final String MS_LOGGER_STR = " ms ";
    public static final String SSP_TOOK_LOGGER_STR = " SSP took ";
    public static final String CONSOLIDATING_TOOK_LOGGER_STR = "Consolidating took ";
    
    public static final int DEFAULT_ANALYSE_TREATMENT_WINDOW = 10;
    public static final int DEFAULT_PROCESSING_TREATMENT_WINDOW = 4;
    public static final int DEFAULT_ADAPTATION_TREATMENT_WINDOW = 4;
    public static final int DEFAULT_CONSOLIDATION_TREATMENT_WINDOW = 200;

    protected int adaptationTreatmentWindow = DEFAULT_ADAPTATION_TREATMENT_WINDOW;
    public void setAdaptationTreatmentWindow(int adaptationTreatmentWindow) {
        this.adaptationTreatmentWindow = adaptationTreatmentWindow;
    }
    protected int analyseTreatmentWindow = DEFAULT_ANALYSE_TREATMENT_WINDOW;
    public void setAnalyseTreatmentWindow(int analyseTreatmentWindow) {
        this.analyseTreatmentWindow = analyseTreatmentWindow;
    }
    protected int consolidationTreatmentWindow = DEFAULT_CONSOLIDATION_TREATMENT_WINDOW;
    public void setConsolidationTreatmentWindow(int consolidationTreatmentWindow) {
        this.consolidationTreatmentWindow = consolidationTreatmentWindow;
    }
    protected int processingTreatmentWindow = DEFAULT_PROCESSING_TREATMENT_WINDOW;
    public void setProcessingTreatmentWindow(int processingTreatmentWindow) {
        this.processingTreatmentWindow = processingTreatmentWindow;
    }
    
    private Audit audit;
    @Override
    public Audit getAudit() {return audit;}
    @Override
    public void setAudit(Audit audit) {
        this.audit = audit;
    }
    
    // The dataServices
    protected AuditDataService auditDataService;
    public void setAuditDataService(AuditDataService auditDataService) {this.auditDataService = auditDataService;}
    protected TestDataService testDataService;
    public void setTestDataService(TestDataService testDataService) {this.testDataService = testDataService;}
    protected ParameterDataService parameterDataService;
    public void setParameterDataService(ParameterDataService parameterDataService) {this.parameterDataService = parameterDataService;}
    protected WebResourceDataService webResourceDataService;
    public void setWebResourceDataService(WebResourceDataService webResourceDataService) {this.webResourceDataService = webResourceDataService;}
    protected ContentDataService contentDataService;
    public void setContentDataService(ContentDataService contentDataService) {this.contentDataService = contentDataService;}
    protected ProcessResultDataService processResultDataService;
    public void setProcessResultDataService(ProcessResultDataService processResultDataService) {this.processResultDataService = processResultDataService;}
    protected TagDataService tagDataService;
    public void setTagDataService(TagDataService tagDataService) {this.tagDataService = tagDataService;}
    protected PreProcessResultDataService preProcessResultDataService;
    public void setPreProcessResultDataService(PreProcessResultDataService preProcessResultDataService) {this.preProcessResultDataService = preProcessResultDataService;}

    // The services
    protected ScenarioLoaderService scenarioLoaderService;
    public void setScenarioLoaderService(ScenarioLoaderService scenarioLoaderService) {this.scenarioLoaderService = scenarioLoaderService;}
    protected ContentAdapterService contentAdapterService;
    public void setContentAdapterService(ContentAdapterService contentAdapterService) {this.contentAdapterService = contentAdapterService;}
    protected ProcessorService processorService;
    public void setProcessorService(ProcessorService processorService) {this.processorService = processorService;}
    protected ConsolidatorService consolidatorService;
    public void setConsolidatorService(ConsolidatorService consolidatorService) {this.consolidatorService = consolidatorService;}
    protected AnalyserService analyserService;
    public void setAnalyserService(AnalyserService analyserService) {this.analyserService = analyserService;}

    // The listeners
    protected AdaptationListener adaptationListener;
    public void setAdaptationListener(AdaptationListener adaptationListener) {this.adaptationListener = adaptationListener;}

    // the options
    private boolean cleanUpRelatedContent = true;
    public void setCleanUpRelatedContent(boolean cleanUpRelatedContent) {
        this.cleanUpRelatedContent = cleanUpRelatedContent;
    }
    /**
     * The audit parameters
     */
    private final Set<Parameter> paramSet;
    /**
     * The audit tags
     */
    private List<Tag> tagList;
    protected ScopeEnum scope;
    protected String targetUrl;

    /**
     * @param paramSet
     * @param tagList
     * @param auditDataService
     */
    public AuditCommandImpl(
            Set<Parameter> paramSet,
            List<Tag> tagList,
            AuditDataService auditDataService,
            ScopeEnum scope) {
        this.paramSet = paramSet;
        this.tagList = tagList;
        this.auditDataService = auditDataService;
        this.scope = scope;
        audit = auditDataService.create();
        setStatusToAudit(AuditStatus.PENDING);
    }
    
    @Override
    public void init() {
        // the paramSet has to be persisted
        parameterDataService.saveOrUpdate(paramSet);
        if (!tagList.isEmpty()) {
            tagList = new ArrayList(tagDataService.saveOrUpdate(tagList));
            audit.setTagList(tagList);
        }
        audit.setTestList(testDataService.getTestListFromParamSet(paramSet));
        audit.setParameterSet(paramSet);
        setStatusToAudit(AuditStatus.SCENARIO_LOADING);
    }

    @Override
    public void crawl() {
        // By default, do nothing
    }

    @Override
    public void loadContent() {
        LOGGER.info("Loading content for " + this.targetUrl);
        if (!getAudit().getStatus().equals(AuditStatus.SCENARIO_LOADING)) {
            LOGGER.warn("Audit Status is " + getAudit().getStatus() + " while " + AuditStatus.SCENARIO_LOADING +
                WAS_REQUIRED_LOGGER_STR);
            setStatusToAudit(AuditStatus.ERROR);
            return;
        }
        // the returned content list is already persisted and associated with
        // the current audit
        WebResource webResource;
        if (audit.getSubject() != null) {
            webResource = audit.getSubject();
        } else {
            webResource = createWebResource();
        }

        callScenarioLoadService(webResource);
        setStatusToAudit(AuditStatus.CONTENT_ADAPTING);

        LOGGER.info(this.targetUrl +" has been loaded");
    }

    protected void callScenarioLoadService(WebResource webResource) {
        scenarioLoaderService.loadScenario(webResource);
    }

    @Override
    public void adaptContent() {
        audit = auditDataService.read(audit.getId());
        if (!audit.getStatus().equals(AuditStatus.CONTENT_ADAPTING)) {
            LOGGER.warn( AUDIT_STATUS_IS_LOGGER_STR + audit.getStatus() + WHILE_LOGGER_STR +
                AuditStatus.CONTENT_ADAPTING + WAS_REQUIRED_LOGGER_STR);
            return;
        }
        
        LOGGER.info("Adapting " + audit.getSubject().getURL());
        
        // debug tools
        Date endRetrieveDate;
        Date endProcessDate;
        Date endPersistDate;
        Long persistenceDuration = (long) 0;

        boolean hasCorrectDOM = false;
        Long i = (long) 0;
        Long webResourceId = audit.getSubject().getId();
        Long nbOfContent = contentDataService.getNumberOfSSPFromWebResource(audit.getSubject(), HttpStatus.SC_OK);

        // Some actions have to be realized when the adaptation starts
        if (adaptationListener != null) {
            adaptationListener.adaptationStarted(audit);
        }
        
        while (i.compareTo(nbOfContent) < 0) {

            LOGGER.debug("Adapting ssp from  " +  i + TO_LOGGER_STR + (i + adaptationTreatmentWindow) + " for " +
                    audit.getSubject().getURL());

            Collection<Content> contentList = contentDataService.getSSPFromWebResource(
                                            webResourceId, 
                                            i, 
                                            adaptationTreatmentWindow, 
                                            true);
            endRetrieveDate = Calendar.getInstance().getTime();

            // Set the referential to the contentAdapterService due to different
            // behaviour in the implementation. Has to be removed when accessiweb 2.1
            // implementations will be based on jsoup.
            Set<Content> contentSet = new HashSet<>(contentAdapterService.adaptContent(contentList));
            
            endProcessDate = Calendar.getInstance().getTime();
            LOGGER.debug("Adapting " + contentList.size() + SSP_TOOK_LOGGER_STR +
                    (endProcessDate.getTime() - endRetrieveDate.getTime()) + MS_LOGGER_STR + contentSet.size());

            hasCorrectDOM = hasCorrectDOM || hasContentSetAtLeastOneCorrectDOM(contentSet);
            
            this.encodeSourceAndPersistContentList(contentSet);

            endPersistDate = Calendar.getInstance().getTime();
            LOGGER.debug("Persisting  " + contentSet.size() + SSP_TOOK_LOGGER_STR +
                    (endPersistDate.getTime() - endProcessDate.getTime()) +  MS_LOGGER_STR + "for " +
                    audit.getSubject().getURL());
            persistenceDuration = persistenceDuration + (endPersistDate.getTime() - endProcessDate.getTime());
            i = i + adaptationTreatmentWindow;
            // explicit call of the Gc
            System.gc();
        }
        
        LOGGER.debug("Application spent " + persistenceDuration + " ms to write in Disk while adapting");
        
        if (hasCorrectDOM) {
            setStatusToAudit(AuditStatus.PROCESSING);
        } else {
            LOGGER.warn("Audit has no corrected DOM");
            setStatusToAudit(AuditStatus.ERROR);
        }
        
        // Some actions have to be realized when the adaptation is completed
        if (adaptationListener != null) {
            adaptationListener.adaptationCompleted(audit);
        }
        LOGGER.info(audit.getSubject().getURL() + " has been adapted");
    }

    /**
     * 
     * @param contentSet
     * @return 
     */
    private boolean hasContentSetAtLeastOneCorrectDOM(Set<Content> contentSet) {
        for (Content content : contentSet) {
            // if one SSP with not empty DOM is encountered, we return true
            if (content instanceof SSP && 
                !((SSP) content).getDOM().isEmpty()) {
                
                return true;
            }
        }
        return false;
    }
    
    /**
     * Encode Source code and persist the content list
     * 
     * @param contentSet 
     */
    private void encodeSourceAndPersistContentList(Set<Content> contentSet) {
        for (Content content : contentSet) {
            if (content instanceof SSP && 
                !((SSP) content).getDOM().isEmpty()) {
                try {
                    ((SSP) content).setSource(MD5Encoder.MD5(((SSP) content).getSource()));
                } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
                    LOGGER.warn(ex.getMessage());
                }
            }
            try {
                contentDataService.saveOrUpdate(content);
            } catch (PersistenceException pe) {
                LOGGER.warn(content.getURI() +" could not have been persisted due to "  +pe.getLocalizedMessage());
                if (content instanceof SSP && 
                !((SSP) content).getDOM().isEmpty()) {
                    ((SSP) content).setDOM("");
                    contentDataService.saveOrUpdate(content);
                }
            }
        }
    }
    
    @Override
    public void process() {
        audit = auditDataService.getAuditWithTest(audit.getId());
        if (!audit.getStatus().equals(AuditStatus.PROCESSING)) {
            LOGGER.warn( AUDIT_STATUS_IS_LOGGER_STR + audit.getStatus() + WHILE_LOGGER_STR + AuditStatus.PROCESSING +
                    WAS_REQUIRED_LOGGER_STR);
            return;
        }
        LOGGER.info("Processing " + audit.getSubject().getURL());
        // debug tools
        Date beginProcessDate;
        Date endProcessDate = null;
        Date endPersistDate;
        long persistenceDuration = 0;

        long i = 0L;
        Long webResourceId = audit.getSubject().getId();
        long nbOfContent = contentDataService.getNumberOfSSPFromWebResource(audit.getSubject(), HttpStatus.SC_OK);

        Set<ProcessResult> processResultSet = new HashSet<>();
        
        while (i < nbOfContent) {
            LOGGER.debug( "Processing from " + i + TO_LOGGER_STR + (i + processingTreatmentWindow) + "for " +
                    audit.getSubject().getURL());
            beginProcessDate = Calendar.getInstance().getTime();
            Collection<Content> contentList = contentDataService.getSSPWithRelatedContentFromWebResource(
                                            webResourceId, 
                                            i, 
                                            processingTreatmentWindow, 
                                            false);
            processResultSet.clear();
            processResultSet.addAll(processorService.process(contentList, audit.getTestList()));
            for (ProcessResult processResult : processResultSet) {
                processResult.setGrossResultAudit(audit);
            }

            if (LOGGER.isDebugEnabled()) {
                endProcessDate = Calendar.getInstance().getTime();
                LOGGER.debug( "Processing of " + processingTreatmentWindow + " elements took " +
                        (endProcessDate.getTime() - beginProcessDate.getTime()) + MS_LOGGER_STR + "for " +
                        audit.getSubject().getURL());
            }
            if (LOGGER.isDebugEnabled()) {
                for (Content content : contentList) {
                    LOGGER.debug("Persisting result for page " +content.getURI());
                }
            }
            processResultDataService.saveOrUpdate(processResultSet);
            if (LOGGER.isDebugEnabled()) {
                endPersistDate = Calendar.getInstance().getTime();
                LOGGER.debug( "Persist processing of " + processingTreatmentWindow + " elements took " +
                        (endPersistDate.getTime() - endProcessDate.getTime()) + MS_LOGGER_STR + "for " +
                        audit.getSubject().getURL());
                persistenceDuration = persistenceDuration
                        + (endPersistDate.getTime() - endProcessDate.getTime());
            }
            i = i + processingTreatmentWindow;
            System.gc();
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug( "Application spent " + persistenceDuration + " ms to write in Disk while processing");
        }

        if (processResultDataService.getNumberOfGrossResultFromAudit(audit) > 0) {
            setStatusToAudit(AuditStatus.CONSOLIDATION);
        } else {
            LOGGER.error("Audit has no gross result");
            setStatusToAudit(AuditStatus.ERROR);
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(audit.getSubject().getURL() + " has been processed");
        }
    }
    
    @Override
    public void consolidate() {
        audit = auditDataService.getAuditWithTest(audit.getId());
        if (!audit.getStatus().equals(AuditStatus.CONSOLIDATION)) {
            LOGGER.warn( AUDIT_STATUS_IS_LOGGER_STR + audit.getStatus() + WHILE_LOGGER_STR + AuditStatus.CONSOLIDATION +
                    WAS_REQUIRED_LOGGER_STR);
            return;
        }

        // debug tools
        Date beginProcessDate = null;
        Date endProcessDate = null;
        Date endPersistDate;

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Consolidating " + audit.getSubject().getURL());
        }
        if (LOGGER.isDebugEnabled()) {
            beginProcessDate = Calendar.getInstance().getTime();
        }
        if (audit.getSubject() instanceof Page) {
            consolidate(processResultDataService.
                    getGrossResultFromAudit(audit), audit.getTestList());
            if (LOGGER.isDebugEnabled()) {
                endProcessDate = Calendar.getInstance().getTime();
                LOGGER.debug( CONSOLIDATING_TOOK_LOGGER_STR + (endProcessDate.getTime() - beginProcessDate.getTime()) +
                        MS_LOGGER_STR);
            }
        } else if (audit.getSubject() instanceof Site) {
            if (contentDataService.getNumberOfSSPFromWebResource(audit.getSubject(), HttpStatus.SC_OK) > 20) {
                List<Test> testList = new ArrayList<>();
                for (Test test : audit.getTestList()) {
                    if (! test.getNoProcess()) { // only consolidate if the process has been launched on the test
                        testList.add(test);

                        Collection<ProcessResult> prList= (List<ProcessResult>) processResultDataService.
                                getGrossResultFromAuditAndTest(audit, test);
                        consolidate(prList, testList);
                        testList.clear();
                    }
                }
                if (LOGGER.isDebugEnabled()) {
                    endProcessDate = Calendar.getInstance().getTime();
                    LOGGER.debug( CONSOLIDATING_TOOK_LOGGER_STR +
                            (endProcessDate.getTime() - beginProcessDate.getTime()) + MS_LOGGER_STR);
                }
            } else {
                Collection<ProcessResult> prList= (List<ProcessResult>) processResultDataService.
                            getGrossResultFromAudit(audit);
                consolidate(prList, audit.getTestList());
                if (LOGGER.isDebugEnabled()) {
                    endProcessDate = Calendar.getInstance().getTime();
                    LOGGER.debug(CONSOLIDATING_TOOK_LOGGER_STR +
                            (endProcessDate.getTime() - beginProcessDate.getTime()) + MS_LOGGER_STR);
                }
            }
        }
        audit = auditDataService.saveOrUpdate(audit);
        if (LOGGER.isDebugEnabled()) {
            endPersistDate = Calendar.getInstance().getTime();
            LOGGER.debug( "Persisting Consolidation of the audit took" +
                        (endPersistDate.getTime() - endProcessDate.getTime()) + MS_LOGGER_STR);
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(audit.getSubject().getURL() + " has been consolidated");
        }
    }

    /**
     * 
     * @param prList
     * @param testList 
     */
    private void consolidate(Collection<ProcessResult> prList, Collection<Test> testList) {
        Set<ProcessResult> processResultSet = new HashSet<>();
        if (LOGGER.isDebugEnabled()) {
            if (testList.size() == 1) {
                LOGGER.debug("Consolidate " + prList.size() + " elements for test " + testList.iterator().next().getCode());
            } else {
                LOGGER.debug("Consolidate " + prList.size() + " elements for " + testList.size() + " tests ");
            }
        }
        processResultSet.addAll(consolidatorService.consolidate(
                prList,
                testList));
        
        if (!processResultSet.isEmpty()) {
            audit.setStatus(AuditStatus.ANALYSIS);
        } else {
            LOGGER.warn("Audit has no net result");
            audit.setStatus(AuditStatus.ERROR);
        }
        Iterator<ProcessResult> iter = processResultSet.iterator();
        Set<ProcessResult> processResultSubset = new HashSet<>();
        int i = 0;
        while (iter.hasNext()) {
            ProcessResult pr = iter.next();
            pr.setNetResultAudit(audit);
            processResultSubset.add(pr);
            i++;
            if (i % consolidationTreatmentWindow == 0) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Persisting Consolidation from " + i + TO_LOGGER_STR + (i + consolidationTreatmentWindow));
                }
                processResultDataService.saveOrUpdate(processResultSubset);
                processResultSubset.clear();
            }
        }
        processResultDataService.saveOrUpdate(processResultSubset);
        processResultSubset.clear();
        System.gc();
    }

    @Override
    public void analyse() {
        audit = auditDataService.read(audit.getId());
        if (!audit.getStatus().equals(AuditStatus.ANALYSIS)) {
            LOGGER.warn(AUDIT_STATUS_IS_LOGGER_STR + audit.getStatus() + WHILE_LOGGER_STR + AuditStatus.ANALYSIS +
                    WAS_REQUIRED_LOGGER_STR);
            return ;
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Analysing " + audit.getSubject().getURL());
        }
        // debug tools
        Date beginProcessDate = null;
        Date endProcessDate = null;
        Date endPersistDate;
        Long persistenceDuration = (long) 0;

        WebResource parentWebResource = audit.getSubject();
        if (parentWebResource instanceof Page) {
            analyserService.analyse(parentWebResource, audit);
            webResourceDataService.saveOrUpdate(parentWebResource);
        } else if (parentWebResource instanceof Site) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Analysing results of scope site");
                beginProcessDate = Calendar.getInstance().getTime();
            }
            analyserService.analyse(parentWebResource, audit);
            if (LOGGER.isDebugEnabled()) {
                endProcessDate = Calendar.getInstance().getTime();
                LOGGER.debug("Analysing results of scope site took " +
                             (endProcessDate.getTime() - beginProcessDate.getTime()) + MS_LOGGER_STR);
            }
            webResourceDataService.saveOrUpdate(parentWebResource);

            if (LOGGER.isDebugEnabled()) {
                endPersistDate = Calendar.getInstance().getTime();
                LOGGER.debug("Persisting Analysis results of scope site " +
                            (endPersistDate.getTime() - endProcessDate.getTime()) + MS_LOGGER_STR);
                persistenceDuration = persistenceDuration
                        + (endPersistDate.getTime() - endProcessDate.getTime());
            }

            Long nbOfContent =
                    webResourceDataService.getNumberOfChildWebResource(parentWebResource);
            Long i = (long) 0;
            List<WebResource> webResourceList;
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Analysing " + nbOfContent + " elements ");
            }
            while (i.compareTo(nbOfContent) < 0) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Analysing results of scope page from " + i + TO_LOGGER_STR +
                            (i + analyseTreatmentWindow));
                    beginProcessDate = Calendar.getInstance().getTime();
                }
                webResourceList = webResourceDataService.getWebResourceFromItsParent(
                        parentWebResource,
                        i.intValue(),
                        analyseTreatmentWindow);
                for (WebResource webResource : webResourceList) {
                    if (LOGGER.isDebugEnabled()) {
                        endProcessDate = Calendar.getInstance().getTime();
                        LOGGER.debug("Analysing results for page " + webResource.getURL() + " took " +
                                (endProcessDate.getTime() - beginProcessDate.getTime()) + MS_LOGGER_STR);
                    }
                    analyserService.analyse(webResource,audit);
                    if (LOGGER.isDebugEnabled()) {
                        endPersistDate = Calendar.getInstance().getTime();
                        LOGGER.debug("Persisting Analysis results for page " + webResource.getURL() + " took " +
                                (endPersistDate.getTime() - endProcessDate.getTime()) + MS_LOGGER_STR);
                        persistenceDuration = persistenceDuration
                                + (endPersistDate.getTime() - endProcessDate.getTime());
                    }
                }
                i = i + analyseTreatmentWindow;
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Application spent " + persistenceDuration + " ms to write in Disk while analysing");
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(audit.getSubject().getURL() + " has been analysed");
        }
        setStatusToAudit(AuditStatus.COMPLETED);
        if (cleanUpRelatedContent) {
            cleanUpTestData(audit);
        }
    }
    
    /**
     * 
     * @param audit 
     */
    private void cleanUpTestData(Audit audit) {
        LOGGER.info("Cleaning-up data for " + audit.getSubject().getURL());
        long i = 0L;
        long webResourceId = audit.getSubject().getId();
        long nbOfContent = contentDataService.getNumberOfSSPFromWebResource(audit.getSubject(), HttpStatus.SC_OK);
        while (i < nbOfContent) {
            Collection<Content> contentList = contentDataService.getSSPWithRelatedContentFromWebResource(
                        webResourceId, 
                        i, 
                        processingTreatmentWindow, 
                        true);
            for (Content content : contentList) {
                if (content instanceof SSP) {
                    for (RelatedContent rc : ((SSP)content).getRelatedContentSet()) {
                        contentDataService.delete(rc.getId());
                    }
                }
            }
            i = i + processingTreatmentWindow;
        }
        for (ProcessResult pr : processResultDataService.getIndefiniteResultFromAudit(audit)) {
            processResultDataService.delete(pr.getId());
        }
        for (PreProcessResult ppr : preProcessResultDataService.getPreProcessResultFromAudit(audit)) {
            preProcessResultDataService.delete(ppr.getId());
        }
        LOGGER.info("Data cleaned-up for " + audit.getSubject().getURL());
    }
    
    /**
     * Set a new status to the audit instance and persist it
     * @param auditStatus 
     */
    public final void setStatusToAudit(AuditStatus auditStatus) {
        audit.setStatus(auditStatus);
        audit = auditDataService.saveOrUpdate(audit);
    }
 
    /**
     * Create a webResource of page type and associate it with the current audit
     * @param url 
     */
    protected void createEmptyPageResource(String url) {
        Page page = webResourceDataService.createPage(url);
        getAudit().setSubject(page);
        webResourceDataService.saveOrUpdate(page);
    }
    
    /**
     * Create a webResource of site type and associate it with the current audit
     * @param url 
     */
    protected void createEmptySiteResource(String url) {
        Site site = webResourceDataService.createSite(url);
        getAudit().setSubject(site);
        webResourceDataService.saveOrUpdate(site);
    }

    /**
     * Create the main webResource attached to the audit and then
     * passed to the scenario loader service
     *
     * @return
     *      a Site instance
     */
    private WebResource createWebResource() {
        WebResource webResource;
        if (scope.equals(PAGE)) {
            webResource = webResourceDataService.createPage(this.targetUrl);
        } else {
            webResource = webResourceDataService.createSite(this.targetUrl);
        }
        webResource.setAudit(getAudit());
        webResourceDataService.saveOrUpdate(webResource);
        getAudit().setSubject(webResource);
        return webResource;
    }

}
