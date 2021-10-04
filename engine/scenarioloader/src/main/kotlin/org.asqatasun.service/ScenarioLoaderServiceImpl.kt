/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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
package org.asqatasun.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.asqatasun.entity.audit.Content
import org.asqatasun.entity.service.subject.WebResourceDataService
import org.asqatasun.entity.subject.Page
import org.asqatasun.entity.subject.Site
import org.asqatasun.entity.subject.WebResource
import org.asqatasun.scenarioloader.ScenarioLoaderFactory
import org.asqatasun.scenarioloader.model.SeleniumIdeScenarioBuilder
import org.asqatasun.util.FileNaming
import org.springframework.stereotype.Service
import java.net.URL

/**
 *
 * @author jkowalczyk
 */
@Service("scenarioLoaderService")
class ScenarioLoaderServiceImpl(private val scenarioLoaderFactory: ScenarioLoaderFactory,
                                private val webResourceDataService: WebResourceDataService): ScenarioLoaderService{

    companion object {
        const val CHUNK_SIZE = 50
    }

    override fun loadScenario(webResource: WebResource, scenarioFile: String?): List<Content> =
        scenarioLoaderFactory.create(webResource, scenarioFile, 0).run { run(); result}

    override fun loadScenario(webResource: WebResource, urlList: List<URL>, startRank: Int): List<Content> {
        val scenario = ObjectMapper().writeValueAsString(SeleniumIdeScenarioBuilder().build(webResource.url, urlList))
        return scenarioLoaderFactory.create(webResource, scenario, startRank).run { run(); result}
    }

    override fun loadScenario(webResource: WebResource): List<Content> {
        return when (webResource) {
            is Page -> {
                val scenario = ObjectMapper().writeValueAsString(SeleniumIdeScenarioBuilder().build(webResource.url))
                loadScenario(webResource, scenario)
            }
            is Site -> {
                val nbUrls = webResourceDataService.getChildWebResourceCount(webResource)
                var counter = 0
                while (counter < nbUrls) {
                    val urlList =
                        webResourceDataService.getWebResourceFromItsParent(webResource, counter, CHUNK_SIZE)
                            .map { URL(FileNaming.addProtocolToUrl(it.url)) }
                    // loadScenario returns a list of Content, but as each content is persisted on the fly, we don't need
                    // to store the returned list, and thus avoid keeping it in memory (in case of big site audits)
                    loadScenario(webResource, urlList, counter + 1)
                    counter += CHUNK_SIZE
                }
                emptyList()
            }
            else -> emptyList()
        }
    }

}

