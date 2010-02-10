package org.opens.tanaguru.entity.service.audit;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditStatus;
import com.adex.sdk.entity.service.GenericDataService;
import java.util.Collection;
import java.util.Date;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface AuditDataService extends GenericDataService<Audit, Long> {

    Audit create(Date date);

    /**
     *
     * @param status
     *            the status to find
     * @return the collection of the audits that have the status
     */
    Collection<? extends Audit> findAll(AuditStatus status);
}
