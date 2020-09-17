/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.asqatasun.entity.dao.parameterization;

import java.util.*;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.audit.AuditImpl;
import org.asqatasun.entity.contract.Act;
import org.asqatasun.entity.contract.ActStatus;
import org.asqatasun.entity.contract.ScopeEnum;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.parameterization.ParameterElement;
import org.asqatasun.entity.parameterization.ParameterFamily;
import org.asqatasun.entity.parameterization.ParameterImpl;
import org.asqatasun.entity.dao.AbstractJPADAO;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jkowalczyk
 */
@Repository("parameterDAO")
public class ParameterDAOImpl extends AbstractJPADAO<Parameter, Long> implements
        ParameterDAO {

    private static final String CACHEABLE_OPTION="org.hibernate.cacheable";

    @Override
    protected Class<? extends Parameter> getEntityClass() {
        return ParameterImpl.class;
    }

    @Override
    public Set<Parameter> findParameterSet(ParameterFamily parameterFamily, Audit audit) {
        return findParametersFromParameterFamily(parameterFamily, findParameterSetFromAudit(audit));
    }

    /**
     * The database has to contain only one default parameter value for a given
     * parameter element. The control is made on application-side. The first
     * encountered default value is considered as the default value.
     *
     * @return
     *      the default parameter set.
     */
    @Override
    public Set<Parameter> findDefaultParameterSet() {
        Set<Parameter> paramSet = new LinkedHashSet<>();
        Set<String> paramElementSet = new HashSet<>();
        Query query = entityManager.createQuery("SELECT p FROM "
                + getEntityClass().getName() + " p"
                + " WHERE p.isDefaultParameterValue = :isDefault");
        query.setParameter("isDefault", true);
        try {
            for (Parameter parameter : (List<Parameter>)query.getResultList()) {
                String paramElement = parameter.getParameterElement().getParameterElementCode();
                if (!paramElementSet.contains(paramElement)) {
                    paramSet.add(parameter);
                }
                paramElementSet.add(paramElement);
            }
            return paramSet;
        } catch (NoResultException nre) {
            return paramSet;
        }
    }

    @Override
    public Set<Parameter> findParameterSetFromAudit(Audit audit) {
        if (audit != null) {
            Query query = entityManager.createQuery("SELECT a FROM "
                    + AuditImpl.class.getName() + " a"
                    + " WHERE a.id = :idAudit");
            query.setParameter("idAudit", audit.getId());
            try {
                audit = (Audit)(query.getSingleResult());
                Set<Parameter> paramSet = new HashSet<Parameter>();
                paramSet.addAll(audit.getParameterSet());
                return paramSet;
            } catch (NoResultException nre) {
                return null;
            }
        }
        return null;
    }

    @Override
    public Parameter findParameter(ParameterElement parameterElement, String parameterValue) {
        Query query = entityManager.createQuery("SELECT p FROM "
                + getEntityClass().getName() + " p"
                + " WHERE p.parameterElement = :parameterElement"
                + " AND p.parameterValue = :parameterValue");
        query.setParameter("parameterElement", parameterElement);
        query.setParameter("parameterValue", parameterValue);
        try {
            return (Parameter)(query.getSingleResult());
        } catch (NoResultException nre) {
            return null;
        }
    }
    
    @Override
    public Parameter findLevelParameter(String parameterValue) {
        Query query = entityManager.createQuery("SELECT p FROM "
                + getEntityClass().getName() + " p"
                + " WHERE p.parameterElement.parameterElementCode = :parameterElementCode"
                + " AND p.parameterValue = :parameterValue");
        query.setParameter("parameterValue", parameterValue);
        query.setParameter("parameterElementCode", "LEVEL");
        try {
            return (Parameter)(query.getSingleResult());
        } catch (NoResultException nre) {
            return null;
        }
    }
    
    @Override
    @Transactional
    public Parameter findParameter(Audit audit, String parameterElementCode) {
        for (Parameter param : findParameterSetFromAudit(audit)) {
            if (param.getParameterElement().getParameterElementCode().equals(parameterElementCode)) {
                return param;
            }
        }
        return null;
    }

    @Override
    public Parameter findDefaultParameter(ParameterElement parameterElement) {
        Query query = entityManager.createQuery("SELECT p FROM "
                + getEntityClass().getName() + " p"
                + " WHERE p.isDefaultParameterValue = :isDefault"
                + " AND p.parameterElement = :parameterElement");
        query.setParameter("isDefault", true);
        query.setParameter("parameterElement", parameterElement);
        try {
            return (Parameter)query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } catch (NonUniqueResultException nure) {
            return (Parameter)query.getResultList().iterator().next();
        }
    }
    
    @Override
    public Set<Parameter> findParametersFromParameterFamily(
            ParameterFamily parameterFamily, 
            Collection<Parameter> globalParamSet) {
        Set<Parameter> paramSet = new HashSet<>();
        for (Parameter param : globalParamSet) {
            if (param.getParameterElement().getParameterFamily().equals(parameterFamily)) {
                paramSet.add(param);
            }
        }
        return paramSet;
    }

    public String findLastParameterValueFromUser(
        Long contract,
        ParameterElement parameterElement,
        ScopeEnum scope) {
        Act act = findLastActByContract(contract, scope);
        if (act == null) {
            return null;
        }
        return retrieveParameterValueFromParameterElementAndAct(parameterElement, act);
    }

    public String findLastParameterValueFromContractAndScenario(
        Long contract,
        ParameterElement parameterElement,
        String scenarioName) {
        Act act = findLastActByContractAndScenario(contract, scenarioName);
        if (act == null) {
            return null;
        }
        return retrieveParameterValueFromParameterElementAndAct(parameterElement, act);
    }

    /**
     *
     * @param parameterElement
     * @param act
     * @return
     */
    private String retrieveParameterValueFromParameterElementAndAct(
        ParameterElement parameterElement,
        Act act) {
        StringBuilder queryString = new StringBuilder();
        queryString.append("SELECT distinct(p.Parameter_Value) FROM");
        queryString.append(" ACT as a,");
        queryString.append(" ACT_AUDIT as taa,");
        queryString.append(" AUDIT_PARAMETER as ap, ");
        queryString.append(" PARAMETER as p, ");
        queryString.append(" PARAMETER_ELEMENT as pe ");
        queryString.append(" WHERE a.Id_Act=:idAct");
        queryString.append(" AND a.Id_Act=taa.ACT_Id_Act");
        queryString.append(" AND taa.AUDIT_Id_Audit=ap.Id_Audit");
        queryString.append(" AND ap.Id_Parameter=p.Id_Parameter");
        queryString.append(" AND p.Id_Parameter_Element=:idParameterElement");
        Query query = entityManager.createNativeQuery(queryString.toString());
        query.setParameter("idParameterElement", parameterElement.getId());
        query.setParameter("idAct", act.getId());
        try {
            return (query.getSingleResult()).toString();
        } catch (NoResultException e) {
            return null;
        }
    }

    private Act findLastActByContractAndScenario(Long idContract, String scenarioName) {
        StringBuilder queryString = new StringBuilder();
        queryString.append("SELECT a FROM ");
        queryString.append(Act.class.getName());
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

     private Act findLastActByContract(Long idContract, ScopeEnum scope) {
        Query query = entityManager.createQuery("SELECT a FROM "
            + Act.class.getName() + " a"
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

}
