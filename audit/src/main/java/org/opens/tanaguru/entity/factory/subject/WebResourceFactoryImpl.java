package org.opens.tanaguru.entity.factory.subject;

import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.PageImpl;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.SiteImpl;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author ADEX
 */
public class WebResourceFactoryImpl implements WebResourceFactory {

	public WebResourceFactoryImpl() {
		super();
	}

	public WebResource create() {
		return new PageImpl();
	}

	public Page createPage(String pageURL) {
		return new PageImpl(pageURL);
	}

	public Site createSite(String siteURL) {
		return new SiteImpl(siteURL);
	}
}
