package com.adex.sdk.entity.i18n.service;

import com.adex.sdk.entity.Entity;
import com.adex.sdk.entity.i18n.Language;
import com.adex.sdk.entity.i18n.dao.GenericI18nDAO;
import com.adex.sdk.entity.service.GenericDataService;
import java.io.Serializable;

/**
 * 
 * @param <E>
 * @param <K>
 * @author ADEX
 * @version 1.0.0
 */
public interface GenericI18nService<E extends Entity, K extends Serializable>
		extends GenericDataService<E, K> {

	/**
	 * 
	 * @param language
	 *            the language of the internationalized entity to find
	 * @param entity
	 *            the entity to be internationalized
	 */
	void internationalize(Language language, E entity);

	/**
	 * 
	 * @param i18nDao
	 *            the internationalization DAO to set
	 */
	void setI18nDao(GenericI18nDAO<E, K> i18nDao);
}
