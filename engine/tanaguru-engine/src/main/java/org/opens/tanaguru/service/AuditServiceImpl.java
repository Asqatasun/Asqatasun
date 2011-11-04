/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.service;

import java.util.HashSet;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.PersistenceException;
import org.opens.tanaguru.contentadapter.AdaptationListener;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.audit.ProcessResultDataService;
import org.opens.tanaguru.entity.service.parameterization.ParameterDataService;
import org.opens.tanaguru.entity.service.reference.TestDataService;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.util.FileNaming;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author jkowalczyk
 */
public class AuditServiceImpl implements AuditService, AuditServiceListener {

    private AnalyserService analyserService;
    private ConsolidatorService consolidatorService;
    private ContentAdapterService contentAdapterService;
    private ContentLoaderService contentLoaderService;
    private CrawlerService crawlerService;
    private ProcessorService processorService;

    private AuditDataService auditDataService;
    private ProcessResultDataService processResultDataService;
    private TestDataService testDataService;
    private WebResourceDataService webResourceDataService;
    private ContentDataService contentDataService;
    private ParameterDataService parameterDataService;
    
    private AuditServiceThreadFactory auditServiceThreadFactory;
    private AuditServiceThreadQueue auditServiceThreadQueue;
    private Set<AuditServiceListener> listeners;
    private AdaptationListener adaptationListener = null;

    @Autowired
    public AuditServiceImpl(ContentDataService contentDataService) {
        super();
        this.contentDataService = contentDataService;
    }

    @Override
    public void add(AuditServiceListener listener) {
        if (listeners == null) {
            listeners = new HashSet<AuditServiceListener>();
        }
        listeners.add(listener);
    }

    @Override
    public void remove(AuditServiceListener listener) {
        if (listeners == null) {
            return;
        }
        listeners.remove(listener);
    }

    public void setAuditServiceThreadQueue(AuditServiceThreadQueue auditServiceThreadQueue) {
        if (this.auditServiceThreadQueue != null) {
            this.auditServiceThreadQueue.remove(this);
        }
        this.auditServiceThreadQueue = auditServiceThreadQueue;
        this.auditServiceThreadQueue.add(this);
    }

    @Override
    public Audit auditPage(String pageUrl, Set<Parameter> paramSet) {
        Page page = webResourceDataService.createPage(pageUrl);

        List<Test> testList = testDataService.getTestListFromParamSet(paramSet);

        // the paramSet has to be persisted
        parameterDataService.saveOrUpdate(paramSet);

        Audit audit = auditDataService.create();
        audit.setSubject(page);
        audit.setTestList(testList);
        audit.setStatus(AuditStatus.CRAWLING);
        audit.setParameterSet(paramSet);
        
        auditServiceThreadQueue.addPageAudit(audit);
        return audit;
    }
    
    @Override
    public Audit auditPageUpload(Map<String, String> fileMap, Set<Parameter> paramSet) {
        WebResource mainWebResource;
        if (fileMap.size() > 1) {
            mainWebResource = webResourceDataService.createSite(
                    FileNaming.addProtocolToUrl(fileMap.keySet().iterator().next()));
            // the webresource instance needs to be persisted first.
            webResourceDataService.saveOrUpdate(mainWebResource);
            for (String pageUrl : fileMap.keySet()) {
                Page page = webResourceDataService.createPage(pageUrl);
                ((Site)mainWebResource).addChild(page);
                webResourceDataService.saveOrUpdate(page);
            }
        } else {
            mainWebResource = webResourceDataService.createPage(fileMap.keySet().iterator().next());
            // the webresource instance needs to be persisted first.
            webResourceDataService.saveOrUpdate(mainWebResource);
        }
        //call the load content service to convert files into SSP and link it
        //to the appropriate webResource
        List<Content> contentList = contentLoaderService.loadContent(mainWebResource, fileMap);
        // retrieve the list of tests from the parameters
        List<Test> testList = testDataService.getTestListFromParamSet(paramSet);

        // the paramSet has to be persisted
        parameterDataService.saveOrUpdate(paramSet);

        Audit audit = auditDataService.create();
        audit.setSubject(mainWebResource);
        audit.setTestList(testList);
        audit.setStatus(AuditStatus.CONTENT_ADAPTING);
        audit.setParameterSet(paramSet);
        auditDataService.saveOrUpdate(audit);
        mainWebResource.setAudit(audit);
        // the webresource needs to be persisted a second time because of the
        // relation with the audit
        webResourceDataService.saveOrUpdate(mainWebResource);
        for (Content content : contentList) {
            content.setAudit(audit);
            try {
            contentDataService.saveOrUpdate(content);
            } catch (PersistenceException pe){
                audit.setStatus(AuditStatus.ERROR);
                break;
            }
        }
        auditServiceThreadQueue.addPageUploadAudit(audit);
        return audit;
    }

    @Override
    public Audit auditSite(String siteUrl, Set<Parameter> paramSet) {
        Site site = webResourceDataService.createSite(siteUrl);

        List<Test> testList = testDataService.getTestListFromParamSet(paramSet);

        // the paramSet has to be persisted
        parameterDataService.saveOrUpdate(paramSet);

        Audit audit = auditDataService.create();
        audit.setSubject(site);
        audit.setTestList(testList);
        audit.setStatus(AuditStatus.CRAWLING);
        audit.setParameterSet(paramSet);

        auditServiceThreadQueue.addSiteAudit(audit);
        return audit;
    }

    @Override
    public Audit auditSite(String siteUrl, List<String> pageUrlList, Set<Parameter> paramSet) {
        Site site = webResourceDataService.createSite(siteUrl);
        for (String pageUrl : pageUrlList) {
            site.addChild(webResourceDataService.createPage(pageUrl));
        }

        List<Test> testList = testDataService.getTestListFromParamSet(paramSet);

        // the paramSet has to be persisted
        parameterDataService.saveOrUpdate(paramSet);
        
        Audit audit = auditDataService.create();
        audit.setSubject(site);
        audit.setTestList(testList);
        audit.setStatus(AuditStatus.CRAWLING);
        audit.setParameterSet(paramSet);
        
        auditServiceThreadQueue.addPageAudit(audit);
        return audit;
    }

    @Override
    public Audit audit(Audit audit) {
        AuditServiceThread auditServiceThread = getInitialisedAuditServiceThread(audit, adaptationListener);
        auditServiceThread.run();
        audit = auditServiceThread.getAudit();
        return audit;
    }

    @Override
    public Audit init(Audit audit) {
        AuditServiceThread auditServiceThread = getInitialisedAuditServiceThread(audit, null);
        auditServiceThread.init();
        audit = auditServiceThread.getAudit();
        return audit;
    }

    @Override
    public Audit crawl(Audit audit) {
        AuditServiceThread auditServiceThread = getInitialisedAuditServiceThread(audit, null);
        auditServiceThread.crawl();
        audit = auditServiceThread.getAudit();
        return audit;
    }

    @Override
    public Audit loadContent(Audit audit) {
        AuditServiceThread auditServiceThread = getInitialisedAuditServiceThread(audit, null);
        auditServiceThread.loadContent();
        audit = auditServiceThread.getAudit();
        return audit;
    }

    @Override
    public Audit adaptContent(Audit audit) {
        AuditServiceThread auditServiceThread = getInitialisedAuditServiceThread(audit, adaptationListener);
        auditServiceThread.adaptContent();
        audit = auditServiceThread.getAudit();
        return audit;
    }

    @Override
    public Audit process(Audit audit) {
        AuditServiceThread auditServiceThread = getInitialisedAuditServiceThread(audit, null);
        auditServiceThread.process();
        audit = auditServiceThread.getAudit();
        return audit;
    }

    @Override
    public Audit consolidate(Audit audit) {
        AuditServiceThread auditServiceThread = getInitialisedAuditServiceThread(audit, null);
        auditServiceThread.consolidate();
        audit = auditServiceThread.getAudit();
        return audit;
    }

    @Override
    public Audit analyse(Audit audit) {
        AuditServiceThread auditServiceThread = getInitialisedAuditServiceThread(audit, null);
        auditServiceThread.analyse();
        audit = auditServiceThread.getAudit();
        return audit;
    }

    /**
     *
     * @param audit
     * @param adaptationListener
     * @return
     */
    private AuditServiceThread getInitialisedAuditServiceThread(
            Audit audit,
            AdaptationListener adaptationListener) {
        return auditServiceThreadFactory.create(
                auditDataService,
                contentDataService,
                processResultDataService,
                webResourceDataService,
                crawlerService,
                contentAdapterService,
                processorService,
                consolidatorService,
                analyserService,
                audit,
                adaptationListener,
                true);
    }

    public void setAnalyserService(AnalyserService analyserService) {
        this.analyserService = analyserService;
    }

    public void setAuditServiceThreadFactory(AuditServiceThreadFactory auditServiceThreadFactory) {
        this.auditServiceThreadFactory = auditServiceThreadFactory;
    }

    public void setContentDataService(ContentDataService contentDataService) {
        this.contentDataService = contentDataService;
    }

    public void setAuditDataService(AuditDataService auditDataService) {
        this.auditDataService = auditDataService;
    }

    public AuditDataService getAuditDataService() {
        return this.auditDataService;
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

    public void setAdaptationListener(
            AdaptationListener adaptationListener) {
        this.adaptationListener = adaptationListener;
    }

    public void setParameterDataService(ParameterDataService parameterDataService) {
        this.parameterDataService = parameterDataService;
    }

    @Override
    public void auditCompleted(Audit audit) {
        fireAuditCompleted(audit);
    }

    private void fireAuditCompleted(Audit audit) {
        if (listeners == null) {
            return;
        }
        for (AuditServiceListener listener : listeners) {
            listener.auditCompleted(audit);
        }
    }

    @Override
    public void auditCrashed(Audit audit, Exception exception) {
        if (listeners == null) {
            return;
        }
        for (AuditServiceListener listener : listeners) {
            listener.auditCrashed(audit, exception);
        }
    }

}