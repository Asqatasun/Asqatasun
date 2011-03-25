package org.opens.tanaguru.client;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.service.AuditService;
import org.opens.tanaguru.service.AuditServiceListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 
 * @author ADEX
 */
public class FullClientImpl implements AuditServiceListener {

    private static final String APPLICATION_CONTEXT_FILE_PATH = "file:///etc/tanaguru/context/local-app/context-fullclient.xml";

    public static void main(String[] args) {
//        if (args[0] != null && args[1] != null) {
//            runExplicitThreaded(args[0], args[1]);
//        } else {
        List<String[]> testMap = new Vector<String[]>();
        for (int i = 0; i < 10; i++) {
            testMap.add(new String[]{"opens-all", null});
            testMap.add(new String[]{"enzolalay", null});
        }

        new FullClientImpl().run(testMap);
//        }
    }

    public FullClientImpl() {
        super();
    }

    public void run(List<String[]> testMap) throws BeansException {
        ApplicationContext springApplicationContext = new FileSystemXmlApplicationContext(APPLICATION_CONTEXT_FILE_PATH);
        BeanFactory springBeanFactory = springApplicationContext;
        AuditService auditService = (AuditService) springBeanFactory.getBean("auditService");
        auditService.add(this);

        for (String[] args : testMap) {
            ResourceBundle parametersBundle = ResourceBundle.getBundle(args[0]);
            String url = args[1];

            String siteUrl = parametersBundle.getString("siteUrl");

            String[] pageUrlList = null;
            if (url != null) {
                pageUrlList = new String[]{url};
            } else {
                pageUrlList = parametersBundle.getString("pageUrlList").split(", ");
            }

            String[] testCodeList = parametersBundle.getString("testCodeList").split(", ");

            Audit audit = null;
            if (!siteUrl.isEmpty()) {
                audit = auditService.auditSite(siteUrl, pageUrlList, testCodeList);
            } else {
                audit = auditService.auditPage(pageUrlList[0], testCodeList);
            }
            System.out.println("debut iteration (id): " + audit.getId());
        }
    }

    @Override
    public void auditCompleted(Audit audit) {
        System.out.println("fin iteration (id): " + audit.getId());
    }

    @Override
    public void auditCrashed(Audit audit, Throwable exception) {
        System.out.println("crash (id+message): " + audit.getId() + " " + exception.getMessage());
    }
}
