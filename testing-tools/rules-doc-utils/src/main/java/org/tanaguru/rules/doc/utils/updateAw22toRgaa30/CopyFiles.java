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
package org.tanaguru.rules.doc.utils.updateAw22toRgaa30;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 *
 * @author alingua
 */
public class CopyFiles {

    private char delimiter = 'ø';

    private Iterable<CSVRecord> getCsv(ResourceBundle resourceBundle) {
        // we parse the csv file to extract the first line and get the headers 
        LineIterator lineIterator;
        try {
            lineIterator = FileUtils.lineIterator(FileUtils.getFile(
                    resourceBundle.getString("export.csvPath")));
        } catch (IOException ex) {
            Logger.getLogger(CopyFiles.class.getName()).log(Level.SEVERE, null, ex);
            lineIterator = null;
        }
        String[] csvHeaders = lineIterator.next().split(String.valueOf(delimiter));

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
            Logger.getLogger(CopyFiles.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(CopyFiles.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void copyFilesAvailable() throws IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("parameters");
        for (CSVRecord record : getCsv(resourceBundle)) {
            String oldTest = record.get("originalReference");
            String newTest = record.get("targetReference");
            String state = record.get("can_copy");
            if (state.equalsIgnoreCase("1")) {
                copyRuleClassWithValidState(oldTest, newTest, resourceBundle);
                copyTestCasesWithValidState(oldTest, newTest, resourceBundle);
                copyRuleUnitTestWithValidState(oldTest, newTest, resourceBundle, false);
            }
            if (state.equalsIgnoreCase("2")) {
                copyRuleClassWithValidState(oldTest, newTest, resourceBundle);
                copyRuleUnitTestWithValidState(oldTest, newTest, resourceBundle, true);
                copyTestCasesWithValidState(oldTest, newTest, resourceBundle);
            }
        }
    }

    public void copyRuleClassWithValidState(String originalTest, String targetTest, ResourceBundle rb) throws IOException {
        String testCode = originalTest.split("-")[1];
        String newTestCode = targetTest.split("-")[1];
        String[] classCode = normalize2DigitsOld(testCode);
        String[] classCode0 = normalize2Digits(newTestCode);
        File classFile = FileUtils.getFile(rb.getString("export.pathUntilClassCode")
                + "/src/main/java/" + rb.getString("export.groupId").replace(".", "/")
                + "/tanaguru/rules/" + rb.getString("export.originalReferentialPackage") + "/"
                + rb.getString("export.originalReferential") + "Rule"
                + classCode[0] + classCode[1] + classCode[2] + ".java");
        LineIterator oldRefFile = FileUtils.lineIterator(classFile);
        StringBuilder strb = new StringBuilder();
        while (oldRefFile.hasNext()) {
            strb.append(oldRefFile.next().replace(rb.getString("export.originalReferential"),
                    rb.getString("export.targetReferential"))
                    .replace(rb.getString("export.originalReferentialPackage"),
                    rb.getString("export.targetReferentialPackage"))
                    .replace("2013", "2014")
                    .replace(rb.getString("export.originalLink"),
                    rb.getString("export.targetLink"))
                    .replace(rb.getString("export.originalReferentialName"),
                    rb.getString("export.targetReferentialName"))
                    .replace(classCode[0] + classCode[1] + classCode[2],
                    classCode0[0] + classCode0[1] + classCode0[2]));
            strb.append("\n");
        }
        FileUtils.writeStringToFile(new File(rb.getString("export.pathUntilNewCode")
                + "/src/main/java/org/opens/tanaguru/rules/"
                + rb.getString("export.targetReferentialPackage") + "/"
                + rb.getString("export.targetReferential")
                + "Rule" + classCode0[0] + classCode0[1] + classCode0[2]
                + ".java"), strb.toString());
    }

    public void copyRuleUnitTestWithValidState(String originalTest, String targetTest, ResourceBundle rb, boolean notTested) throws IOException {
        String testCode = originalTest.split("-")[1];
        String newTestCode = targetTest.split("-")[1];
        String[] classCode = normalize2DigitsOld(testCode);
        String[] classCodeFull = normalize2Digits(testCode);
        String oldTestCaseCode = getOldTestCaseCode(classCodeFull);
        String[] classCode0 = normalize2Digits(newTestCode);
        File classFile = FileUtils.getFile(rb.getString("export.pathUntilClassCode")
                + "/src/test/java/" + rb.getString("export.groupId").replace(".", "/")
                + "/tanaguru/rules/" + rb.getString("export.originalReferentialPackage") + "/"
                + rb.getString("export.originalReferential") + "Rule"
                + classCode[0] + classCode[1] + classCode[2] + "Test.java");
        Pattern p = Pattern.compile("^.*([0-9]{5})");
        Pattern p2 = Pattern.compile("^.*([0-9]{6})");
        LineIterator oldRefFile = FileUtils.lineIterator(classFile);
        StringBuilder strb = new StringBuilder();
        System.out.println(testCode);
        while (oldRefFile.hasNext()) {
            String line = oldRefFile.next();
            line = line.replace(rb.getString("export.originalReferential"),
                    rb.getString("export.targetReferential"))
                    .replace(rb.getString("export.originalReferential").toUpperCase(),
                    rb.getString("export.targetReferential"))
                    .replace(rb.getString("export.originalReferentialPackage"),
                    rb.getString("export.targetReferentialPackage"))
                    .replace("2013", "2014")
                    .replace(rb.getString("export.originalLink"),
                    rb.getString("export.targetLink"))
                    .replace(rb.getString("export.originalReferentialName"),
                    rb.getString("export.targetReferentialName"))
                    .replace(classCode[0] + classCode[1] + classCode[2],
                    classCode0[0] + classCode0[1] + classCode0[2])
                    .replace(oldTestCaseCode,
                    classCode0[0] + "." + classCode0[1] + "." + classCode0[2])
                    .replace(testCode,
                    classCode0[0] + "." + classCode0[1] + "." + classCode0[2]);
            Matcher m = p.matcher(line);
            if (m.find()) {
                Matcher m2 = p2.matcher(line);
                if (!m2.find()) {
                    line = line.replace(m.group(1), getAllDigitsTestCode(m.group(1)));
                }
            }
            strb.append(line);
            strb.append("\n");
        }
        String result = strb.toString();
        if (notTested == true) {
            if (strb.toString().contains("PASSED")) {
                result = strb.toString().replace("PASSED", "NOT_TESTED");
            } else if (strb.toString().contains("FAILED")) {
                result = strb.toString().replace("FAILED", "NOT_TESTED");
            } else if (strb.toString().contains("NOT_APPLICABLE")) {
                result = strb.toString().replace("NOT_APPLICABLE", "NOT_TESTED");
            }
        }
        FileUtils.writeStringToFile(
                new File(rb.getString("export.pathUntilNewCode")
                + "/src/test/java/org/opens/tanaguru/rules/"
                + rb.getString("export.targetReferentialPackage") + "/"
                + rb.getString("export.targetReferential")
                + "Rule" + classCode0[0] + classCode0[1] + classCode0[2]
                + "Test.java"), result);
    }

    public void copyTestCasesWithValidState(String originalTest, String targetTest, ResourceBundle rb) throws IOException {
        String testCode = originalTest.split("-")[1];
        String newTestCode = targetTest.split("-")[1];
        String[] classCode = normalize2DigitsOld(testCode);
        String[] classCode0 = normalize2Digits(newTestCode);
        File directory = FileUtils.getFile(rb.getString("export.pathUntilClassCode")
                + "/src/test/resources/testcases/" + rb.getString("export.originalReferentialPackage") + "/"
                + rb.getString("export.originalReferential") + "Rule"
                + classCode[0] + classCode[1] + classCode[2]);
        String[] extensions = {"html", "json", "css"};
        Collection<File> files = FileUtils.listFiles(directory, extensions, true);
        for (File testCases : files) {
            String[] state = testCases.getName().split("-");
            LineIterator oldRefFile = FileUtils.lineIterator(testCases);
            StringBuilder strb = new StringBuilder();
            while (oldRefFile.hasNext()) {
                strb.append(oldRefFile.next().replace(rb.getString("export.originalReferential"),
                        rb.getString("export.targetReferential"))
                        .replace(rb.getString("export.originalReferential").toUpperCase(),
                        rb.getString("export.targetReferential"))
                        .replace(rb.getString("export.originalReferentialPackage"),
                        rb.getString("export.targetReferentialPackage"))
                        .replace("2013", "2014")
                        .replace(rb.getString("export.originalLink"),
                        rb.getString("export.targetLink"))
                        .replace(rb.getString("export.originalReferentialName"),
                        rb.getString("export.targetReferentialName"))
                        .replace(classCode[0] + classCode[1] + classCode[2],
                        classCode0[0] + classCode0[1] + classCode0[2])
                        .replace(testCode, 
                        classCode0[0] + "." + classCode0[1] + "." + classCode0[2]));
                strb.append("\n");
            }
            if (testCases.getName().endsWith(".css")) {
                FileUtils.writeStringToFile(new File(rb.getString("export.pathUntilNewCode")
                        + "/src/test/resources/testcases/"
                        + rb.getString("export.targetReferentialPackage") + "/"
                        + rb.getString("export.targetReferential") + "Rule"
                        + classCode0[0] + classCode0[1] + classCode0[2] + "/css/"
                        + rb.getString("export.targetReferential")
                        + ".Test."
                        + classCode0[0] + "." + classCode0[1] + "." + classCode0[2]
                        + "-" + state[1] + "-" + state[2]), strb.toString());

            } else {
                FileUtils.writeStringToFile(new File(rb.getString("export.pathUntilNewCode")
                        + "/src/test/resources/testcases/"
                        + rb.getString("export.targetReferentialPackage") + "/"
                        + rb.getString("export.targetReferential") + "Rule"
                        + classCode0[0] + classCode0[1] + classCode0[2] + "/"
                        + rb.getString("export.targetReferential")
                        + ".Test."
                        + classCode0[0] + "." + classCode0[1] + "." + classCode0[2]
                        + "-" + state[1] + "-" + state[2]), strb.toString());
            }
        }
    }

    private String[] normalize2DigitsOld(String testCode) {
        String[] testCodeArray = testCode.split("\\.");
        String thematique = testCodeArray[0];
        String critereCode = testCodeArray[1];
        if (testCodeArray[0].length() == 1) {
            thematique = "0" + testCodeArray[0];
        }
        if (testCodeArray[1].length() == 1) {
            critereCode = "0" + testCodeArray[1];
        }
        String[] classCode = {thematique, critereCode, testCodeArray[2]};
        return classCode;
    }

    private String getAllDigitsTestCode(String testCode) {
        return testCode.substring(0, testCode.length() - 1)
                + "0" + testCode.substring(testCode.length() - 1, testCode.length());
    }

    private String getOldTestCaseCode(String[] testCase) {
        return testCase[0] + "." + testCase[1] + "." + testCase[2];
    }

    private String[] normalize2Digits(String testCode) {
        String[] testCodeArray = testCode.split("\\.");
        String thematique = testCodeArray[0];
        String critereCode = testCodeArray[1];
        String testCode0 = testCodeArray[2];
        if (testCodeArray[0].length() == 1) {
            thematique = "0" + testCodeArray[0];
        }
        if (testCodeArray[1].length() == 1) {
            critereCode = "0" + testCodeArray[1];
        }
        if (testCodeArray[2].length() == 1) {
            testCode0 = "0" + testCodeArray[2];
        }
        String[] classCode = {thematique, critereCode, testCode0};
        return classCode;
    }
}
