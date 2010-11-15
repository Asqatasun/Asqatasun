package org.opens.tanaguru.entity.dao.subject;

import org.opens.tanaguru.entity.subject.WebResource;
import com.adex.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.entity.audit.Audit;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface WebResourceDAO extends GenericDAO<WebResource, Long> {

    /**
     *
     * @param url
     *            the url of the web resource to find
     * @return the web resource found or null
     */
    public WebResource findByUrl(String url);

    /**
     * 
     * @param audit
     * @param url
     * @return
     */
    public WebResource findByAuditAndUrl(Audit audit, String url);
}
