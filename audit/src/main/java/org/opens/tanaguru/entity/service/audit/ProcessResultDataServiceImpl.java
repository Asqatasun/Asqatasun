package org.opens.tanaguru.entity.service.audit;

import com.adex.sdk.entity.service.AbstractGenericDataService;
import java.util.Collection;
import java.util.List;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.dao.audit.ProcessResultDAO;
import org.opens.tanaguru.entity.reference.Scope;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author ADEX
 */
public class ProcessResultDataServiceImpl extends AbstractGenericDataService<ProcessResult, Long> implements
        ProcessResultDataService {

    @Override
    public int getResultByThemeCount(WebResource webresource, TestSolution testSolution, Theme theme) {
        return ((ProcessResultDAO) entityDao).
                getResultByThemeCount(webresource, testSolution, theme);
    }

    @Override
    public Collection<ProcessResult> getResultByScopeList(WebResource webresource, Scope scope) {
        return ((ProcessResultDAO) entityDao).
                getResultByScopeList(webresource, scope);
    }
    
    @Override
    public Long getNumberOfGrossResultFromAudit(Audit audit) {
        return ((ProcessResultDAO) entityDao).retrieveNumberOfGrossResultFromAudit(audit);
    }

    @Override
    public Long getNumberOfNetResultFromAudit(Audit audit) {
        return ((ProcessResultDAO) entityDao).retrieveNumberOfNetResultFromAudit(audit);
    }

    @Override
    public List<? extends ProcessResult> getGrossResultFromAudit(Audit audit) {
        return ((ProcessResultDAO) entityDao).retrieveGrossResultFromAudit(audit);
    }

    @Override
    public List<? extends ProcessResult> getNetResultFromAudit(Audit audit) {
        return ((ProcessResultDAO) entityDao).retrieveNetResultFromAudit(audit);
    }

    @Override
    public List<? extends ProcessResult> getNetResultFromAuditAndWebResource(Audit audit, WebResource webResource) {
        return ((ProcessResultDAO) entityDao).retrieveNetResultFromAuditAndWebResource(audit, webResource);
    }
    
    @Override
    public List<? extends ProcessResult> getGrossResultFromAuditAndTest(Audit audit, Test test) {
        return ((ProcessResultDAO) entityDao).retrieveGrossResultFromAuditAndTest(audit, test);
    }

}