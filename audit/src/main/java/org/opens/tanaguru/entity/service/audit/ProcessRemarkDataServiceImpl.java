package org.opens.tanaguru.entity.service.audit;

import com.adex.sdk.entity.service.AbstractGenericDataService;
import java.util.Collection;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.dao.audit.ProcessRemarkDAO;

/**
 * 
 * @author ADEX
 */
public class ProcessRemarkDataServiceImpl extends AbstractGenericDataService<ProcessRemark, Long> implements
        ProcessRemarkDataService {

    @Override
    public Collection<ProcessRemark> findAllByProcessResult(ProcessResult processResult) {
        return ((ProcessRemarkDAO) entityDao).
                retrieveAllByProcessResult(processResult);
    }
    
}
