/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.analyser;

import java.util.List;
import org.opens.tanaguru.entity.audit.ProcessResult;

/**
 *
 * @author enzolalay
 */
public interface AnalyserFactory {// TODO Write javadoc

    Analyser create(List<ProcessResult> netResultList);
}
