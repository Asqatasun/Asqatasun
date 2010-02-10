package org.opens.tanaguru.entity.dao.audit;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditImpl;
import org.opens.tanaguru.entity.audit.AuditStatus;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.List;
import javax.persistence.Query;

public class AuditDAOImpl extends AbstractJPADAO<Audit, Long> implements
		AuditDAO {

	public AuditDAOImpl() {
		super();
	}

	@Override
	public List<Audit> findAll() {
		List<Audit> auditList = super.findAll();
		for (Audit audit : auditList) {
                    audit.getSubject();
		}
		return auditList;
	}

	public List<Audit> findAll(AuditStatus status) {
		Query query = entityManager.createQuery("SELECT o FROM "
				+ getEntityClass().getName() + " o"
				+ " WHERE o.status = :status");
		query.setParameter("status", status);

		List<Audit> auditList = query.getResultList();

		for (Audit audit : auditList) {
			audit.getSubject();
		}

		return auditList;
	}

	@Override
	protected Class<AuditImpl> getEntityClass() {
		return AuditImpl.class;
	}
}
