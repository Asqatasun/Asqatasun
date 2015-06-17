/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.referentiel.creator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.tanaguru.referentiel.creator.exception.InvalidParameterException;
import org.tanaguru.referentiel.creator.exception.I18NLanguageNotFoundException;

/**
 * @goal generate
 *
 * @author alingua
 */
public class CodeGeneratorMojo extends AbstractMojo {

    private static final String PACKAGE_NAME = "org.opens.tanaguru.rules";
    private static final String THEME_LABEL_COLUMN_NAME = "theme_";
    private static final String CRITERION_LABEL_COLUMN_NAME = "critere-label_";
    private static final String TEST_LABEL_COLUMN_NAME = "test-label_";
    private static final String THEME_CODE_COLUMN_NAME = "theme";
    private static final String CRITERION_CODE_COLUMN_NAME = "critere";
    private static final String TEST_CODE_COLUMN_NAME = "test";
    private static final String LEVEL_CODE_COLUMN_NAME = "level";
    private static final String SCOPE_CODE_COLUMN_NAME = "scope";
    private static final String CLASSNAME_CODE_COLUMN_NAME = "class-name";
    private static boolean IS_I18N_REFERENTIAL_CREATED = false;
    boolean isCriterionPresent;
    /**
     * @parameter
     * default-value="${basedir}/src/main/resources/template/rule/rule.vm"
     */
    File templateRule;
    /**
     * @parameter
     * default-value="${basedir}/src/main/resources/template/testcase/testcase.vm"
     */
    File templateTestCase;
    /**
     * @parameter
     * default-value="${basedir}/src/main/resources/template/unittest/unittest.vm"
     */
    File templateUnitTest;
    /**
     * @parameter
     * default-value="${basedir}/src/main/resources/template/unittest/ruleimplementationtestcase.vm"
     */
    File templateRuleImplementationTestCase;
    /**
     * @parameter
     * default-value="${basedir}/src/main/resources/all/ressources/descriptor.vm"
     */
    File templateDescriptor;
    /**
     * @parameter
     * default-value="${basedir}/src/main/resources/all/ressources/install.vm"
     */
    File templateInstallSh;
    /**
     * @parameter
     * default-value="${basedir}/src/main/resources/all/ressources/conf/webapp/beans-webapp.vm"
     */
    File templateBeansWebapp;
    /**
     * @parameter
     * default-value="${basedir}/src/main/resources/all/ressources/conf/webapp/mvc/form/tgol-beans-audit-result-console-form.vm"
     */
    File templateAuditResultConsole;
    /**
     * @parameter
     * default-value="${basedir}/src/main/resources/all/ressources/conf/webapp/mvc/form/tgol-beans-audit-set-up-form.vm"
     */
    File templateAuditSetUpForm;
    /**
     * @parameter
     * default-value="${basedir}/src/main/resources/all/ressources/conf/webapp/export/tgol-beans-expression.vm"
     */
    File templateBeansExpression;
    /**
     * @parameter
     * default-value="${basedir}/src/main/resources/pomProject/pom.vm"
     */
    File pom;
    /**
     * @parameter
     */
    File dataFile;
    /**
     * @parameter default-value='ø'
     */
    char delimiter;
    /**
     * @parameter
     */
    String destinationFolder;
    /**
     * @parameter
     */
    String referentiel;
    /**
     * @parameter
     */
    String referentielLabel;
    /**
     * @parameter default-value="empty"
     */
    String refDescriptor;
    TreeSet<String> langSet = new TreeSet();
    LinkedList<String> levelList = new LinkedList();
    LinkedList<String> scopeList = new LinkedList();

    /**
     * Default constructor
     */
    public CodeGeneratorMojo() {
    }

    @Override
    public void execute() {
        try {
            isContextValid();
        } catch (InvalidParameterException ipe) {
            Logger.getLogger(CodeGeneratorMojo.class.getName()).log(Level.SEVERE, null, ipe);
            return;
        }

        // Initializes engine
        VelocityEngine ve = initializeVelocity();
        Iterable<CSVRecord> records = getCsv();
        if (records == null) {
            return;
        }
        try {
            initializeContext();
            generate(ve, records);
            cleanUpUnusedFiles();
        } catch (IOException ex) {
            Logger.getLogger(CodeGeneratorMojo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ResourceNotFoundException ex) {
            Logger.getLogger(CodeGeneratorMojo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseErrorException ex) {
            Logger.getLogger(CodeGeneratorMojo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CodeGeneratorMojo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     */
    private VelocityEngine initializeVelocity() {
        Properties props = new Properties();
        props.setProperty("resource.loader", "file");
        props.setProperty("file.resource.loader.description",
                "Velocity File Resource Loader");
        props.setProperty("file.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
        props.setProperty("file.resource.loader.path", "/");
        VelocityEngine ve = new VelocityEngine();
        try {
            ve.init(props);
        } catch (Exception ex) {
            Logger.getLogger(CodeGeneratorMojo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ve;
    }

    /**
     *
     * @return
     */
    private Iterable<CSVRecord> getCsv() {
        // we parse the csv file to extract the first line and get the headers 
        LineIterator lineIterator;
        try {
            lineIterator = FileUtils.lineIterator(dataFile, Charset.defaultCharset().name());
        } catch (IOException ex) {
            Logger.getLogger(CodeGeneratorMojo.class.getName()).log(Level.SEVERE, null, ex);
            lineIterator = null;
        }
        String[] csvHeaders = lineIterator.next().split(String.valueOf(delimiter));
        isCriterionPresent = extractCriterionFromCsvHeader(csvHeaders);
        try {
            extractAvailableLangsFromCsvHeader(csvHeaders);
        } catch (I18NLanguageNotFoundException ex) {
            Logger.getLogger(CodeGeneratorMojo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        // from here we just add each line to a build to re-create the csv content
        // without the first line.
        StringBuilder strb = new StringBuilder();
        while (lineIterator.hasNext()) {
            strb.append(lineIterator.next());
            strb.append("\n");
        }
        Reader in;
        try {
            in = new StringReader(strb.toString());
            CSVFormat csvf = CSVFormat.newFormat(delimiter).withHeader(csvHeaders);
            return csvf.parse(in);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CodeGeneratorMojo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(CodeGeneratorMojo.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private void extractAvailableLangsFromCsvHeader(String[] csvHeaders) throws I18NLanguageNotFoundException {
        LinkedHashSet<String> themeList = new LinkedHashSet<>();
        LinkedHashSet<String> critereList = new LinkedHashSet<>();
        LinkedHashSet<String> testList = new LinkedHashSet<>();
        for (String header : csvHeaders) {
            if (header.startsWith(THEME_LABEL_COLUMN_NAME)) {
                themeList.add(header.split("_")[1]);
            } else if (header.startsWith(CRITERION_LABEL_COLUMN_NAME)) {
                critereList.add(header.split("_")[1]);
            } else if (header.startsWith(TEST_LABEL_COLUMN_NAME)) {
                testList.add(header.split("_")[1]);
            }
        }
        if (isCriterionPresent) {
            if (themeList.equals(critereList)
                    && testList.equals(critereList)) {
                langSet.addAll(themeList);
            } else {
                throw new I18NLanguageNotFoundException("All Label on csv column must have internationalization");
            }
        } else {
            if (themeList.equals(testList)) {
                langSet.addAll(themeList);
            } else {
                throw new I18NLanguageNotFoundException("All Label on csv column must have internationalization");
            }
        }
    }

    private boolean extractCriterionFromCsvHeader(String[] csvHeaders) {
        for (String header : csvHeaders) {
            if (header.startsWith("critere-label")) {
                return true;
            }
        }
        return false;
    }

    private void writeToI18NFile(FileGenerator fg, CSVRecord record, String lang) throws IOException, InvalidParameterException {
        Integer themeIndex = Integer.valueOf(record.get(THEME_CODE_COLUMN_NAME));
        String theme = record.get(THEME_LABEL_COLUMN_NAME + lang);
        String critere;
        String critereCode;
        String test = record.get(TEST_LABEL_COLUMN_NAME + lang);
        String testCode = record.get(TEST_CODE_COLUMN_NAME);
        if (isCriterionPresent) {
            critere = record.get(CRITERION_LABEL_COLUMN_NAME + lang);
            critereCode = record.get(CRITERION_CODE_COLUMN_NAME);
        } else {
            critere = test;
            critereCode = testCode;
        }
        if (StringUtils.isBlank(theme)
                || StringUtils.isBlank(critere)
                || StringUtils.isBlank(critereCode)) {
            throw new InvalidParameterException("Your csv file has an empty column");
        }
        Map themeMap = Collections.singletonMap(themeIndex, theme);
        Map critereMap = Collections.singletonMap(critereCode, critere);
        Map testMap = Collections.singletonMap(testCode, test);
        if (StringUtils.isNotBlank(theme) && StringUtils.isNotBlank(String.valueOf(themeIndex))) {
            fg.writei18NFile(themeMap, lang, langSet.first(), "theme");
        }
        if (StringUtils.isNotBlank(critere) && StringUtils.isNotBlank(critereCode)) {
            fg.writei18NFile(critereMap, lang, langSet.first(), "criterion");
        }
        if (StringUtils.isNotBlank(test) && StringUtils.isNotBlank(testCode)) {
            fg.writei18NFile(testMap, lang, langSet.first(), "rule");
        }
        if (IS_I18N_REFERENTIAL_CREATED == false) {
            fg.writei18NFile(null, lang, langSet.first(), "referential");
        }
    }

    /**
     *
     * @param ve
     * @param records
     * @throws java.io.IOException
     */
    public void generate(VelocityEngine ve, Iterable<CSVRecord> records) throws IOException, ResourceNotFoundException, ParseErrorException, Exception {
        // Getting the Template
        Template ruleTemplate = ve.getTemplate(templateRule.getPath());
        Template pomTemplate = ve.getTemplate(pom.getPath());
        Template webappBeansTemplate = ve.getTemplate(templateBeansWebapp.getPath());
        Template webappBeansExpressionTemplate = ve.getTemplate(templateBeansExpression.getPath());
        Template auditResultConsoleTemplate = ve.getTemplate(templateAuditResultConsole.getPath());
        Template auditSetUpFormTemplate = ve.getTemplate(templateAuditSetUpForm.getPath());
        Template testCaseTemplate = ve.getTemplate(templateTestCase.getPath());
        Template descriptorTemplate = ve.getTemplate(templateDescriptor.getPath());
        Template installTemplate = ve.getTemplate(templateInstallSh.getPath());
        Template unitTestTemplate = ve.getTemplate(templateUnitTest.getPath());
        Template ruleImplementationTestCaseTemplate = ve.getTemplate(templateRuleImplementationTestCase.getPath());
        // Create a context and add data to the templateRule placeholder
        VelocityContext context = new VelocityContext();
        // Fetch templateRule into a StringWriter
        FileGenerator fg = new FileGenerator(referentiel, referentielLabel,
                destinationFolder, refDescriptor, isCriterionPresent);
        fg.createI18NFiles(langSet);

        // we parse the records collection only once to create the i18n files.
        // These files will be then used later to create other context files
        // using the i18n keys.
        for (CSVRecord record : records) {
            String testLabelDefault = record.get(TEST_LABEL_COLUMN_NAME + langSet.first());
            String test = record.get(TEST_CODE_COLUMN_NAME);
            for (String lang : langSet) {
                writeToI18NFile(fg, record, lang);
            }
            IS_I18N_REFERENTIAL_CREATED = true;
            if (!isCriterionPresent) {
                test = test.concat("-1");
            }
            context = fg.getContextRuleClassFile(referentiel, PACKAGE_NAME, test, testLabelDefault, context);
            fg.writeFileCodeGenerate(context, ruleTemplate, getClassNameFromCsvColumn(record));
            fg.writeUnitTestGenerate(context, unitTestTemplate, testLabelDefault, getClassNameFromCsvColumn(record));
            String[] testsCasesState = {"Passed", "Failed", "NMI", "NA"};
            for (int i = 0; i < testsCasesState.length; i++) {
                context.put("state", testsCasesState[i]);
                fg.writeTestCaseGenerate(context, testCaseTemplate,
                        getClassNameFromCsvColumn(record), String.valueOf(i + 1));
            }
            addLevelAndScopeToList(record);
        }

        fg.createSqlReference();
        fg.createSqlTheme();
        fg.createSqlCritere();
        fg.createSqlTest(levelList, scopeList);
        fg.createSqlParameters();

        fg.writeAuditSetUpFormBeanGenerate(context, auditSetUpFormTemplate);
        fg.writeAuditResultConsoleBeanGenerate(context, auditResultConsoleTemplate);
        fg.writeWebappBeansGenerate(context, webappBeansTemplate);
        fg.writeWebappBeansExpressionGenerate(context, webappBeansExpressionTemplate);
        fg.writeInstallGenerate(context, installTemplate);
        fg.writeDescriptorGenerate(context, descriptorTemplate);
        fg.writeRuleImplementationTestCaseGenerate(context, ruleImplementationTestCaseTemplate);

        fg.adaptPom(context, pomTemplate);
    }

    private void addLevelAndScopeToList(CSVRecord record) {
        try {
            String level = record.get(LEVEL_CODE_COLUMN_NAME);
            if (StringUtils.isBlank(level)) {
                levelList.add("1");
            } else {
                levelList.add(level);
            }
        } catch (IllegalArgumentException iae) {
            levelList.add("1");
        }
        try {
            String scope = record.get(SCOPE_CODE_COLUMN_NAME);
            if (StringUtils.isBlank(scope)) {
                scopeList.add("1");
            } else {
                scopeList.add(scope);
            }
        } catch (IllegalArgumentException iae) {
            scopeList.add("1");
        }
    }

    private String getClassNameFromCsvColumn(CSVRecord record) {
        try {
            String className = record.get(CLASSNAME_CODE_COLUMN_NAME);
            if (StringUtils.isBlank(className)) {
                return null;
            } else {
                return className.replace(" ", "").replace("-", "");
            }
        } catch (IllegalArgumentException iae) {
            return null;
        }
    }

    /**
     * This method sets the working directory and copies the static context
     * files such as log4j or xmlDataSet (needed by HsqlDb) to the appropriate
     * path.
     *
     * @throws IOException
     */
    private void initializeContext() throws IOException {
        String workingDir = System.getProperty("user.dir");
        File dataset = FileUtils.getFile(workingDir + "/src/main/resources/dataset/emptyFlatXmlDataSet.xml");
        File log4jFile = FileUtils.getFile(workingDir + "/src/main/resources/log4j/log4j.properties");
        File datasetFolder = new File(destinationFolder + "/src/test/resources/dataSets");
        File log4jFolder = new File(destinationFolder + "/src/test/resources");
        dataset.mkdirs();
        log4jFile.mkdirs();
        FileUtils.copyFileToDirectory(dataset, datasetFolder);
        FileUtils.copyFileToDirectory(log4jFile, log4jFolder);
    }

    /**
     * Clean up uneeded files at the end of the generation (such as App.java
     * created by the maven project context creator)
     *
     * @throws IOException
     */
    private void cleanUpUnusedFiles() throws IOException {
        //FileCleaner.cleanUpContext(destinationFolder);
    }

    /**
     *
     * @throws InvalidParameterException
     */
    private void isContextValid() throws InvalidParameterException {
        if (templateRule == null
                || templateRuleImplementationTestCase == null
                || templateTestCase == null
                || templateUnitTest == null
                || templateAuditResultConsole == null
                || templateAuditSetUpForm == null
                || templateBeansWebapp == null
                || templateInstallSh == null
                || templateDescriptor == null
                || String.valueOf(delimiter).isEmpty()
                || dataFile == null
                || StringUtils.isBlank(destinationFolder)
                || StringUtils.isBlank(referentiel)
                || StringUtils.isBlank(referentielLabel)) {
            throw new InvalidParameterException("One or several parameter(s) set to the pom file is/are invalid(s)");
        }
    }
}
