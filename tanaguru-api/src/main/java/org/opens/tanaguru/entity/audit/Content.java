package org.opens.tanaguru.entity.audit;

import com.adex.sdk.entity.Entity;
import java.util.Date;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface Content extends Entity {

    /**
     *
     * @return the audit
     */
    Audit getAudit();

    /**
     *
     * @return the date of loading
     */
    Date getDateOfLoading();

    /**
     *
     * @return the URI
     */
    String getURI();

    /**
     *
     * @param audit
     *            the audit to set
     */
    void setAudit(Audit audit);

    /**
     *
     * @param dateOfLoading
     *            the date of loading to set
     */
    void setDateOfLoading(Date dateOfLoading);

    /**
     *
     * @param uri
     *            the URI to set
     */
    void setURI(String uri);
}
