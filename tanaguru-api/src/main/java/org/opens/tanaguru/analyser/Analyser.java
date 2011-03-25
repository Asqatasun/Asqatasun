package org.opens.tanaguru.analyser;

import java.util.List;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author ADEX
 */
public interface Analyser {

    /**
     *
     * @return the net result list
     */
    @Deprecated
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
    @Deprecated
    void setNetResultList(List<ProcessResult> netResultList);

    /**
     * 
     * @param webResource
     */
    void setWebResource(WebResource webResource);

    /**
     * 
     */
    WebResource getWebResource();
}
