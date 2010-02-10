package org.opens.tanaguru.entity.factory.audit;

import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.DefiniteResultImpl;
import org.opens.tanaguru.entity.audit.IndefiniteResult;
import org.opens.tanaguru.entity.audit.IndefiniteResultImpl;
import org.opens.tanaguru.entity.audit.ProcessResult;

/**
 * 
 * @author ADEX
 */
public class ProcessResultFactoryImpl implements ProcessResultFactory {

    public ProcessResultFactoryImpl() {
        super();
    }

    public ProcessResult create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public DefiniteResult createDefiniteResult() {
        return new DefiniteResultImpl();
    }

    public IndefiniteResult createIndefiniteResult() {
        return new IndefiniteResultImpl();
    }
}
