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
package org.asqatasun.model

import io.swagger.v3.oas.annotations.media.Schema
import org.asqatasun.entity.audit.TestSolution
import org.asqatasun.entity.service.statistics.WebResourceStatisticsDataService
import org.asqatasun.entity.statistics.WebResourceStatistics
import org.asqatasun.entity.subject.Site
import org.asqatasun.entity.subject.WebResource
import org.asqatasun.model.WebResourceType.GROUP_OF_PAGE
import org.asqatasun.model.WebResourceType.PAGE

@Schema(
    title = "webResource type",
    description = "Type of the webResource. Can be either PAGE or GROUP_OF_PAGE",
    example = "PAGE",
)
enum class WebResourceType {
    PAGE,
    GROUP_OF_PAGE
}

@Schema(
    title = "Grade",
    description = """
The grade indicates an opinionated level of accessibility.
See <https://doc.asqatasun.org/v5/en/User/Asqatasun-meter/>
    """,
    example = "A"
)
enum class GradeResult {
    A,
    B,
    C,
    D,
    E,
    F
}

@Schema(
    title = "webResource",
    description = "A webResource is the audited object, either a page or a group of pages"
)
data class WebResourceDto(
    @field:Schema(
        description = "Unique id of the webResource",
        example = "42",
    )
    val id: Long,
    // WebResourceType already documented in Subject.kt
    val type: WebResourceType,
    @field:Schema(
        description = "URL to be audited",
        example = "https://en.wikipedia.org/",
    )
    val url: String,
    val nbOfPages: Int = 0,
    // GradeResult already documented in Subject.kt
    val grade: GradeResult?,
    @field:Schema(
        description = "The score expressed as a percentage and defined in <https://doc.asqatasun.org/v5/en/User/Asqatasun-meter/>",
        example = "78.12",
    )
    val mark: Float?,
    @field:Schema(
        description = "Number of occurrence of each testResult (type of result: PASSED, FAILED, ...)",
    )
    val repartitionBySolutionType: List<ResultStat>?,
    val pageResult: List<WebResourceDto>? = null
)

@Schema(
    description = "Number of occurrences of a given testSolution, in the context of a given audit"
)
data class ResultStat(
    val type: TestSolution,
    val number: Int
)

fun WebResource.toWebResourceDto(wrDataService: WebResourceStatisticsDataService): WebResourceDto {
    val stats = wrDataService.getWebResourceStatisticsByWebResource(this)
    return if (this is Site)
        WebResourceDto(id, GROUP_OF_PAGE, url, componentList.size, stats?.let { computeGrade(stats.rawMark) },
            stats?.rawMark, stats?.toResultStats(), componentList.map { it.toWebResourceDto(wrDataService) })
    else
        WebResourceDto(
            id, PAGE, url, 1, stats?.let { computeGrade(stats.rawMark) }, stats?.rawMark,
            stats?.toResultStats()
        )
}

fun WebResourceStatistics.toResultStats() = listOf(
    ResultStat(TestSolution.PASSED, nbOfPassed),
    ResultStat(TestSolution.FAILED, nbOfFailed),
    ResultStat(TestSolution.NEED_MORE_INFO, nbOfNmi),
    ResultStat(TestSolution.NOT_APPLICABLE, nbOfNa),
    ResultStat(TestSolution.NOT_TESTED, nbOfNotTested)
)

fun computeGrade(mark: Float) =
    when {
        mark == 100f -> GradeResult.A
        mark > 90f -> GradeResult.B
        mark > 85f -> GradeResult.C
        mark > 75f -> GradeResult.D
        mark > 60f -> GradeResult.E
        else -> GradeResult.F
    }
