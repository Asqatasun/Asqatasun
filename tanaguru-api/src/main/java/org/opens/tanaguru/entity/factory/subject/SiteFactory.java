package org.opens.tanaguru.entity.factory.subject;

import org.opens.tanaguru.entity.subject.Site;
import com.adex.sdk.entity.factory.GenericFactory;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface SiteFactory extends GenericFactory<Site> {

	/**
	 * 
	 * @param url
	 *            the url to set
	 * @return a new instance of Site
	 */
	Site create(String url);
}
