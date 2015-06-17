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
package org.tanaguru.entity.dao.audit;

import javax.persistence.Query;

import org.tanaguru.entity.audit.EvidenceImpl;
import org.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.Collection;
import org.tanaguru.entity.audit.Evidence;

/**
 * 
 * @author jkowalczyk
 */
public class EvidenceDAOImpl extends AbstractJPADAO<Evidence, Long>
        implements EvidenceDAO {

    public EvidenceDAOImpl() {
        super();
    }

    @Override
    protected Class<EvidenceImpl> getEntityClass() {
        return EvidenceImpl.class;
    }

    @Override
    public Collection<Evidence> retrieveAllByCode(String code) {
        Query query = entityManager.createQuery("SELECT n FROM "
                + getEntityClass().getName() + " n WHERE n.code = :code");
        query.setParameter("code", code);
        return query.getResultList();
    }

    @Override
    public Evidence retrieveByCode(String code) {
        Query query = entityManager.createQuery("SELECT n FROM "
                + getEntityClass().getName() + " n" + " WHERE n.code = :code");
        query.setParameter("code", code);
        return (Evidence) query.getSingleResult();
    }
}
