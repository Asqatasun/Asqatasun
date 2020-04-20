/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.webapp.orchestrator;

import org.asqatasun.emailsender.EmailSender;
import org.asqatasun.entity.service.audit.AuditDataService;
import org.asqatasun.service.AuditService;
import org.asqatasun.webapp.entity.factory.contract.ActFactory;
import org.asqatasun.webapp.entity.service.contract.ActDataService;
import org.asqatasun.webapp.entity.service.contract.ScopeDataService;
import org.asqatasun.webapp.entity.service.scenario.ScenarioDataService;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author enzolalay
 */
public final class AsqatasunOrchestratorFactory {

    /**
     * Factory has private constructor
     */
    private AsqatasunOrchestratorFactory() {
    }

    public static AsqatasunOrchestrator create(
        AuditService auditService,
        ActDataService actDataService,
        AuditDataService auditDataService,
        ActFactory actFactory,
        ScenarioDataService scenarioDataService,
        ScopeDataService scopeDataService,
        ThreadPoolTaskExecutor threadPoolTaskExecutor,
        EmailSender emailSender,
        ReloadableResourceBundleMessageSource messageSource
    ) {
        return new AsqatasunOrchestratorImpl(
            auditService,
            auditDataService,
            actDataService,
            actFactory,
            scopeDataService,
            scenarioDataService,
            threadPoolTaskExecutor,
            emailSender,
            messageSource
        );
    }
}
