package org.opens.tanaguru.entity.service.audit;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.factory.audit.AuditFactory;
import org.opens.tanaguru.entity.dao.audit.AuditDAO;
import com.adex.sdk.entity.service.AbstractGenericDataService;
import java.util.Collection;
import java.util.Date;

/**
 * 
 * @author ADEX
 */
public class AuditDataServiceImpl extends AbstractGenericDataService<Audit, Long> implements AuditDataService {

    public AuditDataServiceImpl() {
        super();
    }

    public Audit create(Date date) {
        return ((AuditFactory) entityFactory).create(date);
    }

    protected void deepLoad(ProcessResult processResult) {
        for (ProcessResult childResult : processResult.getChildResultList()) {
            deepLoad(childResult);
        }
    }

    public Collection<? extends Audit> findAll(AuditStatus status) {
        return ((AuditDAO) entityDao).findAll(status);
    }

    @Override
    public Audit getAuditWithWebResource(Long id) {
        return ((AuditDAO) entityDao).findAuditWithWebResource(id);
    }

    @Override
    public Audit getAuditWithTest(Long id) {
        return ((AuditDAO) entityDao).findAuditWithTest(id);
    }

}