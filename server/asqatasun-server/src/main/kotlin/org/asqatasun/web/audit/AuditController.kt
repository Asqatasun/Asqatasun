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

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import org.apache.commons.io.output.ByteArrayOutputStream
import org.asqatasun.entity.audit.TestSolution
import org.asqatasun.entity.service.audit.AuditDataService
import org.asqatasun.entity.service.statistics.WebResourceStatisticsDataService
import org.asqatasun.model.Level
import org.asqatasun.model.Referential
import org.asqatasun.model.toAuditDto
import org.asqatasun.model.toWebResourceDto
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.PrintWriter
import java.util.*
import javax.servlet.http.HttpServletRequest

@OpenAPIDefinition(
    info = Info(
        title = "Asqatasun API",
        version = "0.5.0",
        description = "Manage web-accessibility audits with Asqatasun API",
        license = License(name = "aGPL", url = "https://gitlab.com/asqatasun/Asqatasun/-/blob/master/LICENSE"),
        contact = Contact(
            url = "https://forum.asqatasun.org/",
            name = "Asqatasun team",
            email = "asqatasun@asqatasun.org"
        )
    )
)

@RestController
@RequestMapping("api/v0/audit")
class AuditController(
    private val auditDataService: AuditDataService,
    private val webResourceStatisticsDataService: WebResourceStatisticsDataService,
    private val auditService: AuditService
) {

    @Tag(name = "Audit", description = "Audit of any kind (page, site, scenario, file)")
    @Operation(summary = "Get all audits")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    fun getAll() = auditDataService.findAll()
        .map { audit ->
            audit.toAuditDto(
                audit.subject.toWebResourceDto(webResourceStatisticsDataService),
                audit.tagList.map { tag -> tag.value },
                audit.parameterSet.filter { parameter -> parameter.parameterElement.parameterElementCode == "LEVEL" }[0].value
            )
        }

    @Tag(name = "Audit", description = "Audit of any kind (page, site, scenario, file)")
    @Operation(summary = "Get an audit by its id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) =
        Optional.ofNullable(auditDataService.read(id)).map { audit ->
            audit.toAuditDto(
                audit.subject.toWebResourceDto(webResourceStatisticsDataService),
                audit.tagList.map { tag -> tag.value },
                audit.parameterSet.filter { parameter -> parameter.parameterElement.parameterElementCode == "LEVEL" }[0].value
            )
        }.orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Audit Not Found")
        }!!

    @Tag(name = "Audit", description = "Audit of any kind (page, site, scenario, file)")
    @Operation(
        summary = "Get audits by tag(s)",
        description = "Get all audits tagged with the specified tag(s). Several tags may be specified, separated by a comma"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tags/{tags}")
    fun getByTags(@PathVariable tags: List<String>) =
        Optional.ofNullable(auditDataService.findAllByTags(tags)).map {
            it.map { audit ->
                audit.toAuditDto(
                    audit.subject.toWebResourceDto(webResourceStatisticsDataService),
                    audit.tagList.map { tag -> tag.value },
                    audit.parameterSet.filter { parameter -> parameter.parameterElement.parameterElementCode == "LEVEL" }[0].value
                )
            }
        }.orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Audit Not Found")
        }!!

    @Tag(name = "Audit > Page", description = "Audit on a single page")
    @Operation(summary = "Run a page audit, according to the specified parameters")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = ["/page/run"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun auditPage(@RequestBody auditRequest: PageAuditRequest, request: HttpServletRequest) =
        auditService.runPageAudit(auditRequest, request.remoteAddr)

    @Tag(name = "Audit > Site", description = "Audit on an entire website")
    @Operation(summary = "Run an audit on a whole website, according to the specified parameters")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = ["/site/run"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun auditSite(@RequestBody auditRequest: SiteAuditRequest, request: HttpServletRequest) =
        auditService.runSiteAudit(auditRequest, request.remoteAddr)

    @Tag(name = "Audit > Scenario", description = "Audit on a set of webpages with eventual actions")
    @Operation(
        summary = "Loads a scenario, and runs the audit against it",
        description = """
The scenario is loaded, and forgotten once run. It is not stored in database.
This means if you want to run a scenario twice, you need to send it again as a parameter. 
The scenario needs to be escaped with a tool such as <https://jsonformatter.org/json-escape>
        """
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = ["/scenario/run"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    @Throws(IOException::class)
    fun auditScenario(@RequestBody auditRequest: ScenarioAuditRequest, request: HttpServletRequest) =
        auditService.runScenarioAudit(auditRequest, request.remoteAddr)

    @Tag(name = "Audit > Page", description = "Audit on a single page")
    @Operation(summary = "List test results of the specified audit, organized by themes and criteria")
    @GetMapping(value = ["/{id}/tests"], produces = ["text/csv"])
    fun exportCSV(@PathVariable id: Long): ResponseEntity<Resource> {

        val csvBody = ArrayList<List<String>>()
        var csvHeader = arrayOf("\"Theme\"", "\"Criteria\"", "\"Test\"")

        Optional.ofNullable(auditDataService.read(id)).map { audit ->
            csvHeader = csvHeader.plus('"' + audit.subject.url + '"')
            audit.netResultList
                .sortedBy { result ->
                    result.test.rank
                }
                .forEach { result ->
                    val descriptor = result.test.label.split('.')
                    csvBody.add(
                        listOf(
                            descriptor[0],
                            descriptor[1],
                            descriptor[2],
                            testSolutionToCsvResult(result.value as TestSolution)
                        )
                    )
                }

        }.orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Audit Not Found")
        }!!

        val byteArrayOutputStream =
            ByteArrayOutputStream()
                .use { out ->
                    // defining the CSV printer
                    CSVPrinter(
                        PrintWriter(out),
                        // withHeader is optional
                        CSVFormat.DEFAULT.withHeader(*csvHeader).withQuote(null)
                    ).use { csvPrinter ->
                        // populating the CSV content
                        csvBody.forEach { record ->
                            csvPrinter.printRecord(record)
                        }

                        // writing the underlying stream
                        csvPrinter.flush()

                        ByteArrayInputStream(out.toByteArray())
                    }
                }

        val fileInputStream = InputStreamResource(byteArrayOutputStream)
        // setting HTTP headers
        val headers = HttpHeaders()
        // defining the custom Content-Type
        headers.set(HttpHeaders.CONTENT_TYPE, "text/csv")

        return ResponseEntity(
            fileInputStream,
            headers,
            HttpStatus.OK
        )
    }

}

@Schema(
    title = "Site Audit Request",
)
data class SiteAuditRequest(
    val url: String,
    val referential: Referential,
    val level: Level,
    val depth: Int = 10,
    val maxPages: Int = 1000,
    val maxDuration: Int = 3600,
    val exclusionRegexp: String = "",
    val inclusionRegexp: String = "",
    val robotsTxtActivation: Boolean = true,
    val contractId: Long?,
    val tags: List<String>? = emptyList()
)

@Schema(
    title = "Page Audit Request",
)
data class PageAuditRequest(
    val urls: List<String>,
    // Referential already documented in Reference.kt
    val referential: Referential,
    // Level already documented in Reference.kt
    val level: Level,
    val contractId: Long?,
    val tags: List<String>? = emptyList()
)

@Schema(
    title = "Scenario Audit Request",
    description = """
A scenario is a set of pages (and actions) to be audited.
See <https://doc.asqatasun.org/v5/en/User/Kinds-of-audit/Scenario-audit/>
    """
)
data class ScenarioAuditRequest(
    @field:Schema(
        description = "Name of the scenario",
        example = "Browsing Wikipedia",
        type = "String",
    )
    var name: String,
    @field:Schema(
        description = """
Content of the scenario, in JSON format.
Caution: if the API call is itself in JSON, this field MUST be JSON-escaped.
A tool like <https://jsonformatter.org/json-escape> may be useful.
        """,
        example = "{\\n  \\\"id\\\": \\\"39429fc5-89ab-4270-bb0f-640c5c9390b3\\\",\\n  \\\"version\\\": \\\"2.0\\\",\\n  \\\"name\\\": \\\"Wikipedia homepage\\\",\\n  \\\"url\\\": \\\"https://en.wikipedia.org\\\",\\n  \\\"tests\\\": [{\\n    \\\"id\\\": \\\"9d3869a0-60ac-4a1b-bd3f-0f0f65f302d4\\\",\\n    \\\"name\\\": \\\"Open homepage\\\",\\n    \\\"commands\\\": [{\\n      \\\"id\\\": \\\"48d5b89b-1421-416e-a029-bb1c208221ef\\\",\\n      \\\"comment\\\": \\\"\\\",\\n      \\\"command\\\": \\\"open\\\",\\n      \\\"target\\\": \\\"/wiki/Main_Page\\\",\\n      \\\"targets\\\": [],\\n      \\\"value\\\": \\\"\\\"\\n    }]\\n  }],\\n  \\\"suites\\\": [{\\n    \\\"id\\\": \\\"34095dff-101a-4db9-a045-bbfe6a7c9c44\\\",\\n    \\\"name\\\": \\\"Wikipedia English\\\",\\n    \\\"persistSession\\\": false,\\n    \\\"parallel\\\": false,\\n    \\\"timeout\\\": 300,\\n    \\\"tests\\\": [\\\"9d3869a0-60ac-4a1b-bd3f-0f0f65f302d4\\\"]\\n  }],\\n  \\\"urls\\\": [\\\"https://en.wikipedia.org/\\\"],\\n  \\\"plugins\\\": []\\n}",
        type = "String",
    )
    var scenario: String,
    // Referential already documented in Reference.kt
    var referential: Referential,
    // Level already documented in Reference.kt
    var level: Level,
    @field:Schema(
        description = "Id of the contract (project) holding the scenario",
        example = "1",
        type = "Long",
    )
    val contractId: Long?,
    val tags: List<String>? = emptyList()
)

fun testSolutionToCsvResult(testSolution: TestSolution) =
    when (testSolution) {
        TestSolution.FAILED -> "nc"
        TestSolution.PASSED -> "c"
        TestSolution.NEED_MORE_INFO -> "pq"
        TestSolution.NOT_APPLICABLE -> "na"
        TestSolution.DETECTED -> "pq"
        TestSolution.NOT_TESTED -> "nt"
        TestSolution.SUSPECTED_FAILED -> "pq"
        TestSolution.SUSPECTED_PASSED -> "pq"
    }
