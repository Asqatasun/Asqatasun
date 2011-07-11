package org.opens.tanaguru.entity.factory.audit;

import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.SSPImpl;
import org.opens.tanaguru.entity.subject.Page;
import java.util.Date;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public class SSPFactoryImpl implements SSPFactory {

    public SSPFactoryImpl() {
        super();
    }

    @Override
    public SSP create() {
        return new SSPImpl();
    }

    @Override
    public SSP create(Date dateOfLoading, String uri) {
        return new SSPImpl(dateOfLoading, uri);
    }

    @Override
    public SSP create(Date dateOfLoading, String uri, String sourceCode,
            Page page) {
        return new SSPImpl(dateOfLoading, uri, sourceCode, page);
    }
    
}