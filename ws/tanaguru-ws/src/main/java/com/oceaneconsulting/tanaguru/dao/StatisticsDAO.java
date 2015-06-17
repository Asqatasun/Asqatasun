package com.oceaneconsulting.tanaguru.dao;


import java.util.List;

import org.tanaguru.entity.statistics.WebResourceStatistics;
import org.tanaguru.sdk.entity.dao.GenericDAO;

import com.oceaneconsulting.tanaguru.ws.types.AuditResult;
import com.oceaneconsulting.tanaguru.ws.types.GlobalStatsOrder;



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

	 public List<AuditResult> findWeightedMarkAndStatus(GlobalStatsOrder globalStatsOrder);

}