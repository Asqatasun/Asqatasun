package com.oceaneconsulting.tanaguru.dao.impl;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.tanaguru.entity.statistics.CriterionStatistics;
import org.tanaguru.entity.statistics.CriterionStatisticsImpl;
import org.tanaguru.entity.statistics.TestStatistics;
import org.tanaguru.entity.statistics.TestStatisticsImpl;
import org.tanaguru.entity.statistics.ThemeStatistics;
import org.tanaguru.entity.statistics.ThemeStatisticsImpl;
import org.tanaguru.entity.statistics.WebResourceStatistics;
import org.tanaguru.entity.statistics.WebResourceStatisticsImpl;
import org.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.springframework.stereotype.Repository;

import com.oceaneconsulting.tanaguru.dao.StatisticsDAO;
import com.oceaneconsulting.tanaguru.ws.types.AuditResult;
import com.oceaneconsulting.tanaguru.ws.types.GlobalStatsOrder;

/**
 * Statistics DAO implementation for webservice . This class is based
 *  on Tanaguru model to calculate different audit stats.
 *
 * @author shamdi at oceaneconsulting dot com
 *
 */
@Repository("statisticsDAO")
public class StatisticsDAOImpl extends AbstractJPADAO<WebResourceStatistics, Long> implements StatisticsDAO{
	
    //Audit page global stats query
	private static final String SELECT_CLAUSE_Q1 = "SELECT WEB_RESOURCE_STATISTICS.Mark, AUDIT.STATUS, WEB_RESOURCE_STATISTICS.ID_WEB_RESOURCE ";
    private static final String FROM_CLAUSE_Q1  = "FROM AUDIT left outer join WEB_RESOURCE on WEB_RESOURCE.ID_AUDIT = AUDIT.ID_AUDIT left outer join WEB_RESOURCE_STATISTICS on WEB_RESOURCE_STATISTICS.Id_Web_Resource=WEB_RESOURCE.Id_Web_Resource ";
    private static final String WHERE_CLAUSE_Q1  = "WHERE  AUDIT.ID_AUDIT = :idAudit";
	
    //Audit site/scenario global stats query
    private static final String SELECT_CLAUSE_Q2 = SELECT_CLAUSE_Q1;
    private static final String FROM_CLAUSE_Q2  = FROM_CLAUSE_Q1;
    private static final String WHERE_CLAUSE_Q20  = "WHERE 1=1 ";
    private static final String WHERE_CLAUSE_Q21  = "AND AUDIT.ID_AUDIT in (SELECT WS_INVOCATION.ID_AUDIT FROM WS_INVOCATION WHERE 1=1 ";
    private static final String WHERE_CLAUSE_Q22  = "AND WS_INVOCATION.ID_AUDIT in (:idAudits) ";
    private static final String WHERE_CLAUSE_Q23  = "AND WS_INVOCATION.AUDIT_TYPE = :auditType ";
    private static final String WHERE_CLAUSE_Q24  = "AND WS_INVOCATION.COUNTRY = :country ";
    private static final String WHERE_CLAUSE_Q25  = "AND WS_INVOCATION.CATEGORY = :category ";
    private static final String WHERE_CLAUSE_Q26  = ") ";
    private static final String WHERE_CLAUSE_Q27  = "AND AUDIT.STATUS = :status ";
    private static final String RESULT_URL = "http://localhost:8080/tgol-web-app/home/contract/page-result.html?wr=";
   
    /**
     * {@inheritDoc}
     */
    @Override
    public AuditResult findWeightedMarkAndStatusByAuditId(Long idAudit) {
    	AuditResult auditResult = new AuditResult();
        StringBuilder queryString = new StringBuilder();
        queryString.append(SELECT_CLAUSE_Q1); 
        queryString.append(FROM_CLAUSE_Q1); 
        queryString.append(WHERE_CLAUSE_Q1); 
        Query query = entityManager.createNativeQuery(queryString.toString());
        query.setParameter("idAudit", idAudit);
        try {
        	Object[] result = (Object[])query. getSingleResult();
            
            if (result[0] instanceof Float) {
            	auditResult.setScore((Float)result[0]);
            } else if (result[0] instanceof Double) {
            	auditResult.setScore(((Double)result[0]).floatValue());
            } else {
            	auditResult.setScore(Float.valueOf(0));
            }
            
            auditResult.setStatus((String)result[1]);
            
            if(result[2] != null){
            	auditResult.setUrl(RESULT_URL + (BigInteger)result[2]);
            }
            
            
        } catch (NoResultException e) {
        	return null;
        }
        
        return auditResult;
    }
    
	@Override
	public List<AuditResult> findWeightedMarkAndStatus(GlobalStatsOrder globalStatsOrder) {
		List<AuditResult> auditResults = new ArrayList<AuditResult>();
        StringBuilder queryString = new StringBuilder();
        queryString.append(SELECT_CLAUSE_Q2); 
        queryString.append(FROM_CLAUSE_Q2); 
        queryString.append(WHERE_CLAUSE_Q20); 
        queryString.append(WHERE_CLAUSE_Q21);
        if(globalStatsOrder.getIdAudits() != null && !globalStatsOrder.getIdAudits().isEmpty()){
            queryString.append(WHERE_CLAUSE_Q22);       	
        }
        if(globalStatsOrder.getAuditType() != null){
            queryString.append(WHERE_CLAUSE_Q23);        	
        }
        if(globalStatsOrder.getCountry() != null && !globalStatsOrder.getCountry().isEmpty()){
            queryString.append(WHERE_CLAUSE_Q24);        	
        }
        if(globalStatsOrder.getCategory() != null && !globalStatsOrder.getCategory().isEmpty()){
            queryString.append(WHERE_CLAUSE_Q25);
        }

        queryString.append(WHERE_CLAUSE_Q26); 
        
        if(globalStatsOrder.getStatus() != null && !globalStatsOrder.getStatus().isEmpty()){
            queryString.append(WHERE_CLAUSE_Q27);
        }
        
        Query query = entityManager.createNativeQuery(queryString.toString());
        
        
        if(globalStatsOrder.getIdAudits() != null && !globalStatsOrder.getIdAudits().isEmpty()){
        	query.setParameter("idAudits", globalStatsOrder.getIdAudits());
        }
        if(globalStatsOrder.getAuditType() != null){
        	query.setParameter("auditType", globalStatsOrder.getAuditType());        	
        }
        if(globalStatsOrder.getCountry() != null && !globalStatsOrder.getCountry().isEmpty()){
        	query.setParameter("country", globalStatsOrder.getCountry());  
        }
        if(globalStatsOrder.getCategory() != null && !globalStatsOrder.getCategory().isEmpty()){
        	query.setParameter("category", globalStatsOrder.getCategory());  
        }
        if(globalStatsOrder.getStatus() != null && !globalStatsOrder.getStatus().isEmpty()){
        	query.setParameter("status", globalStatsOrder.getStatus());  
        }

        
        try {
        	List<Object[]> results = (List<Object[]>)query. getResultList();
            for(Object[] result : results){
            	AuditResult auditResult = new AuditResult();
	            if (result[0] instanceof Float) {
	            	auditResult.setScore((Float)result[0]);
	            } else if (result[0] instanceof Double) {
	            	auditResult.setScore(((Double)result[0]).floatValue());
	            } else {
	            	auditResult.setScore(Float.valueOf(0));
	            }
	            
	            auditResult.setStatus((String)result[1]);
	            
	            if(result[2] != null){
	            	auditResult.setUrl(RESULT_URL + (BigInteger)result[2]);
	            }
	            
	            auditResults.add(auditResult);
            }
            
        } catch (NoResultException e) {
        	return null;
        }
        
        return auditResults;
	}

    @Override
    protected Class<? extends WebResourceStatistics> getEntityClass() {
        return WebResourceStatisticsImpl.class;
    }
    
    protected Class<? extends CriterionStatistics> getCriterionStatisticsEntityClass() {
        return CriterionStatisticsImpl.class;
    }
    
    protected Class<? extends TestStatistics> getTestStatisticsEntityClass() {
        return TestStatisticsImpl.class;
    }
    
    protected Class<? extends ThemeStatistics> getThemeStatisticsEntityClass() {
        return ThemeStatisticsImpl.class;
    }


}
