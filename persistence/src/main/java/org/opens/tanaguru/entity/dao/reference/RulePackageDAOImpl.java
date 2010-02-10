package org.opens.tanaguru.entity.dao.reference;

import org.opens.tanaguru.entity.reference.RulePackage;
import org.opens.tanaguru.entity.reference.RulePackageImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.Collection;
import javax.persistence.Query;

/**
 * 
 * @author ADEX
 */
public class RulePackageDAOImpl extends AbstractJPADAO<RulePackage, Long>
		implements RulePackageDAO {

	public RulePackageDAOImpl() {
		super();
	}

	@Override
	protected Class<? extends RulePackage> getEntityClass() {
		return RulePackageImpl.class;
	}

	public Collection<RulePackage> retrieveAllByPackageName(String packageName) {
		Query query = entityManager.createQuery("SELECT r FROM "
				+ getEntityClass().getName()
				+ " r WHERE r.packageName = :packageName");
		query.setParameter("packageName", packageName);
		return query.getResultList();
	}
}
