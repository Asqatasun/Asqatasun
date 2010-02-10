package org.opens.tanaguru.entity.service.subject;

import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import com.adex.sdk.entity.service.GenericDataService;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface WebResourceDataService extends
		GenericDataService<WebResource, Long> {

	Page createPage(String pageURL);

	Site createSite(String siteURL);

	/**
	 * 
	 * @param url
	 *            the url of the web resource to find
	 * @return the web resource found or null
	 */
	WebResource findByUrl(String url);
}
