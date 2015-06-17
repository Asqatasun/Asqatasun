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
package org.tanaguru.entity.dao.parameterization;

import org.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import org.tanaguru.entity.parameterization.ParameterElement;
import org.tanaguru.entity.parameterization.ParameterElementImpl;

/**
 *
 * @author jkowalczyk
 */
public class ParameterElementDAOImpl extends AbstractJPADAO<ParameterElement, Long> implements
        ParameterElementDAO {

    @Override
    protected Class<? extends ParameterElement> getEntityClass() {
        return ParameterElementImpl.class;
    }

    @Override
    public ParameterElement findParameterElementFromCode(String parameterElementCode) {
        Query query = entityManager.createQuery("SELECT pe FROM "
                + getEntityClass().getName() + " pe"
                + " WHERE pe.parameterElementCode = :parameterElementCode");
        query.setParameter("parameterElementCode", parameterElementCode);
        try {
            return (ParameterElement) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } catch (NonUniqueResultException nure) {
            return null;
        }
    }

}