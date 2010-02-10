package org.opens.tanaguru.entity.service.audit;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.reference.Test;
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
        for (ProcessRemark remark : processResult.getRemarkList()) {
        }
        for (ProcessResult childResult : processResult.getChildResultList()) {
            deepLoad(childResult);
        }
    }

    public Collection<? extends Audit> findAll(AuditStatus status) {
        return ((AuditDAO) entityDao).findAll(status);
    }

    @Override
    public Audit read(Long key) {
        Audit entity = super.read(key);

        for (Test test : entity.getTestList()) {
        }

        entity.getSubject();

        for (Content content : entity.getContentList()) {
        }

        for (ProcessResult netResult : entity.getNetResultList()) {
            deepLoad(netResult);
        }

        return entity;
    }
}
