package org.opens.tanaguru.entity.factory.audit;

import org.opens.tanaguru.entity.audit.SourceCodeRemark;
import org.opens.tanaguru.entity.audit.SourceCodeRemarkImpl;

/**
 * 
 * @author ADEX
 */
public class SourceCodeRemarkFactoryImpl implements SourceCodeRemarkFactory {

    public SourceCodeRemarkFactoryImpl() {
        super();
    }

    @Override
    public SourceCodeRemark create() {
        return new SourceCodeRemarkImpl();
    }

}