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
package org.tanaguru.webapp.entity.dao.contract;

import java.util.*;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.tanaguru.entity.audit.AuditStatus;
import org.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.tanaguru.webapp.entity.contract.*;

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
        return query.getResultList();
    }

    @Override
    public Act findActFromAudit(Long auditId) {
        Query query = entityManager.createQuery("SELECT a FROM "
                + getEntityClass().getName() + " a"
                + " WHERE a.audit.id = :auditId");
        query.setParameter("auditId", auditId);
        return (Act)query.getSingleResult();
    }

    @Override
    public int findNumberOfActByPeriodAndIp(Contract contract, Date limitDate, String ip) {
        Query query = entityManager.createQuery("SELECT count(a.id) FROM "
                + getEntityClass().getName() + " a"
                + " left join a.audit au"
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

    @Override
    public int findNumberOfActByScope(Contract contract, Collection<ScopeEnum> scopes) {
        StringBuilder strb = new StringBuilder();
        strb.append("SELECT count(a.id) FROM ");
        strb.append(getEntityClass().getName());
        strb.append(" a");
        strb.append(" left join a.audit au");
        strb.append(" WHERE a.contract = :contract");
        strb.append(" AND a.status = :completedStatus");
        strb.append(" AND au.status = :auditStatus");
        strb.append(" AND (");
        int index=0;
        Iterator<ScopeEnum> iter = scopes.iterator();
        while (iter.hasNext()) {
            strb.append("a.scope.code = :auditScope");
            strb.append(index);
            iter.next();
            index++;
            if (iter.hasNext()) {
                strb.append(" OR ");
            }
        }
        strb.append(")");
        Query query = entityManager.createQuery(strb.toString());
        query.setParameter("contract", contract);
        query.setParameter("completedStatus", ActStatus.COMPLETED);
        query.setParameter("auditStatus", AuditStatus.COMPLETED);
        iter = scopes.iterator();
        index=0;
        while (iter.hasNext()) {
            query.setParameter("auditScope"+index, iter.next());
            index++;
        }
        return ((Long)query.getSingleResult()).intValue();
    }

    @Override
    public Act findLastActByContract(Long idContract, ScopeEnum scope) {
        Query query = entityManager.createQuery("SELECT a FROM "
                + ActImpl.class.getName() + " a"
                + " WHERE a.contract.id = :idContract"
                + " AND (a.status = :completedStatus OR a.status = :errorStatus)"
                + " AND a.scope.code =:scope "
                + " ORDER BY a.id DESC");
        query.setParameter("idContract", idContract);
        query.setParameter("completedStatus", ActStatus.COMPLETED);
        query.setParameter("errorStatus", ActStatus.ERROR);
        query.setParameter("scope", scope);
        query.setMaxResults(1);
        query.setHint(CACHEABLE_OPTION, "true");
        try {
            return (Act)query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
    
    @Override
    public Act findLastActByContractAndScenario(Long idContract, String scenarioName) {
        StringBuilder queryString = new StringBuilder();
        queryString.append("SELECT a FROM ");
        queryString.append(ActImpl.class.getName());
        queryString.append(" a");
        queryString.append(" left join a.audit.subject w");
        queryString.append(" WHERE a.contract.id = :idContract");
        queryString.append(" AND (a.status = :completedStatus OR a.status = :errorStatus)");
        queryString.append(" AND a.scope.code =:scope ");
        queryString.append(" AND w.url=:scenarioName");
        queryString.append(" ORDER BY a.id DESC");
        
        Query query = entityManager.createQuery(queryString.toString());
        query.setParameter("idContract", idContract);
        query.setParameter("completedStatus", ActStatus.COMPLETED);
        query.setParameter("errorStatus", ActStatus.ERROR);
        query.setParameter("scope", ScopeEnum.SCENARIO);
        query.setParameter("scenarioName", scenarioName);
        query.setMaxResults(1);
        query.setHint(CACHEABLE_OPTION, "true");
        try {
            return (Act)query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}