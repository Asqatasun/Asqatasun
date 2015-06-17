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
package org.tanaguru.sdk.entity.dao.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.tanaguru.sdk.entity.Entity;
import org.tanaguru.sdk.entity.dao.GenericDAO;

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
        flushAndCloseEntityManager();
    }

    /**
     * If the ID of the object is null the delete action is skipped.
     *
     * @param entity
     */
    @Override
    public void delete(E entity) {
        if (entity.getId() == null) {
            return;
        }
        flushAndCloseEntityManager();
    }

    /**
     * If the key is null the delete action is skipped.
     *
     * @param key
     */
    @Override
    public void delete(K key) {
        if (key == null) {
            return;
        }

        Query query = entityManager.createQuery("DELETE FROM " + getEntityClass().getName() + " o WHERE o.id = :id");
        query.setParameter("id", key);
        query.executeUpdate();
    }

    @Override
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
    public E update(E entity) {
        E result = entityManager.merge(entity);
        flushAndCloseEntityManager();
        return result;
    }

    /**
     * Due to memory leaks, the entity manager has to be flushed and closed after
     * each db operation. All the elements retrieved while the db access keep
     * a reference to the entity manager and can never be garbaged.
     * By flushing and closing the entity manager, these objects can be free.
     */
    private void flushAndCloseEntityManager(){
        entityManager.flush();
        entityManager.close();
    }
}
