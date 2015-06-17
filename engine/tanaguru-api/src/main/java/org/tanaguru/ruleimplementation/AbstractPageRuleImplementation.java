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
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.service.ProcessRemarkService;

/**
 * This class should be overriden by concrete {@link RuleImplementation} classes
 * which implement tests with page scope. It encapsulates common algorithms of
 * {@link RuleImplementation} operations for tests with page scope.
 * 
 * @author jkowalczyk
 */
public abstract class AbstractPageRuleImplementation extends AbstractRuleImplementation {

    /**
     * This is the implementation of the method declared in
     * {@link AbstractRuleImplementation}. For tests with page scope, gross
     * results are as well net results. Thus, this method always have to return
     * gross results already filtered in the main consolidation operation. In
     * common cases, this method should not be overriden.
     *
     * @param grossResultMap
     *            the gross result map to consolidate.
     * @param processRemarkService
     *            
     * @return the net result list which is exactly the same as the gross result
     *         list received from the parameter set.
     */
    @Override
    protected List<ProcessResult> consolidateImpl(
            Map<WebResource, List<ProcessResult>> grossResultMap, ProcessRemarkService processRemarkService) {
        List<ProcessResult> netResultList = new ArrayList<ProcessResult>();

        for (Map.Entry<WebResource, List<ProcessResult>> entry : grossResultMap.entrySet()) {
            for (ProcessResult grossResult : entry.getValue()) {
                netResultList.add(grossResult);
            }
        }

        return netResultList;
    }
}
