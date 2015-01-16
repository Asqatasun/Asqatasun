/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.cli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import org.apache.commons.cli.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.audit.ProcessRemarkDataService;
import org.opens.tanaguru.entity.service.audit.ProcessResultDataService;
import org.opens.tanaguru.entity.service.parameterization.ParameterDataService;
import org.opens.tanaguru.entity.service.parameterization.ParameterElementDataService;
import org.opens.tanaguru.entity.service.reference.TestDataService;
import org.opens.tanaguru.entity.service.reference.ThemeDataService;
import org.opens.tanaguru.entity.service.statistics.ThemeStatisticsDataService;
import org.opens.tanaguru.entity.service.statistics.WebResourceStatisticsDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.service.AuditService;
import org.opens.tanaguru.service.AuditServiceListener;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * This class launches Tanaguru with urls passed as arguments by the user.
 *
 * @author jkowalczyk
 */
public class Tanaguru implements AuditServiceListener {

    private static final String APPLICATION_CONTEXT_FILE_PATH = "conf/context/application-context.xml";
    
    private static final String PAGE_AUDIT = "Page";
    private static final String SCENARIO_AUDIT = "Scenario";
    private static final String FILE_AUDIT = "File";
    private static final String SITE_AUDIT = "File";
    
    private static final String AW22_REF = "Aw22";
    private static final String RGAA22_REF = "Rgaa22";
    private static String REF = AW22_REF;
    
    private static final String BRONZE_LEVEL = "Bz";
    private static final String A_LEVEL = "A";
    private static final String SILVER_LEVEL = "Ar";
    private static final String AA_LEVEL = "AA";
    private static final String GOLD_LEVEL = "Or";
    private static final String AAA_LEVEL = "AAA";
   
    private static final String LEVEL_1 = "LEVEL_1";
    private static final String LEVEL_2 = "LEVEL_1";
    private static final String LEVEL_3 = "LEVEL_3";
    
    private static String LEVEL = SILVER_LEVEL;
    
    private static final String LEVEL_PARAMETER_ELEMENT_CODE = "LEVEL";
    
    private static final Options OPTIONS = createOptions();
    
    private static String AUDIT_TYPE = PAGE_AUDIT;
    
    private static String TANAGURU_HOME;
    
    private AuditService auditService = null;
    private AuditDataService auditDataService = null;
    private WebResourceDataService webResourceDataService = null;
    private WebResourceStatisticsDataService webResourceStatisticsDataService = null;
    private ProcessResultDataService processResultDataService = null;
    private ProcessRemarkDataService processRemarkDataService = null;
    private ParameterDataService parameterDataService = null;
    private ParameterElementDataService parameterElementDataService = null;
 
    public static void main(String[] args) {
        if (args == null) {
            return;
        }
        TANAGURU_HOME = System.getenv("TANAGURU_PATH");
        CommandLineParser clp = new BasicParser();
        try {
            CommandLine cl = clp.parse(OPTIONS, args);
            if (cl.hasOption("h")) {
                printUsage();
                return;
            }
            if (cl.hasOption("f")) {
                String ffPath = cl.getOptionValue("f");
                if (isValidPath(ffPath, "f", false)) {
                    System.setProperty("webdriver.firefox.bin", ffPath);
                } else {
                    printUsage();
                    return;
                }
            }
            if (cl.hasOption("d")) {
                String display = cl.getOptionValue("d");
                if (isValidDisplay(display, "d")) {
                    System.setProperty("display", ":"+display);
                } else {
                    printUsage();
                    return;
                }
            }
            if (cl.hasOption("r")) {
                String ref = cl.getOptionValue("r");
                if (isValidReferential(ref)) {
                    REF = ref;
                } else {
                    printUsage();
                    return;
                }
            }
            if (cl.hasOption("l")) {
                String level = cl.getOptionValue("l");
                if (isValidLevel(level)) {
                    LEVEL = level;
                } else {
                    printUsage();
                    return;
                }
            }
            if (cl.hasOption("o")) {
                String outputDir = cl.getOptionValue("o");
                if (isValidPath(outputDir, "o", true)) {
                    try {
                        System.setOut(new PrintStream(outputDir));
                    } catch (FileNotFoundException ex) {
                        printUsage();
                        return;
                    }
                } else {
                    printUsage();
                    return;
                }
            }
            if (cl.hasOption("t")) {
                String auditType = cl.getOptionValue("t");
                if (!isValidAuditType(auditType)) {
                    printUsage();
                    return;
                } else {
                    AUDIT_TYPE = auditType;
                }
            }
            if (AUDIT_TYPE.equalsIgnoreCase(PAGE_AUDIT)) {
                if (!isValidPageUrl(cl)) {
                    printUsage();
                } else {
                    new Tanaguru().runAuditOnline(cl.getArgs(),TANAGURU_HOME, REF, LEVEL);
                }
            } else if (AUDIT_TYPE.equalsIgnoreCase(SCENARIO_AUDIT)) {
                if (!isValidScenarioPath(cl)) {
                    printUsage();
                } else {
                    new Tanaguru().runAuditScenario(cl.getArgs()[0],TANAGURU_HOME, REF, LEVEL);
                }
            } else if (AUDIT_TYPE.equalsIgnoreCase(FILE_AUDIT)) {
                if (!isValidFilePath(cl)) {
                    printUsage();
                } else {
                    new Tanaguru().runAuditUpload(cl.getArgs(),TANAGURU_HOME, REF, LEVEL);
                }
            } else if (AUDIT_TYPE.equalsIgnoreCase(SITE_AUDIT)) {
                if (!isValidSiteUrl(cl)) {
                    printUsage();
                } else {
                    System.out.println("Functionnality is not working on cli interface");
                    printUsage();
                }
            }
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(Tanaguru.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Tanaguru() {
        super();
    }

    public void runAuditOnline(String[] urlTab, String tanaguruHome, String ref, String level) {
        Logger.getLogger(this.getClass()).info("runAuditOnline");
        initServices(tanaguruHome);

        Set<Parameter> paramSet = getParameterSetFromAuditLevel(ref, level);

        List<String> pageUrlList = Arrays.asList(urlTab);

        if (pageUrlList.size() > 1) {
            auditService.auditSite("site:" + pageUrlList.get(0), pageUrlList, paramSet);
        } else {
            auditService.auditPage(pageUrlList.get(0), getAuditPageParameterSet(paramSet));
        }
    }

    public void runAuditScenario(String scenarioFilePath, String tanaguruHome, String ref, String level) {
        initServices(tanaguruHome);

        Set<Parameter> paramSet = getParameterSetFromAuditLevel(ref, level);
        System.out.println(scenarioFilePath);
        File scenarioFile = new File(scenarioFilePath);
        try {
            auditService.auditScenario(scenarioFile.getName(), readFile(scenarioFile), paramSet);
        } catch (IOException ex) {
            System.out.println("Unreadable scenario file");
            System.exit(0);
        }
    }
    
    public void runAuditUpload(String[] uploadFilePath, String tanaguruHome, String ref, String level) {
        initServices(tanaguruHome);

        Set<Parameter> paramSet = getParameterSetFromAuditLevel(ref, level);

        Map<String, String> fileMap = new HashMap<>();
        for (String file : Arrays.asList(uploadFilePath)) {
            File uploadFile = new File(file);
            try {
               fileMap.put(uploadFile.getName(), readFile(uploadFile));
            } catch (IOException ex) {
                System.out.println("Unreadable upload file");
                System.exit(0);
            }
        }
        auditService.auditPageUpload(fileMap, paramSet);
    }

    @Override
    public void auditCompleted(Audit audit) {
        audit = auditDataService.read(audit.getId());
        List<ProcessResult> processResultList = (List<ProcessResult>) processResultDataService.getNetResultFromAudit(audit);

        System.out.println("Audit terminated with success at " + audit.getDateOfCreation());
        System.out.println("Audit Id : " + audit.getId());
        System.out.println("");
        System.out.println("RawMark : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(audit.getSubject()).getRawMark() + "%");
        System.out.println("WeightedMark : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(audit.getSubject()).getMark() + "%");
        System.out.println("Nb Passed : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(audit.getSubject()).getNbOfPassed());
        System.out.println("Nb Failed test : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(audit.getSubject()).getNbOfInvalidTest());
        System.out.println("Nb Failed occurences : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(audit.getSubject()).getNbOfFailedOccurences());
        System.out.println("Nb Pre-qualified : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(audit.getSubject()).getNbOfNmi());
        System.out.println("Nb Not Applicable : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(audit.getSubject()).getNbOfNa());
        System.out.println("Nb Not Tested : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(audit.getSubject()).getNbOfNotTested());

        if (audit.getSubject() instanceof Site) {
            int numberOfChildWebResource = webResourceDataService.getNumberOfChildWebResource(audit.getSubject()).intValue();
            for (int i = 0; i < numberOfChildWebResource; i++) {
                displayWebResourceResult(webResourceDataService.getWebResourceFromItsParent(audit.getSubject(), i, 1).iterator().next(),
                        processResultList);
            }
        } else {
            displayWebResourceResult(audit.getSubject(), processResultList);
        }
        System.out.println("");
        System.exit(0);
    }

    private void displayWebResourceResult(WebResource wr, List<ProcessResult> processResultList) {
        System.out.println("");
        System.out.println("Subject : " + wr.getURL());
        List<ProcessResult> prList = new ArrayList<>();
        for (ProcessResult netResult : processResultList) {
            if (netResult.getSubject().getURL().equalsIgnoreCase(wr.getURL())) {
                prList.add(netResult);
            }
        }
        System.out.println("RawMark : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(wr).getRawMark() + "%");
        System.out.println("WeightedMark : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(wr).getMark() + "%");
        System.out.println("Nb Passed : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(wr).getNbOfPassed());
        System.out.println("Nb Failed test : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(wr).getNbOfInvalidTest());
        System.out.println("Nb Failed occurences : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(wr).getNbOfFailedOccurences());
        System.out.println("Nb Pre-qualified : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(wr).getNbOfNmi());
        System.out.println("Nb Not Applicable : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(wr).getNbOfNa());
        System.out.println("Nb Not Tested : " + webResourceStatisticsDataService.getWebResourceStatisticsByWebResource(wr).getNbOfNotTested());
        
        Collections.sort(prList, new Comparator<ProcessResult>() {
            @Override
            public int compare(ProcessResult t, ProcessResult t1) {
                return t.getTest().getId().compareTo(t1.getTest().getId());
            }
        }) ;
        for (ProcessResult result : prList) {
            System.out.println(result.getTest().getCode() + ": " + result.getValue());
            Set<ProcessRemark> processRemarkList = (Set<ProcessRemark>) processRemarkDataService.findProcessRemarksFromProcessResult(result, -1);
            for (ProcessRemark processRemark : processRemarkList) {
                System.out.println(" ->  " + processRemark.getIssue()
                        + " " + processRemark.getMessageCode());
                for (EvidenceElement el : processRemark.getElementList()) {
                    System.out.println("    -> " + el.getEvidence().getCode() + ":" + el.getValue());
                }
            }
        }
    }

    @Override
    public void auditCrashed(Audit audit, Exception exception) {
        exception.printStackTrace();
        System.out.println("crash (id+message): " + audit.getId() + " " + exception.fillInStackTrace());
    }

    /**
     * The default parameter set embeds a depth value that corresponds to the
     * site audit. We need here to replace this parameter by a parameter value
     * equals to 0.
     *
     * @return
     */
    private Set<Parameter> getAuditPageParameterSet(Set<Parameter> defaultParameterSet) {
        ParameterElement parameterElement = parameterElementDataService.getParameterElement("DEPTH");
        Parameter depthParameter = parameterDataService.getParameter(parameterElement, "0");
        Set<Parameter> auditPageParamSet = parameterDataService.updateParameter(
                defaultParameterSet, depthParameter);
        return auditPageParamSet;
    }

    /**
     *
     * @param tanaguruHome
     */
    private void initServices(String tanaguruHome) {
        ApplicationContext springApplicationContext = new FileSystemXmlApplicationContext(tanaguruHome + "/" + APPLICATION_CONTEXT_FILE_PATH);
        BeanFactory springBeanFactory = springApplicationContext;
        auditService = (AuditService) springBeanFactory.getBean("auditService");
        auditDataService = (AuditDataService) springBeanFactory.getBean("auditDataService");
        webResourceDataService = (WebResourceDataService) springBeanFactory.getBean("webResourceDataService");
        webResourceStatisticsDataService = (WebResourceStatisticsDataService) springBeanFactory.getBean("webResourceStatisticsDataService");
        processResultDataService = (ProcessResultDataService) springBeanFactory.getBean("processResultDataService");
        processRemarkDataService = (ProcessRemarkDataService) springBeanFactory.getBean("processRemarkDataService");
        parameterDataService = (ParameterDataService) springBeanFactory.getBean("parameterDataService");
        parameterElementDataService = (ParameterElementDataService) springBeanFactory.getBean("parameterElementDataService");
        auditService.add(this);
    }

    /**
     * 
     * @param auditLevel
     * @return 
     */
    private Set<Parameter> getParameterSetFromAuditLevel(String ref, String level) {
        if (ref.equalsIgnoreCase(RGAA22_REF)) {
            if (level.equalsIgnoreCase(BRONZE_LEVEL)) {
                level=A_LEVEL;
            } else if (level.equalsIgnoreCase(SILVER_LEVEL)) {
                level=AA_LEVEL;
            } else if (level.equalsIgnoreCase(GOLD_LEVEL)) {
                level=AAA_LEVEL;
            }
        }
        if (level.equalsIgnoreCase(BRONZE_LEVEL) || level.equalsIgnoreCase(A_LEVEL)) {
            level=LEVEL_1;
        } else if (level.equalsIgnoreCase(SILVER_LEVEL) || level.equalsIgnoreCase(AA_LEVEL)) {
            level=LEVEL_2;
        } else if (level.equalsIgnoreCase(GOLD_LEVEL) || level.equalsIgnoreCase(AAA_LEVEL)) {
            level=LEVEL_3;
        } 
        ParameterElement levelParameterElement = parameterElementDataService.getParameterElement(LEVEL_PARAMETER_ELEMENT_CODE);
        Parameter levelParameter = parameterDataService.getParameter(levelParameterElement, ref + ";" + level);
        Set<Parameter> paramSet = parameterDataService.getDefaultParameterSet();
        return parameterDataService.updateParameter(paramSet, levelParameter);
    }
 
    /**
     * 
     * @param urlTab
     * @return 
     */
    private List<String> extractUrlListFromParameter(String urlTab) {
        String[] pageUrlTab = urlTab.split(";");
        List<String> pageUrlList = new LinkedList<>();
        pageUrlList.addAll(Arrays.asList(pageUrlTab));
        return pageUrlList;
    }
    
    /**
     * 
     * @param scenarioPath
     * @return 
     */
    private String readFile(File file) throws IOException {
        return FileUtils.readFileToString(file);
    }

    /**
     * Create the tanaguru command line interface options
     * @return the options to launch tanaguru cli
     */
    private static Options createOptions() {
        Options options = new Options();
        
        options.addOption(OptionBuilder.withLongOpt("help")
                             .withDescription("Show this message.")
                             .hasArg(false)
                             .isRequired(false)
                             .create("h"));
        
        options.addOption(OptionBuilder.withLongOpt("output")
                             .withDescription("Path to the output result file.")
                             .hasArg()
                             .isRequired(false)
                             .create("o"));
        
        options.addOption(OptionBuilder.withLongOpt("firefox-bin")
                             .withDescription("Path to the firefox bin.")
                             .hasArg()
                             .isRequired(false)
                             .create("f"));
        
        options.addOption(OptionBuilder.withLongOpt("display")
                             .withDescription("Value of the display")
                             .hasArg()
                             .isRequired(false)
                             .create("d"));
        
        options.addOption(OptionBuilder.withLongOpt("referential")
                             .withDescription("Referential : \n"
                + "- \"Aw22\" for Accessiweb 2.2 (default)\n"
                + "- \"Rgaa22\" for Rgaa 2.2\n")
                             .hasArg()
                             .isRequired(false)
                             .create("r"));
        
        options.addOption(OptionBuilder.withLongOpt("level")
                             .withDescription("Level :\n"
                + "- \"Or\" for Gold level or AAA level, \n"
                + "- \"Ar\" for Silver level or AA level (default), \n"
                + "- \"Bz\" for Bronze level or A level")
                             .hasArg()
                             .isRequired(false)
                             .create("l"));
        
        options.addOption(OptionBuilder.withLongOpt("audit-type")
                             .withDescription("Audit type :\n"
                + "- \"Page\" for page audit (default)\n"
                + "- \"File\" for file audit \n"
                + "- \"Scenario\" for scenario audit \n"
                + "- \"Site\" for site audit")
                             .hasArg()
                             .isRequired(false)
                             .create("t"));

        return options;
    }
    
    /**
     * Print usage
     */
    private static void printUsage() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("./bin/tanaguru.sh [OPTIONS]... [URL OR FILE OR SCENARIO]...\n", OPTIONS);
    }

    /**
     * 
     * @param path
     * @param option
     * @param testWritable
     * @return whether the given path is valid for the given argument
     */
    private static boolean isValidPath(String path, String option, boolean testWritable) {
        File file = FileUtils.getFile(path);
        if (file.exists() && file.canExecute() && file.canRead()) {
            if (!testWritable) {
                return true;
            } else if (file.canWrite()) {
                return true;
            }
        }
        System.out.println("\n"+path + " is an invalid path for " + option + " option.\n");
        return false;
    }
    
    /**
     * 
     * @param display
     * @param option
     * @return whether the given display is valid 
     */
    private static boolean isValidDisplay(String display, String option) {
        try {
            Integer.valueOf(display);
            return true;
        } catch (NumberFormatException nme) {
            System.out.println("\n"+display + " is invalid for " + option + " option.\n");
            return false;
        }
    }
    
    /**
     * 
     * @param path
     * @param argument
     * @param testWritable
     * @return whether the given referential is valid
     */
    private static boolean isValidReferential(String ref) {
        if (StringUtils.equals(ref, AW22_REF) ||
                StringUtils.equals(ref, RGAA22_REF) ) {
            return true;
        }
        System.out.println("\nThe referential \"" + ref + "\" doesn't exist.\n");
        return false;
    }
    
    /**
     * 
     * @param path
     * @param argument
     * @param testWritable
     * @return whether the given level is valid
     */
    private static boolean isValidLevel(String level) {
        if (StringUtils.equals(level, BRONZE_LEVEL) || 
                StringUtils.equals(level, SILVER_LEVEL) ||
                StringUtils.equals(level, GOLD_LEVEL)) {
            return true;
        }
        System.out.println("\nThe level \"" + level + "\" doesn't exist.\n");
        return false;
    }
    
    /**
     * 
     * @param path
     * @param argument
     * @param testWritable
     * @return whether the given level is valid
     */
    private static boolean isValidAuditType(String auditType) {
        if (StringUtils.equalsIgnoreCase(auditType,PAGE_AUDIT) || 
                StringUtils.equalsIgnoreCase(auditType, FILE_AUDIT) ||
                StringUtils.equalsIgnoreCase(auditType, SITE_AUDIT) ||
                StringUtils.equalsIgnoreCase(auditType, SCENARIO_AUDIT)) {
            return true;
        }
        System.out.println("\nThe audit type \"" + auditType + "\" doesn't exist.\n");
        return false;
    }
    
    /**
     * 
     * @param path
     * @param argument
     * @param testWritable
     * @return whether the given level is valid
     */
    private static boolean isValidPageUrl( CommandLine cl) {
        if (cl.getArgList().isEmpty()) {
            System.out.println("\nPlease specify at least one URL\n");
            return false;
        }
        for (String arg : cl.getArgs()) {
            try {
                URL url = new URL(arg);
            } catch (MalformedURLException ex) {
                System.out.println("\nThe URL " + arg + " is malformed\n");
                return false;
            }
        }
        return true;
    }

    private static boolean isValidSiteUrl( CommandLine cl) {
        if (cl.getArgList().isEmpty()) {
            System.out.println("\nPlease specify at least one URL\n");
            return false;
        }
        if (cl.getArgList().size() > 1) {
            System.out.println("\nOnly one URL is expected\n");
            return false;
        }
        try {
            URL url = new URL(cl.getArgs()[0]);
        } catch (MalformedURLException ex) {
            System.out.println("\nThe URL "+ cl.getArgs()[0]+ " is malformed\n");
            return false;
        }

        return true;
    }
    
    private static boolean isValidFilePath( CommandLine cl) {
        if (cl.getArgList().isEmpty()) {
            System.out.println("\nPlease specify at least one file\n");
            return false;
        }
        for (String arg : cl.getArgs()) {
            File file = FileUtils.getFile(arg);
            if (!file.canRead()) {
                System.out.println("\nThe file "+ file.getAbsolutePath() +" is unreadable.\n");
                return false;
            }
        }
        return true;
    }
    
    private static boolean isValidScenarioPath( CommandLine cl) {
        if (cl.getArgList().isEmpty()) {
            System.out.println("\nPlease specify at least one scenario\n");
            return false;
        }
        if (cl.getArgList().size() > 1) {
            System.out.println("\nOnly one scenario is expected\n");
            return false;
        }
        File scenario = FileUtils.getFile(cl.getArgs()[0]);
        if (!scenario.canRead()) {
            System.out.println("\nThe scenario file is unreadable.\n");
            return false;
        }
        return true;
    }

}