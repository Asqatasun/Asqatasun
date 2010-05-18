package org.opens.tanaguru.ws.client;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.opens.tanaguru.ws.AuditImpl;
import org.opens.tanaguru.ws.AuditWeb;
import org.opens.tanaguru.ws.AuditWebService;
import org.opens.tanaguru.ws.DefiniteResultImpl;

/**
 *
 * @author ADEX
 */
public class AuditWebServiceClient {

    public static void main(String[] args) {
        run("opens-all");
    }

    static void run(String bundleName) {
        ResourceBundle parametersBundle = ResourceBundle.getBundle(bundleName);

        String siteUrl = parametersBundle.getString("siteUrl");
        String[] pageUrlArray = parametersBundle.getString("pageUrlList").split(", ");
        String[] testCodeArray = parametersBundle.getString("testCodeList").split(", ");

        List<String> pageUrlList = new ArrayList<String>();
        for (String pageUrl : pageUrlArray) {
            pageUrlList.add(pageUrl);
        }
        List<String> testCodeList = new ArrayList<String>();
        for (String testCode : testCodeArray) {
            testCodeList.add(testCode);
        }

        AuditWebService auditWebService = new AuditWebService();
        AuditWeb auditWeb = auditWebService.getAuditWebPort();

        AuditImpl audit = null;
        if (!siteUrl.isEmpty()) {
            audit = auditWeb.auditSiteWithoutCrawler(siteUrl, pageUrlList, testCodeList);
        } else {
            audit = auditWeb.auditPage(pageUrlArray[0], testCodeList);
        }

        System.out.println("id: " + audit.getId());
        for (DefiniteResultImpl netResult : audit.getNetResultList().getDefiniteResultImpl()) {
            System.out.println(netResult.getTestImpl().getRuleClassName() + ", " + ((netResult.getPageImpl() != null) ? netResult.getPageImpl().getURL() : ((netResult.getSiteImpl() != null) ? netResult.getSiteImpl().getURL() : "")) + ": " + netResult.getDefiniteValue());
        }
        System.out.println("grossResultCount: " + audit.getGrossResultList().getIndefiniteResultImplOrDefiniteResultImpl().size());
        System.out.println("netResultCount: " + audit.getNetResultList().getDefiniteResultImpl().size());
        System.out.println("mark: " + audit.getMark());
        System.out.println();
    }
}
