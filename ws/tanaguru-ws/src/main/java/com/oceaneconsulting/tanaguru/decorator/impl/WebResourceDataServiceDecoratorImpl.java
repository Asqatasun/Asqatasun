package com.oceaneconsulting.tanaguru.decorator.impl;


import java.util.List;

import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.oceaneconsulting.tanaguru.dao.StatisticsDAO;
import com.oceaneconsulting.tanaguru.decorator.WebResourceDataServiceDecorator;
import com.oceaneconsulting.tanaguru.ws.types.AuditResult;
import com.oceaneconsulting.tanaguru.ws.types.GlobalStatsOrder;

/**
 * This class wrap {@link org.opens.tanaguru.entity.service.subject.WebResourceDataService} class. 
 * It enlarge default webresource data service by adding specific statistics.
 *
 * @author shamdi at oceaneconsulting dot com
 *
 */
@Service("webResourceDataServiceDecorator")
public class WebResourceDataServiceDecoratorImpl extends AbstractGenericDataService<WebResource, Long>  implements WebResourceDataServiceDecorator {
		
		@Autowired
		@Qualifier("webResourceDataService")
	    private WebResourceDataService webResourceDataService;
	    
		@Autowired
		private StatisticsDAO statisticsDAO;


	    public AuditResult findWeightedMarkAndStatusByAuditId(Long idAudit) {
	    	return statisticsDAO.findWeightedMarkAndStatusByAuditId(idAudit);
	    }
		
	    public List<AuditResult> findWeightedMarkAndStatus(GlobalStatsOrder globalStatsOrder) {
	    	return statisticsDAO.findWeightedMarkAndStatus(globalStatsOrder);
		}
		
	    //TODO get statistics for audit site 

		@Override
		public Page createPage(String arg0) {
			//Not implemented
			return null;
		}

		@Override
		public Site createSite(String arg0) {
			//Not implemented
			return null;
		}

		@Override
		public WebResource getByUrl(String arg0) {
			//Not implemented
			return null;
		}

		@Override
		public WebResource getByUrlAndParentWebResource(String arg0,
				WebResource arg1) {
			//Not implemented
			return null;
		}

		@Override
		public Long getNumberOfChildWebResource(WebResource arg0) {
			//Not implemented
			return null;
		}

		@Override
		public List<WebResource> getWebResourceFromItsParent(WebResource arg0,
				int arg1, int arg2) {
			//Not implemented
			return null;
		}


	}