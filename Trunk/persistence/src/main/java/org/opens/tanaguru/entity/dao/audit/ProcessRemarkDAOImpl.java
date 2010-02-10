package org.opens.tanaguru.entity.dao.audit;

import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessRemarkImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;

/**
 * 
 * @author ADEX
 */
public class ProcessRemarkDAOImpl extends AbstractJPADAO<ProcessRemark, Long>
		implements ProcessRemarkDAO {

	public ProcessRemarkDAOImpl() {
		super();
	}

	protected Class<ProcessRemarkImpl> getEntityClass() {
		return ProcessRemarkImpl.class;
	}
}
