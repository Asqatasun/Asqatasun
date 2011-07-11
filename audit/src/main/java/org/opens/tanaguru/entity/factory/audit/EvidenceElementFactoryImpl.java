package org.opens.tanaguru.entity.factory.audit;

import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.EvidenceElementImpl;

/**
 * 
 * @author ADEX
 */
public class EvidenceElementFactoryImpl implements
        EvidenceElementFactory {

    public EvidenceElementFactoryImpl() {
        super();
    }

    @Override
    public EvidenceElement create() {
        return new EvidenceElementImpl();
    }

    @Override
    public EvidenceElement create(String value) {
        return new EvidenceElementImpl(value);
    }

}