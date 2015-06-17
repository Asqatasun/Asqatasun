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
package org.tanaguru.persistence.i18n;

import java.io.Serializable;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.tanaguru.sdk.entity.Entity;
import org.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.tanaguru.sdk.entity.i18n.InternationalizedEntity;
import org.tanaguru.sdk.entity.i18n.Language;
import org.tanaguru.sdk.entity.i18n.dao.GenericI18nDAO;

/**
 * 
 * @author jkowalczyk
 * @param <E>
 * @param <K>
 */
public abstract class AbstractJPAI18nDAO<E extends Entity, K extends Serializable>
        extends AbstractJPADAO<InternationalizedEntity<E>, K> implements
        GenericI18nDAO<E, K> {

    public AbstractJPAI18nDAO() {
        super();
    }

    @Override
    public InternationalizedEntity<E> find(Language language, E entity) {
        try {
            Query query = entityManager.createQuery("SELECT o FROM "
                    + getEntityClass().getName()
                    + " o WHERE o.target = :target AND o.language = :language");
            query.setParameter("target", entity);
            query.setParameter("language", language);
            return (InternationalizedEntity<E>) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

}