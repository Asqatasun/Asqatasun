package org.opens.tanaguru.entity.dao.subject;

import org.opens.tanaguru.entity.subject.WebResource;
import com.adex.sdk.entity.dao.GenericDAO;
import java.util.List;
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
    WebResource findByUrl(String url);

    /**
     * 
     * @param audit
     * @param url
     * @return
     */
    WebResource findByAuditAndUrl(Audit audit, String url);

    /**
     *
     * @param url
     * @param webResource
     * @return
     */
    WebResource findByUrlAndParentWebResource(String url, WebResource webResource);

    /**
     * 
     * @param webResource
     * @param start
     * @param chunkSize
     * @return
     */
    List<WebResource> findWebResourceFromItsParent(WebResource webResource,
            int start,
            int chunkSize);

    /**
     * 
     * @param webResource
     * @return
     */
    Long findNumberOfChildWebResource(WebResource webResource);

}
