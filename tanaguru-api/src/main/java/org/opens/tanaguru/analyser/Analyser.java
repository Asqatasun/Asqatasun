package org.opens.tanaguru.analyser;

import java.util.List;
import org.opens.tanaguru.entity.audit.ProcessResult;

/**
 * 
 * @author ADEX
 */
public interface Analyser {

    /**
     *
     * @return the net result list
     */
    List<ProcessResult> getNetResultList();

    /**
     *
     * @return the result
     */
    float getResult();

    /**
     * Starts the processing
     */
    void run();

    /**
     *
     * @param netResultList the result list to set
     */
    void setNetResultList(List<ProcessResult> netResultList);
}
