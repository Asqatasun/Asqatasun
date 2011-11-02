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
package org.opens.tanaguru.entity.dao.reference;

import javax.persistence.Query;

import org.opens.tanaguru.entity.reference.Nomenclature;
import org.opens.tanaguru.entity.reference.NomenclatureImpl;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.Collection;

/**
 * 
 * @author jkowalczyk
 */
public class NomenclatureDAOImpl extends AbstractJPADAO<Nomenclature, Long>
        implements NomenclatureDAO {

    public NomenclatureDAOImpl() {
        super();
    }

    @Override
    protected Class<NomenclatureImpl> getEntityClass() {
        return NomenclatureImpl.class;
    }

    @Override
    public Collection<Nomenclature> retrieveAllByCode(String code) {
        Query query = entityManager.createQuery("SELECT n FROM "
                + getEntityClass().getName() + " n WHERE n.code = :code");
        query.setParameter("code", code);
        return query.getResultList();
    }

    @Override
    public Nomenclature retrieveByCode(String code) {
        Query query = entityManager.createQuery("SELECT n FROM "
                + getEntityClass().getName() + " n" + " WHERE n.code = :code");
        query.setParameter("code", code);
        return (Nomenclature) query.getSingleResult();
    }

}