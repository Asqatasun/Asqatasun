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

import org.asqatasun.entity.audit.Content
import org.asqatasun.entity.subject.WebResource
import org.asqatasun.scenarioloader.ScenarioLoaderFactory
import org.springframework.stereotype.Service

/**
 *
 * @author jkowalczyk
 */
@Service
class ScenarioLoaderServiceImpl(private val scenarioLoaderFactory: ScenarioLoaderFactory): ScenarioLoaderService{

    override fun loadScenario(webResource: WebResource, scenarioFile: String?): List<Content> =
        scenarioLoaderFactory.create(webResource, scenarioFile).run { run(); result}

}