/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.entity.dao.statistics;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.reference.Test;
import org.tanaguru.entity.statistics.TestStatistics;
import org.tanaguru.entity.statistics.TestStatisticsImpl;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.entity.subject.WebResourceImpl;
import org.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

/**
 *
 * @author jkowalczyk
 */
public class TestStatisticsDAOImpl extends AbstractJPADAO<TestStatistics, Long>
        implements TestStatisticsDAO {

    private static final String JOIN_PROCESS_RESULT =" JOIN r.processResultSet pr";
    private static final String JOIN_TEST =" JOIN pr.test t";

    @Override
    protected Class<? extends TestStatistics> getEntityClass() {
        return TestStatisticsImpl.class;
    }

    @Override
    public Class<? extends WebResource> getWebResourceEntityClass() {
        return WebResourceImpl.class;
    }

    @Override
    public Long findResultCounterByResultTypeAndTest(
            WebResource webResource,
            Test test,
            TestSolution testSolution) {
        if (webResource == null) {
            return null;
        }
        Query query = entityManager.createQuery(
                "SELECT count(r.id) FROM "
                + getWebResourceEntityClass().getName() + " r"
                + JOIN_PROCESS_RESULT
                + JOIN_TEST
                + " WHERE r.parent.id=:id"
                + " AND pr.definiteValue = :value"
                + " AND t = :test");
        query.setParameter("id", webResource.getId());
        query.setParameter("value", testSolution);
        query.setParameter("test", test);
        try {
            return (Long)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}