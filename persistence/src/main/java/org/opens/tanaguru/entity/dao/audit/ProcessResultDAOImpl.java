package org.opens.tanaguru.entity.dao.audit;

import java.util.Collection;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.ProcessResultImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Query;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.DefiniteResultImpl;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Scope;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author ADEX
 */
public class ProcessResultDAOImpl extends AbstractJPADAO<ProcessResult, Long>
        implements ProcessResultDAO {

    public ProcessResultDAOImpl() {
        super();
    }

    protected Class<ProcessResultImpl> getEntityClass() {
        return ProcessResultImpl.class;
    }

    private Class<DefiniteResultImpl> getDefitiniteResultClass() {
        return DefiniteResultImpl.class;
    }

    @Override
    public int getResultByThemeCount(WebResource webresource, TestSolution testSolution, Theme theme) {
        StringBuilder queryStr = new StringBuilder();
        queryStr.append("SELECT COUNT(pr) FROM "
                + getDefitiniteResultClass().getName() + " pr "
                + "WHERE "+
                "pr.subject = :webresource AND " +
                "pr.definiteValue = :definiteValue");
        if (theme != null) {
            queryStr.append(" AND pr.test.criterion.theme = :theme");
        }
        Query query = entityManager.createQuery(queryStr.toString());
        query.setParameter("definiteValue", testSolution);
        query.setParameter("webresource", webresource);
        if (theme != null) {
            query.setParameter("theme", theme);
        }
        return Long.valueOf((Long)query.getSingleResult()).intValue();
    }

    @Override
    public Collection<ProcessResult> getResultByScopeList(WebResource webresource, Scope scope) {
        Query query = entityManager.createQuery("SELECT pr FROM "
                + getDefitiniteResultClass().getName() + " pr "
                + " WHERE "+
                "pr.subject = :webresource AND " +
                "pr.test.scope = :scope");
        query.setParameter("webresource", webresource);
        query.setParameter("scope", scope);
        Set setItems = new LinkedHashSet(query.getResultList());
        return  setItems;
    }

    @Override
    public Long retrieveNumberOfGrossResultFromAudit(Audit audit) {
        Query query = entityManager.createQuery("SELECT count(pr.id) FROM "
                + getEntityClass().getName() + " pr "
                + " WHERE "
                + " pr.grossResultAudit = :audit");
        query.setParameter("audit", audit);
        return  (Long)query.getSingleResult();
    }

    @Override
    public Long retrieveNumberOfNetResultFromAudit(Audit audit) {
        Query query = entityManager.createQuery("SELECT count(pr.id) FROM "
                + getEntityClass().getName() + " pr "
                + " WHERE "
                + " pr.netResultAudit = :audit");
        query.setParameter("audit", audit);
        return  (Long)query.getSingleResult();
    }

    @Override
    public List<? extends ProcessResult> retrieveGrossResultFromAudit(Audit audit) {
        Query query = entityManager.createQuery("SELECT pr FROM "
                + getEntityClass().getName() + " pr "
                + " LEFT JOIN FETCH pr.subject"
                + " LEFT JOIN FETCH pr.test"
                + " WHERE "
                + " pr.grossResultAudit = :audit");
        query.setParameter("audit", audit);
        return query.getResultList();
    }

    @Override
    public List<? extends ProcessResult> retrieveNetResultFromAudit(Audit audit) {
        Query query = entityManager.createQuery("SELECT pr FROM "
                + getEntityClass().getName() + " pr "
                + " LEFT JOIN FETCH pr.subject"
                + " WHERE "
                + " pr.netResultAudit = :audit");
        query.setParameter("audit", audit);
        return query.getResultList();
    }

    @Override
    public List<? extends ProcessResult> retrieveNetResultFromAuditAndWebResource(Audit audit, WebResource webResource) {
        Query query = entityManager.createQuery("SELECT pr FROM "
                + getDefitiniteResultClass().getName() + " pr "
                + " WHERE "
                + " pr.netResultAudit = :audit AND"
                + " pr.subject = :webResource");
        query.setParameter("audit", audit);
        query.setParameter("webResource", webResource);
        return query.getResultList();
    }

}
