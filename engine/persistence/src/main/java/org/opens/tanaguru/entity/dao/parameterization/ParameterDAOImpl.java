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
package org.opens.tanaguru.entity.dao.parameterization;

import java.util.*;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditImpl;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.parameterization.ParameterFamily;
import org.opens.tanaguru.entity.parameterization.ParameterImpl;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;

/**
 *
 * @author jkowalczyk
 */
public class ParameterDAOImpl extends AbstractJPADAO<Parameter, Long> implements
        ParameterDAO {

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
        Set<Parameter> paramSet = new LinkedHashSet<Parameter>();
        Set<String> paramElementSet = new HashSet<String>();
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
        Set<Parameter> paramSet = new HashSet<Parameter>();
        for (Parameter param : globalParamSet) {
            if (param.getParameterElement().getParameterFamily().equals(parameterFamily)) {
                paramSet.add(param);
            }
        }
        return paramSet;
    }

}