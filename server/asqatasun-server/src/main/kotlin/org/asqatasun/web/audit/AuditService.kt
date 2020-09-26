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

import org.asqatasun.entity.audit.Audit
import org.asqatasun.entity.contract.*
import org.asqatasun.entity.contract.ScopeEnum.*
import org.asqatasun.entity.parameterization.Parameter
import org.asqatasun.entity.service.audit.TagDataService
import org.asqatasun.entity.service.contract.ActDataService
import org.asqatasun.entity.service.contract.ContractDataService
import org.asqatasun.entity.service.contract.ScopeDataService
import org.asqatasun.entity.service.parameterization.ParameterDataService
import org.asqatasun.service.AuditServiceListener
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.IOException
import java.net.URL
import java.time.Instant
import java.util.*
import javax.annotation.PostConstruct
import org.asqatasun.service.AuditService as EngineAuditService

@Service
class AuditService(private val auditService: EngineAuditService,
                   private val contractDataService: ContractDataService,
                   private val actDataService: ActDataService,
                   private val scopeDataService: ScopeDataService,
                   private val parameterDataService: ParameterDataService,
                   private val tagDataService: TagDataService) {

    private val scopeMap: MutableMap<ScopeEnum, Scope> = EnumMap(ScopeEnum::class.java)

    @PostConstruct
    private fun initializeScopeMap() {
        for (scope in scopeDataService.findAll()) {
            scopeMap[scope.code] = scope
        }
    }

    fun runPageAudit(par: PageAuditRequest, ipAddress: String): Long {
        val contract = getContract(par.contractId)

        return if (par.urls.size > 1) {
            getAuditId(
                auditService.auditSite(
                    par.urls[0],
                    par.urls,
                    initialiseParamSet(par.referential.code, par.level.code),
                    tagDataService.getTagListFromValues(par.tags)),
                ipAddress,
                GROUPOFPAGES,
                contract)
        } else {
            getAuditId(
                auditService.auditPage(
                    par.urls[0],
                    initialiseParamSet(par.referential.code, par.level.code),
                    tagDataService.getTagListFromValues(par.tags)),
                ipAddress,
                PAGE,
                contract)
        }
    }

    fun runScenarioAudit(sar: ScenarioAuditRequest, ipAddress: String): Long {
        val contract = getContract(sar.contractId)

        return auditService.auditScenario(sar.name,
                                          readFile(sar.scenario),
                                          initialiseParamSet(sar.referential.code, sar.level.code),
                                          tagDataService.getTagListFromValues(sar.tags)).let {
            getAuditId(it, ipAddress, SCENARIO, contract)
        }
    }

    private fun getAuditId(audit: Audit, ipAddress: String, scope: ScopeEnum, contract: Contract?): Long {
        val act = initialiseAct(ipAddress, scope, audit, contract)
        auditService.add(RestAuditServiceListener(act?.id, actDataService, auditService))
        return audit.id
    }

    private fun initialiseAct(ipAddress: String, scope: ScopeEnum, audit: Audit, contract: Contract?): Act? {
        return contract?.let {
                val a = actDataService.create()
                a.status = ActStatus.RUNNING
                a.beginDate = Date.from(Instant.now())
                a.clientIp = ipAddress
                a.scope = scopeMap[scope]
                a.contract = it
                a.audit = audit
                actDataService.saveOrUpdate(a)
        }
    }

    private fun getContract(contractId: Long?) = contractId?.let { id ->
        contractDataService.read(id) ?: throw ContractNotFoundException()
    }

    private fun initialiseParamSet(referentialCode: String, levelCode: String) =
        parameterDataService.getParameterSetFromAuditLevel(referentialCode, levelCode)

    @Throws(IOException::class)
    // #57 issue quick fix.......
    private fun readFile(scenario: String) =
        scenario.replace("\"formatVersion\": 2", "\"formatVersion\":1")
            .replace("\"formatVersion\":2", "\"formatVersion\":1")

}

class ContractNotFoundException : Throwable() {}

class RestAuditServiceListener(private val actId: Long?,
                               private val actDataService: ActDataService,
                               private val auditService: EngineAuditService) : AuditServiceListener {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(RestAuditServiceListener::class.java)
    }

    override fun auditCompleted(audit: Audit?) {
        LOGGER.info("Rest audit ${audit!!.id} terminated successfully")
        actId?.let {
            val act = actDataService.read(it)
            act.endDate = Date.from(Instant.now())
            act.status = ActStatus.COMPLETED
            actDataService.saveOrUpdate(act)
        }
        auditService.remove(this)
    }

    override fun auditCrashed(audit: Audit?, exception: Exception?) {
        LOGGER.info("Rest audit ${audit!!.id} crashed")
        LOGGER.info(exception!!.message)
        actId?.let {
            val act = actDataService.read(it)
            act.endDate = Date.from(Instant.now())
            act.status = ActStatus.ERROR
            actDataService.saveOrUpdate(act)
        }
        auditService.remove(this)
    }

}
