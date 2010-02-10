package org.opens.tanaguru.client;

import java.util.ResourceBundle;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.service.AuditService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author ADEX
 */
public class FullClientImpl {

    public static void main(String[] args) {
//        run("one-one");
//        run("one-two");
//        run("two-one");
//        run("two-two");
        run("opens-all");
//        run("one-all");
//        run("two-all");
    }

    private static void run(String bundleName) throws BeansException {
        ResourceBundle parametersBundle = ResourceBundle.getBundle(bundleName);

        String siteUrl = parametersBundle.getString("siteUrl");
        String[] pageUrlList = parametersBundle.getString("pageUrlList").split(", ");
        String[] testCodeList = parametersBundle.getString("testCodeList").split(", ");

        ApplicationContext springApplicationContext = new ClassPathXmlApplicationContext("/META-INF/context-fullclient.xml");
        BeanFactory springBeanFactory = springApplicationContext;
        AuditService auditService = (AuditService) springBeanFactory.getBean("auditService");

        Audit audit = null;
        if (!siteUrl.isEmpty()) {
            audit = auditService.auditSite(siteUrl, pageUrlList, testCodeList);
        } else {
            audit = auditService.auditPage(pageUrlList[0], testCodeList);
        }

        System.out.println("id: " + audit.getId());
        for (ProcessResult netResult : audit.getNetResultList()) {
            System.out.println(netResult.getTest().getCode() + ", " + netResult.getSubject().getURL() + ": " + netResult.getValue());
        }
        System.out.println("grossResultCount: " + audit.getGrossResultList().size());
        System.out.println("netResultCount: " + audit.getNetResultList().size());
        System.out.println("mark: " + audit.getMark());
        System.out.println();
    }
}
