package org.opens.tanaguru.entity.dao.reference;

import org.opens.tanaguru.entity.reference.Reference;
import org.opens.tanaguru.entity.reference.ReferenceImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.Collection;
import javax.persistence.Query;

public class ReferenceDAOImpl extends AbstractJPADAO<Reference, Long> implements
		ReferenceDAO {

	public ReferenceDAOImpl() {
		super();
	}

	@Override
	protected Class<ReferenceImpl> getEntityClass() {
		return ReferenceImpl.class;
	}

	public Collection<Reference> retrieveAllByCode(String code) {
		Query query = entityManager.createQuery("SELECT r FROM "
				+ getEntityClass().getName() + " r WHERE r.code = :code");
		query.setParameter("code", code);
		return query.getResultList();
	}
}
