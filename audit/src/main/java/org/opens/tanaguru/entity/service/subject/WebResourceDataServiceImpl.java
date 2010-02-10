package org.opens.tanaguru.entity.service.subject;

import org.opens.tanaguru.entity.service.subject.*;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.dao.subject.WebResourceDAO;
import com.adex.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author ADEX
 */
public class WebResourceDataServiceImpl extends
		AbstractGenericDataService<WebResource, Long> implements
		WebResourceDataService {

	public WebResourceDataServiceImpl() {
		super();
	}

	public Page createPage(String url) {
		return ((WebResourceFactory) entityFactory).createPage(url);
	}

	public Site createSite(String url) {
		return ((WebResourceFactory) entityFactory).createSite(url);
	}

	public WebResource findByUrl(String url) {
		return ((WebResourceDAO) entityDao).findByUrl(url);
	}
}
