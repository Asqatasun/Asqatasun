package org.opens.tanaguru.ruleimplementation;

import java.util.ArrayList;
import java.util.List;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import java.util.Map;
import org.opens.tanaguru.service.ProcessRemarkService;

/**
 * This class should be overriden by concrete {@link RuleImplementation} classes
 * which implement tests with group scope. It encapsulates common algorithms of
 * {@link RuleImplementation} operations for tests with group scope.
 * 
 * @author ADEX
 * @version 1.0.0
 */
public abstract class AbstractGroupRuleImplementation extends AbstractRuleImplementation {

    /**
     * This is the method that has to be implemented by concrete
     * {@link RuleImplementation} classes for tests with group scope. This
     * method should consolidate gross results by group.
     *
     * @param group
     *            the group of the gross result list ton consolidate.
     * @param groupedGrossResultSet
     *            the gross result list to consolidate concerning the group.
     * @return the net result of the group from the gross result list
     */
    protected DefiniteResult consolidateGroup(Site group,
            List<ProcessResult> groupedGrossResultSet,
            ProcessRemarkService processRemarkService) {
        return consolidateGroupImpl(group, groupedGrossResultSet, processRemarkService);
    }

    protected abstract DefiniteResult consolidateGroupImpl(Site group,
            List<ProcessResult> groupedGrossResultSet,
            ProcessRemarkService processRemarkService);

    /**
     * This is the implementation of the method declared in
     * {@link AbstractRuleImplementation}. It encapsulates the main algorithm of
     * the consolidation operation of concrete {@link RuleImplementation}
     * classes for tests with group scope. It calls the method
     * {@link #consolidateGroup(com.adex.evalaccess.entity.subject.Site, java.util.Set)}
     * which should consolidate gross results by group. In common cases, this
     * method should not be overriden.
     *
     * @param grossResultMap
     *            the gross result map to consolidate.
     * @return the list of net results from the gross result list.
     */
    @Override
    protected List<ProcessResult> consolidateImpl(
            Map<WebResource, List<ProcessResult>> grossResultMap, 
            ProcessRemarkService processRemarkService) {
        List<ProcessResult> netResultList = new ArrayList<ProcessResult>();

        for (Map.Entry<WebResource, List<ProcessResult>> entry : grossResultMap.entrySet()) {
            WebResource key = entry.getKey();
            List<ProcessResult> grossResultList = entry.getValue();

            if (entry.getKey() instanceof Page) {
                DefiniteResult netResult = definiteResultFactory.create(test,
                        key);
                netResult.setDefiniteValue(TestSolution.NOT_APPLICABLE);
                for (ProcessResult grossResult : grossResultList) {
                    netResult.addChildResult(grossResult);
                }
                netResultList.add(netResult);
                continue;
            } else {
                netResultList.add(consolidateGroup((Site) key, grossResultList,processRemarkService));
            }
        }

        return consolidateSite(netResultList, processRemarkService);
    }

    protected List<ProcessResult> consolidateSite(
            List<ProcessResult> netResultSet,
            ProcessRemarkService processRemarkService) {
        return netResultSet;
    }
}
