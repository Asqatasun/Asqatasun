package org.opens.tanaguru.entity.factory.audit;

import com.adex.sdk.entity.factory.GenericFactory;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.IndefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;

/**
 * 
 * @author ADEX
 */
public interface ProcessResultFactory extends GenericFactory<ProcessResult> {

    /**
     *
     * @return
     */
    DefiniteResult createDefiniteResult();

    /**
     *
     * @return
     */
    IndefiniteResult createIndefiniteResult();
}
