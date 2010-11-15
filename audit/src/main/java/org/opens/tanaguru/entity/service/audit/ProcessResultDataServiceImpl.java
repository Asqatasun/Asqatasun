package org.opens.tanaguru.entity.service.audit;

import com.adex.sdk.entity.service.AbstractGenericDataService;
import java.util.Collection;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.dao.audit.ProcessResultDAO;
import org.opens.tanaguru.entity.reference.Scope;
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
}
