/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.ruleimplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.tanaguru.entity.audit.DefiniteResult;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.subject.Page;
import org.tanaguru.entity.subject.Site;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.service.ProcessRemarkService;

/**
 * This class should be overriden by concrete {@link RuleImplementation} classes
 * which implement tests with group scope. It encapsulates common algorithms of
 * {@link RuleImplementation} operations for tests with group scope.
 * 
 * @author jkowalczyk
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
     * @param processRemarkService
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
     * {@link #consolidateGroup(org.tanaguru.entity.subject.Site, java.util.Set)}
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
        List<ProcessResult> netResultList = new ArrayList<>();

        for (Map.Entry<WebResource, List<ProcessResult>> entry : grossResultMap.entrySet()) {
            WebResource key = entry.getKey();
            List<ProcessResult> grossResultList = entry.getValue();

            if (entry.getKey() instanceof Page) {
                DefiniteResult netResult = processResultDataService.getDefiniteResult(test,
                        key);
                netResult.setDefiniteValue(TestSolution.NOT_APPLICABLE);
                for (ProcessResult grossResult : grossResultList) {
//                    netResult.addChildResult(grossResult);
                    netResult.setNetResultAudit(grossResult.getGrossResultAudit());
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
