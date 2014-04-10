package com.oceaneconsulting.tanaguru.dao;


import org.opens.tanaguru.entity.statistics.WebResourceStatistics;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;

import com.oceaneconsulting.tanaguru.ws.types.AuditResult;




public interface StatisticsDAO extends GenericDAO<WebResourceStatistics, Long> {

	 public AuditResult findWeightedMarkAndStatusByAuditId(Long idAudit);

}