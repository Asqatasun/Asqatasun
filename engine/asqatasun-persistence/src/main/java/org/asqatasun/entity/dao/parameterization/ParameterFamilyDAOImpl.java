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

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import org.asqatasun.entity.parameterization.ParameterFamily;
import org.asqatasun.entity.parameterization.ParameterFamilyImpl;
import org.asqatasun.entity.dao.AbstractJPADAO;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jkowalczyk
 */
@Repository("parameterFamilyDAO")
public class ParameterFamilyDAOImpl extends AbstractJPADAO<ParameterFamily, Long> implements
        ParameterFamilyDAO {

    @Override
    protected Class<? extends ParameterFamily> getEntityClass() {
        return ParameterFamilyImpl.class;
    }

    @Override
    public ParameterFamily findParameterFamilyFromCode(String parameterFamilyCode) {
        Query query = entityManager.createQuery("SELECT pf FROM "
                + getEntityClass().getName() + " pf"
                + " WHERE pf.paramFamilyCode = :parameterFamilyCode");
        query.setParameter("parameterFamilyCode", parameterFamilyCode);
        try {
            return (ParameterFamily) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } catch (NonUniqueResultException nure) {
            return null;
        }
    }

}
