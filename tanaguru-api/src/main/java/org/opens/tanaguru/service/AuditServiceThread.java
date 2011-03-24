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
public interface AuditServiceThread extends Runnable {

    Audit getAudit();

    void init();

    void crawl();

    void loadContent();

    void adaptContent();

    void process();

    void consolidate();

    void analyse();

    void add(AuditServiceThreadListener listener);

    void remove(AuditServiceThreadListener listener);
}
