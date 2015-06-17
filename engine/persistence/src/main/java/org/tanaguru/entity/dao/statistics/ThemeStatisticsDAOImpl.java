/*
 * @(#)ThemeStatisticsDAOImpl.java
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

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.reference.Theme;
import org.tanaguru.entity.statistics.ThemeStatistics;
import org.tanaguru.entity.statistics.ThemeStatisticsImpl;
import org.tanaguru.entity.statistics.WebResourceStatistics;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.entity.subject.WebResourceImpl;
import org.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

/**
 *
 * @author jkowalczyk
 */
public class ThemeStatisticsDAOImpl extends AbstractJPADAO<ThemeStatistics, Long>
        implements ThemeStatisticsDAO {

    private static final String JOIN_PROCESS_RESULT =" JOIN r.processResultSet pr";
    private static final String JOIN_TEST =" JOIN pr.test t";

    @Override
    protected Class<? extends ThemeStatistics> getEntityClass() {
        return ThemeStatisticsImpl.class;
    }

    @Override
    public Class<? extends WebResource> getWebResourceEntityClass() {
        return WebResourceImpl.class;
    }

    @Override
    public Long findResultCountByResultTypeAndTheme(
            WebResource webResource,
            TestSolution testSolution,
            Theme theme) {
        Query query = entityManager.createQuery(
                "SELECT count(pr.id)FROM "
                + getWebResourceEntityClass().getName() + " r"
                + JOIN_PROCESS_RESULT
                + JOIN_TEST
                + " JOIN t.criterion cr"
                + " JOIN cr.theme th"
                + " WHERE (r.id=:id OR r.parent.id=:id)"
                + " AND pr.definiteValue = :value"
                + " AND th = :theme");
        query.setParameter("id", webResource.getId());
        query.setParameter("value", testSolution);
        query.setParameter("theme", theme);
        try {
            return (Long)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     * */
	@Override
	public ThemeStatistics findThemeStatisticsByWebResource(Theme theme,
			WebResourceStatistics wrStats) {

		StringBuilder builder = new StringBuilder();

		builder.append(" SELECT ts FROM ");
		builder.append(getEntityClass().getName());
		builder.append(" as ts");
		builder.append(" WHERE ");
		builder.append(" ts.webResourceStatistics= :wrStats").append(" and ");
		builder.append(" ts.theme= :theme");

		Query query = entityManager.createQuery(builder.toString());
		query.setParameter("wrStats", wrStats);
		query.setParameter("theme", theme);

		try {
			return (ThemeStatistics) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

}
