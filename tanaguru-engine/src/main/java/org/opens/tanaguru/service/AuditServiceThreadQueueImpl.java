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

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.log4j.Logger;
import org.opens.tanaguru.contentadapter.AdaptationListener;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.audit.ProcessResultDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;

/**
 *
 * @author enzolalay
 */
public class AuditServiceThreadQueueImpl implements AuditServiceThreadQueue, AuditServiceThreadListener {
    private static Logger LOGGER = Logger.getLogger(AuditServiceThreadQueueImpl.class);
    private AuditDataService auditDataService;
    private ContentDataService contentDataService;
    private ProcessResultDataService processResultDataService;
    private WebResourceDataService webResourceDataService;
    private CrawlerService crawlerService;
    private ContentAdapterService contentAdapterService;
    private ProcessorService processorService;
    private ConsolidatorService consolidatorService;
    private AnalyserService analyserService;
    private AuditServiceThreadFactory auditServiceThreadFactory;
    private AdaptationListener adaptationListener;
    private Queue<Audit> pageAuditWaitQueue = new ConcurrentLinkedQueue<Audit>();
    private Queue<Audit> siteAuditWaitQueue = new ConcurrentLinkedQueue<Audit>();
    private List<AuditServiceThread> pageAuditExecutionList = new Vector<AuditServiceThread>();
    private List<AuditServiceThread> siteAuditExecutionList = new Vector<AuditServiceThread>();
    private int pageAuditExecutionListMax = 3;
    private int siteAuditExecutionListMax = 3;
        private Set<AuditServiceListener> listeners;
    private Long lastToken = 0L;

    public AuditServiceThreadQueueImpl() {
        super();
    }

    @Override
    public void setPageAuditExecutionListMax(int max) {
        this.pageAuditExecutionListMax = max;
    }

    @Override
    public void setSiteAuditExecutionListMax(int max) {
        this.siteAuditExecutionListMax = max;
    }

    public AuditServiceThreadQueueImpl(
            AuditDataService auditDataService,
            ContentDataService contentDataService,
            ProcessResultDataService processResultDataService,
            WebResourceDataService webResourceDataService,
            CrawlerService crawlerService,
            ContentAdapterService contentAdapterService,
            ProcessorService processorService,
            ConsolidatorService consolidatorService,
            AnalyserService analyserService,
            AuditServiceThreadFactory auditServiceThreadFactory,
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
        this.auditServiceThreadFactory = auditServiceThreadFactory;
        this.adaptationListener = adaptationListener;
    }

    public void setAdaptationListener(AdaptationListener adaptationListener) {
        this.adaptationListener = adaptationListener;
    }

    public void setAnalyserService(AnalyserService analyserService) {
        this.analyserService = analyserService;
    }

    public void setAuditDataService(AuditDataService auditDataService) {
        this.auditDataService = auditDataService;
    }

    public void setAuditServiceThreadFactory(AuditServiceThreadFactory auditServiceThreadFactory) {
        this.auditServiceThreadFactory = auditServiceThreadFactory;
    }

    public void setConsolidatorService(ConsolidatorService consolidatorService) {
        this.consolidatorService = consolidatorService;
    }

    public void setContentAdapterService(ContentAdapterService contentAdapterService) {
        this.contentAdapterService = contentAdapterService;
    }

    public void setContentDataService(ContentDataService contentDataService) {
        this.contentDataService = contentDataService;
    }

    public void setCrawlerService(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }

    public void setProcessResultDataService(ProcessResultDataService processResultDataService) {
        this.processResultDataService = processResultDataService;
    }

    public void setProcessorService(ProcessorService processorService) {
        this.processorService = processorService;
    }

    public void setWebResourceDataService(WebResourceDataService webResourceDataService) {
        this.webResourceDataService = webResourceDataService;
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

    @Override
    public synchronized void addPageAudit(Audit audit) {
        pageAuditWaitQueue.offer(audit);
        processPageAuditWaitQueue();
    }

    private synchronized void processPageAuditWaitQueue() {
        processAuditWaiQueue(
                pageAuditWaitQueue,
                pageAuditExecutionList,
                pageAuditExecutionListMax,
                true);
    }

    @Override
    public synchronized void addPageUploadAudit(Audit audit) {
        pageAuditWaitQueue.offer(audit);
        processPageUploadAuditWaitQueue();
    }

    private synchronized void processPageUploadAuditWaitQueue() {
        processAuditWaiQueue(
                pageAuditWaitQueue,
                pageAuditExecutionList,
                pageAuditExecutionListMax,
                false);
    }

    @Override
    public synchronized void addSiteAudit(Audit audit) {
        siteAuditWaitQueue.offer(audit);
        processSiteAuditWaitQueue();
    }

    private synchronized void processSiteAuditWaitQueue() {
        processAuditWaiQueue(
                siteAuditWaitQueue,
                siteAuditExecutionList,
                siteAuditExecutionListMax,
                true);
    }

    private synchronized void processAuditWaiQueue(Queue<Audit> auditWaitQueue,
            List<AuditServiceThread> auditExecutionList,
            int auditExecutionListMax,
            boolean isAuditOnline) {
        if (auditWaitQueue.peek() == null) {
            return;
        }
        if (auditExecutionList.size() < auditExecutionListMax) {
            synchronized (lastToken) {
                Long token = new Date().getTime();
                while (token - lastToken < 10) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        LOGGER.error(ex);
                    }
                }
                lastToken = token.longValue();
            }
            AuditServiceThread auditServiceThread = auditServiceThreadFactory.create(
                    auditDataService,
                    contentDataService,
                    processResultDataService,
                    webResourceDataService,
                    crawlerService,
                    contentAdapterService,
                    processorService,
                    consolidatorService,
                    analyserService,
                    auditWaitQueue.poll(),
                    adaptationListener,
                    isAuditOnline);
            auditServiceThread.add(this);
            auditExecutionList.add(auditServiceThread);
            new Thread(auditServiceThread).start();
        }
    }

    @Override
    public void auditCompleted(AuditServiceThread thread) {
        if (!pageAuditExecutionList.remove(thread)) {
            siteAuditExecutionList.remove(thread);
        }
        fireAuditCompleted(thread.getAudit());
        thread.remove(this);
        processWaitQueue();
    }

    @Override
    public void auditCrashed(AuditServiceThread thread, Exception exception) {
        if (!pageAuditExecutionList.remove(thread)) {
            siteAuditExecutionList.remove(thread);
        }
        fireAuditCrashed(thread.getAudit(), exception);
        thread.remove(this);
        processWaitQueue();
    }

    @Override
    public void processWaitQueue() {
        processPageAuditWaitQueue();
        processPageUploadAuditWaitQueue();
        processSiteAuditWaitQueue();
    }

    private void fireAuditCompleted(Audit audit) {
        if (listeners == null) {
            return;
        }
        for (AuditServiceListener listener : listeners) {
            listener.auditCompleted(audit);
        }
    }

    private void fireAuditCrashed(Audit audit, Exception exception) {
        if (listeners == null) {
            return;
        }
        for (AuditServiceListener listener : listeners) {
            listener.auditCrashed(audit, exception);
        }
    }

}