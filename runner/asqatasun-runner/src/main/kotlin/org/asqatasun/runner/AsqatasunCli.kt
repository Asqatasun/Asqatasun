/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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

import org.apache.commons.cli.*
import org.apache.commons.io.FileUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.io.FileNotFoundException
import java.io.PrintStream
import java.net.MalformedURLException
import java.net.URL

/**
 * This class launches Asqatasun with urls passed as arguments by the user.
 *
 * @author jkowalczyk
 */
class AsqatasunCli {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(AsqatasunCli::class.java)

        private var referential = Referential.RGAA_3_0
        private var level = Level.SILVER
        private var auditType = AuditType.PAGE_AUDIT
        private const val DEFAULT_XMS_VALUE = 64
        private val OPTIONS = createOptions()

        @JvmStatic
        fun main(args: Array<String>) {
            val clp: CommandLineParser = DefaultParser()
            try {
                val cl = clp.parse(OPTIONS, args)
                if (cl.hasOption("h")) {
                    printUsage()
                    return
                }
                if (cl.hasOption("f")) {
                    val ffPath = cl.getOptionValue("f")
                    if (isValidPath(ffPath, "f", false)) {
                        System.setProperty("webdriver.firefox.bin", ffPath)
                    } else {
                        printUsage()
                        return
                    }
                }
                if (cl.hasOption("d")) {
                    val display = cl.getOptionValue("d")
                    if (isValidDisplay(display, "d")) {
                        System.setProperty("display", ":$display")
                    } else {
                        printUsage()
                        return
                    }
                }
                if (cl.hasOption("r")) {
                    val ref = cl.getOptionValue("r")
                    if (Referential.fromCode(ref) == null) {
                        println("\nThe referential \"$ref\" doesn't exist.\n")
                        printUsage()
                        return
                    }
                    this.referential = Referential.fromCode(ref)!!
                }
                if (cl.hasOption("l")) {
                    val level = cl.getOptionValue("l")
                    if (Level.fromCode(level) == null) {
                        println("\nThe level \"" + level + "\" doesn't exist.\n");
                        printUsage()
                        return
                    }
                    this.level = Level.fromCode(level)!!
                }
                if (cl.hasOption("t")) {
                    val auditType = cl.getOptionValue("t")
                    if (AuditType.fromCode(auditType) == null) {
                        println("\nThe audit type \"$auditType\" doesn't exist.\n")
                        printUsage()
                        return
                    }
                    this.auditType = AuditType.fromCode(auditType)!!
                }
                if (cl.hasOption("o")) {
                    val outputDir = cl.getOptionValue("o")
                    if (isValidPath(outputDir, "o", true)) {
                        try {
                            System.setOut(PrintStream(outputDir))
                        } catch (ex: FileNotFoundException) {
                            printUsage()
                            return
                        }
                    } else {
                        printUsage()
                        return
                    }
                }
                if (cl.hasOption("x")) {
                    val xmxValue = cl.getOptionValue("x")
                    if (!isValidXmxValue(xmxValue)) {
                        printUsage()
                        return
                    }
                }
                when (auditType) {
                    AuditType.PAGE_AUDIT -> {
                        if (!isValidPageUrl(cl)) {
                            printUsage()
                            return
                        }
                        initSpringContextAndGetRunner()
                            .runAuditOnline(cl.args, referential.code, level.code)
                    }
                    AuditType.SCENARIO_AUDIT -> {
                        if (!isValidScenarioPath(cl)) {
                            printUsage()
                            return
                        }
                        initSpringContextAndGetRunner().runAuditScenario(cl.args.first(), referential.code, level.code)
                    }
                    AuditType.UPLOAD_AUDIT -> {
                        if (!isValidFilePath(cl)) {
                            printUsage()
                            return
                        }
                        initSpringContextAndGetRunner().runAuditUpload(cl.args, referential.code, level.code)
                    }
                    AuditType.SITE_AUDIT -> {
                        println("Functionality is not working on cli interface")
                        printUsage()
                    }

                }
            } catch (ex: ParseException) {
                logger.error("Cannot parse scenario or file", ex)
            }
        }

        /**
         *
         * @return an instance of AsqatasunRunner
         */
        private fun initSpringContextAndGetRunner(): AsqatasunRunner {
            val context: ApplicationContext = AnnotationConfigApplicationContext("org.asqatasun")
            val asqatasunRunner = AsqatasunRunner()
            //the magic: auto-wire the instance with all its dependencies:
            context.autowireCapableBeanFactory.autowireBeanProperties(asqatasunRunner,
                AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true)
            return asqatasunRunner
        }

        /**
         * Create the asqatasun command line interface options
         * @return the options to launch asqatasun cli
         */
        private fun createOptions() =
            Options()
                .addOption(Option.builder("h")
                    .longOpt("help")
                    .desc("Show this message.")
                    .hasArg(false)
                    .required(false)
                    .build())
                .addOption(Option.builder("o")
                    .longOpt("output")
                    .desc("Path to the output result file.")
                    .hasArg(true)
                    .required(false)
                    .build())
                .addOption(Option.builder("f")
                    .longOpt("firefox-bin")
                    .desc("Path to the firefox bin.")
                    .hasArg(true)
                    .required(false)
                    .build())
                .addOption(Option.builder("d")
                    .longOpt("display")
                    .desc("Value of the display")
                    .hasArg(true)
                    .required(false)
                    .build())
                .addOption(Option.builder("r")
                    .longOpt("referential")
                    .desc("""Referential : 
                          - "Aw22" for Accessiweb 2.2
                          - "Rgaa30" for Rgaa 3.0 (default)
                          - "Seo" for SEO 1.0""")
                    .hasArg(true)
                    .required(false)
                    .build())
                .addOption(Option.builder("l")
                    .longOpt("level")
                    .desc("""Level :
                          - "Or" for Gold level or AAA level,
                          - "Ar" for Silver level or AA level (default),
                          - "Bz" for Bronze level or A level""")
                    .hasArg(true)
                    .required(false)
                    .build())
                .addOption(Option.builder("t")
                    .longOpt("audit-type")
                    .desc("""Audit type :
                          - "Page" for page audit (default)
                          - "File" for file audit
                          - "Scenario" for scenario audit
                          - "Site" for site audit""")
                    .hasArg(true)
                    .required(false)
                    .build())
                .addOption(Option.builder("x")
                    .longOpt("xmx-value")
                    .desc("""Xmx value set to the process (without 'M' at the end):
                          - default is 256
                          - must be superior to 64 (Xms value)""")
                    .hasArg(true)
                    .required(false)
                    .build())


        /**
         * Print usage
         */
        private fun printUsage() =
            HelpFormatter().printHelp(
                "./bin/asqatasun.sh [OPTIONS]... [URL OR FILE OR SCENARIO]...\n",
                OPTIONS)

        /**
         *
         * @param path
         * @param option
         * @param testWritable
         * @return whether the given path is valid for the given argument
         */
        private fun isValidPath(path: String, option: String, testWritable: Boolean): Boolean {
            val file = FileUtils.getFile(path)
            if (file.exists() && file.canExecute() && file.canRead()) {
                if (!testWritable) {
                    return true
                } else if (file.canWrite()) {
                    return true
                }
            }
            println("\n$path is an invalid path for $option option.\n")
            return false
        }

        /**
         *
         * @param display
         * @param option
         * @return whether the given display is valid
         */
        private fun isValidDisplay(display: String, option: String): Boolean {
            return try {
                Integer.valueOf(display)
                true
            } catch (nme: NumberFormatException) {
                println("\n$display is invalid for $option option.\n")
                false
            }
        }

        /**
         *
         * @param xmxStr
         * @return whether the given level is valid
         */
        private fun isValidXmxValue(xmxStr: String): Boolean {
            println(xmxStr)
            try {
                val xmxValue = Integer.valueOf(xmxStr)
                if (xmxValue <= DEFAULT_XMS_VALUE) {
                    println("\nThe value of the Xmx value \"$xmxStr\" must be superior to $DEFAULT_XMS_VALUE.\n")
                    return false
                }
            } catch (nfe: NumberFormatException) {
                println("\nThe format of the Xmx value \"$xmxStr\" is incorrect.\n")
                return false
            }
            return true
        }

        /**
         *
         * @param auditType
         * @return whether the given level is valid
         */
        private fun isValidAuditType(auditType: String) = (AuditType.fromCode(auditType) != null)


        /**
         *
         * @param cl
         * @return whether the given level is valid
         */
        private fun isValidPageUrl(cl: CommandLine): Boolean {
            if (cl.argList.isEmpty()) {
                println("\nPlease specify at least one URL\n")
                return false
            }
            for (arg in cl.args) {
                try {
                    URL(arg)
                } catch (ex: MalformedURLException) {
                    println("\nThe URL $arg is malformed\n")
                    return false
                }
            }
            return true
        }

        private fun isValidSiteUrl(cl: CommandLine): Boolean {
            if (cl.argList.isEmpty()) {
                println("\nPlease specify at least one URL\n")
                return false
            }
            if (cl.argList.size > 1) {
                println("\nOnly one URL is expected\n")
                return false
            }
            try {
                URL(cl.args[0])
            } catch (ex: MalformedURLException) {
                println("\nThe URL " + cl.args[0] + " is malformed\n")
                return false
            }
            return true
        }

        private fun isValidFilePath(cl: CommandLine): Boolean {
            if (cl.argList.isEmpty()) {
                println("\nPlease specify at least one file\n")
                return false
            }
            for (arg in cl.args) {
                val file = FileUtils.getFile(arg)
                if (!file.canRead()) {
                    println("\nThe file " + file.absolutePath + " is unreadable.\n")
                    return false
                }
            }
            return true
        }

        private fun isValidScenarioPath(cl: CommandLine): Boolean {
            if (cl.argList.isEmpty()) {
                println("\nPlease specify at least one scenario\n")
                return false
            }
            if (cl.argList.size > 1) {
                println("\nOnly one scenario is expected\n")
                return false
            }
            val scenario = FileUtils.getFile(cl.args[0])
            if (!scenario.canRead()) {
                println("\nThe scenario file is unreadable.\n")
                return false
            }
            return true
        }
    }
}
