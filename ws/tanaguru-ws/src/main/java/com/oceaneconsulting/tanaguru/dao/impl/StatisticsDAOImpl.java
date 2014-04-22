package com.oceaneconsulting.tanaguru.dao.impl;


import java.math.BigInteger;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.opens.tanaguru.entity.statistics.CriterionStatistics;
import org.opens.tanaguru.entity.statistics.CriterionStatisticsImpl;
import org.opens.tanaguru.entity.statistics.TestStatistics;
import org.opens.tanaguru.entity.statistics.TestStatisticsImpl;
import org.opens.tanaguru.entity.statistics.ThemeStatistics;
import org.opens.tanaguru.entity.statistics.ThemeStatisticsImpl;
import org.opens.tanaguru.entity.statistics.WebResourceStatistics;
import org.opens.tanaguru.entity.statistics.WebResourceStatisticsImpl;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.springframework.stereotype.Repository;

import com.oceaneconsulting.tanaguru.dao.StatisticsDAO;
import com.oceaneconsulting.tanaguru.ws.types.AuditResult;


@Repository("statisticsDAO")  
public class StatisticsDAOImpl extends AbstractJPADAO<WebResourceStatistics, Long> implements StatisticsDAO {

    private static final String SELECT_CLAUSE_Q1 = "SELECT WEB_RESOURCE_STATISTICS.Mark, AUDIT.STATUS, WEB_RESOURCE_STATISTICS.ID_WEB_RESOURCE ";
    private static final String FROM_CLAUSE_Q1  = "FROM AUDIT left outer join WEB_RESOURCE on WEB_RESOURCE.ID_AUDIT = AUDIT.ID_AUDIT left outer join WEB_RESOURCE_STATISTICS on WEB_RESOURCE_STATISTICS.Id_Web_Resource=WEB_RESOURCE.Id_Web_Resource ";
    private static final String WHERE_CLAUSE_Q1  = "WHERE  AUDIT.ID_AUDIT = :idAudit";
	
    private static final String RESULT_URL = "http://localhost:8080/tgol-web-app/home/contract/page-result.html?wr=";
   
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
        	Object[] result = (Object[])query.getSingleResult();
            
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
