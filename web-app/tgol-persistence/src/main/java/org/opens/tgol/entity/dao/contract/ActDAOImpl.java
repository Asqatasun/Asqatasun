/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tgol.entity.dao.contract;

import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.opens.tgol.entity.contract.Act;
import org.opens.tgol.entity.contract.ActImpl;
import org.opens.tgol.entity.contract.ActStatus;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.contract.ScopeEnum;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.opens.tanaguru.entity.audit.AuditStatus;

/**
 *
 * @author jkowalczyk
 */
public class ActDAOImpl extends AbstractJPADAO<Act, Long> implements ActDAO {

    private static final String CACHEABLE_OPTION="org.hibernate.cacheable";
    private static final String DESC_STR = " DESC ";
    private static final String ASC_STR = " ASC ";
    
    public ActDAOImpl(){
        super();
    }

    @Override
    protected Class<? extends Act> getEntityClass() {
        return ActImpl.class;
    }

    @Override
    public int findNumberOfAct(Contract contract) {
        Query query = entityManager.createQuery("SELECT COUNT(a.id) FROM "
                + getEntityClass().getName() + " a "
                + " WHERE a.contract = :contract");
        query.setParameter("contract", contract);
        query.setHint(CACHEABLE_OPTION, "true");
        return Long.valueOf((Long)query.getSingleResult()).intValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Act> findAllActsByContract(Contract contract) {
        Query query = entityManager.createQuery("SELECT a FROM "
                + getEntityClass().getName() + " a"
                + " WHERE a.contract = :contract");
        query.setHint(CACHEABLE_OPTION, "true");
        query.setParameter("contract", contract);
        return (List<Act>)query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Act> findActsByContract(
            Contract contract,
            int nbOfActs,
            int sortDirection,
            ScopeEnum scopeEnum,
            boolean onlyCompleted) {
        StringBuilder queryString = new StringBuilder();
        queryString.append("SELECT a FROM ");
        queryString.append(getEntityClass().getName());
        queryString.append(" a");
        queryString.append(" WHERE a.contract = :contract");
        queryString.append(" AND ");
        if (!onlyCompleted) {
            queryString.append("(");
        }
        queryString.append("a.status = :completedStatus");
        if (!onlyCompleted) {
            queryString.append(" OR a.status = :errorStatus)");
        }
        if (scopeEnum != null) {
            queryString.append(" AND a.scope.code = :scopeCode");
        }
        queryString.append(" ORDER BY a.endDate");
        if (sortDirection == 2) {
            queryString.append(DESC_STR);
        } else {
            queryString.append(ASC_STR);
        }
        Query query = entityManager.createQuery(queryString.toString());
        query.setParameter("contract", contract);
        query.setParameter("completedStatus", ActStatus.COMPLETED);
        if (!onlyCompleted) {
            query.setParameter("errorStatus", ActStatus.ERROR);
        }
        if (scopeEnum != null) {
            query.setParameter("scopeCode", scopeEnum);
        }
        if (nbOfActs != -1) {
            query.setMaxResults(nbOfActs);
        }
        query.setHint(CACHEABLE_OPTION, "true");
        return new LinkedHashSet<Act>(query.getResultList());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Act> findRunningActsByContract(Contract contract) {
        Query query = entityManager.createQuery("SELECT a FROM "
                + getEntityClass().getName() + " a"
                + " WHERE a.contract = :contract AND a.status = :runningStatus");
        query.setParameter("contract", contract);
        query.setParameter("runningStatus", ActStatus.RUNNING);
        query.setHint(CACHEABLE_OPTION, "true");
        return query.getResultList();
    }

    @Override
    public Act findActFromWebresource(Long webResourceId) {
        Query query = entityManager.createQuery("SELECT a FROM "
                + getEntityClass().getName() + " a"
                + " WHERE a.webResource.id = :webResourceId");
        query.setParameter("webResourceId", webResourceId);
        query.setHint(CACHEABLE_OPTION, "true");
        return (Act)query.getSingleResult();
    }

    @Override
    public int findNumberOfActByPeriodAndIp(Contract contract, Date limitDate, String ip) {
        Query query = entityManager.createQuery("SELECT count(a.id) FROM "
                + getEntityClass().getName() + " a"
                + " left join a.webResource w"
                + " left join w.audit au"
                + " WHERE a.clientIp = :clientIp"
                + " AND a.contract = :contract"
                + " AND (a.status = :completedStatus OR a.status = :errorStatus)"
                + " AND a.endDate > :limitDate"
                + " AND au.status = :auditStatus");
        query.setParameter("clientIp", ip);
        query.setParameter("contract", contract);
        query.setParameter("completedStatus", ActStatus.COMPLETED);
        query.setParameter("errorStatus", ActStatus.ERROR);
        query.setParameter("auditStatus", AuditStatus.COMPLETED);
        query.setParameter("limitDate", limitDate, TemporalType.TIMESTAMP);
        return ((Long)query.getSingleResult()).intValue();
    }

}