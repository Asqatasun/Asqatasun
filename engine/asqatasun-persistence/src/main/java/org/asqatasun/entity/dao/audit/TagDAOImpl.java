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
package org.asqatasun.entity.dao.audit;

import org.asqatasun.entity.audit.*;
import org.asqatasun.entity.dao.AbstractJPADAO;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

/**
 * 
 * @author jkowalczyk
 */
@Repository("tagDAO")
public class TagDAOImpl extends AbstractJPADAO<Tag, Long> implements
        TagDAO {

    public TagDAOImpl() {
        super();
    }

    @Override
    protected Class<TagImpl> getEntityClass() {
        return TagImpl.class;
    }

    @Override
    public List<Tag> retrieveAllByValues(List<String> values) {
        Query query = entityManager.createQuery("SELECT t FROM "
            + getEntityClass().getName() + " t"
            + " WHERE t.value in :values");
        query.setParameter("values", values);
        return (List) query.getResultList();
    }

    @Override
    public Tag retrieveByValue(String value) {
        Query query = entityManager.createQuery("SELECT t FROM "
            + getEntityClass().getName() + " t"
            + " WHERE t.value = :value");
        query.setParameter("value", value);
        try {
            return (Tag) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
