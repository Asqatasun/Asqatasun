package org.opens.tanaguru.entity.factory.audit;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditImpl;
import java.util.Date;

/**
 * 
 * @author ADEX
 */
public class AuditFactoryImpl implements AuditFactory {

    public AuditFactoryImpl() {
        super();
    }

    @Override
    public Audit create() {
        return new AuditImpl(new Date());
    }

    @Override
    public Audit create(Date dateOfCreation) {
        return new AuditImpl(dateOfCreation);
    }
    
}