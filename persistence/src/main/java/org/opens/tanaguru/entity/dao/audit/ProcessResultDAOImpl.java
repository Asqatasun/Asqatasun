package org.opens.tanaguru.entity.dao.audit;

import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.ProcessResultImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;

/**
 * 
 * @author ADEX
 */
public class ProcessResultDAOImpl extends AbstractJPADAO<ProcessResult, Long>
		implements ProcessResultDAO {

	public ProcessResultDAOImpl() {
		super();
	}

	protected Class<ProcessResultImpl> getEntityClass() {
		return ProcessResultImpl.class;
	}
}
