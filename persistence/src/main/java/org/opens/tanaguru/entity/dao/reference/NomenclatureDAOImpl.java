package org.opens.tanaguru.entity.dao.reference;

import javax.persistence.Query;

import org.opens.tanaguru.entity.reference.Nomenclature;
import org.opens.tanaguru.entity.reference.NomenclatureImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.Collection;

public class NomenclatureDAOImpl extends AbstractJPADAO<Nomenclature, Long>
		implements NomenclatureDAO {

	public NomenclatureDAOImpl() {
		super();
	}

	@Override
	protected Class<NomenclatureImpl> getEntityClass() {
		return NomenclatureImpl.class;
	}

	public Collection<Nomenclature> retrieveAllByCode(String code) {
		Query query = entityManager.createQuery("SELECT n FROM "
				+ getEntityClass().getName() + " n WHERE n.code = :code");
		query.setParameter("code", code);
		return query.getResultList();
	}

	public Nomenclature retrieveByCode(String code) {
		Query query = entityManager.createQuery("SELECT n FROM "
				+ getEntityClass().getName() + " n" + " WHERE n.code = :code");
		query.setParameter("code", code);
		return (Nomenclature) query.getSingleResult();
	}
}
