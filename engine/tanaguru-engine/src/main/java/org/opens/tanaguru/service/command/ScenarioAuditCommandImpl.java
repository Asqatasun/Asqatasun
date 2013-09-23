/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2013  Open-S Company
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: open-s AT open-s DOT com
 */

package org.opens.tanaguru.service.command;

import java.util.Set;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.service.audit.AuditDataService;

/**
 *
 * @author jkowalczyk
 */
public class ScenarioAuditCommandImpl extends AbstractScenarioAuditCommandImpl {

    /**
     * 
     * @param scenarioName
     * @param scenario
     * @param paramSet
     * @param auditDataService 
     */
    public ScenarioAuditCommandImpl(
            String scenarioName,
            String scenario,
            Set<Parameter> paramSet, 
            AuditDataService auditDataService) {
        super(paramSet, auditDataService);
        setScenario(scenario);
        setScenarioName(scenarioName);
        setIsPage(false);
    }

}