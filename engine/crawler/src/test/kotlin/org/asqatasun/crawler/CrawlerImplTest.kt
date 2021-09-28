/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2021  Asqatasun.org
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
package org.asqatasun.crawler

import org.asqatasun.entity.audit.AuditImpl
import org.asqatasun.entity.parameterization.Parameter
import org.asqatasun.entity.parameterization.ParameterElementImpl
import org.asqatasun.entity.parameterization.ParameterImpl
import org.asqatasun.entity.service.subject.WebResourceDataService
import org.asqatasun.entity.subject.PageImpl
import org.asqatasun.entity.subject.SiteImpl
import org.junit.jupiter.api.Test
import org.mockito.Matchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.times

class CrawlerImplTest {

    private val webResourceDataService: WebResourceDataService = Mockito.mock(WebResourceDataService::class.java)
    companion object {
        private const val URL = "https://site-robot.asqatasun.ovh/"
        private const val rootPageUrl = URL
        private const val depthOnePageUrl = URL + "page-1.html"
        private const val depthTwoPageUrl = URL + "page-2.html"
        private const val robotsAccessForbiddenPageUrl = URL + "page-access-forbidden-for-robots.html"
    }

    @Test
    fun crawlSiteDepth0() {
        crawlSite(0, listOf(rootPageUrl), listOf(depthOnePageUrl, depthTwoPageUrl, robotsAccessForbiddenPageUrl))
    }

    @Test
    fun crawlSiteDepth1() {
        crawlSite(1, listOf(rootPageUrl, depthOnePageUrl), listOf(depthTwoPageUrl, robotsAccessForbiddenPageUrl))
    }

    @Test
    fun crawlSiteDepth2() {
        crawlSite(2, listOf(rootPageUrl, depthOnePageUrl, depthTwoPageUrl), listOf(robotsAccessForbiddenPageUrl))
    }

    @Test
    fun crawlSiteDepth2AndNotRespectRobotsTxtDirectives() {
        crawlSite(2, listOf(rootPageUrl, depthOnePageUrl, depthTwoPageUrl, robotsAccessForbiddenPageUrl), listOf(), false)
    }

    private fun crawlSite(depth: Int, calledUrlList: List<String>, neverCalledUrlList: List<String>, respectRobotsTxt: Boolean = true) {
        val site = SiteImpl()
        Mockito.`when`(webResourceDataService.createSite(URL)).thenReturn(site)
        Mockito.`when`(webResourceDataService.createPage(anyString())).thenReturn(PageImpl())
        Mockito.`when`(webResourceDataService.saveOrUpdate(site)).thenReturn(site)
        val audit = AuditImpl()
        audit.parameterSet = setParameters(depth, respectRobotsTxt)
        val crawler = CrawlerImpl(audit, URL, webResourceDataService,
            "", "", "", "", emptyList())
        crawler.run()
        calledUrlList.forEach {
            Mockito.verify(webResourceDataService, times(1)).createPage(it)
        }
        neverCalledUrlList.forEach {
            Mockito.verify(webResourceDataService, never()).createPage(it)
        }
    }

    private fun setParameters(depth: Int, respectRobotsTxt: Boolean): Set<Parameter> {
        val param1 = ParameterImpl()
        param1.value = "10"
        val paramElem1 = ParameterElementImpl()
        paramElem1.parameterElementCode = "MAX_DOCUMENTS"
        param1.parameterElement = paramElem1
        val param2 = ParameterImpl()
        param2.value = depth.toString()
        val paramElem2 = ParameterElementImpl()
        paramElem2.parameterElementCode = "DEPTH"
        param2.parameterElement = paramElem2
        val param3 = ParameterImpl()
        param3.value = ""
        val paramElem3 = ParameterElementImpl()
        paramElem3.parameterElementCode = "INCLUSION_REGEXP"
        param3.parameterElement = paramElem3
        val param4 = ParameterImpl()
        param4.value = ""
        val paramElem4 = ParameterElementImpl()
        paramElem4.parameterElementCode = "EXCLUSION_REGEXP"
        param4.parameterElement = paramElem4
        val param5 = ParameterImpl()
        param5.value = "600"
        val paramElem5 = ParameterElementImpl()
        paramElem5.parameterElementCode = "MAX_DURATION"
        param5.parameterElement = paramElem5
        val param6 = ParameterImpl()
        param6.value = respectRobotsTxt.toString()
        val paramElem6 = ParameterElementImpl()
        paramElem6.parameterElementCode = "ROBOTS_TXT_ACTIVATION"
        param6.parameterElement = paramElem6
        return setOf(param1, param2, param3, param4, param5, param6)
    }

}
