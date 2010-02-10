package org.opens.tanaguru.ruleimplementation;

import java.util.List;
import org.opens.tanaguru.entity.audit.ProcessResult;

/**
 * This class should be overriden by concrete {@link RuleImplementation} classes
 * which implement tests with site scope. It encapsulates common algorithms of
 * {@link RuleImplementation} operations for tests with site scope.
 * 
 * @author ADEX
 * @version 1.0.0
 */
public abstract class AbstractSiteGroupRuleImplementation extends AbstractGroupRuleImplementation {

    @Override
    protected List<ProcessResult> consolidateSite(
            List<ProcessResult> netResultList) {
        return consolidateSiteImpl(netResultList);
    }

    /**
     * 
     * @param netResultList
     * @return
     */
    protected abstract List<ProcessResult> consolidateSiteImpl(
            List<ProcessResult> netResultSet);
}
