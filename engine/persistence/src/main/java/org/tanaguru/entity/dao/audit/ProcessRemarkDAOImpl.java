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
package org.tanaguru.entity.dao.audit;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.Query;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessRemarkImpl;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

/**
 * 
 * @author jkowalczyk
 */
public class ProcessRemarkDAOImpl extends AbstractJPADAO<ProcessRemark, Long>
        implements ProcessRemarkDAO {

    public ProcessRemarkDAOImpl() {
        super();
    }

    @Override
    protected Class<ProcessRemarkImpl> getEntityClass() {
        return ProcessRemarkImpl.class;
    }

    @Override
    public Collection<ProcessRemark> retrieveProcessRemarksFromProcessResult(
            ProcessResult processResult, 
            int limit) {
        Query query = entityManager.createQuery("SELECT r FROM "
                + getEntityClass().getName() + " r"
                + " left join fetch r.elementSet e"
                + " WHERE r.processResult = :processResult "
                + " ORDER BY r.id ASC");
        query.setParameter("processResult", processResult);
        /* limit = 0 or -1 means all */
        if (limit > 0) {
            query.setMaxResults(limit);
        }
        Set setItems = new LinkedHashSet(query.getResultList());
        return setItems;
    }

    @Override
    public Collection<ProcessRemark> retrieveProcessRemarksFromProcessResultAndTestSolution(
            ProcessResult processResult, 
            TestSolution testSolution, 
            int limit) {
        Query query = entityManager.createQuery("SELECT r FROM "
                + getEntityClass().getName() + " r"
                + " left join fetch r.elementSet e"
                + " WHERE r.processResult = :processResult "
                + " AND r.issue=:testSolution"
                + " ORDER BY r.id ASC");
        query.setParameter("processResult", processResult);
        query.setParameter("testSolution", testSolution);
        /* limit = 0 or -1 means all */
        if (limit > 0) {
            query.setMaxResults(limit);
        }
        Set setItems = new LinkedHashSet(query.getResultList());
        return setItems;
    }
    
    @Override
    public int countProcessRemarksFromProcessResultAndTestSolution(
            ProcessResult processResult, 
            TestSolution testSolution) {
        Query query = entityManager.createQuery("SELECT count(r.id) FROM "
                + getEntityClass().getName() + " r"
                + " WHERE r.processResult = :processResult "
                + " AND r.issue=:testSolution");
        query.setParameter("processResult", processResult);
        query.setParameter("testSolution", testSolution);
        return ((Long)query.getSingleResult()).intValue();
    }
    
}