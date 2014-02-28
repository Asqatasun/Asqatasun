/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tgol.orchestrator;

import org.opens.emailsender.EmailSender;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.service.AuditService;
import org.opens.tgol.entity.factory.contract.ActFactory;
import org.opens.tgol.entity.service.contract.ActDataService;
import org.opens.tgol.entity.service.contract.ScopeDataService;
import org.opens.tgol.entity.service.scenario.ScenarioDataService;
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
