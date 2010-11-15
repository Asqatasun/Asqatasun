package org.opens.tanaguru.entity.service.audit;

import com.adex.sdk.entity.service.GenericDataService;
import java.util.Collection;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;

/**
 * 
 * @author ADEX
 */
public interface ProcessRemarkDataService extends
		GenericDataService<ProcessRemark, Long> {

    /**
     * 
     * @param sourceCodeRemark
     * @return
     */
    public Collection<ProcessRemark> findAllByProcessResult(ProcessResult processResult);
    
}
