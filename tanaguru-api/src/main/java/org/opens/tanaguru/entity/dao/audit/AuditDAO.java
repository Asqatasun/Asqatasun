package org.opens.tanaguru.entity.dao.audit;

import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditStatus;
import com.adex.sdk.entity.dao.GenericDAO;
import java.util.Collection;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface AuditDAO extends GenericDAO<Audit, Long> {

    /**
     *
     * @param status
     *            the status to find
     * @return the collection of the audits that have the status
     */
    Collection<Audit> findAll(AuditStatus status);

    /**
     * 
     * @param id
     * @return
     */
    Audit findAuditWithWebResource(Long id);

    /**
     * 
     * @param id
     * @return
     */
    Audit findAuditWithTest(Long id);
}
