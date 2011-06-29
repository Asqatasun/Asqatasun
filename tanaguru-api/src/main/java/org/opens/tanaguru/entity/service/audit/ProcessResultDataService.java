package org.opens.tanaguru.entity.service.audit;

import com.adex.sdk.entity.service.GenericDataService;
import java.util.Collection;
import java.util.List;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Scope;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author ADEX
 */
public interface ProcessResultDataService extends
		GenericDataService<ProcessResult, Long> {

    /**
     * This method returns the number of elements for a given resource,
     * testSolution and theme
     * @param webresource
     * @param testSolution
     * @param theme
     * @return
     */
    int getResultByThemeCount(
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
    Collection<ProcessResult> getResultByScopeList(
            WebResource webresource,
            Scope scope);

    /**
     * 
     * @param audit
     * @return
     */
    Long getNumberOfGrossResultFromAudit(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    Long getNumberOfNetResultFromAudit(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    List<? extends ProcessResult> getGrossResultFromAudit(Audit audit);

    /**
     *
     * @param audit
     * @return
     */
    List<? extends ProcessResult> getGrossResultFromAuditAndTest(Audit audit, Test test);

    /**
     *
     * @param audit
     * @return
     */
    List<? extends ProcessResult> getNetResultFromAudit(Audit audit);

    /**
     * 
     * @param audit
     * @param webResource
     * @return
     */
    List<? extends ProcessResult> getNetResultFromAuditAndWebResource(Audit audit, WebResource webResource);
}