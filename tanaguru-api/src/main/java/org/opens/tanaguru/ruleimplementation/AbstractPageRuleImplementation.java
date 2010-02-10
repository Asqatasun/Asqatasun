package org.opens.tanaguru.ruleimplementation;

import java.util.ArrayList;
import java.util.List;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.subject.WebResource;
import java.util.Map;

/**
 * This class should be overriden by concrete {@link RuleImplementation} classes
 * which implement tests with page scope. It encapsulates common algorithms of
 * {@link RuleImplementation} operations for tests with page scope.
 * 
 * @author ADEX
 * @version 1.0.0
 */
public abstract class AbstractPageRuleImplementation extends AbstractRuleImplementation {

    /**
     * This is the implementation of the method declared in
     * {@link AbstractRuleImplementation}. For tests with page scope, gross
     * results are as well net results. Thus, this method always have to return
     * gross results already filtered in the main consolidation operation. In
     * common cases, this method should not be overriden.
     *
     * @param grossResultList
     *            the gross result list to consolidate.
     * @return the net result list which is exactly the same as the gross result
     *         list received from the parameter set.
     */
    @Override
    protected List<ProcessResult> consolidateImpl(
            Map<WebResource, List<ProcessResult>> grossResultMap) {
        List<ProcessResult> netResultList = new ArrayList<ProcessResult>();

        for (Map.Entry<WebResource, List<ProcessResult>> entry : grossResultMap.entrySet()) {
            for (ProcessResult grossResult : entry.getValue()) {
                netResultList.add(grossResult);
            }
        }

        return netResultList;
    }
}
