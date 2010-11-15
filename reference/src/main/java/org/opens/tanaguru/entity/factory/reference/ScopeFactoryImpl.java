package org.opens.tanaguru.entity.factory.reference;

import org.opens.tanaguru.entity.reference.ScopeImpl;
import org.opens.tanaguru.entity.reference.Scope;

/**
 * 
 * @author ADEX
 */
public class ScopeFactoryImpl implements ScopeFactory {

    public ScopeFactoryImpl() {
        super();
    }

    public Scope create() {
        return new ScopeImpl();
    }
}
