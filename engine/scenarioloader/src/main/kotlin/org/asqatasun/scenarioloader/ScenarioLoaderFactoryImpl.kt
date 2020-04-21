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
package org.asqatasun.scenarioloader

import org.apache.commons.io.IOUtils
import org.asqatasun.entity.service.audit.ContentDataService
import org.asqatasun.entity.service.audit.PreProcessResultDataService
import org.asqatasun.entity.service.subject.WebResourceDataService
import org.asqatasun.entity.subject.WebResource
import org.asqatasun.util.factory.DateFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

/**
 *
 * @author enzolalay
 */
@Component
class ScenarioLoaderFactoryImpl(@Value("\${app.engine.loader.timeout:30}") private val pageLoadDriverTimeout: Int,
                                @Value("\${colorExtractor:/js/jsExtractor.js}") private val colorExtractor: String,
                                @Value("\${doctypeExtractor:/js/doctypeExtractor.js}") private val doctypeExtractor: String,
                                private val remoteWebDriverFactory: RemoteWebDriverFactory,
                                private val webResourceDataService: WebResourceDataService,
                                private val contentDataService: ContentDataService,
                                private val preProcessResultDataService: PreProcessResultDataService,
                                private val dateFactory: DateFactory) : ScenarioLoaderFactory {

    private lateinit var jsScriptMap: Map<String, String>

    @PostConstruct
    fun initScriptMap() {
        jsScriptMap =
            mapOf("colorExtractor" to IOUtils.toString(javaClass.getResourceAsStream(colorExtractor), "UTF-8"),
                  "doctypeExtractor" to IOUtils.toString(javaClass.getResourceAsStream(doctypeExtractor), "UTF-8"))
    }

    override fun create(mainWebResource: WebResource, scenario: String) =
        ScenarioLoaderImpl(mainWebResource, scenario, remoteWebDriverFactory, webResourceDataService,
            contentDataService, preProcessResultDataService, dateFactory, jsScriptMap, pageLoadDriverTimeout)

}