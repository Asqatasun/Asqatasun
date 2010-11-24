package org.opens.tanaguru.entity.subject;

import com.adex.sdk.entity.Entity;
import java.util.Collection;
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

    /**
     *
     * @return the mark
     */
    float getMark();

    /**
     *
     * @param audit
     */
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
    Collection<? extends ProcessResult> getProcessResultList();

    /**
     *
     * @param processResultList the process result Collection to set
     */
    void setProcessResultList(Collection<? extends ProcessResult> processResultList);

    /**
     *
     * @param mark
     *            the mark to set
     */
    void setMark(float mark);

    /**
     *
     * @param processResult the process result to add
     */
    void addProcessResult(ProcessResult processResult);

    /**
     *
     * @param processResultList the process result Collection to add
     */
    void addAllProcessResult(Collection<? extends ProcessResult> processResultList);

}
