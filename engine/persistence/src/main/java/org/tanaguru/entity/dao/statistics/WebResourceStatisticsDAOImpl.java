/*
 * @(#)WebResourceStatisticsDAOImpl.java
 *
 * Copyright  2011 SAS OPEN-S. All rights reserved.
 * OPEN-S PROPRIETARY/CONFIDENTIAL.  Use is subject to license terms.
 *
 * This file is  protected by the  intellectual  property rights
 * in  France  and  other  countries, any  applicable  copyrights  or
 * patent rights, and international treaty provisions. No part may be
 * reproduced  in  any  form  by  any  mean  without   prior  written
 * authorization of OPEN-S.
 */

package org.tanaguru.entity.dao.statistics;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.tanaguru.entity.audit.SSP;
import org.tanaguru.entity.audit.SSPImpl;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.parameterization.Parameter;
import org.tanaguru.entity.reference.Test;
import org.tanaguru.entity.reference.TestImpl;
import org.tanaguru.entity.statistics.WebResourceStatistics;
import org.tanaguru.entity.statistics.WebResourceStatisticsImpl;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.entity.subject.WebResourceImpl;
import org.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

/**
 * 
 * @author jkowalczyk
 */
public class WebResourceStatisticsDAOImpl extends
		AbstractJPADAO<WebResourceStatistics, Long> implements
		WebResourceStatisticsDAO {

	private static final String JOIN_PROCESS_RESULT = " LEFT JOIN r.processResultSet pr ";
	private static final String JOIN_TEST = " LEFT JOIN pr.test t";

	@Override
	protected Class<? extends WebResourceStatistics> getEntityClass() {
		return WebResourceStatisticsImpl.class;
	}

	@Override
	public Class<? extends WebResource> getWebResourceEntityClass() {
		return WebResourceImpl.class;
	}

	private Class<? extends SSP> getSspEntityClass() {
		return SSPImpl.class;
	}

	private Class<? extends Test> getTestEntityClass() {
		return TestImpl.class;
	}

	@Override
	public Long findResultCountByResultType(Long webresourceId,
			TestSolution testSolution) {
		if (webresourceId == null) {
			return null;
		}
		Query query = entityManager.createQuery("SELECT count (pr.id) FROM "
				+ getWebResourceEntityClass().getName() + " r"
				+ JOIN_PROCESS_RESULT + " WHERE (r.id=:id OR r.parent.id=:id)"
				+ " AND pr.definiteValue = :value");
		query.setParameter("id", webresourceId);
		query.setParameter("value", testSolution);
		try {
			return (Long) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public BigDecimal findWeightedResultCountByResultType(Long webresourceId,
			Collection<Parameter> paramSet, TestSolution testSolution,
			boolean isManualAudit) {
		if (webresourceId == null) {
			return null;
		}
		String queryStringForAutomatic = "SELECT t FROM "
				+ getWebResourceEntityClass().getName() + " r"
				+ JOIN_PROCESS_RESULT + JOIN_TEST
				+ " WHERE (r.id=:id OR r.parent.id=:id)"
				+ " AND pr.definiteValue = :value";
		String queryStringForManual = "SELECT t FROM "
				+ getWebResourceEntityClass().getName() + " r"
				+ JOIN_PROCESS_RESULT + JOIN_TEST
				+ " WHERE (r.id=:id OR r.parent.id=:id)"
				+ " AND pr.manualDefiniteValue = :value";
		Query query = null;
		if (isManualAudit) {
			query = entityManager.createQuery(queryStringForManual);
		} else {
			query = entityManager.createQuery(queryStringForAutomatic);
		}
		query.setParameter("id", webresourceId);
		query.setParameter("value", testSolution);
		try {
			return computeWeightedCountFromTestList(
					(List<Test>) query.getResultList(), paramSet);
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Long findNumberOfOccurrencesByWebResourceAndResultType(
			Long webresourceId, TestSolution testSolution, boolean isManualAudit) {
		if (webresourceId == null) {
			return null;
		}
		String queryStringForAutomatic = "SELECT count(pk.id) FROM "
				+ getWebResourceEntityClass().getName() + " r"
				+ JOIN_PROCESS_RESULT + " JOIN pr.remarkSet pk"
				+ " WHERE (r.id=:id OR r.parent.id=:id)"
				+ " AND pr.definiteValue = :value" + " AND pk.issue = :value";
		String queryStringForManual = "SELECT count(pk.id) FROM "
				+ getWebResourceEntityClass().getName() + " r"
				+ JOIN_PROCESS_RESULT + " JOIN pr.remarkSet pk"
				+ " WHERE (r.id=:id OR r.parent.id=:id)"
				+ " AND pr.manualDefiniteValue = :value" + " AND pk.issue = :value";
		Query query = null;
		if(isManualAudit){
			query = entityManager.createQuery(queryStringForManual);
		} else {
			query = entityManager.createQuery(queryStringForAutomatic);
		}
		
		query.setParameter("id", webresourceId);
		query.setParameter("value", testSolution);
		try {
			return (Long) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Integer findHttpStatusCodeByWebResource(Long webresourceId) {
		if (webresourceId == null) {
			return null;
		}
		Query query = entityManager
				.createQuery("SELECT s.httpStatusCode FROM "
						+ getSspEntityClass().getName() + " s"
						+ " WHERE s.page.id=:id");
		query.setParameter("id", webresourceId);
		try {
			return (Integer) query.getSingleResult();
		} catch (NoResultException e) {
			return -1;
		} catch (NullPointerException e) {
			return -1;
		}
	}


	@Override
	public WebResourceStatistics findWebResourceStatisticsByWebResource(
			WebResource webResource) {
		
		//FIXME :YNE: henceforth, we have two lines of WebResourceStatistics (auto and manual). this methode must be adapted to this function
		//TODO :YNE we can do so by adding a new critorion manualAudit !=1 (That can return the automatic audit stats)
		if (webResource == null) {
			return null;
		}
		Query query = entityManager.createQuery("SELECT s FROM "
				+ getEntityClass().getName() + " s"
				+ " WHERE s.webResource=:webResource");
		query.setParameter("webResource", webResource);
		try {
			return (WebResourceStatistics) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NullPointerException e) {
			return null;
		}
	}

	/**
	 * 
	 * @param testList
	 * @param paramSet
	 * @return
	 */
	private BigDecimal computeWeightedCountFromTestList(List<Test> testList,
			Collection<Parameter> paramSet) {
		BigDecimal weightedCount = BigDecimal.ZERO;

		for (Test test : testList) {
			BigDecimal weight = getTestWeightFromParameter(test, paramSet);
			if (weight == null) {
				weight = test.getWeight();
			}
			weightedCount = weightedCount.add(weight);
		}
		return weightedCount;
	}

	/**
	 * 
	 * @param test
	 * @param paramSet
	 * @return
	 */
	private BigDecimal getTestWeightFromParameter(Test test,
			Collection<Parameter> paramSet) {
		for (Parameter param : paramSet) {
			if (param.getParameterElement().getParameterElementCode()
					.equals(test.getCode())) {
				return BigDecimal.valueOf(Double.valueOf(param.getValue()));
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * */
	@Override
	public WebResourceStatistics findWebResourceStatisticsByWebResource(
			WebResource webResource, boolean manual) {
		if (webResource == null) {
			return null;
		}
		
		Query query = entityManager.createQuery("SELECT s FROM "
				+ getEntityClass().getName() + " s"
				+ " WHERE s.webResource=:webResource" + " AND s.isManualAuditStatistics=:manual");
		
		query.setParameter("webResource", webResource);
		query.setParameter("manual", manual? 1 : 0 );
		try {
			return (WebResourceStatistics) query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	}

}