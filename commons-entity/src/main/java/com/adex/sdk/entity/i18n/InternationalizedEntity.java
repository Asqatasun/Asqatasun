package com.adex.sdk.entity.i18n;

import com.adex.sdk.entity.Entity;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 * @param <E>
 */
public interface InternationalizedEntity<E> extends Internationalized, Entity {

	/**
	 * 
	 * @return the target
	 */
	E getTarget();

	/**
	 * 
	 * @param target
	 *            the target to set
	 */
	void setTarget(E target);
}
