package org.opens.tanaguru.entity.dao.reference;

import javax.persistence.Query;

import org.opens.tanaguru.entity.reference.StandardMessage;
import org.opens.tanaguru.entity.reference.StandardMessageImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.Collection;

/**
 * 
 * @author ADEX
 */
public class StandardMessageDAOImpl extends
		AbstractJPADAO<StandardMessage, Long> implements StandardMessageDAO {

	public StandardMessageDAOImpl() {
		super();
	}

	public StandardMessage findByCode(String code) {
		Query query = entityManager.createQuery("SELECT s FROM "
				+ getEntityClass().getName() + " s" + " WHERE s.code = :code");
		query.setParameter("code", code);
		return (StandardMessage) query.getSingleResult();
	}

	@Override
	protected Class<? extends StandardMessage> getEntityClass() {
		return StandardMessageImpl.class;
	}

	public Collection<StandardMessage> retrieveAllByCodeAndText(String code,
			String text) {
		Query query = entityManager.createQuery("SELECT r FROM "
				+ getEntityClass().getName()
				+ " r WHERE r.code = :code AND r.text = :text");
		query.setParameter("code", code);
		query.setParameter("text", text);
		return query.getResultList();
	}
}
