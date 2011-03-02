package org.opens.tanaguru.entity.service.subject;

import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import com.adex.sdk.entity.service.GenericDataService;
import java.util.List;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface WebResourceDataService extends
        GenericDataService<WebResource, Long> {

    /**
     *
     * @param pageURL
     * @return
     */
    Page createPage(String pageURL);

    /**
     *
     * @param siteURL
     * @return
     */
    Site createSite(String siteURL);

    /**
     *
     * @param url
     *            the url of the web resource to find
     * @return the web resource found or null
     */
    WebResource getByUrl(String url);

    /**
     * 
     * @param url
     * @param webResource
     * @return
     */
    WebResource getByUrlAndParentWebResource(String url, WebResource webResource);

    /**
     * 
     * @param webResource
     * @param start
     * @param chunkSize
     * @return
     */
    List<WebResource> getWebResourceFromItsParent(WebResource webResource,
            int start,
            int chunkSize);

    /**
     * 
     * @param webResource
     * @return
     */
    Long getNumberOfChildWebResource(WebResource webResource);
}
