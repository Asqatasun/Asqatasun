package org.opens.tanaguru.entity.dao.audit;

import org.opens.tanaguru.entity.audit.ProcessResult;
import com.adex.sdk.entity.dao.GenericDAO;
import java.util.Collection;
import java.util.List;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Scope;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface ProcessResultDAO extends GenericDAO<ProcessResult, Long> {

    /**
     * This method returns the number of elements for a given resource,
     * testSolution and theme
     * @param webresource
     * @param testSolution
     * @param theme
     * @return
     */
    public int getResultByThemeCount(
            WebResource webresource,
            TestSolution testSolution,
            Theme theme);

    /**
     * This method returns all the process result for a given resource and
     * for a given scope (Site or Page)
     * @param webresource
     * @param scope
     * @return
     */
    public Collection<ProcessResult> getResultByScopeList(
            WebResource webresource,
            Scope scope);

    /**
     * 
     * @param audit
     * @return
     */
    public Long retrieveNumberOfGrossResultFromAudit(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    public Long retrieveNumberOfNetResultFromAudit(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    public List<? extends ProcessResult> retrieveGrossResultFromAudit(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    public List<? extends ProcessResult> retrieveNetResultFromAudit(Audit audit);

    /**
     * 
     * @param audit
     * @param webResource
     * @return
     */
    public List<? extends ProcessResult> retrieveNetResultFromAuditAndWebResource(Audit audit, WebResource webResource);

}
