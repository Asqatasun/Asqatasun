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
package org.tanaguru.sdk.entity.dao;

import java.io.Serializable;
import java.util.Collection;
import org.tanaguru.sdk.entity.Entity;

/**
 * 
 * @author jkowalczyk
 */
public interface GenericDAO<E extends Entity, K extends Serializable> {

    /**
     *
     * @param entity
     *            the entity to create
     */
    void create(E entity);

    /**
     *
     * @param entity
     *            the entity to delete
     */
    void delete(E entity);

    /**
     *
     * @param key
     *            the prmiary key of the entity to delete
     */
    void delete(K key);

    /**
     * 
     * @param entitySet
     */
    void delete(Collection<E> entitySet);

    /**
     *
     * @return the entity list found
     */
    Collection<E> findAll();

    /**
     *
     * @param key
     *            the key of the entity to read
     * @return the entity read
     */
    E read(K key);

    /**
     *
     * @param entity
     *            the entity to refresh
     */
    void refresh(E entity);

    /**
     * Creates the object in the database if the ID is null. Updates the object
     * in the database if the ID is not null.
     *
     * @param entity
     * @return the object after the action.
     */
    E saveOrUpdate(E entity);

    /**
     * 
     * @param entitySet
     * @return
     */
    Collection<E> saveOrUpdate(Collection<E> entitySet);

    /**
     *
     * @param entity
     *            the entity to update
     * @return the entity updated
     */
    E update(E entity);
}
