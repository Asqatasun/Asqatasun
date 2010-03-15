package org.opens.tanaguru.client;

import java.util.ResourceBundle;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.service.AuditService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 
 * @author ADEX
 */
public class FullClientImpl {

    private static final String APPLICATION_CONTEXT_FILE_PATH = "file:///etc/tanaguru/context/local-app/context-fullclient.xml";

    public static void main(String[] args) {
        if (args[0] != null && args[1] != null) {
            run(args[0], args[1]);
        } else {
            run("opens-all", null);
        }
    }

    private static void run(String bundleName, String url) throws BeansException {
        ResourceBundle parametersBundle = ResourceBundle.getBundle(bundleName);

        String siteUrl = parametersBundle.getString("siteUrl");

        String[] pageUrlList = null;
        if (url != null) {
            pageUrlList = new String[]{url};
        } else {
            pageUrlList = parametersBundle.getString("pageUrlList").split(", ");
        }

        String[] testCodeList = parametersBundle.getString("testCodeList").split(", ");

        ApplicationContext springApplicationContext = new FileSystemXmlApplicationContext(APPLICATION_CONTEXT_FILE_PATH);
        BeanFactory springBeanFactory = springApplicationContext;
        AuditService auditService = (AuditService) springBeanFactory.getBean("auditService");

        Audit audit = null;
        if (!siteUrl.isEmpty()) {
            audit = auditService.auditSite(siteUrl, pageUrlList, testCodeList);
        } else {
            audit = auditService.auditPage(pageUrlList[0], testCodeList);
        }

        if (audit.getStatus().equals(AuditStatus.ERROR)) {
            boolean hasContent = false;
            for (Content content : audit.getContentList()) {
                if (content instanceof SSP) {
                //We check that some content has been downloaded and has to
                //adapter. For the moment we ignore the return error code @TODO
                    if (!((SSP)content).getSource().isEmpty()) {
                        hasContent = true;
                        break;
                    }
                }
            }
            if (!hasContent){
                System.out.println("");
                System.out.println("An error occured while loading " + audit.getSubject().getURL());
                System.out.println("mark: 0");
                return;
            }
            if (audit.getGrossResultList().isEmpty()) {
                System.out.println("");
                System.out.println("An error occured while testing " + audit.getSubject().getURL());
                System.out.println("mark: 0");
                return;
            }
        }

        System.out.println("id: " + audit.getId());
        for (ProcessResult netResult : audit.getNetResultList()) {
            System.out.println(netResult.getTest().getCode() + ", " + netResult.getSubject().getURL() + ": " + netResult.getValue());
        }
        System.out.println("grossResultCount: " + audit.getGrossResultList().size());
        System.out.println("netResultCount: " + audit.getNetResultList().size());
        System.out.println("mark: " + audit.getMark());
     }
}
