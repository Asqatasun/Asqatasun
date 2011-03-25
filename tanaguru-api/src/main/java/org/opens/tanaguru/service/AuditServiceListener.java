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

    void auditCompleted(Audit audit);

    void auditCrashed(Audit audit, Throwable exception);
}
