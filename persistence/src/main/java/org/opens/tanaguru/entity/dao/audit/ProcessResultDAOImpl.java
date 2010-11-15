package org.opens.tanaguru.entity.dao.audit;

import java.util.Collection;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.ProcessResultImpl;
import com.adex.sdk.entity.dao.jpa.AbstractJPADAO;
import javax.persistence.Query;
import org.opens.tanaguru.entity.audit.DefiniteResultImpl;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Scope;
import org.opens.tanaguru.entity.reference.TestImpl;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.reference.ThemeImpl;
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
        queryStr.append("SELECT COUNT(*) FROM "
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
                + "WHERE "+
                "pr.subject = :webresource AND " +
                "pr.test.scope = :scope");
        query.setParameter("webresource", webresource);
        query.setParameter("scope", scope);
        return  query.getResultList();
    }

}
