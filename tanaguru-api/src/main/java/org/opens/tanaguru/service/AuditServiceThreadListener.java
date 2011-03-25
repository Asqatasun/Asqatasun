/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.service;

/**
 *
 * @author enzolalay
 */
public interface AuditServiceThreadListener {// TODO Write javadoc

    void auditCompleted(AuditServiceThread thread);

    void auditCrashed(AuditServiceThread aThis, Throwable exception);
}
