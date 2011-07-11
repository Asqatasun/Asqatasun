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

    @Override
    public ProcessResult create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DefiniteResult createDefiniteResult() {
        return new DefiniteResultImpl();
    }

    @Override
    public IndefiniteResult createIndefiniteResult() {
        return new IndefiniteResultImpl();
    }

}