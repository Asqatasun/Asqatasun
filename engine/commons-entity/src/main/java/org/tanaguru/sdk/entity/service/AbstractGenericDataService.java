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
package org.tanaguru.sdk.entity.service;

import java.io.Serializable;
import java.util.Collection;
import org.tanaguru.sdk.entity.Entity;
import org.tanaguru.sdk.entity.dao.GenericDAO;
import org.tanaguru.sdk.entity.factory.GenericFactory;

/**
 * 
 * @param <E>
 * @param <K>
 * @author jkowalczyk
 */
public abstract class AbstractGenericDataService<E extends Entity, K extends Serializable>
        implements GenericDataService<E, K> {

    protected GenericDAO<E, K> entityDao;
    protected GenericFactory<E> entityFactory;

    public AbstractGenericDataService() {
        super();
    }

    /**
     *
     * @return the entity created
     */
    @Override
    public E create() {
        return entityFactory.create();
    }

    /**
     *
     * @param entity
     *            the entity to create
     */
    @Override
    public void create(E entity) {
        entityDao.create(entity);
    }

    /**
     *
     * @param entity
     *            the entity to delete
     */
    @Override
    public void delete(E entity) {
        entityDao.delete(entity);
    }

    @Override
    public void delete(K key) {
        entityDao.delete(key);
    }

    @Override
    public void delete(Collection<E> entitySet) {
        entityDao.delete(entitySet);
    }

    @Override
    public Collection<E> findAll() {
        return entityDao.findAll();
    }

    /**
     *
     * @param key
     *            the key of the entity to read
     * @return the entity read
     */
    @Override
    public E read(K key) {
        return entityDao.read(key);
    }

    /**
     * 
     * @param entity
     * @return
     */
    @Override
    public E saveOrUpdate(E entity) {
        return entityDao.saveOrUpdate(entity);
    }

    /**
     * 
     * @param entitySet
     * @return
     */
    @Override
    public Collection<E> saveOrUpdate(Collection<E> entitySet) {
        return entityDao.saveOrUpdate(entitySet);
    }

    /**
     *
     * @param entityDao
     *            the entity DAO to set
     */
    @Override
    public void setEntityDao(GenericDAO<E, K> entityDao) {
        this.entityDao = entityDao;
    }

    /**
     *
     * @param entityFactory
     *            the entity factory to set
     */
    @Override
    public void setEntityFactory(GenericFactory<E> entityFactory) {
        this.entityFactory = entityFactory;
    }

    /**
     *
     * @param entity
     *            the entity to update
     * @return the entity updated
     */
    @Override
    public E update(E entity) {
        return entityDao.update(entity);
    }
}
