/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.service;

import org.opens.tanaguru.entity.audit.Audit;

/**
 *
 * @author enzolalay
 */
public interface AuditServiceThreadQueue {// TODO Write javadoc

    void setPageAuditExecutionListMax(int max);

    void setSiteAuditExecutionListMax(int max);

    void addPageAudit(Audit audit);

    void addSiteAudit(Audit audit);

    void processWaitQueue();

    void add(AuditServiceListener listener);

    void remove(AuditServiceListener listener);
}
