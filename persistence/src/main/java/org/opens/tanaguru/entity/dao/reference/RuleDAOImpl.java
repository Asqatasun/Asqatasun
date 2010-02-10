package org.opens.tanaguru.entity.dao.reference;

import javax.persistence.Query;

import org.opens.tanaguru.entity.reference.Rule;
import org.opens.tanaguru.entity.reference.RuleImpl;
import org.opens.tanaguru.entity.reference.Test;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.Collection;
import org.opens.tanaguru.entity.reference.RulePackage;

/**
 * 
 * @author ADEX
 */
public class RuleDAOImpl extends AbstractJPADAO<Rule, Long> implements RuleDAO {

	public RuleDAOImpl() {
		super();
	}

	@Override
	protected Class<? extends Rule> getEntityClass() {
		return RuleImpl.class;
	}

	public Rule retrieve(Test test) {
		Query query = entityManager.createQuery("SELECT r FROM "
				+ getEntityClass().getName() + " r, IN(r.testList) t"
				+ " WHERE t = :test");
		query.setParameter("test", test);
		return (Rule) query.getSingleResult();
	}

	public Collection<Rule> retrieveAll(RulePackage owningPackage,
			String className) {
		Query query = entityManager
				.createQuery("SELECT r FROM "
						+ getEntityClass().getName()
						+ " r WHERE r.owningPackage = :owningPackage AND r.className = :className");
		query.setParameter("owningPackage", owningPackage);
		query.setParameter("className", className);
		return query.getResultList();
	}
}
