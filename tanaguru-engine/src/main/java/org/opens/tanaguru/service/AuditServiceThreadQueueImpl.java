/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
    public void auditCrashed(AuditServiceThread thread, Throwable exception) {
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

    private void fireAuditCrashed(Audit audit, Throwable exception) {
        if (listeners == null) {
            return;
        }
        for (AuditServiceListener listener : listeners) {
            listener.auditCrashed(audit, exception);
        }
    }
}
