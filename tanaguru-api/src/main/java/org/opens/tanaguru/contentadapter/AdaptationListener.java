/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.contentadapter;

import org.opens.tanaguru.entity.audit.Audit;

/**
 *
 * @author jkowalczyk
 */
public interface AdaptationListener {

    /**
     *
     * @param audit
     */
    void adaptationStarted(Audit audit);
    
    /**
     *
     * @param audit
     */
    void adaptationCompleted(Audit audit);

}