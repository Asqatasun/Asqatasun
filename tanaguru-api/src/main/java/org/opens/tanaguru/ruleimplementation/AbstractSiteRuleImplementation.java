package org.opens.tanaguru.ruleimplementation;

import java.util.List;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.service.ProcessRemarkService;

/**
 * This class should be overriden by concrete {@link RuleImplementation} classes
 * which implement tests with site scope. It encapsulates common algorithms of
 * {@link RuleImplementation} operations for tests with site scope.
 * 
 * @author ADEX
 * @version 1.0.0
 */
public abstract class AbstractSiteRuleImplementation extends AbstractGroupRuleImplementation {

    @Override
    protected DefiniteResult consolidateGroupImpl(Site group,
            List<ProcessResult> groupedGrossResultList,
            ProcessRemarkService processRemarkService) {
        return consolidateSiteImpl(group, groupedGrossResultList, processRemarkService);
    }

    /**
     *
     * @param group
     * @param groupedGrossResultList
     * @return
     */
    protected abstract DefiniteResult consolidateSiteImpl(Site group,
            List<ProcessResult> groupedGrossResultList,
            ProcessRemarkService processRemarkService);
}
