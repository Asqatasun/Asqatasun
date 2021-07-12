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
import org.asqatasun.entity.service.parameterization.ParameterElementDataService
import org.asqatasun.service.AuditServiceListener
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.IOException
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
                   private val parameterElementDataService: ParameterElementDataService,
                   private val tagDataService: TagDataService) {

    private val scopeMap: MutableMap<ScopeEnum, Scope> = EnumMap(ScopeEnum::class.java)

    companion object {
        const val DEPTH_PARAM_KEY = "DEPTH"
        const val MAX_DOCUMENT_PARAM_KEY = "MAX_DOCUMENTS"
        const val MAX_DURATION_PARAM_KEY = "MAX_DURATION"
        const val EXCLUSION_URL_LIST_PARAM_KEY = "EXCLUSION_REGEXP"
        const val INCLUSION_URL_LIST_PARAM_KEY = "INCLUSION_REGEXP"
    }

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

        return auditService.auditScenario(
            sar.name,
            readFile(sar.scenario),
            initialiseParamSet(sar.referential.code, sar.level.code),
            tagDataService.getTagListFromValues(sar.tags)).let {
            getAuditId(it, ipAddress, SCENARIO, contract)
            }
    }

    fun runSiteAudit(sar: SiteAuditRequest, ipAddress: String): Long {
        val contract = getContract(sar.contractId)
        val paramSet = initialiseParamSet(sar.referential.code, sar.level.code).toMutableSet()
        initSiteAuditParamSet(sar, paramSet)
        return auditService.auditSite(
            sar.url,
            paramSet,
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

    private fun initSiteAuditParamSet(sar: SiteAuditRequest, paramSet: MutableSet<Parameter>) {
        paramSet.removeIf {
            p -> p.parameterElement.parameterElementCode.equals(DEPTH_PARAM_KEY) ||
                 p.parameterElement.parameterElementCode.equals(MAX_DURATION_PARAM_KEY) ||
                 p.parameterElement.parameterElementCode.equals(MAX_DOCUMENT_PARAM_KEY) ||
                 p.parameterElement.parameterElementCode.equals(INCLUSION_URL_LIST_PARAM_KEY) ||
                 p.parameterElement.parameterElementCode.equals(EXCLUSION_URL_LIST_PARAM_KEY)
        }
        paramSet.add(parameterDataService.getParameter(
            parameterElementDataService.getParameterElement(DEPTH_PARAM_KEY), sar.depth.toString()))
        paramSet.add(parameterDataService.getParameter(
            parameterElementDataService.getParameterElement(MAX_DURATION_PARAM_KEY), sar.maxDuration.toString()))
        paramSet.add(parameterDataService.getParameter(
            parameterElementDataService.getParameterElement(MAX_DOCUMENT_PARAM_KEY), sar.maxPages.toString()))
        paramSet.add(parameterDataService.getParameter(
            parameterElementDataService.getParameterElement(INCLUSION_URL_LIST_PARAM_KEY), sar.inclusionRegexp))
        paramSet.add(parameterDataService.getParameter(
            parameterElementDataService.getParameterElement(EXCLUSION_URL_LIST_PARAM_KEY), sar.exclusionRegexp))
    }

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
