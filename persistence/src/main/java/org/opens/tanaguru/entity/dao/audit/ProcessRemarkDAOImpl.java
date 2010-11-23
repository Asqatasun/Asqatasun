package org.opens.tanaguru.entity.dao.audit;

import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessRemarkImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
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

    @Override
    protected Class<ProcessRemarkImpl> getEntityClass() {
        return ProcessRemarkImpl.class;
    }

    @Override
    public Collection<ProcessRemark> retrieveAllByProcessResult(
            ProcessResult processResult) {
        Query query = entityManager.createQuery("SELECT r FROM "
                + getEntityClass().getName() + " r"
                + " left join fetch r.elementList e"
                + " WHERE r.processResult = :processResult");
        query.setParameter("processResult", processResult);
        Set setItems = new LinkedHashSet(query.getResultList());
        return setItems;
    }
}
