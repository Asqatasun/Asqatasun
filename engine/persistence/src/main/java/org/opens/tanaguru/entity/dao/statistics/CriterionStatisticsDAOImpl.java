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

package org.opens.tanaguru.entity.dao.statistics;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.commons.lang3.StringUtils;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.statistics.CriterionStatistics;
import org.opens.tanaguru.entity.statistics.CriterionStatisticsImpl;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.subject.WebResourceImpl;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

/**
 *
 * @author jkowalczyk
 */
public class CriterionStatisticsDAOImpl extends AbstractJPADAO<CriterionStatistics, Long>
        implements CriterionStatisticsDAO {

    private static final String JOIN_PROCESS_RESULT =" JOIN r.processResultSet pr";
    private static final String JOIN_TEST =" JOIN pr.test t";

    @Override
    protected Class<? extends CriterionStatistics> getEntityClass() {
        return CriterionStatisticsImpl.class;
    }

    @Override
    public Class<? extends WebResource> getWebResourceEntityClass() {
        return WebResourceImpl.class;
    }
    
    private String selectAllThemeKey;
    public String getSelectAllThemeKey() {
        return selectAllThemeKey;
    }

    public void setSelectAllThemeKey(String selectAllThemeKey) {
        this.selectAllThemeKey = selectAllThemeKey;
    }
    

    @Override
    public Long findResultCountByResultTypeAndCriterion(
            WebResource webResource,
            TestSolution testSolution,
            Criterion criterion) {
        Query query = entityManager.createQuery(
                "SELECT count(pr.id)FROM "
                + getWebResourceEntityClass().getName() + " r"
                + JOIN_PROCESS_RESULT
                + JOIN_TEST
                + " JOIN t.criterion cr"
                + " WHERE (r.id=:id OR r.parent.id=:id)"
                + " AND pr.definiteValue = :value"
                + " AND cr = :criterion");
        query.setParameter("id", webResource.getId());
        query.setParameter("value", testSolution);
        query.setParameter("criterion", criterion);
        try {
            return (Long)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
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
                + " JOIN t.criterion cr"
                + " WHERE (r.id=:id OR r.parent.id=:id)"
                + " AND pr.definiteValue = :value"
                + " AND cr = :criterion");
        query.setParameter("id", webResource.getId());
        query.setParameter("value", testSolution);
//        query.setParameter("criterion", criterion);
        try {
            return (Long)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Collection<CriterionStatistics> findCriterionStatisticsByWebResource(
            WebResource webResource,
            String theme,
            Collection<String> testSolutions) {
        boolean hasTheme = false;
        if (theme != null && !StringUtils.equals(theme, selectAllThemeKey)) {
            hasTheme = true;
        }
        boolean hasTestSolution = false;
        if (!testSolutions.isEmpty()) {
            hasTestSolution = true;
        }
        StringBuilder strb = new StringBuilder();
        strb.append("SELECT cs FROM ");
        strb.append(getEntityClass().getName());
        strb.append(" cs ");
        strb.append(" JOIN cs.webResourceStatistics wrs ");
        if (hasTheme) {
            strb.append(" JOIN cs.criterion cr ");
        }
        strb.append(" WHERE wrs.webResource=:webResource ");
        if (hasTheme) {
            strb.append(" AND cr.theme.code=:theme ");
        }
        if (hasTestSolution) {
            strb.append(" AND cs.criterionResult IN (:testSolution) ");
        }
        Query query = entityManager.createQuery(strb.toString());
        query.setParameter("webResource", webResource);
        if (hasTestSolution) {
            Collection<TestSolution> solutions = new ArrayList<TestSolution>();
            for (String solution : testSolutions) {
                solutions.add(TestSolution.valueOf(solution));
            }
            query.setParameter("testSolution", solutions);
        }
        if (hasTheme) {
            query.setParameter("theme", theme);
        }
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}