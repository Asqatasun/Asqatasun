package org.opens.tanaguru.entity.factory.subject;

import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import com.adex.sdk.entity.factory.GenericFactory;

/**
 * 
 * @author ADEX
 */
public interface WebResourceFactory extends GenericFactory<WebResource> {

	Page createPage(String pageURL);

	Site createSite(String siteURL);
}
