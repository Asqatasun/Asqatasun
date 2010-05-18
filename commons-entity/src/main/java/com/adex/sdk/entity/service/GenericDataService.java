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
public interface GenericDataService<E extends Entity, K extends Serializable> {

	/**
	 * 
	 * @return the entity created
	 */
	E create();

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
	 *            the key of the entity to delete
	 */
	void delete(K key);

	void delete(Set<E> entitySet);

	/**
	 * 
	 * @return all instancies of the entity
	 */
	Collection<? extends E> findAll();

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
	 *            the entity to update
	 * @return the entity updated
	 */
	E saveOrUpdate(E entity);

	Set<E> saveOrUpdate(Set<E> entitySet);

	/**
	 * 
	 * @param dao
	 *            the DAO to set
	 */
	void setEntityDao(GenericDAO<E, K> dao);

	/**
	 * 
	 * @param factory
	 *            the factory to set
	 */
	void setEntityFactory(GenericFactory<E> factory);

	/**
	 * 
	 * @param entity
	 *            the entity to update
	 * @return the entity updated
	 */
	E update(E entity);
}
