package org.opens.tanaguru.entity.dao.audit;

import org.opens.tanaguru.entity.audit.ProcessRemark;
import com.adex.sdk.entity.dao.GenericDAO;
import java.util.Collection;
import org.opens.tanaguru.entity.audit.ProcessResult;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface ProcessRemarkDAO extends GenericDAO<ProcessRemark, Long> {

    public Collection<ProcessRemark> retrieveAllByProcessResult(
            ProcessResult processResult);
}
