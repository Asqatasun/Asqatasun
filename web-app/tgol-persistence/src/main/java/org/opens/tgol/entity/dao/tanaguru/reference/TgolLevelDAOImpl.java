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
package org.opens.tgol.entity.dao.tanaguru.reference;

import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.opens.tanaguru.entity.reference.Level;
import org.opens.tanaguru.entity.reference.LevelImpl;

/**
 * This class implements the TgolLevelDAO interface.
 * @author jkowalczyk
 */
public class TgolLevelDAOImpl extends AbstractJPADAO<Level, Long>
        implements TgolLevelDAO {

    /**
     * Default constructor
     */
    public TgolLevelDAOImpl() {
        super();
    }

    @Override
    protected Class<? extends Level> getEntityClass() {
        return LevelImpl.class;
    }

    @Override
    public Level retrieveLevelByCode(String code) {
        Query query = entityManager.createQuery("SELECT l FROM "
                    + getEntityClass().getName() + " l"
                    + " WHERE l.code = :code");
        query.setParameter("code", code);
        query.setHint("org.hibernate.cacheable", "true");
        try {
            return (Level)query.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

}