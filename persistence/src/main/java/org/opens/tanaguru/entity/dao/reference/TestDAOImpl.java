package org.opens.tanaguru.entity.dao.reference;

import javax.persistence.Query;

import org.opens.tanaguru.entity.reference.Reference;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.reference.TestImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.ArrayList;
import java.util.List;
import org.opens.tanaguru.entity.reference.Criterion;

public class TestDAOImpl extends AbstractJPADAO<Test, Long> implements TestDAO {

	public TestDAOImpl() {
		super();
	}

	@Override
	protected Class<TestImpl> getEntityClass() {
		return TestImpl.class;
	}

	public List<Test> retrieveAll(Reference reference) {
		Query query = entityManager.createQuery("SELECT t FROM "
				+ getEntityClass().getName() + " t"
				+ " WHERE t.criterion.reference = :reference");
		query.setParameter("reference", reference);
		return query.getResultList();
	}

	public List<Test> retrieveAll(String code, Criterion criterion) {
		Query query = entityManager.createQuery("SELECT t FROM "
				+ getEntityClass().getName() + " t"
				+ " WHERE t.code = :code AND t.criterion = :criterion");
		query.setParameter("code", code);
		query.setParameter("criterion", criterion);
		return query.getResultList();
	}

	public List<Test> retrieveAllByCode(String[] codeArray) {
		if (codeArray.length == 0) {
			return new ArrayList<Test>();
		}

		StringBuilder stringBuilder = new StringBuilder("SELECT t FROM "
				+ getEntityClass().getName() + " t" + " WHERE t.code IN (");

		boolean first = true;
		for (String code : codeArray) {
			if (!first) {
				stringBuilder.append(',');
			} else {
				first = false;
			}
			stringBuilder.append("'" + code + "'");
		}
		stringBuilder.append(')');

		Query query = entityManager.createQuery(stringBuilder.toString());
		return query.getResultList();
	}
}
