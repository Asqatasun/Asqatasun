package org.opens.tanaguru.entity.subject;

import com.adex.sdk.entity.Entity;
import java.util.List;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.ProcessResult;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface WebResource extends Entity {

    Audit getAudit();

    /**
     *
     * @return the label
     */
    String getLabel();

    /**
     * 
     * @return the container
     */
    Site getParent();

    /**
     *
     * @return the URL
     */
    String getURL();

    void setAudit(Audit audit);

    /**
     *
     * @param label
     *            the label to set
     */
    void setLabel(String label);

    /**
     *
     * @param container
     *            the container to set
     */
    void setParent(Site container);

    /**
     *
     * @param url
     *            the URL to set
     */
    void setURL(String url);

    /**
     *
     * @return the process result list
     */
    List<? extends ProcessResult> getProcessResultList();

    /**
     *
     * @param processResultList the process result list to set
     */
    void setProcessResultList(List<? extends ProcessResult> processResultList);

    /**
     *
     * @param processResult the process result to add
     */
    void addProcessResult(ProcessResult processResult);

    /**
     *
     * @param processResultList the process result list to add
     */
    void addAllProcessResult(List<? extends ProcessResult> processResultList);
}
