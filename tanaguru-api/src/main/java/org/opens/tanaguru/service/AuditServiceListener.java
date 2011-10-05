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
public interface AuditServiceListener {// TODO Write javadoc

    /**
     *
     * @param audit
     */
    void auditCompleted(Audit audit);

    /**
     * 
     * @param audit
     * @param exception
     */
    void auditCrashed(Audit audit, Exception exception);
}
