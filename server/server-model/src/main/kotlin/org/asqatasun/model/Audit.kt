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
import org.asqatasun.entity.audit.Audit
import org.asqatasun.entity.audit.AuditStatus
import java.util.*

@Schema(
    title = "Audit",
    description = "The audit with its characteristics"
)
data class AuditDto(
    @field:Schema(
        description = "Unique id of the audit",
        example = "42",
    )
    val id: Long,
    // WebResource already documented in Subject.kt
    val subject: WebResourceDto,
    // AuditStatus already described in engine/asqatasun-api/src/main/java/org/asqatasun/entity/audit/AuditStatus.java
    val status: AuditStatus,
    val date: Date,
    @field:Schema(
        description = "Tag(s) associated to the audit",
        example = "monthly_audit_on_localgovs, localgovs_France, 2022-01-01",
        type = "List<String>",
    )
    val tags: List<String>,
    // Referential already documented in Reference.kt
    val referential: Referential,
    @field:Schema(
        description = "Referential level",
        example = "AA",
        type = "String",
    )
    val referentialLevel: String
)

fun Audit.toAuditDto(webResource: WebResourceDto, tags: List<String>, rawParameterValue: String) = AuditDto(
    id,
    webResource,
    status,
    dateOfCreation,
    tags,
    Referential.fromCode(rawParameterValue.split(';')[0])!!,
    rawParameterValue.split(';')[1]
)

