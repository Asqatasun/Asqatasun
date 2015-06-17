/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
package org.tanaguru.webapp.entity.dao.tanaguru.parameterization;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.tanaguru.entity.parameterization.Parameter;
import org.tanaguru.entity.parameterization.ParameterElement;
import org.tanaguru.entity.parameterization.ParameterImpl;
import org.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.tanaguru.webapp.entity.contract.Act;
import org.tanaguru.webapp.entity.contract.ScopeEnum;
import org.tanaguru.webapp.entity.dao.contract.ActDAO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class TgolParameterDAOImpl extends AbstractJPADAO<Parameter, Long>
        implements TgolParameterDAO {

    private ActDAO actDAO;
    @Autowired
    public void setActDAO(ActDAO actDAO) {
        this.actDAO = actDAO;
    }
    
    @Override
    protected Class<? extends Parameter> getEntityClass() {
        return ParameterImpl.class;
    }

    @Override
    public String findLastParameterValueFromUser(
            Long contract,
            ParameterElement parameterElement,
            ScopeEnum scope) {
        Act act = actDAO.findLastActByContract(contract, scope);
        if (act == null) {
            return null;
        }
        return retrieveParameterValueFromParameterElementAndAct(parameterElement, act);
    }
    
    
    
    @Override
    public String findLastParameterValueFromContractAndScenario(
            Long contract,
            ParameterElement parameterElement,
            String scenarioName) {
        Act act = actDAO.findLastActByContractAndScenario(contract, scenarioName);
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
        queryString.append(" TGSI_ACT as a,");
        queryString.append(" TGSI_ACT_AUDIT as taa,");
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
    
}