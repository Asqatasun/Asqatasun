package org.opens.tanaguru.entity.dao.audit;

import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessRemarkImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.Collection;
import javax.persistence.Query;
import org.opens.tanaguru.entity.audit.ProcessResult;

/**
 * 
 * @author ADEX
 */
public class ProcessRemarkDAOImpl extends AbstractJPADAO<ProcessRemark, Long>
        implements ProcessRemarkDAO {

    public ProcessRemarkDAOImpl() {
        super();
    }

    protected Class<ProcessRemarkImpl> getEntityClass() {
        return ProcessRemarkImpl.class;
    }

    public Collection<ProcessRemark> retrieveAllByProcessResult(
            ProcessResult processResult) {
        Query query = entityManager.createQuery("SELECT r FROM "
                + getEntityClass().getName()
                + " r WHERE r.processResult = :processResult");
        query.setParameter("processResult", processResult);
        return query.getResultList();
    }
}
