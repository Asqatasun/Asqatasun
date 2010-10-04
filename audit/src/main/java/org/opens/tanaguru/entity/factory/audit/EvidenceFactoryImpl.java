package org.opens.tanaguru.entity.factory.audit;

import org.opens.tanaguru.entity.audit.Evidence;
import org.opens.tanaguru.entity.audit.EvidenceImpl;

/**
 * 
 * @author jkowalczyk
 */
public class EvidenceFactoryImpl implements EvidenceFactory {

    public EvidenceFactoryImpl() {
        super();
    }

    public Evidence create() {
        return new EvidenceImpl();
    }

    public Evidence create(String code) {
        return new EvidenceImpl(code);
    }
}
