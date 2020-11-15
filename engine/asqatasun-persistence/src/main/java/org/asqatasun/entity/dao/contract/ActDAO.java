/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.entity.dao.contract;

import java.util.*;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.asqatasun.entity.contract.ScopeEnum;
import org.asqatasun.entity.audit.AuditStatus;
import org.asqatasun.entity.dao.AbstractJPADAO;
import org.asqatasun.entity.contract.*;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jkowalczyk
 */
@Repository("actDAO")
public class ActDAO extends AbstractJPADAO<Act, Long> {

    private static final String CACHEABLE_OPTION="org.hibernate.cacheable";
    private static final String DESC_STR = " DESC ";
    private static final String ASC_STR = " ASC ";
    
    public ActDAO(){
        super();
    }

    @Override
    protected Class<? extends Act> getEntityClass() {
        return Act.class;
    }

    public int findNumberOfAct(Contract contract) {
        Query query = entityManager.createQuery("SELECT COUNT(a.id) FROM "
                + getEntityClass().getName() + " a "
                + " WHERE a.contract = :contract");
        query.setParameter("contract", contract);
        query.setHint(CACHEABLE_OPTION, "true");
        return ((Long)query.getSingleResult()).intValue();
    }

    @SuppressWarnings("unchecked")
    public Collection<Act> findAllActsByContract(Contract contract) {
        Query query = entityManager.createQuery("SELECT a FROM "
                + getEntityClass().getName() + " a"
                + " WHERE a.contract = :contract");
        query.setHint(CACHEABLE_OPTION, "true");
        query.setParameter("contract", contract);
        return (List<Act>)query.getResultList();
    }

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

    @SuppressWarnings("unchecked")
    public Collection<Act> findRunningActsByContract(Contract contract) {
        Query query = entityManager.createQuery("SELECT a FROM "
                + getEntityClass().getName() + " a"
                + " WHERE a.contract = :contract AND a.status = :runningStatus");
        query.setParameter("contract", contract);
        query.setParameter("runningStatus", ActStatus.RUNNING);
        return query.getResultList();
    }

    public Act findActFromAudit(Long auditId) {
        Query query = entityManager.createQuery("SELECT a FROM "
                + getEntityClass().getName() + " a"
                + " WHERE a.audit.id = :auditId");
        query.setParameter("auditId", auditId);
        return (Act)query.getSingleResult();
    }

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

}
