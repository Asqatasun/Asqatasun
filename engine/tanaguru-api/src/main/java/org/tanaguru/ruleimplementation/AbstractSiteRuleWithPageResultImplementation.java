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

import java.util.List;
import org.tanaguru.entity.audit.DefiniteResult;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.service.audit.ProcessRemarkDataService;
import org.tanaguru.entity.subject.Site;
import org.tanaguru.service.ProcessRemarkService;

/**
 * This class should be overriden by concrete {@link RuleImplementation} classes
 * which implement tests with site scope. It encapsulates common algorithms of
 * {@link RuleImplementation} operations for tests with site scope.
 * 
 * @author jkowalczyk
 */
public abstract class AbstractSiteRuleWithPageResultImplementation extends AbstractGroupRuleWithPageResultImplementation {

    protected ProcessRemarkDataService processRemarkDataService;
    public void setProcessRemarkDataService(ProcessRemarkDataService processRemarkDataService) {
        this.processRemarkDataService = processRemarkDataService;
    }
    
    @Override
    protected List<DefiniteResult> consolidateGroupImpl(Site group,
            List<ProcessResult> groupedGrossResultList,
            ProcessRemarkService processRemarkService) {
        return consolidateSiteImpl(group, groupedGrossResultList, processRemarkService);
    }

    /**
     *
     * @param group
     * @param groupedGrossResultList
     * @param processRemarkService
     * @return
     */
    protected abstract List<DefiniteResult> consolidateSiteImpl(Site group,
            List<ProcessResult> groupedGrossResultList,
            ProcessRemarkService processRemarkService);

}