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
package org.asqatasun.entity.dao.parameterization;

import org.asqatasun.entity.dao.AbstractJPADAO;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import org.asqatasun.entity.parameterization.ParameterElement;
import org.asqatasun.entity.parameterization.ParameterElementImpl;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jkowalczyk
 */
@Repository("parameterElementDAO")
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
