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
package org.asqatasun.runner

import org.apache.commons.io.FileUtils
import org.asqatasun.entity.audit.Audit
import org.asqatasun.entity.audit.ProcessRemark
import org.asqatasun.entity.audit.ProcessResult
import org.asqatasun.entity.service.audit.AuditDataService
import org.asqatasun.entity.service.audit.ProcessRemarkDataService
import org.asqatasun.entity.service.audit.ProcessResultDataService
import org.asqatasun.entity.service.parameterization.ParameterDataService
import org.asqatasun.entity.service.statistics.WebResourceStatisticsDataService
import org.asqatasun.entity.service.subject.WebResourceDataService
import org.asqatasun.entity.subject.Site
import org.asqatasun.entity.subject.WebResource
import org.asqatasun.service.AuditService
import org.asqatasun.service.AuditServiceListener
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import java.io.File
import java.io.IOException
import java.util.*

/**
 * This class launches Asqatasun with urls passed as arguments by the user.
 *
 * @author jkowalczyk
 */
class AsqatasunRunner : AuditServiceListener {
    @Autowired
    private val auditService: AuditService? = null
    @Autowired
    private val auditDataService: AuditDataService? = null
    @Autowired
    private val webResourceDataService: WebResourceDataService? = null
    @Autowired
    private val webResourceStatisticsDataService: WebResourceStatisticsDataService? = null
    @Autowired
    private val processResultDataService: ProcessResultDataService? = null
    @Autowired
    private val processRemarkDataService: ProcessRemarkDataService? = null
    @Autowired
    private val parameterDataService: ParameterDataService? = null

    fun runAuditOnline(urlTab: Array<String?>, ref: String?, level: String?) {
        LoggerFactory.getLogger(this.javaClass).info("runAuditOnline")
        initServices()
        val paramSet = parameterDataService!!.getParameterSetFromAuditLevel(ref, level)
        val pageUrlList = urlTab.asList()
        if (pageUrlList.size > 1) {
            auditService!!.auditSite("site:" + pageUrlList[0], pageUrlList, paramSet)
        } else {
            auditService!!.auditPage(pageUrlList[0], parameterDataService.getAuditPageParameterSet(paramSet))
        }
    }

    fun runAuditScenario(scenarioFilePath: String?, ref: String?, level: String?) {
        initServices()
        val paramSet = parameterDataService!!.getParameterSetFromAuditLevel(ref, level)
        println(scenarioFilePath)
        val scenarioFile = File(scenarioFilePath)
        try {
            auditService!!.auditScenario(scenarioFile.name, readFile(scenarioFile), paramSet)
        } catch (ex: IOException) {
            println("Unreadable scenario file")
            System.exit(0)
        }
    }

    fun runAuditUpload(uploadFilePath: Array<String?>, ref: String?, level: String?) {
        initServices()
        val paramSet = parameterDataService!!.getParameterSetFromAuditLevel(ref, level)
        val fileMap: MutableMap<String, String> = HashMap()
        for (file in uploadFilePath.asList()) {
            val uploadFile = File(file)
            try {
                fileMap[uploadFile.name] = readFile(uploadFile)
            } catch (ex: IOException) {
                println("Unreadable upload file")
                System.exit(0)
            }
        }
        auditService!!.auditPageUpload(fileMap, paramSet)
    }

    override fun auditCompleted(audit: Audit) {
        var audit = audit
        audit = auditDataService!!.read(audit.id)
        val processResultList = processResultDataService!!.getNetResultFromAudit(audit) as List<ProcessResult>
        println("Audit terminated with success at " + audit.dateOfCreation)
        println("Audit Id : " + audit.id)
        println("")
        println("RawMark : " + webResourceStatisticsDataService!!.getWebResourceStatisticsByWebResource(audit.subject).rawMark + "%")
        println("WeightedMark : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(audit.subject).mark + "%")
        println("Nb Passed : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(audit.subject).nbOfPassed)
        println("Nb Failed test : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(audit.subject).nbOfInvalidTest)
        println("Nb Failed occurences : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(audit.subject).nbOfFailedOccurences)
        println("Nb Pre-qualified : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(audit.subject).nbOfNmi)
        println("Nb Not Applicable : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(audit.subject).nbOfNa)
        println("Nb Not Tested : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(audit.subject).nbOfNotTested)
        if (audit.subject is Site) {
            val numberOfChildWebResource = webResourceDataService!!.getNumberOfChildWebResource(audit.subject).toInt()
            for (i in 0 until numberOfChildWebResource) {
                displayWebResourceResult(webResourceDataService.getWebResourceFromItsParent(audit.subject, i, 1).iterator().next(),
                    processResultList)
            }
        } else {
            displayWebResourceResult(audit.subject, processResultList)
        }
        println("")
        System.exit(0)
    }

    private fun displayWebResourceResult(wr: WebResource, processResultList: List<ProcessResult>) {
        println("")
        println("Subject : " + wr.url)
        val prList: MutableList<ProcessResult> = ArrayList()
        for (netResult in processResultList) {
            if (netResult.subject.url.equals(wr.url, ignoreCase = true)) {
                prList.add(netResult)
            }
        }
        println("RawMark : " + webResourceStatisticsDataService!!.getWebResourceStatisticsByWebResource(wr).rawMark + "%")
        println("WeightedMark : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(wr).mark + "%")
        println("Nb Passed : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(wr).nbOfPassed)
        println("Nb Failed test : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(wr).nbOfInvalidTest)
        println("Nb Failed occurences : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(wr).nbOfFailedOccurences)
        println("Nb Pre-qualified : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(wr).nbOfNmi)
        println("Nb Not Applicable : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(wr).nbOfNa)
        println("Nb Not Tested : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(wr).nbOfNotTested)
        prList.sortWith(Comparator { t, t1 -> t.test.id.compareTo(t1.test.id) })
        for (result in prList) {
            println(result.test.code + ": " + result.value)
            val processRemarkList = processRemarkDataService!!.findProcessRemarksFromProcessResult(result, -1) as Set<ProcessRemark>
            for (processRemark in processRemarkList) {
                println(" ->  " + processRemark.issue
                    + " " + processRemark.messageCode)
                for (el in processRemark.elementList) {
                    println("    -> " + el.evidence.code + ":" + el.value)
                }
            }
        }
    }

    override fun auditCrashed(audit: Audit, exception: Exception) {
        exception.printStackTrace()
        println("crash (id+message): " + audit.id + " " + exception.fillInStackTrace())
    }

    /**
     *
     */
    private fun initServices() {
        auditService!!.add(this)
    }

    /**
     *
     * @return
     */
    @Throws(IOException::class)
    private fun readFile(file: File) =  // #57 issue quick fix.......
         FileUtils.readFileToString(file, "UTF-8").replace("\"formatVersion\": 2", "\"formatVersion\":1")
            .replace("\"formatVersion\":2", "\"formatVersion\":1")

}
