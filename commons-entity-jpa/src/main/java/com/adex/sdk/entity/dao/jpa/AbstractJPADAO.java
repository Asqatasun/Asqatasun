package com.adex.sdk.entity.dao.jpa;

import com.adex.sdk.entity.dao.GenericDAO;
import com.adex.sdk.entity.Entity;
import java.io.Serializable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class AbstractJPADAO<E extends Entity, K extends Serializable>
        implements GenericDAO<E, K> {

    @PersistenceContext
    protected EntityManager entityManager;

    public AbstractJPADAO() {
        super();
    }

    public void create(E entity) {
        entityManager.persist(entity);
    }

    /**
     * If the ID of the object is null the delete action is skipped.
     *
     * @param entity
     */
    public void delete(E entity) {
        if (entity.getId() == null) {
            return;
        }

        entityManager.refresh(entity);
        entityManager.remove(entity);
    }

    /**
     * If the key is null the delete action is skipped.
     *
     * @param key
     */
    public void delete(K key) {
        if (key == null) {
            return;
        }

        Query query = entityManager.createQuery("DELETE FROM " + getEntityClass().getName() + " o WHERE o.id = :id");
        query.setParameter("id", key);
        query.executeUpdate();
    }

    public void delete(Set<E> entitySet) {
        for (E entity : entitySet) {
            delete(entity);
        }
    }

    public List<E> findAll() {
        Query query = entityManager.createQuery("SELECT o FROM " + getEntityClass().getName() + " o");
        return query.getResultList();
    }

    protected abstract Class<? extends E> getEntityClass();

    public E read(K key) {
        return (E) entityManager.find(getEntityClass(), key);
    }

    public void refresh(E entity) {
        entityManager.refresh(this);
    }

    public E saveOrUpdate(E entity) {
        if (entity.getId() == null) {
            create(entity);
        } else {
            entity = update(entity);
        }
        return entity;
    }

    public Set<E> saveOrUpdate(Set<E> entitySet) {
        Set<E> resultSet = new HashSet<E>();
        for (E entity : entitySet) {
            resultSet.add(saveOrUpdate(entity));
        }
        return resultSet;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public E update(E entity) {
        return entityManager.merge(entity);
    }
}
