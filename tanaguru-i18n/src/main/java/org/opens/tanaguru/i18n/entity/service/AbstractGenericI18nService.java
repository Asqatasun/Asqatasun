package org.opens.tanaguru.i18n.entity.service;

import com.adex.sdk.entity.i18n.service.GenericI18nService;
import com.adex.sdk.entity.Entity;
import com.adex.sdk.entity.i18n.InternationalizedEntity;
import com.adex.sdk.entity.i18n.Language;
import com.adex.sdk.entity.i18n.dao.GenericI18nDAO;
import com.adex.sdk.entity.service.AbstractGenericDataService;
import java.io.Serializable;

/**
 * 
 * @param <E>
 * @param <K>
 * @author ADEX
 */
public abstract class AbstractGenericI18nService<E extends Entity, K extends Serializable>
		extends AbstractGenericDataService<E, K> implements
		GenericI18nService<E, K> {

	protected GenericI18nDAO<E, K> i18nDao;

	public AbstractGenericI18nService() {
		super();
	}

	public void internationalize(Language language, E entity) {
		InternationalizedEntity<? extends E> i18nContent = i18nDao.find(
				language, entity);
		if (i18nContent != null) {
			mergeI18nContent(entity, i18nContent);
		}
	}

	protected abstract void mergeI18nContent(E entity,
			InternationalizedEntity<? extends E> i18nContent);

	public void setI18nDao(GenericI18nDAO<E, K> i18nDao) {
		this.i18nDao = i18nDao;
	}
}
