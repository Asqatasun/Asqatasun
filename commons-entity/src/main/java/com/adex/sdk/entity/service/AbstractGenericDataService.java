package com.adex.sdk.entity.service;

import com.adex.sdk.entity.Entity;
import java.io.Serializable;
import java.util.Collection;

import com.adex.sdk.entity.factory.GenericFactory;
import com.adex.sdk.entity.dao.GenericDAO;
import java.util.Set;

/**
 * 
 * @param <E>
 * @param <K>
 * @author ADEX
 * @version 1.0.0
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
	public E create() {
		return entityFactory.create();
	}

	/**
	 * 
	 * @param entity
	 *            the entity to create
	 */
	public void create(E entity) {
		entityDao.create(entity);
	}

	/**
	 * 
	 * @param entity
	 *            the entity to delete
	 */
	public void delete(E entity) {
		entityDao.delete(entity);
	}

	public void delete(K key) {
		entityDao.delete(key);
	}

	public void delete(Set<E> entitySet) {
		entityDao.delete(entitySet);
	}

	public Collection<? extends E> findAll() {
		return entityDao.findAll();
	}

	/**
	 * 
	 * @param key
	 *            the key of the entity to read
	 * @return the entity read
	 */
	public E read(K key) {
		return entityDao.read(key);
	}

	public E saveOrUpdate(E entity) {
		return entityDao.saveOrUpdate(entity);
	}

	public Set<E> saveOrUpdate(Set<E> entitySet) {
		return entityDao.saveOrUpdate(entitySet);
	}

	/**
	 * 
	 * @param entityDao
	 *            the entity DAO to set
	 */
	public void setEntityDao(GenericDAO<E, K> entityDao) {
		this.entityDao = entityDao;
	}

	/**
	 * 
	 * @param entityFactory
	 *            the entity factory to set
	 */
	public void setEntityFactory(GenericFactory<E> entityFactory) {
		this.entityFactory = entityFactory;
	}

	/**
	 * 
	 * @param entity
	 *            the entity to update
	 * @return the entity updated
	 */
	public E update(E entity) {
		return entityDao.update(entity);
	}
}
