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
package org.asqatasun.entity.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.asqatasun.entity.Entity;

/**
 * 
 * @author jkowalczyk
 * @param <E>
 * @param <K>
 */
public abstract class AbstractJPADAO<E extends Entity, K extends Serializable>
        implements GenericDAO<E, K> {

    @PersistenceContext
    protected EntityManager entityManager;

    public AbstractJPADAO() {
        super();
    }

    @Override
    public void create(E entity) {
        entityManager.persist(entity);
    }

    /**
     * If the ID of the object is null the delete action is skipped.
     *
     * @param entity
     */
    @Override
    @Transactional
    public void delete(E entity) {
        if (entity.getId() == null) {
            return;
        }
    }

    /**
     * If the key is null the delete action is skipped.
     *
     * @param key
     */
    @Override

    @Transactional
    public void delete(K key) {
        if (key == null) {
            return;
        }

        Query query = entityManager.createQuery("DELETE FROM " + getEntityClass().getName() + " o WHERE o.id = :id");
        query.setParameter("id", key);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public void delete(Collection<E> entitySet) {
        for (E entity : entitySet) {
            delete(entity);
        }
    }

    @Override
    public List<E> findAll() {
        Query query = entityManager.createQuery("SELECT o FROM " + getEntityClass().getName() + " o");
        return query.getResultList();
    }

    protected abstract Class<? extends E> getEntityClass();

    @Override
    public E read(K key) {
        E result = (E) entityManager.find(getEntityClass(), key);
        return result;
    }

    @Override
    public void refresh(E entity) {
        entityManager.refresh(this);
    }

    @Override
    public E saveOrUpdate(E entity) {
        if (entity.getId() == null) {
            create(entity);
        } else {
            entity = update(entity);
        }
        return entity;
    }

    @Override
    public Collection<E> saveOrUpdate(Collection<E> entitySet) {
        Collection<E> resultSet = new HashSet<E>();
        for (E entity : entitySet) {
            resultSet.add(saveOrUpdate(entity));
        }
        return resultSet;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public E update(E entity) {
        E result = entityManager.merge(entity);
        return result;
    }

}
