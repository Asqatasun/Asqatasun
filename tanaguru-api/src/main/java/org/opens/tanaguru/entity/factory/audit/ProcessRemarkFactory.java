package org.opens.tanaguru.entity.factory.audit;

import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.TestSolution;
import com.adex.sdk.entity.factory.GenericFactory;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface ProcessRemarkFactory extends GenericFactory<ProcessRemark> {

	ProcessRemark create(TestSolution issue, String messageCode);
}
