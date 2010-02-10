package com.adex.sdk.entity.dao;

import com.adex.sdk.entity.Entity;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * 
 * @author ADEX
 * @version 1.0.0
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

	void delete(Set<E> entitySet);

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

	Set<E> saveOrUpdate(Set<E> entitySet);

	/**
	 * 
	 * @param entity
	 *            the entity to update
	 * @return the entity updated
	 */
	E update(E entity);
}
