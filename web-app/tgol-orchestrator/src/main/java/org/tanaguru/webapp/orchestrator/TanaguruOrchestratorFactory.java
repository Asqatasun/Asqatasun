/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
package org.tanaguru.webapp.orchestrator;

import org.tanaguru.emailsender.EmailSender;
import org.tanaguru.entity.service.audit.AuditDataService;
import org.tanaguru.service.AuditService;
import org.tanaguru.webapp.entity.factory.contract.ActFactory;
import org.tanaguru.webapp.entity.service.contract.ActDataService;
import org.tanaguru.webapp.entity.service.contract.ScopeDataService;
import org.tanaguru.webapp.entity.service.scenario.ScenarioDataService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *
 * @author enzolalay
 */
public final class TanaguruOrchestratorFactory {

    /**
     * Factory has private constructor
     */
    private TanaguruOrchestratorFactory(){}

    public static TanaguruOrchestrator create(
            AuditService auditService,
            ActDataService actDataService,
            AuditDataService auditDataService,
            ActFactory actFactory,
            ScenarioDataService scenarioDataService,
            ScopeDataService scopeDataService,
            ThreadPoolTaskExecutor threadPoolTaskExecutor,
            EmailSender emailSender) {
        return new TanaguruOrchestratorImpl(
                auditService,
                auditDataService,
                actDataService,
                actFactory,
                scopeDataService,
                scenarioDataService,
                threadPoolTaskExecutor,
                emailSender);
    }
}
