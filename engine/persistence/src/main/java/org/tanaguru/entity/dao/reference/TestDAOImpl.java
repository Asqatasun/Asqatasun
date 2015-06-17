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
package org.tanaguru.entity.dao.reference;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.AuditImpl;
import org.tanaguru.entity.reference.*;
import org.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

/**
 *
 * @author jkowalczyk
 */
public class TestDAOImpl extends AbstractJPADAO<Test, Long> implements TestDAO {

    /**
     * Constructor
     */
    public TestDAOImpl() {
        super();
    }

    @Override
    protected Class<TestImpl> getEntityClass() {
        return TestImpl.class;
    }
    
    protected Class<AuditImpl> getAuditEntityClass() {
        return AuditImpl.class;
    }

    @Override
    public Test read(String label) {
        StringBuilder queryStr = new StringBuilder();
        queryStr.append("SELECT t FROM ");
        queryStr.append(getEntityClass().getName());
        queryStr.append(" t WHERE");
        queryStr.append(" t.label = :label");
        Query query = entityManager.createQuery(queryStr.toString());
        query.setParameter("label", label);
        query.setHint("org.hibernate.cacheable", "true");
        return (Test)query.getSingleResult();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Test> retrieveAll(Reference reference) {
        Query query = entityManager.createQuery("SELECT t FROM "
                + getEntityClass().getName() + " t"
                + " WHERE t.criterion.reference = :reference");
        query.setParameter("reference", reference);
        query.setHint("org.hibernate.cacheable", "true");
        return (List<Test>) query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Test> retrieveAllByCode(String[] codeArray) {
        if (codeArray.length == 0) {
            return new ArrayList<Test>();
        }

        StringBuilder stringBuilder = new StringBuilder("SELECT t FROM "
                + getEntityClass().getName() + " t" + " WHERE t.code IN (");

        boolean first = true;
        for (String code : codeArray) {
            if (!first) {
                stringBuilder.append(',');
            } else {
                first = false;
            }
            stringBuilder.append("'");
            stringBuilder.append(code);
            stringBuilder.append("'");
        }
        stringBuilder.append(')');
        Query query = entityManager.createQuery(stringBuilder.toString());
        return (List<Test>) query.getResultList();
    }

    @Override
    public List<Test> retrieveAllByReferenceAndLevel(Reference reference, Level level) {
        StringBuilder queryStr = new StringBuilder();
        queryStr.append("SELECT t FROM ");
        queryStr.append(getEntityClass().getName());
        queryStr.append(" t WHERE");
        queryStr.append(" t.level.rank <= :levelRank");

        queryStr.append(" AND t.criterion.reference = :reference");
        Query query = entityManager.createQuery(queryStr.toString());
        query.setParameter("levelRank", level.getRank());
        query.setParameter("reference", reference);
        query.setHint("org.hibernate.cacheable", "true");
        return query.getResultList();
    }

    @Override
    public List<Test> retrieveAllByCriterion(Criterion criterion) {
        StringBuilder queryStr = new StringBuilder();
        queryStr.append("SELECT t FROM ");
        queryStr.append(getEntityClass().getName());
        queryStr.append(" t WHERE");
        queryStr.append(" t.criterion = :criterion");
        Query query = entityManager.createQuery(queryStr.toString());
        query.setParameter("criterion", criterion);
        query.setHint("org.hibernate.cacheable", "true");
        return query.getResultList();
    }
    
    @Override
    public List<Test> retrieveAllByReferenceAndCriterion(
            Reference reference,
            List<Criterion> criterion) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Test retrieveTestFromAuditAndLabel(Audit audit, String testLabel) {
        Query query = entityManager.createQuery(
                "SELECT t FROM "
                + getAuditEntityClass().getName() + " a"
                + " LEFT JOIN a.testList t"
                + " WHERE t.label=:testLabel"
                + " AND a = :audit");
        query.setParameter("audit", audit);
        query.setParameter("testLabel", testLabel);
        try {
            return (Test)query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}