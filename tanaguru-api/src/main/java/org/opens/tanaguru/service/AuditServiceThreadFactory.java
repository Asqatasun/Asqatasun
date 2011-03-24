/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.service;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.audit.ProcessResultDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;

/**
 *
 * @author enzolalay
 */
public interface AuditServiceThreadFactory {// TODO Write javadoc

    AuditServiceThread create(AuditDataService auditDataService, ContentDataService contentDataService, ProcessResultDataService processResultDataService, WebResourceDataService webResourceDataService, CrawlerService crawlerService, ContentAdapterService contentAdapterService, ProcessorService processorService, ConsolidatorService consolidatorService, AnalyserService analyserService, Audit audit);
}
