package org.opens.tanaguru.client;

import java.util.ArrayList;
import java.util.List;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.factory.reference.TestFactory;
import org.opens.tanaguru.service.ContentAdapterService;
import org.opens.tanaguru.service.AnalyserService;
import org.opens.tanaguru.service.ConsolidatorService;
import org.opens.tanaguru.service.CrawlerService;
import org.opens.tanaguru.service.ContentLoaderService;
import org.opens.tanaguru.service.ProcessorService;
import org.opens.tanaguru.service.RuleImplementationLoaderService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author ADEX
 */
public class SimpleClientImpl {

    private static String RULE_ARCHIVE_NAME = "AccessiWeb";
    private static String RULE_PACKAGE_NAME = "org.opens.tanaguru.rule.accessiweb.";

    private static Test createTest(String ruleClassName, TestFactory testFactory) {
        Test test = testFactory.create();
        test.setRuleArchiveName(RULE_ARCHIVE_NAME);
        test.setRuleClassName(RULE_PACKAGE_NAME + ruleClassName);
        return test;
    }
    static String testcasesBase = "file:///home/nselly/NetBeansProjects/tanaguru/testcases";

    private static String[] getPageURLList() {
        return new String[]{
                    testcasesBase + "/AW11.Test.02.04.a-2Failed-02.html",
                    testcasesBase + "/AW11.Test.02.04.a-2Failed-03.html",
                    testcasesBase + "/AW11.Test.02.04.a-4NA-01.html",
                    testcasesBase + "/AW11.Test.06.01.a-1Passed-01.html",
                    testcasesBase + "/AW11.Test.06.01.a-1Passed-02.html",
                    testcasesBase + "/AW11.Test.06.01.a-2Failed-01.html",
                    testcasesBase + "/AW11.Test.06.01.a-2Failed-02.html",
                    testcasesBase + "/AW11.Test.06.01.a-3NMI-01.html",
                    testcasesBase + "/AW11.Test.06.01.a-4NA-01.html",
                    testcasesBase + "/AW11.Test.06.01.b-4NA-01.html",
                    testcasesBase + "/AW11.Test.06.04.a-2Failed-01.html",
                    testcasesBase + "/AW11.Test.06.04.a-2Failed-02.html",
                    testcasesBase + "/AW11.Test.06.05.c-1Passed-01.html",
                    testcasesBase + "/AW11.Test.06.05.c-1Passed-02.html",
                    testcasesBase + "/AW11.Test.06.05.c-2Failed-01.html",
                    testcasesBase + "/AW11.Test.06.05.c-2Failed-02.html",
                    testcasesBase + "/AW11.Test.06.05.c-3NMI-01.html",
                    testcasesBase + "/AW11.Test.06.05.c-4NA-01.html",
                    testcasesBase + "/AW11.Test.10.05.a-1Passed-01.html",
                    testcasesBase + "/AW11.Test.10.05.a-1Passed-02.html",
                    testcasesBase + "/AW11.Test.10.05.a-1Passed-03.html",
                    testcasesBase + "/AW11.Test.10.05.a-2Failed-01a.html",
                    testcasesBase + "/AW11.Test.10.05.a-2Failed-01b.html",
                    testcasesBase + "/AW11.Test.10.05.a-2Failed-01c.html",
                    testcasesBase + "/AW11.Test.10.05.a-2Failed-01d.html",
                    testcasesBase + "/AW11.Test.10.05.a-2Failed-01e.html",
                    testcasesBase + "/AW11.Test.10.05.a-2Failed-02a.html",
                    testcasesBase + "/AW11.Test.10.05.a-2Failed-02b.html",
                    testcasesBase + "/AW11.Test.10.05.a-2Failed-02c.html",
                    testcasesBase + "/AW11.Test.10.05.a-2Failed-02d.html",
                    testcasesBase + "/AW11.Test.10.05.a-2Failed-02e.html",
                    testcasesBase + "/AW11.Test.10.05.a-2Failed-03a.html",
                    testcasesBase + "/AW11.Test.10.05.a-2Failed-03b.html",
                    testcasesBase + "/AW11.Test.10.05.a-2Failed-03c.html",
                    testcasesBase + "/AW11.Test.10.05.a-2Failed-03d.html",
                    testcasesBase + "/AW11.Test.10.05.a-2Failed-03e.html",
                    testcasesBase + "/AW11.Test.10.05.a-4NA-01.html",
                    testcasesBase + "/AW11.Test.12.03.a-1Passed-01-page01.html",
                    testcasesBase + "/AW11.Test.12.03.a-1Passed-01-page02.html",
                    testcasesBase + "/AW11.Test.12.03.a-2Failed-01-page01.html",
                    testcasesBase + "/AW11.Test.12.03.a-2Failed-01-page02.html",
                    testcasesBase + "/AW11.Test.12.03.a-2Failed-02-page01.html",
                    testcasesBase + "/AW11.Test.12.03.a-2Failed-02-page02.html",
                    testcasesBase + "/AW11.Test.12.03.a-2Failed-03-page01.html",
                    testcasesBase + "/AW11.Test.12.03.a-2Failed-03-page02.html",
                    testcasesBase + "/AW11.Test.12.03.a-4NA-01.html",
                    testcasesBase + "/AW11.Test.13.02.a-2Failed-01.html",
                    testcasesBase + "/AW11.Test.13.02.a-2Failed-02.html",
                    testcasesBase + "/AW11.Test.13.02.a-2Failed-03.html",
                    testcasesBase + "/AW11.Test.13.02.a-4NA-01.html"
                };
    }

    private static List<Test> getTestSet(BeanFactory springBeanFactory) {
        TestFactory testFactory = (TestFactory) springBeanFactory.getBean("testFactory");

        List<Test> testSet = new ArrayList<Test>();

        testSet.add(createTest("Rule0101a", testFactory));
        testSet.add(createTest("Rule0101b", testFactory));
        testSet.add(createTest("Rule0101e", testFactory));
        testSet.add(createTest("Rule0105b", testFactory));
        testSet.add(createTest("Rule0204a", testFactory));
        testSet.add(createTest("Rule0601a", testFactory));
        testSet.add(createTest("Rule0601b", testFactory));
        testSet.add(createTest("Rule0602a", testFactory));
        testSet.add(createTest("Rule0604d", testFactory));
        testSet.add(createTest("Rule0605c", testFactory));
        testSet.add(createTest("Rule0807a", testFactory));
        testSet.add(createTest("Rule1001a", testFactory));
        testSet.add(createTest("Rule1005a", testFactory));
        testSet.add(createTest("Rule1109a", testFactory));
        testSet.add(createTest("Rule1203c", testFactory));
        testSet.add(createTest("Rule1303a", testFactory));

        return testSet;
    }

    public static void main(String[] args) {
        ApplicationContext springApplicationContext = new ClassPathXmlApplicationContext(
                "/META-INF/context-simpleclient.xml");
        BeanFactory springBeanFactory = springApplicationContext;

        CrawlerService crawlerService = (CrawlerService) springBeanFactory.getBean("crawlerService");
        ContentLoaderService contentLoaderService = (ContentLoaderService) springBeanFactory.getBean("contentLoaderService");
        ContentAdapterService contentAdapterService = (ContentAdapterService) springBeanFactory.getBean("contentAdapterService");
        RuleImplementationLoaderService ruleImplementationLoaderService = (RuleImplementationLoaderService) springBeanFactory.getBean("ruleImplementationLoaderService");
        ProcessorService processorService = (ProcessorService) springBeanFactory.getBean("processorService");
        ConsolidatorService consolidatorService = (ConsolidatorService) springBeanFactory.getBean("consolidatorService");
        AnalyserService analyserService = (AnalyserService) springBeanFactory.getBean("analyserService");

        String siteURL = null;// "file:///C:/projects/evalaccess/testcases";
        String[] pageURLList = getPageURLList();

        WebResource webResource = null;// XXX

        List<Test> testList = getTestSet(springBeanFactory);

        List<Content> contentList = contentLoaderService.loadContent(webResource);

        contentList = contentAdapterService.adaptContent(contentList);

        List<ProcessResult> grossResultList = processorService.process(
                contentList, testList);

        List<ProcessResult> netResultList = consolidatorService.consolidate(
                grossResultList, testList);

        float mark = analyserService.analyse(netResultList);

        for (ProcessResult netResult : netResultList) {
            System.out.println(netResult.getTest().getRuleClassName() + ", " + netResult.getSubject().getURL() + ": " + netResult.getValue());
        }
        System.out.println("mark: " + mark);
    }
}
