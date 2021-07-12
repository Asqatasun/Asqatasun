/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2021  Asqatasun.org
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

import org.asqatasun.entity.service.audit.AuditDataService
import org.asqatasun.entity.service.statistics.WebResourceStatisticsDataService
import org.asqatasun.model.*
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.io.IOException
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("api/v1/audit")
class AuditController(private val auditDataService: AuditDataService,
                      private val webResourceStatisticsDataService: WebResourceStatisticsDataService,
                      private val auditService: AuditService) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    fun getAll() = auditDataService.findAll()
        .map { audit ->
            audit.toAuditDto(
                audit.subject.toWebResourceDto(webResourceStatisticsDataService),
                audit.tagList.map { tag -> tag.value },
                audit.parameterSet.filter { parameter -> parameter.parameterElement.parameterElementCode == "LEVEL" }[0].value)
        }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) =
        Optional.ofNullable(auditDataService.read(id)).map {audit ->
            audit.toAuditDto(
                audit.subject.toWebResourceDto(webResourceStatisticsDataService),
                audit.tagList.map { tag -> tag.value },
                audit.parameterSet.filter { parameter -> parameter.parameterElement.parameterElementCode == "LEVEL" }[0].value)
        }.orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Audit Not Found")
        }!!

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tags/{tags}")
    fun getByTags(@PathVariable tags: List<String>) =
        Optional.ofNullable(auditDataService.findAllByTags(tags)).map {
            it.map {audit ->
                audit.toAuditDto(
                    audit.subject.toWebResourceDto(webResourceStatisticsDataService),
                    audit.tagList.map { tag -> tag.value },
                    audit.parameterSet.filter { parameter -> parameter.parameterElement.parameterElementCode == "LEVEL" }[0].value)
            }
        }.orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Audit Not Found")
        }!!

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = ["/page/run"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun auditPage(@RequestBody auditRequest: PageAuditRequest, request: HttpServletRequest)
        = auditService.runPageAudit(auditRequest, request.remoteAddr)

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = ["/site/run"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun auditSite(@RequestBody auditRequest: SiteAuditRequest, request: HttpServletRequest)
        = auditService.runSiteAudit(auditRequest, request.remoteAddr)

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = ["/scenario/run"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Throws(IOException::class)
    fun auditScenario(@RequestBody auditRequest: ScenarioAuditRequest, request: HttpServletRequest)
        = auditService.runScenarioAudit(auditRequest, request.remoteAddr)

}

data class SiteAuditRequest(
    val url: String,
    val referential: Referential,
    val level: Level,
    val depth: Int = 10,
    val maxPages: Int = 1000,
    val maxDuration: Int = 3600,
    val exclusionRegexp: String = "",
    val inclusionRegexp: String = "",
    val contractId: Long?,
    val tags: List<String>? = emptyList()
)

data class PageAuditRequest(
    val urls: List<String>,
    val referential: Referential,
    val level: Level,
    val contractId: Long?,
    val tags: List<String>? = emptyList()
)

data class ScenarioAuditRequest(
    var name: String,
    var scenario: String,
    var referential: Referential,
    var level: Level,
    val contractId: Long?,
    val tags: List<String>? = emptyList()
)
