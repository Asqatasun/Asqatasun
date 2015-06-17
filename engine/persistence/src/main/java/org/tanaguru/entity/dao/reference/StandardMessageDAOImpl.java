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
package org.tanaguru.entity.dao.reference;

import javax.persistence.Query;

import org.tanaguru.entity.reference.StandardMessage;
import org.tanaguru.entity.reference.StandardMessageImpl;
import org.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.Collection;

/**
 * 
 * @author jkowalczyk
 */
public class StandardMessageDAOImpl
        extends AbstractJPADAO<StandardMessage, Long> implements StandardMessageDAO {

    public StandardMessageDAOImpl() {
        super();
    }

    public StandardMessage findByCode(String code) {
        Query query = entityManager.createQuery("SELECT s FROM "
                + getEntityClass().getName() + " s" + " WHERE s.code = :code");
        query.setParameter("code", code);
        return (StandardMessage) query.getSingleResult();
    }

    @Override
    protected Class<? extends StandardMessage> getEntityClass() {
        return StandardMessageImpl.class;
    }

    @Override
    public Collection<StandardMessage> retrieveAllByCodeAndText(String code,
            String text) {
        Query query = entityManager.createQuery("SELECT r FROM "
                + getEntityClass().getName()
                + " r WHERE r.code = :code AND r.text = :text");
        query.setParameter("code", code);
        query.setParameter("text", text);
        return query.getResultList();
    }

}