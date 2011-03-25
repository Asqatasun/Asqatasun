/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.analyser;

import java.util.List;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 *
 * @author enzolalay
 */
public interface AnalyserFactory {// TODO Write javadoc

    /**
     *
     * @param netResultList
     * @return
     */
    Analyser create(List<ProcessResult> netResultList);

    /**
     * 
     * @param webResource
     * @return
     */
    Analyser create(WebResource webResource);
}
