/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This program is free software: you can redistribute it and/or modify
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

package org.tanaguru.rules.doc.utils.rga33.extractor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;

/**
 *
 * @author jkowalczyk
 */
public class Rgaa3Extractor {

    static final Map<String, Rule> RGAA3 = new LinkedHashMap<>();
    static final Map<String, String> AW22 = new LinkedHashMap<>();

    static final String SOURCES_DIR_PATH = "/home/$user/Documents/Sources/";
    
    static final String TANAGURU_CONTEXT_PATH = SOURCES_DIR_PATH+"Tanaguru/";
    static final String DOC_CONTEXT_PATH = SOURCES_DIR_PATH+"Tanaguru-rules-RGAA-3-doc";
    
    static final String RGAA3_CONTEXT_PATH = TANAGURU_CONTEXT_PATH+"rules/rgaa3.0/";
    static final String RGAA3_SRC_PATH = RGAA3_CONTEXT_PATH+"src/main/java/org/opens/tanaguru/rules/rgaa30/";
    static final String RGAA3_TEST_PATH = RGAA3_CONTEXT_PATH+"src/test/java/org/opens/tanaguru/rules/rgaa30/";
    static final String RGAA3_TESTCASE_PATH = RGAA3_CONTEXT_PATH+"src/test/resources/testcases/rgaa30/";

    static final String RGAA3_I18_FILES_PATH = RGAA3_CONTEXT_PATH+"src/main/resources/i18n/";
    static final String CRITERION_I18N_FILE_PATH = RGAA3_I18_FILES_PATH+"criterion-rgaa30-I18N.properties";
    static final String TEST_I18N_FILE_PATH = RGAA3_I18_FILES_PATH+"rule-rgaa30-I18N.properties";
    static final String THEME_I18N_FILE_PATH = RGAA3_I18_FILES_PATH+"theme-rgaa30-I18N_en.properties";
    
    static final String RULE_TEMPLATE = TANAGURU_CONTEXT_PATH+"testing-tools/rules-doc-utils/src/main/resources/rgaa3Templates/Rgaa3RuleTemplate.txt";
    static final String RULE_DOC_TEMPLATE = TANAGURU_CONTEXT_PATH+"testing-tools/rules-doc-utils/src/main/resources/rgaa3Templates/Rule-template.md";
    static final String RULE_TEST_TEMPLATE = TANAGURU_CONTEXT_PATH+"testing-tools/rules-doc-utils/src/main/resources/rgaa3Templates/Rgaa3RuleTestTemplate.txt";
    static final String TESTCASE_TEMPLATE = TANAGURU_CONTEXT_PATH+"testing-tools/rules-doc-utils/src/main/resources/rgaa3Templates/Rgaa3TestcaseTemplate.txt";
    
    static final String RGAA3_MAIN_URL = "http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/";
    
    static final String RGAA3_REF_URL = RGAA3_MAIN_URL+"referentiel_technique.htm";
    static final String AW22_URL = "http://www.accessiweb.org/index.php/accessiweb_2.2_liste_deployee.html";

    static final String TEST_SELECTOR = ".tests li";
    static final String CRITERION_SELECTOR = "h3.crit";
    
    static final String TEST_ID_PREFIX = "test-";
    
    static final String OUTPUT_DIR = "/tmp/extractor/";
    
    static final String REF_NAME = "Rgaa30";
    
    static final Map<String, String> levelFromCrit = new LinkedHashMap<>();
    static boolean writeCritInFile = false;
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // Get content from websites
        Document rgaa3Doc = Jsoup.parse(new URL(RGAA3_REF_URL), 10000);
        Document aw22Doc = Jsoup.parse(new URL(AW22_URL), 10000);

        extractLevelFromCriterionAndWrite(rgaa3Doc);
        extractRuleInfo(rgaa3Doc);
        
        // Extract rules from accessiweb 2.2
        for (Element el : aw22Doc.select(TEST_SELECTOR)) {
            if (StringUtils.isNotBlank(el.text())) {
                AW22.put(extractTestFromId(el.id()), el.text());
            }
        }

//        generateMysql();
        compareReferentials();
//
//        getRuleI18nKeys();
//        checkClassesExist();
//        updateTestcasesWithRuleTitle();
//        generateMkdoc();
//        createTestcaseFiles();

    }

    private static void extractLevelFromCriterionAndWrite(Document doc) throws IOException {
        StringBuilder crit = new StringBuilder();
        for (Element el : doc.select(CRITERION_SELECTOR)) {
            if (StringUtils.isNotBlank(el.id())) {
                crit.append(el.id().replace("crit", "Rgaa30"));
                crit.append("=");
                String content = el.html();
                content = content.substring(content.indexOf("] ") + 1);
                content = extractRuleContent(content);
                crit.append(content);
                crit.append("\n");
                String level = el.text().substring(el.text().indexOf("[")+1, el.text().indexOf("]"));
                levelFromCrit.put(el.id().replaceAll("crit-", ""), level);
            }
        }
        if (writeCritInFile) {
            FileUtils.write(new File(CRITERION_I18N_FILE_PATH), crit.toString());
        }
    }
    
    private static void extractRuleInfo(Document doc) {
        boolean isFirst112 = false;
        for (Element el : doc.select(TEST_SELECTOR)) {
            if (StringUtils.isNotBlank(el.id())) {
                Rule rule = new Rule(RGAA3_REF_URL, REF_NAME);
                rule.ruleId = el.id();
                rule.ruleDash = extractTestFromId(rule.ruleId);
                if (rule.ruleDash.equals("1-1-2")) {
                    if (!isFirst112) {
                        isFirst112 = true;
                    } else {
                        rule.ruleDash = "1-1-4";
                        rule.ruleId = "test-1-1-4";
                    }
                } else if (rule.ruleDash.equals("11-1-4-5")) {
                    rule.ruleDash = "11-14-5";
                }
                rule.setRuleRawHtml(el.html().replaceAll("href=\"", "href=\""+RGAA3_MAIN_URL));
                rule.ruleHtmlWithoutLink = extractRuleContent(rule.ruleRawHtml);
                rule.ruleText = el.text();
                rule.level = levelFromCrit.get(rule.getCriterion());
                RGAA3.put(rule.ruleDash, rule);
            }
        }
    }
    
    private static void compareReferentials() throws IOException {
        final Map<String, String> newRgaa3 = new LinkedHashMap<>();
        final Map<String, String> identicalRgaa3 = new LinkedHashMap<>();
        final Map<String, String> differentRgaa3 = new LinkedHashMap<>();
        for (Map.Entry<String, Rule> entry : RGAA3.entrySet()) {
            if (!AW22.containsKey(entry.getKey()) && !AW22.containsKey(entry.getKey().replaceAll("\\.", "-"))) {
                newRgaa3.put(entry.getKey(), entry.getValue().ruleText);
            } else {
                if (AW22.get(entry.getKey()).equals(entry.getValue().ruleText) || 
                        AW22.get(entry.getKey().replaceAll("\\.", "-")).equals(entry.getValue().ruleText)) {
                    identicalRgaa3.put(entry.getKey(), entry.getValue().ruleText);
                } else {
                    differentRgaa3.put(entry.getKey(), entry.getValue().ruleText);
                }
            }
        }
        // Display and write into file new rules
        StringBuilder strbnew = new StringBuilder();
        for (Map.Entry<String, String> entry : newRgaa3.entrySet()) {
            strbnew.append(entry.getKey().replaceAll("\\.", "-"));
            strbnew.append("\n");
        }
        FileUtils.write(new File(OUTPUT_DIR+"/new_in_rgaa3.txt"), strbnew.toString());
        
        // write into file identical rules
        StringBuilder strbidentical = new StringBuilder();
        for (Map.Entry<String, String> entry : identicalRgaa3.entrySet()) {
            strbidentical.append(entry.getKey());
            strbidentical.append("\n");
        }
        FileUtils.write(new File(OUTPUT_DIR+"/identical_in_rgaa3.txt"), strbidentical.toString());
        
        // Display and write into file different rules
        StringBuilder strbdifferent = new StringBuilder();
        for (Map.Entry<String, String> entry : differentRgaa3.entrySet()) {
            strbdifferent.append(entry.getKey());
            strbdifferent.append("\n");
        }
        FileUtils.write(new File(OUTPUT_DIR+"/different_in_rgaa3.txt"), strbdifferent.toString());
    }
    
    private static void generateMysql() throws IOException {
        StringBuilder sqlInsert = new StringBuilder();
        sqlInsert.append("INSERT IGNORE INTO `TEST` (");
        sqlInsert.append("`Cd_Test`, ");
        sqlInsert.append("`Description`, ");
        sqlInsert.append("`Label`, ");
        sqlInsert.append("`Rank`, ");
        sqlInsert.append("`Weight`, ");
        sqlInsert.append("`Rule_Archive_Name`, ");
        sqlInsert.append("`Rule_Class_Name`, ");
        sqlInsert.append("`Id_Decision_Level`, ");
        sqlInsert.append("`Id_Level`, ");
        sqlInsert.append("`Id_Scope`, ");
        sqlInsert.append("`Rule_Design_Url`, ");
        sqlInsert.append("`No_Process`) ");
        sqlInsert.append("VALUES");
        sqlInsert.append("\n");
        
        // Check whether the rule is either new, either identical or different
        // regarding the rule text
        int rank = 1;
        for (Rule rule : RGAA3.values()) {
            sqlInsert.append("('Rgaa30-");
            sqlInsert.append(rule.ruleDash);
            sqlInsert.append("', '', '");
            sqlInsert.append(rule.getRuleDot());
            sqlInsert.append("', ");
            sqlInsert.append(rank);
            sqlInsert.append(", '1.0', 'rgaa30', 'org.opens.tanaguru.rules.rgaa30.");
            sqlInsert.append(rule.getFileName());
            sqlInsert.append("', NULL, ");
            sqlInsert.append(rule.getLevelId());
            sqlInsert.append(", 1, 'http://tanaguru-rules-rgaa3.readthedocs.org/en/master/Rule-");
            sqlInsert.append(rule.ruleDash);
            sqlInsert.append("', b'1'),");
            sqlInsert.append("\n");
            rank++;
        }
        FileUtils.write(new File(OUTPUT_DIR+"/rgaa3_insert.sql"), sqlInsert.toString());
    }
    
    private static void getRuleI18nKeys() throws IOException {
        StringBuilder strb = new StringBuilder();
        for (Map.Entry<String, Rule> entry : RGAA3.entrySet()) {
            strb.append(entry.getValue().getRuleKey());
            strb.append("=");
            strb.append(entry.getValue().ruleHtmlWithoutLink);
            strb.append("\n");
            strb.append(entry.getValue().getRuleKey());
            strb.append("-url=");
            strb.append(entry.getValue().getTestUrl());
            strb.append("\n");
        }
        FileUtils.write(new File(TEST_I18N_FILE_PATH), strb.toString());
    }
    
    private static String extractTestFromId(String id) {
        return id.replace(TEST_ID_PREFIX, "").replaceAll("\\.", "-");
    }
    
    /**
     * Remove links into htlm but keep code tags and ul/li.
     * 
     * @param rawRuleContent
     * @return 
     */
    private static String extractRuleContent(String rawRuleContent) {

        String ruleContent = rawRuleContent.replaceAll("</a>", "");
        ruleContent = ruleContent.replaceAll(" class=\"ssTests\"", "").trim();
        while (ruleContent.indexOf("<a") > 0) {
            String linkToReplace = ruleContent.substring(ruleContent.indexOf("<a"), ruleContent.indexOf("\">")+2);
            ruleContent = ruleContent.replaceAll(linkToReplace, "");
        }
        ruleContent = ruleContent.replaceAll("\n", " \\\\\n");
        return ruleContent;
    }
    
    private static void updateTestcasesWithRuleTitle() throws IOException {
        
    }
    
    private static void checkClassesExist() throws IOException {
        String ruleTemplate = FileUtils.readFileToString(new File(RULE_TEMPLATE));
        String ruleTestTemplate = FileUtils.readFileToString(new File(RULE_TEST_TEMPLATE));
        String testcaseTemplate = FileUtils.readFileToString(new File(TESTCASE_TEMPLATE));
        
        for (Map.Entry<String,Rule> entry : RGAA3.entrySet()) {
            String fileName = RGAA3_SRC_PATH+entry.getValue().getFileName()+".java";
            File ruleFile = new File(fileName);
            if (! ruleFile.exists()) {
                Rule rule = entry.getValue();
                System.out.println("Have to create "+rule.getFileName());
                // ruleCLass
                String classeContent = ruleTemplate.replaceAll("\\$ruleDot", rule.getRuleDot());
                classeContent = classeContent.replaceAll("\\$ruleDash", rule.ruleDash);
                classeContent = classeContent.replaceAll("\\$ruleFileName", rule.getFileName());
//                System.out.println(classeContent);
                FileUtils.writeStringToFile(ruleFile, classeContent);
                // ruleTestClass
                String classeTestContent = ruleTestTemplate.replaceAll("\\$ruleDot", rule.getRuleDot());
                classeTestContent = classeTestContent.replaceAll("\\$ruleDash", rule.ruleDash);
                classeTestContent = classeTestContent.replaceAll("\\$ruleFileName", rule.getFileName());
                FileUtils.writeStringToFile(new File(RGAA3_TEST_PATH+entry.getValue().getFileName()+"Test.java"), classeTestContent);
                
                FileUtils.forceMkdir(new File(RGAA3_TESTCASE_PATH+entry.getValue().getFileName()));
                // Passed Testcase
                String passedTcContent = testcaseTemplate.replaceAll("\\$ruleDash", rule.ruleDash);
                passedTcContent = passedTcContent.replaceAll("\\$result", "Passed");
                passedTcContent = passedTcContent.replaceAll("\\$ruleDescription", rule.ruleRawHtml);
                FileUtils.writeStringToFile(new File(RGAA3_TESTCASE_PATH+entry.getValue().getFileName()+"/Rgaa30.Test."+rule.getRuleDot()+"-1Passed-01.html"), passedTcContent);
                // Failed Testcase
                String failedTcContent = testcaseTemplate.replaceAll("\\$ruleDash", rule.ruleDash);
                failedTcContent = failedTcContent.replaceAll("\\$result", "Failed");
                failedTcContent = failedTcContent.replaceAll("\\$ruleDescription", rule.ruleRawHtml);
                FileUtils.writeStringToFile(new File(RGAA3_TESTCASE_PATH+entry.getValue().getFileName()+"/Rgaa30.Test."+rule.getRuleDot()+"-2Failed-01.html"), failedTcContent);
                // NMI Testcase
                String nmiTcContent = testcaseTemplate.replaceAll("\\$ruleDash", rule.ruleDash);
                nmiTcContent = nmiTcContent.replaceAll("\\$result", "Pre-Qualified");
                nmiTcContent = nmiTcContent.replaceAll("\\$ruleDescription", rule.ruleRawHtml);
                FileUtils.writeStringToFile(new File(RGAA3_TESTCASE_PATH+entry.getValue().getFileName()+"/Rgaa30.Test."+rule.getRuleDot()+"-3NMI-01.html"), nmiTcContent);
                // NA Testcase
                String naTcContent = testcaseTemplate.replaceAll("\\$ruleDash", rule.ruleDash);
                naTcContent = naTcContent.replaceAll("\\$result", "Not Applicable");
                naTcContent = naTcContent.replaceAll("\\$ruleDescription", rule.ruleRawHtml);
                FileUtils.writeStringToFile(new File(RGAA3_TESTCASE_PATH+entry.getValue().getFileName()+"/Rgaa30.Test."+rule.getRuleDot()+"-4NA-01.html"), naTcContent);
            }
        }
    }
    
    private static void generateDoc() {
        
    }
    
    private static void generateMkdoc() throws IOException {
        Map<String, String> themeKeys = new HashMap<>();
        for (String line : FileUtils.readLines(new File(THEME_I18N_FILE_PATH))) {
            String[] values = line.split("=");
            themeKeys.put(values[0], values[1]);
        }
        
        StringBuilder strb = new StringBuilder();
        strb.append("site_name: Tanaguru Rules RGAA3");
        strb.append("\n");
        strb.append("pages:");
        strb.append("\n");
        strb.append("- ['index.md', 'Home']");
        strb.append("\n");
        for (Map.Entry<String,Rule> entry : RGAA3.entrySet()) {
            String rule = entry.getKey().replaceAll("\\.", "-");
            String fileName="Rule-"+rule+".md";
            strb.append("- ['");
            if (!FileUtils.getFile(DOC_CONTEXT_PATH+"/docs/"+fileName).exists()) {
//                fileName="Rule-"+rule+"-new.md";
                FileUtils.writeStringToFile(new File(DOC_CONTEXT_PATH+"/docs/"+fileName), createRule(entry.getValue()));
            } else {
                updateRule(new File(DOC_CONTEXT_PATH+"/docs/"+fileName),entry.getValue());
            }
            strb.append(fileName);
            strb.append("','");
            //Theme
            String themeKey = "Rgaa30-"+rule.split("-")[0];
            strb.append(themeKeys.get(themeKey));
            strb.append("','Rule ");
            strb.append(rule.replaceAll("-","\\."));
            strb.append("']");
            strb.append("\n");
        }
        strb.append("\n");
        strb.append("theme: readthedocs");
        strb.append("\n");
        strb.append("repo_url: https://github.com/Tanaguru/Tanaguru");
        
        FileUtils.write(new File (DOC_CONTEXT_PATH+"/mkdocs.yml"), strb.toString());
    }
    
    private static String createRule(Rule rule) throws IOException {
        StringBuilder strb = new StringBuilder();
        for (String line : FileUtils.readLines(new File(RULE_DOC_TEMPLATE)) ){
            line = line.replace("$ruleName", "Rule " +rule.getRuleDot());
            line = line.replace("$criterion", rule.getCriterionMardown());
            line = line.replace("$test", rule.getTestMardown());
            line = line.replace("$ruleDescription", rule.getTestDescriptionMardown());
            line = line.replace("$level", rule.level );
            strb.append(line);
            strb.append("\n");
        }
        
        return strb.toString();
    }
    
    private static void updateRule(File file, Rule rule) throws IOException {
        StringBuilder strb = new StringBuilder();
        strb.append("# Rule ");
        strb.append(rule.getRuleDot());
        strb.append("\n");
        boolean withinBuDesc = false;
        boolean withinTechDesc = false;
        for (String line : FileUtils.readLines(file) ){

            line = line.replace("### Summary", "## Summary");
            line = line.replace("### Business description", "## Business description");
            line = line.replace("### Technical description", "## Technical description");
            line = line.replace("### Algorithm", "## Algorithm");
            line = line.replace("#### Selection", "### Selection");
            line = line.replace("#### Process", "### Process");
            line = line.replace("#### Analysis", "### Analysis");
            line = line.replace("##### NA", "#### Not Applicable");
            line = line.replace("##### NMI", "#### Pre-qualified");
            line = line.replace("##### Pre-qualified", "#### Pre-qualified");
            line = line.replace("##### Failed", "#### Failed");
            line = line.replace("##### Passed", "#### Passed");
            line = line.replace("### Notes", "## Notes");
            
            if (line.contains("Business description")) {
                withinBuDesc = true;
                strb.append("## Business description");
                strb.append("\n");
                strb.append("\n");
                strb.append("### Criterion");
                strb.append("\n");
                strb.append("\n");
                strb.append(rule.getCriterionMardown());
                strb.append("\n");
                strb.append("\n");
                strb.append("### Test");
                strb.append("\n");
                strb.append("\n");
                strb.append(rule.getTestMardown());
                strb.append("\n");
                strb.append("\n");
                strb.append("### Description");
                strb.append("\n");
                strb.append("\n");
                strb.append(rule.getTestDescriptionMardown());
                strb.append("\n");
                strb.append("\n");
                strb.append("### Level");
                strb.append("\n");
                strb.append("\n");
                strb.append("**");
                strb.append(rule.level);
                strb.append("**");
                strb.append("\n");
                strb.append("\n");
            }
            if (line.contains("Technical description")) {
                strb.append("## Technical description");
                strb.append("\n");
                strb.append("\n");
                withinBuDesc = false;
                withinTechDesc = true;
            }
            if (line.contains("## Algorithm")) {
                withinBuDesc = false;
                withinTechDesc = false;
            }
            if (withinTechDesc) {
                if (line.startsWith("Scope")) {
                    String scope = line.substring((line.indexOf("[")+1), line.indexOf("]"));
                    strb.append("### Scope");
                    strb.append("\n");
                    strb.append("\n");
                    strb.append("**");
                    strb.append(scope);
                    strb.append("**");
                    strb.append("\n");
                    strb.append("\n");
                } else if (line.startsWith("Decision level :")) {
                    strb.append("### Decision level");
                    strb.append("\n");
                    strb.append("\n");
                } else if (line.startsWith("[")) {
                    String decisionLevel = line.substring((line.indexOf("[")+1), line.indexOf("]"));
                    strb.append("**");
                    strb.append(decisionLevel);
                    strb.append("**");
                    strb.append("\n");
                    strb.append("\n");
                }
                
            }
            if (!withinBuDesc && !withinTechDesc) {
                strb.append(line);
                strb.append("\n");
            }
            
        }
        FileUtils.writeStringToFile(file, strb.toString());
    }
    
    private static void createTestcaseFiles() throws IOException {
        File srcDir = new File(RGAA3_TESTCASE_PATH);
        for (File file : srcDir.listFiles()) {
            String fileName = file.getName().replace("Rgaa30Rule", "").replace(".java", "");
            String theme = fileName.substring(0, 2);
            String crit = fileName.substring(2, 4);
            String test = fileName.substring(4, 6);
            String testKey = Integer.valueOf(theme).toString()+"-"+Integer.valueOf(crit).toString()+"-"+Integer.valueOf(test).toString();
            String wrongKey = theme+"."+crit+"."+test;
            for (File testcase : file.listFiles()) {
                if (testcase.isFile() && testcase.getName().contains("html")) {
                    Document doc = Jsoup.parse(FileUtils.readFileToString(testcase));
                    Element detail = doc.select(".test-detail").first();
                    if (detail == null) {
                        System.out.println(doc.outerHtml());
                    } else {
                        detail.tagName("div");
                        detail.text("");
                        for (Element el : detail.children()) {
                            el.remove();
                        }
                        if (!detail.hasAttr("lang")) {
                            detail.attr("lang", "fr");
                        }
                        detail.append("\n"+RGAA3.get(testKey).ruleRawHtml+"\n");
                        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
                        doc.outputSettings().outline(false);
                        doc.outputSettings().indentAmount(4);
                        String outputHtml = doc.outerHtml();
                        if (outputHtml.contains(wrongKey)) {
                            outputHtml = outputHtml.replaceAll(wrongKey, RGAA3.get(testKey).getRuleDot());
                        }
                        FileUtils.writeStringToFile(testcase, outputHtml);
                    }
                }
            }
        }
    }
    
}
