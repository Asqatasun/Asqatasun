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
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;
import static java.nio.charset.StandardCharsets.*;
import java.util.LinkedList;

/**
 *
 * @author alingua
 */
public class FileGenerator {

    private final VelocityParametersContext vpc;
    private final boolean isCriterionPresent;

    public FileGenerator(String referentiel,
            String referentielLabel,
            String destinationFolder,
            String refDescriptor,
            boolean isCriterionPresent) {
        vpc = new VelocityParametersContext();
        vpc.setReferentiel(String.valueOf(referentiel.charAt(0)).toUpperCase()
                + referentiel.substring(1));
        vpc.setReferentielLabel(referentielLabel);
        vpc.setDestinationFolder(destinationFolder);
        vpc.setRefDescriptor(refDescriptor);
        this.isCriterionPresent = isCriterionPresent;
    }

    protected File getSqlFile() {
        return new File(vpc.getDestinationFolder()
                + "/src/main/resources/sql/"
                + vpc.getReferentiel().toLowerCase()
                + "-insert.sql");
    }

    protected File getI18nDefaultFile(String category) {
        return new File(vpc.getDestinationFolder()
                + "/src/main/resources/i18n/"
                + category + "-"
                + vpc.getReferentiel().replace(".", "").toLowerCase()
                + "-I18N.properties");
    }

    protected File getI18nFile(String lang, String category) {
        return new File(vpc.getDestinationFolder()
                + "/src/main/resources/i18n/"
                + category + "-"
                + vpc.getReferentiel().replace(".", "").toLowerCase()
                + "-I18N_" + lang.toLowerCase() + ".properties");
    }

    public VelocityContext getContextRuleClassFile(
            String referentielLower, String packageName, String test,
            String testLabel, VelocityContext context) throws IOException {
        String[] testCodeArray = test.split("-");
        vpc.getThemes().add(Integer.valueOf(testCodeArray[0]));

        vpc.setTestCode(test);
        vpc.setPackageString(packageName);
        String[] twoDigitTestCode = normalize2Digits(testCodeArray);
        vpc.setClassString(vpc.getReferentiel().replace(".", "") + "Rule"
                + twoDigitTestCode[0] + twoDigitTestCode[1] + twoDigitTestCode[2]);
        context.put("referentiel", vpc.getReferentiel().replace(".", ""));
        context.put("referentielFolder", vpc.getReferentiel());
        context.put("rule", vpc.getClassString());
        context.put("ruleCode", vpc.getTestCode());
        context.put("referentielName", vpc.getReferentielLabel());
        context.put("testLabel", testLabel);
        context.put("package", vpc.getPackageString());
        context.put("refDescriptor", vpc.getRefDescriptor());
        return context;
    }

    private String[] normalize2Digits(String[] testCodeArray) {
        String thematique = testCodeArray[0];
        String critereCode = testCodeArray[1];
        String test2DigitsCode = testCodeArray[2];
        if (testCodeArray[0].length() == 1) {
            thematique = "0" + testCodeArray[0];
        }
        if (testCodeArray[1].length() == 1) {
            critereCode = "0" + testCodeArray[1];
        }
        if (testCodeArray[2].length() == 1) {
            test2DigitsCode = "0" + testCodeArray[2];
        }
        String[] classCode = {thematique, critereCode, test2DigitsCode};
        return classCode;
    }

    public void writeFileCodeGenerate(VelocityContext context, Template temp,
            String className) throws IOException {
        StringWriter wr = new StringWriter();
        if (StringUtils.isNotBlank(className)) {
            vpc.setClassString(className);
            context.put("rule", vpc.getClassString());
        }
        temp.merge(context, wr);
        vpc.getClassRule().add(vpc.getClassString());
        File classFile = new File(vpc.getDestinationFolder()
                + "/src/main/java/"
                + vpc.getPackageString().replace('.', '/') + "/"
                + vpc.getReferentiel().replace(".", "").toLowerCase() + "/"
                + vpc.getClassString() + ".java");
        FileUtils.writeStringToFile(classFile, wr.toString());
    }

    public void adaptPom(VelocityContext context, Template temp) throws IOException {
        StringWriter wr = new StringWriter();
        temp.merge(context, wr);
        File pomFile = new File(vpc.getDestinationFolder()
                + "/pom.xml");
        FileUtils.writeStringToFile(pomFile, wr.toString());
    }

    public void writeTestCaseGenerate(VelocityContext context, Template temp,
            String className, String testCaseNumber) throws IOException {
        StringWriter wr = new StringWriter();
        if (StringUtils.isNotBlank(className)) {
            vpc.setClassString(className);
            context.put("rule", vpc.getClassString());
        }
        temp.merge(context, wr);
        File testCaseFile = new File(vpc.getDestinationFolder()
                + "/src/test/resources/testcases/"
                + vpc.getReferentiel().replace(".", "").toLowerCase() + "/"
                + vpc.getClassString() + "/" + vpc.getReferentiel().replace(".", "")
                + ".Test." + vpc.getTestCode().replace('-', '.')
                + "-" + testCaseNumber + context.get("state") + "-01.html");
        FileUtils.writeStringToFile(testCaseFile, wr.toString());
    }

    public void writeUnitTestGenerate(VelocityContext context, Template temp,
            String testCaseNumber, String className) throws IOException {
        StringWriter wr = new StringWriter();
        if (StringUtils.isNotBlank(className)) {
            vpc.setClassString(className);
            context.put("rule", vpc.getClassString());
        }
        temp.merge(context, wr);
        File testCaseFile = new File(vpc.getDestinationFolder()
                + "/src/test/java/"
                + vpc.getPackageString().replace('.', '/') + "/"
                + vpc.getReferentiel().replace(".", "").toLowerCase() + "/"
                + vpc.getClassString() + "Test.java");
        FileUtils.writeStringToFile(testCaseFile, wr.toString());
    }

    public void writeRuleImplementationTestCaseGenerate(
            VelocityContext context, Template temp) throws IOException {
        StringWriter wr = new StringWriter();
        temp.merge(context, wr);
        File testCaseFile = new File(vpc.getDestinationFolder()
                + "/src/test/java/"
                + vpc.getPackageString().replace('.', '/') + "/"
                + vpc.getReferentiel().replace(".", "").toLowerCase() + "/test/"
                + vpc.getReferentiel().replace(".", "")
                + "RuleImplementationTestCase.java");
        FileUtils.writeStringToFile(testCaseFile, wr.toString());
    }

    public void writei18NFile(Map categoryMap,
            String lang, String defaultLanguage,
            String category) throws IOException {
        if (category.equals("referential")) {
            writeI18NReferentialFile(lang, defaultLanguage, category);
            return;
        }
        String code;
        if (category.equals("rule") && !isCriterionPresent) {
            code = categoryMap.keySet().iterator().next().toString() + "-1";
        } else {
            code = categoryMap.keySet().iterator().next().toString();
        }
        String desc = cleanI18NString(categoryMap.values().iterator().next().toString());
        StringBuilder sb = new StringBuilder();
        sb.append(vpc.getReferentiel().replace(".", ""));
        sb.append("-").append(code).append("=").append(desc).append("\n");
        if (category.equals("rule")) {
            writeTestUrlI18NFile(vpc.getRefDescriptor(), code, sb);
        }
        if (!FileUtils.readFileToString(getI18nFile(lang, category), UTF_8).contains(sb.toString())) {
            FileUtils.writeStringToFile(FileUtils.getFile(getI18nFile(lang, category)), sb.toString(), UTF_8, true);
        }
        if (lang.equalsIgnoreCase(defaultLanguage) && !FileUtils.readFileToString(getI18nDefaultFile(category), UTF_8).contains(sb.toString())) {
            FileUtils.writeStringToFile(FileUtils.getFile(getI18nDefaultFile(category)), sb.toString(), UTF_8, true);
        }
    }

    private String cleanI18NString(String desc) {
        if (desc.startsWith("\'")) {
            desc = desc.replaceFirst("\'", "");
        }
        if (desc.endsWith("\'")) {
            desc = desc.substring(0, desc.length() - 1);
        }
        return desc;
    }

    private StringBuilder writeTestUrlI18NFile(String refDescriptor, Object code, StringBuilder sb) {
        sb.append(vpc.getReferentiel().replace(".", ""));
        sb.append("-").append(code).append("-url=");
        if (!refDescriptor.equalsIgnoreCase("empty")) {
            sb.append(refDescriptor).append("#test-").append(code);
        } else {
            sb.append("#");
        }
        sb.append("\n");
        return sb;
    }

    private void writeI18NReferentialFile(String lang, String defaultLanguage, String category) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(vpc.getReferentiel().replace(".", "")).append("=").append(vpc.getReferentielLabel()).append("\n");
        sb.append(vpc.getReferentiel().replace(".", "")).append("-optgroup=").append(vpc.getReferentielLabel()).append("\n");
        sb.append(vpc.getReferentiel().replace(".", "")).append("-LEVEL_1=A\n");
        sb.append(vpc.getReferentiel().replace(".", "")).append("-LEVEL_2=AA\n");
        sb.append(vpc.getReferentiel().replace(".", "")).append("-LEVEL_3=AAA");
        if (!FileUtils.readFileToString(getI18nFile(lang, category), UTF_8).contains(sb.toString())) {
            FileUtils.writeStringToFile(FileUtils.getFile(getI18nFile(lang, category)), sb.toString(), UTF_8, true);
        }
        if (lang.equalsIgnoreCase(defaultLanguage) && !FileUtils.readFileToString(getI18nDefaultFile(category), UTF_8).contains(sb.toString())) {
            FileUtils.writeStringToFile(FileUtils.getFile(getI18nDefaultFile(category)), sb.toString(), UTF_8, true);
        }
    }

    public void createI18NFiles(Set<String> langs) throws IOException {
        FileUtils.touch(getI18nDefaultFile("theme"));
        FileUtils.touch(getI18nDefaultFile("criterion"));
        FileUtils.touch(getI18nDefaultFile("rule"));
        FileUtils.touch(getI18nDefaultFile("rule-remark"));
        FileUtils.touch(getI18nDefaultFile("referential"));
        for (String lang : langs) {
            FileUtils.touch(getI18nFile(lang, "theme"));
            FileUtils.touch(getI18nFile(lang, "criterion"));
            FileUtils.touch(getI18nFile(lang, "rule"));
            FileUtils.touch(getI18nFile(lang, "rule-remark"));
            FileUtils.touch(getI18nFile(lang, "referential"));
        }
    }

    public void writeDescriptorGenerate(VelocityContext context, Template temp) throws IOException {
        StringWriter wr = new StringWriter();
        temp.merge(context, wr);
        File descriptorFile = new File(vpc.getDestinationFolder()
                + "/src/main/resources/"
                + "descriptor.xml");
        FileUtils.writeStringToFile(descriptorFile, wr.toString());
    }

    public void writeInstallGenerate(VelocityContext context, Template temp) throws IOException {
        StringWriter wr = new StringWriter();
        temp.merge(context, wr);
        File installFile = new File(vpc.getDestinationFolder()
                + "/src/main/resources/"
                + "deploy.sh");
        FileUtils.writeStringToFile(installFile, wr.toString());
    }

    public void writeWebappBeansGenerate(VelocityContext context,
            Template temp) throws IOException {
        StringWriter wr = new StringWriter();
        temp.merge(context, wr);
        File beansWebappFile = new File(vpc.getDestinationFolder()
                + "/src/main/resources/conf/context/"
                + vpc.getReferentiel().replace(".", "").toLowerCase()
                + "/web-app/"
                + vpc.getReferentiel().replace(".", "").toLowerCase()
                + "-beans-webapp.xml");
        FileUtils.writeStringToFile(beansWebappFile, wr.toString());
    }

    public void writeWebappBeansExpressionGenerate(VelocityContext context,
            Template temp) throws IOException {
        StringWriter wr = new StringWriter();
        temp.merge(context, wr);
        File beansWebappFile = new File(vpc.getDestinationFolder()
                + "/src/main/resources/conf/context/"
                + vpc.getReferentiel().replace(".", "").toLowerCase()
                + "/web-app/export/" + "tgol-beans-"
                + vpc.getReferentiel().replace(".", "").toLowerCase()
                + "-expression.xml");
        FileUtils.writeStringToFile(beansWebappFile, wr.toString());
    }

    public void writeAuditResultConsoleBeanGenerate(VelocityContext context,
            Template temp) throws IOException {
        StringWriter wr = new StringWriter();
        context.put("themes", vpc.getThemes());
        temp.merge(context, wr);
        File beansAuditResultConsoleFile = new File(vpc.getDestinationFolder()
                + "/src/main/resources/conf/context/"
                + vpc.getReferentiel().replace(".", "").toLowerCase()
                + "/web-app/mvc/form/" + "tgol-beans-"
                + vpc.getReferentiel().replace(".", "").toLowerCase()
                + "-audit-result-console-form.xml");
        FileUtils.writeStringToFile(beansAuditResultConsoleFile, wr.toString());
    }

    public void writeAuditSetUpFormBeanGenerate(VelocityContext context,
            Template temp) throws IOException {
        StringWriter wr = new StringWriter();
        temp.merge(context, wr);
        File beansAuditResultConsoleFile = new File(vpc.getDestinationFolder()
                + "/src/main/resources/conf/context/"
                + vpc.getReferentiel().replace(".", "").toLowerCase()
                + "/web-app/mvc/form/" + "tgol-beans-"
                + vpc.getReferentiel().replace(".", "").toLowerCase()
                + "-audit-set-up-form.xml");
        FileUtils.writeStringToFile(beansAuditResultConsoleFile, wr.toString());
    }

    public void createSqlReference() throws IOException {
        FileUtils.touch(getSqlFile());
        StringBuilder strb = new StringBuilder();
        strb.append("INSERT IGNORE INTO `REFERENCE` (`Cd_Reference`, `Description`, `Label`, `Url`, `Rank`, `Id_Default_Level`) VALUES\n");
        strb.append("(\'").append(vpc.getReferentiel().replace(".", "")).append("\', NULL, \'").append(vpc.getReferentielLabel()).append("\', \'\', 2000, 1);\n\n");
        strb.append("INSERT IGNORE INTO `TGSI_REFERENTIAL` (`Code`, `Label`) VALUES\n");
        strb.append("(\'").append(vpc.getReferentiel().replace(".", "")).append("\', \'").append(vpc.getReferentielLabel()).append("\');\n\n");
        FileUtils.writeStringToFile(FileUtils.getFile(getSqlFile()), strb.toString(), true);
    }

    public void createSqlTheme() throws IOException {
        List<String> themesList = FileUtils.readLines(getI18nDefaultFile("theme"));
        StringBuilder strb = new StringBuilder();
        strb.append("INSERT IGNORE INTO `THEME` (`Cd_Theme`, `Description`, `Label`, `Rank`) VALUES\n");
        for (int i = 0; i < themesList.size(); i++) {
            strb.append("(\'").append(themesList.get(i).split("=")[0]).append("\', NULL, \'").append(themesList.get(i).split("=")[1].replace("\'", "")).append("\', ");
            strb.append(String.valueOf(i + 1)).append(")");
            if (i < themesList.size() - 1) {
                strb.append(",\n");
            } else if (i == themesList.size() - 1) {
                strb.append(";\n\n");
            }
        }
        FileUtils.writeStringToFile(FileUtils.getFile(getSqlFile()), strb.toString(), true);
    }

    public void createSqlCritere() throws IOException {
        List<String> criteres = FileUtils.readLines(getI18nDefaultFile("criterion"));
        List<String> themesList = FileUtils.readLines(getI18nDefaultFile("theme"));
        StringBuilder strb = new StringBuilder();
        strb.append("INSERT IGNORE INTO `CRITERION` (`Cd_Criterion`, `Description`, `Label`, `Url`, `Rank`) VALUES\n");
        for (int i = 0; i < criteres.size(); i++) {
            strb.append("(\'").append(criteres.get(i).split("=")[0]);
            strb.append("\', \'").append(criteres.get(i).split("=")[1].replace("\'", ""));
            strb.append("\', \'").append(criteres.get(i).split("=")[0].substring(vpc.getReferentiel().length()).replace("-", "."));
            strb.append("\', \'\', ");
            strb.append(String.valueOf(i + 1)).append(")");
            if (i < criteres.size() - 1) {
                strb.append(",\n");
            } else if (i == criteres.size() - 1) {
                strb.append(";\n\n");
            }
        }
        strb.append("UPDATE `CRITERION` SET `Reference_Id_Reference` = (SELECT `Id_Reference` FROM `REFERENCE` WHERE `Cd_Reference` LIKE \'");
        strb.append(vpc.getReferentiel().replace(".", ""));
        strb.append("\') WHERE `Cd_Criterion` LIKE \'");
        strb.append(vpc.getReferentiel().replace(".", "")).append("-%\';\n");
        for (int i = 0; i < themesList.size(); i++) {
            strb.append("UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE \'");
            strb.append(themesList.get(i).split("=")[0]);
            strb.append("\') WHERE `Cd_Criterion` LIKE \'");
            strb.append(themesList.get(i).split("=")[0]).append("-%\';\n");
            if (i == themesList.size() - 1) {
                strb.append("\n");
            }
        }
        FileUtils.writeStringToFile(FileUtils.getFile(getSqlFile()), strb.toString(), true);
    }

    public void createSqlParameters() throws IOException {
        StringBuilder strb = new StringBuilder();
        strb.append("INSERT IGNORE INTO `PARAMETER` (`Id_Parameter_Element`, `Parameter_Value`, `Is_Default`) VALUES\n");
        strb.append("(5, \'").append(vpc.getReferentiel().replace(".", "")).append(";LEVEL_1\', b\'0\'),\n");
        strb.append("(5, \'").append(vpc.getReferentiel().replace(".", "")).append(";LEVEL_2\', b\'0\'),\n");
        strb.append("(5, \'").append(vpc.getReferentiel().replace(".", "")).append(";LEVEL_3\', b\'0\');\n\n");
        FileUtils.writeStringToFile(FileUtils.getFile(getSqlFile()), strb.toString(), true);
    }

    public void createSqlTest(LinkedList<String> levelColum,
            LinkedList<String> scopeColum) throws IOException {
        List<String> tests = FileUtils.readLines(getI18nDefaultFile("rule"));
        List<String> criteres = FileUtils.readLines(getI18nDefaultFile("criterion"));
        StringBuilder strb = new StringBuilder();
        strb.append("INSERT IGNORE INTO `TEST` (`Cd_Test`, `Description`, `Label`, `Rank`, `Weight`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Decision_Level`, `Id_Level`, `Id_Scope`, `Rule_Design_Url`, `No_Process`) VALUES\n");
        for (int i = 0; i < tests.size(); i += 2) {
            strb.append("(\'").append(tests.get(i).split("=")[0]).append("\', \'\', \'");
            String tmpLabel = tests.get(i).substring(vpc.getReferentiel().replace(".", "").length() + 1).split("=")[0].replace("-", ".");
            if (!isCriterionPresent) {
                strb.append(tmpLabel.substring(0, tmpLabel.length() - 2));
            } else {
                strb.append(tmpLabel);
            }
            strb.append("\', ").append(String.valueOf(i - (i / 2) + 1)).append(", ").append("\'1.0\', \'");
            strb.append(vpc.getReferentiel().replace(".", "").replace(" ", "").toLowerCase()).append("\', \'");
            strb.append(vpc.getPackageString()).append('.');
            strb.append(vpc.getReferentiel().replace(".", "").toLowerCase()).append(".");
            strb.append(String.valueOf(vpc.getClassRule().get(i - (i / 2)))).append("\', ");
            strb.append("NULL, ").append(getLevelColum(levelColum.get(i - (i / 2))));
            strb.append(", ").append(scopeColum.get(i - (i / 2)));
            strb.append(", \'\', b\'0\')");
            if (i < tests.size() - 2) {
                strb.append(",\n");
            } else if (i == tests.size() - 2) {
                strb.append(";\n\n");
            }
        }
        for (int i = 0; i < criteres.size(); i += 1) {
            strb.append("UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE \'");
            strb.append(criteres.get(i).split("=")[0]);
            strb.append("\') WHERE `Cd_Test` LIKE \'");
            strb.append(criteres.get(i).split("=")[0]).append("-%\';\n");
            if (i == criteres.size() - 1) {
                strb.append("\n");
            }
        }
        FileUtils.writeStringToFile(FileUtils.getFile(getSqlFile()), strb.toString(), true);
    }

    private Integer getLevelColum(String columLevel) {
        if (columLevel.equalsIgnoreCase("AA") || columLevel.equalsIgnoreCase("2")) {
            return 2;
        } else if (columLevel.equalsIgnoreCase("AAA") || columLevel.equalsIgnoreCase("3")) {
            return 3;
        } else {
            return 1;
        }
    }
}
