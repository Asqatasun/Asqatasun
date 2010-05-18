/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.rule.accessiweb20;

import java.util.ArrayList;
import java.util.List;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rule.test.AbstractRuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class Aw20Rule01021Test extends AbstractRuleImplementationTestCase {

    public Aw20Rule01021Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        ruleImplementationClassName =
                "org.opens.tanaguru.rule.accessiweb20.Aw20Rule01021";
    }

    @Override
    protected void setUpWebResourceMap() {
        webResourceMap.put("AW20.Test.01.02.01-1Passed-01",
                webResourceFactory.createPage(
                testcasesFilePath + "AW22/AW20.Test.01.02.01-1Passed-01.html"));
        webResourceMap.put("AW20.Test.01.02.01-1Passed-02",
                webResourceFactory.createPage(
                testcasesFilePath + "AW22/AW20.Test.01.02.01-1Passed-02.html"));
        webResourceMap.put("AW20.Test.01.02.01-1Passed-03",
                webResourceFactory.createPage(
                testcasesFilePath + "AW22/AW20.Test.01.02.01-1Passed-03.html"));
        webResourceMap.put("AW20.Test.01.02.01-1Passed-04",
                webResourceFactory.createPage(
                testcasesFilePath + "AW22/AW20.Test.01.02.01-1Passed-04.html"));
        webResourceMap.put("AW20.Test.01.02.01-2Failed-01",
                webResourceFactory.createPage(
                testcasesFilePath + "AW22/AW20.Test.01.02.01-2Failed-01.html"));
        webResourceMap.put("AW20.Test.01.02.01-2Failed-02",
                webResourceFactory.createPage(
                testcasesFilePath + "AW22/AW20.Test.01.02.01-2Failed-02.html"));
        webResourceMap.put("AW20.Test.01.02.01-2Failed-03",
                webResourceFactory.createPage(
                testcasesFilePath + "AW22/AW20.Test.01.02.01-2Failed-03.html"));
        webResourceMap.put("AW20.Test.01.02.01-3NMI-01",
                webResourceFactory.createPage(
                testcasesFilePath + "AW22/AW20.Test.01.02.01-3NMI-01.html"));
        webResourceMap.put("AW20.Test.01.02.01-3NMI-02",
                webResourceFactory.createPage(
                testcasesFilePath + "AW22/AW20.Test.01.02.01-3NMI-02.html"));
        webResourceMap.put("AW20.Test.01.02.01-4NA-01",
                webResourceFactory.createPage(
                testcasesFilePath + "AW22/AW20.Test.01.02.01-4NA-01.html"));
        webResourceMap.put("AW20.Test.01.02.01-4NA-02",
                webResourceFactory.createPage(
                testcasesFilePath + "AW22/AW20.Test.01.02.01-4NA-02.html"));
        webResourceMap.put("AW20.Test.01.02.01-4NA-03",
                webResourceFactory.createPage(
                testcasesFilePath + "AW22/AW20.Test.01.02.01-4NA-03.html"));

        setUpRelatedContentMap();
    }

    private void setUpRelatedContentMap(){

        List<String> relatedContent1 = new ArrayList<String>();
        relatedContent1.add("images/decorative-black.jpg");
        relatedContent1.add("images/decorative-white.jpg");
        relatedContentMap.put(webResourceMap.get("AW20.Test.01.02.01-1Passed-01"), relatedContent1);

        List<String> relatedContent2 = new ArrayList<String>();
        relatedContent2.add("images/mono-dimension-heigth.jpg");
        relatedContent2.add("images/mono-dimension-width.jpg");
        relatedContentMap.put(webResourceMap.get("AW20.Test.01.02.01-1Passed-02"), relatedContent2);

        List<String> relatedContent3 = new ArrayList<String>();
        relatedContent3.add("images/decorative-black.jpg");
        relatedContent3.add("images/decorative-red.jpg");
        relatedContent3.add("images/informative1.jpg");
        relatedContentMap.put(webResourceMap.get("AW20.Test.01.02.01-1Passed-03"), relatedContent3);

        List<String> relatedContent4 = new ArrayList<String>();
        relatedContent4.add("images/decorative-black.jpg");
        relatedContentMap.put(webResourceMap.get("AW20.Test.01.02.01-1Passed-04"), relatedContent4);

        List<String> relatedContent5 = new ArrayList<String>();
        relatedContent5.add("images/decorative-black.jpg");
        relatedContent5.add("images/decorative-red.jpg");
        relatedContentMap.put(webResourceMap.get("AW20.Test.01.02.01-2Failed-01"), relatedContent5);

        List<String> relatedContent6 = new ArrayList<String>();
        relatedContent6.add("images/mono-dimension-heigth.jpg");
        relatedContent6.add("images/mono-dimension-width.jpg");
        relatedContentMap.put(webResourceMap.get("AW20.Test.01.02.01-2Failed-02"), relatedContent6);

        List<String> relatedContent7 = new ArrayList<String>();
        relatedContent7.add("images/mono-dimension-heigth.jpg");
        relatedContent7.add("images/mono-dimension-width.jpg");
        relatedContent7.add("http://www.tanaguru.org/sites/default/files/pictures/picture-1.jpg");
        relatedContentMap.put(webResourceMap.get("AW20.Test.01.02.01-2Failed-03"), relatedContent7);

        List<String> relatedContent8 = new ArrayList<String>();
        relatedContent8.add("images/decorative-black.jpg");
        relatedContent8.add("images/informative1.jpg");
        relatedContentMap.put(webResourceMap.get("AW20.Test.01.02.01-3NMI-01"), relatedContent8);

        List<String> relatedContent9 = new ArrayList<String>();
        relatedContent9.add("images/informative1.jpg");
        relatedContent9.add("images/informative2.gif");
        relatedContentMap.put(webResourceMap.get("AW20.Test.01.02.01-3NMI-02"), relatedContent9);
    }

    @Override
    protected void setProcess() {
        assertEquals(TestSolution.NEED_MORE_INFO,
                processPageTest("AW20.Test.01.02.01-1Passed-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                processPageTest("AW20.Test.01.02.01-1Passed-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                processPageTest("AW20.Test.01.02.01-1Passed-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                processPageTest("AW20.Test.01.02.01-1Passed-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                processPageTest("AW20.Test.01.02.01-2Failed-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                processPageTest("AW20.Test.01.02.01-2Failed-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                processPageTest("AW20.Test.01.02.01-2Failed-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                processPageTest("AW20.Test.01.02.01-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                processPageTest("AW20.Test.01.02.01-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW20.Test.01.02.01-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW20.Test.01.02.01-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW20.Test.01.02.01-4NA-03").getValue());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW20.Test.01.02.01-1Passed-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW20.Test.01.02.01-1Passed-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW20.Test.01.02.01-1Passed-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW20.Test.01.02.01-1Passed-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW20.Test.01.02.01-2Failed-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW20.Test.01.02.01-2Failed-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW20.Test.01.02.01-2Failed-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW20.Test.01.02.01-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW20.Test.01.02.01-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW20.Test.01.02.01-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW20.Test.01.02.01-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW20.Test.01.02.01-4NA-03").getValue());
    }
}
