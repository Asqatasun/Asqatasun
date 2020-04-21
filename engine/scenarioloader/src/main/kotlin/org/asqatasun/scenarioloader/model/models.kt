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

package org.asqatasun.scenarioloader.model

import com.google.gson.annotations.SerializedName
import java.util.*

class SeleniumIdeScenarioBuilder {
    fun build(url: String) = SeleniumIdeScenario(url = url, urls = listOf(url))
}

data class SeleniumIdeScenario(
    @SerializedName("id")
    val id: String = UUID.randomUUID().toString(),
    @SerializedName("name")
    val name: String = "asqatasun",
    @SerializedName("plugins")
    val plugins: List<Any> = emptyList(),
    @SerializedName("suites")
    val suites: List<Suite> = listOf(Suite()),
    @SerializedName("tests")
    val tests: List<Test> = listOf(Test()),
    @SerializedName("url")
    val url: String,
    @SerializedName("urls")
    val urls: List<String>,
    @SerializedName("version")
    val version: String = "2.0"
) {
    companion object
}

data class Suite(
    @SerializedName("id")
    val id: String = UUID.randomUUID().toString(),
    @SerializedName("name")
    val name: String = "Default Suite",
    @SerializedName("parallel")
    val parallel: Boolean = false,
    @SerializedName("persistSession")
    val persistSession: Boolean = false,
    @SerializedName("tests")
    val tests: List<String> = listOf("038fb62e-034a-4f58-97df-cc55f07cd2ad"),
    @SerializedName("timeout")
    val timeout: Int = 300
)

data class Test(
    @SerializedName("commands")
    val commands: List<Command> = listOf(Command()),
    @SerializedName("id")
    val id: String = "038fb62e-034a-4f58-97df-cc55f07cd2ad",
    @SerializedName("name")
    val name: String = "navigate"
)

data class Command(
    @SerializedName("command")
    val command: String = "open",
    @SerializedName("comment")
    val comment: String = "",
    @SerializedName("id")
    val id: String = UUID.randomUUID().toString(),
    @SerializedName("target")
    val target: String = "/",
    @SerializedName("targets")
    val targets: List<List<String>> = emptyList(),
    @SerializedName("value")
    val value: String = ""
)