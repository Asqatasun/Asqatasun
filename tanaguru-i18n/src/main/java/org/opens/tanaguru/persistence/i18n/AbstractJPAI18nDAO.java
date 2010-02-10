package org.opens.tanaguru.persistence.i18n;

import com.adex.sdk.entity.i18n.dao.GenericI18nDAO;
import com.adex.sdk.entity.Entity;
import com.adex.sdk.entity.i18n.InternationalizedEntity;
import com.adex.sdk.entity.i18n.Language;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.io.Serializable;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * 
 * @author ADEX
 */
public abstract class AbstractJPAI18nDAO<E extends Entity, K extends Serializable>
		extends AbstractJPADAO<InternationalizedEntity<E>, K> implements
		GenericI18nDAO<E, K> {

	public AbstractJPAI18nDAO() {
		super();
	}

	public InternationalizedEntity<E> find(Language language, E entity) {
		try {
			Query query = entityManager.createQuery("SELECT o FROM "
					+ getEntityClass().getName()
					+ " o WHERE o.target = :target AND o.language = :language");
			query.setParameter("target", entity);
			query.setParameter("language", language);
			return (InternationalizedEntity<E>) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
}
