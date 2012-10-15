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

package org.opens.tanaguru.entity.dao.statistics;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.SSPImpl;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.statistics.WebResourceStatistics;
import org.opens.tanaguru.entity.statistics.WebResourceStatisticsImpl;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.subject.WebResourceImpl;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

/**
 *
 * @author jkowalczyk
 */
public class WebResourceStatisticsDAOImpl extends AbstractJPADAO<WebResourceStatistics, Long>
        implements WebResourceStatisticsDAO {

    private static final String JOIN_PROCESS_RESULT =" JOIN r.processResultSet pr";

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

    @Override
    public Long findResultCountByResultType(
            Long webresourceId,
            TestSolution testSolution) {
        if (webresourceId == null) {
            return null;
        }
        Query query = entityManager.createQuery(
                "SELECT count (pr.id) FROM "
                + getWebResourceEntityClass().getName() + " r"
                + JOIN_PROCESS_RESULT
                + " WHERE (r.id=:id OR r.parent.id=:id)"
                + " AND pr.definiteValue = :value");
        query.setParameter("id", webresourceId);
        query.setParameter("value", testSolution);
        try {
            return (Long)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }  

    @Override
    public Long findNumberOfOccurrencesByWebResourceAndResultType(
            Long webresourceId,
            TestSolution testSolution) {
        if (webresourceId == null) {
            return null;
        }
        Query query = entityManager.createQuery(
                "SELECT count(pk.id) FROM "
                + getWebResourceEntityClass().getName() + " r"
                + JOIN_PROCESS_RESULT
                + " JOIN pr.remarkSet pk"
                + " WHERE (r.id=:id OR r.parent.id=:id)"
                + " AND pr.definiteValue = :value"
                + " AND pk.issue = :value");
        query.setParameter("id", webresourceId);
        query.setParameter("value", testSolution);
        try {
            return (Long)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Integer findHttpStatusCodeByWebResource(Long webresourceId) {
        if (webresourceId == null) {
            return null;
        }
        Query query = entityManager.createQuery(
                "SELECT s.httpStatusCode FROM "
                + getSspEntityClass().getName() + " s"
                + " WHERE s.page.id=:id");
        query.setParameter("id", webresourceId);
        try {
            return (Integer)query.getSingleResult();
        } catch (NoResultException e) {
            return -1;
        } catch (NullPointerException e) {
            return -1;
        }
    }

    @Override
    public WebResourceStatistics findWebResourceStatisticsByWebResource(WebResource webResource) {
        if (webResource == null) {
            return null;
        }
        Query query = entityManager.createQuery(
                "SELECT s FROM "
                + getEntityClass().getName() + " s"
                + " WHERE s.webResource=:webResource");
        query.setParameter("webResource", webResource);
        try {
            return (WebResourceStatistics)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

}