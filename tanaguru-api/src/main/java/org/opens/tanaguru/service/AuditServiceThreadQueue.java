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

    /**
     *
     * @param max
     */
    void setPageAuditExecutionListMax(int max);

    /**
     *
     * @param max
     */
    void setSiteAuditExecutionListMax(int max);

    /**
     *
     * @param audit
     */
    void addPageAudit(Audit audit);

    /**
     *
     * @param audit
     */
    void addPageUploadAudit(Audit audit);

    /**
     *
     * @param audit
     */
    void addSiteAudit(Audit audit);

    /**
     *
     */
    void processWaitQueue();

    /**
     *
     * @param listener
     */
    void add(AuditServiceListener listener);

    /**
     *
     * @param listener
     */
    void remove(AuditServiceListener listener);
}
