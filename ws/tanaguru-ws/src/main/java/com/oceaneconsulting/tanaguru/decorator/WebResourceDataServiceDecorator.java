package com.oceaneconsulting.tanaguru.decorator;



import org.opens.tanaguru.entity.service.subject.WebResourceDataService;

import com.oceaneconsulting.tanaguru.ws.types.AuditResult;
/**
 * This interface define added statistic methods to the wrapped statistics service.
 *
 * @author shamdi at oceaneconsulting dot com
 *
 */
public interface WebResourceDataServiceDecorator extends WebResourceDataService {
	
	/**
	  * Global statistics for a given audit.
	  * @param idAudit audit identifier
	  * @return Audit global result
	  */
	public AuditResult findWeightedMarkAndStatusByAuditId(Long idAudit);
}
