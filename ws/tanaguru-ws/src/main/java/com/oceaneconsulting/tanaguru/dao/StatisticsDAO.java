package com.oceaneconsulting.tanaguru.dao;


import org.opens.tanaguru.entity.statistics.WebResourceStatistics;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;

import com.oceaneconsulting.tanaguru.ws.types.AuditResult;



/**
 * Statistics DAO description for webservice .
 *
 * @author shamdi at oceaneconsulting dot com
 *
 */
public interface StatisticsDAO extends GenericDAO<WebResourceStatistics, Long> {

	/**
	  * Global statistics for a given audit when it is in complete status.
	  * @param idAudit audit identifier
	  * @return Audit global result
	  */
	 public AuditResult findWeightedMarkAndStatusByAuditId(Long idAudit);

}