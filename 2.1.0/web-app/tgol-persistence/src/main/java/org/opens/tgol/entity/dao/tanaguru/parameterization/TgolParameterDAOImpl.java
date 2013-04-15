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
package org.opens.tgol.entity.dao.tanaguru.parameterization;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.parameterization.ParameterImpl;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.opens.tgol.entity.contract.Act;
import org.opens.tgol.entity.contract.ActImpl;
import org.opens.tgol.entity.contract.ActStatus;
import org.opens.tgol.entity.contract.ScopeEnum;

/**
 *
 * @author jkowalczyk
 */
public class TgolParameterDAOImpl extends AbstractJPADAO<Parameter, Long>
        implements TgolParameterDAO {

    private static final String CACHEABLE_OPTION="org.hibernate.cacheable";

    @Override
    protected Class<? extends Parameter> getEntityClass() {
        return ParameterImpl.class;
    }

    @Override
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
    
    private Act findLastActByContract(Long idContract, ScopeEnum scope) {
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
     * @param idContract
     * @param scenarioName
     * @return 
     */
    private Act findLastActByContractAndScenario(Long idContract, String scenarioName) {
        StringBuilder queryString = new StringBuilder();
        queryString.append("SELECT a FROM ");
        queryString.append(ActImpl.class.getName());
        queryString.append(" a");
        queryString.append(" left join a.webResource w");
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
        queryString.append(" TGSI_ACT as a,");
        queryString.append(" TGSI_ACT_WEB_RESOURCE as tawr,");
        queryString.append(" WEB_RESOURCE as w, ");
        queryString.append(" AUDIT_PARAMETER as ap, ");
        queryString.append(" PARAMETER as p, ");
        queryString.append(" PARAMETER_ELEMENT as pe ");
        queryString.append(" WHERE a.Id_Act=:idAct");
        queryString.append(" AND a.Id_Act=tawr.ACT_Id_Act");
        queryString.append(" AND tawr.WEB_RESOURCE_Id_Web_Resource=w.Id_Web_Resource");
        queryString.append(" AND w.Id_Audit=ap.Id_Audit");
        queryString.append(" AND ap.Id_Parameter=p.Id_Parameter");
        queryString.append(" AND p.Id_Parameter_Element=:idParameterElement");
        Query query = entityManager.createNativeQuery(queryString.toString());
        query.setParameter("idParameterElement", parameterElement.getId());
        query.setParameter("idAct", act.getId());
        try {
            return ((String)query.getSingleResult()).toString();
        } catch (NoResultException e) {
            return null;
        }
    }
    
}