package com.adex.sdk.entity.factory;

/**
 * 
 * @param <E>
 * @author ADEX
 * @version 1.0.0
 */
public interface GenericFactory<E> {

	/**
	 * 
	 * @return the entity created
	 */
	E create();
}
