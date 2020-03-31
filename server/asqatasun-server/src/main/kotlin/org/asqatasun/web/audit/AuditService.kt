/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2020  Asqatasun.org
 *
 *  This file is part of Asqatasun.
 *
 *  Asqatasun is free software: you can redistribute it and/or modify
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
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */

package org.asqatasun.web.audit

import org.asqatasun.entity.parameterization.Parameter
import org.asqatasun.entity.service.parameterization.ParameterDataService
import org.springframework.stereotype.Service
import java.io.IOException
import org.asqatasun.service.AuditService as EngineAuditService

@Service
class AuditService(private val parameterDataService: ParameterDataService,
                   private val auditService: EngineAuditService) {

    fun runPageAudit(par: PageAuditRequest): Long {
        val paramSet: Set<Parameter> =
            parameterDataService.getParameterSetFromAuditLevel(par.referential.code, par.level.code)
        return if (par.urls.size > 1)
            auditService.auditSite("site:" + par.urls[0], par.urls, paramSet).id
        else
            auditService.auditPage(par.urls[0], parameterDataService.getAuditPageParameterSet(paramSet)).id
    }

    fun runScenarioAudit(sar: ScenarioAuditRequest): Long {
        val paramSet: Set<Parameter> =
            parameterDataService.getParameterSetFromAuditLevel(sar.referential.code, sar.level.code)
        return auditService.auditScenario(sar.name, readFile(sar.scenario), paramSet).id
    }

    @Throws(IOException::class)
    // #57 issue quick fix.......
    private fun readFile(scenario: String) =
        scenario.replace("\"formatVersion\": 2", "\"formatVersion\":1")
            .replace("\"formatVersion\":2", "\"formatVersion\":1")

}
